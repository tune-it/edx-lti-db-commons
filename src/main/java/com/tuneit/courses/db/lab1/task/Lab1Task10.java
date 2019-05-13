package com.tuneit.courses.db.lab1.task;

import com.tuneit.courses.db.Task;
import com.tuneit.courses.db.LabTaskQA;
import com.tuneit.courses.db.lab1.Lab1Task;
import com.tuneit.courses.db.lab1.schema.DiffDate;
import com.tuneit.courses.db.lab1.schema.DiffDateOption;
import com.tuneit.courses.db.lab1.schema.Schema01;

import java.util.Random;

import static com.tuneit.courses.db.schema.Schema.getRandomElement;

public class Lab1Task10 extends Lab1Task {

    @Override
    public LabTaskQA generate(Schema01 schema01, Task task) {
        query = new StringBuilder();
        answer = new StringBuilder();

        Random random = task.getRandom();

        DiffDate diffDate = getRandomElement(random, schema01.getDiffDates());
        DiffDateOption option = getRandomElement(random, diffDate.getDiffDateOptions());

        query.append("Подсчитайте ")
                .append(diffDate.getNativeDescription())
                .append(" ")
                .append(option.getNativeOption())
                .append(". Важно: в postgresql результат вычитания двух дат имеет тип interval. Для извлечения количества минут из данных типа interval можно воспользоваться функцией extract.");

        answer.append("SELECT ")
                .append("EXTRACT(EPOCH FROM ")
                .append(diffDate.getSqlNameColumn1())
                .append(" - ")
                .append(diffDate.getSqlNameColumn2())
                .append(")")
                .append(option.getSqlOption())
                .append(" FROM ")
                .append(diffDate.getSqlTableName())
                .append(";");

        return new LabTaskQA(task.getId(), query.toString(), answer.toString());
    }

}