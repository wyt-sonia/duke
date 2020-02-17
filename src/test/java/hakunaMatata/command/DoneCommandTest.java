package hakunaMatata.command;

import hakunaMatata.HakunaMatataException;
import hakunaMatata.Storage;
import hakunaMatata.Ui;
import hakunaMatata.task.Task;
import hakunaMatata.task.TaskList;
import hakunaMatata.task.ToDo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DoneCommandTest {

    @Test
    public void doneCommandTest_wrongIndexRange_throwHakunaMatataException() {
        HakunaMatataException actualException1 = Assertions.assertThrows(HakunaMatataException.class, () -> {
            ArrayList<Task> tasks = new ArrayList<Task>();
            tasks.add(new ToDo("test"));
            TaskList testList = new TaskList(tasks);
            new DeleteCommand(3).execute(testList, new Ui(), new Storage("tasks.txt"));
        });
        assertEquals("deleteWrongIndexRange", actualException1.getMessage());

        HakunaMatataException actualException2 = Assertions.assertThrows(HakunaMatataException.class, () -> {
            ArrayList<Task> tasks = new ArrayList<Task>();
            tasks.add(new ToDo("test"));
            TaskList testList = new TaskList(tasks);
            new DeleteCommand(-1).execute(testList, new Ui(), new Storage("tasks.txt"));
        });
        assertEquals("deleteWrongIndexRange", actualException2.getMessage());
    }

    @Test
    public void doneCommandTest_alreadyDone_throwHakunaMatataException() {
        HakunaMatataException actualException = Assertions.assertThrows(HakunaMatataException.class, () -> {
            ArrayList<Task> tasks = new ArrayList<Task>();
            tasks.add(new ToDo("test", true));
            TaskList testList = new TaskList(tasks);
            new DoneCommand(1).execute(testList, new Ui(), new Storage("tasks.txt"));
        });
        assertEquals("doneComplectedTask", actualException.getMessage());
    }
}