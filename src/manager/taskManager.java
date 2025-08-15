package manager;

import domain.task;
import domain.taskPriority;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class taskManager {
    private List<task> tasks = new ArrayList<task>();

    /**
     * Adds a new task (currently there's no need for any verification, might change in the future)
     * @param title task title
     * @param description task description
     * @param priority task priority
     */
    public void createTask(String title, String description, taskPriority priority, String userId) {
        System.out.println("Creating task " + title + " " + description + " " + priority);
        tasks.add(new task(title, description, priority, userId));
    }

    public List<task> getTasks(String userId) {
        return tasks.stream()
                .filter(task -> task.getUserID().equals(userId))
                .collect(Collectors.toList());
    }
}
