package ui;

import model.Task;
import model.TaskList;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import static javax.swing.JOptionPane.showMessageDialog;

//DayPlanner application.  The central object of the program.  Creates main JFrame/ Window and creates all main panels.
public class GUI implements ActionListener {
    //Constant Measurements
    public static final int WINDOW_WIDTH = 500;
    public static final int WINDOW_HEIGHT = 650;

    public static final int TITLE_BAR_HEIGHT = 40;

    public static final int SIDEBAR_WIDTH = 85;
    public static final int SIDEBAR_BUTTON_HEIGHT = 30;

    //COLOR DECLARATIONS
    private static Color actionPnlColor = new Color(86, 201, 255);
    private static Color bckgrndPnlColor = new Color(120, 151, 154);
    private static Color sidePnlColor = new Color(141, 136, 136);
    private static Color titlePnlColor = new Color(90, 168, 248, 255);


    private TaskList taskList;
    private JLabel dateLabel;
    private JLabel taskNumLabel;
    private JPanel sidePanel;
    private JPanel mainPanel;
    private JPanel titlePanel;
    private JPanel taskPanel;
    private JFrame frame = new JFrame();
    private JSplitPane sideDiv;
    private JPanel actionPanel;

    //Json
    private static final String JSON_PATH = "./data/tasklist.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    //sidebar buttons
    private JButton todoButton;
    private JButton saveButton;
    private JButton loadButton;
    private JButton quitButton;
    private JButton newListButton;
    private Icon todoLogo = new ImageIcon("data/images/todoLogo.png");

    //EFFECTS Sets up the JSonReader/Writer and initializes the program.  Creates the main JFrame and panels.
    public GUI() {
        jsonWriter = new JsonWriter(JSON_PATH);
        jsonReader = new JsonReader(JSON_PATH);

        initTaskList();

        actionPanel = new ActionPanel(this).getJPanel();

        initTitlePanel();
        initTaskPanel();
        initSidePanel();
        initMainPanel();

        sideDiv = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, sidePanel, mainPanel);
        sideDiv.setDividerLocation(SIDEBAR_WIDTH);
        sideDiv.setEnabled(false);
        sideDiv.setDividerSize(0);
        frame.add(sideDiv);

        frame.setTitle("ToDo");
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setResizable(false);
        //Hides window while waiting for user to create real list
        while (taskList.getDate().equals("BugFixPurposes")) {
            frame.setVisible(false);
        }
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    //MODIFIES this
    //EFFECTS attempts to load the previous TaskList.  If unsuccessful, prompts user to create a new TaskList
    private void initTaskList() {
        try {
            taskList = jsonReader.read();
            System.out.println("Retrieved TaskList " + taskList.getDate() + "!");
        } catch (Exception e) {
            System.out.println("Unable to load previous task list... ");
            //creates temp task list to keep program from crashing during setup
            taskList = new TaskList("BugFixPurposes");
            createTaskList();
        }
    }

    //MODIFIES this
    //EFFECTS creates the TaskPanel with correct background color.  Calls updateTaskPanel() to draw an initial tasks.
    public void initTaskPanel() {
        taskPanel = new JPanel();
        taskPanel.setBackground(bckgrndPnlColor);
        taskPanel.setLayout(new BoxLayout(taskPanel, BoxLayout.PAGE_AXIS));

        updateTaskPanel();
    }

    //MODIFIES this
    //EFFECTS used to display any changes to the TaskList during runtime.  Loops through TaskList and creates
    //        a new TaskPanel object for each task in the list.
    public void updateTaskPanel() {
        taskPanel.removeAll();
        taskPanel.revalidate();
        taskPanel.repaint();
        taskPanel.add(actionPanel);
        int i = 1;
        for (Task t : taskList.getTasks()) {
            TaskPanel tempPanel = new TaskPanel(t, i);
            taskPanel.add(tempPanel.getTPanel());
            System.out.println(taskList.getTasks());
            i++;
        }

    }

    //MODIFIES this
    //EFFECTS  creates the mainPanel.  mainPanel encompasses the TaskPanel, actionPanel (through taskPanel), titlePanel
    private void initMainPanel() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));

        mainPanel.add(titlePanel);
        JScrollPane scrollPane = new JScrollPane(taskPanel);
        mainPanel.add(scrollPane);
    }

    //MODIFIES this
    //EFFECTS Creates the side panel, calls initSideButtons to create the sideButtons.
    private void initSidePanel() {
        //creates the Panel
        sidePanel = new JPanel();
        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.PAGE_AXIS));
        sidePanel.setSize(SIDEBAR_WIDTH, WINDOW_HEIGHT);
        //sidePanel.setBorder(BorderFactory.createLineBorder(Color.darkGray));
        sidePanel.setBackground(sidePnlColor);

        initSideButtons();

        sidePanel.add(todoButton);
        sidePanel.add(saveButton);
        sidePanel.add(loadButton);
        sidePanel.add(newListButton);
        sidePanel.add(quitButton, BorderLayout.SOUTH);
    }

    //MODIFIES this
    //EFFECTS  creates the side buttons for the sidebar.
    private void initSideButtons() {
        Dimension buttonDimensions = new Dimension(SIDEBAR_WIDTH, SIDEBAR_BUTTON_HEIGHT);

        //Creates sidebar buttons / tabs
        todoButton = new JButton("ToDo");
        saveButton = new JButton("Save");
        loadButton = new JButton("Load");
        quitButton = new JButton("Quit");
        newListButton = new JButton("New List");

        //for style
        todoButton.setMaximumSize(buttonDimensions);
        saveButton.setMaximumSize(buttonDimensions);
        newListButton.setMaximumSize(buttonDimensions);
        loadButton.setMaximumSize(buttonDimensions);
        quitButton.setMaximumSize(buttonDimensions);
        saveButton.addActionListener(this);
        loadButton.addActionListener(this);
        newListButton.addActionListener(this);
        quitButton.addActionListener(this);

        todoButton.setBorderPainted(false);
        todoButton.setBorder(null);
        todoButton.setMargin(new Insets(0, 0, 0, 0));
        todoButton.setContentAreaFilled(false);
        todoButton.setIcon(todoLogo);
    }

    //MODIFIES this
    //EFFECTS creates the titlePanel which displays the date of the TaskList as well as # of tasks.
    private void initTitlePanel() {
        //TaskList name text
        dateLabel = new JLabel();
        dateLabel.setText(taskList.getDate());

        taskNumLabel = new JLabel();
        taskNumLabel.setText(taskList.getTasks().size() + " Tasks!");

        titlePanel = new JPanel();
        titlePanel.setLayout(new GridLayout(2, 1));
        titlePanel.setBorder(BorderFactory.createLineBorder(Color.darkGray));
        titlePanel.setBackground(titlePnlColor);
        titlePanel.add(dateLabel);
        titlePanel.add(taskNumLabel);
        titlePanel.setPreferredSize(new Dimension(WINDOW_WIDTH, TITLE_BAR_HEIGHT));
        titlePanel.setMaximumSize(new Dimension(WINDOW_WIDTH, TITLE_BAR_HEIGHT));
    }

    //MODIFIES this
    //EFFECTS handles SideButton user input
    @Override
    public void actionPerformed(ActionEvent e) {
        //Turn the event into a Jbutton, and get text from button
        JButton b = (JButton) e.getSource();
        String text = b.getText();

        if (text.equals("Save")) {
            save();
        } else if (text.equals("Load")) {
            load();
        } else if (text.equals("New List")) {
            createTaskList();
        } else if (text.equals("Quit")) {
            quit();
        }
    }

    //MODIFIES this
    //EFFECTS saves the taskList to set JPath
    public void save() {
        try {
            jsonWriter.open();
            jsonWriter.write(taskList);
            jsonWriter.close();
            showMessageDialog(null, "TaskList saved successfully");
            System.out.println("TaskList from: " + taskList.getDate() + " saved successfully!");
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_PATH);
            showMessageDialog(null, "Error: unable to write to file");
        }
    }

    //MODIFIES this
    //EFFECTS loads the taskList from set JPath
    private void load() {
        TaskList oldList = this.taskList;
        try {
            taskList = jsonReader.read();
            showMessageDialog(null, "TaskList Retrieved Successfully");
            System.out.println("Retrieved TaskList " + taskList.getDate() + "!");
        } catch (Exception e) {
            showMessageDialog(null, "Unable to load task list.");
            System.out.println("Unable to load previous task list... ");
            this.taskList = oldList;
        } finally {
            updateTitlePanel();
            updateTaskPanel();
        }
    }

    //MODIFIES this
    //EFFECTS calls for creation of new TaskListWindow
    private void createTaskList() {
        new CreateTaskListWindow(this);
    }

    //MODIFIES this
    //EFFECTS calls for creation of new QuitWindow
    private void quit() {
        new QuitWindow(this);
    }

    //MODIFIES this
    //EFFECTS updates the TitlePanel to reflect changes in the TaskList
    public void updateTitlePanel() {
        dateLabel.setText(taskList.getDate());
        taskNumLabel.setText(taskList.getTasks().size() + " Tasks!");

        titlePanel.revalidate();
        titlePanel.repaint();
    }


    public TaskList getTaskList() {
        return this.taskList;
    }

    public void setTaskList(TaskList tl) {
        this.taskList = tl;
    }

    public Color getActionColor() {
        return this.actionPnlColor;
    }

}
