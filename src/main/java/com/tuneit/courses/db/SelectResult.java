/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tuneit.courses.db;

/**
 * @author serge
 */
public class SelectResult {

    public static final int NORESULT = -1;
    public static final int OK = 0;
    public static final int INTERNAL_ERROR = -2;
    public static final int TIMEOUT = 57014;

    private boolean hasHtml = false;
    private int resultCode = NORESULT;
    private StringBuilder htmlRows = null;
    private String errorMessage = "SQL was not processed";
    private String resultCheckSum = null;
    private long rowCount = -1l;
    private double executionTime = -1d;

    public boolean hasHtml() {
        return hasHtml;
    }

    public void setHtmlOutput(boolean hasHtml) {
        this.hasHtml = hasHtml;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public StringBuilder getHtmlRows() {
        return htmlRows;
    }

    public void setHtmlRows(StringBuilder htmlRows) {
        this.htmlRows = htmlRows;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getResultCheckSum() {
        return resultCheckSum;
    }

    public void setResultCheckSum(String resultCheckSum) {
        this.resultCheckSum = resultCheckSum;
    }

    public long getRowCount() {
        return rowCount;
    }

    public void setRowCount(long rowCount) {
        this.rowCount = rowCount;
    }

    public double getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(double executionTime) {
        this.executionTime = executionTime;
    }

    @Override
    public String toString() {
        return "SelectResult{" + "hasHtml=" + hasHtml + ", resultCode=" + resultCode + ", htmlRows=" + htmlRows + ", errorMessage=" + errorMessage + ", resultCheckSum=" + resultCheckSum + ", rowCount=" + rowCount + ", executionTime=" + executionTime + '}';
    }

}
