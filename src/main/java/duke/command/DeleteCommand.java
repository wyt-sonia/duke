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
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        int listSize = tasks.getSize();
        if (listSize == 0) {
            throw new DukeException("emptyList");
        }
        if (this.index > listSize || index < 1) {
            throw new DukeException("deleteWrongIndexRange");
        }
        Task t = tasks.getTasks().get(index - 1);
        String output = "     Noted. I've removed this task: \n"
                + "       " + t.toString() + "\n"
                + "     Now you have " + tasks.getSize() + " tasks in the list.";
        storage.save(tasks);
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
