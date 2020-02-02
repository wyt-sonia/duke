package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents a command that used to mark a task at <code>index<code/> as done.
 *
 * @author Wang Yuting
 */
public class DoneCommand extends Command {

    private int index;

    /**
     * Creates a new <code>DoneCommand<code/> with the given index.
     */
    public DoneCommand(int index) {
        this.index = index;
    }

    /**
     * Executes command to mark a task at <code>index<code/> as done.
     *
     * @param tasks The list of current tasks from storage.
     * @param ui The current UI.
     * @param storage The system storage, used as database.
     * @throws DukeException If there is no task in current <code>tasks<code/>,
     *                       the <code>index<code/> is out side of the valid range
     *                       or the selected task is done already.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        int listSize = tasks.getSize();
        if (listSize == 0) throw new DukeException("emptyList");
        if (this.index > listSize || index < 1)
            throw new DukeException("doneWrongIndexRange");
        Task t = tasks.getTasks().get(index - 1);
        if (t.isDone()) throw new DukeException("doneComplectedTask");
        t.markAsDone();
        String output = "     Nice! I've marked this task as done: \n" +
                            "       " + t.toString();
        System.out.println(output);
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
