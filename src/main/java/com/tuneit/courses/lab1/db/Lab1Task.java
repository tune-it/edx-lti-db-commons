package com.tuneit.courses.lab1.db;

import com.tuneit.courses.Task;
import com.tuneit.courses.db.LabTask;
import com.tuneit.courses.db.LabTaskQA;
import com.tuneit.courses.db.schema.Schema;
import com.tuneit.courses.lab1.db.schema.Schema01;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.NONE)
public abstract class Lab1Task implements LabTask {
    protected StringBuilder query;
    protected StringBuilder answer;

    public abstract LabTaskQA generate(Schema01 schema, Task task);

    @Override
    public LabTaskQA generate(Schema schema, Task task) {
        return generate((Schema01) schema, task);
    }

}
