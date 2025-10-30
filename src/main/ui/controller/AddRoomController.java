package ui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import service.RoomService;

import java.io.File;

public class AddRoomController {
    @FXML private TextField numberField;
    @FXML private TextField areaField;
    @FXML private TextField guestsField;
    @FXML private TextField photoField;
    private final RoomService roomService = new RoomService();

    @FXML
    public void choosePhoto() {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Выберите фото");
        chooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Изображения", "*.jpg", "*.jpeg", "*.png")
        );
        File file = chooser.showOpenDialog(new Stage());
        if (file != null) photoField.setText(file.getAbsolutePath());
    }

    @FXML
    public void addRoom() {
        try {
            roomService.addRoom(
                    numberField.getText(),
                    Double.parseDouble(areaField.getText()),
                    Integer.parseInt(guestsField.getText()),
                    photoField.getText()
            );
            new Alert(Alert.AlertType.INFORMATION, "✅ Номер добавлен!").show();
            ((Stage) numberField.getScene().getWindow()).close();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Ошибка: " + e.getMessage()).show();
        }
    }
}
