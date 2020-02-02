package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        int listSize = tasks.getSize();
        if (listSize == 0) {
            throw new DukeException("emptyList");
        }
        if (this.index > listSize || index < 1) {
            throw new DukeException("deleteWrongIndexRange");
        }
        Task t = tasks.getTasks().get(index - 1);
        String output = "     Noted. I've removed this task: \n" +
                "       " + t.toString() + "\n" +
                "     Now you have " + tasks.getSize() + " tasks in the list.";
        storage.save(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
