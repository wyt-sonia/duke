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
                            "     "+ Task.getTaskListString()  +
                            "    ____________________________________________________________\n";
                    break;

                default:
                    Task inputedTask = new Task(userInput);
                    reply = "    ____________________________________________________________\n" +
                            "     added: "+ inputedTask.getTaskName() +"\n" +
                            "    ____________________________________________________________\n";
            }
            System.out.println(reply);
        }
    }
}
