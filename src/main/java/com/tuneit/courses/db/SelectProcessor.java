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


    private static final long timeout_limit = 10000l;
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
        tasks[0].setAnswer("select * from ticket_flights;").setComplete(false);
        tasks[1].setAnswer("select fare_conditions from seats;").setComplete(false);
        tasks[2].setAnswer("select aircraft_code, seat_no from seats;").setComplete(false);
        tasks[3].setAnswer("select distinct total_amount from bookings;").setComplete(false);
        tasks[4].setAnswer("select extract(minute from scheduled_departure - scheduled_arrival) from flights;").setComplete(false);
        tasks[5].setAnswer("select 'Я билетик ' || ticket_no || ' ' || 'id - ' || passenger_id || ' ' || 'имя фамилия - ' || passenger_name || ' ' || 'код брони - ' || book_ref from tickets").setComplete(false);
        tasks[6].setAnswer("select arrival_airport from flights where status like '%Arr%';").setComplete(false);
        tasks[7].setAnswer("select aircraft_code from seats where seat_no like '12%' order by seat_no;").setComplete(false);
        tasks[8].setAnswer("select aircraft_code from aircrafts where model like '%100' order by 1;").setComplete(false);
        tasks[9].setAnswer("select fare_conditions from seats where seat_no = '50A' OR seat_no = '12A' OR seat_no = '27C';").setComplete(false);
        tasks[10].setAnswer("select '11C' || ' = ' || count(seat_no) from boarding_passes where seat_no = '11C' group by seat_no;").setComplete(false);
        tasks[11].setAnswer("select count(seat_no) from boarding_passes group by seat_no order by count(seat_no) asc limit 5;").setComplete(true);
        ds.checkTasks(tasks);
        for (Task t : tasks) {
//            if (t.isComplete()) {
            System.out.println(t.getQuestion());
//            }
//            if (t.isComplete() && t.getRating() != 1) {
//                System.out.println("INCORRECT " + t);
//                System.out.println(t.getQuestion());
//            }
        }
    }

    public SelectResult execute_select(Schema schema, String sql) {
        return execute_select(schema, sql, 100, false);
    }

    /**
     * @param schema         - schema name to generate queries
     * @param sql            - sql to execute
     * @param row_limit      Limits row in output, 0 - zero rows, -1 unlimited
     * @param do_html_output - save html output to new StringBuilder in SelectResult
     * @return SelectResult - object with encapsulated results
     */
    public SelectResult execute_select(final Schema schema, final String sql,
                                       final int row_limit,
                                       final boolean do_html_output) {

        //TODO error sign is null;
        //String query_md5 = "Error at executing query";
        SelectResult selectResult = new SelectResult();
        if (do_html_output) {
            selectResult.setHtmlOutput(do_html_output);
            selectResult.setHtmlRows(new StringBuilder());
            selectResult.getHtmlRows().append("<table id=sqlresult class=\"").append(TABLE_CLASSES).append("\">\n");
        }

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        ResultSet explainSet = null; //explain resultSet
        long controlSum = 0;
        try {
            // we are count only first five rows in select
            int row_counter = row_limit;
            // TODO probably should change in future on quicker alg or on procedure in DB
            connection = schema.getConnection();
            statement = connection.createStatement();
            statement.setMaxRows(row_limit);
            // setup hard limit timeout
            statement.execute(session_timeout_command);
            //fetch row count and execution time for query
            explainSet = statement.executeQuery("EXPLAIN (analyse on, format xml) " + sql);
            while (explainSet.next()) {
                String exrow = explainSet.getString(1);
                //System.out.println("plan  "+exrow);
                Matcher rowcount_matcher = rowcount_pattern.matcher(exrow);
                if (rowcount_matcher.find()) {
                    selectResult.setRowCount(Long.parseLong(rowcount_matcher.group(1)));
                }
                Matcher executetime_matcher = executiontime_pattern.matcher(exrow);
                if (executetime_matcher.find()) {
                    selectResult.setExecutionTime(Double.parseDouble(executetime_matcher.group(1)));
                }
            }
            //check for possible timeout exceeding 
            //if it can run over given millisecond do not process query
            //to not overload server
            if (selectResult.getExecutionTime() > (double) timeout_limit) {
                throw new SQLException("ERROR: proactive canceling statement due to possible timeout exceeding",
                        Integer.toString(SelectResult.TIMEOUT), SelectResult.TIMEOUT);
            }
            //Finally execute sql statement
            resultSet = statement.executeQuery(sql);
            ResultSetMetaData rsmd = resultSet.getMetaData();
            int numcols = rsmd.getColumnCount();
            //headers
            if (do_html_output) {
                selectResult.getHtmlRows().append("<tr class=\"").append(ROW_CLASSES).append("\">");
                for (int i = 1; i <= numcols; i++) {
                    String fieldLabel = rsmd.getColumnLabel(i);
                    selectResult.getHtmlRows().append("<th class=\"").append(HEADER_CLASSES).append("\">")
                            .append(fieldLabel).append("</th>");
                }
                selectResult.getHtmlRows().append("</tr>\n");
            }
            while (resultSet.next() && row_counter-- != 0) {
                if (do_html_output)
                    selectResult.getHtmlRows().append("<tr class=\"").append(ROW_CLASSES).append("\">");
                for (int i = 1; i <= numcols; i++) {
                    String field = resultSet.getString(i);
                    if (field != null) {
                        controlSum += field.hashCode();
                        if (do_html_output)
                            selectResult.getHtmlRows().append("<td class=\"").append(CELL_CLASSES).append("\">")
                                    .append(field).append("</td>");
                    }
                }
                if (do_html_output)
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
            //Logger.getLogger(SelectProcessor.class.getName()).log(Level.SEVERE, selectResult.getErrorMessage(), e);
        } catch (NumberFormatException en) {
            selectResult.setErrorMessage("Internal error, cant parse row count or execution time in explain statement");
            selectResult.setResultCode(SelectResult.INTERNAL_ERROR);
            Logger.getLogger(SelectProcessor.class.getName()).log(Level.SEVERE, selectResult.getErrorMessage(), en);
        } finally {
            try {
                if (resultSet != null) resultSet.close();
            } catch (Exception e) {
            }
            try {
                if (explainSet != null) explainSet.close();
            } catch (Exception e) {
            }
            try {
                if (statement != null) statement.close();
            } catch (Exception e) {
            }
            try {
                if (connection != null) connection.close();
            } catch (Exception e) {
            }
        }
        if (selectResult.getResultCode() == SelectResult.OK) {
            //update rows checksum with row count
            controlSum += selectResult.getRowCount();
            //set checksum in results
            selectResult.setResultCheckSum(Long.toString(controlSum));
        }
        if (do_html_output) {
            if (selectResult.getResultCode() != SelectResult.OK) {
                selectResult.getHtmlRows().delete(0, selectResult.getHtmlRows().length());
                selectResult.getHtmlRows().append("<H1>").append(selectResult.getErrorMessage()).append("</H1>");
            } else {
                selectResult.getHtmlRows().append("</table>\n");
            }
        }
        return selectResult;
    }

}
