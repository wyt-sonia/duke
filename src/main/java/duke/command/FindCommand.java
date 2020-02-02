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
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String output = "     Here are the matching tasks in your list:\n";
        if (tasks.getSize() == 0) {
            ui.showErrorMessage(new DukeException("emptyList"));
        } else {
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
        ui.displayOutput(output);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
