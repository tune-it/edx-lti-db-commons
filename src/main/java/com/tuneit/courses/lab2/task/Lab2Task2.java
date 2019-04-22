package com.tuneit.courses.lab2.task;

import com.tuneit.courses.Task;
import com.tuneit.courses.db.LabTaskQA;
import com.tuneit.courses.db.schema.Column;
import com.tuneit.courses.db.schema.Table;
import com.tuneit.courses.lab2.Lab2Task;
import com.tuneit.courses.lab2.schema.Case;
import com.tuneit.courses.lab2.schema.Schema02;
import com.tuneit.courses.lab2.schema.TableCases;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.tuneit.courses.db.schema.Schema.getRandomElement;

public class Lab2Task2 extends Lab2Task {
    @Override
    public LabTaskQA generate(Schema02 schema02, Task task) {
        query = new StringBuilder();
        answer = new StringBuilder();

        Random random = task.getRandom();

        TableCases tableCases = getRandomElement(random, schema02.getTablesCases());
        Table table = schema02.findTableBySqlName(tableCases.getSqlTableName());

        Case randomCase = getRandomElement(random, tableCases.getCases());
        Column caseColumn = table.findColumn(randomCase.getSqlNameColumn());
        table.getColumns().remove(caseColumn);


        List<Column> columns = table.getRandomColumns(task.getRandom(), 1);

        List<String> columnsRevisedForWrite = new ArrayList<>();
        columns.forEach(
                column -> columnsRevisedForWrite.add(column.getNamePlural()));

        query.append("Выведите содержимое полей: ");
        writeColumnToQuery(columnsRevisedForWrite, ", ", query);
        query.append(", ")
                .append(caseColumn.getNamePlural())
                .append(" из таблицы ")
                .append(table.getNameGenitive())
                .append(", заменив значения ")
                .append(caseColumn.getNameGenitivePlural())
                .append(" ")
                .append(randomCase.getNativeQuery())
                .append(".");

        columnsRevisedForWrite.clear();
        columns.forEach(
                column -> columnsRevisedForWrite.add(column.getColumnName()));

        answer.append("select ");
        writeColumnToQuery(columnsRevisedForWrite, ", ", answer);
        answer.append(", ")
                .append(randomCase.getSqlQuery())
                .append(" from ")
                .append(table.getTableName());

        return new LabTaskQA(task.getId(), query.toString(), answer.toString());
    }

}