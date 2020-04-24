import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class StudentSQLDaoTest {

    private Connection conn;
    private StudentDao studentDao;

    @BeforeAll
    void setUp() throws ClassNotFoundException, SQLException {

        System.out.printf("%d", a++);

        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection(
                "jdbc:mysql://localhost",
                "root",
                "12345678");
        Statement stm = conn.createStatement();
        stm.execute("CREATE DATABASE students_test_db");
        stm.execute("USE students_test_db");
        studentDao = new StudentSQLDao(conn);
        studentDao.createTable();
    }

    @AfterAll
    void tearDown() throws SQLException {
        studentDao.dropTable();
        Statement stm = conn.createStatement();
        stm.execute("DROP DATABASE students_test_db");
        conn.close();
    }

    @Test
    void testCreateRead() {
        Student student = new Student("foo", "bar");
        assertTrue(studentDao.create(student));
        assertNotEquals(0, student.getId());
        Student st = studentDao.get(student.getId());
        assertEquals(student, st);
        studentDao.delete(student);
    }

    @Test
    void testDelete() {

    }

}