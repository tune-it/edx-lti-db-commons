
package com.tuneit.courses.db;

import org.apache.commons.collections4.map.LRUMap;

/**
 * Dummy Class to persisting store already generated task question and answer 
 * and avoid high load data corruption.
 * 
 * @author serge
 */

public class LabTaskQA {
    
    protected final String id;
    protected final String question;
    protected final String correctAnswer;
    protected String correct_cksum = "";
    //cache for store question and md5 for correct answer
    private static LRUMap<String,LabTaskQA> cache = new LRUMap<>(100000);

    public LabTaskQA(final String id, final String question, final String correctAnswer) {
        if (id==null||question==null||correctAnswer==null) {
            throw new IllegalArgumentException("Could not instantiate LabTaskQA with null values in constructor");
        }
        this.id = id;
        this.question = question;
        this.correctAnswer = correctAnswer;
    }

    public String getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }
    
    public String getCorrectCheckSum() {
        return correct_cksum;
    }

    public void setCorrect_cksum(String correct_cksum) {
        this.correct_cksum = correct_cksum;
    }
    
    

}
