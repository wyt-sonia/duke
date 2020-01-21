import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String stopFlag = "bye";
        String greeting =
                "    ____________________________________________________________\n" +
                "     Hello! I'm Duke\n" +
                "     What can I do for you?\n" +
                "    ____________________________________________________________";
        String userInput = "";


        System.out.println(greeting);

        while(!userInput.equals(stopFlag)){
            userInput = scanner.nextLine();
            String reply = "";

            switch (userInput) {
                case "bye":
                    reply = "    ____________________________________________________________\n" +
                            "     Bye. Hope to see you again soon!\n" +
                            "    ____________________________________________________________\n";
                    break;

                case "list":
                    reply = "    ____________________________________________________________\n" +
                            "     Here are the tasks in your list: \n" +
                            "     "+ Task.getTaskListString()  +
                            "    ____________________________________________________________\n";
                    break;

                default:
                    String[] tempArr = userInput.split(" ");
                    if(tempArr[0].equals("done")) {
                        if(tempArr.length != 2 || !isInteger(tempArr[1]) ||
                                Integer.parseInt(tempArr[1]) > Task.taskList.size() || Integer.parseInt(tempArr[1]) < 1) {
                            reply = "    ____________________________________________________________\n" +
                                    "     Please enter a valid task number\n" +
                                    "    ____________________________________________________________\n";
                        } else {
                            Task t = Task.taskList.get(Integer.parseInt(tempArr[1]) - 1);
                            t.markAsDone();
                            reply = "    ____________________________________________________________\n" +
                                    "     Nice! I've marked this task as done: \n" +
                                    "       " + t.getStatusString() + " " + t.getTaskName() + "\n" +
                                    "    ____________________________________________________________\n";
                        }
                    } else {
                        Task inputedTask = new Task(userInput);
                        reply = "    ____________________________________________________________\n" +
                                "     added: "+ inputedTask.getTaskName() +"\n" +
                                "    ____________________________________________________________\n";
                    }

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
