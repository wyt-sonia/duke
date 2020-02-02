package duke;

/**
 * Represents exception in duke program.
 *
 * @author Wang Yuting
 */
public class DukeException extends Exception {

    /**
     * The error message of <code>DukeException</code>.
     */
    protected String errorMessage = "     ☹ OOPS!!! ";

    /**
     * Creats a <code>DukeException</code> with given message.
     */
    public DukeException(String message) {
        super(message);

        switch (message) {
            case "emptyList":
                this.errorMessage += "There is no task yet. Please add some tasks.";
                break;

            case "doneMissingIndex":
                this.errorMessage += "The index number of a done query cannot be empty.";
                break;

            case "doneWrongIndexFormat":
                //Fallthrough
            case "deleteWrongIndexFormat":
                this.errorMessage += "Please use an integer as index number.";
                break;

            case "doneWrongIndexRange":
                //Fallthrough
            case "deleteWrongIndexRange":
                this.errorMessage += "Please enter valid task index.";
                break;

            case "doneComplectedTask":
                this.errorMessage += "The task is already completed.";
                break;

            case "taskMissingDescription":
                this.errorMessage += "The description of a todo/deadline/event cannot be empty.";
                break;

            case "deadline&eventWrongDescriptionFormat":
                this.errorMessage += "Please follow the valid deadline/event creation format. " +
                        "\n       e.g. deadline description /by date ; event description /at date";
                break;

            case "randomInput":
                this.errorMessage += "I'm sorry, but I don't know what that means :-(";
                break;

            case "deleteMissingIndex":
                this.errorMessage += "Please enter the index of the task you want to delete.";
                break;

            case "pastDateTime":
                this.errorMessage += "Please enter a time in the future.";
                break;

            case "FileNotFound":
                this.errorMessage += "There is something wrong with your file storage.";
                break;

            case "DateTimeParseError":
                this.errorMessage += "Invalid date time format, please follow the format below:\n       " +
                                     "HH:mm dd/MM/yyyy   e.g. 12:00 01/01/2020";
                break;
            default:
                this.errorMessage += "Something went wrong: " + message;

        }
    }
}
