package model;

import exceptions.InvalidStatusException;
import exceptions.TimeFormatException;
import org.json.JSONObject;
import persistence.Writable;

//A Task represents a task documented with a description, starting time and status.
//status represents a task's importance.  0 = incomplete.  1 = important.  2 = complete
public class Task implements Writable {
    private String description;
    private int startTime;
    private int status;
    private String timeFormat = "([0-5]\\d)|(\\d[0-5]\\d)|([01]\\d[0-5]\\d)|(2[0-3][0-5]\\d)|(2400)|\\d";


    //EFFECTS Creates a task with given description, startTime and importance
    public Task(String desc, int time, int state) throws TimeFormatException, InvalidStatusException {
        String timeString = Integer.toString(time);
        if (state != 0 && state != 1 && state != 2) {
            throw new InvalidStatusException();
        }
        if (!timeString.matches(timeFormat)) {
            throw new TimeFormatException();
        }
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
    //EFFECTS returns properly formatted string of task
    public String toString() {
        String state = "";
        if (this.getStatus() == 0) {
            state = "Incomplete";
        } else if (this.getStatus() == 1) {
            state = "IMPORTANT";
        } else {
            state = "Complete";
        }
        return (this.getDesc() + " | " + this.getTime() + " | " + state);
    }

    public int getTime() {
        return this.startTime;
    }

    public int getStatus() {
        return this.status;
    }

    public String getDesc() {
        return this.description;
    }

    @Override
    //EFFECTS Returns this Task in JSONObject format to be stored by JsonWriter.
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("description", this.description);
        json.put("start time", this.startTime);
        json.put("importance", this.status);
        return json;
    }

}
