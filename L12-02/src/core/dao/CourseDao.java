package core.dao;

import core.bean.Course;
import core.bean.Student;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

public class CourseDao {

    private final DataSource ds;

    public CourseDao(DataSource ds) {
        this.ds = ds;
    }

    public Course getCourseById(int studentId) throws SQLException {
        try (Connection conn = ds.getConnection()) {
            try (PreparedStatement stmt = conn.prepareStatement("select c.* from courses c where c.course_id = ?")) {
                stmt.setInt(1, studentId);

                try (ResultSet rslt = stmt.executeQuery()) {
                    if (rslt.next()) {
                        return fetchCourse(rslt);
                    } else {
                        throw new IllegalArgumentException("Invalid course id");
                    }
                }
            }
        }
    }

    public List<Course> getCourseList() throws SQLException {
        try (Connection conn = ds.getConnection()) {
            try (Statement stmt = conn.createStatement()) {
                try (ResultSet rslt = stmt.executeQuery("select c.* from courses c order by c.course_id desc")) {
                    List<Course> list = new ArrayList<>();

                    while (rslt.next()) {
                        list.add(fetchCourse(rslt));
                    }

                    return list;
                }
            }
        }
    }
    
    public Student getStudentCourses(int studentId) throws SQLException {
        StudentDao studentDao = new StudentDao(ds);
        
        Student student = studentDao.getStudentById(studentId);
        
        try (Connection conn = ds.getConnection()) {
            try (PreparedStatement stmt = conn.prepareStatement("select c.* from courses c, student_courses sc "
                    + "where c.course_id = sc.course_id and sc.student_id = ? "
                    + "order by c.course_id desc")) {
                stmt.setInt(1, studentId);
                
                try (ResultSet rslt = stmt.executeQuery()) {
                    while (rslt.next()) {
                        student.getCourses().add(fetchCourse(rslt));
                    }
                }
            }
        }
        
        return student;
    }

    private Course fetchCourse(ResultSet rslt) throws SQLException {
        Course course = new Course();
        course.setCourseId(rslt.getInt("course_id"));
        course.setCourseName(rslt.getString("course_name"));
        course.setCourseCredit(rslt.getInt("course_credit"));
        course.setCourseType(rslt.getString("course_type"));
        return course;
    }
}
