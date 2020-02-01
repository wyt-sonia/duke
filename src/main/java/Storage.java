import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filepath;

    public Storage(String filepath) {
        this.filepath = filepath;
    }

    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> records = new ArrayList<>();
        try{
            File f = new File(this.filepath);
            Scanner s = new Scanner(f); // create a Scanner using the File as the source
            while (s.hasNextLine()) {
                String taskRecord = s.nextLine();
                String[] recordInfo = taskRecord.split("\\|");
                Task temp = null;
                boolean isDone = false;
                if (recordInfo[1].trim().equals("1"))
                    isDone = true;
                switch (recordInfo[0].trim()) {
                    case "T":
                        temp = new ToDo(recordInfo[2].trim(), isDone);
                        break;
                    case "D":
                        temp = new Deadline(recordInfo[2].trim(), LocalDateTime.parse(recordInfo[3].trim(), Task.DATETIME_FORMAT), isDone);
                        break;
                    case "E":
                        temp = new Event(recordInfo[2].trim(), recordInfo[3].trim(), isDone);
                        break;
                }
                records.add(temp);
            }
        }
        catch (FileNotFoundException ex) {
            throw new DukeException("FileNotFound");
        }
        catch (DateTimeParseException e) {
            throw new DukeException("DateTimeParseError");
        }
        finally {
            return records;
        }
    }

    public void save(TaskList taskList) throws DukeException {
        try{
            FileWriter fw = new FileWriter(this.filepath, false);
            fw.write(taskList.getSavedString());
            fw.close();
        }
        catch (IOException ex) {
            throw new DukeException(ex.getMessage());
        }
    }
}
