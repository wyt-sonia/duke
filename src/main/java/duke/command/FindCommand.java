package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyWord) {
        this.keyword = keyWord;
    }

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
