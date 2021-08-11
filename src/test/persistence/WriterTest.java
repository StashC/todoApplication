package persistence;

import model.Task;
import model.TaskList;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class WriterTest extends JsonTest {

    @Test
    void testWriterNoFileFound() {
        try {
            //try to save file with illegal characters in destination name
            TaskList tl = new TaskList("My Task List");
            JsonWriter writer = new JsonWriter("./data/Illegal?,:FilName.json");
            writer.open();
            //If opened successfully, fail
            fail("Expection IO Exception");
        } catch (IOException e) {
            //pass, exception expected
        }
    }

    @Test
    void testEmptyTaskList() {
        try {
            //create new empty task list
            TaskList tl = new TaskList("March 7th");
            //save empty task list to test file location.
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyTaskList.json");
            writer.open();
            writer.write(tl);
            writer.close();

            //use already tested reader to check if writer saved correctly
            JsonReader reader = new JsonReader("./data/testWriterEmptyTaskList.json");
            tl = reader.read();
            assertEquals("March 7th", tl.getDate());
            assertEquals(0, tl.getTasks().size());
        } catch (Exception e) {
            fail("Exception note expected to be thrown");
        }
    }

    @Test
    void testNormalTaskList() {
        try {
            //creates new task list and adds two tasks
            TaskList tl = new TaskList("March 7th");
            tl.addTask(new Task("Walk my Fish", 1200, 1));
            tl.addTask((new Task("Pet Dog", 1500, 2)));
            //save task list to test file
            JsonWriter jsWriter = new JsonWriter("./data/testWriterNormalCase.json");
            jsWriter.open();
            jsWriter.write(tl);
            jsWriter.close();

            //retrieve task list from test file
            JsonReader jsReader = new JsonReader("./data/testWriterNormalCase.json");
            tl = jsReader.read();
            //check task list saved and read error free.
            assertEquals("March 7th", tl.getDate());
            assertEquals(2, tl.getTasks().size());
            checkTask("Walk my Fish", 1200, 1, tl.getTasks().get(0));
            checkTask("Pet Dog", 1500, 2, tl.getTasks().get(1));
        } catch (Exception e) {
            fail("Exception not expected");
        }

    }
}
