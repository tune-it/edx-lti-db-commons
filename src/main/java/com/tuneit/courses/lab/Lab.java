package com.tuneit.courses.lab;

import com.tuneit.courses.Task;

public interface Lab {
    LabTaskQA generate(Task task);

    LabTaskQA[] generateAll(Task task);
}
