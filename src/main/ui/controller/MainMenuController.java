package ui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import store.LoginState;

public class MainMenuController {

    @FXML private Button loginBtn;
    @FXML private Button logoutBtn;
    @FXML private Button registerBtn;
    @FXML private Button usersBtn;
    @FXML private Button roomsBtn;
    @FXML private Label welcomeLabel;

    // сохраняем ссылку на контроллер для обновления меню после логина
    private static MainMenuController instance;

    public MainMenuController() {
        instance = this;
    }

    public static void refreshMenu() {
        if (instance != null) instance.updateUI();
    }

    @FXML
    public void initialize() {
        updateUI();
    }

    private void updateUI() {
        boolean logged = LoginState.isLoggedIn;

        if (welcomeLabel != null) {
            welcomeLabel.setText(logged
                    ? "Добро пожаловать, " + LoginState.currentUsername
                    : "Панель управления пользователями");
        }

        loginBtn.setVisible(!logged);
        logoutBtn.setVisible(logged);
        logoutBtn.setManaged(logged);
        registerBtn.setDisable(logged); // можно запретить повторную регистрацию
        usersBtn.setDisable(!logged);
        roomsBtn.setDisable(false);
    }

    // универсальный метод для открытия окон
    private void openWindow(String fxml, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/view/" + fxml));
            Scene scene = new Scene(loader.load());
            scene.getStylesheets().add(getClass().getResource("/ui/style/app.css").toExternalForm());
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // кнопки меню
    public void openLogin(ActionEvent e) { openWindow("login.fxml", "Вход в систему"); }
    public void openRegister(ActionEvent e) { openWindow("register.fxml", "Регистрация пользователя"); }
    public void openUsers(ActionEvent e) { openWindow("user_list.fxml", "Список пользователей"); }
    public void openRooms(ActionEvent e) { openWindow("room_list.fxml", "Номера"); }

    @FXML
    public void logout(ActionEvent e) {
        LoginState.logout();

        var roomList = store.ControllerRegistry.getRoomListController();
        if (roomList != null) {
            roomList.updateAddButtonVisibility();
        }

        updateUI();
    }
}
