package duke.command;

import java.util.ArrayList;
import java.util.stream.Collectors;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

public class FindCommand extends Command {
    private String keyword;

    /**
     * Creates a new <code>FindCommand</code> with the given keyword.
     */
    public FindCommand(String keyWord) {
        this.keyword = keyWord;
    }

    /**
     * Executes command to search tasks base on the keyword.
     *
     * @param tasks   The list of current tasks from storage.
     * @param ui      The current UI.
     * @param storage The system storage, used as database.
     * @throws DukeException If there is any DukeException.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String output = "";
        if (tasks.getSize() == 0) {
            output = ui.getErrorMessage(new DukeException("emptyList"));
        } else {
            output = "Here are the matching tasks in your list:\n";
            int counter = 0;
            for (Task t : tasks.getTasks()) {
                if (t.getDescription().contains(this.keyword)) {
                    output += "       " + (counter + 1) + "." + t.toString();
                    if (tasks.getSize() - counter > 1) {
                        output += "\n";
                    }
                    counter++;
                }
            }
        }
        return output;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
