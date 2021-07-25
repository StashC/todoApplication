package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    private Task testTask;

    @BeforeEach
    void setup() {
        testTask = new Task("test", 1200, 0);
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
        testTask = new Task("test", 1200, 2);
        testTask.setIncomplete();
        assertEquals(0, testTask.getStatus());
    }
}
