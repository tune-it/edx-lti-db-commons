package com.tuneit.course.lab2;

import com.tuneit.courses.DBTaskGeneratorService;
import com.tuneit.courses.Task;
import org.junit.jupiter.api.Test;

import static com.tuneit.course.lab2.TaskData.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskVariantTest {
    @Test
    public void testTask1AllVariants() {
        for (int i = 0; i < getAnswerTask1().length; i++) {
            DBTaskGeneratorService taskGenerator = new DBTaskGeneratorService();
            Task task = taskGenerator.getTask("serge@cs.ifmo.ru", "lab02", 0, Integer.toString(i), 0);
            task.setAnswer(getAnswerTask1()[i]).setComplete(true);
            taskGenerator.checkTasks(task);

            assertEquals(task.getQuestion(), getQueryTask1()[i]);
            assertEquals(task.getRating(), 1);
        }
    }

}
