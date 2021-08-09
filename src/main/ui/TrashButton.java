package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//A button used to Remove Tasks from the list.
public class TrashButton implements ActionListener {
    private JButton removeButton;
    private Icon removeIcon = new ImageIcon("data/images/redCross.png");
    private ActionPanel actionPanel;

    //EFFECTS creates the TrashButton object, linked to the ActionPanel.
    public TrashButton(ActionPanel apanel) {
        this.actionPanel = apanel;
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

    //MODIFIES The TaskList [GUI.taskList]
    //EFFECTS notifies action panel that the button was pressed.
    @Override
    public void actionPerformed(ActionEvent e) {
        actionPanel.remove();
    }
}
