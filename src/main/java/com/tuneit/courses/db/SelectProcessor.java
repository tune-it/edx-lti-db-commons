package com.tuneit.courses.db;

import com.tuneit.courses.Task;
import com.tuneit.courses.db.schema.Schema;
import com.tuneit.courses.db.schema.SchemaLoader;

import javax.xml.bind.DatatypeConverter;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author serge
 */
public class SelectProcessor {


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
        tasks[5].setAnswer("select 'Я билетик ' || ticket_no || ' id - ' || passenger_id || ' имя фамилия - ' || passenger_name || ' код брони - ' || book_ref from tickets").setComplete(false);
        tasks[6].setAnswer("select timezone, airport_code from airports where airport_code like '%3%'").setComplete(true);
        System.out.println(tasks[6].getAnswer());
        ds.checkTasks(tasks);
        for (Task t : tasks) {
            if (t.isComplete()) {
                System.out.println(t);
            }
        }
    }
    
    static Pattern rowcount_pattern = Pattern.compile("<Actual-Rows>(.*?)</Actual-Rows>");
    static Pattern executiontime_pattern = Pattern.compile("<Execution-Time>(.*?)</Execution-Time>");

    private static final long timeout_limit = 10000l;
    private static final String session_timeout_command = 
                                "SET statement_timeout = "+timeout_limit+";";

    private static final String TABLE_CLASSES = "dummy-table";
    private static final String ROW_CLASSES = "dummy-row";
    private static final String CELL_CLASSES = "dummy-cell";
    private static final String HEADER_CLASSES = "dummy-header";
        
    public SelectResult execute_select(Schema schema, String sql) {
            return execute_select(schema,sql,100,false);
    }
    /**
     * @param schema    - schema name to generate queries
     * @param sql       - sql to execute
     * @param row_limit Limits row in output, 0 - zero rows, -1 unlimited
     * @param do_html_output - save html output to new StringBuilder in SelectResult
     * @return SelectResult - object with encapsulated results
     */
    public SelectResult execute_select(final Schema schema, final String sql, 
                                        final int row_limit,
                                        final boolean do_html_output ) {
        
        //TODO error sign is null;
        //String query_md5 = "Error at executing query";
        SelectResult sres = new SelectResult();
        if (do_html_output) {
            sres.setHtmlOutput(do_html_output);
            sres.setHtmlRows(new StringBuilder());
            sres.getHtmlRows().append("<table id=sqlresult class=\"").append(TABLE_CLASSES).append("\">\n");
        }

        Connection conn = null;
        Statement stmt = null;
        ResultSet rset = null;
        ResultSet eset = null; //explain rset
        MessageDigest md = null;
        try 
        {
            // we are count only first five rows in select
            int row_counter = row_limit;
            // TODO probably should change in future on quicker alg or on procedure in DB
            md = MessageDigest.getInstance("MD5");
            conn = schema.getConnection();
            stmt = conn.createStatement();
            stmt.setMaxRows(5);
            // setup hard limit timeout
            stmt.execute(session_timeout_command);
            //fetch row count and execution time for query
            eset = stmt.executeQuery("EXPLAIN (analyse on, format xml) "+ sql);
            while(eset.next()) {
                String exrow = eset.getString(1);
                //System.out.println("plan  "+exrow);
                Matcher rowcount_matcher = rowcount_pattern.matcher(exrow);
                if (rowcount_matcher.find()) {
                    sres.setRowCount(Long.parseLong(rowcount_matcher.group(1)));
                }
                Matcher executetime_matcher = executiontime_pattern.matcher(exrow);
                if (executetime_matcher.find()) {
                    sres.setExecutionTime(Double.parseDouble(executetime_matcher.group(1)));
                }                
            }
            //check for possible timeout exceeding 
            //if it can run over given millisecond do not process query
            //to not overload server
            if (sres.getExecutionTime()>(double)timeout_limit) {
                throw new SQLException("ERROR: proactive canceling statement due to possible timeout exceeding",
                        Integer.toString(SelectResult.TIMEOUT), SelectResult.TIMEOUT);
            }
            //Finally execute sql statement
            rset = stmt.executeQuery(sql);
            ResultSetMetaData rsmd = rset.getMetaData();
            int numcols = rsmd.getColumnCount();
            //headers
            if (do_html_output) {
                 sres.getHtmlRows().append("<tr class=\"").append(ROW_CLASSES).append("\">");
                for(int i=1;i<=numcols;i++) {
                    String fieldLabel = rsmd.getColumnLabel(i);
                    sres.getHtmlRows().append("<th class=\"").append(HEADER_CLASSES).append("\">")
                                      .append(fieldLabel).append("</th>");
                }
                sres.getHtmlRows().append("</tr>\n");
            }
            while(rset.next() && row_counter-- != 0 ) {
                if (do_html_output) 
                    sres.getHtmlRows().append("<tr class=\"").append(ROW_CLASSES).append("\">");
                for(int i=1;i<=numcols;i++) {
                    String field = rset.getString(i);
                    md.update(field.getBytes());
                    if (do_html_output) 
                        sres.getHtmlRows().append("<td class=\"").append(CELL_CLASSES).append("\">")
                                          .append(field).append("</td>");
                }
                if (do_html_output)
                     sres.getHtmlRows().append("</tr>\n");
            }
            sres.setResultCode(SelectResult.OK);
            sres.setErrorMessage("None");
        } catch(SQLException e) {
            try {
                sres.setResultCode(Integer.parseInt(e.getSQLState()));
                sres.setErrorMessage(e.getMessage().replace('\n', ' '));
            } catch (NumberFormatException esqlstate) {
                sres.setResultCode(SelectResult.INTERNAL_ERROR);
                sres.setErrorMessage("Unparseable SQLState="+e.getSQLState()+" Message="+e.getMessage().replace('\n', ' '));
                Logger.getLogger(SelectProcessor.class.getName()).log(Level.SEVERE, sres.getErrorMessage(), e);
            }
            //Logger.getLogger(SelectProcessor.class.getName()).log(Level.SEVERE, sres.getErrorMessage(), e);
        } catch (NumberFormatException en) {
            sres.setErrorMessage("Internal error, cant parse row count or execution time in explain statement");
            sres.setResultCode(SelectResult.INTERNAL_ERROR);
            Logger.getLogger(SelectProcessor.class.getName()).log(Level.SEVERE, sres.getErrorMessage(), en);
        } catch (NoSuchAlgorithmException ex) {
            sres.setErrorMessage("Internal error, cant get MD5 provider");
            sres.setResultCode(SelectResult.INTERNAL_ERROR);
            Logger.getLogger(SelectProcessor.class.getName()).log(Level.SEVERE, sres.getErrorMessage(), ex);
        } finally {
            try { if (rset != null) rset.close(); } catch(Exception e) { }
            try { if (eset != null) eset.close(); } catch(Exception e) { }
            try { if (stmt != null) stmt.close(); } catch(Exception e) { }
            try { if (conn != null) conn.close(); } catch(Exception e) { }
        }
        if (sres.getResultCode() == SelectResult.OK ) {
            //update rows checksum with row count
            ByteBuffer rc = ByteBuffer.allocate(Long.BYTES).putLong(sres.getRowCount());
            md.update(rc.array());
            byte[] digest = md.digest();
            //set checksum in results
            sres.setResultCheckSum(DatatypeConverter.printHexBinary(digest).toUpperCase());
        }
        if (do_html_output) {
            if (sres.getResultCode() != SelectResult.OK) {
                sres.getHtmlRows().delete(0, sres.getHtmlRows().length());
                sres.getHtmlRows().append("<H1>").append(sres.getErrorMessage()).append("</H1>");
            } else {
                sres.getHtmlRows().append("</table>\n");
            }
        }
        return sres;
    }

}
