package persistence;

import model.Task;
import model.TaskList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

//a reader which can read a TaskList from a JSON data file
public class JsonReader {
    private String src;

    //EFFECTS constructs a read to read from source file
    public JsonReader(String source) {
        this.src = source;
    }

    //EFFECTS: reads and returns TaskList
    //throws IOException if error occurs reading
    public TaskList read() throws IOException {
        String data = readFile(src);
        JSONObject jsobject = new JSONObject(data);
        return parseTaskList(jsobject);
    }

    //EFFECTS: reads and returns source file as string
    private String readFile(String source) throws IOException {
        StringBuilder tlBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach((t -> tlBuilder.append(t)));
        }
        return tlBuilder.toString();
    }

    //EFFECTS pareses and creates TaskList from JSONObject and returns it
    //        Gets the date of the stored task list
    private TaskList parseTaskList(JSONObject jsObject) {
        String date = jsObject.getString("Date:");
        TaskList tl = new TaskList(date);
        addTasks(tl, jsObject);
        return tl;
    }

    //EFFECTS parses the stored list of tasks from the JSONObject and adds to TaskList.
    private void addTasks(TaskList tl, JSONObject jsObject) {
        JSONArray jsArray = jsObject.getJSONArray("TaskList");
        for (Object js : jsArray) {
            JSONObject nextTask = (JSONObject) js;
            addTask(tl, nextTask);
        }
    }

    //EFFECTS gets task from given JSObject and adds it to input TaskList.  Works in tandem with addTasks()
    private void addTask(TaskList tl, JSONObject jsObject) {
        String desc = jsObject.getString("description");
        int startTime = jsObject.getInt("start time");
        int status = jsObject.getInt("importance");
        Task t = new Task(desc, startTime, status);
        tl.addTask(t);
    }

}
