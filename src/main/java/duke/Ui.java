package duke;

import java.util.Scanner;

public class Ui {
    private Scanner sacnner = new Scanner(System.in);
    private String line = "     ___________________________________________________________________\n";
    private String greeting = "     Hello! I'm duke.Duke, a task list chat box\n"
            + "     You can key in your tasks and I'll keep track of them for you.\n\n"
            + "     ============================== Guide ==============================\n"
            + "     There are three types of tasks I'm able to keep track of.\n\n"
            + "         Todo: use \"todo + description\" to create (e.g. todo taskInfo).\n\n"
            + "         Deadline: use \"deadline + description + /by + date\" to create \n"
            + "                  (e.g. deadline taskInfo /by 12:00 01/01/2020).\n\n"
            + "         Event: use \"event + description + /at + date\" to create\n "
            + "                  (e.g. event taskInfo /at Mon 2-4pm).\n\n"
            + "     You can also use \"list\" to check the recorded tasks.\n"
            + "     Or use \"done + task index\" to mark the task as done. \n\n"
            + "     To terminate me, please use \"bye\".\n"
            + "     What can I do for you?";

    public void showWelcome() {
        this.showLine();
        System.out.println(greeting);
        this.showLine();
    }

    public void showLine() {
        System.out.println(this.line);
    }

    public void showErrorMessage(DukeException ex) {
        System.out.println(ex.errorMessage);
    }

    public String readCommand() {
        return this.sacnner.nextLine();
    }

    public void displayOutput(String output) {
        System.out.println(output);
    }
}
