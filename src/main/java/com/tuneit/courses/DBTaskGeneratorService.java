package com.tuneit.courses;

import com.tuneit.courses.lab1.db.*;
import com.tuneit.courses.lab1.db.schema.Schema;
import com.tuneit.courses.lab1.db.schema.SchemaLoader;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class DBTaskGeneratorService implements TaskGeneratorService {

    @Override
    public Task[] getTasks(String studentId, String labId, String variant, int complexity) {
        //initial task to get schema
        Task initialTask = new Task();
        initialTask.setStudentId(studentId).setLabId(labId).setVariant(variant);

        Schema schema = SchemaLoader.getSchema(initialTask.getYearOfStudy(), initialTask.getStudentId());

        List<LabTask> lab01Tasks = getLabTasks(schema, initialTask);
        Task[] tasks = new Task[lab01Tasks.size()];

        for (int i = 0; i < tasks.length; i++) {
            tasks[i] = getTaskByInitialTaskAndLabTask(initialTask, lab01Tasks.get(i),schema);
        }

        return tasks;
    }

    @Override
    public Task getTask(String studentId, String labId, int taskId, String variant, int complexity) {
        Task initialTask = new Task();
        initialTask.setStudentId(studentId).setLabId(labId).setVariant(variant);

        Schema schema = SchemaLoader.getSchema(initialTask.getYearOfStudy(), initialTask.getStudentId());

        List<LabTask> lab01Tasks = getLabTasks(schema, initialTask);

        if (lab01Tasks.size() <= taskId) {
            throw new IllegalArgumentException(new ArrayIndexOutOfBoundsException("TaskId more that the number of tasks"));
        }

        return getTaskByInitialTaskAndLabTask(initialTask, lab01Tasks.get(taskId), schema);
    }

    @Override
    public Task[] checkTasks(Task... tasks) {
        for (Task task : tasks) {
            Schema schema = SchemaLoader.getSchema(task.getYearOfStudy(), task.getStudentId());

            LabTask lab01Task = findLabTask(task);

            if (task.isComplete()) {
                try {
                    LabTaskQA labTaskQA = lab01Task.generate(schema, task);
                    SelectProcessor tester = new SelectProcessor();

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

    private List<LabTask> getLabTasks(Schema schema, Task initialTask) {
        Optional<Lab> optionalLab = schema.getLabs().stream().
                filter(lab -> lab.getId().equalsIgnoreCase(initialTask.getLabId().trim())).findFirst();

        if (!optionalLab.isPresent()) {
            throw new IllegalArgumentException("Could not find lab with name="
                    + initialTask + " in schema " + schema.getName());
        }
        Lab lab = optionalLab.get();

        return lab.getLabTask();
    }

    private Task getTaskByInitialTaskAndLabTask(Task initialTask, LabTask labTask, Schema schema) {
        Task task = new Task();
        task.setStudentId(initialTask.getStudentId()).setLabId(initialTask.getLabId());
        task.setVariant(initialTask.getVariant()).setYearOfStudy(initialTask.getYearOfStudy());
        task.setTaskId(labTask.getId());

        LabTaskQA labTaskQA = labTask.generate(schema, task);
        task.setQuestion(labTaskQA.getQuestion());

        return task;
    }

    public LabTask findLabTask(Task task) {
        Schema schema = SchemaLoader.getSchema(task.getYearOfStudy(), task.getStudentId());

        Optional<Lab> optionalLab = schema.getLabs().stream().
                filter(lab -> lab.getId().equalsIgnoreCase(task.getLabId().trim())).findFirst();
        if (!optionalLab.isPresent()) {
            throw new IllegalArgumentException("Could not find lab with name="
                    + task.getLabId() + " in schema " + schema.getName());
        }
        Lab lab = optionalLab.get();

        Optional<LabTask> optionalLabTask = lab.getLabTask().stream().
                filter(lab01Task -> lab01Task.getId().equalsIgnoreCase(task.getTaskId())).findFirst();
        if (!optionalLabTask.isPresent()) {
            throw new IllegalArgumentException("Could not find lab task with lab name="
                    + task.getLabId() + " and task " + task.getTaskId() + " in schema " + schema.getName());
        }
        return optionalLabTask.get();
    }

}
