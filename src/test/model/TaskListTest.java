package model;

import exceptions.InvalidStatusException;
import exceptions.TimeFormatException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

//Tests the functionality of a TaskList
public class TaskListTest {
    private TaskList testTaskList;
    private ArrayList<Task> taskList;


    private Task task1;
    private Task task2;

    //Checks if two Tasks are Equivalent, i.e same Desc, Time Status.
    private Boolean isEqual(Task t1, Task t2) {
        if (t1.getDesc() == t2.getDesc() && t1.getTime() == t2.getTime()
                && t1.getStatus() == t2.getStatus()) {
            return true;
        } else {
            return false;
        }
    }

    @BeforeEach
    void setup() {
        //creates a new TaskList() before each test, already sorted.
        try {
            task1 = new Task("task1", 1300, 0);
            task2 = new Task("task2", 1400, 0);
        } catch (TimeFormatException e) {
            fail("TimeFormat thrown");
        } catch (InvalidStatusException e){
            fail("Invalid Status thrown");
        }
        testTaskList = new TaskList("March 7th");
        testTaskList.addTask(task1);
        testTaskList.addTask(task2);

        taskList = testTaskList.getTasks();
    }

    @Test
    void testIsEqualTrue() {
        assertTrue(isEqual(task1, task1));
    }

    @Test
    void testIsEqualFail() {
        assertFalse(isEqual(task1, task2));
    }

    @Test
    void testGetNextTaskFirst() {
        assertTrue(isEqual(task1, testTaskList.getNextTask()));
    }

    @Test
    void testGetNextTaskLast() {
        testTaskList.getTasks().get(0).setComplete();
        assertTrue(isEqual(task2, testTaskList.getNextTask()));
    }

    @Test
    void testGetNextImportant() {
        testTaskList.getTasks().get(0).setImportant();
        assertTrue(isEqual(testTaskList.getNextTask(), task1));
    }

    @Test
    void testGetNextAllComplete() {
        testTaskList.getTasks().get(0).setComplete();
        testTaskList.getTasks().get(1).setComplete();
        assertEquals(null, testTaskList.getNextTask());
    }

    @Test
    void testAddTaskStart() {
        try {
            Task testTask = new Task("test", 1200, 0);
            testTaskList.addTask(testTask);

            assertTrue(isEqual(taskList.get(0), testTask));
            assertTrue(isEqual(taskList.get(1), task1));
            assertTrue(isEqual(taskList.get(2), task2));
        } catch (Exception e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testAddTaskMiddle() {
        try {
            Task testTask = new Task("test", 1330, 0);
            testTaskList.addTask(testTask);

            assertTrue(isEqual(taskList.get(0), task1));
            assertTrue(isEqual(taskList.get(1), testTask));
            assertTrue(isEqual(taskList.get(2), task2));
        } catch (Exception e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testAddTaskEnd() {
        try {
            Task testTask = new Task("test", 1900, 0);
            testTaskList.addTask(testTask);

            assertEquals(3, taskList.size());
            assertTrue(isEqual(taskList.get(0), task1));
            assertTrue(isEqual(taskList.get(1), task2));
            assertTrue(isEqual(taskList.get(2), testTask));
        } catch (Exception e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
        //TEST ADD MULTIPLE X TO Y
    void testAddMany() {
        try {
            Task test1 = new Task("test1", 800, 0);
            Task test2 = new Task("test2", 1330, 1);
            Task test3 = new Task("test3", 2100, 2);
            testTaskList.addTask(test1);
            testTaskList.addTask(test2);
            testTaskList.addTask(test3);
            //list should be  test1 task1 test2 task2 test3
            assertTrue(isEqual(test1, testTaskList.getTasks().get(0)));
            assertTrue(isEqual(task1, testTaskList.getTasks().get(1)));
            assertTrue(isEqual(test2, testTaskList.getTasks().get(2)));
            assertTrue(isEqual(task2, testTaskList.getTasks().get(3)));
            assertTrue(isEqual(test3, testTaskList.getTasks().get(4)));
            assertEquals(testTaskList.getTasks().size(), 5);
        } catch (Exception e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testRemoveTaskBeginning() {
        //list should be [task1, task2]
        testTaskList.removeTask(0);
        assertEquals(1, testTaskList.getTasks().size());
        assertTrue(isEqual(taskList.get(0), task2));
    }

    @Test
    void testRemoveTaskMiddle() {
        try {
            Task testTask = new Task("test", 1100, 0);
            testTaskList.addTask(testTask);

            //list should be [testTask, task1, task2]
            testTaskList.removeTask(1);
            assertEquals(2, testTaskList.getTasks().size());
            assertTrue(isEqual(taskList.get(0), testTask));
            assertTrue(isEqual(taskList.get(1), task2));
        } catch (Exception e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testRemoveTaskEnd() {
        //list should be [task1, task2]
        testTaskList.removeTask(1);
        assertEquals(1, testTaskList.getTasks().size());
        assertTrue(isEqual(taskList.get(0), task1));
    }

}
