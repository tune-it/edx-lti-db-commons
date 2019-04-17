package com.tuneit.courses.lab1.task;

import com.tuneit.courses.Task;
import com.tuneit.courses.db.LabTaskQA;
import com.tuneit.courses.db.schema.Table;
import com.tuneit.courses.lab1.Lab1Task;
import com.tuneit.courses.lab1.schema.Schema01;

public class Lab1Task1 extends Lab1Task {
    @Override
    public LabTaskQA generate(Schema01 schema01, Task task) {
        query = new StringBuilder();
        answer = new StringBuilder();

        Table table = schema01.getRandomTable(task.getRandom());

        query.append("Выведите содержимое всех полей таблицы ")
                .append(table.getTableName())
                .append(".");

        answer.append("SELECT * FROM ")
                .append(table.getTableName())
                .append(";");

        return new LabTaskQA(task.getId(), query.toString(), answer.toString());
    }

}