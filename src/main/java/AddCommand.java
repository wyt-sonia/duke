public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException{
        tasks.getTasks().add(task);
        String output = "     Got it. I've added this task: \n" +
                        "       " + task.toString() + "\n" +
                        "     Now you have " + tasks.getSize() + " tasks in the list.";
        System.out.println(output);
        storage.save(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
