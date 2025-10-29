package service;

import dao.RoomDao;
import dao.RoomDaoImpl;
import model.Room;
import java.util.List;

public class RoomService {
    private final RoomDao roomDao = new RoomDaoImpl();

    public void addRoom(String number, double area, int guests, String photoPath) {
        Room room = new Room(number, area, guests, photoPath);
        roomDao.addRoom(room);
    }

    public List<Room> getAllRooms() {
        return roomDao.getAllRooms();
    }
}
