package com.tuneit.courses;

import com.tuneit.courses.db.Lab;
import com.tuneit.courses.db.LabTaskQA;
import com.tuneit.courses.db.schema.Schema;
import com.tuneit.courses.db.schema.SchemaLoader;
import org.springframework.stereotype.Component;

@Component
public class DBTaskGeneratorService implements TaskGeneratorService {

    @Override
    public Task[] getTasks(String studentId, int labId, String variant, int complexity) {
        //initial task to get schema01
        Task initialTask = new Task();
        initialTask.setStudentId(studentId).setLabId(labId).setVariant(variant);

        Schema schema = SchemaLoader.getSchema(labId);

        Lab lab = schema.getLab();

        LabTaskQA[] labTaskQAS = lab.generateAll(initialTask);
        Task[] tasks = new Task[labTaskQAS.length];
        for (int i = 0; i < tasks.length; i++) {
            tasks[i] = getTaskByInitialTaskAndLabTask(initialTask, labTaskQAS[i]);
        }

        return tasks;
    }

    @Override
    public Task getTask(String studentId, int labId, int taskId, String variant, int complexity) {
        Schema schema = SchemaLoader.getSchema(labId);

        Lab lab = schema.getLab();

        Task task = new Task()
                .setStudentId(studentId)
                .setTaskId(taskId)
                .setVariant(variant);

        LabTaskQA labTaskQA = lab.generate(task);
        task.setQuestion(labTaskQA.getQuestion());

        return task;
    }

    @Override
    public Task[] checkTasks(Task... tasks) {
        SelectProcessor tester = new SelectProcessor();

        for (Task task : tasks) {
            Schema schema = SchemaLoader.getSchema(task.getLabId());

            Lab lab = schema.getLab();

            if (task.isComplete()) {
                try {
                    LabTaskQA labTaskQA = lab.generate(task);

                    SelectResult answer = tester.executeQuery(schema, task.getAnswer(), 5, false);
                    SelectResult correct = tester.executeQuery(schema, labTaskQA.getCorrectAnswer(), 5, false);

                    if (answer.getResultCode() != SelectResult.OK) {
                        task.setRating(0);
                    } else {
                        if (correct.getResultCheckSum().equalsIgnoreCase(answer.getResultCheckSum())) {
                            task.setRating(1);
                        } else {
                            task.setRating(0.001f);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    task.setRating(0.0f);
                }
            }

        }

        return tasks;
    }

    private Task getTaskByInitialTaskAndLabTask(Task initialTask, LabTaskQA labTaskQA) {
        Task task = new Task();
        task.setStudentId(initialTask.getStudentId()).setLabId(initialTask.getLabId());
        task.setVariant(initialTask.getVariant()).setYearOfStudy(initialTask.getYearOfStudy());
        task.setTaskId(initialTask.getTaskId());

        task.setQuestion(labTaskQA.getQuestion());

        return task;
    }
}
