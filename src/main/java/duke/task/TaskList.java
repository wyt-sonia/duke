package duke.task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public Task delete(int index) {
        return tasks.remove(index);
    }

    public String getSavedString() {
        String tasks = "";
        for (Task t : this.tasks) {
            String temp = "";
            int status = t.isDone ? 1 : 0;
            switch (t.type) {
            case TODO:
                temp += "T | " + status + " | " + t.getDescription() + "\n";
                break;
            case DEADLINE:
                temp += "D | " + status + " | " + t.getDescription() + " | "
                        + ((Deadline) t).getDateTimeString() + "\n";
                break;
            case EVENT:
                temp += "E | " + status + " | " + t.getDescription() + " | " + ((Event) t).getAt() + "\n";
                break;
            default:
            }
            tasks += temp;
        }
        return tasks;
    }

    public int getSize() {
        return this.tasks.size();
    }
}
