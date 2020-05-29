package ge.edu.freeuni.dao;

import ge.edu.freeuni.model.User;

import java.sql.SQLException;
import java.util.Collection;

public interface UserDao {
    public User get(String username);
    public void create(User user);
    public void delete(String username);
}
