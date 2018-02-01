package Tests;

import AddressBookApp.AddressBook;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestSortContactsByLastName extends AddressBook {

    @Test
    public void testSortContactsByLastName() {

        contactsArrayList.clear(); //adding this to allow TestSuite to run correctly

        System.out.println("Testing that sort by last name method is working correctly, Mary Burke" +
                " should be the first contact in list");
        addDefaultContacts();
        sortByLastName();
        assertEquals("First contact should be Mary Burke", "Mary Burke",
                contactsArrayList.get(0).getFirstName()+" "+contactsArrayList.get(0).getLastName());
    }

}
