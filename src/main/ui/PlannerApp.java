package ui;


import model.Task;
import model.TaskList;

import java.util.Scanner;

//Day planner application
public class PlannerApp {

    private TaskList taskList;
    private Scanner input;

    //EFFECTS: runs the application
    public PlannerApp() {
        runApp();
    }

    //MODIFIES: this
    //EFFECTS: processes user input
    private void runApp() {
        boolean isRunning = true;
        String command = null;

        setup();

        while (isRunning) {
            displayInterface();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                isRunning = false;
            } else {
                handleCommand(command);
            }
        }
    }

    //MODIFIES: this
    //EFFECTS: initializes the program
    private void setup() {
        taskList = new TaskList();
        input = new Scanner(System.in);
    }

    private void displayInterface() {
        displayList();
        System.out.println();
        System.out.println("Select from: ");
        System.out.println("\ta \t add new task");
        System.out.println("\tr \t remove a task");
        System.out.println("\tm \t modify task");
        System.out.println("\tn \t display next task");
        System.out.println("\tq \t quit");
    }

    //!!!!!!
    private void displayList() {
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

    //EFFECTS calls correct operation based on input command and state of taskList
    private void handleCommand(String c) {
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

    //!!!
    private void doAddTask() {
        Task newTask;
        String desc;
        int time;
        int status;
        System.out.println("Enter task description: ");
        String blank = input.nextLine();
        desc = input.nextLine();
        System.out.println("Enter start time (hhmm) e.g 1430");
        time = input.nextInt();
        System.out.println("Is this task important? y/n");
        if (input.next() == "y") {
            status = 1;
        } else {
            status = 0;
        }
        newTask = new Task(desc, time, status);
        taskList.addTask(newTask);
        System.out.println("Task added successfully!");
    }

    //!!!
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


    //!!!
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


