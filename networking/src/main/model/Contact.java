package model;

import java.io.Serializable;

public class Contact implements Serializable {
    private String id;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String phoneNumber;

    public Contact(String firstName, String lastName, String emailAddress, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Contact)) {
            return false;
        }
        Contact o = (Contact) obj;
        return firstName.equals(o.firstName) &&
                lastName.equals(o.lastName) &&
                emailAddress.equals(o.emailAddress) &&
                phoneNumber.equals(o.phoneNumber);
    }
}
