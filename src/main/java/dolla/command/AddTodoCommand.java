package dolla.command;

import dolla.Ui;
import dolla.task.Task;
import dolla.task.TaskList;
import dolla.task.ToDo;

/**
 * AddToDoCommand is a command used to add a todo task into the duke.task.TaskList.
 */
public class AddTodoCommand extends AddCommand {

    public AddTodoCommand(String taskDescription) {
        super(taskDescription);
    }

    /**
     * Adds a todo task into the specified duke.task.TaskList.
     * <p>
     *     If the task description is missing, return without doing anything.
     * </p>
     * @param tasks The duke.task.TaskList where the todo task is to be added.
     */
    @Override
    public void execute(TaskList tasks) {

        if (taskDescription.length() == 0) { // TODO: Exception handling?
            Ui.printMsg(missingDescriptionString);
            return;
        }

        Task newTask = new ToDo(taskDescription);
        tasks.add(newTask);
        Ui.echoAdd(newTask, tasks.size());
    }
}