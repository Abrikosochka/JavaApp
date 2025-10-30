package ui.controller;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import model.Room;
import store.LoginState;
import service.RoomService;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class RoomListController {
    @FXML private FlowPane roomContainer;
    @FXML private Button addBtn;
    private final RoomService roomService = new RoomService();

    @FXML
    public void initialize() {
        store.ControllerRegistry.registerRoomListController(this);
        addBtn.setVisible(store.LoginState.isLoggedIn);
        loadRooms();
    }

    public void updateAddButtonVisibility() {
        addBtn.setVisible(store.LoginState.isLoggedIn);
    }

    private void loadRooms() {
        roomContainer.getChildren().clear();
        List<Room> rooms = roomService.getAllRooms();

        for (Room room : rooms) {
            VBox card = new VBox(10);
            card.getStyleClass().add("room-card");

            ImageView img = new ImageView();
            img.setFitWidth(180);
            img.setFitHeight(120);
            if (room.getPhotoPath() != null && new File(room.getPhotoPath()).exists()) {
                img.setImage(new Image(new File(room.getPhotoPath()).toURI().toString()));
            } else {
                img.setImage(new Image(getClass().getResource("/ui/assets/no-photo.png").toString()));
            }

            Label lbl = new Label("№ " + room.getRoomNumber() + "\n" +
                    "Площадь: " + room.getArea() + " м²\n" +
                    "Гостей: " + room.getGuests());
            lbl.setWrapText(true);

            card.getChildren().addAll(img, lbl);
            roomContainer.getChildren().add(card);
        }
    }

    @FXML
    public void openAddRoom() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/view/add_room.fxml"));
        Scene scene = new Scene(loader.load());
        scene.getStylesheets().add(getClass().getResource("/ui/style/app.css").toExternalForm());
        Stage stage = new Stage();
        stage.setTitle("Добавить номер");
        stage.setScene(scene);
        stage.showAndWait();
        loadRooms();
    }
}

