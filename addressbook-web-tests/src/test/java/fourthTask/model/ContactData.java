package fourthTask.model;

public class ContactData {
    private final String firstName;
 //   private final String middleName;
    private String lastName;
 //   private final String nickname;
    private final String address;
    private final String homeNumber;
    private final String email;
    private String group;

    public ContactData(String firstName, String lastName, String address, String homeNumber, String email, String group) {
        this.firstName = firstName;
//        this.middleName = middleName;
        this.lastName = lastName;
//        this.nickname = nickname;
        this.address = address;
        this.homeNumber = homeNumber;
        this.email = email;
        this.group = group;
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
