package ui;

import model.Task;
import model.TaskList;

import javax.swing.*;
import java.awt.*;


public class GUI {
    private int count = 0;

    public static final int WINDOW_WIDTH = 500;
    public static final int WINDOW_HEIGHT = 650;

    public static final int TITLE_BAR_HEIGHT = 40;

    public static final int SIDEBAR_WIDTH = 80;
    public static final int SIDEBAR_BUTTON_HEIGHT = 30;

    private Color actionPanelColor = new Color(86, 201, 255);
    private Color bckgrndPnlColor = new Color(120, 151, 154);
    private Color sidePnlColor = new Color(141, 136, 136);
    private Color titlePnlColor = new Color(90, 168, 248, 255);


    private TaskList tl;

    private JLabel label;
    private JPanel sidePanel;
    private JPanel mainPanel;
    private JPanel titlePanel;
    private JPanel taskPanel;
    private JPanel actionPanel;
    private JFrame frame = new JFrame();
    private JSplitPane sideDiv;
    private JSplitPane splitTitle;

    public GUI() {
        //topDiv = new JSplitPane(JSplitPane.VERTICAL_SPLIT, titlePanel, )
        tl = new TaskList("March 7th");
        tl.addTask(new Task("Wheezy Outta Here", 1200, 1));
        tl.addTask(new Task("LIKE THE 3 MUSKETEERS AY AY ", 1100, 2));
        tl.addTask(new Task("I wanna die", 1300, 0));
        initTitlePanel();
        initTaskPanel();
        initSidePanel();
        initMainPanel();

        sideDiv = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, sidePanel, mainPanel);
        sideDiv.setDividerLocation(SIDEBAR_WIDTH);
        sideDiv.setEnabled(false);
        sideDiv.setDividerSize(0);
        frame.add(sideDiv);
        //frame.add(createTaskPanel(tl.getTasks().get(0)));


        //adding first = bottom layer
        // frame.add(sidePanel, BorderLayout.WEST);
        // frame.add(titlePanel, BorderLayout.NORTH);

        frame.setTitle("ToDo");
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setResizable(false);
        frame.setVisible(true);

    }

    private void initTaskPanel() {
        taskPanel = new JPanel();
        taskPanel.setBackground(bckgrndPnlColor);
        taskPanel.setLayout(new BoxLayout(taskPanel, BoxLayout.PAGE_AXIS));

        for (Task t : tl.getTasks()) {
            TaskPanel tempPanel = new TaskPanel(t);
            taskPanel.add(tempPanel.getTPanel());
        }
    }

    private void initMainPanel() {
        //!!! ADD ACTION PANEL AT TOP of main panel
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));

        mainPanel.add(titlePanel);
        //mainPanel.add(actionPanel);
        mainPanel.add(taskPanel);

    }

    private void initSidePanel() {
        Dimension buttonDimensions = new Dimension(SIDEBAR_WIDTH, SIDEBAR_BUTTON_HEIGHT);
        //creates the Panel
        sidePanel = new JPanel();
        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.PAGE_AXIS));
        sidePanel.setSize(SIDEBAR_WIDTH, WINDOW_HEIGHT);
        //sidePanel.setBorder(BorderFactory.createLineBorder(Color.darkGray));
        sidePanel.setBackground(sidePnlColor);

        //Creates sidebar buttons / tabs
        JButton todoButton = new JButton("ToDo");
        JButton saveButton = new JButton("Save");
        todoButton.setMaximumSize(buttonDimensions);
        saveButton.setMaximumSize(buttonDimensions);

        sidePanel.add(todoButton);
        sidePanel.add(saveButton);

    }

    private void initTitlePanel() {
        //TaskList name text
        JLabel dateLabel = new JLabel();
        dateLabel.setText(tl.getDate());

        JLabel taskNumLabel = new JLabel();
        taskNumLabel.setText(tl.getTasks().size() + " Tasks!");

        titlePanel = new JPanel();
        titlePanel.setLayout(new GridLayout(2, 1));
        titlePanel.setBorder(BorderFactory.createLineBorder(Color.darkGray));
        titlePanel.setBackground(titlePnlColor);
        titlePanel.add(dateLabel);
        titlePanel.add(taskNumLabel);
        titlePanel.setPreferredSize(new Dimension(WINDOW_WIDTH, TITLE_BAR_HEIGHT));
        titlePanel.setMaximumSize(new Dimension(WINDOW_WIDTH, TITLE_BAR_HEIGHT));
    }

    private void initActionPanel() {
        int barHeight = 20;
        actionPanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(actionPanelColor);

    }

}
