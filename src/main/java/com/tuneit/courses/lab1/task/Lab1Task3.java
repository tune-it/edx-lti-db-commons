package com.tuneit.courses.lab1.task;

import com.tuneit.courses.Task;
import com.tuneit.courses.db.LabTaskQA;
import com.tuneit.courses.db.schema.Column;
import com.tuneit.courses.db.schema.Table;
import com.tuneit.courses.lab1.Lab1Task;
import com.tuneit.courses.lab1.schema.Schema01;

import java.util.Random;

public class Lab1Task3 extends Lab1Task {
    @Override
    public LabTaskQA generate(Schema01 schema01, Task task) {
        query = new StringBuilder();
        answer = new StringBuilder();

        Random random = task.getRandom();
        Table table = schema01.getRandomTable(random);
        Column column = table.getRandomColumn(random);

        query.append("Выведите все уникальные значения столбца ")
                .append(column.getNamePlural())
                .append(" (")
                .append(column.getColumnName().toLowerCase())
                .append(") из таблицы ")
                .append(table.getTableName())
                .append(".");

        answer.append("SELECT DISTINCT ")
                .append(column.getColumnName())
                .append(" FROM ")
                .append(table.getTableName())
                .append(";");

        return new LabTaskQA(task.getId(), query.toString(), answer.toString());
    }

}