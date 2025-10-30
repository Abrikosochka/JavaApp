package store;

import ui.controller.RoomListController;

public class ControllerRegistry {
    private static RoomListController roomListController;

    public static void registerRoomListController(RoomListController controller) {
        roomListController = controller;
    }

    public static RoomListController getRoomListController() {
        return roomListController;
    }
}

