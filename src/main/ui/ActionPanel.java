package ui;

import model.TaskList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//THE REMOVE TASK BUTTON IN THE ACTION BAR
public class ActionPanel implements ActionListener {
    private JButton removeButton;
    private TaskList tl;
    private int input;
    private GUI theGUI;
    private JPanel actionPanel;
    private JTextField removeText;
    private static int REMOVE_PANEL_HEIGHT = 30;
    private JButton addButton;
    private Icon addIcon = new ImageIcon("data/images/plusIcon.png");


    public ActionPanel(GUI gui) {
        this.theGUI = gui;
        this.tl = theGUI.getTaskList();
        actionPanel = new JPanel();
        actionPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
        actionPanel.setBackground(theGUI.getActionColor());
        actionPanel.setMaximumSize(new Dimension(GUI.WINDOW_WIDTH - GUI.SIDEBAR_WIDTH, REMOVE_PANEL_HEIGHT));

        removeText = new JTextField(3);
        removeButton = new TrashButton(this).getButton();
        initAddButton();

        actionPanel.add(addButton);
        actionPanel.add(removeButton, BorderLayout.EAST);
        actionPanel.add(removeText, BorderLayout.EAST);

    }

    public void remove() {
        //check if input integer - 1 is in index of set.
        input = Integer.parseInt(removeText.getText());
        if (0 <= input - 1 && input - 1 < tl.getTasks().size() && tl.getTasks().size() > 0) {
            tl.removeTask(input - 1);
            theGUI.setTaskList(this.tl);
            System.out.println("removed task " + (input - 1));
            theGUI.updateTaskPanel();
            theGUI.updateTitlePanel();
        }
    }

    private void initAddButton() {
        addButton = new JButton();
        addButton.addActionListener(this);
        addButton.setBorderPainted(false);
        addButton.setBorder(null);
        addButton.setMargin(new Insets(0, 0, 0, 0));
        addButton.setContentAreaFilled(false);
        addButton.setIcon(addIcon);
    }

    public JPanel getJPanel() {
        return this.actionPanel;
    }

    //ActionListener for JTextField
    @Override
    public void actionPerformed(ActionEvent e) {
        AddTaskWindow temp = new AddTaskWindow(theGUI);
    }
}
