package com.tuneit.courses.db;

import com.tuneit.courses.Task;
import com.tuneit.courses.db.schema.Schema;
import com.tuneit.courses.db.schema.SchemaLoader;

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

    public static void main(String[] args) throws Exception {
        Schema s = SchemaLoader.getSchema(0);
        for (Lab t : s.getLabs()) {
            System.out.println(t);
        }
        DBTaskGeneratorService ds = new DBTaskGeneratorService();
        Task[] tasks = ds.getTasks("serge@cs.ifmo.ru", "lab02", "01", 0);
        tasks[0].setAnswer("select * from ticket_flights;").setComplete(true);
        tasks[1].setAnswer("select fare_conditions from seats;").setComplete(true);
        tasks[2].setAnswer("select aircraft_code, seat_no from seats;").setComplete(true);
        tasks[3].setAnswer("select distinct total_amount from bookings;").setComplete(true);
        tasks[4].setAnswer("select extract(minute from scheduled_departure - scheduled_arrival) from flights;").setComplete(true);
        tasks[5].setAnswer("select 'Я билетик ' || ticket_no || ' ' || 'id - ' || passenger_id || ' ' || 'имя фамилия - ' || passenger_name || ' ' || 'код брони - ' || book_ref from tickets").setComplete(true);
        tasks[6].setAnswer("select arrival_airport from flights where status like '%Arr%';").setComplete(true);
        tasks[7].setAnswer("select aircraft_code from seats where seat_no like '12%' order by seat_no;").setComplete(true);
        tasks[8].setAnswer("select aircraft_code from aircrafts where model like '%100' order by 1;").setComplete(true);
        tasks[9].setAnswer("select fare_conditions from seats where seat_no = '50A' OR seat_no = '12A' OR seat_no = '27C';").setComplete(true);
        tasks[10].setAnswer("select '11C' || ' = ' || count(seat_no) from boarding_passes where seat_no = '11C' group by seat_no;").setComplete(true);
        tasks[11].setAnswer("select count(seat_no) from boarding_passes group by seat_no order by count(seat_no) asc limit 5;").setComplete(true);
        ds.checkTasks(tasks);
        for (Task t : tasks) {
            System.out.println(t.getRating());
        }
    }

    public SelectResult executeQuery(Schema schema, String sql) {
        return executeQuery(schema, sql, 100, false);
    }

    /**
     * @param schema       - schema name to generate queries
     * @param query        - sql to execute
     * @param maxRowLimit  Limits row in output, 0 - zero rows, -1 unlimited
     * @param doHtmlOutput - save html output to new StringBuilder in SelectResult
     * @return SelectResult - object with encapsulated results
     */
    public SelectResult executeQuery(final Schema schema, final String query,
                                     final int maxRowLimit, final boolean doHtmlOutput) {

        SelectResult selectResult = new SelectResult();
        if (doHtmlOutput) {
            selectResult.setHtmlOutput(doHtmlOutput);
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
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int numCols = resultSetMetaData.getColumnCount();
            //headers
            if (doHtmlOutput) {
                selectResult.getHtmlRows().append("<tr class=\"").append(ROW_CLASSES).append("\">");
                for (int i = 1; i <= numCols; i++) {
                    String fieldLabel = resultSetMetaData.getColumnLabel(i);
                    selectResult.getHtmlRows().append("<th class=\"").append(HEADER_CLASSES).append("\">")
                            .append(fieldLabel).append("</th>");
                }
                selectResult.getHtmlRows().append("</tr>\n");
            }
            while (resultSet.next()) {
                if (doHtmlOutput)
                    selectResult.getHtmlRows().append("<tr class=\"").append(ROW_CLASSES).append("\">");
                for (int i = 1; i <= numCols; i++) {
                    String field = resultSet.getString(i);
                    if (field != null) {
                        controlSum += field.hashCode();
                        if (doHtmlOutput)
                            selectResult.getHtmlRows().append("<td class=\"").append(CELL_CLASSES).append("\">")
                                    .append(field).append("</td>");
                    }
                }
                if (doHtmlOutput)
                    selectResult.getHtmlRows().append("</tr>\n");
            }
            selectResult.setResultCode(SelectResult.OK);
            selectResult.setErrorMessage("None");
        } catch (SQLException e) {
            try {
                selectResult.setResultCode(Integer.parseInt(e.getSQLState()));
                selectResult.setErrorMessage(e.getMessage().replace('\n', ' '));
            } catch (NumberFormatException esqlstate) {
                selectResult.setResultCode(SelectResult.INTERNAL_ERROR);
                selectResult.setErrorMessage("Unparseable SQLState=" + e.getSQLState() + " Message=" + e.getMessage().replace('\n', ' '));
                Logger.getLogger(SelectProcessor.class.getName()).log(Level.SEVERE, selectResult.getErrorMessage(), e);
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
        if (doHtmlOutput) {
            if (selectResult.getResultCode() != SelectResult.OK) {
                selectResult.getHtmlRows().delete(0, selectResult.getHtmlRows().length());
                selectResult.getHtmlRows().append("<H1>").append(selectResult.getErrorMessage()).append("</H1>");
            } else {
                selectResult.getHtmlRows().append("</table>\n");
            }
        }
        return selectResult;
    }

    private boolean checkTimeoutByUsingExplain(Statement statement, String sql, SelectResult selectResult) throws SQLException {
        ResultSet explainSet = statement.executeQuery("EXPLAIN (analyse on, format xml) " + sql);
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
