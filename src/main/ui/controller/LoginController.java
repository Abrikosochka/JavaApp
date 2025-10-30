package ui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import service.UserService;
import store.LoginState;

public class LoginController {
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;

    private final UserService userService = new UserService();

    @FXML
    public void loginUser() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (userService.login(username, password)) {
            LoginState.isLoggedIn = true;
            LoginState.currentUsername = username;
            var roomList = store.ControllerRegistry.getRoomListController();
            if (roomList != null) {
                roomList.updateAddButtonVisibility();
            }
            new Alert(Alert.AlertType.INFORMATION, "Добро пожаловать, " + username + "!").show();
            usernameField.getScene().getWindow().hide();
            MainMenuController.refreshMenu();
        } else {
            new Alert(Alert.AlertType.ERROR, "Неверное имя или пароль").show();
        }
    }
}
