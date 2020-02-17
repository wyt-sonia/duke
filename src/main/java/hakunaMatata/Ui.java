package hakunaMatata;

import java.util.Scanner;

/**
 * Represents the program ui which handles the interaction with user.
 *
 * @author Wang Yuting
 */
public class Ui {
    private Scanner scanner = new Scanner(System.in);
    private String greeting = "\uD83C\uDD77\uD83C\uDD74\uD83C\uDD7B\uD83C\uDD7B\uD83C\uDD7E! "
            + "\uD83C\uDD78'\uD83C\uDD7C \uD83C\uDD77\uD83C\uDD70\uD83C\uDD7A\uD83C\uDD84\uD83C\uDD7D\uD83C\uDD70\uD83C\uDD7C\uD83C\uDD70\uD83C\uDD83\uD83C\uDD70\uD83C\uDD83\uD83C\uDD70 ʕ•ω•ʔ\n\n"
            + "I can help you to keep track of you tasks ~!\n\n"
            + "= Guide = \n\n"
            + "    ✎ Create Tasks(Todo, Deadline and Event):\n"
            + "     \uD83C\uDDF9\uD83C\uDDF4\uD83C\uDDE9\uD83C\uDDF4\n"
            + "        Use \"todo + description\" to create\n"
            + "        E.g. todo taskInfo.\n"
            + "     \uD83C\uDDE9\uD83C\uDDEA\uD83C\uDDE6\uD83C\uDDE9\uD83C\uDDF1\uD83C\uDDEE\uD83C\uDDF3\uD83C\uDDEA\n"
            + "        Use \"deadline + description + /by + date\" to create\n"
            + "        E.g. deadline taskInfo /by 12:00 01/01/2020.\n"
            + "     \uD83C\uDDEA\uD83C\uDDFB\uD83C\uDDEA\uD83C\uDDF3\uD83C\uDDF9\n"
            + "        Use \"event + description + /at + start_date-end_date\" to create\n"
            + "        E.g. event taskInfo /at 12:00 01/01/2020-14:00 01/01/2020\n\n"
            + "    ✎ Other functions: \n"
            + "    ✧ Use \"list\" to check the recorded tasks,\n"
            + "    ✧ Use\"sort + sort_term(description or deadline)\" to sort tasks,\n"
            + "    ✧ Use \"done + task_index\" to mark the task as done. \n"
            + "    ✧ Use \"bye\" to exit.\n\n"
            + "ʕ•ω•ʔ   \uD83C\uDD86\uD83C\uDD77\uD83C\uDD70\uD83C\uDD83 \uD83C\uDD72\uD83C\uDD70\uD83C\uDD7D "
            + "\uD83C\uDD78 \uD83C\uDD73\uD83C\uDD7E \uD83C\uDD75\uD83C\uDD7E\uD83C\uDD81 "
            + "\uD83C\uDD88\uD83C\uDD7E\uD83C\uDD84?";

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
    public String getErrorMessage(HakunaMatataException ex) {
        return ex.errorMessage;
    }

    /**
     * Reads user input.
     *
     * @return The input read from keyboard.
     */
    public String readCommand() {
        return this.scanner.nextLine();
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
