package ui;

import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainMenuFrame extends JFrame {

    public MainMenuFrame() {
        // Современная тема
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception e) {
            System.err.println("Не удалось применить FlatLaf: " + e.getMessage());
        }

        setTitle("Главное меню");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblTitle = new JLabel("Панель управления пользователями", SwingConstants.CENTER);
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 22));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        panel.add(lblTitle, gbc);

        gbc.gridy++;
        JButton btnRegister = new JButton("➕ Регистрация");
        btnRegister.setFont(new Font("SansSerif", Font.PLAIN, 16));
        btnRegister.addActionListener(this::openRegister);
        panel.add(btnRegister, gbc);

        gbc.gridy++;
        JButton btnUsers = new JButton("📋 Список пользователей");
        btnUsers.setFont(new Font("SansSerif", Font.PLAIN, 16));
        btnUsers.addActionListener(this::openUserList);
        panel.add(btnUsers, gbc);

        add(panel);
    }

    private void openRegister(ActionEvent e) {
        new RegisterFrame().setVisible(true);
    }

    private void openUserList(ActionEvent e) {
        new UserListFrame().setVisible(true);
    }
}
