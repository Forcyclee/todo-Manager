package ui;

import manager.userManager;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class LogInApp {
    public LogInApp() {
        JFrame frame = new JFrame();


        JButton loginButton = new JButton("Login");
        JButton registerButton = new JButton("Register");
        registerButton.setVisible(false);
        JButton newButton = new JButton("New Account");


        JTextField username = new JTextField();
        JPasswordField password = new JPasswordField();
        JTextField email = new JTextField();
        JTextField firstName = new JTextField();
        JTextField lastName = new JTextField();

        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");
        JLabel emailLabel = new JLabel("E-mail:");
        JLabel firstNameLabel = new JLabel("First Name:");
        JLabel lastNameLabel = new JLabel("Last Name:");
        JLabel birthdayLabel = new JLabel("Birthday:");


        email.setVisible(false);
        firstName.setVisible(false);
        lastName.setVisible(false);
        emailLabel.setVisible(false);
        firstNameLabel.setVisible(false);
        lastNameLabel.setVisible(false);
        birthdayLabel.setVisible(false);


        SpinnerDateModel dateModel = new SpinnerDateModel();
        JSpinner dobSpinner = new JSpinner(dateModel);
        JSpinner.DateEditor editor = new JSpinner.DateEditor(dobSpinner, "yyyy-MM-dd");
        dobSpinner.setEditor(editor);
        dobSpinner.setVisible(false);


        int frameWidth = 500;
        int topMargin = 40;
        int leftLabel = 80;
        int fieldWidth = 200;
        int fieldHeight = 30;
        int spacing = 50; // distance between fields


        usernameLabel.setBounds(leftLabel, topMargin, 100, fieldHeight);
        username.setBounds(leftLabel + 100, topMargin, fieldWidth, fieldHeight);

        passwordLabel.setBounds(leftLabel, topMargin + spacing, 100, fieldHeight);
        password.setBounds(leftLabel + 100, topMargin + spacing, fieldWidth, fieldHeight);

        emailLabel.setBounds(leftLabel, topMargin + spacing * 2, 100, fieldHeight);
        email.setBounds(leftLabel + 100, topMargin + spacing * 2, fieldWidth, fieldHeight);

        firstNameLabel.setBounds(leftLabel, topMargin + spacing * 3, 100, fieldHeight);
        firstName.setBounds(leftLabel + 100, topMargin + spacing * 3, fieldWidth, fieldHeight);

        lastNameLabel.setBounds(leftLabel, topMargin + spacing * 4, 100, fieldHeight);
        lastName.setBounds(leftLabel + 100, topMargin + spacing * 4, fieldWidth, fieldHeight);

        birthdayLabel.setBounds(leftLabel, topMargin + spacing * 5, 100, fieldHeight);
        dobSpinner.setBounds(leftLabel + 100, topMargin + spacing * 5, fieldWidth, fieldHeight);


        int buttonHeight = 40;
        int buttonWidth = 120;
        int buttonY = topMargin + spacing * 6;
        loginButton.setBounds(leftLabel, buttonY, buttonWidth, buttonHeight);
        registerButton.setBounds((frameWidth - buttonWidth) / 2, buttonY, buttonWidth, buttonHeight);         newButton.setBounds(leftLabel + 150, buttonY, 160, buttonHeight);

        newButton.setBorderPainted(false);
        newButton.setContentAreaFilled(false);
        newButton.setFocusPainted(false);
        newButton.setForeground(Color.BLUE);
        newButton.setCursor(new Cursor(Cursor.HAND_CURSOR));


        int frameHeight = buttonY + buttonHeight + 80;
        frame.setSize(frameWidth, frameHeight);


        newButton.addActionListener(e -> {
            loginButton.setVisible(false);
            registerButton.setVisible(true);
            newButton.setVisible(false);

            email.setVisible(true);
            firstName.setVisible(true);
            lastName.setVisible(true);
            dobSpinner.setVisible(true);
            emailLabel.setVisible(true);
            firstNameLabel.setVisible(true);
            lastNameLabel.setVisible(true);
            birthdayLabel.setVisible(true);
        });

        loginButton.addActionListener(e -> {
            String user = username.getText();
            String pass = new String(password.getPassword());

            try{
                userManager temp = userManager.getInstance();
                int result = temp.login(user, pass);
                switch (result) {
                    case 0 -> JOptionPane.showMessageDialog(frame, "Login com sucesso!");
                    case 1 -> JOptionPane.showMessageDialog(frame, "Password errada!");
                    case 2 -> JOptionPane.showMessageDialog(frame, "Utilizador não encontrado!");
                    case 3 -> JOptionPane.showMessageDialog(frame, "Já existe sessão iniciada!");
                    default -> JOptionPane.showMessageDialog(frame, "Erro inesperado!");
                }

            }catch(Exception ex){
                JOptionPane.showMessageDialog(frame, ex.getMessage(), "Login Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        registerButton.addActionListener(e -> {
            String user = username.getText();
            String pass = new String(password.getPassword());
            String mail = email.getText();
            String first = firstName.getText();
            String last = lastName.getText();
            LocalDate dob = ((Date) dobSpinner.getValue())
                    .toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();
            try{
                userManager temp = userManager.getInstance();
                int result = temp.register(user, pass, mail, first, last, dob);
                switch (result) {
                    case 0:
                        JOptionPane.showMessageDialog(frame, "Registered with success!");
                        loginButton.setVisible(true);
                        registerButton.setVisible(false);
                        newButton.setVisible(true);

                        email.setVisible(false);
                        firstName.setVisible(false);
                        lastName.setVisible(false);
                        dobSpinner.setVisible(false);
                        emailLabel.setVisible(false);
                        firstNameLabel.setVisible(false);
                        lastNameLabel.setVisible(false);
                        birthdayLabel.setVisible(false);
                        break;
                    case 1:
                        JOptionPane.showMessageDialog(frame, "User already exists!");
                        break;

                    case 3:
                        JOptionPane.showMessageDialog(frame, "Logout of current account first!");

                        break;
                    case 4:
                        JOptionPane.showMessageDialog(frame, "All camps are obligatory!");
                        break;
                    case 5:
                        JOptionPane.showMessageDialog(frame, "Password should have at least 8 characters!");
                        break;
                    case 6:
                        JOptionPane.showMessageDialog(frame, "Username should have at least 8 characters!");
                        break;
                        case 7:
                            JOptionPane.showMessageDialog(frame, "You need to be 18 or older to join!");
                    default:
                        throw new IllegalStateException("Unexpected value: " + result);
                }
            }catch(Exception ex){
                JOptionPane.showMessageDialog(frame, "Erro inesperado!");
            }
        });


        frame.add(usernameLabel);
        frame.add(passwordLabel);
        frame.add(username);
        frame.add(password);
        frame.add(loginButton);
        frame.add(registerButton);
        frame.add(newButton);
        frame.add(emailLabel);
        frame.add(firstNameLabel);
        frame.add(lastNameLabel);
        frame.add(email);
        frame.add(firstName);
        frame.add(lastName);
        frame.add(dobSpinner);
        frame.add(birthdayLabel);

        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
