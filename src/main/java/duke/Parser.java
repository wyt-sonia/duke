package duke;

import duke.command.Command;
import duke.command.ExitCommand;
import duke.command.ShowListCommand;
import duke.command.DoneCommand;
import duke.command.AddCommand;
import duke.command.DeleteCommand;
import duke.command.FindCommand;

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

        switch (option.toLowerCase()) {
        case "bye":
            command = parseExitCommand(desc);
            break;

        case "list":
            command = parseListCommand(desc);
            break;

        case "done":
            command = parseDoneCommand(desc);
            break;

        case "todo":
            command = parseAddTodoCommand(desc);
            break;

        case "deadline":
            command = parseAddDeadlineCommand(desc);
            break;

        case "event":
            command = parseEventCommand(desc);
            break;

        case "delete":
            command = parseDeleteCommand(desc);
            break;

        case "find":
            command = parseFindCommand(desc);
            break;

        default:
            throw new DukeException("randomInput");
        }
        return command;
    }

    /** Parses exit command */
    private static Command parseExitCommand(String desc) throws DukeException {
        if (!desc.isBlank()) {
            throw new DukeException("randomInput");
        }
        return new ExitCommand();
    }

    /** Parses list command */
    private static Command parseListCommand(String desc) throws DukeException {
        if (!desc.isBlank()) {
            throw new DukeException("randomInput");
        }
        return new ShowListCommand();
    }

    /** Parses done command */
    private static Command parseDoneCommand(String desc) throws DukeException {
        if (desc.isBlank()) {
            throw new DukeException("doneMissingIndex");
        }
        if (!isInteger(desc.trim())) {
            throw new DukeException("doneWrongIndexFormat");
        }
        int index = Integer.parseInt(desc);
        return new DoneCommand(index);
    }

    /** Parses todo command */
    private static Command parseAddTodoCommand(String desc) throws DukeException {
        if (desc.isBlank()) {
            throw new DukeException("taskMissingDescription");
        }
        ToDo newTodo = new ToDo(desc.trim());
        return new AddCommand(newTodo);
    }

    /** Parses deadline command */
    private static Command parseAddDeadlineCommand(String desc) throws DukeException {
        String[] tempInputParts;
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
        Deadline newDeadline = new Deadline(tempInputParts[0].trim(), inputTime);
        return new AddCommand(newDeadline);
    }

    /** Parses event command */
    private static Command parseEventCommand(String desc) throws DukeException  {
        String[] tempInputParts;
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
        Event newEvent = new Event(tempInputParts[0].trim(), tempInputParts[1].trim());
        return new AddCommand(newEvent);
    }

    /** Parses delete command */
    private static Command parseDeleteCommand(String desc) throws DukeException  {
        if (desc.isBlank()) {
            throw new DukeException("deleteMissingIndex");
        }
        if (!isInteger(desc.trim())) {
            throw new DukeException("deleteWrongIndexFormat");
        }
        return new DeleteCommand(Integer.parseInt(desc));
    }

    /** Parses find command */
    private static Command parseFindCommand(String desc) throws DukeException  {
        if (desc.isBlank()) {
            throw new DukeException("findMissingKeyword");
        }
        return new FindCommand(desc);
    }


    /**
     * Checks whether the input String is an integer.
     *
     * @param s The input String need to be checked.
     * @return True if the String is an integer, false if it is not.
     */
    public static boolean isInteger(String s) {
        try {
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

}
