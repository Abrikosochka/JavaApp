package ui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import service.UserService;

public class RegisterController {
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;

    private final UserService userService = new UserService();

    @FXML
    public void registerUser() {
        try {
            userService.registerUser(usernameField.getText(), passwordField.getText());
            new Alert(Alert.AlertType.INFORMATION, "✅ Пользователь зарегистрирован!").show();
            usernameField.getScene().getWindow().hide();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Ошибка: " + e.getMessage()).show();
        }
    }
}
