package core.dao;

import core.bean.Course;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import core.bean.Student;
import javax.sql.DataSource;

public class StudentDao {

    private final DataSource ds;

    public StudentDao(DataSource ds) {
        this.ds = ds;
    }

    public Student getStudentById(int studentId) throws SQLException {
        try (Connection conn = ds.getConnection()) {
            try (PreparedStatement stmt = conn.prepareStatement("select s.* from students s where s.student_id = ?")) {
                stmt.setInt(1, studentId);

                try (ResultSet rslt = stmt.executeQuery()) {
                    if (rslt.next()) {
                        return fetchStudent(rslt);
                    } else {
                        throw new IllegalArgumentException("Invalid student id");
                    }
                }
            }
        }
    }

    public List<Student> getStudentList() throws SQLException {
        try (Connection conn = ds.getConnection()) {
            try (Statement stmt = conn.createStatement()) {
                try (ResultSet rslt = stmt.executeQuery("select s.* from students s order by s.student_id desc")) {
                    List<Student> list = new ArrayList<>();

                    while (rslt.next()) {
                        list.add(fetchStudent(rslt));
                    }

                    return list;
                }
            }
        }
    }
    
    public Course getCourseStudents(int courseId) throws SQLException {
        CourseDao courseDao = new CourseDao(ds);
        
        Course course = courseDao.getCourseById(courseId);
        
        try (Connection conn = ds.getConnection()) {
            try (PreparedStatement stmt = conn.prepareStatement("select s.* from students s, student_courses sc "
                    + "where s.student_id = sc.student_id and sc.course_id = ? "
                    + "order by s.student_id desc")) {
                stmt.setInt(1, courseId);
                
                try (ResultSet rslt = stmt.executeQuery()) {
                    while (rslt.next()) {
                        course.getStudents().add(fetchStudent(rslt));
                    }
                }
            }
        }
        
        return course;
    }

    private Student fetchStudent(ResultSet rslt) throws SQLException {
        Student student = new Student();
        student.setStudentId(rslt.getInt("student_id"));
        student.setIdnumber(rslt.getString("idnumber"));
        student.setFirstName(rslt.getString("firstname"));
        student.setLastName(rslt.getString("lastname"));
        student.setRegisterDate(new Date(rslt.getTimestamp("register_date").getTime()));
        return student;
    }
}