package com.tuneit.courses.db.lab2;

import com.tuneit.courses.Task;
import com.tuneit.courses.db.LabTask;
import com.tuneit.courses.db.LabTaskQA;
import com.tuneit.courses.db.schema.Schema;
import com.tuneit.courses.db.schema.Table;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.List;

@XmlAccessorType(XmlAccessType.NONE)
class Task01 extends LabTask {

    @Override
    public String toString() {
        return "Task01{" + super.toString()+ ", forbiddenList=" + forbiddenList + '}';
    }

    @Override
    public LabTaskQA generate(Schema s, Task t) {
        Table table = getRandomTable(s).clone();

        return new LabTaskQA(t.getId(), getProlog() + table.getTableName() + getEpilog(),
                "SELECT * FROM " + table.getTableName() + ";");
    }
}
