package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {
    private TaskList testTaskList;
    private ArrayList<Task> taskList;

    private Task task1 = new Task("task1", 1300, 0);
    private Task task2 = new Task("task2", 1400, 0);

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
        testTaskList = new TaskList();
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
    void testGetNextTask() {
        assertTrue(isEqual(task1, testTaskList.getNextTask()));
    }

    @Test
    void testAddTaskStart() {
        Task testTask = new Task("test", 1200, 0);
        testTaskList.addTask(testTask);

        assertTrue(isEqual(taskList.get(0), testTask));
        assertTrue(isEqual(taskList.get(1), task1));
        assertTrue(isEqual(taskList.get(2), task2));
    }

    @Test
    void testAddTaskMiddle() {
        Task testTask = new Task("test", 1330, 0);
        testTaskList.addTask(testTask);

        assertTrue(isEqual(taskList.get(0), task1));
        assertTrue(isEqual(taskList.get(1), testTask));
        assertTrue(isEqual(taskList.get(2), task2));
    }

    @Test
    void testAddTaskEnd() {
        Task testTask = new Task("test", 1900, 0);
        testTaskList.addTask(testTask);

        assertEquals(3, taskList.size());
        assertTrue(isEqual(taskList.get(0), task1));
        assertTrue(isEqual(taskList.get(1), task2));
        assertTrue(isEqual(taskList.get(2), testTask));
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
        Task testTask = new Task("test", 1100, 0);
        testTaskList.addTask(testTask);

        //list should be [testTask, task1, task2]
        testTaskList.removeTask(1);
        assertEquals(2, testTaskList.getTasks().size());
        assertTrue(isEqual(taskList.get(0), testTask));
        assertTrue(isEqual(taskList.get(1), task2));
    }

    @Test
    void testRemoveTaskEnd() {
        //list should be [task1, task2]
        testTaskList.removeTask(1);
        assertEquals(1, testTaskList.getTasks().size());
        assertTrue(isEqual(taskList.get(0), task1));
    }

}