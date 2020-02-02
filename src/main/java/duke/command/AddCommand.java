package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

public class AddCommand extends Command {

    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.getTasks().add(task);
        String output = "     Got it. I've added this task: \n"
                + "       " + task.toString() + "\n"
                + "     Now you have " + tasks.getSize() + " tasks in the list.";
        System.out.println(output);
        storage.save(tasks);
    }

    /**
     * Returns true if it is an exit command.
     *
     * @return False, as it is not an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
