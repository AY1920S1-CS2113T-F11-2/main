package duke.command;

import duke.Ui;
import duke.task.DoAfter;
import duke.task.Task;
import duke.task.TaskList;

public class AddDoAfterTaskCommand extends AddCommand{
    private int position;
    private String event;

    public AddDoAfterTaskCommand (String taskDescription) {
        super(taskDescription);
    }

    /**
     * This method will split the user input into task description and the event
     * @param tasks duke.task.TaskList containing all the tasks stored.
     */
    public void execute(TaskList tasks) {
        try {
            position = taskDescription.indexOf("/");
            event = taskDescription.substring(position + 10);
                Task newTask = new DoAfter(taskDescription.substring(0, position - 1), event);
                tasks.add(newTask);
                Ui.echoAdd(newTask, tasks.size());
        } catch (Exception e) {
                Ui.printInvaidDoAfterInput();
        }
    }
}
