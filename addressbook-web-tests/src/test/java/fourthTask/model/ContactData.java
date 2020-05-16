package fourthTask.model;

import java.util.Objects;

public class ContactData {
    private final String id;
    private final String firstName;
 //   private final String middleName;
    private String lastName;
 //   private final String nickname;
    private final String address;
    private final String homeNumber;
    private final String email;
    private String group;



    public ContactData(String id, String firstName, String lastName, String address, String homeNumber, String email, String group) {
        this.id = id;
        this.firstName = firstName;
//        this.middleName = middleName;
        this.lastName = lastName;
//        this.nickname = nickname;
        this.address = address;
        this.homeNumber = homeNumber;
        this.email = email;
        this.group = group;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lastName, email);
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id='" + id + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public ContactData(String firstName, String lastName, String address, String homeNumber, String email, String group) {
        this.id = null;
        this.firstName = firstName;
//        this.middleName = middleName;
        this.lastName = lastName;
//        this.nickname = nickname;
        this.address = address;
        this.homeNumber = homeNumber;
        this.email = email;
        this.group = group;
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

//    public String getMiddleName() {
//        return middleName;
//    }

    public String getLastName() {
        return lastName;
    }

//    public String getNickname() {
//        return nickname;
//    }

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
