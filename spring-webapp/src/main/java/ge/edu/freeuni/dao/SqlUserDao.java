package ge.edu.freeuni.dao;

import ge.edu.freeuni.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Collection;

@Component("users")
public class SqlUserDao implements UserDao {
    @Autowired
    private DataSource db;
//    private static SqlUserDao instance;

//    private Connection conn;
//
////    public static SqlUserDao getInstance() throws SQLException, ClassNotFoundException {
////        if (instance == null) {
////            synchronized (SqlUserDao.class) {
////                if (instance == null) {
////                    instance = new SqlUserDao();
////                }
////            }
////        }
////        return instance;
////    }
//
//    private SqlUserDao() throws ClassNotFoundException, SQLException {
//        Class.forName("com.mysql.jdbc.Driver");
//        conn = DriverManager.getConnection(
//                "jdbc:mysql://localhost/notesapp?user=root&password=12345678");
//    }

    @Override
    public User get(String username) {
        PreparedStatement stm = null;
        try {
            stm = db.getConnection().prepareStatement(
                    "SELECT username, password FROM users WHERE username = ?;");
            stm.setString(1, username);
            ResultSet res = stm.executeQuery();
            if (!res.next()) {
                stm.close();
                return null;
            }
            User ret = new User(
                    res.getString("username"),
                    res.getString("password"));
            stm.close();
            return ret;
        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
        return null;
    }

    @Override
    public void create(User user) {
        PreparedStatement stm = null;
        try {
            stm = db.getConnection().prepareStatement(
                    "INSERT INTO users (username, password) VALUES (?, ?)");
            stm.setString(1, user.getUsername());
            stm.setString(2, user.getPassword());
            int rowsUpdated = stm.executeUpdate();
            stm.close();
            assert (rowsUpdated == 1);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void delete(String username) {
        PreparedStatement stm = null;
        try {
            stm = db.getConnection().prepareStatement(
                    "DELETE FROM users WHERE username = ?");
            stm.setString(1, username);
            int rowsDeleted = stm.executeUpdate();
            stm.close();
            assert (rowsDeleted == 1);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
