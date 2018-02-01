package Tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)
@Suite.SuiteClasses({
        TestAddDefaultContacts.class,
        TestSortContactsByFirstName.class,
        TestSortContactsByLastName.class,
        TestAddContactToList.class
})

public class TestSuite {

}