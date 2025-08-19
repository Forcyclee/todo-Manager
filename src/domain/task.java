package domain;

import java.time.LocalDateTime;
import java.util.UUID;

public class task {
    private String title;
    private String description;
    private taskPriority priority;
    private String userID;
    private taskStatus status;
    private String taskID;
    private LocalDateTime created;
    private LocalDateTime completed;
    private LocalDateTime deadline;
    private LocalDateTime lastUpdated;

    public task(String title, String description, taskPriority priority, String userID,  LocalDateTime deadline) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.userID = userID;
        this.status = taskStatus.PENDING;
        this.taskID = UUID.randomUUID().toString();
        this.created = LocalDateTime.now();
        this.deadline = deadline;
        System.out.println("TaskId: " + this.taskID);

    }

    public taskStatus getStatus() {
        return status;
    }

    public String getUserID() {
        return userID;
    }

    public taskPriority getPriority() {
        return priority;
    }

    public String getTaskID() {
        return this.taskID;
    }
    public String getTitle() {
        return title;
    }

    public void setStatus(taskStatus status) {
        this.lastUpdated = LocalDateTime.now();
        this.status = status;
    }

    public void editTask(String title, String description, taskPriority priority, LocalDateTime deadline, taskStatus status) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.deadline = deadline;
        this.lastUpdated = LocalDateTime.now();
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getDueDate() {
        return this.deadline;
    }


    @Override
    public String toString() {
        return "Task{name='" + title + "', description='" + description + "Priority" + this.priority + "', userID='" + userID + "'}";
    }

}

