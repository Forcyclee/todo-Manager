package ui;

import domain.task;
import manager.sessionManager;
import manager.taskManager;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TaskManagerApp {
    public TaskManagerApp() {
        JFrame frame = new JFrame("TaskManager App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(230, 230, 250));
        headerPanel.setPreferredSize(new Dimension(frame.getWidth(), 60));

        JLabel title = new JLabel("Task Manager App");
        title.setFont(new Font("Arial", Font.BOLD, 18));
        title.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 10));
        headerPanel.add(title, BorderLayout.WEST);

        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        rightPanel.setOpaque(false);

        JButton newTaskButton = new JButton("New Task");
        newTaskButton.setFocusPainted(false);
        newTaskButton.setBackground(new Color(60, 179, 113));
        newTaskButton.setForeground(Color.WHITE);
        newTaskButton.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));

        JButton logoutButton = new JButton("Logout");
        logoutButton.setFocusPainted(false);
        logoutButton.setBackground(new Color(220, 20, 60));
        logoutButton.setForeground(Color.WHITE);
        logoutButton.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));

        logoutButton.addActionListener(e -> {
            frame.dispose();
            sessionManager temp = sessionManager.getInstance();
            temp.logout();
            new LogInApp();
        });

        newTaskButton.addActionListener(e -> {
            frame.dispose();
            new CreateTaskApp();

        });

        rightPanel.add(newTaskButton);
        rightPanel.add(logoutButton);
        headerPanel.add(rightPanel, BorderLayout.EAST);



        JPanel taskBoard = new JPanel();
        taskBoard.setLayout(new BoxLayout(taskBoard, BoxLayout.Y_AXIS));
        taskBoard.setBackground(new Color(245, 245, 245));

        taskManager temp = taskManager.getInstance();
        List<task> tasks = temp.getTasks();

        for (task t : tasks) {
            JPanel card = new JPanel();
            card.setLayout(new BorderLayout());
            card.setPreferredSize(new Dimension(750, 80)); // FIXED height
            card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80)); // max width expandable
            card.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
            card.setBackground(Color.WHITE);

            JLabel taskTitle = new JLabel(t.getTitle());
            taskTitle.setFont(new Font("Arial", Font.BOLD, 14));
            taskTitle.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

            JButton editButton = new JButton("Edit");
            editButton.addActionListener(e -> {
                // new EditTaskApp(t);
            });

            card.add(taskTitle, BorderLayout.CENTER);
            card.add(editButton, BorderLayout.EAST);

            taskBoard.add(Box.createVerticalStrut(10));
            taskBoard.add(card);
        }

        JScrollPane scrollPane = new JScrollPane(taskBoard);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        frame.add(scrollPane, BorderLayout.CENTER);







        frame.add(headerPanel, BorderLayout.NORTH);

        frame.setVisible(true);

    }
}
