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

    public task(String title, String description, taskPriority priority, String userID) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.userID = userID;
        this.status = taskStatus.PENDING;
        this.taskID = UUID.randomUUID().toString();
        this.created = LocalDateTime.now();

    }

    public String getUserID() {
        return userID;
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


    @Override
    public String toString() {
        return "Task{name='" + title + "', description='" + description + "Priority" + this.priority + "', userID='" + userID + "'}";
    }

}

