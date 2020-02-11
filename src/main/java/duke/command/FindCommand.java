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
            output = getChosenTasksString(tasks);
        }
        return output;
    }

    /**
     * Gets the output string of search result.
     */
    private String getChosenTasksString(TaskList tasks) {
        String output = "";
        ArrayList<Task> searchResult =
                tasks.getTasks().stream()
                        .filter(t -> t.getDescription().contains(this.keyword))
                        .collect(Collectors
                                .toCollection(ArrayList::new));
        if (searchResult.size() < 1) {
            output += "Here is no matching task in your list, please change a keyword.";
        } else {
            output += "Here are the matching tasks in your list:\n";
            output += searchResult.stream().map(t -> (tasks.getTasks().indexOf(t) + 1) + "." + t.toString())
                    .collect(Collectors.joining("\n"));
        }
        return output;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
