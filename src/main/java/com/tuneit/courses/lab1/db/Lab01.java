package com.tuneit.courses.lab1.db;

import com.tuneit.courses.Task;
import com.tuneit.courses.db.Lab;
import com.tuneit.courses.db.LabTaskQA;
import com.tuneit.courses.db.schema.SchemaLoader;
import com.tuneit.courses.lab1.db.schema.Schema01;
import com.tuneit.courses.lab1.db.task.Lab1Task1;
import com.tuneit.courses.lab1.db.task.Lab1Task2;
import com.tuneit.courses.lab1.db.task.Lab1Task3;
import com.tuneit.courses.lab1.db.task.Lab1Task4;

public class Lab01 implements Lab {

    @Override
    public LabTaskQA generate(Task task) {
        task.setLabId(1);
        Schema01 schema = (Schema01) SchemaLoader.getSchema(1);
        switch (task.getTaskId()) {
            case 1:
                return new Lab1Task1().generate(schema, task);
            case 2:
                return new Lab1Task2().generate(schema, task);
            case 3:
                return new Lab1Task3().generate(schema, task);
            case 4:
                return new Lab1Task4().generate(schema, task);
            /*case 5:
                return new Task5().generate(schema, task);
            case 6:
                return new Task6().generate(schema, task);
            case 7:
                return new Task7().generate(schema, task);
            case 8:
                return new Task8().generate(schema, task);
            case 9:
                return new Task9().generate(schema, task);
            case 10:
                return new Task10().generate(schema, task);*/
            default:
                return null;
        }
    }

    @Override
    public LabTaskQA[] generateAll(Task task) {
        task.setLabId(1);
        Schema01 schema = (Schema01) SchemaLoader.getSchema(1);

        return new LabTaskQA[]{
                new Lab1Task1().generate(schema, task),
                new Lab1Task2().generate(schema, task),
                new Lab1Task3().generate(schema, task),
                new Lab1Task4().generate(schema, task),
        };
    }
}

