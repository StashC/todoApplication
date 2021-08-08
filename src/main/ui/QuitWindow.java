package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuitWindow implements ActionListener {

    private GUI theGUI;
    private JPanel panel;
    private JFrame frame;
    private JButton yesButton;
    private JButton noButton;

    private int windowWidth = 200;
    private int windowHeight = 100;

    public QuitWindow(GUI gui) {
        this.theGUI = gui;

        frame = new JFrame();
        frame.setTitle("Save?");
        frame.setResizable(false);
        frame.setSize(windowWidth, windowHeight);

        panel = new JPanel();
        JLabel prompt = new JLabel("Would you like to save?");
        panel.add(prompt);


        yesButton = new JButton("yes");
        noButton = new JButton("no");
        yesButton.addActionListener(this);
        noButton.addActionListener(this);

        panel.add(noButton);
        panel.add(yesButton);

        frame.add(panel);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Turn the event into a Jbutton, and get text from button
        JButton b = (JButton) e.getSource();
        String text = b.getText();
        if (text.equals("yes")) {
            theGUI.save();
            System.out.println("exited with saving");
            System.exit(0);
        } else if (text.equals("no")) {
            System.out.println("Exited without saving");
            System.exit(0);
        }
    }
}
