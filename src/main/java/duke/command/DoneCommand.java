package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

public class DoneCommand extends Command {
    private int index;

    public DoneCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        int listSize = tasks.getSize();
        if (listSize == 0) {
            throw new DukeException("emptyList");
        }
        if (this.index > listSize || index < 1) {
            throw new DukeException("doneWrongIndexRange");
        }
        Task t = tasks.getTasks().get(index - 1);
        if (t.isDone()) {
            throw new DukeException("doneComplectedTask");
        }
        t.markAsDone();
        String output = "     Nice! I've marked this task as done: \n" +
                "       " + t.toString();
        System.out.println(output);
        storage.save(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
