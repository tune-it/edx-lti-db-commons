package com.tuneit.courses.lab1.db.task;

import com.tuneit.courses.Task;
import com.tuneit.courses.db.LabTaskQA;
import com.tuneit.courses.lab1.db.Lab1Task;
import com.tuneit.courses.lab1.db.schema.Condition;
import com.tuneit.courses.lab1.db.schema.ConditionTable;
import com.tuneit.courses.lab1.db.schema.Schema01;

import java.util.Random;

public class Lab1Task6 extends Lab1Task {
    @Override
    public LabTaskQA generate(Schema01 schema01, Task task) {
        query = new StringBuilder();
        answer = new StringBuilder();

        Random random = task.getRandom();

        schema01.getConditionTables().removeIf(conditionTable ->
                conditionTable.getConditions().stream().allMatch(condition -> condition.getContainsNull().isEmpty()));

        ConditionTable conditionTable = schema01.getRandomConditionTable(random);

        conditionTable.getConditions().removeIf(condition -> condition.getContainsNull().isEmpty());

        Condition condition = conditionTable.getRandomCondition(random);


        query.append("Выведите все ")
                .append(conditionTable.getNativeTableName())
                .append(", которые удовлетовярют условию. ")
                .append(condition.getNativeColumnName())
                .append(" ")
                .append(condition.getContainsNull())
                .append(".");

        answer.append("SELECT * FROM ")
                .append(conditionTable.getSqlTableName())
                .append(" WHERE ")
                .append(condition.getSqlColumnName())
                .append(" ISNULL");

        return new LabTaskQA(task.getId(), query.toString(), answer.toString());
    }

}