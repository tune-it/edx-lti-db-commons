package com.tuneit.courses.db.lab;

import com.tuneit.courses.db.Task;

public interface Lab {
    LabTaskQA generate(Task task);

    LabTaskQA[] generateAll(Task task);
}
