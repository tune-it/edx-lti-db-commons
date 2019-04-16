package com.tuneit.course.lab1;

import com.tuneit.courses.DBTaskGeneratorService;
import com.tuneit.courses.Task;
import org.junit.jupiter.api.Test;

import static com.tuneit.course.lab1.TaskData.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskVariantTest {
    @Test
    public void testTask1AllVariants() {
        for (int i = 0; i < getAnswerTask1().length; i++) {
            DBTaskGeneratorService taskGenerator = new DBTaskGeneratorService();
            Task task = taskGenerator.getTask("serge@cs.ifmo.ru", 1, 1, Integer.toString(i), 0);
            task.setAnswer(getAnswerTask1()[i]).setComplete(true);
            taskGenerator.checkTasks(task);

            assertEquals(getQueryTask1()[i], task.getQuestion());
            assertEquals(1, task.getRating());
        }
    }

    @Test
    public void testTask2AllVariants() {
        for (int i = 0; i < getAnswerTask2().length; i++) {
            DBTaskGeneratorService taskGenerator = new DBTaskGeneratorService();
            Task task = taskGenerator.getTask("serge@cs.ifmo.ru", 1, 2, Integer.toString(i), 0);
            task.setAnswer(getAnswerTask2()[i]).setComplete(true);
            taskGenerator.checkTasks(task);

            assertEquals(getQueryTask2()[i], task.getQuestion());
            assertEquals(1, task.getRating());
        }
    }

    @Test
    public void testTask3AllVariants() {
        for (int i = 0; i < getAnswerTask3().length; i++) {
            DBTaskGeneratorService taskGenerator = new DBTaskGeneratorService();
            Task task = taskGenerator.getTask("serge@cs.ifmo.ru", 1, 3, Integer.toString(i), 0);
            task.setAnswer(getAnswerTask3()[i]).setComplete(true);
            taskGenerator.checkTasks(task);

            assertEquals(getQueryTask3()[i], task.getQuestion());
            assertEquals(1, task.getRating());
        }
    }

    @Test
    public void testTask4AllVariants() {
        for (int i = 0; i < getAnswerTask4().length; i++) {
            DBTaskGeneratorService taskGenerator = new DBTaskGeneratorService();
            Task task = taskGenerator.getTask("serge@cs.ifmo.ru", 1, 4, Integer.toString(i), 0);
            task.setAnswer(getAnswerTask4()[i]).setComplete(true);
            taskGenerator.checkTasks(task);

            assertEquals(getQueryTask4()[i], task.getQuestion());
            assertEquals(1, task.getRating());
        }
    }

    @Test
    public void testTask5AllVariants() {
        for (int i = 0; i < getAnswerTask5().length; i++) {
            DBTaskGeneratorService taskGenerator = new DBTaskGeneratorService();
            Task task = taskGenerator.getTask("serge@cs.ifmo.ru", 1, 5, Integer.toString(i), 0);
            task.setAnswer(getAnswerTask5()[i]).setComplete(true);
            taskGenerator.checkTasks(task);

            assertEquals(getQueryTask5()[i], task.getQuestion());
            assertEquals(1, task.getRating());
        }
    }

    @Test
    public void testTask6AllVariants() {
        for (int i = 0; i < getAnswerTask6().length; i++) {
            DBTaskGeneratorService taskGenerator = new DBTaskGeneratorService();
            Task task = taskGenerator.getTask("serge@cs.ifmo.ru", 1, 6, Integer.toString(i), 0);
            task.setAnswer(getAnswerTask6()[i]).setComplete(true);
            taskGenerator.checkTasks(task);

            assertEquals(getQueryTask6()[i], task.getQuestion());
            assertEquals(1, task.getRating());
        }
    }

    @Test
    public void testTask7AllVariants() {
        for (int i = 0; i < getAnswerTask7().length; i++) {
            DBTaskGeneratorService taskGenerator = new DBTaskGeneratorService();
            Task task = taskGenerator.getTask("serge@cs.ifmo.ru", 1, 7, Integer.toString(i), 0);
            task.setAnswer(getAnswerTask7()[i]).setComplete(true);
            taskGenerator.checkTasks(task);

            assertEquals(getQueryTask7()[i], task.getQuestion());
            assertEquals(1, task.getRating());
        }
    }

}
