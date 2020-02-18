package hakunaMatata.command;

import hakunaMatata.HakunaMatataException;
import hakunaMatata.Storage;
import hakunaMatata.Ui;
import hakunaMatata.task.Task;
import hakunaMatata.task.TaskList;

/**
 * Represents a command that used to handle new task insertion.
 *
 * @author Wang Yuting
 */
public class AddCommand extends Command {

    private Task task;
    private String title = "\uD83C\uDD83\uD83C\uDD70\uD83C\uDD82\uD83C\uDD7A "
            + "\uD83C\uDD78\uD83C\uDD7D\uD83C\uDD82\uD83C\uDD74\uD83C\uDD81\uD83C\uDD83\uD83C\uDD74\uD83C\uDD73:\n";

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
     * @throws HakunaMatataException If there is any HakunaMatataException.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws HakunaMatataException {
        tasks.getTasks().add(task);
        int originalListSize = tasks.getSize();
        assert tasks.getSize() == originalListSize + 1 : "The size of task list didn't change after insertion,"
                + " please check.";
        storage.save(tasks);

        String output = title
                + task.toString() + "\n"
                + "  Now you have " + tasks.getSize() + " tasks in the list.";
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
