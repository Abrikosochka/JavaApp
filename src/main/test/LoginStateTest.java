package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import store.LoginState;

import static org.junit.jupiter.api.Assertions.*;

class LoginStateTest {

    @BeforeEach
    void resetState() {
        LoginState.logout(); // сбрасываем состояние перед каждым тестом
    }

    @Test
    void testInitialState() {
        assertFalse(LoginState.isLoggedIn, "По умолчанию пользователь не должен быть залогинен");
        assertNull(LoginState.currentUsername, "Имя пользователя должно быть null по умолчанию");
    }

    @Test
    void testLoginSetsStateCorrectly() {
        LoginState.login("artem");

        assertTrue(LoginState.isLoggedIn, "После логина isLoggedIn должен быть true");
        assertEquals("artem", LoginState.currentUsername, "Имя пользователя должно сохраняться после логина");
    }

    @Test
    void testLoginSetsStateNotCorrectly() {
        LoginState.login("artem");

        assertTrue(LoginState.isLoggedIn, "После логина isLoggedIn должен быть true");
        assertEquals("artem1", LoginState.currentUsername, "Имя пользователя должно сохраняться после логина");
    }

    @Test
    void testLogoutResetsState() {
        LoginState.login("artem");
        LoginState.logout();

        assertFalse(LoginState.isLoggedIn, "После выхода пользователь не должен быть залогинен");
        assertNull(LoginState.currentUsername, "Имя пользователя должно сбрасываться после выхода");
    }

    @Test
    void testMultipleLoginsOverrideUsername() {
        LoginState.login("artem");
        LoginState.login("ivan");

        assertTrue(LoginState.isLoggedIn, "isLoggedIn должен оставаться true");
        assertEquals("ivan", LoginState.currentUsername, "Имя пользователя должно обновляться при новом входе");
    }
}
