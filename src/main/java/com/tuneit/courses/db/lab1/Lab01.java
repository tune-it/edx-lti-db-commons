package com.tuneit.courses.db.lab1;

import com.tuneit.courses.db.Task;
import com.tuneit.courses.db.lab.Lab;
import com.tuneit.courses.db.lab.LabTaskQA;
import com.tuneit.courses.db.lab.schema.SchemaLoader;
import com.tuneit.courses.db.lab1.schema.Schema01;
import com.tuneit.courses.db.lab1.task.*;

public class Lab01 implements Lab {

    @Override
    public LabTaskQA generate(Task task) {
        task.setLabId(1);
        Schema01 schema = (Schema01) SchemaLoader.getSchema(1);
        switch (task.getTaskId()) {
            case 1:
                return new Lab1Task1().generate(schema.clone(), task);
            case 2:
                return new Lab1Task2().generate(schema.clone(), task);
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
                return new Lab1Task10().generate(schema.clone(), task);
            default:
                return null;
        }
    }

    @Override
    public LabTaskQA[] generateAll(Task task) {
        task.setLabId(1);
        Schema01 schema = (Schema01) SchemaLoader.getSchema(1);

        return new LabTaskQA[]{
                new Lab1Task1().generate(schema.clone(), task),
                new Lab1Task2().generate(schema.clone(), task),
                new Lab1Task3().generate(schema.clone(), task),
                new Lab1Task4().generate(schema.clone(), task),
                new Lab1Task5().generate(schema.clone(), task),
                new Lab1Task6().generate(schema.clone(), task),
                new Lab1Task7().generate(schema.clone(), task),
                new Lab1Task8().generate(schema.clone(), task),
                new Lab1Task9().generate(schema.clone(), task),
                new Lab1Task10().generate(schema.clone(), task),
        };
    }
}

