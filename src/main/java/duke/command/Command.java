package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * Command is the abstract base class for all executable commands,
 * which handle the execution of commands and program termination.
 *
 * @author Wang Yuting
 */
public abstract class Command {

    /**
     * Executes command.
     *
     * @param tasks   The list of current tasks from storage.
     * @param ui      The current UI.
     * @param storage The system storage, used as database.
     * @throws DukeException If there is any DukeException.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Returns true if it is an exit command.
     *
     * @return Returns true if it is an exit command.
     */
    public abstract boolean isExit();

}