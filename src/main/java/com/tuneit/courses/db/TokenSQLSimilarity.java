package com.tuneit.courses.db;

import org.apache.commons.text.StringTokenizer;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author serge
 */
public class TokenSQLSimilarity {
    private List<String> answer_sql;
    private List<String> correct_sql;
    private final String delimiters = " \n\r\f\t,()";
    
    public TokenSQLSimilarity(String answer_sql, String correct_sql) {
        StringTokenizer sta = new StringTokenizer(answer_sql);//, delimiters);
        StringTokenizer stc = new StringTokenizer(correct_sql, delimiters);

        this.answer_sql = sta.getTokenList();
        this.correct_sql = stc.getTokenList();
    }

    @Override
    public String toString() {
        return "TokenSQLSimilarity{" + "answer_sql=" + answer_sql + ", correct_sql=" + correct_sql + '}';
    }
    
    
}
