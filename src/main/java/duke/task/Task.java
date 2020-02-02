package duke.task;

import java.time.format.DateTimeFormatter;

enum TaskType {
    TODO,
    DEADLINE,
    EVENT
}

public class Task {
    public static final DateTimeFormatter DATETIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy");
    protected String taskName;
    protected boolean isDone;
    protected TaskType type;

    public Task() {

    }

    public Task(String taskName, TaskType type) {
        this.taskName = taskName;
        this.type = type;
        this.isDone = false;
    }

    public boolean isDone() {
        return isDone;
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
    public String toString() {
        return this.getStatusString() + " " + this.getTaskName();
    }

}
