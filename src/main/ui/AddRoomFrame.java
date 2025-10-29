package ui;

import service.RoomService;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class AddRoomFrame extends JFrame {
    private final JTextField numberField;
    private final JTextField areaField;
    private final JTextField guestsField;
    private final JTextField photoField;
    private final RoomService roomService = new RoomService();
    private final RoomListFrame parentFrame; // ссылка на окно списка номеров

    public AddRoomFrame(RoomListFrame parentFrame) {
        this.parentFrame = parentFrame;

        setTitle("Добавление номера");
        setSize(500, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Номер:"), gbc);
        numberField = new JTextField();
        gbc.gridx = 1;
        panel.add(numberField, gbc);

        gbc.gridx = 0; gbc.gridy++;
        panel.add(new JLabel("Площадь (м²):"), gbc);
        areaField = new JTextField();
        gbc.gridx = 1;
        panel.add(areaField, gbc);

        gbc.gridx = 0; gbc.gridy++;
        panel.add(new JLabel("Количество гостей:"), gbc);
        guestsField = new JTextField();
        gbc.gridx = 1;
        panel.add(guestsField, gbc);

        gbc.gridx = 0; gbc.gridy++;
        panel.add(new JLabel("Фото:"), gbc);

        JPanel photoPanel = new JPanel(new BorderLayout(5, 0));
        photoField = new JTextField();
        photoField.setEditable(false);
        JButton btnBrowse = new JButton("Выбрать...");
        btnBrowse.addActionListener(this::choosePhoto);
        photoPanel.add(photoField, BorderLayout.CENTER);
        photoPanel.add(btnBrowse, BorderLayout.EAST);
        gbc.gridx = 1;
        panel.add(photoPanel, gbc);

        gbc.gridx = 0; gbc.gridy++; gbc.gridwidth = 2;
        JButton btnAdd = new JButton("Добавить номер");
        btnAdd.addActionListener(this::addRoom);
        panel.add(btnAdd, gbc);

        add(panel);
    }

    private void choosePhoto(ActionEvent e) {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Выберите фото");
        chooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter(
                "Изображения (JPG, PNG, JPEG)", "jpg", "jpeg", "png"));
        int result = chooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = chooser.getSelectedFile();
            photoField.setText(selectedFile.getAbsolutePath());
        }
    }

    private void addRoom(ActionEvent e) {
        try {
            String number = numberField.getText();
            double area = Double.parseDouble(areaField.getText());
            int guests = Integer.parseInt(guestsField.getText());
            String photo = photoField.getText();

            roomService.addRoom(number, area, guests, photo);
            JOptionPane.showMessageDialog(this, "✅ Номер добавлен!");

            // обновляем таблицу
            if (parentFrame != null) {
                parentFrame.loadRooms();
            }

            dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Ошибка: " + ex.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }
}
