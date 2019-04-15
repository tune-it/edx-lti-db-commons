package com.tuneit.courses.lab1.db.task;

import com.tuneit.courses.Task;
import com.tuneit.courses.db.LabTaskQA;
import com.tuneit.courses.db.schema.Column;
import com.tuneit.courses.db.schema.Table;
import com.tuneit.courses.lab1.db.Lab1Task;
import com.tuneit.courses.lab1.db.schema.Schema01;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Lab1Task4 extends Lab1Task {
    @Override
    public LabTaskQA generate(Schema01 schema01, Task task) {
        query = new StringBuilder();
        answer = new StringBuilder();

        Random random = task.getRandom();
        Table table = schema01.getRandomTable(random).clone();
        Column sortedColumn = table.getRandomColumn(random);
        table.getColumns().remove(sortedColumn);
        List<Column> columns = table.getRandomColumns(task.getRandom(), 2);
        boolean isDirectionSortedASC = random.nextBoolean();

        List<String> columnsRevisedForWrite = new ArrayList<>();
        columns.forEach(
                column -> columnsRevisedForWrite.add(column.getNamePlural() + " (" + column.getColumnName().toLowerCase() + ")"));

        query.append("Выведите все ");
        writeColumnToQuery(columnsRevisedForWrite, ", ", query);
        query.append(" для таблицы ")
                .append(table.getTableName())
                .append(". Отсортированные по столбцу ")
                .append(sortedColumn.getNamePlural())
                .append(" (")
                .append(sortedColumn.getColumnName().toLowerCase())
                .append(") по ");
        if (isDirectionSortedASC) {
            query.append("возрастанию.");
        } else {
            query.append("убыванию.");
        }

        columnsRevisedForWrite.clear();
        columns.forEach(
                column -> columnsRevisedForWrite.add(column.getColumnName()));

        answer.append("SELECT ");
        writeColumnToQuery(columnsRevisedForWrite, ", ", answer);
        answer.append(" FROM ")
                .append(table.getTableName())
                .append(" ORDER BY ")
                .append(sortedColumn.getColumnName());
        if (isDirectionSortedASC) {
            answer.append(" ASC;");
        } else {
            answer.append(" DESC;");
        }

        return new LabTaskQA(task.getId(), query.toString(), answer.toString());
    }

}