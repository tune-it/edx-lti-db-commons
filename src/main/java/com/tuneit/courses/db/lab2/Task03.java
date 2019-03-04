package com.tuneit.courses.db.lab2;

import com.tuneit.courses.Task;
import com.tuneit.courses.db.parser.LabTask;
import com.tuneit.courses.db.schema.Table;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.NONE)
class Task03 extends LabTask {
    @Override
    public String toString() {
        return "Task01{" + super.toString()+ ", forbiddenList=" + forbiddenList + '}';
    }

    @Override
    protected void updateQuery(Table table, Task task) {
        updateQueryPL(table, task);
    }

}
