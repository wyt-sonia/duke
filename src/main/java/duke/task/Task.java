package duke.task;

import java.time.format.DateTimeFormatter;

/**
 * Different task types.
 *
 * @author Wang Yuting
 */
enum TaskType {

    /**
     * ToDo Rask.
     */
    TODO,

    /**
     * Deadline Rask.
     */
    DEADLINE,

    /**
     * Event Rask.
     */
    EVENT
}

/**
 * Represents a task.
 *
 * @author Wang Yuting
 */
public class Task {

    /**
     * The description of task.
     */
    protected String description;

    /**
     * The isDone status of task, true for done.
     */
    protected boolean isDone;

    /**
     * The type of the task.
     */
    protected TaskType type;

    /**
     * The acceptable data and time format.
     */
    public static final DateTimeFormatter DATETIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy");

    /**
     * Creates a <code>Task</code>.
     */
    public Task() {

    }

    /**
     * Creates a <code>Task</code> with given <code>description</code> and <code>type</code>.
     */
    public Task(String description, TaskType type) {
        this.description = description;
        this.type = type;
        this.isDone = false;
    }

    /**
     * Gets <code>isDone</code> status.
     *
     * @return The <code>isDone</code> status of the <code>Task</code>.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Gets <code>description</code>.
     *
     * @return The <code>description</code> of the <code>Task</code>.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets <code>isDone</code> status as a String.
     *
     * @return "[✓]" for done and "[✗]" for pending.
     */
    public String getStatusString() {
        return isDone ? "[✓]" : "[✗]";
    }

    /**
     * Marks isDone status of a <code>Task</code> as true.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Converts the <code>Task</code> detail into a String.
     *
     * @return The detail of <code>Task</code> in String.
     */
    @Override
    public String toString(){
        return this.getStatusString() + " " + this.getDescription();
    }

}
