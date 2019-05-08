package com.tuneit.courses.db.lab1.task;

import com.tuneit.courses.db.Task;
import com.tuneit.courses.db.lab.LabTaskQA;
import com.tuneit.courses.db.lab.schema.Aggregation;
import com.tuneit.courses.db.lab.schema.Condition;
import com.tuneit.courses.db.lab1.Lab1Task;
import com.tuneit.courses.db.lab1.schema.Schema01;

import java.util.Random;

import static com.tuneit.courses.db.lab.schema.Schema.getRandomElement;

public class Lab1Task8 extends Lab1Task {
    @Override
    public LabTaskQA generate(Schema01 schema01, Task task) {
        query = new StringBuilder();
        answer = new StringBuilder();

        Random random = task.getRandom();

        Aggregation aggregation = getRandomElement(random, schema01.getAggregations());

        String conditionName = aggregation.getConditionColumnName();
        Condition condition = schema01.findCondition(conditionName);
        Condition.PairSign sign = condition.getConditionSign(random);

        String option = getRandomElement(random, condition.getOptionConditions());

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

}