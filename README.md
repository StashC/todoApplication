# Time Mapper

 My project is to create an **easy to use** time-planner.  Users will be able to
map out their schedules by creating appointments and/or tasks scheduled at given times.
Users will be able to get a breakdown of a specific day and see there scheduled tasks for the
week.

<p>The application is aimed at people who live busy lives and often find themselves
juggling many different commitments at the same time.  I find myself using 
other applications, such as Google Calendar and microsoft todo, however I 
find google calendar takes too much time to quickly make changes and 
can be overkill for something like a todo list, where as Microsoft Todo is 
too primitive and lacks the ability to schedule.  I aim to create a blend
of the two and create a user-friendly time planner.</p>
 
### Key Features

- Schedule tasks for a given time.
- View a _quick rundown_ of your day.
- Displays time until next scheduled task of the day.
- Mark tasks as completed in a todo format.
- View all scheduled tasks.

### User Stories

- As a user, I want to be able to schedule a task.
- As a user, I want to be able to remove a task.
- As a user, I want to be able to mark a task as complete or important.
- As a user, I want to be able to view a quick breakdown of my day.
- As a user, I want to be able to view all my scheduled tasks.
- As a user, I want to be able to save my to do list to a file.
- As a user, I want to be abel to load my to do list from a file.

###Phase 4: Task 2
Added 2 Exceptions dealing with illegal inputs in the Task Constructor
TimeFormatException handles any input which doesn't satisfy the new regex requirement
IllegalStatusException handles creating tasks with an illegal state.
 
This prevents the user from adding tasks which broke the old requires clause from the GUI.

###Phase 4: Task 3

Could have made a new abstract class which created the stylized buttons InterfaceButton for example,
this would create a single point of control if I wanted to change how the buttons are displayed.
I could also have another class to do this for the sidepanel buttons.  This abstraction would remove a lot of
duplicate code in the GUI class making it easier to read.  
I would also remove the StatusButton and ImportantButton class. I realized it is unnecessary to have these as 
seperate objects from the TaskPanel, and with the new abstract class for creating buttons,
it would not make the TaskPanel class too complex.  