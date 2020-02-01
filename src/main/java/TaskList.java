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

    public String getTaskListString() {
        String listOfTasks = "";

        for(Task t : this.tasks) {
            int counter = this.tasks.indexOf(t);
            if (counter != 0) listOfTasks += "     ";
            listOfTasks += counter + 1 + "." + t.toString() + "\n";
        }
        return this.tasks.size() != 0 ? listOfTasks : "- No task was entered -\n";
    }

    public String getSavedString() {
        String tasks = "";
        for(Task t : this.tasks) {
            String temp = "";
            int status = t.isDone ? 1 : 0;
            switch (t.type) {
                case TODO:
                    temp += "T | " + status + " | " + t.getTaskName() + "\n";
                    break;
                case DEADLINE:
                    temp += "D | "+ status + " | " + t.getTaskName() + " | " + ((Deadline)t).getDateTimeString() + "\n";
                    break;
                case EVENT:
                    temp += "E | "+ status + " | " + t.getTaskName() + " | " + ((Event)t).getAt() + "\n";
                    break;
            }
            tasks += temp;
        }
        return tasks;
    }

    public int getSize() {
        return this.tasks.size();
    }
}
