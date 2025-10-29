package ui;

import com.formdev.flatlaf.FlatLightLaf;
import service.UserService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class RegisterFrame extends JFrame {
    private final JTextField usernameField;
    private final JPasswordField passwordField;
    private final UserService userService = new UserService();

    public RegisterFrame() {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception e) {
            System.err.println("Не удалось применить FlatLaf: " + e.getMessage());
        }

        setTitle("Регистрация пользователя");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 12, 12, 12);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblTitle = new JLabel("Регистрация нового пользователя", SwingConstants.CENTER);
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 20));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        panel.add(lblTitle, gbc);

        gbc.gridwidth = 1;

        gbc.gridy++;
        panel.add(new JLabel("Имя пользователя:"), gbc);
        usernameField = new JTextField();
        gbc.gridx = 1;
        panel.add(usernameField, gbc);

        gbc.gridx = 0; gbc.gridy++;
        panel.add(new JLabel("Пароль:"), gbc);
        passwordField = new JPasswordField();
        gbc.gridx = 1;
        panel.add(passwordField, gbc);

        gbc.gridx = 0; gbc.gridy++; gbc.gridwidth = 2;
        JButton btnRegister = new JButton("✅ Зарегистрировать");
        btnRegister.setFont(new Font("SansSerif", Font.PLAIN, 16));
        btnRegister.addActionListener(this::registerUser);
        panel.add(btnRegister, gbc);

        add(panel);
    }

    private void registerUser(ActionEvent e) {
        try {
            userService.registerUser(
                    usernameField.getText(),
                    new String(passwordField.getPassword())
            );
            JOptionPane.showMessageDialog(this, "✅ Пользователь зарегистрирован!");
            dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Ошибка: " + ex.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }
}
