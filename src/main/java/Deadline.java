import java.time.LocalDateTime;

public class Deadline extends Task {
    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description, TaskType.DEADLINE);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() +
                " (by: " + by.getHour() + ":" + by.getMinute() +
                " " + by.getDayOfMonth() + "/" + by.getMonthValue() + "/" + by.getYear() + ")";
    }
}
