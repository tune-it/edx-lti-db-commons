package com.tuneit.courses.lab1.db;

import com.tuneit.courses.lab1.db.schema.Schema;
import org.postgresql.core.SqlCommand;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author serge
 */
public class SelectProcessor {


    private static final long timeout_limit = 20000l;
    private static final String session_timeout_command =
            "SET statement_timeout = " + timeout_limit + ";";
    private static final String TABLE_CLASSES = "table";
    private static final String ROW_CLASSES = "row";
    private static final String CELL_CLASSES = "cell";
    private static final String HEADER_CLASSES = "header";
    static Pattern rowcount_pattern = Pattern.compile("<Actual-Rows>(.*?)</Actual-Rows>");
    static Pattern executiontime_pattern = Pattern.compile("<Execution-Time>(.*?)</Execution-Time>");

    public SelectResult executeQuery(Schema schema, String sql) {
        return executeQuery(schema, sql, 100, false);
    }

    /**
     * @param schema        - schema name to generate queries
     * @param query         - sql to execute
     * @param maxRowLimit   Limits row in output, 0 - zero rows, -1 unlimited
     * @param hasHtmlOutput - save html output to new StringBuilder in SelectResult
     * @return SelectResult - object with encapsulated results
     */
    public SelectResult executeQuery(final Schema schema, final String query,
                                     final int maxRowLimit, final boolean hasHtmlOutput) {

        SelectResult selectResult = new SelectResult();
        if (hasHtmlOutput) {
            selectResult.setHtmlOutput(hasHtmlOutput);
            selectResult.setHtmlRows(new StringBuilder());
            selectResult.getHtmlRows().append("<table id=sqlresult class=\"").append(TABLE_CLASSES).append("\">\n");
        }

        long controlSum = 0;

        ResultSet resultSet = null;
        try (Connection connection = schema.getConnection();
             Statement statement = connection.createStatement()
        ) {
            statement.setMaxRows(maxRowLimit);
            statement.execute(session_timeout_command);

            if (checkTimeoutByUsingExplain(statement, query, selectResult)) {
                throw new SQLException("ERROR: proactive canceling statement due to possible timeout exceeding",
                        Integer.toString(SelectResult.TIMEOUT), SelectResult.TIMEOUT);
            }

            resultSet = statement.executeQuery(query);

            if (hasHtmlOutput) {
                setHtmlHeaderForSelectResult(selectResult, resultSet);
            }

            controlSum += countingControlSum(resultSet, hasHtmlOutput, selectResult);

            selectResult.setResultCode(SelectResult.OK);
            selectResult.setErrorMessage("None");
        } catch (SQLException exception) {
            try {
                selectResult.setResultCode(Integer.parseInt(exception.getSQLState()));
                selectResult.setErrorMessage(exception.getMessage().replace('\n', ' '));
            } catch (NumberFormatException esqlstate) {
                selectResult.setResultCode(SelectResult.INTERNAL_ERROR);
                selectResult.setErrorMessage("Unparseable SQLState=" + exception.getSQLState() + " Message=" + exception.getMessage().replace('\n', ' '));
                Logger.getLogger(SelectProcessor.class.getName()).log(Level.SEVERE, selectResult.getErrorMessage());
            }
        } catch (NumberFormatException en) {
            selectResult.setErrorMessage("Internal error, cant parse row count or execution time in explain statement");
            selectResult.setResultCode(SelectResult.INTERNAL_ERROR);
            Logger.getLogger(SelectProcessor.class.getName()).log(Level.SEVERE, selectResult.getErrorMessage(), en);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException ignored) {
            }
        }

        if (selectResult.getResultCode() == SelectResult.OK) {
            //update rows checksum with row count
            controlSum += selectResult.getRowCount();
            //set checksum in results
            selectResult.setResultCheckSum(Long.toString(controlSum));
        }
        if (hasHtmlOutput) {
            if (selectResult.getResultCode() != SelectResult.OK) {
                selectResult.getHtmlRows().delete(0, selectResult.getHtmlRows().length());
                selectResult.getHtmlRows().append("<H1>").append(selectResult.getErrorMessage()).append("</H1>");
            } else {
                selectResult.getHtmlRows().append("</table>\n");
            }
        }
        return selectResult;
    }

    private void setHtmlHeaderForSelectResult(SelectResult selectResult, ResultSet resultSet) throws SQLException {
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        int numberColumns = resultSetMetaData.getColumnCount();

        selectResult.getHtmlRows().append("<tr class=\"").append(ROW_CLASSES).append("\">");
        for (int i = 1; i <= numberColumns; i++) {
            String fieldLabel = resultSetMetaData.getColumnLabel(i);
            selectResult.getHtmlRows().append("<th class=\"").append(HEADER_CLASSES).append("\">")
                    .append(fieldLabel).append("</th>");
        }
        selectResult.getHtmlRows().append("</tr>\n");
    }

    private long countingControlSum(ResultSet resultSet, boolean hasHtmlOutput, SelectResult selectResult) throws SQLException {
        long controlSum = 0;
        long numberColumns = resultSet.getMetaData().getColumnCount();

        while (resultSet.next()) {
            if (hasHtmlOutput)
                selectResult.getHtmlRows().append("<tr class=\"").append(ROW_CLASSES).append("\">");
            for (int i = 1; i <= numberColumns; i++) {
                String field = resultSet.getString(i);
                if (field != null) {
                    controlSum += field.hashCode();
                    if (hasHtmlOutput)
                        selectResult.getHtmlRows().append("<td class=\"").append(CELL_CLASSES).append("\">")
                                .append(field).append("</td>");
                }
            }
            if (hasHtmlOutput)
                selectResult.getHtmlRows().append("</tr>\n");
        }

        return controlSum;
    }

    private boolean checkTimeoutByUsingExplain(Statement statement, String sql, SelectResult selectResult) throws SQLException {
        try (ResultSet explainSet = statement.executeQuery("EXPLAIN (analyse on, format xml) " + sql)) {
            while (explainSet.next()) {
                String exrow = explainSet.getString(1);
                Matcher rowcount_matcher = rowcount_pattern.matcher(exrow);
                if (rowcount_matcher.find()) {
                    selectResult.setRowCount(Long.parseLong(rowcount_matcher.group(1)));
                }
                Matcher executetime_matcher = executiontime_pattern.matcher(exrow);
                if (executetime_matcher.find()) {
                    selectResult.setExecutionTime(Double.parseDouble(executetime_matcher.group(1)));
                }
            }

            return selectResult.getExecutionTime() > (double) timeout_limit;
        }
    }


}
