package com.tuneit.course.lab1;

import com.tuneit.courses.Task;
import com.tuneit.courses.DBTaskGeneratorService;
import org.junit.jupiter.api.Test;

import static com.tuneit.course.lab1.TaskData.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskVariantTest {
    @Test
    public void testTask1AllVariants() {
        for (int i = 0; i < getAnswerTask1().length; i++) {
            DBTaskGeneratorService taskGenerator = new DBTaskGeneratorService();
            Task task = taskGenerator.getTask("serge@cs.ifmo.ru", "lab01", 0, Integer.toString(i), 0);
            task.setAnswer(getAnswerTask1()[i]).setComplete(true);
            taskGenerator.checkTasks(task);

            assertEquals(task.getQuestion(), getQueryTask1()[i]);
            assertEquals(task.getRating(), 1);
        }
    }

    @Test
    public void testTask2AllVariants() {
        for (int i = 0; i < getAnswerTask2().length; i++) {
            DBTaskGeneratorService taskGenerator = new DBTaskGeneratorService();
            Task task = taskGenerator.getTask("serge@cs.ifmo.ru", "lab01", 1, Integer.toString(i), 0);
            task.setAnswer(getAnswerTask2()[i]).setComplete(true);
            taskGenerator.checkTasks(task);

            assertEquals(task.getQuestion(), getQueryTask2()[i]);
            assertEquals(task.getRating(), 1);
        }
    }

    @Test
    public void testTask3AllVariants() {
        for (int i = 0; i < getAnswerTask3().length; i++) {
            DBTaskGeneratorService taskGenerator = new DBTaskGeneratorService();
            Task task = taskGenerator.getTask("serge@cs.ifmo.ru", "lab01", 2, Integer.toString(i), 0);
            task.setAnswer(getAnswerTask3()[i]).setComplete(true);
            taskGenerator.checkTasks(task);

            assertEquals(task.getQuestion(), getQueryTask3()[i]);
            assertEquals(task.getRating(), 1);
        }
    }

    @Test
    public void testTask4AllVariants() {
        for (int i = 0; i < getAnswerTask4().length; i++) {
            DBTaskGeneratorService taskGenerator = new DBTaskGeneratorService();
            Task task = taskGenerator.getTask("serge@cs.ifmo.ru", "lab01", 3, Integer.toString(i), 0);
            task.setAnswer(getAnswerTask4()[i]).setComplete(true);
            taskGenerator.checkTasks(task);

            assertEquals(task.getQuestion(), getQueryTask4()[i]);
            assertEquals(task.getRating(), 1);
        }
    }

    @Test
    public void testTask5AllVariants() {
        for (int i = 0; i < getAnswerTask5().length; i++) {
            DBTaskGeneratorService taskGenerator = new DBTaskGeneratorService();
            Task task = taskGenerator.getTask("serge@cs.ifmo.ru", "lab01", 4, Integer.toString(i), 0);
            task.setAnswer(getAnswerTask5()[i]).setComplete(true);
            taskGenerator.checkTasks(task);

            assertEquals(task.getQuestion(), getQueryTask5()[i]);
            assertEquals(task.getRating(), 1);
        }
    }

    @Test
    public void testTask6AllVariants() {
        for (int i = 0; i < getAnswerTask6().length; i++) {
            DBTaskGeneratorService taskGenerator = new DBTaskGeneratorService();
            Task task = taskGenerator.getTask("serge@cs.ifmo.ru", "lab01", 5, Integer.toString(i), 0);
            task.setAnswer(getAnswerTask6()[i]).setComplete(true);
            taskGenerator.checkTasks(task);

            assertEquals(task.getQuestion(), getQueryTask6()[i]);
            assertEquals(task.getRating(), 1);
        }
    }

    @Test
    public void testTask7AllVariants() {
        for (int i = 0; i < getAnswerTask7().length; i++) {
            DBTaskGeneratorService taskGenerator = new DBTaskGeneratorService();
            Task task = taskGenerator.getTask("serge@cs.ifmo.ru", "lab01", 6, Integer.toString(i), 0);
            task.setAnswer(getAnswerTask7()[i]).setComplete(true);
            taskGenerator.checkTasks(task);

            assertEquals(task.getQuestion(), getQueryTask7()[i]);
            assertEquals(task.getRating(), 1);
        }
    }

    @Test
    public void testTask8AllVariants() {
        for (int i = 0; i < getAnswerTask8().length; i++) {
            DBTaskGeneratorService taskGenerator = new DBTaskGeneratorService();
            Task task = taskGenerator.getTask("serge@cs.ifmo.ru", "lab01", 7, Integer.toString(i), 0);
            task.setAnswer(getAnswerTask8()[i]).setComplete(true);
            taskGenerator.checkTasks(task);

            assertEquals(task.getQuestion(), getQueryTask8()[i]);
            assertEquals(task.getRating(), 1);
        }
    }

    @Test
    public void testTask9AllVariants() {
        for (int i = 0; i < getAnswerTask9().length; i++) {
            DBTaskGeneratorService taskGenerator = new DBTaskGeneratorService();
            Task task = taskGenerator.getTask("serge@cs.ifmo.ru", "lab01", 8, Integer.toString(i), 0);
            task.setAnswer(getAnswerTask9()[i]).setComplete(true);
            taskGenerator.checkTasks(task);

            assertEquals(task.getQuestion(), getQueryTask9()[i]);
            assertEquals(task.getRating(), 1);
        }
    }

    @Test
    public void testTask10AllVariants() {
        for (int i = 0; i < getAnswerTask10().length; i++) {
            DBTaskGeneratorService taskGenerator = new DBTaskGeneratorService();
            Task task = taskGenerator.getTask("serge@cs.ifmo.ru", "lab01", 9, Integer.toString(i), 0);
            task.setAnswer(getAnswerTask10()[i]).setComplete(true);
            taskGenerator.checkTasks(task);

            assertEquals(task.getQuestion(), getQueryTask10()[i]);
            assertEquals(task.getRating(), 1);
        }
    }

    @Test
    public void testTask11AllVariants() {
        for (int i = 0; i < getAnswerTask11().length; i++) {
            DBTaskGeneratorService taskGenerator = new DBTaskGeneratorService();
            Task task = taskGenerator.getTask("serge@cs.ifmo.ru", "lab01", 10, Integer.toString(i), 0);
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
            Task task = taskGenerator.getTask("serge@cs.ifmo.ru", "lab01", 11, Integer.toString(i), 0);
            task.setAnswer(getAnswerTask12()[i]).setComplete(true);
            taskGenerator.checkTasks(task);

            assertEquals(task.getQuestion(), getQueryTask12()[i]);
            assertEquals(task.getRating(), 1);
        }
    }
}
