package ui;

import model.Task;
import model.TaskList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//A window with JTextFields to handle user input which creates and adds a new text to the list.
public class AddTaskWindow implements ActionListener {
    private JFrame frame;
    private JPanel panel;
    private JButton confirmButton;
    private JTextField descIn;
    private JTextField timeIn;
    private JCheckBox isImportant;
    private GUI theGUI;

    private int windowHeight = 125;
    private int windowWidth = 300;

    //EFFECTS creates the AddTaskWindow.  Creates the JFrame and all the components required.
    public AddTaskWindow(GUI gui) {
        this.theGUI = gui;
        panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));
        frame = new JFrame();
        frame.setSize(windowWidth, windowHeight);
        frame.setTitle("Add Task");
        frame.setResizable(false);
        frame.add(panel);

        descIn = new JTextField(30);
        timeIn = new JTextField(4);

        JLabel descLabel = new JLabel("Enter Description:");
        JLabel timeLabel = new JLabel("Enter Start Time [hhmm]:");
        confirmButton = new JButton("Add Task");
        confirmButton.addActionListener(this);
        isImportant = new JCheckBox("Is this important?");

        panel.add(descLabel);
        panel.add(descIn);
        panel.add(timeLabel);
        panel.add(timeIn);
        panel.add(isImportant);
        panel.add(confirmButton);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    //MODIFIES this
    //EFFECTS if input fields are null, does nothing on button press.  Else, creates and adds a new task to the list
    //        based on user input.  Closes window after task is created and added.
    @Override
    public void actionPerformed(ActionEvent e) {
        //make sure each box is filled
        if (!descIn.getText().equals("") && !timeIn.getText().equals("")) {
            int status = 0;
            if (isImportant.isSelected()) {
                status = 1;
            }
            //create task with input and add to list.
            TaskList tempList = theGUI.getTaskList();
            Task tempTask = new Task(descIn.getText(), Integer.parseInt(timeIn.getText()), status);
            tempList.addTask(tempTask);
            System.out.println("Task added successfully");
            theGUI.updateTaskPanel();
            theGUI.updateTitlePanel();
            frame.dispose();
        }

    }
}
