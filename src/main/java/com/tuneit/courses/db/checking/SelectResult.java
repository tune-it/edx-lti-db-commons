package com.tuneit.courses.db.checking;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Stores the result of the query execution (html, checksum and error message)
 * @author serge
 */
@Getter
@Setter
@ToString
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
    private long rowCount = -1;
    private double executionTime = -1;
}
