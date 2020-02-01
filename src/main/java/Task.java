import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

enum TaskType {
    TODO,
    DEADLINE,
    EVENT
}

public class Task {
    protected String taskName;
    protected boolean isDone;
    protected TaskType type;
    public static ArrayList<Task> taskList = new ArrayList<>();

    public Task() {

    }

    public Task(String taskName, TaskType type) {
        this.taskName = taskName;
        this.type = type;
        taskList.add(this);
        this.isDone = false;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getStatusString() {
        return isDone ? "[✓]" : "[✗]";
    }

    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString(){
        return this.getStatusString() + " " + this.getTaskName();
    }

    public static String getTaskListString() {
        String listOfTasks = "";

        for(Task t : taskList) {
            int counter = taskList.indexOf(t);
            if (counter != 0) listOfTasks += "     ";
            listOfTasks += counter + 1 + "." + t.toString() + "\n";
        }
        return taskList.size() != 0 ? listOfTasks : "- No task was entered -\n";
    }

    public static String getSavedString() {
        String tasks = "";
        for(Task t : taskList) {
            String temp = "";
            int status = t.isDone ? 1 : 0;
            switch (t.type) {
                case TODO:
                    temp += "T | " + status + " | " + t.getTaskName() + "\n";
                    break;
                case DEADLINE:
                    temp += "D | "+ status + " | " + t.getTaskName() + " | " + ((Deadline)t).getBy() + "\n";
                    break;
                case EVENT:
                    temp += "E | "+ status + " | " + t.getTaskName() + " | " + ((Event)t).getAt() + "\n";
                    break;
            }
            tasks += temp;
        }
        return tasks;
    }
}
