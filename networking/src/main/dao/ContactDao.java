package dao;

import model.Contact;

public interface ContactDao {
    public Contact get(String id);
    public String create(Contact contact);
}
