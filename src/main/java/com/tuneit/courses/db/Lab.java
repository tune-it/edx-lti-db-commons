package com.tuneit.courses.db;

public interface Lab {
    LabTaskQA generate(Task task);

    LabTaskQA[] generateAll(Task task);
}
