package ui;

import model.Task;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


//The button responsible for marking tasks as Complete or Incomplete.
public class StatusButton extends JPanel implements ActionListener {
    private Task task;
    private Icon incompleteIcon = new ImageIcon("data/images/EmptyCircleIcon.png");
    private Icon completeIcon = new ImageIcon("data/images/CheckMarkIcon.png");
    private JButton statusButton;

    //EFFECTS creates a StatusButton for the given task.
    public StatusButton(Task t) {
        this.task = t;

        statusButton = new JButton();
        statusButton.addActionListener(this);
        statusButton.setBorderPainted(false);
        statusButton.setBorder(null);
        statusButton.setMargin(new Insets(0, 0, 0, 0));
        statusButton.setContentAreaFilled(false);
        setStatusIcon();

    }

    public JButton getButton() {
        return this.statusButton;
    }

    //MODIFIES this
    //EFFECTS sets the appropriate Icon based on the status of the task.
    public void setStatusIcon() {
        Icon result;
        if (task.getStatus() == 2) {
            result = completeIcon;
        } else {
            result = incompleteIcon;
        }
        statusButton.setIcon(result);
    }

    //MODIFIES this
    //EFFECTS sets the status of the task, calls setStatusIcon() to update icon of button
    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.task.getStatus() == 0) {
            this.task.setComplete();
        } else {
            this.task.setIncomplete();
        }
        setStatusIcon();
    }


}

