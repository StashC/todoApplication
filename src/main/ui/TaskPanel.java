package ui;

import model.Task;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static ui.GUI.SIDEBAR_WIDTH;
import static ui.GUI.WINDOW_WIDTH;

public class TaskPanel extends JPanel implements ActionListener {
    private JPanel taskPanel;
    private Task task;
    private Icon incompleteIcon = new ImageIcon("data/images/EmptyCircleIcon.png");
    private Icon importantIcon = new ImageIcon("data/images/StarIcon.png");
    private Icon completeIcon = new ImageIcon("data/images/CheckMarkIcon.png");


    public TaskPanel(Task t) {
        this.task = t;
        int statusButtonWidth = 10;
        int taskPanelHeight = 50;

        JPanel resultPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
        resultPanel.setBackground(new Color(166, 203, 205));
        resultPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JButton statusButton = new JButton();
        statusButton.setIcon(getStatusIcon());
        //statusButton.setMaximumSize(new Dimension(statusButtonWidth, taskPanelHeight));
        JLabel taskLabel = new JLabel();
        taskLabel.setText(displayString());
        //taskLabel.setPreferredSize(new Dimension(WINDOW_WIDTH - SIDEBAR_WIDTH - statusButtonWidth, taskPanelHeight));
        resultPanel.setMaximumSize(new Dimension(WINDOW_WIDTH - SIDEBAR_WIDTH, taskPanelHeight));
        resultPanel.add(statusButton);
        resultPanel.add(taskLabel);
        this.taskPanel = resultPanel;
    }

    private String displayString() {
        return this.task.getDesc() + "  |  " + this.task.getTime();
    }

    private Icon getStatusIcon() {
        Icon result;
        if (task.getStatus() == 1) {
            result = importantIcon;
        } else if (task.getStatus() == 2) {
            result = completeIcon;
        } else {
            result = incompleteIcon;
        }
        return result;
    }

    public JPanel getTPanel() {
        return this.taskPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
