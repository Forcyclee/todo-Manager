package manager;

import domain.task;
import domain.taskPriority;
import domain.taskStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class taskManager {
    private static  taskManager instance;
    private List<task> tasks = new ArrayList<task>();

    private taskManager() {};
    public static taskManager getInstance() {
        if (instance == null) {
            instance = new taskManager();
        }
        return instance;
    }

    /**
     * Adds a new task (currently there's no need for any verification, might change in the future)
     *
     * @param title       task title
     * @param description task description
     * @param priority    task priority
     * @param dueDate
     */
    public int createTask(String title, String description, taskPriority priority, LocalDateTime dueDate) {
        sessionManager temp = sessionManager.getInstance();
        if(temp.getCurrentUser() == null){
            return 1;
        }
        if(title.equals("")){
            return 2;
        }
        System.out.println("Creating task " + title + " " + description + " " + priority);
        tasks.add(new task(title, description, priority, temp.getCurrentUser().getUserID(), dueDate));
        return 0;
    }

    public List<task> getTasks() {
        sessionManager temp = sessionManager.getInstance();
        if(temp.getCurrentUser() == null){
            throw new IllegalStateException("Log in first before attempting this.");
        }
       return tasks.stream()
                .filter(task -> task.getUserID().equals(temp.getCurrentUser().getUserID()))
                .collect(Collectors.toList());
    }


    /**
     * Removes a task.
     * @param uuid task uuid.
     */
    public int removeTask(String uuid){
        sessionManager temp = sessionManager.getInstance();
        if(temp.getCurrentUser() == null){
            return 1; //log in first
        }
        task tempTask = tasks.stream().filter(task -> task.getTaskID().equals(uuid)).findFirst().orElse(null);
        if(tempTask == null){
            return 2; //no task with that ID
        }
        if(!tempTask.getUserID().equals(temp.getCurrentUser().getUserID())){
            return 3; //Task doesn't belong to this user
        }
        tasks.remove(tempTask);
        return 0;
    }

    /**
     *
     * Edits the task attributes.
     *
     * @param uuid
     * @param title
     * @param description
     * @param priority
     */
    public int editTask(String uuid, String title, String description, taskPriority priority, LocalDateTime deadline, taskStatus status) {
        sessionManager temp = sessionManager.getInstance();
        if(temp.getCurrentUser() == null){
            return 1; //Not logged in
        }
        task tempTask = tasks.stream().filter(task -> task.getTaskID().equals(uuid)).findFirst().orElse(null);
        if(tempTask == null){
            return 2; //No task with that ID
        }
        if(!tempTask.getUserID().equals(temp.getCurrentUser().getUserID())){
            return 3; //Task doesn't belong to this user
        }
        if(title.equals("")){
            return 4;
        }
        tempTask.editTask(title, description, priority, deadline, status);
        return 0;
    }


}
