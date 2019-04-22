package com.tuneit.courses.lab1.task;

import com.tuneit.courses.Task;
import com.tuneit.courses.db.LabTaskQA;
import com.tuneit.courses.db.schema.Aggregation;
import com.tuneit.courses.db.schema.Condition;
import com.tuneit.courses.db.schema.ConditionTable;
import com.tuneit.courses.lab1.Lab1Task;
import com.tuneit.courses.lab1.schema.Schema01;

import java.util.Random;

public class Lab1Task8 extends Lab1Task {
    @Override
    public LabTaskQA generate(Schema01 schema01, Task task) {
        query = new StringBuilder();
        answer = new StringBuilder();

        Random random = task.getRandom();

        Aggregation aggregation = schema01.getRandomAggregation(random);

        String conditionName = aggregation.getConditionColumnName();
        Condition condition = findCondition(schema01, conditionName);
        Condition.PairSign sign = condition.getConditionSign(random);

        String option = condition.getRandomOption(random);

        query.append("Выведите ")
                .append(aggregation.getColumnFunctionNativeName())
                .append(": ")
                .append(condition.getNativeColumnName())
                .append(sign.getSignConditionNative())
                .append("\"")
                .append(option)
                .append("\".");

        answer.append("SELECT ")
                .append(aggregation.getFunctionSqlName())
                .append("(")
                .append(aggregation.getColumnSqlName())
                .append(") FROM ")
                .append(aggregation.getTableSqlName())
                .append(" WHERE ")
                .append(condition.getSqlColumnName())
                .append(sign.getSignConditionSql())
                .append("'")
                .append(option)
                .append("';");


        return new LabTaskQA(task.getId(), query.toString(), answer.toString());
    }

    private Condition findCondition(Schema01 schema01, String conditionName) {
        for (ConditionTable conditionTable : schema01.getConditionTables()) {
            for (Condition condition : conditionTable.getConditions()) {
                if (condition.getSqlColumnName().equals(conditionName)) {
                    return condition;
                }
            }
        }
        return null;
    }

}