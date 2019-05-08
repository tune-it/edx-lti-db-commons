package com.tuneit.courses.lab;

import com.tuneit.courses.Task;
import com.tuneit.courses.lab.schema.Schema;

import java.util.List;

public interface LabTask {
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
