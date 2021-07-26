package model;

public class Task {
    private String description;
    private int startTime;
    //status represents a task's importance.  0 = incomplete.  1 = important.  2 = complete
    private int status;

    //REQUIRES  desc has non-zero length, time is in valid 24hr format (hhmm) e.g 1200
    //          state is either 0, 1 or 2
    //EFFECTS Creates a task with given description, startTime and importance
    public Task(String desc, int time, int state) {
        this.description = desc;
        this.startTime = time;
        this.status = state;
    }

    //MODIFIES this
    //EFFECTS sets Task's status to incomplete
    public void setIncomplete() {
        this.status = 0;
    }

    //MODIFIES this
    //EFFECTS sets Task's status to important
    public void setImportant() {
        this.status = 1;
    }

    //MODIFIES this
    //EFFECTS sets Task's status to complete
    public void setComplete() {
        this.status = 2;
    }

    @Override
    //EFFECTS: returns properly formatted string of task
    public String toString() {
        String state = "";
        if (this.getStatus() == 0) {
            state = "Incomplete";
        } else if (this.getStatus() == 1) {
            state = "IMPORTANT";
        } else {
            state = "Complete";
        }
        return (this.getDesc() + this.getTime() + state);
    }

    public int getTime() {
        return this.startTime;
    }

    public int getStatus() {
        return this.status;
    }

    //REQUIRES time input is in proper format indicated by console
    public void setStartTime(int time) {
        this.startTime = time;
    }

    public String getDesc() {
        return this.description;
    }

}
