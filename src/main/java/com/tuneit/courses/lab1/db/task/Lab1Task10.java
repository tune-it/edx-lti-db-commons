package com.tuneit.courses.lab1.db.task;

import com.tuneit.courses.Task;
import com.tuneit.courses.db.LabTaskQA;
import com.tuneit.courses.lab1.db.Lab1Task;
import com.tuneit.courses.lab1.db.schema.DiffDate;
import com.tuneit.courses.lab1.db.schema.DiffDateOption;
import com.tuneit.courses.lab1.db.schema.Schema01;

import java.util.Random;

public class Lab1Task10 extends Lab1Task {

    @Override
    public LabTaskQA generate(Schema01 schema01, Task task) {
        query = new StringBuilder();
        answer = new StringBuilder();

        Random random = task.getRandom();

        DiffDate diffDate = schema01.getRandomDiffDate(random);
        DiffDateOption option = diffDate.getRandomDiffDateOption(random);

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