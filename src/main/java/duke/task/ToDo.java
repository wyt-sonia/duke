package duke.task;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description, TaskType.TODO);
    }

    public ToDo(String description, boolean isDone) {
        super(description, TaskType.TODO);
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
