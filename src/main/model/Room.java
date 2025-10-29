package model;

public class Room {
    private int id;
    private String roomNumber;
    private double area;
    private int guests;
    private String photoPath;

    public Room() {}

    public Room(String roomNumber, double area, int guests, String photoPath) {
        this.roomNumber = roomNumber;
        this.area = area;
        this.guests = guests;
        this.photoPath = photoPath;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getRoomNumber() { return roomNumber; }
    public void setRoomNumber(String roomNumber) { this.roomNumber = roomNumber; }

    public double getArea() { return area; }
    public void setArea(double area) { this.area = area; }

    public int getGuests() { return guests; }
    public void setGuests(int guests) { this.guests = guests; }

    public String getPhotoPath() { return photoPath; }
    public void setPhotoPath(String photoPath) { this.photoPath = photoPath; }
}

