package persistence;

import model.Task;

import static org.junit.jupiter.api.Assertions.assertEquals;

//check that Tasks are stored and retrieved correctly from JSON
public class JsonTest {
    //Helper function used in tests involving json. Used to check if Tasks are identical.
    protected void checkTask(String desc, int startTime, int status, Task t){
        assertEquals(desc, t.getDesc());
        assertEquals(startTime, t.getTime());
        assertEquals(status, t.getStatus());
    }

}
