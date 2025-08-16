import domain.task;
import domain.taskPriority;
import manager.taskManager;
import manager.userManager;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws Exception {
        userManager teste  = new userManager();
        taskManager testeTask = new taskManager();
        teste.register("Diogo", "gugugaga", "diogogomecardoso", "Diogo", "Cardoso", LocalDate.now());
        teste.login("Diogo", "gugugaga");
        Scanner sc = new Scanner(System.in);
        String userID = sc.nextLine();
        testeTask.createTask("Reuniao", "Reuniao de condominio", taskPriority.HIGH, userID);
        testeTask.createTask("Reuniao 2", "Reuniao de condominio 2", taskPriority.HIGH, userID);
        List<task> tasks = testeTask.getTasks();
        for (task task : tasks) {
            System.out.println(task.toString());
        }
        String taskID = sc.nextLine();
        testeTask.removeTask(taskID);
        tasks = testeTask.getTasks();

        for (task task : tasks) {
            System.out.println(task.toString());
        }

    }
}