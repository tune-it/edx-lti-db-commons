package com.tuneit.courses.lab2;

import com.tuneit.courses.Task;
import com.tuneit.courses.db.Lab;
import com.tuneit.courses.db.LabTaskQA;
import com.tuneit.courses.db.schema.SchemaLoader;
import com.tuneit.courses.lab2.schema.Schema02;
import com.tuneit.courses.lab2.task.Lab2Task1;

public class Lab02 implements Lab {

    @Override
    public LabTaskQA generate(Task task) {
        task.setLabId(2);
        Schema02 schema = (Schema02) SchemaLoader.getSchema(2);
        switch (task.getTaskId()) {
            case 1:
                return new Lab2Task1().generate(schema.clone(), task);
            /*case 2:
                return new Lab2Task1().generate(schema.clone(), task);
            case 3:
                return new Lab1Task3().generate(schema.clone(), task);
            case 4:
                return new Lab1Task4().generate(schema.clone(), task);
            case 5:
                return new Lab1Task5().generate(schema.clone(), task);
            case 6:
                return new Lab1Task6().generate(schema.clone(), task);
            case 7:
                return new Lab1Task7().generate(schema.clone(), task);
            case 8:
                return new Lab1Task8().generate(schema.clone(), task);
            case 9:
                return new Lab1Task9().generate(schema.clone(), task);
            case 10:
                return new Lab1Task10().generate(schema.clone(), task);*/
            default:
                return null;
        }
    }

    @Override
    public LabTaskQA[] generateAll(Task task) {
        task.setLabId(2);
        Schema02 schema = (Schema02) SchemaLoader.getSchema(2);

        return new LabTaskQA[]{
                new Lab2Task1().generate(schema.clone(), task),/*
                new Lab2Task1().generate(schema.clone(), task),
                new Lab1Task3().generate(schema.clone(), task),
                new Lab1Task4().generate(schema.clone(), task),
                new Lab1Task5().generate(schema.clone(), task),
                new Lab1Task6().generate(schema.clone(), task),
                new Lab1Task7().generate(schema.clone(), task),
                new Lab1Task8().generate(schema.clone(), task),
                new Lab1Task9().generate(schema.clone(), task),
                new Lab1Task10().generate(schema.clone(), task),*/
        };
    }
}

