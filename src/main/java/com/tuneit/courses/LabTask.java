package com.tuneit.courses;

import com.tuneit.courses.lab1.db.schema.Schema;

public interface LabTask {

    String getId();

    LabTaskQA generate(Schema schema, Task task);
}
