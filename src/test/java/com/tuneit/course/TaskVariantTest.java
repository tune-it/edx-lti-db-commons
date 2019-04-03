package com.tuneit.course;

import com.tuneit.courses.Task;
import com.tuneit.courses.db.DBTaskGeneratorService;
import com.tuneit.courses.db.LabTaskQA;
import com.tuneit.courses.db.schema.Schema;
import com.tuneit.courses.db.schema.SchemaLoader;
import org.junit.jupiter.api.Test;

import static com.tuneit.course.TaskData.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskVariantTest {
    @Test
    public void testTask5AllVariants() {
        for (int i = 0; i < getAnswerTask5().length; i++) {
            DBTaskGeneratorService taskGenerator = new DBTaskGeneratorService();
            Task task = taskGenerator.getTask("serge@cs.ifmo.ru", "lab02", 4, Integer.toString(i), 0);
            task.setAnswer(getAnswerTask5()[i]).setComplete(true);
            taskGenerator.checkTasks(task);

            assertEquals(task.getQuestion(), getQueryTask5()[i]);
            assertEquals(task.getRating(), 1);
        }
    }

    @Test
    public void testTask11AllVariants() {
        for (int i = 0; i < getAnswerTask11().length; i++) {
            DBTaskGeneratorService taskGenerator = new DBTaskGeneratorService();
            Task task = taskGenerator.getTask("serge@cs.ifmo.ru", "lab02", 10, Integer.toString(i), 0);
            task.setAnswer(getAnswerTask11()[i]).setComplete(true);
            taskGenerator.checkTasks(task);

            assertEquals(task.getQuestion(), getQueryTask11()[i]);
            assertEquals(task.getRating(), 1);
        }
    }

    @Test
    public void testTask12AllVariants() {
        for (int i = 0; i < getAnswerTask12().length; i++) {
            DBTaskGeneratorService taskGenerator = new DBTaskGeneratorService();
            Task task = taskGenerator.getTask("serge@cs.ifmo.ru", "lab02", 11, Integer.toString(i), 0);
            task.setAnswer(getAnswerTask12()[i]).setComplete(true);
            taskGenerator.checkTasks(task);

            assertEquals(task.getQuestion(), getQueryTask12()[i]);
            assertEquals(task.getRating(), 1);
        }
    }

    private LabTaskQA[] getLabTaskQAS(String variant, DBTaskGeneratorService taskGeneratorService) {
        LabTaskQA[] labTaskQAS = new LabTaskQA[12];
        Task[] tasks = taskGeneratorService.getTasks("serge@cs.ifmo.ru", "lab02", variant, 0);

        for (int i = 0; i < tasks.length; i++) {
            Task task = tasks[i];
            Schema schema = SchemaLoader.getSchema(task.getYearOfStudy(), task.getStudentId());
            LabTaskQA labTaskQA = taskGeneratorService.findLabTask(task).generate(schema, task);
            labTaskQAS[i] = labTaskQA;
        }

        return labTaskQAS;
    }
}
