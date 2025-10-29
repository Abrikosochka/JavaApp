package ui;

import service.UserService;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class LoginFrame extends JFrame {
    private final JTextField usernameField;
    private final JPasswordField passwordField;
    private final UserService userService = new UserService();
    public static boolean isLoggedIn = false;

    public LoginFrame() {
        setTitle("Авторизация");
        setSize(400, 250);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
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
        JButton loginBtn = new JButton("Войти");
        loginBtn.addActionListener(this::login);
        panel.add(loginBtn, gbc);

        add(panel);
    }

    private void login(ActionEvent e) {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        if (userService.login(username, password)) {
            isLoggedIn = true;
            JOptionPane.showMessageDialog(this, "Добро пожаловать, " + username + "!");
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Неверное имя или пароль", "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }
}

