package duke.task;

/**
 * Represents an event task.
 *
 * @author Wang Yuting
 */
public class Event extends Task {

    /**
     * This is the date and time of the event.
     */
    protected String at;

    /**
     * Creates a new <code>Event</code> with the given <code>description</code> and <code>at</code> (date and time).
     */
    public Event(String description, String at) {
        super(description, TaskType.EVENT);
        this.at = at;
    }

    /**
     * Creates a new <code>Event</code> with the given <code>description</code>, <code>at</code> (date and time) and
     * <code>isDone</code>.
     */
    public Event(String description, String at, boolean isDone) {
        super(description, TaskType.EVENT);
        this.isDone = isDone;
        this.at = at;
    }

    /**
     * Gets the <code>at</code> (date and time) of the <code>Event</code>.
     *
     * @return The <code>at</code> (date and time) of the <code>Event</code>.
     */
    public String getAt() {
        return at;
    }

    /**
     * Converts the <code>Event</code> detail into a String.
     *
     * @return The detail of <code>Event</code> in String.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}

