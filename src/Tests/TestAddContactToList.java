package Tests;

import AddressBookApp.AddressBook;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestAddContactToList extends AddressBook{

    @Test
    public void testAddContactToList() {

        contactsArrayList.clear(); //adding this to allow TestSuite to run correctly

        System.out.println("Testing that new contacts are added to list successfully.");
        addDefaultContacts();
        addContactToList("Brian","Phillips","087 142 6795","bphillipswex@outlook.com",
                "92 Corish Park, Wexford Town");
        assertEquals("ArrayList size must be 6", 6, contactsArrayList.size());
    }

}
