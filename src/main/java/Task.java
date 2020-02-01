import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
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
    static final DateTimeFormatter DATETIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy");

    public Task() {

    }

    public Task(String taskName, TaskType type) {
        this.taskName = taskName;
        this.type = type;
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

}
