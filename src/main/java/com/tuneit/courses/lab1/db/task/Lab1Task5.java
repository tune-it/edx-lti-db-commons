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

        Condition condition = conditionTable.getRandomCondition(random);
        String option = condition.getRandomOption(random);

        Condition.PairSign conditionSign = condition.getConditionSign(random);

        query.append("Выведите все ")
                .append(conditionTable.getNativeTableName())
                .append(", которые удовлетовярют условию. ")
                .append(condition.getNativeColumnName())
                .append(conditionSign.getSignConditionNative())
                .append("\"")
                .append(option)
                .append("\".");

        answer.append("SELECT * FROM ")
                .append(conditionTable.getSqlTableName())
                .append(" WHERE ")
                .append(condition.getSqlColumnName())
                .append(conditionSign.getSignConditionSql())
                .append("'")
                .append(option)
                .append("';");

        return new LabTaskQA(task.getId(), query.toString(), answer.toString());
    }

}