package ui;

import domain.taskPriority;
import manager.taskManager;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CreateTaskApp {

    public CreateTaskApp() {
        JFrame frame = new JFrame("Create Task");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);

        JLabel titleLabel = new JLabel("Title:");
        titleLabel.setBounds(30, 20, 100, 25);
        JTextField titleField = new JTextField();
        titleField.setBounds(140, 20, 200, 25);

        JLabel descriptionLabel = new JLabel("Description:");
        descriptionLabel.setBounds(30, 60, 100, 25);
        JTextArea descriptionArea = new JTextArea();
        descriptionArea.setBounds(140, 60, 200, 100);
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);

        JLabel priorityLabel = new JLabel("Priority:");
        priorityLabel.setBounds(30, 180, 100, 25);
        JComboBox<taskPriority> priorityBox = new JComboBox<>(taskPriority.values());
        priorityBox.setBounds(140, 180, 200, 25);

        JLabel dueDateLabel = new JLabel("Due Date:");
        dueDateLabel.setBounds(30, 220, 100, 25);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        JFormattedTextField dueDateField = null;
        try {
            MaskFormatter mask = new MaskFormatter("####-##-## ##:##");
            mask.setPlaceholderCharacter('_');
            dueDateField = new JFormattedTextField(mask);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        dueDateField.setBounds(140, 220, 200, 25);
        dueDateField.setValue(LocalDateTime.now().format(formatter));

        JButton saveButton = new JButton("Save Task");
        saveButton.setBounds(140, 310, 120, 30);
        JFormattedTextField finalDueDateField = dueDateField;

        saveButton.addActionListener(e -> {
            String title = titleField.getText();
            String description = descriptionArea.getText();
            taskPriority priority = (taskPriority) priorityBox.getSelectedItem();

            LocalDateTime dueDate;
            try {
                dueDate = LocalDateTime.parse(finalDueDateField.getText(), formatter);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Invalid due date! Use format yyyy-MM-dd HH:mm");
                return;
            }

            taskManager temp = taskManager.getInstance();
            int result = temp.createTask(title, description, priority, dueDate);
            switch(result){
                case 0:
                    JOptionPane.showMessageDialog(null, "Task creation successful!");
                    frame.dispose();
                    new TaskManagerApp();
                    break;
                case 1:
                    JOptionPane.showMessageDialog(null, "Log In first before attempting to create a task!");
                    break;
                case 2:
                    JOptionPane.showMessageDialog(null, "Task needs a name!");
                    break;
            }
        });

        frame.add(titleLabel);
        frame.add(titleField);
        frame.add(descriptionLabel);
        frame.add(descriptionArea);
        frame.add(priorityLabel);
        frame.add(priorityBox);
        frame.add(dueDateLabel);
        frame.add(dueDateField);
        frame.add(saveButton);

        frame.setVisible(true);
    }
}
