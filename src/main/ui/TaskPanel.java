package ui;

import model.Task;

import javax.swing.*;
import java.awt.*;

import static ui.GUI.SIDEBAR_WIDTH;
import static ui.GUI.WINDOW_WIDTH;

//Given a task, creates a JPanel with the necessary buttons to display the Task to the User in the GUI
public class TaskPanel extends JPanel {
    private Task task;
    private JPanel taskPanel;
    private StatusButton statusButton;
    private ImportantButton importantButton;
    private int taskNum;

    //EFFECTS creates the JFrame, initializes taskNum and task.  Initializes and adds all components to panel.
    public TaskPanel(Task t, int tnum) {
        this.task = t;
        this.taskNum = tnum;
        int taskPanelHeight = 50;
        JLabel numLabel = new JLabel(Integer.toString(tnum));
        JPanel resultPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
        resultPanel.setBackground(new Color(166, 203, 205));
        resultPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        statusButton = new StatusButton(this.task);

        JLabel taskLabel = new JLabel();
        taskLabel.setText(displayString());
        resultPanel.setMaximumSize(new Dimension(WINDOW_WIDTH - SIDEBAR_WIDTH, taskPanelHeight));
        this.importantButton = new ImportantButton(this.task);

        resultPanel.add(numLabel);
        resultPanel.add(statusButton.getButton());
        resultPanel.add(taskLabel);
        resultPanel.add(importantButton.getButton());
        this.taskPanel = resultPanel;
    }

    //EFFECTS returns the Task's description and startTime in a formatted string
    private String displayString() {
        return this.task.getDesc() + "  |  " + this.task.getTime();
    }


    public JPanel getTPanel() {
        return this.taskPanel;
    }

}
