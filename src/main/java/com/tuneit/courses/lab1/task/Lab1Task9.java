package com.tuneit.courses.lab1.task;

import com.tuneit.courses.Task;
import com.tuneit.courses.db.LabTaskQA;
import com.tuneit.courses.db.schema.Column;
import com.tuneit.courses.db.schema.Table;
import com.tuneit.courses.lab1.Lab1Task;
import com.tuneit.courses.lab1.schema.Schema01;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.tuneit.courses.db.schema.Schema.getRandomElement;

public class Lab1Task9 extends Lab1Task {
    @Override
    public LabTaskQA generate(Schema01 schema01, Task task) {
        query = new StringBuilder();
        answer = new StringBuilder();

        Random random = task.getRandom();
        Table table = getRandomElement(random, schema01.getTables());
        Column sortedColumn = getRandomElement(random, table.getColumns());
        table.getColumns().remove(sortedColumn);
        List<Column> columns = table.getRandomColumns(task.getRandom(), 2);
        boolean isDirectionSortedASC = random.nextBoolean();

        List<String> columnsRevisedForWrite = new ArrayList<>();
        columns.forEach(
                column -> columnsRevisedForWrite.add(column.getNamePlural()));

        query.append("Выведите содержимое полей: ");
        writeColumnToQuery(columnsRevisedForWrite, ", ", query);
        query.append(" из таблицы ")
                .append(table.getNameGenitive())
                .append(". Отсортируйте результат по столбцу ")
                .append(sortedColumn.getNamePlural())
                .append(" в порядке ");
        if (isDirectionSortedASC) {
            query.append("возрастания.");
        } else {
            query.append("убывания.");
        }
        query.append(" Вывести первые 3 строки.");

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
            answer.append(" ASC");
        } else {
            answer.append(" DESC");
        }

        answer.append(" LIMIT 3;");

        return new LabTaskQA(task.getId(), query.toString(), answer.toString());
    }

}