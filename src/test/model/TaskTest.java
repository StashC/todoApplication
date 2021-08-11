package model;

import exceptions.InvalidStatusException;
import exceptions.TimeFormatException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//Tests the functionality of a Task
public class TaskTest {
    private Task testTask;

    @BeforeEach
    void setup() {
        try {
            testTask = new Task("test", 1200, 0);
        } catch (Exception e) {
            fail("Exception not expected");
        }
    }

    @Test
    void testCreateTask() {
        assertEquals("test", testTask.getDesc());
        assertEquals(1200, testTask.getTime());
        assertEquals(0, testTask.getStatus());

    }

    @Test
    void testSetComplete() {
        testTask.setComplete();
        assertEquals(2, testTask.getStatus());
    }

    @Test
    void testSetImportant() {
        testTask.setImportant();
        assertEquals(1, testTask.getStatus());
    }

    @Test
    void testSetIncomplete() {
        try {
            testTask = new Task("test", 1200, 2);
            testTask.setIncomplete();
            assertEquals(0, testTask.getStatus());

        } catch (Exception e) {
            fail("Exception not expected to be thrown");
        }

    }

    @Test
    void testToStringIncomplete() {
        //make sure task is set to incomplete
        testTask.setIncomplete();
        assertEquals(0, testTask.getStatus());

        String testString = testTask.toString();
        assertTrue(testString.equals(testTask.getDesc() + " | " + testTask.getTime() + " | " + "Incomplete"));
    }

    @Test
    void testToStringImportant() {
        //make sure task is set to incomplete
        testTask.setImportant();
        assertEquals(1, testTask.getStatus());

        String testString = testTask.toString();
        assertTrue(testString.equals(testTask.getDesc() + " | " + testTask.getTime() + " | " + "IMPORTANT"));
    }

    @Test
    void testToStringComplete() {
        //make sure task is set to incomplete
        testTask.setComplete();
        assertEquals(2, testTask.getStatus());

        String testString = testTask.toString();
        assertTrue(testString.equals(testTask.getDesc() + " | " + testTask.getTime() + " | " + "Complete"));
    }

    @Test
    void illegalTimeTest() {
        try {
            Task t = new Task("test", 1579, 1);
            fail("illegal time input");
        } catch (TimeFormatException e) {
            //pass
        } catch (Exception e) {
            fail("Wrong exception caught + thrown");
        }
    }

    @Test
    void illegalStatusTest() {
        try {
            Task t = new Task("desc", 1200, 5);
            fail("illegal status input");
        } catch (TimeFormatException e) {
            fail("Wrong exception caught, Time caught");
        } catch (InvalidStatusException e){
            //pass
        }
    }

}
