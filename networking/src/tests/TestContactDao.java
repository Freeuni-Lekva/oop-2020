import dao.InMemoryContactDao;
import model.Contact;

public class TestContactDao extends InMemoryContactDao {
    public TestContactDao() {
        create(new Contact("Foo", "Bar", "foo@bar.com", "12345"));
        create(new Contact("Dev", "Null", "dev@null.com", "98765"));
    }
}
