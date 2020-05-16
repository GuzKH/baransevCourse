package fourthTask.model;

import java.util.Objects;

public class ContactData {
    private int id;
    private final String firstName;
    private String lastName;
    private final String address;
    private final String homeNumber;
    private final String email;
    private String group;

    public ContactData(int id, String firstName, String lastName, String address, String homeNumber, String email, String group) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.homeNumber = homeNumber;
        this.email = email;
        this.group = group;
    }

    public ContactData(String firstName, String lastName, String address, String homeNumber, String email, String group) {
        this.id = 0;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.homeNumber = homeNumber;
        this.email = email;
        this.group = group;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", group='" + group + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return id == that.id &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(email, that.email) &&
                Objects.equals(group, that.group);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lastName, email, group);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getHomeNumber() {
        return homeNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getGroup() {
        return group;
    }

}
