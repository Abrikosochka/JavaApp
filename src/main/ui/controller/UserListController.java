package ui.controller;

import javafx.fxml.FXML;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import model.User;
import service.UserService;

import java.util.List;

public class UserListController {
    @FXML private VBox userContainer;
    private final UserService userService = new UserService();

    @FXML
    public void initialize() {
        List<User> users = userService.getAllUsers();
        for (User user : users) {
            Label lbl = new Label("👤 " + user.getUsername() + " — пароль: " + user.getPassword());
            lbl.setStyle("-fx-font-size: 16; -fx-background-color: #fff; -fx-padding: 8 12; -fx-background-radius: 8;");
            userContainer.getChildren().add(lbl);
        }
    }
}
