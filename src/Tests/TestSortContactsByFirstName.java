package Tests;

import org.junit.Test;

import AddressBookApp.AddressBook;

import static org.junit.Assert.assertEquals;

public class TestSortContactsByFirstName extends AddressBook{

    @Test
    public void testSortContactsByFirstName() {

        contactsArrayList.clear(); //adding this to allow TestSuite to run correctly

        System.out.println("Testing that sort by first name method is working correctly, Elaine Delaney" +
                " should be the second contact in list");
        addDefaultContacts();
        sortByFirstName();
        assertEquals("Second contact should be Elaine Delaney", "Elaine Delaney",
                contactsArrayList.get(1).getFirstName()+" "+contactsArrayList.get(1).getLastName());
    }

}
