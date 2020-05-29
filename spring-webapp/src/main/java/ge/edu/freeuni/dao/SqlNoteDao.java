package ge.edu.freeuni.dao;

import ge.edu.freeuni.model.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component("notes")
public class SqlNoteDao implements NoteDao {
    @Autowired
    private DataSource db;
//    private Connection conn;
//
//    private SqlNoteDao() throws ClassNotFoundException, SQLException {
//        Class.forName("com.mysql.jdbc.Driver");
//        conn = DriverManager.getConnection(
//                "jdbc:mysql://localhost/notesapp?user=root&password=12345678");
//    }

    @Override
    public Note get(int id) {
        try {
            PreparedStatement stm = db.getConnection().prepareStatement(
                    "SELECT id, username, text FROM notes WHERE id = ?;");
            stm.setInt(1, id);
            ResultSet res = stm.executeQuery();
            if (!res.next()) {
                stm.close();
                return null;
            }
            Note ret = new Note(res.getInt(1), res.getString(2), res.getString(3));
            stm.close();
            return ret;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean create(Note note) {
        try {
            PreparedStatement stm = db.getConnection().prepareStatement(
                    "INSERT INTO notes (username, text) VALUES (?, ?);",
                    Statement.RETURN_GENERATED_KEYS);
            stm.setString(1, note.getUsername());
            stm.setString(2, note.getText());
            int rowsUpdated = stm.executeUpdate();
            assert (rowsUpdated == 1);
            ResultSet ids = stm.getGeneratedKeys();
            if (ids.next()) {
                note.setId(ids.getInt(1));
                stm.close();
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        try {
            PreparedStatement stm = db.getConnection().prepareStatement(
                    "DELETE FROM notes WHERE id = ?;");
            stm.setInt(1, id);
            int rowsDelted = stm.executeUpdate();
            stm.close();
            return rowsDelted == 1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public Collection<Note> getAll(String username) {
        List<Note> ret = new ArrayList<>();
        try {
            PreparedStatement stm = db.getConnection().prepareStatement(
                    "SELECT id, username, text FROM notes WHERE username = ?");
            stm.setString(1, username);
            ResultSet res = stm.executeQuery();
            while (res.next()) {
                ret.add(new Note(res.getInt(1), res.getString(2), res.getString(3)));
            }
            stm.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return ret;
    }
}
