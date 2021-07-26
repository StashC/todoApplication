package model;

import java.util.ArrayList;

public class TaskList {

    //INVARIANT, a TaskList should always be sorted Ascending Chronologically;

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

    //REQUIRES Task is Valid??
    //MODIFIES this
    //EFFECTS  adds the given task to the Tasklist, correctly Inserted chronologically
    public void addTask(Task t) {
        if (this.tasks.size() == 0 || t.getTime() >= tasks.get(tasks.size() - 1).getTime()) {
            this.tasks.add(t);
        } else {
            for (int i = 0; i <= this.tasks.size() - 1; i++) {
                if (t.getTime() < this.tasks.get(i).getTime()) {
                    this.tasks.add(i, t);
                    break;
                }
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
