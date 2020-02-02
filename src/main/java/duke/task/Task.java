package duke.task;

import java.time.format.DateTimeFormatter;

public class Task {

    protected String description;

    protected boolean isDone;

    protected TaskType type;

    public static final DateTimeFormatter DATETIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy");

    public Task() {

    }

    public Task(String description, TaskType type) {
        this.description = description;
        this.type = type;
        this.isDone = false;
    }

    public boolean isDone() {
        return isDone;
    }

    public String getDescription() {
        return description;
    }

    public String getStatusString() {
        return isDone ? "[✓]" : "[✗]";
    }

    public void markAsDone() {
        this.isDone = true;
    }
    
    @Override
    public String toString() {
        return this.getStatusString() + " " + this.getDescription();
    }

}
