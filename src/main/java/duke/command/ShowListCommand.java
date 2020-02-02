package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents a command that used to display all current tasks in system storage.
 *
 * @author Wang Yuting
 */
public class ShowListCommand extends Command {

    /**
     * Executes command to display all current tasks in system storage.
     *
     * @param tasks The list of current tasks from storage.
     * @param ui The current UI.
     * @param storage The system storage, used as database.
     * @throws DukeException If there is no task in current <code>tasks<code/>.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String output = "";
        if (tasks.getSize() == 0) {
            ui.showErrorMessage(new DukeException("emptyList"));
        } else {
            for (Task t : tasks.getTasks()) {
                int counter = tasks.getTasks().indexOf(t);
                output += "       " +  (counter + 1) + "." + t.toString();
                if (tasks.getSize() - counter > 1) output += "\n";
            }
        }
        ui.displayOutput(output);
    }

    /**
     * Returns true if it is an exit command.
     *
     * @return True, as it is the exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
