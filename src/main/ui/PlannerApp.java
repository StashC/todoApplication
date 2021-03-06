package ui;


import exceptions.InvalidStatusException;
import exceptions.TimeFormatException;
import model.Task;
import model.TaskList;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

//Day planner application.  Handles the UI for the application.
public class PlannerApp {
    private static final String JSON_PATH = "./data/tasklist.json";
    private TaskList taskList;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private boolean isRunning;


    //EFFECTS: runs the application
    public PlannerApp() throws TimeFormatException, InvalidStatusException {
        jsonWriter = new JsonWriter(JSON_PATH);
        jsonReader = new JsonReader(JSON_PATH);
        runApp();
    }

    //MODIFIES: this
    //EFFECTS: processes user input and displays interface, handles user termination
    private void runApp() throws TimeFormatException, InvalidStatusException {
        isRunning = true;
        String command = null;
        setup();

        while (isRunning) {
            displayInterface();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                handleSaving();
            } else {
                handleCommand(command);
            }
        }
    }

    //MODIFIES: this
    //EFFECTS: initializes the taskList and scanner, handles loading;
    private void setup() throws TimeFormatException, InvalidStatusException {
        input = new Scanner(System.in);
        handleLoading();

    }

    private void handleLoading() throws TimeFormatException, InvalidStatusException {
        System.out.println("Would you like to load your previous list?  y/n ");
        if (input.next().equals("y")) {
            try {
                taskList = jsonReader.read();
                System.out.println("Retrieved TaskList " + taskList.getDate() + "!");
            } catch (IOException e) {
                System.out.println("Unable to load previous task list... ");
                createTaskList();
            }
        } else {
            createTaskList();
        }
    }

    //MODIFIES: this
    //EFFECTS: create new TaskList with user-input date
    private void createTaskList() {
        System.out.println("To create a new list, please enter today's date: ");
        String temp = input.nextLine();
        taskList = new TaskList(input.nextLine());
    }

    private void handleSaving() {
        System.out.println("would you like to save the current TaskList? y/n");
        String command = input.next();
        if (command.equals("y")) {
            try {
                jsonWriter.open();
                jsonWriter.write(taskList);
                jsonWriter.close();
                System.out.println("TaskList from: " + taskList.getDate() + " saved successfully!");
                isRunning = false;
            } catch (FileNotFoundException e) {
                System.out.println("Unable to write to file: " + JSON_PATH);
            }
        } else if (command.equals("n")) {
            isRunning = false;
        } else {
            System.out.println("Input Invalid...");
            handleSaving();
        }
    }


    //EFFECTS: Helper method to display the options for user input and list.
    private void displayInterface() {
        displayList();
        System.out.println();
        System.out.println("Select from:");
        System.out.println("\ta \t add new task");
        System.out.println("\tr \t remove a task");
        System.out.println("\tm \t modify task");
        System.out.println("\tn \t display next task");
        System.out.println("\tq \t quit, with/without saving");
    }

    //EFFECTS: Formats the current TaskList with task #'s and proper formatting
    private void displayList() {
        System.out.println(taskList.getDate());
        System.out.println("My Day: ");
        int num = 1;
        if (taskList.getTasks().size() != 0) {
            for (Task t : taskList.getTasks()) {
                System.out.println(num + "  " + t.toString());
                num++;
            }
        } else {
            System.out.println("No tasks have been added!");
        }
    }

    //MODIFIES: this
    //EFFECTS calls correct operation based on input command and state of taskList
    private void handleCommand(String c) throws TimeFormatException, InvalidStatusException {
        if (c.equals("a")) {
            doAddTask();
        } else if (c.equals("r")) {
            if (taskList.getTasks().size() == 0) {
                System.out.println("There are no tasks to remove. ");
            } else {
                doRemoveTask();
            }
        } else if (c.equals("m")) {
            if (taskList.getTasks().size() == 0) {
                System.out.println("There are no tasks to modify. ");
            } else {
                doUpdateTask();
            }
        } else if (c.equals("n")) {
            doDisplayNextTask();
        } else {
            System.out.println("Selection invalid...");
        }
    }

    //MODIFIES: this
    //EFFECTS: Adds a task to the taskList based on user input.
    private void doAddTask() throws TimeFormatException, InvalidStatusException {
        Task newTask;
        String desc;
        int time;
        int status;
        System.out.println("Enter task description: ");
        String temp = input.nextLine();
        desc = input.nextLine();
        System.out.println("Enter start time (hhmm) e.g 1430");
        time = input.nextInt();
        System.out.println("Is this task important? y/n");
        temp = input.nextLine();
        if (input.nextLine().equals("y")) {
            status = 1;
        } else {
            status = 0;
        }
        newTask = new Task(desc, time, status);
        taskList.addTask(newTask);
        System.out.println("Task added successfully!");
    }

    //MODIFIES: this
    //EFFECTS: Removes user-specified task from the TaskList
    private void doRemoveTask() {
        int taskNum;
        displayList();
        System.out.println("Select number to remove: ");
        taskNum = input.nextInt();
        if (taskNum > taskList.getTasks().size() || taskNum <= 0) {
            System.out.println("Invalid Selection");
        } else {
            taskList.removeTask(taskNum - 1);
            System.out.println("Task removed successfully");
        }
    }


    //MODIFIES: this
    //EFFECTS: updates the status of user-specified task to the user-specified status
    private void doUpdateTask() {
        int index;
        int statusIn;
        displayList();
        System.out.println("Select number to modify: ");
        index = input.nextInt() - 1;
        if (index > taskList.getTasks().size() || index < 0) {
            System.out.println("Invalid Selection");
        } else {
            System.out.println("Enter 0 for incomplete:\nEnter 1 for important:\nEnter 2 for complete:");
            statusIn = input.nextInt();
            if (statusIn == 0) {
                taskList.getTasks().get(index).setIncomplete();
                System.out.println("Task updated successfully");
            } else if (statusIn == 1) {
                taskList.getTasks().get(index).setImportant();
                System.out.println("Task updated successfully");
            } else if (statusIn == 2) {
                taskList.getTasks().get(index).setComplete();
                System.out.println("Task updated successfully");
            } else {
                System.out.println("Invalid Selection");
            }
        }
    }

    //EFFECTS: displays the first non-completed task in list
    private void doDisplayNextTask() {
        if (taskList.getNextTask() == null) {
            System.out.println("All tasks have been completed!  WooHoo!");
        } else {
            System.out.println("Next Task is: \n");
            System.out.println(taskList.getNextTask().toString());
        }
    }
}


