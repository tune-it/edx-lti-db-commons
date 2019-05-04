package com.tuneit.course.lab2;

import com.tuneit.courses.DBTaskGeneratorService;
import com.tuneit.courses.Task;
import org.junit.jupiter.api.Test;

import static com.tuneit.course.lab2.Lab2Data.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Lab2VariantTest {
    @Test
    public void testTask1AllVariants() {
        for (int i = 0; i < getAnswerTask1().length; i++) {
            DBTaskGeneratorService taskGenerator = new DBTaskGeneratorService();
            Task task = taskGenerator.getTask("1db56a451774ae25e5d2788a78f5863a", 2, 1, Integer.toString(i), 0);
            task.setAnswer(getAnswerTask1()[i]).setComplete(true);
            taskGenerator.checkTasks(task);
            System.out.println("Выведите все уникальные значения статусов из таблицы рейсов воздушных судов, максимальная дальность полёта которых превышает \"4200\". Для выборки использовать данные из объединения таблиц рейсов воздушных судов и воздушных судов.");

            assertEquals(getQueryTask1()[i], task.getQuestion());
            assertEquals(1, task.getRating());
        }
    }

    @Test
    public void testTask2AllVariants() {
        for (int i = 0; i < getAnswerTask2().length; i++) {
            DBTaskGeneratorService taskGenerator = new DBTaskGeneratorService();
            Task task = taskGenerator.getTask("1db56a451774ae25e5d2788a78f5863a", 2, 2, Integer.toString(i), 0);
            task.setAnswer(getAnswerTask2()[i]).setComplete(true);
            taskGenerator.checkTasks(task);
            System.out.println(task.getQuestion());

            assertEquals(getQueryTask2()[i], task.getQuestion());
            assertEquals(1, task.getRating());
        }
    }

    @Test
    public void testTask3AllVariants() {
        for (int i = 0; i < getAnswerTask3().length; i++) {
            DBTaskGeneratorService taskGenerator = new DBTaskGeneratorService();
            Task task = taskGenerator.getTask("1db56a451774ae25e5d2788a78f5863a", 2, 3, Integer.toString(i), 0);
            task.setAnswer(getAnswerTask3()[i]).setComplete(true);
            taskGenerator.checkTasks(task);
            System.out.println(task.getQuestion());

            assertEquals(getQueryTask3()[i], task.getQuestion());
            assertEquals(1, task.getRating());
        }
    }

    @Test
    public void testTask4AllVariants() {
        for (int i = 0; i < getAnswerTask4().length; i++) {
            DBTaskGeneratorService taskGenerator = new DBTaskGeneratorService();
            Task task = taskGenerator.getTask("1db56a451774ae25e5d2788a78f5863a", 2, 4, Integer.toString(i), 0);
            task.setAnswer(getAnswerTask4()[i]).setComplete(true);
            taskGenerator.checkTasks(task);
            System.out.println(task.getQuestion());

            assertEquals(getQueryTask4()[i], task.getQuestion());
            assertEquals(1, task.getRating());
        }
    }

    @Test
    public void testTask5AllVariants() {
        for (int i = 0; i < getAnswerTask5().length; i++) {
            DBTaskGeneratorService taskGenerator = new DBTaskGeneratorService();
            Task task = taskGenerator.getTask("1db56a451774ae25e5d2788a78f5863a", 2, 5, Integer.toString(i), 0);
            task.setAnswer(getAnswerTask5()[i]).setComplete(true);
            taskGenerator.checkTasks(task);
            System.out.println(task.getQuestion());

            assertEquals(getQueryTask5()[i], task.getQuestion());
            assertEquals(1, task.getRating());
        }
    }
}
