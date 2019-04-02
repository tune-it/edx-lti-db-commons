package com.tuneit.courses.db;

import com.tuneit.courses.Task;
import com.tuneit.courses.TaskGeneratorService;
import com.tuneit.courses.db.schema.Schema;
import com.tuneit.courses.db.schema.SchemaLoader;
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

        List<LabTask> labTasks = getLabTasks(schema, initialTask);
        Task[] tasks = new Task[labTasks.size()];

        for (int i = 0; i < tasks.length; i++) {
            tasks[i] = getTaskByInitialTaskAndLabTask(initialTask, labTasks.get(i),schema);
        }

        return tasks;
    }

    @Override
    public Task getTask(String studentId, String labId, int taskId, String variant, int complexity) {
        Task initialTask = new Task();
        initialTask.setStudentId(studentId).setLabId(labId).setVariant(variant);

        Schema schema = SchemaLoader.getSchema(initialTask.getYearOfStudy(), initialTask.getStudentId());

        List<LabTask> labTasks = getLabTasks(schema, initialTask);

        if (labTasks.size() <= taskId) {
            throw new IllegalArgumentException(new ArrayIndexOutOfBoundsException("TaskId more that the number of tasks"));
        }

        return getTaskByInitialTaskAndLabTask(initialTask, labTasks.get(taskId), schema);
    }

    @Override
    public Task[] checkTasks(Task... tasks) {
        for (Task task : tasks) {
            Schema schema = SchemaLoader.getSchema(task.getYearOfStudy(), task.getStudentId());

            LabTask labTask = findLabTask(task);

            if (task.isComplete()) {
                try {
                    //TODO check persistent store of question and answers to avoid excessive generation
                    //based on t.getId();
                    LabTaskQA labTaskQA = labTask.generate(schema, task);
                    SelectProcessor tester = new SelectProcessor();
                    SelectResult answer = tester.execute_select(schema, task.getAnswer(), 5, false);
//                    System.out.println("Answer= " + answer + " " + task.getAnswer());
                    SelectResult correct = tester.execute_select(schema, labTaskQA.getCorrectAnswer(), 5, false);
//                    System.out.println("Correct= " + correct + " " + labTaskQA.getCorrectAnswer());
                    //
                    //compute student rating, based on 80+20 priciple
                    //80 if result is correct
                    //0-20 - if systax is similar to generated
                    //OLD style: t.setRating(correct_md5.equalsIgnoreCase(answer_md5) ? 1.0f : 0.0f);
                    //
                    if (answer.getResultCode() != SelectResult.OK) {
                        //incorrect or too long sql query
                        //TODO should we distinguish it?
                        task.setRating(0.001f);
                    } else {
                        float rating = 0.0f;
                        if (correct.getResultCheckSum().equalsIgnoreCase(answer.getResultCheckSum())) {
                            rating = 0.8f;
                            TokenSQLSimilarity sm = new TokenSQLSimilarity(task.getAnswer(), labTaskQA.getCorrectAnswer());
                            rating += 0.2f * sm.calculate();
                            //System.out.println(sm.calculate()+"   "+sm);
                        }
                        task.setRating(rating);
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
                filter(labTask -> labTask.getId().equalsIgnoreCase(task.getTaskId())).findFirst();
        if (!optionalLabTask.isPresent()) {
            throw new IllegalArgumentException("Could not find lab task with lab name="
                    + task.getLabId() + " and task " + task.getTaskId() + " in schema " + schema.getName());
        }
        return optionalLabTask.get();
    }

}
