public class ShowListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String output = "";
        if (tasks.getSize() == 0) {
            ui.showErrorMessage(new DukeException("emptyList"));
        } else {
            for (Task t : tasks.getTasks()) {
                int counter = tasks.getTasks().indexOf(t);
                output += "       " +  (counter + 1) + "." + t.toString();
                if (tasks.getSize() - counter > 1) output += "\n";
            }
        }
        ui.displayOutput(output);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
