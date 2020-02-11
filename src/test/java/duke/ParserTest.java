package duke;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void parserTest_randomInput_throwDukeException() {
        DukeException actualException = Assertions.assertThrows(DukeException.class, () -> {
            Parser.parse("test  ");
        });
        assertEquals("randomInput", actualException.getMessage());
    }

    @Test
    public void parserTest_missingDescriptionTest_throwDukeException() {
        DukeException actualException1 = Assertions.assertThrows(DukeException.class, () -> {
            Parser.parse("todo");
        });
        assertEquals("taskMissingDescription", actualException1.getMessage());

        DukeException actualException2 = Assertions.assertThrows(DukeException.class, () -> {
            Parser.parse("todo  ");
        });
        assertEquals("taskMissingDescription", actualException2.getMessage());

        DukeException actualException3 = Assertions.assertThrows(DukeException.class, () -> {
            Parser.parse("deadline");
        });
        assertEquals("taskMissingDescription", actualException3.getMessage());

        DukeException actualException4 = Assertions.assertThrows(DukeException.class, () -> {
            Parser.parse("deadline  ");
        });
        assertEquals("taskMissingDescription", actualException4.getMessage());

        DukeException actualException5 = Assertions.assertThrows(DukeException.class, () -> {
            Parser.parse("event");
        });
        assertEquals("taskMissingDescription", actualException5.getMessage());

        DukeException actualException6 = Assertions.assertThrows(DukeException.class, () -> {
            Parser.parse("event  ");
        });
        assertEquals("taskMissingDescription", actualException6.getMessage());
    }

    @Test
    public void parserTest_missingIndexTest_throwDukeException() {
        DukeException actualException1 = Assertions.assertThrows(DukeException.class, () -> {
            Parser.parse("done");
        });
        assertEquals("doneMissingIndex", actualException1.getMessage());

        DukeException actualException2 = Assertions.assertThrows(DukeException.class, () -> {
            Parser.parse("done  ");
        });
        assertEquals("doneMissingIndex", actualException2.getMessage());

        DukeException actualException3 = Assertions.assertThrows(DukeException.class, () -> {
            Parser.parse("delete");
        });
        assertEquals("deleteMissingIndex", actualException3.getMessage());

        DukeException actualException4 = Assertions.assertThrows(DukeException.class, () -> {
            Parser.parse("delete  ");
        });
        assertEquals("deleteMissingIndex", actualException4.getMessage());
    }

    @Test
    public void parserTest_wrongIndexFormatTest_throwDukeException() {
        DukeException actualExceptionDone = Assertions.assertThrows(DukeException.class, () -> {
            Parser.parse("done 12test");
        });

        DukeException actualExceptionDelete = Assertions.assertThrows(DukeException.class, () -> {
            Parser.parse("delete 12test");
        });

        assertEquals("doneWrongIndexFormat", actualExceptionDone.getMessage());
        assertEquals("deleteWrongIndexFormat", actualExceptionDelete.getMessage());
    }

    @Test
    public void parserTest_wrongDescriptionFormat_throwException() {
        DukeException actualExceptionDeadlineMissingName = Assertions.assertThrows(DukeException.class, () -> {
            Parser.parse("deadline /by 12:12 12/12/2020");
        });
        assertEquals("deadline&eventWrongDescriptionFormat", actualExceptionDeadlineMissingName.getMessage());

        DukeException actualExceptionDeadlineMissingBy = Assertions.assertThrows(DukeException.class, () -> {
            Parser.parse("deadline test 12:12 12/12/2020");
        });
        assertEquals("deadline&eventWrongDescriptionFormat", actualExceptionDeadlineMissingBy.getMessage());

        DukeException actualExceptionDeadlineMissingDateTime = Assertions.assertThrows(DukeException.class, () -> {
            Parser.parse("deadline test /by");
        });
        assertEquals("deadline&eventWrongDescriptionFormat", actualExceptionDeadlineMissingDateTime.getMessage());

        DukeException actualExceptionEventMissingName = Assertions.assertThrows(DukeException.class, () -> {
            Parser.parse("event /at 2-4pm 12/12/2020");
        });
        assertEquals("deadline&eventWrongDescriptionFormat", actualExceptionEventMissingName.getMessage());

        DukeException actualExceptionEventMissingAt = Assertions.assertThrows(DukeException.class, () -> {
            Parser.parse("event test 2-4pm 12/12/2020");
        });
        assertEquals("deadline&eventWrongDescriptionFormat", actualExceptionEventMissingAt.getMessage());

        DukeException actualExceptionEventMissingDateTime = Assertions.assertThrows(DukeException.class, () -> {
            Parser.parse("event test /at");
        });
        assertEquals("deadline&eventWrongDescriptionFormat", actualExceptionEventMissingDateTime.getMessage());
    }

    @Test
    public void parserTest_wrongDateTimeFormat_throwException() {
        DukeException actualExceptionDeadlineWrongDateTimeFormat = Assertions.assertThrows(DukeException.class, () -> {
            Parser.parse("deadline test /by invalidDateTime");
        });
        assertEquals("DateTimeParseError", actualExceptionDeadlineWrongDateTimeFormat.getMessage());
    }

    @Test
    public void parserTest_pastDateTime_throwDukeException() {
        DukeException actualException = Assertions.assertThrows(DukeException.class, () -> {
            Parser.parse("deadline test /by 12:12 12/12/2010");
        });
        assertEquals("pastDateTime", actualException.getMessage());
    }

}
