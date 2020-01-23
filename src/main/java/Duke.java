import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws FileNotFoundException {

        //File text = new File("text-ui-test/input.txt");
        //Scanner scanner = new Scanner(text);
        Scanner scanner = new Scanner(System.in);
        final String line = "    ____________________________________________________________";
        String greeting =
                        line + "\n" +
                                "     Hello! I'm Duke, a task list chat box\n" +
                                "     You can key in your tasks and I'll keep track of them for you.\n\n" +
                                "     ============================== Guide ==============================\n"+
                                "     There are three types of tasks I'm able to keep track of.\n" +
                                "         Todo: use \"todo + description\" to create (e.g. todo taskInfo).\n" +
                                "         Deadline: use \"deadline + description + /by + date\" to create (e.g. deadline taskInfo /by June 6th).\n" +
                                "         Event: use \"event + description + /at + date\" to create (e.g. event taskInfo /at Mon 2-4pm).\n\n" +
                                "     You can also use \"list\" to check the recorded tasks.\n" +
                                "     Or use \"done + task index\" to mark the task as done. \n\n" +
                                "     To terminate me, please use \"bye\".\n\n" +
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
                switch (option.toLowerCase()) {
                    case "bye":
                        userInput = scanner.next();
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
                        if (!isInteger(userInput.trim())) throw new DukeException("doneWrongIndexFormat");
                        if(Task.taskList.size() == 0) throw new DukeException("emptyList");
                        int index = Integer.parseInt(userInput.trim());
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
                        ToDo toDo = new ToDo(userInput.trim());
                        reply += "     Got it. I've added this task: \n" +
                                "       " + toDo.toString() + "\n" +
                                "     Now you have " + Task.taskList.size() + " tasks in the list.\n";
                        break;

                    case "deadline":
                        userInput = scanner.nextLine();
                        if (userInput.isBlank()) throw new DukeException("taskMissingDescription");
                        if (!userInput.contains("/by")) throw new DukeException("deadline&eventWrongDescriptionFormat");
                        tempArr = userInput.trim().split("/by");
                        if (tempArr.length != 2) throw new DukeException("deadline&eventWrongDescriptionFormat");
                        Deadline deadline = new Deadline(tempArr[0].trim(), tempArr[1].trim());
                        reply += "     Got it. I've added this task: \n" +
                                "       " + deadline.toString() + "\n" +
                                "     Now you have " + Task.taskList.size() + " tasks in the list.\n";
                        break;

                    case "event":
                        userInput = scanner.nextLine();
                        if (userInput.isBlank()) throw new DukeException("taskMissingDescription");
                        if (!userInput.contains("/at")) throw new DukeException("deadline&eventWrongDescriptionFormat");
                        tempArr = userInput.trim().split("/at");
                        if (tempArr.length != 2) throw new DukeException("deadline&eventWrongDescriptionFormat");
                        Event event = new Event(tempArr[0].trim(), tempArr[1].trim());
                        reply += "     Got it. I've added this task: \n" +
                                "       " + event.toString() + "\n" +
                                "     Now you have " + Task.taskList.size() + " tasks in the list.\n";
                        break;

                    case "delete":
                        userInput = scanner.nextLine();
                        if (userInput.isBlank()) throw new DukeException("deleteMissingIndex");
                        if (!isInteger(userInput.trim())) throw new DukeException("deleteWrongIndexFormat");
                        if(Task.taskList.size() == 0) throw new DukeException("emptyList");
                        int deleteIndex = Integer.parseInt(userInput.trim());
                        if (deleteIndex > Task.taskList.size() || deleteIndex < 1)
                            throw new DukeException("deleteWrongIndexRange");
                        Task temp = Task.taskList.remove(deleteIndex - 1);
                        reply += "     Noted. I've removed this task: \n" +
                                 "       " + temp.toString() + "\n" +
                                 "     Now you have " + Task.taskList.size() + " tasks in the list.\n";
                        break;

                    default:
                        //System.out.println(option);
                        scanner.nextLine();
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

    public static boolean validation(String option, String userInput) {
        boolean valid = true;
        return valid;
    }
}
