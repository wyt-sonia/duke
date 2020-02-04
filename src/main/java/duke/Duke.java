package duke;

import java.io.FileNotFoundException;

import duke.command.Command;
import duke.task.TaskList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;

/**
 * Represents a duke program, which is a task list keeper.
 * It allows users to add, delete and update their tasks' status.
 *
 * @author Wang Yuting
 */
public class Duke  {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private boolean isExit;

    /**
     * Create a new duke with given file path.
     */
    public Duke(String filePath) {
        ui = new Ui();
        isExit = false;
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            // change needed
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

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return "Duke heard: " + input;
    }


}
