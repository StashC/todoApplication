package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TrashButton implements ActionListener {
    private JButton removeButton;
    private Icon removeIcon = new ImageIcon("data/images/redCross.png");
    private ActionPanel actionPanel;

    public TrashButton(ActionPanel rpanel) {
        this.actionPanel = rpanel;
        removeButton = new JButton("");
        removeButton.addActionListener(this);
        removeButton.setBorderPainted(false);
        removeButton.setBorder(null);
        removeButton.setMargin(new Insets(0, 0, 0, 0));
        removeButton.setContentAreaFilled(false);
        removeButton.setIcon(removeIcon);
    }

    public JButton getButton() {
        return this.removeButton;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        actionPanel.remove();
    }
}
