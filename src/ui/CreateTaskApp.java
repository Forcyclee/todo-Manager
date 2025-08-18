package ui;

import domain.taskPriority;
import domain.taskStatus;
import manager.taskManager;

import javax.swing.*;

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






        JButton saveButton = new JButton("Save Task");
        saveButton.setBounds(140, 310, 120, 30);
        saveButton.addActionListener(e -> {
            String title = titleField.getText();
            String description = descriptionArea.getText();
            taskPriority priority = (taskPriority) priorityBox.getSelectedItem();

            taskManager temp = taskManager.getInstance();
            int result = temp.createTask(title, description, priority);
            switch(result){
                case 0:
                    JOptionPane.showMessageDialog(null, "Task creation successful!");
                    frame.dispose();
                    new TaskManagerApp();
                    break;
                case 1:
                    JOptionPane.showMessageDialog(null, "Log In first before attempting to create a task!");
                    break;
            }




        });

        frame.add(titleLabel);
        frame.add(titleField);
        frame.add(descriptionLabel);
        frame.add(descriptionArea);
        frame.add(priorityLabel);
        frame.add(priorityBox);
        frame.add(saveButton);

        frame.setVisible(true);

    }
}
