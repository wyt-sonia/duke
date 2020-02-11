package duke;

import java.util.Scanner;

/**
 * Represents the program ui which handles the interaction with user.
 *
 * @author Wang Yuting
 */
public class Ui {
    private Scanner sacnner = new Scanner(System.in);
    private String greeting = "Hello! I'm duke.Duke, a task list chat box\n"
            + "You can key in your tasks and I'll keep track of them for you.\n\n"
            + "Guide: \n"
            + "There are three types of tasks I'm able to keep track of.\n\n"
            + "  Todo: use \"todo + description\" to create (e.g. todo taskInfo).\n\n"
            + "  Deadline: use \"deadline + description + /by + date\" to create \n"
            + "  (e.g. deadline taskInfo /by 12:00 01/01/2020).\n\n"
            + "  Event: use \"event + description + /at + start_date-end_date\" to create\n "
            + "  (e.g. event taskInfo /at 12:00 01/01/2020-14:00 01/01/2020)\n\n"
            + "  You can also use \"list\" to check the recorded tasks,\n"
            + "  use \"sort + sort_term(description or deadline)\" to check the recorded tasks,\n"
            + "  or use \"done + task_index\" to mark the task as done. \n\n"
            + "  To terminate me, please use \"bye\".\n"
            + "  What can I do for you?";

    /**
     * Displays the welcome message.
     */
    public String getWelcome() {
        return greeting;
    }

    /**
     * Displays an error message.
     *
     * @param ex The exception encountered.
     */
    public String getErrorMessage(DukeException ex) {
        return ex.errorMessage;
    }

    /**
     * Reads user input.
     *
     * @return The input read from keyboard.
     */
    public String readCommand() {
        return this.sacnner.nextLine();
    }

    /**
     * Displays the outpur.
     *
     * @param output The output which will be displayed.
     */
    public void displayOutput(String output) {
        System.out.println(output);
    }
}
