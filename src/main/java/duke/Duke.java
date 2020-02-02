package duke;

import duke.DukeException;
import duke.command.Command;
import duke.task.TaskList;


import java.io.FileNotFoundException;

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

    /**
     * Create a new duke with given file path.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showErrorMessage(new DukeException("loadingError"));
            tasks = new TaskList();
        }
    }

    /**
     * Executes the duke program.
     */
    public void run() {
        this.ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showErrorMessage(e);
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * The entry point of the program.
     *
     * @param args The input arguments.
     */
    public static void main(String[] args) throws FileNotFoundException {
        new Duke("data/tasks.txt").run();
    }
}
