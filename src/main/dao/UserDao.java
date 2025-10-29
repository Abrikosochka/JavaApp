package dao;
import java.util.List;
import model.User;

public interface UserDao {
    void save(User user);
    List<User> findAll();
    User findById(int id);
}
