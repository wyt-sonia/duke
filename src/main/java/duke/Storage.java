package duke;

import duke.task.Task;
import duke.task.TaskList;
import duke.task.Event;
import duke.task.ToDo;
import duke.task.Deadline;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filepath;

    public Storage(String filepath) {
        this.filepath = filepath;
    }

    public ArrayList<Task> load() {
        ArrayList<Task> records = new ArrayList<>();
        try {
            File f = new File(this.filepath);
            Scanner s = new Scanner(f); // create a Scanner using the File as the source
            while (s.hasNextLine()) {
                String taskRecord = s.nextLine();
                String[] recordInfoParts = taskRecord.split("\\|");
                Task temp = null;
                boolean isDone = false;
                if (recordInfoParts[1].trim().equals("1")) {
                    isDone = true;
                }
                switch (recordInfoParts[0].trim()) {
                case "T":
                    temp = new ToDo(recordInfoParts[2].trim(), isDone);
                    break;
                case "D":
                    temp = new Deadline(recordInfoParts[2].trim(), LocalDateTime.parse(recordInfoParts[3].trim(),
                            Task.DATETIME_FORMAT), isDone);
                    break;
                case "E":
                    temp = new Event(recordInfoParts[2].trim(), recordInfoParts[3].trim(), isDone);
                    break;
                default:
                }
                records.add(temp);
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } finally {
            return records;
        }
    }

    public void save(TaskList taskList) throws DukeException {
        try {
            FileWriter fw = new FileWriter(this.filepath, false);
            fw.write(taskList.getSavedString());
            fw.close();
        } catch (IOException ex) {
            throw new DukeException(ex.getMessage());
        }
    }
}
