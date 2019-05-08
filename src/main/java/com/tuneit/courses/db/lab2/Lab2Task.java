package com.tuneit.courses.db.lab2;

import com.tuneit.courses.db.Task;
import com.tuneit.courses.db.lab.LabTask;
import com.tuneit.courses.db.lab.LabTaskQA;
import com.tuneit.courses.db.lab.schema.Schema;
import com.tuneit.courses.db.lab2.schema.Schema02;

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
