package freeuni.model;

import java.io.Serializable;

public class Employee implements Serializable {

    private long id;
    private String firstName;
    private String lastName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Employee [id=" + id + ", firstName=" + firstName
                + ", lastName=" + lastName + "]";
    }

    public Employee copy() {
        Employee copy = new Employee();
        copy.setId(id);
        copy.setFirstName(firstName);
        copy.setLastName(lastName);
        return copy;
    }

}
