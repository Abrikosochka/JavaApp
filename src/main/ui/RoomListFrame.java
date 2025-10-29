package ui;

import model.Room;
import service.RoomService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.io.File;
import java.util.List;

public class RoomListFrame extends JFrame {
    private final RoomService roomService = new RoomService();
    private final DefaultTableModel model;
    private final JTable table;

    public RoomListFrame() {
        setTitle("Список номеров");
        setSize(900, 500);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        String[] columns = {"ID", "Номер", "Площадь (м²)", "Гостей", "Фото"};
        model = new DefaultTableModel(columns, 0) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 4) return ImageIcon.class; // колонка "Фото"
                return super.getColumnClass(columnIndex);
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(model);
        table.setRowHeight(100); // делаем побольше строку для картинки
        table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton refreshBtn = new JButton("🔄 Обновить");
        refreshBtn.addActionListener(e -> loadRooms());
        bottomPanel.add(refreshBtn);

        if (LoginFrame.isLoggedIn) {
            JButton addRoomBtn = new JButton("➕ Добавить номер");
            addRoomBtn.addActionListener(e -> new AddRoomFrame(this).setVisible(true));
            bottomPanel.add(addRoomBtn);
        }

        add(bottomPanel, BorderLayout.SOUTH);
        loadRooms();
    }

    // Загружаем список номеров из базы
    public void loadRooms() {
        model.setRowCount(0); // очистить таблицу перед обновлением
        List<Room> rooms = roomService.getAllRooms();
        for (Room r : rooms) {
            ImageIcon imageIcon = null;
            if (r.getPhotoPath() != null && !r.getPhotoPath().isEmpty()) {
                File imgFile = new File(r.getPhotoPath());
                if (imgFile.exists()) {
                    // уменьшаем изображение под высоту строки
                    ImageIcon icon = new ImageIcon(r.getPhotoPath());
                    Image img = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                    imageIcon = new ImageIcon(img);
                }
            }
            model.addRow(new Object[]{
                    r.getId(),
                    r.getRoomNumber(),
                    r.getArea(),
                    r.getGuests(),
                    imageIcon
            });
        }
    }
}
