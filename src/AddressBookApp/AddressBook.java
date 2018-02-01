package AddressBookApp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class AddressBook {

    //using Scanner for menu options
    Scanner input = new Scanner(System.in);

    //using BufferedReader to take in contact details strings
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    //Creating ArrayList of type Contact to store default and newly added objects
    public static ArrayList<Contact> contactsArrayList = new ArrayList<Contact>();


    public static void main(String[] args) throws IOException{

        addDefaultContacts(); //calling method to add 5 contacts, located around line 520

        //Creating and running new instance of Address Book
        AddressBook app = new AddressBook();
        app.mainMenu();
    }


    //Start of Main Menu
    public void mainMenu() throws IOException{

        int option = 0;

        do{
            //Main Menu layout
            System.out.println("------------------");
            System.out.println("***Address Book***");
            System.out.println("------------------");
            System.out.println(contactsArrayList.size()+" contacts in address book.");
            System.out.println("");
            System.out.println("1. View all contacts");
            System.out.println("2. View all details for one contact");
            System.out.println("3. Add contact");
            System.out.println("4. Edit contact");
            System.out.println("5. Remove one contact");
            System.out.println("6. Remove all contacts");
            System.out.println("0. Close address book");
            System.out.println("");
            System.out.print("Select option(0-6) --> ");

            //taking in user selection
            option = input.nextInt();
            System.out.println("");

            switch(option){
                case 1:
                    viewContactsMenu(); //method located around line 97
                    break;

                case 2:
                    viewOneContactMenu(); //method located around line 144
                    break;

                case 3:
                    addContactMenu(); //method located around line 220
                    break;

                case 4:
                    editContactMenu(); //method located around line 297
                    break;

                case 5:
                    removeContactMenu(); //method located around line 402
                    break;

                case 6:
                    removeAllContactsMenu(); //method located around line 437
                    break;

                case 0:
                    close(); //method located around line 480
                    break;

                default:
                    System.out.println("Invalid selection, please try again.");
                    System.out.println("");
            }
        }while(option!=0);
    }
    //End of Main Menu


    //Start of View Contacts Methods
    public void viewContactsMenu() throws IOException{

        if(!contactsArrayList.isEmpty()) { //only display this menu if address book has contacts

            String sortChoice = "";

            //Initial View Contacts menu, offering user ways to sort contacts
            System.out.println("");
            System.out.println("------------------");
            System.out.println("***All Contacts***");
            System.out.println("------------------");
            System.out.println("Choose sort method -");
            System.out.print("enter F/f for first name, L/l for last name --> ");

            sortChoice = input.next();
            System.out.println("");

            switch (sortChoice.toLowerCase()) {
                case "f":
                    sortByFirstName(); //calling method that sorts ArrayList by first name, located around line 182
                    System.out.println("");
                    System.out.println("Press enter to continue");
                    br.readLine();
                    mainMenu();
                    break;

                case "l":
                    sortByLastName(); //calling method that sorts ArrayList by last name, located around line 188
                    System.out.println("");
                    System.out.println("Press enter to continue");
                    br.readLine();
                    mainMenu();
                    break;

                default:
                    System.out.println("Invalid selection.");
                    System.out.println("");
                    viewContactsMenu(); //reopening View Contacts menu
            }
        }else{
            System.out.println("No contacts in address book.");
            System.out.println("");
            mainMenu();
        }
    }


    public void viewOneContactMenu() throws IOException{

        if(!contactsArrayList.isEmpty()) {

            System.out.println("--------------------------------");
            System.out.println("***One Contact - Full Details***");
            System.out.println("--------------------------------");

            listContactsByID(); //calling contacts ID method, located at line 505

            System.out.println("");
            System.out.print("Enter ID of contact --> ");

            int enteredID = input.nextInt();
            System.out.println("");

            try {
                Contact selectedContact = contactsArrayList.get(enteredID - 1);
                System.out.println(selectedContact.getFirstName() + " " + selectedContact.getLastName());
                System.out.println(selectedContact.getLocation());
                System.out.println(selectedContact.getTelNo());
                System.out.println(selectedContact.getEmail());
                System.out.println("");
                System.out.println("Press enter to continue");
                br.readLine();
                mainMenu();
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Error: this ID does not exist, recheck and try again");
                System.out.println("");
                viewOneContactMenu();
            }
        }else{
            System.out.println("No contacts in address book.");
            System.out.println("");
            mainMenu();
        }
    }

    public void sortByFirstName(){
        Collections.sort(contactsArrayList, Contact.sortByFirstName); //sorting ArrayList using Comparator method in Contact class
        String sortedBy = "Sorted by First Name"; //header for sorted array display
        displaySortedArray(sortedBy); //calling display method, passing in header
    }

    public void sortByLastName(){
        Collections.sort(contactsArrayList, Contact.sortByLastName); //sorting ArrayList using Comparator method in Contact class
        String sortedBy = "Sorted by Last Name"; //header for sorted array display
        displaySortedArray(sortedBy); //calling display method, passing in header
    }


    public void displaySortedArray(String sortedBy){

        System.out.println("");
        System.out.println("***"+sortedBy+"***");
        System.out.println("");

        //determining which layout to use based on header passed in
        if(sortedBy.contains("First")){
            for(Contact thisContact:contactsArrayList){
                System.out.println(thisContact.getFirstName()+" "+thisContact.getLastName());
                System.out.println(thisContact.getLocation());
                System.out.println("");
            }
        }else{
            for(Contact thisContact:contactsArrayList){
                System.out.println(thisContact.getLastName()+", "+thisContact.getFirstName());
                System.out.println(thisContact.getLocation());
                System.out.println("");
            }
        }
    }
    //End of View Contacts Methods


    //Start of Add Contact Methods
    public void addContactMenu() throws IOException{

        String firstName, lastName, telNo, email, location, confirmInput;

        System.out.println("---------------------");
        System.out.println("***Add New Contact***");
        System.out.println("---------------------");
        System.out.println("Enter first name --> ");
        firstName = br.readLine();
        System.out.println("Enter last name --> ");
        lastName = br.readLine();
        System.out.println("Enter phone number --> ");
        telNo = br.readLine();
        System.out.println("Enter email address --> ");
        email = br.readLine();
        System.out.println("Enter home/office address --> ");
        location = br.readLine();
        System.out.println("");

        System.out.println("***Confirm Details***");
        System.out.println("Name: "+firstName+" "+lastName);
        System.out.println("Phone No.: "+telNo);
        System.out.println("Email: "+email);
        System.out.println("Address: "+location);
        System.out.println("");

        if(firstName.isEmpty() || lastName.isEmpty() || telNo.isEmpty() //if any fields are empty give error to user
                || email.isEmpty() || location.isEmpty()){
            System.out.println("Error: one or more fields are empty!");
            System.out.print("Reset(R/r), Cancel(C/c) --> ");
            confirmInput = br.readLine();
        }else{
            System.out.print("Add(A/a), Reset(R/r), Cancel(C/c) --> ");
            confirmInput = br.readLine();
        }

        switch(confirmInput.toLowerCase()){
            case "a":
                try{
                    addContactToList(firstName, lastName, telNo, email, location); //calling add method, passing in Strings
                    System.out.println("Success: "+firstName+" "+lastName+" added.");
                    System.out.println("");
                    System.out.println("Press enter to continue");
                    br.readLine();
                    mainMenu();
                }catch(Exception e){
                    System.out.println("Error: "+e.getMessage());
                }
                break;

            case "r":
                addContactMenu(); //resetting screen by launching this menu again
                break;

            case "c":
                mainMenu();
                break;

            default:
                System.out.println("Invalid selection, please try again --> ");
                confirmInput = br.readLine();
                System.out.println("");
        }
    }

    public void addContactToList(String firstName, String lastName, String telNo,
                                 String email, String location){

        Contact newContact = new Contact(firstName, lastName,
                telNo, email, location);
        contactsArrayList.add(newContact);

    }
    //End of Add Contact Methods


    //Start of Edit Contact Methods
    public void editContactMenu() throws IOException{

        if(!contactsArrayList.isEmpty()) { //only show menu if address book has contacts in it

            System.out.println("--------------------------");
            System.out.println("***Edit Contact Details***");
            System.out.println("--------------------------");

            listContactsByID(); //calling contacts ID method, located at line 505

            System.out.println("");
            System.out.print("Select the ID of the contact you want to edit --> ");
            int idSelection = input.nextInt();

            try {
                Contact selectedContact = contactsArrayList.get(idSelection - 1); //User list starts at ID 1, -1 to
                                                                                  //extract correct Contact object
                System.out.println("");
                System.out.println("***Current Details***");
                System.out.println(selectedContact.getFirstName() + " " + selectedContact.getLastName());
                System.out.println(selectedContact.getLocation());
                System.out.println(selectedContact.getTelNo());
                System.out.println(selectedContact.getEmail());
                System.out.println("");

                String firstName, lastName, telNo, email, location, confirmInput;

                System.out.println("");
                System.out.println("Edit first name (press enter to leave unchanged) --> ");
                firstName = br.readLine();
                System.out.println("Edit last name (press enter to leave unchanged) --> ");
                lastName = br.readLine();
                System.out.println("Edit phone number (press enter to leave unchanged) --> ");
                telNo = br.readLine();
                System.out.println("Edit email address (press enter to leave unchanged) --> ");
                email = br.readLine();
                System.out.println("Edit home/office address (press enter to leave unchanged) --> ");
                location = br.readLine();
                System.out.println("");

                if (firstName.isEmpty()) {
                    firstName = selectedContact.getFirstName(); //if user hasn't entered new String use old details
                }
                if (lastName.isEmpty()) {
                    lastName = selectedContact.getLastName(); //if user hasn't entered new String use old details
                }
                if (telNo.isEmpty()) {
                    telNo = selectedContact.getTelNo(); //if user hasn't entered new String use old details
                }
                if (email.isEmpty()) {
                    email = selectedContact.getEmail(); //if user hasn't entered new String use old details
                }
                if (location.isEmpty()) {
                    location = selectedContact.getLocation(); //if user hasn't entered new String use old details
                }

                System.out.println("***Confirm Edits***");
                System.out.println("Name: " + firstName + " " + lastName);
                System.out.println("Phone No.: " + telNo);
                System.out.println("Email: " + email);
                System.out.println("Address: " + location);
                System.out.println("");

                System.out.println("Add(A/a), Cancel(C/c) --> ");
                confirmInput = br.readLine();

                switch (confirmInput.toLowerCase()) {
                    case "a":
                        Contact editedContact = new Contact(firstName, lastName,
                                telNo, email, location);
                        contactsArrayList.set(idSelection - 1, editedContact); //overwriting old object with new
                        System.out.println("");
                        System.out.println("Edit Successful: " + firstName + " " + lastName + " saved.");
                        System.out.println("");
                        System.out.println("Press enter to continue");
                        br.readLine();
                        mainMenu();
                        break;

                    case "c":
                        System.out.println("Edit cancelled.");
                        System.out.println("");
                        mainMenu();
                        break;

                    default:
                        System.out.println("Invalid selection, please try again --> ");
                        confirmInput = br.readLine();
                        System.out.println("");
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Error: this ID does not exist, recheck and try again");
                System.out.println("");
                editContactMenu();
            }
        }else{
            System.out.println("No contacts in address book.");
            System.out.println("");
            mainMenu();
        }
    }
    //End of Edit Contact Methods


    //Start of Remove Contact Methods
    public void removeContactMenu() throws IOException{

        if(!contactsArrayList.isEmpty()){

            System.out.println("--------------------");
            System.out.println("***Remove Contact***");
            System.out.println("--------------------");

            listContactsByID(); //calling contacts ID method, located at line 505

            System.out.println("");
            System.out.println("Select the ID of the contact you want to remove --> ");
            int idSelection = input.nextInt();

            try{
                Contact selectedContact = contactsArrayList.get(idSelection-1);
                String contactName = selectedContact.getFirstName()+" "+selectedContact.getLastName();
                contactsArrayList.remove(selectedContact);
                System.out.println("Success: "+contactName+" removed.");
                System.out.println("");
                System.out.println("Press enter to continue");
                br.readLine();
                mainMenu();
            }catch(IndexOutOfBoundsException e){
                System.out.println("Error: this ID does not exist, recheck and try again");
                System.out.println("");
                removeContactMenu();
            }
        }else{
            System.out.println("No contacts in address book.");
            System.out.println("");
            mainMenu();
        }
    }

    public void removeAllContactsMenu() throws IOException{

        if(!contactsArrayList.isEmpty()){
            System.out.println("-------------------------");
            System.out.println("***Remove All Contacts***");
            System.out.println("-------------------------");
            System.out.println("");
            System.out.print("Are you sure you want to empty the address book?(Y/y/N/n) --> ");

            String choice = input.next();

            switch(choice.toLowerCase()){
                case "y":
                    contactsArrayList.clear();
                    System.out.println("Success: All contacts removed.");
                    System.out.println("");
                    System.out.println("Press enter to continue");
                    br.readLine();
                    mainMenu();
                    break;

                case "n":
                    System.out.println("");
                    mainMenu();
                    break;

                default:
                    System.out.println("Invalid selection, please try again.");
                    System.out.println("");
                    removeAllContactsMenu();
            }
        }else{
            System.out.println("There are no contacts to remove.");
            System.out.println("");
            System.out.println("Press enter to continue");
            br.readLine();
            mainMenu();
        }
    }
    //End of Remove Contact Methods


    //Start of Close Method
    public void close() throws IOException{
        String confirmation;

        System.out.print("Close address book? (Y/y,N/n) --> ");

        confirmation = input.next();
        System.out.println("");

        switch(confirmation.toLowerCase()){
            case "y":
                System.out.println("Address book closed.");
                System.exit(0);
                break;

            case "n":
                mainMenu();
                break;

            default:
                System.out.println("Invalid selection.");
        }
    }
    //End of Close Method


    public void listContactsByID(){

        System.out.println("ID  NAME");
        System.out.println("--  ----");

        Collections.sort(contactsArrayList, Contact.sortByLastName);

        for(int i = 00; i<contactsArrayList.size(); i++){
            Contact thisContact = contactsArrayList.get(i);
            System.out.println((i+1)+"   "+thisContact.getLastName()+", "+thisContact.getFirstName());
        }

    }


    public static void addDefaultContacts(){

        //Creating 5 default contacts using Contact object
        Contact defaultContact1 = new Contact("Brian","Phillips",
                "087 142 6795","bphillipswex@outlook.com","92 Corish Park, Wexford Town");
        Contact defaultContact2 = new Contact("Elaine","Delaney",
                "083 784 7744","elainedelaney@live.ie","Cork Road, Waterford City");
        Contact defaultContact3 = new Contact("Mary","Burke",
                "091 913 5432","maryburke@esatclear.ie","Murrintown, Co. Wexford");
        Contact defaultContact4 = new Contact("Paul","Scallan",
                "053 915 8098","paulscallan@gmail.com","25 Castlewoods, Piercestown, Wexford");
        Contact defaultContact5 = new Contact("Holly","O'Brien",
                "051 511 4523","hollyobrien@hotmail.com","17 Main Street, Waterford City");

        //Adding default contacts to ArrayList
        contactsArrayList.add(defaultContact1);
        contactsArrayList.add(defaultContact2);
        contactsArrayList.add(defaultContact3);
        contactsArrayList.add(defaultContact4);
        contactsArrayList.add(defaultContact5);

    }
}