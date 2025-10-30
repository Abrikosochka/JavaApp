package store;

public class LoginState {
    public static boolean isLoggedIn = false;
    public static String currentUsername = null;

    public static void login(String username) {
        isLoggedIn = true;
        currentUsername = username;
    }

    public static void logout() {
        isLoggedIn = false;
        currentUsername = null;
    }
}

