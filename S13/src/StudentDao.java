import java.util.List;

public interface StudentDao {
    public boolean createTable();
    public boolean dropTable();

    public Student get(int id);

    public boolean create(Student student);

    public void update(Student student);

    public void delete(int id);

    public List<Student> getAll();

    // Convenience
    default public void delete(Student student) {
        delete(student.getId());
    }
}
