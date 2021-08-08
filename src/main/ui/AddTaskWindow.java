package ui;

import model.Task;
import model.TaskList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        frame.setVisible(true);
    }

    public JFrame getFrame() {
        return this.frame;
    }

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
