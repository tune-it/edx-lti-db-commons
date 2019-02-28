package com.tuneit.courses.db;

import org.apache.commons.text.StringTokenizer;
import org.apache.commons.text.matcher.StringMatcher;
import org.apache.commons.text.matcher.StringMatcherFactory;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
/**
 *
 * @author serge
 */
public class TokenSQLSimilarity {
    private final List<String> answer_sql;
    private final List<String> correct_sql;
    private final StringMatcher delimiters = 
            StringMatcherFactory.INSTANCE.charSetMatcher(" ,();\n\r\f\t");
    
    public TokenSQLSimilarity(final String answer_sql, final String correct_sql) {
        StringTokenizer sta = new StringTokenizer(answer_sql);
        sta.setDelimiterMatcher(delimiters);
        //sta.setQuoteMatcher(StringMatcherFactory.INSTANCE.singleQuoteMatcher());
        sta.setTrimmerMatcher(StringMatcherFactory.INSTANCE.trimMatcher());
        StringTokenizer stc = new StringTokenizer(correct_sql);
        stc.setDelimiterMatcher(delimiters);
        //stc.setQuoteMatcher(StringMatcherFactory.INSTANCE.singleQuoteMatcher());
        stc.setTrimmerMatcher(StringMatcherFactory.INSTANCE.trimMatcher());
        //sta.setIgnoredMatcher(StringMatcherFactory.INSTANCE.noneMatcher());
        //sta.setEmptyTokenAsNull(false);
        //sta.setIgnoreEmptyTokens(false);
        this.answer_sql = sta.getTokenList();
        this.correct_sql = stc.getTokenList();
    }
    
    public float calculate() {
        return calculateJaccardSimilarity(answer_sql, correct_sql).floatValue();
    }
    
    private Double calculateJaccardSimilarity(final List<String> left, final List<String> right) {
        final int leftLength = left.size();
        final int rightLength = right.size();
        if (leftLength == 0 || rightLength == 0) {
            return 0d;
        }
        final Set<String> leftSet = new HashSet<>();
        for (int i = 0; i < leftLength; i++) {
            String el = left.get(i);
            if (el.startsWith("'")) {
                leftSet.add(el);
            } else {
                leftSet.add(el.toUpperCase());
            }
        }
        final Set<String> rightSet = new HashSet<>();
        for (int i = 0; i < rightLength; i++) {
            String el = right.get(i);
            if (el.startsWith("'")) {
                rightSet.add(el);
            } else {
                rightSet.add(el.toUpperCase());
            }
        }
        final Set<String> unionSet = new HashSet<>(leftSet);
        unionSet.addAll(rightSet);
        final int intersectionSize = leftSet.size() + rightSet.size() - unionSet.size();
        return 1.0d * intersectionSize / unionSet.size();
    }
    
    @Override
    public String toString() {
        return "TokenSQLSimilarity{" + "answer_sql=" + answer_sql + ", correct_sql=" + correct_sql + '}';
    }
    
    
}
