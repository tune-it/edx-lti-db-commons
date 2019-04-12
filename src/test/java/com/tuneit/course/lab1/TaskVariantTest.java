package com.tuneit.course.lab1;

import com.tuneit.courses.DBTaskGeneratorService;
import com.tuneit.courses.Task;
import org.junit.jupiter.api.Test;

import static com.tuneit.course.lab1.TaskData.getAnswerTask1;
import static com.tuneit.course.lab1.TaskData.getQueryTask1;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskVariantTest {
    @Test
    public void testTask1AllVariants() {
        for (int i = 0; i < getAnswerTask1().length; i++) {
            DBTaskGeneratorService taskGenerator = new DBTaskGeneratorService();
            Task task = taskGenerator.getTask("serge@cs.ifmo.ru", 1, 1, Integer.toString(i), 0);
            task.setAnswer(getAnswerTask1()[i]).setComplete(true);
            taskGenerator.checkTasks(task);

            assertEquals(task.getQuestion(), getQueryTask1()[i]);
            assertEquals(task.getRating(), 1);
        }
    }
}
