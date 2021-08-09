package persistence;

import model.TaskList;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;


    /*  Acknowledgement:
    /   This JsonReader class was modeled from the JsonSerializationDemo example class.
    */

// A class which stores a TaskList in json
public class JsonWriter {
    //how many spaces in a TAB
    private static final int TAB = 4;
    private PrintWriter writer;
    private String fileName;

    //EFFECTS sets writer to write to given filename
    public JsonWriter(String dest) {
        this.fileName = dest;
    }

    //MODIFIES this
    //EFFECTS opens destination, throws FileNotFoundException if target file can not be opened
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(fileName));
    }

    //MODIFIES: this
    //EFFECTS: closes json writer
    public void close() {
        writer.close();
    }

    //MODIFIES this
    //EFFECTS writes a TaskList as JSON to fileName.
    public void write(TaskList tl) {
        //Converts TaskLit representation to json format
        JSONObject json = tl.toJson();
        //saves json to file as string
        save(json.toString(TAB));
    }

    //MODIFIES this
    //EFFECTS saves the JSON string to file
    private void save(String json) {
        writer.print(json);
    }
}
