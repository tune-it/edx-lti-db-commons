package com.tuneit.courses.db;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.collections4.map.LRUMap;

/**
 * Dummy Class to persisting store already generated task question and answer
 * and avoid high load data corruption.
 *
 * @author serge
 */
public class LabTaskQA {

    //cache for store question and md5 for correct answer
    private static LRUMap<String, LabTaskQA> cache = new LRUMap<>(100000);
    @Getter
    protected final String id;
    @Getter
    protected final String question;
    @Getter
    protected final String correctAnswer;
    @Getter
    @Setter
    protected String correctCheckSum = "";
    @Getter
    @Setter
    private String columnToSort;

    public LabTaskQA(String id, String question, String correctAnswer, String columnToSort) {
        if (id == null || question == null || correctAnswer == null) {
            throw new IllegalArgumentException("Could not instantiate LabTaskQA with null values in constructor");
        }
        this.id = id;
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.columnToSort = columnToSort;
    }

    public LabTaskQA(String id, String question, String correctAnswer) { //TODO deleteme
        if (id == null || question == null || correctAnswer == null) {
            throw new IllegalArgumentException("Could not instantiate LabTaskQA with null values in constructor");
        }
        this.id = id;
        this.question = question;
        this.correctAnswer = correctAnswer;
    }


}
