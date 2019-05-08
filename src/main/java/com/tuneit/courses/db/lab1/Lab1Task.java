package com.tuneit.courses.db.lab1;

import com.tuneit.courses.db.Task;
import com.tuneit.courses.db.lab.LabTask;
import com.tuneit.courses.db.lab.LabTaskQA;
import com.tuneit.courses.db.lab.schema.Schema;
import com.tuneit.courses.db.lab1.schema.Schema01;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.NONE)
public abstract class Lab1Task implements LabTask {
    protected StringBuilder query;
    protected StringBuilder answer;

    @Override
    public LabTaskQA generate(Schema schema, Task task) {
        return generate((Schema01) schema, task);
    }

    public abstract LabTaskQA generate(Schema01 schema, Task task);


}
