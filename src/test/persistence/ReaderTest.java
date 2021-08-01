package persistence;

import model.TaskList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class ReaderTest extends JsonTest {
    JsonReader reader;


    @Test
    void testReaderFileNotFound() {
        //try to read file from non-existent destination
        JsonReader reader = new JsonReader("./data/fakeFile.json");
        try {
            TaskList tl = reader.read();
            fail("Expected exception thrown");
        } catch (IOException e) {
            //Test passed
        }
    }

    @Test
    void testEmptyTaskList() {
        //read premade empty task list.
        JsonReader reader = new JsonReader("./data/EmptyTaskListTest.json");
        try {
            //check retrieved correctly.
            TaskList tl = reader.read();
            assertEquals("EmptyTaskList", tl.getDate());
            assertEquals(0, tl.getTasks().size());
        } catch (IOException e) {
            fail("Shouldn't fail, file should be readable. ");
        }
    }

    @Test
    void testNormalTaskList() {
        //read pre-made non-empty TaskList
        JsonReader reader = new JsonReader("./data/NormalTaskListTest.json");
        try {
            //checks to make sure reader is reading correct date and tasks.
            TaskList tl = reader.read();
            assertEquals("March 7th", tl.getDate());
            assertEquals(2, tl.getTasks().size());
            checkTask("Pet dog", 1200, 1, tl.getTasks().get(0));
            checkTask("Walk Fish", 1500, 0, tl.getTasks().get(1));

        } catch (IOException e) {
            fail("shouldn't fail, file should be readable ");
        }
    }
}