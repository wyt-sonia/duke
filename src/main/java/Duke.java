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

        System.out.println(greeting);

        String userInput = "";
        while(!userInput.equals(stopFlag)){
            userInput = scanner.nextLine();
            String reply = "";
            if(!userInput.equals(stopFlag)) {
                reply = "    ____________________________________________________________\n" +
                        "     "+ userInput +"\n" +
                        "    ____________________________________________________________\n";
            } else {
                reply = "    ____________________________________________________________\n" +
                        "     Bye. Hope to see you again soon!\n" +
                        "    ____________________________________________________________\n";
            }
            System.out.println(reply);
        }
    }
}
