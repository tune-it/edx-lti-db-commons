package com.tuneit.courses.lab1;

/**
 * Generator of tasks
 */
public interface TaskGeneratorService {

    /**
     * @param studentId  identifier of student
     * @param labId      identifier of lab
     * @param complexity count of questions
     * @return array of tasks
     */
    Task[] getTasks(String studentId, String labId, String variant, int complexity);

    Task getTask(String studentId, String labId, int taskId, String variant, int complexity);

    /**
     * @param tasks list of tasks for validation
     * @return array of validated tasks
     */
    Task[] checkTasks(Task... tasks);

}
