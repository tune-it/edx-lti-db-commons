package com.tuneit.courses.lab2.task;

import com.tuneit.courses.Task;
import com.tuneit.courses.db.LabTaskQA;
import com.tuneit.courses.lab2.Lab2Task;
import com.tuneit.courses.lab2.schema.Schema02;

public class Lab2Task1 extends Lab2Task {
    @Override
    public LabTaskQA generate(Schema02 schema02, Task task) {
        query = new StringBuilder();
        answer = new StringBuilder();

        query.append("Сделать запрос для получения атрибутов из указанных таблиц, применив фильтры по указанным условиям:\n");


//        Table table = schema02.getRandomTable(task.getRandom());
//        List<Column> columns = table.getRandomColumns(task.getRandom(), 2);
//
//        List<String> columnsRevisedForWrite = new ArrayList<>();
//        columns.forEach(
//                column -> columnsRevisedForWrite.add(column.getNamePlural() + " (" + column.getColumnName().toLowerCase() + ")"));
//
//        query.append("Выведите все ");
//        writeColumnToQuery(columnsRevisedForWrite, ", ", query);
//        query.append(" для таблицы ")
//                .append(table.getTableName())
//                .append(".");
//
//        columnsRevisedForWrite.clear();
//        columns.forEach(
//                column -> columnsRevisedForWrite.add(column.getColumnName()));
//
//        answer.append("SELECT ");
//        writeColumnToQuery(columnsRevisedForWrite, ", ", answer);
//        answer.append(" FROM ")
//                .append(table.getTableName())
//                .append(";");
//
        return new LabTaskQA(task.getId(), query.toString(), answer.toString());
    }

}