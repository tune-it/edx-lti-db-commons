package com.tuneit.courses.db.lab2.task;

import com.tuneit.courses.db.Task;
import com.tuneit.courses.db.lab.LabTaskQA;
import com.tuneit.courses.db.lab.schema.Column;
import com.tuneit.courses.db.lab.schema.Condition;
import com.tuneit.courses.db.lab2.Lab2Task;
import com.tuneit.courses.db.lab2.schema.Reference;
import com.tuneit.courses.db.lab2.schema.Schema02;

import java.util.Random;

import static com.tuneit.courses.db.lab.schema.Schema.getRandomElement;

public class Lab2Task4 extends Lab2Task {
    @Override
    public LabTaskQA generate(Schema02 schema02, Task task) {
        query = new StringBuilder();
        answer = new StringBuilder();

        Random random = task.getRandom();

        Reference.ChainTable chainTable = schema02.getRandomChainTable(random);

        schema02.getConditionTables().removeIf(conditionTable ->
                !conditionTable.getSqlTableName().equalsIgnoreCase(chainTable.getRightTable().getTableName()));

        Condition condition = getRandomElement(random, schema02.getConditionTables().get(0).getConditions());
        String option = getRandomElement(random, condition.getOptionConditions());
        Condition.PairSign conditionSign = condition.getConditionSign(random);

        Column column = getRandomElement(random, chainTable.getLeftTable().getColumns());

        query.append("Напишите запрос для получения полей из объединённых таблиц, " +
                "удовлетворяющих указанным условиям. Таблицы: ")
                .append(chainTable.getLeftTable().getNameGenitive())
                .append(", ")
                .append(chainTable.getRightTable().getNameGenitive())
                .append(". Поля: все уникальные значения ")
                .append(column.getNameGenitivePlural())
                .append(" из таблицы ")
                .append(chainTable.getLeftTable().getNameGenitive())
                .append(". Условия: ")
                .append(condition.getNativeColumnName())
                .append(conditionSign.getSignConditionNative())
                .append("\"")
                .append(option)
                .append("\". В запросе должен использоваться INNER JOIN.");

        answer.append("select distinct ")
                .append(chainTable.getLeftTable().getTableName())
                .append(".")
                .append(column.getColumnName())
                .append(" from ")
                .append(chainTable.getLeftTable().getTableName())
                .append(" inner join ")
                .append(chainTable.getRightTable().getTableName())
                .append(" on ")
                .append(chainTable.getLeftTable().getTableName())
                .append(".")
                .append(chainTable.getLeftColumn().getColumnName())
                .append(" = ")
                .append(chainTable.getRightTable().getTableName())
                .append(".")
                .append(chainTable.getRightColumn().getColumnName())
                .append(" where ")
                .append(chainTable.getRightTable().getTableName())
                .append(".")
                .append(condition.getSqlColumnName())
                .append(conditionSign.getSignConditionSql())
                .append("'")
                .append(option)
                .append("';");

        return new LabTaskQA(task.getId(), query.toString(), answer.toString());
    }

}