package com.tuneit.courses.lab2;

import com.tuneit.courses.Task;
import com.tuneit.courses.db.LabTask;
import com.tuneit.courses.db.LabTaskQA;
import com.tuneit.courses.db.schema.Schema;
import com.tuneit.courses.lab2.schema.Schema02;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.NONE)
public abstract class Lab2Task implements LabTask {
    protected StringBuilder query;
    protected StringBuilder answer;

    @Override
    public LabTaskQA generate(Schema schema, Task task) {
        return generate((Schema02) schema, task);
    }

    public abstract LabTaskQA generate(Schema02 schema, Task task);
}
