package manager;

import domain.task;
import domain.taskPriority;
import domain.taskStatus;

import java.time.LocalDate;
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
     * @param title task title
     * @param description task description
     * @param priority task priority
     */
    public int createTask(String title, String description, taskPriority priority) {
        sessionManager temp = sessionManager.getInstance();
        if(temp.getCurrentUser() == null){
            return 1;
        }
        System.out.println("Creating task " + title + " " + description + " " + priority);
        tasks.add(new task(title, description, priority, temp.getCurrentUser().getUserID()));
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
     * Changes task status to the one desired
     * @param uuid task uuid.
     * @param status new task status.
     * @return 0 if successful operation.
     */
    public int changeTaskStatus(String uuid, taskStatus status){
        sessionManager temp = sessionManager.getInstance();
        if(temp.getCurrentUser() == null){
            throw new IllegalStateException("Log in first before attempting this.");
        }

        task tempTask = tasks.stream().filter(task -> task.getTaskID().equals(uuid)).findFirst().orElse(null);
        if(tempTask == null){
            throw new IllegalStateException("No task with that ID was found");
        }
        if(tempTask.getUserID().equals(temp.getCurrentUser().getUserID())){
            throw new IllegalStateException("This task doesn't belong to this user!");
        }

        tempTask.setStatus(status);
        return 0;
    }

    /**
     * Removes a task.
     * @param uuid task uuid.
     */
    public void removeTask(String uuid){
        sessionManager temp = sessionManager.getInstance();
        if(temp.getCurrentUser() == null){
            throw new IllegalStateException("Log in first before attempting this.");
        }
        task tempTask = tasks.stream().filter(task -> task.getTaskID().equals(uuid)).findFirst().orElse(null);
        if(tempTask == null){
            throw new IllegalStateException("No task with that ID was found");
        }
        if(!tempTask.getUserID().equals(temp.getCurrentUser().getUserID())){
            throw new IllegalStateException("This task doesn't belong to this user!");
        }
        tasks.remove(tempTask);
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
    public void editTask(String uuid, String title, String description, taskPriority priority, LocalDateTime deadline) {
        sessionManager temp = sessionManager.getInstance();
        if(temp.getCurrentUser() == null){
            throw new IllegalStateException("Log in first before attempting this.");
        }
        task tempTask = tasks.stream().filter(task -> task.getTaskID().equals(uuid)).findFirst().orElse(null);
        if(tempTask == null){
            throw new IllegalStateException("No task with that ID was found");
        }
        if(!tempTask.getUserID().equals(temp.getCurrentUser().getUserID())){
            throw new IllegalStateException("This task doesn't belong to this user!");
        }
        tempTask.editTask(title, description, priority, deadline);
    }

}
