package duke.command;

import java.util.ArrayList;
import java.util.stream.Collectors;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.TaskType;

import java.time.LocalDateTime;

public class SortCommand extends Command {
    private String sortTerm;

    /**
     * Creates a new <code>SortCommand</code> with the given sortTerm.
     */
    public SortCommand(String sortTerm) {
        assert sortTerm.equals("description") || sortTerm.equals("deadline") : "Wrong sort term format "
                + "didn't caught at Parser.";
        this.sortTerm = sortTerm;
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
            output = getSortedTasksString(tasks);
        }
        return output;
    }

    /** Gets the output string of sorted result.*/
    private String getSortedTasksString(TaskList tasks){
        String output = "";
        ArrayList<Task> sortedTasks = null;
        if(this.sortTerm.equals("deadline")) {
            ArrayList<Task> sortedTasksWithoutTodo = tasks.getTasks().stream()
                    .filter(t -> !t.getType().equals(TaskType.TODO))
                    .sorted(Task::compareTo)
                    .collect(Collectors.toCollection(ArrayList::new));
            ArrayList<Task> sortedTasksTodo = tasks.getTasks().stream()
                    .filter(t -> t.getType().equals(TaskType.TODO))
                    .sorted((Task t1, Task t2) -> t1.getDescription().compareTo(t2.getDescription()))
                    .collect(Collectors.toCollection(ArrayList::new));

            sortedTasksWithoutTodo.addAll(sortedTasksTodo);
            sortedTasks = sortedTasksWithoutTodo;
        }
        if(this.sortTerm.equals("description")) {
            sortedTasks = tasks.getTasks().stream()
                    .sorted((Task t1, Task t2) -> t1.getDescription().compareTo(t2.getDescription()))
                    .collect(Collectors.toCollection(ArrayList::new));
        }
        output += "Here are the task list sorted based on: " + this.sortTerm + "\n";
        output += sortedTasks.stream().map(t-> (tasks.getTasks().indexOf(t) + 1) + "." + t.toString())
                .collect(Collectors.joining("\n"));
        return output;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

