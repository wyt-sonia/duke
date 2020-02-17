package hakunaMatata.command;

import hakunaMatata.HakunaMatataException;
import hakunaMatata.Storage;
import hakunaMatata.Ui;
import hakunaMatata.task.TaskList;

/**
 * Represents a command that used to handle existing task deletion.
 *
 * @author Wang Yuting
 */
public class DeleteCommand extends Command {

    private int index;
    private String title = "\uD83C\uDD83\uD83C\uDD70\uD83C\uDD82\uD83C\uDD7A "
            + "\uD83C\uDD73\uD83C\uDD74\uD83C\uDD7B\uD83C\uDD74\uD83C\uDD83\uD83C\uDD74\uD83C\uDD73\n";
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
     * @throws HakunaMatataException If there is no task in current <code>tasks</code>,
     *                       or the <code>index</code> is out side of the valid range.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws HakunaMatataException {
        int originalListSize = tasks.getSize();

        if (originalListSize == 0) {
            throw new HakunaMatataException("emptyList");
        }
        if (this.index > originalListSize || index < 1) {
            throw new HakunaMatataException("deleteWrongIndexRange");
        }

        tasks.getTasks().remove(index - 1);
        assert tasks.getSize() == originalListSize - 1 : "The size of task list didn't change after deletion,"
                + " please check.";
        storage.save(tasks);

        String output = title
                + tasks.getTasks().get(index - 1).toString() + "\n"
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
