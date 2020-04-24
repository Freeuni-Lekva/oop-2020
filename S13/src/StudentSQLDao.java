import java.sql.*;
import java.util.List;

public class StudentSQLDao implements StudentDao {
    private Connection conn;

    public StudentSQLDao(Connection conn) {
        this.conn = conn;
    }

    @Override
    public boolean createTable() {
        Statement stm = null;
        try {
            stm = conn.createStatement();
        stm.execute("CREATE TABLE Students ("
                + "id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, "
                + "first_name VARCHAR(50), "
                + "last_name VARCHAR(50));");
        return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean dropTable() {
        Statement stm = null;
        try {
            stm = conn.createStatement();
            stm.execute("DROP  TABLE Students");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Student get(int id) {
        PreparedStatement stm = null;
        try {
            stm = conn.prepareStatement(
                    "SELECT id, first_name, last_name FROM Students WHERE id = ?;");
            stm.setInt(1, id);
            ResultSet res = stm.executeQuery();
            res.next();
            Student student = new Student(
                    res.getInt(1),
                    res.getString(2),
                    res.getString(3));
            if (res.next()) {
                throw new RuntimeException("Expected only one student record.");
            }
            return student;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean create(Student student) {
        try {
            PreparedStatement stm = conn.prepareStatement(
                    "INSERT INTO Students (first_name, last_name) VALUES (?, ?);");
            stm.setString(1, student.getFirstName());
            stm.setString(2, student.getLastName());
            stm.executeUpdate();
            PreparedStatement get = conn.prepareStatement(
                    "SELECT id FROM Students WHERE first_name = ? AND last_name = ?;");
            get.setString(1, student.getFirstName());
            get.setString(2, student.getLastName());
            ResultSet res = get.executeQuery();
            res.next();
            student.setId(res.getInt(1));
            // res.next == false
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void update(Student student) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<Student> getAll() {
        return null;
    }
}
