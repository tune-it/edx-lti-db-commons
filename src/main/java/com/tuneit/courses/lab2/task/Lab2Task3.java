package com.tuneit.courses.lab2.task;

import com.tuneit.courses.Task;
import com.tuneit.courses.db.LabTaskQA;
import com.tuneit.courses.db.schema.Column;
import com.tuneit.courses.db.schema.Table;
import com.tuneit.courses.lab2.Lab2Task;
import com.tuneit.courses.lab2.schema.Schema02;
import com.tuneit.courses.lab2.schema.Subquery;
import com.tuneit.courses.lab2.schema.TableSubquery;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Lab2Task3 extends Lab2Task {
    @Override
    public LabTaskQA generate(Schema02 schema02, Task task) {
        query = new StringBuilder();
        answer = new StringBuilder();

        Random random = task.getRandom();

        boolean isGreaterOption = random.nextBoolean();

        TableSubquery tableSubquery = schema02.getRandomTableSubquery(random);
        Table leftTable = schema02.findTableBySqlName(tableSubquery.getSqlTableName());

        Subquery subquery = tableSubquery.getRandomSubquery(random);
        Column leftJoinColumn = leftTable.findColumn(subquery.getSqlNameColumn());
        Column rightJoinColumn = leftTable.findColumn(subquery.getSqlNameJoinColumn());
        Table rightTable = schema02.findTableBySqlName(subquery.getSqlNameJoinTable());

        String nativeOption;
        String sqlOption;
        if (isGreaterOption) {
            String option = subquery.getRandomOption(random);
            nativeOption = " более " + option;
            sqlOption = ">" + option;
        } else {
            String option = subquery.getRandomOption(random);
            nativeOption = " менее " + option;
            sqlOption = "<" + option;
        }

        leftTable.getColumns().remove(leftJoinColumn);

        List<Column> columns = leftTable.getRandomColumns(task.getRandom(), 1);

        List<String> columnsRevisedForWrite = new ArrayList<>();
        columns.forEach(
                column -> columnsRevisedForWrite.add(column.getNamePlural()));

        query.append("Выведите все ");
        writeColumnToQuery(columnsRevisedForWrite, ", ", query);
        query.append(" из таблицы ")
                .append(leftTable.getNameGenitive())
                .append(", ")
                .append(subquery.getNativeQuery())
                .append(nativeOption)
                .append(".");

        columnsRevisedForWrite.clear();
        columns.forEach(
                column -> columnsRevisedForWrite.add(column.getColumnName()));

        answer.append("select ");
        writeColumnToQuery(columnsRevisedForWrite, ", ", answer);
        answer.append(" from ")
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