package ui;

import model.Task;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ImportantButton extends JPanel implements ActionListener {
    private Task task;
    private Icon notImportantIcon = new ImageIcon("data/images/EmptyStarIcon.png");
    private Icon importantIcon = new ImageIcon("data/images/StarIcon.png");
    private JButton importantButton;

    public ImportantButton(Task t) {
        this.task = t;
        this.importantButton = new JButton();
        importantButton.setBorderPainted(false);
        importantButton.setBorder(null);
        importantButton.setMargin(new Insets(0, 0, 0, 0));
        importantButton.setContentAreaFilled(false);
        this.importantButton.setIcon(importantIcon);
        setImportantIcon();
        this.importantButton.addActionListener(this);
    }

    public JButton getButton() {
        return this.importantButton;
    }

    public void setImportantIcon() {
        Icon result;
        if (this.task.getStatus() == 1) {
            result = importantIcon;
        } else {
            result = notImportantIcon;
        }
        importantButton.setIcon(result);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (importantButton.getIcon() == notImportantIcon) {
            importantButton.setIcon(importantIcon);
        } else {
            importantButton.setIcon(notImportantIcon);
        }
    }
}
