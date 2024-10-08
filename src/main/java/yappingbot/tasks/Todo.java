package yappingbot.tasks;

import yappingbot.tasks.tasklist.TaskTypes;

/**
 * To-do variant of the Task that can be created.
 * Includes task name, and if task is marked done.
 */
public class Todo extends Task {

    /**
     * Creates a To-Do task.
     *
     * @param taskName String name of this task.
     * @param isTaskDone Boolean of whether the task is marked or unmarked as done.
     */
    public Todo(String taskName, boolean isTaskDone) {
        super(taskName, isTaskDone);
        super.setTaskType(TaskTypes.TODO);
    }

    public Todo() {
        super();
        setTaskType(TaskTypes.TODO);
    }

    @Override
    public String getTaskTypeSymbol() {
        return "T";
    }

    @Override
    public String toString() {
        return String.format("%s", super.getTaskName());
    }
}
