package duke.task;

public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description, TaskType.EVENT);
        this.at = at;
    }

    public Event(String description, String at, boolean isDone) {
        super(description, TaskType.EVENT);
        this.isDone = isDone;
        this.at = at;
    }

    public String getAt() {
        return at;
    }

    public void setAt(String at) {
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}

