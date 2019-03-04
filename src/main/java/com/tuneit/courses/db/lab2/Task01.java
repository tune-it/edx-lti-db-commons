package com.tuneit.courses.db.lab2;

import com.tuneit.courses.Task;
import com.tuneit.courses.db.parser.LabTask;
import com.tuneit.courses.db.LabTaskQA;
import com.tuneit.courses.db.schema.Schema;
import com.tuneit.courses.db.schema.Table;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.NONE)
class Task01 extends LabTask {

    @Override
    public String toString() {
        return "Task01{" + super.toString()+ ", forbiddenList=" + forbiddenList + '}';
    }

    @Override
    public LabTaskQA generate(Schema schema, Task task) {
        Table table = getRandomTable(schema, task).clone();

        return new LabTaskQA(task.getId(), getProlog() + table.getTableName() + getEpilog(),
                "SELECT * FROM " + table.getTableName() + ";");
    }
}
