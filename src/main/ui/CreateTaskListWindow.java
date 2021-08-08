package ui;

import model.TaskList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateTaskListWindow implements ActionListener {
    private GUI theGUI;
    private JFrame frame;
    private JPanel panel;
    private JButton confirmButton;
    private JTextField dateIn;
    private JLabel dateLabel;

    private int windowHeight = 100;
    private int windowWidth = 200;

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
        panel.add(confirmButton, BorderLayout.EAST);

        frame.setVisible(true);
    }

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
