package service;

import dao.UserDao;
import dao.UserDaoImpl;
import model.User;
import java.util.List;

public class UserService {
    private final UserDao userDao = new UserDaoImpl();

    public void registerUser(String username, String password) {
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("Имя пользователя не может быть пустым");
        }
        User user = new User(username, password);
        userDao.save(user);
    }

    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    public User getUser(int id) {
        return userDao.findById(id);
    }
}
