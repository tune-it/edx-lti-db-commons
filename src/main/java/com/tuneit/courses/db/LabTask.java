package com.tuneit.courses.db;

import com.tuneit.courses.Task;
import com.tuneit.courses.db.schema.Schema;

public interface LabTask {
    LabTaskQA generate(Schema schema, Task task);
}
