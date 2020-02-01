package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

public class ExitCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String output = "     Bye. Hope to see you again soon!";
        System.out.println(output);
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
