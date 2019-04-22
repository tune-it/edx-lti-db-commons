package com.tuneit.courses.lab1.task;

import com.tuneit.courses.Task;
import com.tuneit.courses.db.LabTaskQA;
import com.tuneit.courses.db.schema.Condition;
import com.tuneit.courses.db.schema.ConditionTable;
import com.tuneit.courses.db.schema.Table;
import com.tuneit.courses.lab1.Lab1Task;
import com.tuneit.courses.lab1.schema.Schema01;

import java.util.Random;

import static com.tuneit.courses.db.schema.Schema.getRandomElement;

public class Lab1Task6 extends Lab1Task {
    @Override
    public LabTaskQA generate(Schema01 schema01, Task task) {
        query = new StringBuilder();
        answer = new StringBuilder();

        Random random = task.getRandom();

        schema01.getConditionTables().removeIf(conditionTable ->
                conditionTable.getConditions().stream().allMatch(condition -> condition.getContainsNull().isEmpty()));

        ConditionTable conditionTable = getRandomElement(random, schema01.getConditionTables());
        Table table = schema01.findTableBySqlName(conditionTable.getSqlTableName());

        conditionTable.getConditions().removeIf(condition -> condition.getContainsNull().isEmpty());

        Condition condition = getRandomElement(random, conditionTable.getConditions());

        query.append("Выведите содержимое всех полей из таблицы ")
                .append(table.getNameGenitive())
                .append(", которые удовлетворяют следующему условию: ")
                .append(condition.getNativeColumnName())
                .append(" ")
                .append(condition.getContainsNull())
                .append(".");

        answer.append("SELECT * FROM ")
                .append(table.getTableName())
                .append(" WHERE ")
                .append(condition.getSqlColumnName())
                .append(" ISNULL");

        return new LabTaskQA(task.getId(), query.toString(), answer.toString());
    }

}