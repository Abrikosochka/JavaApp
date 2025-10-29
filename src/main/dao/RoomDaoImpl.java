package dao;

import model.Room;
import util.DatabaseUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomDaoImpl implements RoomDao {

    @Override
    public void addRoom(Room room) {
        String sql = "INSERT INTO rooms (room_number, area, guests, photo_path) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, room.getRoomNumber());
            ps.setDouble(2, room.getArea());
            ps.setInt(3, room.getGuests());
            ps.setString(4, room.getPhotoPath());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Room> getAllRooms() {
        List<Room> rooms = new ArrayList<>();
        String sql = "SELECT * FROM rooms ORDER BY id";
        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Room r = new Room();
                r.setId(rs.getInt("id"));
                r.setRoomNumber(rs.getString("room_number"));
                r.setArea(rs.getDouble("area"));
                r.setGuests(rs.getInt("guests"));
                r.setPhotoPath(rs.getString("photo_path"));
                rooms.add(r);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rooms;
    }
}
