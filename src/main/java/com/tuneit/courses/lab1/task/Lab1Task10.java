package com.tuneit.courses.lab1.task;

import com.tuneit.courses.Task;
import com.tuneit.courses.db.LabTaskQA;
import com.tuneit.courses.lab1.Lab1Task;
import com.tuneit.courses.lab1.schema.DiffDate;
import com.tuneit.courses.lab1.schema.DiffDateOption;
import com.tuneit.courses.lab1.schema.Schema01;

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
                .append(option.getNativeOption())
                .append(".");

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