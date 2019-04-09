package com.tuneit.courses.lab2.db;

import com.tuneit.courses.db.LabTask;
import com.tuneit.courses.db.LabTaskQA;
import com.tuneit.courses.Task;
import com.tuneit.courses.lab1.db.schema.Schema01;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.NONE)
public abstract class Lab02Task implements LabTask {
    @XmlAttribute(name = "id")
    protected String id;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public LabTaskQA generate(Schema01 schema01, Task task) {
        return new LabTaskQA(task.getId(), "Question", "Select * from airports;");
    }
}
