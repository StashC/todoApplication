package persistence;

import org.json.JSONObject;

public interface Writable {
    //Taken from example project
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
