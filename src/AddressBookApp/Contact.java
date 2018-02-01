package AddressBookApp;

import java.util.Comparator;

public class Contact {

    private String firstName, lastName, telNo, email, location;

    public Contact(String firstName, String lastName,
                   String telNo, String email, String location) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.telNo = telNo;
        this.email = email;
        this.location = location;
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

    public String getTelNo() {
        return telNo;
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


    //Methods to sort contacts ArrayList by first or last name
    public static Comparator<Contact> sortByFirstName = new Comparator<Contact>() {
        public int compare(Contact first, Contact second) {
            return first.firstName.toLowerCase().compareTo(second.firstName.toLowerCase());
        }
    };

    public static Comparator<Contact> sortByLastName = new Comparator<Contact>() {
        public int compare(Contact first, Contact second) {
            return first.lastName.toLowerCase().compareTo(second.lastName.toLowerCase());
        }
    };

}
