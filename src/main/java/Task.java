import java.util.ArrayList;

public class Task {
    private String taskName;
    public static ArrayList<Task> taskList = new ArrayList<>();

    public Task(String taskName) {
        this.taskName = taskName;
        taskList.add(this);
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskName() {
        return taskName;
    }

    public static String getTaskListString () {
        String listOfTasks = "";

        for(Task t : taskList) {
            int counter = taskList.indexOf(t);
            if (counter != 0) listOfTasks += "     ";
            listOfTasks += counter + 1 + " . " + t.getTaskName() + "\n";
        }
        return taskList.size() != 0 ? listOfTasks : "No task was entered.\n";
    }
}
