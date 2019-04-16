package com.tuneit.courses.lab1.db.task;

import com.tuneit.courses.Task;
import com.tuneit.courses.db.LabTaskQA;
import com.tuneit.courses.lab1.db.Lab1Task;
import com.tuneit.courses.lab1.db.schema.Condition;
import com.tuneit.courses.lab1.db.schema.ConditionTable;
import com.tuneit.courses.lab1.db.schema.Schema01;

import java.util.Random;

public class Lab1Task7 extends Lab1Task {
    @Override
    public LabTaskQA generate(Schema01 schema01, Task task) {
        query = new StringBuilder();
        answer = new StringBuilder();

        Random random = task.getRandom();

        boolean isUniteWordAnd = random.nextBoolean();

        ConditionTable conditionTable = schema01.getRandomConditionTable(random);

        Condition condition1 = conditionTable.getRandomCondition(random);
        conditionTable.getConditions().remove(condition1);
        Condition condition2 = conditionTable.getRandomCondition(random);

        char[] nativeColumnName = condition2.getNativeColumnName().toCharArray();
        nativeColumnName[0] = Character.toLowerCase(nativeColumnName[0]);
        condition2.setNativeColumnName(String.valueOf(nativeColumnName));

        String option1 = condition1.getRandomOption(random);
        String option2 = condition2.getRandomOption(random);

        Condition.PairSign conditionSign1 = condition1.getConditionSign(random);
        Condition.PairSign conditionSign2 = condition2.getConditionSign(random);

        query.append("Выведите все ")
                .append(conditionTable.getNativeTableName())
                .append(", которые удовлетовярют условию. ")
                .append(condition1.getNativeColumnName())
                .append(conditionSign1.getSignConditionNative())
                .append("\"")
                .append(option1)
                .append("\"")
                .append(isUniteWordAnd ? " и " : " или ")
                .append(condition2.getNativeColumnName())
                .append(conditionSign2.getSignConditionNative())
                .append("\"")
                .append(option2)
                .append("\".");

        answer.append("SELECT * FROM ")
                .append(conditionTable.getSqlTableName())
                .append(" WHERE ")
                .append(condition1.getSqlColumnName())
                .append(conditionSign1.getSignConditionSql())
                .append("'")
                .append(option1)
                .append("'")
                .append(isUniteWordAnd ? " AND " : " OR ")
                .append(condition2.getSqlColumnName())
                .append(conditionSign2.getSignConditionSql())
                .append("'")
                .append(option2)
                .append("'")
                .append(";");

        System.out.println(answer);
        return new LabTaskQA(task.getId(), query.toString(), answer.toString());
    }

}