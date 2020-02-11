package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DoneCommandTest {

    @Test
    public void doneCommandTest_wrongIndexRange_throwDukeException() {
        DukeException actualException1 = Assertions.assertThrows(DukeException.class, () -> {
            ArrayList<Task> tasks = new ArrayList<Task>();
            tasks.add(new ToDo("test"));
            TaskList testList = new TaskList(tasks);
            new DeleteCommand(3).execute(testList, new Ui(), new Storage("tasks.txt"));
        });
        assertEquals("deleteWrongIndexRange", actualException1.getMessage());

        DukeException actualException2 = Assertions.assertThrows(DukeException.class, () -> {
            ArrayList<Task> tasks = new ArrayList<Task>();
            tasks.add(new ToDo("test"));
            TaskList testList = new TaskList(tasks);
            new DeleteCommand(-1).execute(testList, new Ui(), new Storage("tasks.txt"));
        });
        assertEquals("deleteWrongIndexRange", actualException2.getMessage());
    }

    @Test
    public void doneCommandTest_alreadyDone_throwDukeException() {
        DukeException actualException = Assertions.assertThrows(DukeException.class, () -> {
            ArrayList<Task> tasks = new ArrayList<Task>();
            tasks.add(new ToDo("test", true));
            TaskList testList = new TaskList(tasks);
            new DoneCommand(1).execute(testList, new Ui(), new Storage("tasks.txt"));
        });
        assertEquals("doneComplectedTask", actualException.getMessage());
    }
}
