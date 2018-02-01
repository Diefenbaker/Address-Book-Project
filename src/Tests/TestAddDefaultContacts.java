package Tests;

import org.junit.Test;

import static AddressBookApp.AddressBook.addDefaultContacts;
import static AddressBookApp.AddressBook.contactsArrayList;
import static org.junit.Assert.assertEquals;

public class TestAddDefaultContacts {

    @Test
    public void testAddDefaultContacts() {
        System.out.println("Testing that 5 default contacts are being created and added to the contacts ArrayList");
        addDefaultContacts();
        assertEquals("ArrayList size must be 5", 5, contactsArrayList.size());
    }

}
