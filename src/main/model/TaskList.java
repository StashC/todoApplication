package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

//A TaskList is a list of Tasks which is stored using an ArrayList.
//INVARIANT, a TaskList should always be sorted Ascending Chronologically;
public class TaskList implements Writable {
    private ArrayList<Task> tasks;
    private String date;

    //EFFECTS  creates a new empty tasklist
    public TaskList(String date) {
        this.date = date;
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

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Date:", date);
        //turn the actual list of tasks into a JSONArray
        json.put("TaskList", tasksToJson());
        return json;
    }

    //EFFECTS: returns the tasks in the list as a formatted JSONArray
    private JSONArray tasksToJson() {
        JSONArray jsarray = new JSONArray();

        for (Task t : this.tasks) {
            jsarray.put(t.toJson());
        }
        return jsarray;
    }

    //EFFECTS: returns the date as string
    public String getDate() {
        return this.date;
    }

}
