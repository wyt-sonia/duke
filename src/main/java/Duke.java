import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        final String line = "    ____________________________________________________________";
        String greeting =
                        line + "\n" +
                        "     Hello! I'm Duke, a task list chat box\n" +
                        "     You can key in your tasks and I'll keep track of them for you.\n" +
                        "     Use \"list\" keyword to check your task list and their status.\n" +
                        "     Use \"done and the index of the task\" (e.g. done 1)  to mark target task as done.\n\n" +
                        "     What can I do for you?\n" +
                        line;

        String option = "";
        String userInput = "";
        String[] tempArr;
        String reply = "";
        System.out.println(greeting);
        while(!option.equals("bye")) {
            reply = line + "\n";
            option = scanner.next();
            try {
                switch (option) {
                    case "bye":
                        userInput = scanner.nextLine();
                        if (!userInput.isBlank()) {
                            option = "";
                            throw new DukeException("randomInput");
                        }
                        reply += "     Bye. Hope to see you again soon!\n";
                        break;

                    case "list":
                        userInput = scanner.nextLine();
                        if (!userInput.isBlank()) throw new DukeException("randomInput");
                        String listOfTasks = "";
                        if (Task.taskList.size() == 0) {
                            throw new DukeException("emptyList");
                        } else {
                            for (Task t : Task.taskList) {
                                int counter = Task.taskList.indexOf(t);
                                if (counter != 0) listOfTasks += "     ";
                                listOfTasks += counter + 1 + "." + t.toString() + "\n";
                            }
                            reply += "     Here are the tasks in your list: \n" +
                                    "     " + listOfTasks;
                        }
                        break;

                    case "done":
                        userInput = scanner.nextLine();
                        if (userInput.isBlank()) throw new DukeException("doneMissingIndex");
                        if (!isInteger(userInput.substring(1))) throw new DukeException("doneWrongIndexFormat");
                        int index = Integer.parseInt(userInput.substring(1));
                        if (index > Task.taskList.size() || index < 1)
                            throw new DukeException("doneWrongIndexRange");
                        Task t = Task.taskList.get(index - 1);
                        if (t.isDone) throw new DukeException("doneComplectedTask");
                        t.markAsDone();
                        reply += "     Nice! I've marked this task as done: \n" +
                                "       " + t.toString() + "\n";
                        break;

                    case "todo":
                        userInput = scanner.nextLine();
                        if (userInput.isBlank()) throw new DukeException("taskMissingDescription");
                        ToDo toDo = new ToDo(userInput.substring(1));
                        reply += "     Got it. I've added this task: \n" +
                                "       " + toDo.toString() + "\n" +
                                "     Now you have " + Task.taskList.size() + " tasks in the list.\n";
                        break;

                    case "deadline":
                        userInput = scanner.nextLine();
                        if (userInput.isBlank()) throw new DukeException("taskMissingDescription");
                        if (!userInput.contains("/")) throw new DukeException("deadline&eventWrongDescriptionFormat");
                        tempArr = userInput.substring(1).split("/");
                        if (tempArr.length != 2) throw new DukeException("deadline&eventWrongDescriptionFormat");
                        Deadline deadline = new Deadline(tempArr[0].substring(0, tempArr[0].length() - 1), tempArr[1]);
                        reply += "     Got it. I've added this task: \n" +
                                "       " + deadline.toString() + "\n" +
                                "     Now you have " + Task.taskList.size() + " tasks in the list.\n";
                        break;

                    case "event":
                        userInput = scanner.nextLine();
                        if (userInput.isBlank()) throw new DukeException("taskMissingDescription");
                        if (!userInput.contains("/")) throw new DukeException("deadline&eventWrongDescriptionFormat");
                        tempArr = userInput.substring(1).split("/");
                        if (tempArr.length != 2) throw new DukeException("deadline&eventWrongDescriptionFormat");
                        Event event = new Event(tempArr[0].substring(0, tempArr[0].length() - 1), tempArr[1]);
                        reply += "     Got it. I've added this task: \n" +
                                "       " + event.toString() + "\n" +
                                "     Now you have " + Task.taskList.size() + " tasks in the list.\n";
                        break;

                    default:
                        throw new DukeException("randomInput");
                }

            } catch (DukeException e) {
                reply += e.errorMessage + "\n";
            } finally {
                reply += line + "\n";
                System.out.println(reply);
            }
        }
    }

    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch(NumberFormatException e) {
            return false;
        }
        return true;
    }
}
