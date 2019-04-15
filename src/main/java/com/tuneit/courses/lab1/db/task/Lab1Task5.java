package com.tuneit.courses.lab1.db.task;

import com.tuneit.courses.Task;
import com.tuneit.courses.db.LabTaskQA;
import com.tuneit.courses.lab1.db.Lab1Task;
import com.tuneit.courses.lab1.db.schema.Condition;
import com.tuneit.courses.lab1.db.schema.ConditionTable;
import com.tuneit.courses.lab1.db.schema.Schema01;

import java.util.Random;

public class Lab1Task5 extends Lab1Task {
    @Override
    public LabTaskQA generate(Schema01 schema01, Task task) {
        query = new StringBuilder();
        answer = new StringBuilder();

        Random random = task.getRandom();

        ConditionTable conditionTable = schema01.getRandomConditionTable(random);
        conditionTable.getConditions().removeIf(condition -> condition.getCountConditions() != 1);

        Condition condition = conditionTable.getRandomCondition(random);
        String option = condition.getRandomOption(random);

        String signConditionNative;
        String signConditionSql;
        if (!condition.getGreater().isEmpty() && random.nextBoolean()) {
            signConditionNative = " " + condition.getGreater() + " ";
            signConditionSql = " > ";
        } else if (!condition.getBelow().isEmpty() && random.nextBoolean()) {
            signConditionNative = " " + condition.getBelow() + " ";
            signConditionSql = " < ";
        } else if (!condition.getEquals().isEmpty() && random.nextBoolean()) {
            signConditionNative = " " + condition.getEquals() + " ";
            signConditionSql = " = ";
        } else {
            if (!condition.getGreater().isEmpty()) {
                signConditionNative = " " + condition.getGreater() + " ";
                signConditionSql = " > ";
            } else if (!condition.getBelow().isEmpty()) {
                signConditionNative = " " + condition.getBelow() + " ";
                signConditionSql = " < ";
            } else {
                signConditionNative = " " + condition.getEquals() + " ";
                signConditionSql = " = ";
            }
        }


        query.append("Выведите все ")
                .append(conditionTable.getNativeTableName())
                .append(", которые удовлетовярют условию. ")
                .append(condition.getNativeColumnName())
                .append(signConditionNative)
                .append("\"")
                .append(option)
                .append("\".");

        answer.append("SELECT * FROM ")
                .append(conditionTable.getSqlTableName())
                .append(" WHERE ")
                .append(condition.getSqlColumnName())
                .append(signConditionSql)
                .append("'")
                .append(option)
                .append("';");

        return new LabTaskQA(task.getId(), query.toString(), answer.toString());
    }

}