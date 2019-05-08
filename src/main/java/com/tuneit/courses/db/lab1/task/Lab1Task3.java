package com.tuneit.courses.db.lab1.task;

import com.tuneit.courses.db.Task;
import com.tuneit.courses.db.LabTaskQA;
import com.tuneit.courses.db.schema.Column;
import com.tuneit.courses.db.schema.Table;
import com.tuneit.courses.db.lab1.Lab1Task;
import com.tuneit.courses.db.lab1.schema.Schema01;

import java.util.Random;

import static com.tuneit.courses.db.schema.Schema.getRandomElement;

public class Lab1Task3 extends Lab1Task {
    @Override
    public LabTaskQA generate(Schema01 schema01, Task task) {
        query = new StringBuilder();
        answer = new StringBuilder();

        Random random = task.getRandom();
        Table table = getRandomElement(random, schema01.getTables());
        Column column = getRandomElement(random, table.getColumns());

        query.append("Выведите все уникальные ")
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