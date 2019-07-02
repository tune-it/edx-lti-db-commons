package com.tuneit.courses.db.generate;

import java.util.List;

/**
 * Task generator
 */
public interface LabTask {
    /**
     * generate task
     * @param schema - task generation data
     * @param task - task description
     * @return generated tasks with question, correct answer and the column by which the sorting will be performed
     */
    LabTaskQA generate(Schema schema, Task task);

    default void writeColumnToQuery(List<String> elements, String delimiter, StringBuilder stringBuilderOut) {
        for (int i = 0; i < elements.size(); i++) {
            stringBuilderOut.append(elements.get(i));
            if (i != elements.size() - 1) {
                stringBuilderOut.append(delimiter);
            }
        }
    }
}
