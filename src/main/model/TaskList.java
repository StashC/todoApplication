package model;

import java.util.ArrayList;

//A TaskList is a list of Tasks which is stored using an ArrayList.
//INVARIANT, a TaskList should always be sorted Ascending Chronologically;
public class TaskList {
    private ArrayList<Task> tasks;

    //EFFECTS  creates a new empty tasklist
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    //EFFECTS returns the first non-completed task
    public Task getNextTask() {
        for (int i = 0; i <= this.tasks.size() - 1; i++) {
            if (this.tasks.get(i).getStatus() == 0 || this.tasks.get(i).getStatus() == 1) {
                return this.tasks.get(i);
            }
        }
        return null;
    }

    //REQUIRES Task has proper time formatting
    //MODIFIES this
    //EFFECTS  adds the given task to the Tasklist, correctly Inserted chronologically
    public void addTask(Task t) {
        int i = 0;
        if (this.tasks.size() == 0) {
            this.tasks.add(t);
        } else {
            //loops through tasks, inserts task before first task with later start time
            for (Task task : this.tasks) {
                if (t.getTime() < task.getTime()) {
                    this.tasks.add(i, t);
                    break;
                }
                i++;
            }
            if (t.getTime() >= tasks.get(tasks.size() - 1).getTime()) {
                this.tasks.add(t);
            }
        }
    }

    //REQUIRES 0 < index < taskList.size()
    //MODIFIES this
    //EFFECTS removes the task at index
    public void removeTask(int index) {
        this.tasks.remove(index);
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

}
