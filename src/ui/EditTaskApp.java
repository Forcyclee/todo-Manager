package ui;

import domain.task;
import domain.taskPriority;
import domain.taskStatus;
import manager.taskManager;


import javax.swing.*;
import javax.swing.text.DateFormatter;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EditTaskApp {
    public EditTaskApp(task t) {

        taskManager temp = taskManager.getInstance();

        JFrame frame = new JFrame("Edit Task");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(450, 500);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);

        int y = 20;
        int gap = 40;


        JLabel titleLabel = new JLabel("Title:");
        titleLabel.setBounds(30, y, 100, 25);
        frame.add(titleLabel);

        JTextField titleField = new JTextField(t.getTitle());
        titleField.setBounds(140, y, 250, 25);
        frame.add(titleField);
        y += gap;


        JLabel priorityLabel = new JLabel("Priority:");
        priorityLabel.setBounds(30, y, 100, 25);
        frame.add(priorityLabel);

        JComboBox<String> priorityCombo = new JComboBox<>(new String[]{"LOW", "NORMAL", "HIGH"});
        priorityCombo.setSelectedItem(t.getPriority().toString());
        priorityCombo.setBounds(140, y, 250, 25);
        frame.add(priorityCombo);
        y += gap;


        JLabel statusLabel = new JLabel("Status:");
        statusLabel.setBounds(30, y, 100, 25);
        frame.add(statusLabel);

        JComboBox<String> statusCombo = new JComboBox<>(new String[]{"PENDING", "IN_PROGRESS", "COMPLETED"});
        statusCombo.setSelectedItem(t.getStatus().toString());
        statusCombo.setBounds(140, y, 250, 25);
        frame.add(statusCombo);
        y += gap;


        JLabel descriptionLabel = new JLabel("Description:");
        descriptionLabel.setBounds(30, y, 100, 25);
        frame.add(descriptionLabel);

        JTextArea descriptionArea = new JTextArea(t.getDescription());
        JScrollPane scrollPane = new JScrollPane(descriptionArea);
        scrollPane.setBounds(140, y, 250, 80);
        frame.add(scrollPane);
        y += 90;


        JLabel dueDateLabel = new JLabel("Due Date:");
        dueDateLabel.setBounds(30, y, 100, 25);
        frame.add(dueDateLabel);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        DateFormatter dateFormatter = new DateFormatter(dateFormat);

        JFormattedTextField dueDateField = new JFormattedTextField(dateFormatter);
        dueDateField.setBounds(140, y, 250, 25);
        dueDateField.setText(t.getDueDate() != null ? t.getDueDate().format(formatter) : LocalDateTime.now().format(formatter));
        frame.add(dueDateField);
        y += gap;


        JButton saveButton = new JButton("Save");
        saveButton.setBounds(30, y, 100, 30);
        saveButton.addActionListener(e -> {
            try {
                String newTitle = titleField.getText();
                taskPriority newPriority = taskPriority.valueOf((String) priorityCombo.getSelectedItem());
                taskStatus newStatus = taskStatus.valueOf((String) statusCombo.getSelectedItem());
                String newDescription = descriptionArea.getText();

                LocalDateTime newDueDate;
                try {
                    newDueDate = LocalDateTime.parse(dueDateField.getText(), formatter);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Invalid due date! Use format yyyy-MM-dd HH:mm");
                    return;
                }

                taskManager tempo = taskManager.getInstance();
                int reulst = tempo.editTask(t.getTaskID(), newTitle, newDescription, newPriority, newDueDate, newStatus);
                switch (reulst) {
                    case 0:
                        JOptionPane.showMessageDialog(null, "Task updated successfully!");
                        frame.dispose();
                        new TaskManagerApp();
                        break;
                    case 1:
                        JOptionPane.showMessageDialog(null, "Must be logged in!");
                        break;
                    case 2:
                        JOptionPane.showMessageDialog(null, "No task with that ID!");
                        break;
                    case 3:
                        JOptionPane.showMessageDialog(null, "Task doesn't belong to you!");
                        break;
                    case 4:
                        JOptionPane.showMessageDialog(null, "Task needs a name!");
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Unexpected Error!");
                        break;
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        frame.add(saveButton);


        JButton deleteButton = new JButton("Delete");
        deleteButton.setBounds(145, y, 100, 30);
        frame.add(deleteButton);

        deleteButton.addActionListener(e -> {
            int result = temp.removeTask(t.getTaskID());
            switch (result) {
                case 1 -> JOptionPane.showMessageDialog(null, "User not Logged In!");
                case 2 -> JOptionPane.showMessageDialog(null, "Task doesn't exist!");
                case 3 -> JOptionPane.showMessageDialog(null, "Task doesn't belong to you!");
                case 0 -> {
                    JOptionPane.showMessageDialog(null, "Task deleted successfully!");
                    frame.dispose();
                    new TaskManagerApp();
                }
                default -> JOptionPane.showMessageDialog(null, "Error! Please try again!");
            }
        });

        frame.setVisible(true);
    }


}
