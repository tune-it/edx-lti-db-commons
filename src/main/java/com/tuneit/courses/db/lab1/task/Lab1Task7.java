package com.tuneit.courses.db.lab1.task;

import com.tuneit.courses.db.Task;
import com.tuneit.courses.db.lab.LabTaskQA;
import com.tuneit.courses.db.lab.schema.Condition;
import com.tuneit.courses.db.lab.schema.ConditionTable;
import com.tuneit.courses.db.lab.schema.Table;
import com.tuneit.courses.db.lab1.Lab1Task;
import com.tuneit.courses.db.lab1.schema.Schema01;

import java.util.Random;

import static com.tuneit.courses.db.lab.schema.Schema.getRandomElement;

public class Lab1Task7 extends Lab1Task {
    @Override
    public LabTaskQA generate(Schema01 schema01, Task task) {
        query = new StringBuilder();
        answer = new StringBuilder();

        Random random = task.getRandom();

        ConditionTable conditionTable = getRandomElement(random, schema01.getConditionTables());
        Table table = schema01.findTableBySqlName(conditionTable.getSqlTableName());

        Condition condition1 = getRandomElement(random, conditionTable.getConditions());
        conditionTable.getConditions().remove(condition1);
        Condition condition2 = getRandomElement(random, conditionTable.getConditions());

        char[] nativeColumnName = condition2.getNativeColumnName().toCharArray();
        nativeColumnName[0] = Character.toLowerCase(nativeColumnName[0]);
        condition2.setNativeColumnName(String.valueOf(nativeColumnName));

        String option1 = getRandomElement(random, condition1.getOptionConditions());
        String option2 = getRandomElement(random, condition2.getOptionConditions());

        Condition.PairSign conditionSign1 = condition1.getConditionSign(random);
        Condition.PairSign conditionSign2 = condition2.getConditionSign(random);

        query.append("Выведите содержимое всех полей из таблицы ")
                .append(table.getNameGenitive())
                .append(", которые удовлетворяют следующему условию: ")
                .append(condition1.getNativeColumnName())
                .append(conditionSign1.getSignConditionNative())
                .append("\"")
                .append(option1)
                .append("\"")
                .append(" или ")
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
                .append(" OR ")
                .append(condition2.getSqlColumnName())
                .append(conditionSign2.getSignConditionSql())
                .append("'")
                .append(option2)
                .append("'")
                .append(";");

        return new LabTaskQA(task.getId(), query.toString(), answer.toString());
    }

}