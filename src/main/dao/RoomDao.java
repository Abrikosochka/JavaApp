package dao;

import model.Room;
import java.util.List;

public interface RoomDao {
    void addRoom(Room room);
    List<Room> getAllRooms();
}

