package com.tuneit.courses.db.service;

import com.tuneit.courses.db.generate.Task;

/**
 * Generator of tasks
 */
public interface TaskGeneratorService {

    /**
     * @param studentId identifier of student
     * @param labId identifier of lab
     * @param variant random seed
     * @param complexity count of questions
     * @return array of tasks
     */
    Task[] getTasks(String studentId, int labId, String variant, int complexity);

    /**
     * @param studentId identifier of student
     * @param labId identifier of lab
     * @param taskId identifier of task
     * @param variant argument for random
     * @param complexity count of questions
     * @return array of tasks
     */
    Task getTask(String studentId, int labId, int taskId, String variant, int complexity);

    /**
     * @param tasks list of tasks for validation
     * @return array of validated tasks
     */
    Task[] checkTasks(Task... tasks);

}
