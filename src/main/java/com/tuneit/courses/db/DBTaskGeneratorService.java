package com.tuneit.courses.db;

import com.tuneit.courses.Task;
import com.tuneit.courses.TaskGeneratorService;
import com.tuneit.courses.db.schema.Schema;
import com.tuneit.courses.db.schema.SchemaLoader;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Profile("prod")
public class DBTaskGeneratorService implements TaskGeneratorService {

    @Override
    public Task[] getTasks(String studentId, String labId, String variant, int complexity) {
        //initial task to get schema
        Task it = new Task();
        it.setStudentId(studentId).setLabId(labId).setVariant(variant);
        Schema s = SchemaLoader.getSchema(it.getYearOfStudy(),it.getStudentId());
        Lab clab = null;
        for (Lab l : s.getLabs()) {
            if (l.getId().equalsIgnoreCase(labId.trim())) {
                clab = l;
                break;
            }
        }
        if (clab == null) {
            throw new IllegalArgumentException("Could not find lab with name="
                      +labId+" in schema "+s.getName());
        }
        List<LabTask> labtasks = clab.getLabTask();
        Task[] tasks = new Task[labtasks.size()];
        int i=0;
        for(LabTask lt : labtasks) {
            Task t = new Task();
            t.setStudentId(it.getStudentId()).setLabId(it.getLabId());
            t.setVariant(it.getVariant()).setYearOfStudy(it.getYearOfStudy());
            t.setTaskId(lt.getId());
            //TODO check persistent store of question and answers to avoid excessive generation
            //based on t.getId();
            LabTaskQA ltqa = lt.generate(s, t);
            t.setQuestion(ltqa.getQuestion());
            tasks[i++] = t;
        }
        return tasks;
    }

    @Override
    public Task[] checkTasks(Task... tasks) {

        for(Task t : tasks) {
            Schema s = SchemaLoader.getSchema(t.getYearOfStudy(),t.getStudentId());
            Lab clab = null;
            for (Lab l : s.getLabs()) {
                if (l.getId().equalsIgnoreCase(t.getLabId().trim())) {
                    clab = l;
                    break;
                }
            }
            if (clab == null) {
                throw new IllegalArgumentException("Could not find lab with name="
                          +t.getLabId()+" in schema "+s.getName());
            }
            List<LabTask> labtasks = clab.getLabTask();
            LabTask ctask = null;
            for (LabTask lt : labtasks) {
                if (lt.getId().equalsIgnoreCase(t.getTaskId())) {
                    ctask = lt;
                    break;
                }
            }
            if (ctask == null) {
                throw new IllegalArgumentException("Could not find lab task with lab name="
                          +t.getLabId()+" and task "+t.getTaskId()+" in schema "+s.getName());
            }
            if(t.isComplete()) {
                try {
                    //TODO check persistent store of question and answers to avoid excessive generation
                    //based on t.getId();
                    LabTaskQA ltqa = ctask.generate(s, t);
                    SelectProcessor tester = new SelectProcessor();
                    String answer_md5 = tester.execute_select(s, t.getAnswer(), 5, null);
                    //System.out.println("Answer MD5="+answer_md5+" "+t.getAnswer());
                    String correct_md5 = tester.execute_select(s, ltqa.getCorrectAnswer(), 5, null);
                    //System.out.println("Correct MD5="+correct_md5+" "+ltqa.getCorrectAnswer());
                    //
                    //compute student rating, based on 80+20 priciple
                    //80 if result is correct
                    //0-20 - if systax is similar to generated
                    //OLD style: t.setRating(correct_md5.equalsIgnoreCase(answer_md5) ? 1.0f : 0.0f);
                    //
                    if (answer_md5.startsWith("SQLState")) {
                        //incorrect on too long sql query
                        //TODO should we distinguish it?
                        t.setRating(0.001f);
                    } else {
                        float rating = 0.0f;
                        if (correct_md5.equalsIgnoreCase(answer_md5)) {
                            rating = 0.8f;
                            TokenSQLSimilarity sm = new TokenSQLSimilarity(t.getAnswer(), ltqa.getCorrectAnswer());
                            rating += 0.2f*sm.calculate();
                            //System.out.println(sm.calculate()+"   "+sm);
                        }
                        t.setRating(rating);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    t.setRating(0.0f);
                }
            }

        }

        return tasks;
    }

}
