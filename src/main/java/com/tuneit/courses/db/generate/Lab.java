package com.tuneit.courses.db.generate;

/**
 * Task generator this lab
 */
public interface Lab {

    /**
     * generate selected task
     * @param task - task description
     * @param schema - task generation data
     * @return generated task question, correct answer and the column by which the sorting will be performed
     */
    LabTaskQA generate(Task task, Schema schema);

    /**
     * generates all tasks for this lab
     * @param task - task description
     * @param schema - task generation data
     * @return generated tasks with question, correct answer and the column by which the sorting will be performed
     */
    LabTaskQA[] generateAll(Task task, Schema schema);
}
