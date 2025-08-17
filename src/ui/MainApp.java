package ui;

import manager.sessionManager;
import manager.userManager;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class MainApp {
    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(() -> {
            new LogInApp();
        });
    }
}