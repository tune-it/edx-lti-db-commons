package com.tuneit.courses.lab2;

import com.tuneit.courses.Task;
import com.tuneit.courses.lab.Lab;
import com.tuneit.courses.lab.LabTaskQA;
import com.tuneit.courses.lab.schema.SchemaLoader;
import com.tuneit.courses.lab2.schema.Schema02;
import com.tuneit.courses.lab2.task.*;

public class Lab02 implements Lab {

    @Override
    public LabTaskQA generate(Task task) {
        task.setLabId(2);
        Schema02 schema = (Schema02) SchemaLoader.getSchema(2);
        switch (task.getTaskId()) {
            case 1:
                return new Lab2Task1().generate(schema.clone(), task);
            case 2:
                return new Lab2Task2().generate(schema.clone(), task);
            case 3:
                return new Lab2Task3().generate(schema.clone(), task);
            case 4:
                return new Lab2Task4().generate(schema.clone(), task);
            case 5:
                return new Lab2Task5().generate(schema.clone(), task);
            default:
                return null;
        }
    }

    @Override
    public LabTaskQA[] generateAll(Task task) {
        task.setLabId(2);
        Schema02 schema = (Schema02) SchemaLoader.getSchema(2);

        return new LabTaskQA[]{
                new Lab2Task1().generate(schema.clone(), task),
                new Lab2Task2().generate(schema.clone(), task),
                new Lab2Task3().generate(schema.clone(), task),
                new Lab2Task4().generate(schema.clone(), task),
                new Lab2Task5().generate(schema.clone(), task),
        };
    }
}

