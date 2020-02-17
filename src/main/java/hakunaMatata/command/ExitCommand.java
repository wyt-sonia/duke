package hakunaMatata.command;

import hakunaMatata.HakunaMatataException;
import hakunaMatata.Storage;
import hakunaMatata.Ui;
import hakunaMatata.task.TaskList;

/**
 * Represents a command that used to exit the program.
 *
 * @author Wang Yuting
 */
public class ExitCommand extends Command {

    private String title = "-  \uD83C\uDD71\uD83C\uDD88\uD83C\uDD74  -\n";
    /**
     * Executes command to exit the program.
     *
     * @param tasks   The list of current tasks from storage.
     * @param ui      The current UI.
     * @param storage The system storage, used as database.
     * @throws HakunaMatataException If there is any HakunaMatataException.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws HakunaMatataException {
        String output = title + "(ᴑ̶̷˙ᵚ˙ᴑ̴̶̷)◞ Hope to see you again soon!\n"
                + "Press enter to exit.";
        return output;
    }

    /**
     * Returns true if it is an exit command.
     *
     * @return True, as it is the exit command.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
