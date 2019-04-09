package com.tuneit.courses.db;

import com.tuneit.courses.Task;
import com.tuneit.courses.lab1.db.schema.Schema01;

public interface LabTask {

    String getId();

    LabTaskQA generate(Schema01 schema01, Task task);
}
