package duke;

import duke.command.Command;
import duke.task.TaskList;

/**
 * Represents a duke program, which is a task list keeper.
 * It allows users to add, delete and update their tasks' status.
 *
 * @author Wang Yuting
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private boolean isExit;

    /**
     * Create a new duke with given file path.
     */
    public Duke(String filePath) {
        isExit = false;
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.getErrorMessage(new DukeException("loadingError"));
            tasks = new TaskList();
        }
    }

    public Ui getUi() {
        return ui;
    }

    /**
     * Executes the duke program.
     */
    public String run(String input) {
        String response = "";
        if (!isExit) {
            try {
                String fullCommand = input;
                Command c = Parser.parse(fullCommand);
                response = c.execute(tasks, ui, storage);
                this.isExit = c.isExit();
            } catch (DukeException e) {
                response = ui.getErrorMessage(e);
            }
        }
        return response;
    }


}
