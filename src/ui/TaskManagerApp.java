package ui;

import manager.sessionManager;
import manager.taskManager;

import javax.swing.*;
import java.awt.*;

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

        frame.add(headerPanel, BorderLayout.NORTH);

        frame.setVisible(true);

    }
}
