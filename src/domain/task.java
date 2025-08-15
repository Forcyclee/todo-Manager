package domain;

public class task {
    private String title;
    private String description;
    private taskPriority priority;
    private String userID;

    public task(String title, String description, taskPriority priority, String userID) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.userID = userID;

    }

    public String getUserID() {
        return userID;
    }

    @Override
    public String toString() {
        return "Task{name='" + title + "', description='" + description + "Priority" + this.priority + "', userID='" + userID + "'}";
    }

}

