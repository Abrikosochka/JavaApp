package ui;

import com.formdev.flatlaf.FlatLightLaf;
import model.User;
import service.UserService;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class UserListFrame extends JFrame {
    private final UserService userService = new UserService();

    public UserListFrame() {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception e) {
            System.err.println("Не удалось применить FlatLaf: " + e.getMessage());
        }

        setTitle("Список пользователей");
        setSize(700, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel lblTitle = new JLabel("Список зарегистрированных пользователей", SwingConstants.CENTER);
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 20));
        lblTitle.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        add(lblTitle, BorderLayout.NORTH);

        List<User> users = userService.getAllUsers();
        String[] columns = {"ID","Имя", "Пароль"};
        String[][] data = new String[users.size()][3];

        for (int i = 0; i < users.size(); i++) {
            data[i][0] = String.valueOf(users.get(i).getId());
            data[i][1] = users.get(i).getUsername();
            data[i][2] = users.get(i).getPassword();
        }

        JTable table = new JTable(data, columns);
        table.setFont(new Font("SansSerif", Font.PLAIN, 14));
        table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));
        table.setRowHeight(24);

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
    }
}
