package com.tuneit.courses.db;

import com.tuneit.courses.Task;

public interface Lab {
    LabTaskQA generate(Task task);

    LabTaskQA[] generateAll(Task task);
}
