package hakunaMatata;

/**
 * Represents exception in hakunaMatata program.
 *
 * @author Wang Yuting
 */
public class HakunaMatataException extends Exception {

    /**
     * The error message of <code>HakunaMatataException</code>.
     */
    public String errorMessage = "(⑉꒦^꒦⑉) !!! ";
    // set of special text
    private String invalidIndex = "\uD83C\uDD78"
            + "\uD83C\uDD7D\uD83C\uDD85\uD83C\uDD70\uD83C\uDD7B\uD83C\uDD78\uD83C\uDD73 "
            + "\uD83C\uDD78\uD83C\uDD7D\uD83C\uDD73\uD83C\uDD74\uD83C\uDD87\n";
    private String invalidFormat = "\uD83C\uDD78"
            + "\uD83C\uDD7D\uD83C\uDD85\uD83C\uDD70\uD83C\uDD7B\uD83C\uDD78\uD83C\uDD73 "
            + "\uD83C\uDD75\uD83C\uDD7E\uD83C\uDD81\uD83C\uDD7C\uD83C\uDD70\uD83C\uDD83\n";
    private String randomInput = "\uD83C\uDD82\uD83C\uDD7E\uD83C\uDD81\uD83C\uDD81\uD83C\uDD88\n";
    private String emptyList = "\uD83C\uDD74\uD83C\uDD7C\uD83C\uDD7F\uD83C\uDD83\uD83C\uDD88 "
            + "\uD83C\uDD7B\uD83C\uDD78\uD83C\uDD82\uD83C\uDD83\n";

    /**
     * Creats a <code>HakunaMatataException</code> with given message.
     */
    public HakunaMatataException(String message) {
        super(message);

        switch (message) {
        case "emptyList":
            this.errorMessage += emptyList + "There is no task yet. Please add some tasks.";
            break;

        case "doneMissingIndex":
            this.errorMessage += invalidIndex + "The index number of a done query cannot be empty.";
            break;

        case "doneWrongIndexFormat":
            //Fallthrough
        case "deleteWrongIndexFormat":
            this.errorMessage += invalidIndex + "Please use an integer as index number.";
            break;

        case "findMissingKeyword":
            this.errorMessage += invalidFormat + "Please enter your search keyword.";
            break;

        case "sortMissingSortTerm":
            this.errorMessage = invalidFormat + "Please enter the sort term (description/deadline).";
            break;

        case "sortWrongSortTermFormat":
            this.errorMessage = invalidFormat + "Please enter a valid sort term (description/deadline).";
            break;

        case "doneWrongIndexRange":
            //Fallthrough
        case "deleteWrongIndexRange":
            this.errorMessage += invalidIndex + "Please enter valid task index.";
            break;

        case "doneComplectedTask":
            this.errorMessage += invalidIndex + "The task is already completed.";
            break;

        case "taskMissingDescription":
            this.errorMessage += invalidFormat + "The description of a todo/deadline/event cannot be empty.";
            break;

        case "deadline&eventWrongDescriptionFormat":
            this.errorMessage += invalidFormat + "Please follow the valid deadline/event creation format. "
                    + "\n  e.g. deadline description /by date ; event description /at date";
            break;

        case "randomInput":
            this.errorMessage += randomInput + "I'm sorry, but I don't know what that means :-(";
            break;

        case "deleteMissingIndex":
            this.errorMessage += invalidIndex + "Please enter the index of the task you want to delete.";
            break;

        case "pastDateTime":
            this.errorMessage += invalidFormat + "Please enter a time in the future.";
            break;

        case "FileNotFound":
            this.errorMessage += "There is something wrong with your file storage.";
            break;

        case "DateTimeParseError":
            this.errorMessage += invalidFormat + "Invalid date time format, please follow the format below:\n  "
                    + "deadline: HH:mm dd/MM/yyyy   e.g. 12:00 01/01/2020\n"
                    + "event   : HH:mm dd/MM/yyyy-HH:mm dd/MM/yyyy   "
                    + "e.g. 12:00 01/01/2020-14:00 01/01/2020\n";
            break;

        case "EventEndBeforeStartError":
            this.errorMessage += invalidFormat + "The end date you entered is before the start date, please check.";
            break;

        default:
            this.errorMessage += "Something went wrong: " + message;
            break;
        }
    }
}
