package duke;

import duke.command.*;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Parser {

    public Parser() {

    }

    public static Command parse(String fullCommand) throws DukeException {
        Command command = null;
        if (fullCommand.isBlank()) {
            throw new DukeException("randomInput");
        }
        String[] option_desc = fullCommand.split(" ", 2);
        String option = option_desc[0];
        String desc = option_desc.length == 2 ? option_desc[1] : "";
        String[] tempArr;

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
            tempArr = desc.trim().split("/by");
            if (tempArr.length != 2 || tempArr[0].isBlank()) {
                throw new DukeException("deadline&eventWrongDescriptionFormat");
            }
            LocalDateTime inputTime;
            try {
                inputTime = LocalDateTime.parse(tempArr[1].trim(), Task.DATETIME_FORMAT);
            } catch (DateTimeParseException e) {
                throw new DukeException("DateTimeParseError");
            }
            if (inputTime.isBefore(LocalDateTime.now())) {
                throw new DukeException("pastDateTime");
            }
            command = new AddCommand(new Deadline(tempArr[0].trim(), inputTime));
            break;

        case "event":
            if (desc.isBlank()) {
                throw new DukeException("taskMissingDescription");
            }
            if (!desc.contains("/at")) {
                throw new DukeException("deadline&eventWrongDescriptionFormat");
            }
            tempArr = desc.trim().split("/at");
            if (tempArr.length != 2 || tempArr[0].isBlank()) {
                throw new DukeException("deadline&eventWrongDescriptionFormat");
            }
            command = new AddCommand(new Event(tempArr[0].trim(), tempArr[1].trim()));
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

        case "find":
            if (desc.isBlank()) {
                throw new DukeException("findMissingKeyword");
            }
            command = new FindCommand(desc);
            break;

        default:
            throw new DukeException("randomInput");
        }
        return command;
    }

    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

}
