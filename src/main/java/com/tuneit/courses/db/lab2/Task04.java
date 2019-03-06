package com.tuneit.courses.db.lab2;

import com.tuneit.courses.Task;
import com.tuneit.courses.db.LabTask;
import com.tuneit.courses.db.schema.Column;
import com.tuneit.courses.db.schema.Table;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.Collections;
import java.util.List;

@XmlAccessorType(XmlAccessType.NONE)
class Task04 extends LabTask {
    @Override
    public String toString() {
        return "Task01{" + super.toString()+ ", forbiddenList=" + forbiddenList + '}';
    }

    @Override
    protected void updateAnswer(Table table, Task task) {
        List<Column> columns = table.getColumns();
        Collections.shuffle(columns, getRandom(task));

        answer.append("SELECT DISTINCT ");
        answer.append(columns.get(getRandom(task).nextInt(columns.size())).getColumnName());
        answer.append(" FROM ").append(table.getTableName()).append(';');
    }

    @Override
    protected void updateQuery(Table table, Task task) {
        updateQueryPL(table, task);
    }

    @Override
    protected void updateQueryPL(Table table, Task task) {
        List<Column> columns = table.getColumns();
        query.append(getProlog());
        query.append(columns.get(getRandom(task).nextInt(columns.size())).getNamePL());
        query.append(getEpilog()).append(table.getNameRPL()).append('.');
    }
}
