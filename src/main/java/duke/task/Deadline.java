package duke.task;

import java.time.LocalDateTime;

public class Deadline extends Task {
    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description, TaskType.DEADLINE);
        this.by = by;
    }

    public Deadline(String description, LocalDateTime by, boolean isDone) {
        super(description, TaskType.DEADLINE);
        this.isDone = isDone;
        this.by = by;
    }

    public LocalDateTime getBy() {
        return by;
    }

    public void setBy(LocalDateTime by) {
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() +
                " (by: " + by.getHour() + ":" + by.getMinute() +
                " " + by.getDayOfMonth() + "/" + by.getMonthValue() + "/" + by.getYear() + ")";
    }

    public String getDateTimeString() {
        return by.getHour() + ":" + by.getMinute() +
                " " + by.getDayOfMonth() + "/" + by.getMonthValue() + "/" + by.getYear();
    }
}
