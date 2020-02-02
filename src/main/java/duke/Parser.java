package duke;

import duke.command.Command;
import duke.command.ExitCommand;
import duke.command.ShowListCommand;
import duke.command.DoneCommand;
import duke.command.AddCommand;
import duke.command.DeleteCommand;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * Represents a parser which handle the parsing of user input.
 *
 * @author Wang Yuting
 */
public class Parser {

    /**
     * Creates a <code>Parser</code>.
     */
    public Parser() {

    }

    /**
     * Parse a user input to get valid <code>Command</code>.
     *
     * @param fullCommand The raw user input read by <code>UI</code>.
     * @return A valid <code>Command</code>.
     * @throws DukeException If the input is not valid.
     */
    public static Command parse(String fullCommand) throws DukeException {
        Command command = null;
        if (fullCommand.isBlank()) {
            throw new DukeException("randomInput");
        }
        String[] inputParts = fullCommand.split(" ", 2);
        String option = inputParts[0];
        String desc = inputParts.length == 2 ? inputParts[1] : "";
        String[] tempInputParts;

        switch (option.toLowerCase()) {
        case "bye":
            if (!desc.isBlank()) {
                throw new DukeException("randomInput");
            }
            command = new ExitCommand();
            break;

        case "list":
            if (!desc.isBlank()) {
                throw new DukeException("randomInput");
            }
            command = new ShowListCommand();
            break;

        case "done":
            if (desc.isBlank()) {
                throw new DukeException("doneMissingIndex");
            }
            if (!isInteger(desc.trim())) {
                throw new DukeException("doneWrongIndexFormat");
            }
            command = new DoneCommand(Integer.parseInt(desc));
            break;

        case "todo":
            if (desc.isBlank()) {
                throw new DukeException("taskMissingDescription");
            }
            command = new AddCommand(new ToDo(desc.trim()));
            break;

        case "deadline":
            if (desc.isBlank()) {
                throw new DukeException("taskMissingDescription");
            }
            if (!desc.contains("/by")) {
                throw new DukeException("deadline&eventWrongDescriptionFormat");
            }
            tempInputParts = desc.trim().split("/by");
            if (tempInputParts.length != 2 || tempInputParts[0].isBlank()) {
                throw new DukeException("deadline&eventWrongDescriptionFormat");
            }
            LocalDateTime inputTime = null;
            try {
                inputTime = LocalDateTime.parse(tempInputParts[1].trim(), Task.DATETIME_FORMAT);
            } catch (DateTimeParseException e) {
                throw new DukeException("DateTimeParseError");
            }
            if (inputTime.isBefore(LocalDateTime.now())) {
                throw new DukeException("pastDateTime");
            }
            command = new AddCommand(new Deadline(tempInputParts[0].trim(), inputTime));
            break;

        case "event":
            if (desc.isBlank()) {
                throw new DukeException("taskMissingDescription");
            }
            if (!desc.contains("/at")) {
                throw new DukeException("deadline&eventWrongDescriptionFormat");
            }
            tempInputParts = desc.trim().split("/at");
            if (tempInputParts.length != 2 || tempInputParts[0].isBlank()) {
                throw new DukeException("deadline&eventWrongDescriptionFormat");
            }
            command = new AddCommand(new Event(tempInputParts[0].trim(), tempInputParts[1].trim()));
            break;

        case "delete":
            if (desc.isBlank()) {
                throw new DukeException("deleteMissingIndex");
            }
            if (!isInteger(desc.trim())) {
                throw new DukeException("deleteWrongIndexFormat");
            }
            command = new DeleteCommand(Integer.parseInt(desc));
            break;

        default:
            throw new DukeException("randomInput");
        }
        return command;
    }

    /**
     * Checks whether the input String is an integer.
     *
     * @param s The input String need to be checked.
     * @return True if the String is an integer, false if it is not.
     */
    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

}
