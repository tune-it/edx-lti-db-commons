package com.tuneit.course.lab1;

import com.tuneit.courses.DBTaskGeneratorService;
import com.tuneit.courses.Task;
import org.junit.jupiter.api.Test;

import static com.tuneit.course.lab1.Lab1Data.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Lab1VariantTest {
    @Test
    public void testTask1AllVariants() {
        for (int i = 0; i < getAnswerTask1().length; i++) {
            DBTaskGeneratorService taskGenerator = new DBTaskGeneratorService();
            Task task = taskGenerator.getTask("1db56a451774ae25e5d2788a78f5863a", 1, 1, Integer.toString(i), 0);
            task.setAnswer(getAnswerTask1()[i]).setComplete(true);
            taskGenerator.checkTasks(task);
            System.out.println(task.getTaskId() + ") " + task.getQuestion());

            assertEquals(getQueryTask1()[i], task.getQuestion());
            assertEquals(1, task.getRating());
        }
    }

    @Test
    public void testTask2AllVariants() {
        for (int i = 0; i < getAnswerTask2().length; i++) {
            DBTaskGeneratorService taskGenerator = new DBTaskGeneratorService();
            Task task = taskGenerator.getTask("1db56a451774ae25e5d2788a78f5863a", 1, 2, Integer.toString(i), 0);
            task.setAnswer(getAnswerTask2()[i]).setComplete(true);
            taskGenerator.checkTasks(task);
            System.out.println(task.getTaskId() + ") " + task.getQuestion());

            assertEquals(getQueryTask2()[i], task.getQuestion());
            assertEquals(1, task.getRating());
        }
    }

    @Test
    public void testTask3AllVariants() {
        for (int i = 0; i < getAnswerTask3().length; i++) {
            DBTaskGeneratorService taskGenerator = new DBTaskGeneratorService();
            Task task = taskGenerator.getTask("1db56a451774ae25e5d2788a78f5863a", 1, 3, Integer.toString(i), 0);
            task.setAnswer(getAnswerTask3()[i]).setComplete(true);
            taskGenerator.checkTasks(task);
            System.out.println(task.getTaskId() + ") " + task.getQuestion());

            assertEquals(getQueryTask3()[i], task.getQuestion());
            assertEquals(1, task.getRating());
        }
    }

    @Test
    public void testTask4AllVariants() {
        for (int i = 0; i < getAnswerTask4().length; i++) {
            DBTaskGeneratorService taskGenerator = new DBTaskGeneratorService();
            Task task = taskGenerator.getTask("1db56a451774ae25e5d2788a78f5863a", 1, 4, Integer.toString(i), 0);
            task.setAnswer(getAnswerTask4()[i]).setComplete(true);
            taskGenerator.checkTasks(task);
            System.out.println(task.getTaskId() + ") " + task.getQuestion());

            assertEquals(getQueryTask4()[i], task.getQuestion());
            assertEquals(1, task.getRating());
        }
    }

    @Test
    public void testTask5AllVariants() {
        for (int i = 0; i < getAnswerTask5().length; i++) {
            DBTaskGeneratorService taskGenerator = new DBTaskGeneratorService();
            Task task = taskGenerator.getTask("1db56a451774ae25e5d2788a78f5863a", 1, 5, Integer.toString(i), 0);
            task.setAnswer(getAnswerTask5()[i]).setComplete(true);
            taskGenerator.checkTasks(task);
            System.out.println(task.getTaskId() + ") " + task.getQuestion());

            assertEquals(getQueryTask5()[i], task.getQuestion());
            assertEquals(1, task.getRating());
        }
    }

    @Test
    public void testTask6AllVariants() {
        for (int i = 0; i < getAnswerTask6().length; i++) {
            DBTaskGeneratorService taskGenerator = new DBTaskGeneratorService();
            Task task = taskGenerator.getTask("1db56a451774ae25e5d2788a78f5863a", 1, 6, Integer.toString(i), 0);
            task.setAnswer(getAnswerTask6()[i]).setComplete(true);
            taskGenerator.checkTasks(task);
            System.out.println(task.getTaskId() + ") " + task.getQuestion());

            assertEquals(getQueryTask6()[i], task.getQuestion());
            assertEquals(1, task.getRating());
        }
    }

    @Test
    public void testTask7AllVariants() {
        for (int i = 0; i < getAnswerTask7().length; i++) {
            DBTaskGeneratorService taskGenerator = new DBTaskGeneratorService();
            Task task = taskGenerator.getTask("1db56a451774ae25e5d2788a78f5863a", 1, 7, Integer.toString(i), 0);
            task.setAnswer(getAnswerTask7()[i]).setComplete(true);
            taskGenerator.checkTasks(task);
            System.out.println(task.getTaskId() + ") " + task.getQuestion());

            assertEquals(getQueryTask7()[i], task.getQuestion());
            assertEquals(1, task.getRating());
        }
    }

    @Test
    public void testTask8AllVariants() {
        for (int i = 0; i < getAnswerTask8().length; i++) {
            DBTaskGeneratorService taskGenerator = new DBTaskGeneratorService();
            Task task = taskGenerator.getTask("1db56a451774ae25e5d2788a78f5863a", 1, 8, Integer.toString(i), 0);
            task.setAnswer(getAnswerTask8()[i]).setComplete(true);
            taskGenerator.checkTasks(task);
            System.out.println(task.getTaskId() + ") " + task.getQuestion());

            assertEquals(getQueryTask8()[i], task.getQuestion());
            assertEquals(1, task.getRating());
        }
    }

    @Test
    public void testTask9AllVariants() {
        for (int i = 0; i < getAnswerTask9().length; i++) {
            DBTaskGeneratorService taskGenerator = new DBTaskGeneratorService();
            Task task = taskGenerator.getTask("1db56a451774ae25e5d2788a78f5863a", 1, 9, Integer.toString(i), 0);
            task.setAnswer(getAnswerTask9()[i]).setComplete(true);
            taskGenerator.checkTasks(task);
            System.out.println(task.getTaskId() + ") " + task.getQuestion());

            assertEquals(getQueryTask9()[i], task.getQuestion());
            assertEquals(1, task.getRating());
        }
    }

    @Test
    public void testTask10AllVariants() {
        for (int i = 0; i < getAnswerTask10().length; i++) {
            DBTaskGeneratorService taskGenerator = new DBTaskGeneratorService();
            Task task = taskGenerator.getTask("1db56a451774ae25e5d2788a78f5863a", 1, 10, Integer.toString(i), 0);
            task.setAnswer(getAnswerTask10()[i]).setComplete(true);
            taskGenerator.checkTasks(task);
            System.out.println(task.getTaskId() + ") " + task.getQuestion());

            assertEquals(getQueryTask10()[i], task.getQuestion());
            assertEquals(1, task.getRating());
        }
    }

}
