package ui;

import model.TaskList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//A class for a window which prompts the user to enter a Date and creates a task list with the given date.
public class CreateTaskListWindow implements ActionListener {
    private GUI theGUI;
    private JFrame frame;
    private JPanel panel;
    private JButton confirmButton;
    private JTextField dateIn;
    private JLabel dateLabel;

    private int windowHeight = 100;
    private int windowWidth = 200;

    //EFFECTS sets this.theGUI to given gui.  Creates and adds all components to a new JFrame.
    public CreateTaskListWindow(GUI gui) {
        this.theGUI = gui;

        panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2));

        frame = new JFrame();
        frame.setSize(windowWidth, windowHeight);
        frame.setTitle("New Task List");
        frame.setResizable(false);
        frame.add(panel);

        dateLabel = new JLabel("Enter the date: ");
        dateIn = new JTextField(20);
        confirmButton = new JButton("Confirm");
        confirmButton.addActionListener(this);

        panel.add(dateLabel);
        panel.add(dateIn);
        //empty component for formatting
        panel.add(new JLabel());
        panel.add(confirmButton);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    //MODIFIES this
    //EFFECTS Creates a new TaskList with input date.  If input field is empty, does nothing.
    @Override
    public void actionPerformed(ActionEvent e) {
        if (!dateIn.getText().equals("")) {
            theGUI.setTaskList(new TaskList(dateIn.getText()));
            theGUI.updateTaskPanel();
            theGUI.updateTitlePanel();
            frame.dispose();
            System.out.println("this worked");
        }
    }
}
