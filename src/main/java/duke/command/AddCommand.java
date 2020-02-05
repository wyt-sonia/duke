package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents a command that used to handle new task insertion.
 *
 * @author Wang Yuting
 */
public class AddCommand extends Command {

    private Task task;

    /**
     * Creates a new <code>AddCommand</code> with the given <code>task</code>.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes command to insert new task into system storage.
     *
     * @param tasks   The list of current tasks from storage.
     * @param ui      The current UI.
     * @param storage The system storage, used as database.
     * @throws DukeException If there is any DukeException.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.getTasks().add(task);
        String output = "Got it. I've added this task: \n"
                + task.toString() + "\n"
                + "  Now you have " + tasks.getSize() + " tasks in the list.";
        storage.save(tasks);
        return output;
    }

    /**
     * Returns true if it is an exit command.
     *
     * @return False, as it is not an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
