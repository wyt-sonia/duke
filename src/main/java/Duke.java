import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String stopFlag = "bye";
        String greeting =
                "    ____________________________________________________________\n" +
                        "     Hello! I'm Duke, a task list chat box\n" +
                        "     You can key in your tasks and I'll keep track of them for you.\n" +
                        "     Use \"list\" keyword to check your task list and their status.\n" +
                        "     Use \"done and the index of the task\" (e.g. done 1)  to mark target task as done.\n\n" +
                        "     What can I do for you?\n" +
                        "    ____________________________________________________________";

        String invalidInput =
                "    ____________________________________________________________\n" +
                "     Please enter valid order\n" +
                "    ____________________________________________________________\n";

        String option = "";
        String userInput = "";
        String[] tempArr;

        System.out.println(greeting);

        while(!option.equals(stopFlag)){
            option = scanner.next();
            String reply = "";
            reply = invalidInput;

            switch (option) {
                case "bye":
                    reply = "    ____________________________________________________________\n" +
                            "     Bye. Hope to see you again soon!\n" +
                            "    ____________________________________________________________\n";
                    break;

                case "list":
                    String listOfTasks = "";
                    for(Task t : Task.taskList) {
                        int counter = Task.taskList.indexOf(t);
                        if (counter != 0) listOfTasks += "     ";
                        listOfTasks += counter + 1 + "." + t.toString() + "\n";
                    }
                    reply = "    ____________________________________________________________\n" +
                            "     Here are the tasks in your list: \n" +
                            "     "+ (Task.taskList.size() != 0 ? listOfTasks : "- No task was entered -\n") +
                            "    ____________________________________________________________\n";
                    scanner.nextLine();
                    break;

                case "done":
                    userInput =  scanner.nextLine();
                    if(!userInput.equals("")) userInput = userInput.substring(1);
                        if(isInteger(userInput)) {
                            int index = Integer.parseInt(userInput);
                            if( index <= Task.taskList.size() && index >= 1) {
                                Task t = Task.taskList.get(index - 1);
                                t.markAsDone();
                                reply = "    ____________________________________________________________\n" +
                                        "     Nice! I've marked this task as done: \n" +
                                        "       " + t.toString() + "\n" +
                                        "    ____________________________________________________________\n";
                            }
                        }
                    break;

                case "todo":
                    userInput =  scanner.nextLine();
                    if(!userInput.equals("")){
                        userInput =  userInput.substring(1);
                        ToDo toDo = new ToDo(userInput);
                        reply = "    ____________________________________________________________\n" +
                                "     Got it. I've added this task: \n"+
                                "       "+ toDo.toString()+ "\n" +
                                "     Now you have "+ Task.taskList.size() + " tasks in the list.\n" +
                                "    ____________________________________________________________\n";
                    }
                    break;

                case "deadline":
                    userInput =  scanner.nextLine();
                    if(!userInput.equals("") && userInput.contains("/")) {
                        userInput = userInput.substring(1);
                        tempArr = userInput.split("/");
                        if (tempArr.length == 2) {
                            Deadline deadline = new Deadline(tempArr[0].substring(0, tempArr[0].length() - 1), tempArr[1]);
                            reply = "    ____________________________________________________________\n" +
                                    "     Got it. I've added this task: \n" +
                                    "       " + deadline.toString() + "\n" +
                                    "     Now you have " + Task.taskList.size() + " tasks in the list.\n" +
                                    "    ____________________________________________________________\n";
                        }
                    }
                    break;

                case "event":
                    userInput =  scanner.nextLine();
                    if(!userInput.equals("") && userInput.contains("/")){
                        userInput = userInput.substring(1);
                        tempArr = userInput.split("/");
                        if(tempArr.length == 2) {
                            Event event  = new Event(tempArr[0].substring(0, tempArr[0].length() - 1), tempArr[1]);
                            reply = "    ____________________________________________________________\n" +
                                    "     Got it. I've added this task: \n"+
                                    "       "+ event.toString()+ "\n" +
                                    "     Now you have "+ Task.taskList.size() + " tasks in the list.\n" +
                                    "    ____________________________________________________________\n";
                        }
                    }
                    break;
            }
            System.out.println(reply);
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
