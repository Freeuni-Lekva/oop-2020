package dao;

import model.Contact;

import java.util.HashMap;
import java.util.Map;

public class InMemoryContactDao implements ContactDao {
    private Map<String, Contact> contacts;

    public InMemoryContactDao() {
        contacts = new HashMap<>();
    }

    @Override
    public Contact get(String id) {
        return contacts.get(id);
    }

    @Override
    public String create(Contact contact) {
        String id = String.valueOf(contacts.size());
        contact.setId(id);
        contacts.put(id, contact);
        return id;
    }
}
