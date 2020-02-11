package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents a command that used to handle existing task deletion.
 *
 * @author Wang Yuting
 */
public class DeleteCommand extends Command {

    private int index;

    /**
     * Creates a new <code>DeleteCommand</code> with the given index.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes command to delete a task at <code>index</code>.
     *
     * @param tasks   The list of current tasks from storage.
     * @param ui      The current UI.
     * @param storage The system storage, used as database.
     * @throws DukeException If there is no task in current <code>tasks</code>,
     *                       or the <code>index</code> is out side of the valid range.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        int originalListSize = tasks.getSize();

        if (originalListSize == 0) {
            throw new DukeException("emptyList");
        }
        if (this.index > originalListSize || index < 1) {
            throw new DukeException("deleteWrongIndexRange");
        }

        Task chosenTask = tasks.getTasks().get(index - 1);
        tasks.getTasks().remove(index - 1);
        assert tasks.getSize() == originalListSize - 1 : "The size of task list didn't change after deletion,"
                + " please check.";
        storage.save(tasks);

        String output = "Noted. I've removed this task: \n"
                + chosenTask.toString() + "\n"
                + "Now you have " + tasks.getSize() + " tasks in the list.";
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
