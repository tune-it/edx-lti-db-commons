package com.tuneit.courses.db.generate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

/**
 * Stores the data of the generated task (the question, the correct answer
 * and the column to perform sorting for answer checking)
 * @author serge
 */
@AllArgsConstructor
@Getter
@NonNull
public class LabTaskQA {
    private final String id;
    private final String question;
    private final String correctAnswer;
    private final String columnToSort;
}
