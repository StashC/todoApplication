package ui;

import model.Task;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class StatusButton extends JPanel implements ActionListener {
    private Task task;
    private Icon incompleteIcon = new ImageIcon("data/images/EmptyCircleIcon.png");
    private Icon completeIcon = new ImageIcon("data/images/CheckMarkIcon.png");
    private JButton statusButton;

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

    public void setStatusIcon() {
        Icon result;
        if (task.getStatus() == 2) {
            result = completeIcon;
        } else {
            result = incompleteIcon;
        }
        statusButton.setIcon(result);
    }

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

