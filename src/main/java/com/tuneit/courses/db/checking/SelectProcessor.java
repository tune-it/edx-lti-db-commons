package com.tuneit.courses.db.checking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Execute sql query for task answer validation
 * @author serge
 */
@Component
public class SelectProcessor {
    private final long TIMEOUT_LIMIT = 20000L;
    private final String SESSION_TIMEOUT_COMMAND = "SET statement_timeout = " + TIMEOUT_LIMIT + ";";

    private final String TABLE_CLASSES = "table";
    private final String ROW_CLASSES = "row";
    private final String CELL_CLASSES = "cell";
    private final String HEADER_CLASSES = "header";

    private final Pattern ROW_COUNT_NUMBER = Pattern.compile("<Actual-Rows>(.*?)</Actual-Rows>");
    private final Pattern EXECUTION_TIME_PATTERN = Pattern.compile("<Execution-Time>(.*?)</Execution-Time>");

    @Autowired
    private SchemaConnection schemaConnection;

    /**
     * Executes sql query, counts checksum and generates html from the query result.
     *
     * If query execution time counted by sql query analyser greater
     * than {@link #TIMEOUT_LIMIT} select result contains error message.
     * @param query - sql to execute
     * @param columnToSort - the column to perform sorting for answer checking
     * @param maxRowLimit - result rows limit
     * @param hasHtmlOutput - flag to generate html
     * @return - checksum and html if the corresponding flag was set
     */
    public SelectResult executeQuery(String query, String columnToSort,
                                     int maxRowLimit, boolean hasHtmlOutput) {

        SelectResult selectResult = new SelectResult();
        if (hasHtmlOutput) {
            selectResult.setHasHtml(hasHtmlOutput);
            selectResult.setHtmlRows(new StringBuilder());
            selectResult.getHtmlRows().append("<table id=sqlresult class=\"").append(TABLE_CLASSES).append("\">\n");
        }

        long controlSum = 0;

        ResultSet resultSet = null;
        try (Connection connection = schemaConnection.getConnection();
             Statement statement = connection.createStatement()
        ) {
            statement.setMaxRows(maxRowLimit);
            statement.execute(SESSION_TIMEOUT_COMMAND);

            if (checkTimeoutByUsingExplain(statement, query, selectResult)) {
                throw new SQLException("ERROR: proactive canceling statement due to possible timeout exceeding",
                        Integer.toString(SelectResult.TIMEOUT), SelectResult.TIMEOUT);
            }
            String queryWithOrder = addOrder(query, columnToSort);
            resultSet = statement.executeQuery(queryWithOrder);

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

    private String addOrder(String query, String columnToSort) {
        query = query.trim();
        if (columnToSort != null && !query.toUpperCase().contains("ORDER BY")) {
            if (query.charAt(query.length() - 1) == ';') {
                return query.substring(0, query.length() - 1) + " ORDER BY " + columnToSort;
            } else {
                return query + " ORDER BY " + columnToSort;
            }
        } else {
            return query;
        }
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
        long controlSum = selectResult.getRowCount();
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
                Matcher rowcount_matcher = ROW_COUNT_NUMBER.matcher(exrow);
                if (rowcount_matcher.find()) {
                    selectResult.setRowCount(Long.parseLong(rowcount_matcher.group(1)));
                }
                Matcher executetime_matcher = EXECUTION_TIME_PATTERN.matcher(exrow);
                if (executetime_matcher.find()) {
                    selectResult.setExecutionTime(Double.parseDouble(executetime_matcher.group(1)));
                }
            }

            return selectResult.getExecutionTime() > (double) TIMEOUT_LIMIT;
        }
    }


}
