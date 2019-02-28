package com.tuneit.courses.db.lab2;

import com.tuneit.courses.Task;
import com.tuneit.courses.db.LabTask;
import com.tuneit.courses.db.LabTaskQA;
import com.tuneit.courses.db.schema.Schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.NONE)
class Task05 extends LabTask {
    @Override
    public LabTaskQA generate(Schema s, Task t) {
        return new LabTaskQA(t.getId(), "dummyQuestion", "dummyAnswer");
    }
}
