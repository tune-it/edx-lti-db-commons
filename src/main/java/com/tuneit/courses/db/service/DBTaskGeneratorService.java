package com.tuneit.courses.db.service;

import com.tuneit.courses.db.checking.SelectProcessor;
import com.tuneit.courses.db.checking.SelectResult;
import com.tuneit.courses.db.generate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Service for creating and checking tasks
 */
@Component
public class DBTaskGeneratorService implements TaskGeneratorService {

    @Autowired
    private SchemaLoader schemaLoader;

    @Autowired
    private SelectProcessor tester;

    @Autowired
    private Labs labs;

    public DBTaskGeneratorService() {
        System.out.println("hello");
    }

    @PostConstruct
    private void test(){
        System.out.println(getTask("1db56a451774ae25e5d2788a78f5863a", 1, 1, "0", 0));
    }

    @Override
    public Task[] getTasks(String studentId, int labId, String variant, int complexity) {
        //initial task to get schema01
        Task initialTask = new Task();
        initialTask.setStudentId(studentId);
        initialTask.setLabId(labId);
        initialTask.setVariant(variant);

        Lab lab = labs.getLab(labId);

        LabTaskQA[] labTaskQAS = lab.generateAll(initialTask, schemaLoader.getSchema(labId));
        Task[] tasks = new Task[labTaskQAS.length];
        for (int i = 0; i < tasks.length; i++) {
            tasks[i] = getTaskByInitialTaskAndLabTask(initialTask, labTaskQAS[i]);
        }

        return tasks;
    }

    @Override
    public Task getTask(String studentId, int labId, int taskId, String variant, int complexity) {
        Task task = new Task();
        task.setStudentId(studentId);
        task.setTaskId(taskId);
        task.setVariant(variant);

        Lab lab = labs.getLab(labId);
        LabTaskQA labTaskQA = lab.generate(task, schemaLoader.getSchema(labId));
        task.setQuestion(labTaskQA.getQuestion());

        return task;
    }

    @Override
    public Task[] checkTasks(Task... tasks) {
        for (Task task : tasks) {
            Lab lab = labs.getLab(task.getLabId());

            if (task.isComplete()) {
                try {
                    LabTaskQA labTaskQA = lab.generate(task, schemaLoader.getSchema(task.getLabId()));
                    SelectResult answer = tester.executeQuery(task.getAnswer(), labTaskQA.getColumnToSort(), 5, false);
                    SelectResult correct = tester.executeQuery(labTaskQA.getCorrectAnswer(), labTaskQA.getColumnToSort(), 5, false);

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
        task.setStudentId(initialTask.getStudentId());
        task.setLabId(initialTask.getLabId());
        task.setVariant(initialTask.getVariant());
        task.setYearOfStudy(initialTask.getYearOfStudy());
        task.setTaskId(initialTask.getTaskId());

        task.setQuestion(labTaskQA.getQuestion());

        return task;
    }
}
