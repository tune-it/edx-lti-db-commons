package com.tuneit.courses.lab2.task;

import com.tuneit.courses.Task;
import com.tuneit.courses.lab.LabTaskQA;
import com.tuneit.courses.lab.schema.Column;
import com.tuneit.courses.lab.schema.Table;
import com.tuneit.courses.lab2.Lab2Task;
import com.tuneit.courses.lab2.schema.Schema02;
import com.tuneit.courses.lab2.schema.Subquery;
import com.tuneit.courses.lab2.schema.TableSubquery;

import java.util.Random;

import static com.tuneit.courses.lab.schema.Schema.getRandomElement;

public class Lab2Task3 extends Lab2Task {
    @Override
    public LabTaskQA generate(Schema02 schema02, Task task) {
        query = new StringBuilder();
        answer = new StringBuilder();

        Random random = task.getRandom();

        boolean isGreaterOption = random.nextBoolean();

        TableSubquery tableSubquery = getRandomElement(random, schema02.getTablesSubqueries());
        Table leftTable = schema02.findTableBySqlName(tableSubquery.getSqlTableName());

        Subquery subquery = getRandomElement(random, tableSubquery.getSubqueries());
        Column leftJoinColumn = leftTable.findColumn(subquery.getSqlNameColumn());
        Column rightJoinColumn = leftTable.findColumn(subquery.getSqlNameJoinColumn());
        Table rightTable = schema02.findTableBySqlName(subquery.getSqlNameJoinTable());

        String nativeOption;
        String sqlOption;
        if (isGreaterOption) {
            String option = getRandomElement(random, subquery.getOptions());
            nativeOption = " более " + option;
            sqlOption = ">" + option;
        } else {
            String option = getRandomElement(random, subquery.getOptions());
            nativeOption = " менее " + option;
            sqlOption = "<" + option;
        }

        leftTable.getColumns().remove(leftJoinColumn);

        Column column = getRandomElement(random, leftTable.getColumns());

        query.append("Выведите все уникальные значения ")
                .append(column.getNameGenitivePlural())
                .append(" из таблицы ")
                .append(leftTable.getNameGenitive())
                .append(", ")
                .append(subquery.getNativeQuery())
                .append(nativeOption)
                .append(".");

        answer.append("select distinct ")
                .append(column.getColumnName())
                .append(" from ")
                .append(leftTable.getTableName())
                .append(" where ")
                .append(leftJoinColumn.getColumnName())
                .append(" in (select ")
                .append(rightJoinColumn.getColumnName())
                .append(" from ")
                .append(rightTable.getTableName())
                .append(" group by ")
                .append(rightJoinColumn.getColumnName())
                .append(" having ")
                .append(subquery.getSqlFunction())
                .append("(")
                .append(subquery.getSqlNameConditionColumn())
                .append(")")
                .append(sqlOption)
                .append(")");

        return new LabTaskQA(task.getId(), query.toString(), answer.toString());
    }

}