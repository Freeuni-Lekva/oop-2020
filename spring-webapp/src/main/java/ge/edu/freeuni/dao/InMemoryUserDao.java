package ge.edu.freeuni.dao;

import ge.edu.freeuni.model.User;

import java.util.HashMap;
import java.util.Map;

public class InMemoryUserDao implements UserDao {
    private static InMemoryUserDao instance;

    private Map<String, User> users;

    public static InMemoryUserDao getInstance() {
        if (instance == null) {
            synchronized (InMemoryUserDao.class) {
                if (instance == null) {
                    instance = new InMemoryUserDao();
                }
            }
        }
        return instance;
    }

    private InMemoryUserDao() {
        users = new HashMap<>();
        users.put("qwe", new User("qwe", "qwe"));
    }

    @Override
    public User get(String username) {
        return users.get(username);
    }

    @Override
    public void create(User user) {
        users.put(user.getUsername(), user);
    }

    @Override
    public void delete(String username) {
        users.remove(username);
    }
}
