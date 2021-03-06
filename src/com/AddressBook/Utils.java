/*
 * Util class for all the utilities
 */
package AddressBook;

import java.util.*;
import java.io.*;

public class Utils {
    Scanner readIn = new Scanner(System.in);
    /*
     * Prints the Person Details stored in CSV
     */
    public void showAddressBook() {
        String line = "";
        String splitBy = ",";
        try {
            //parsing a CSV file into BufferedReader class constructor  
            BufferedReader bufferead = new BufferedReader(new FileReader("src/CSVFiles/AddressBook.csv"));
            while ((line = bufferead.readLine()) != null) {
                String[] personData = line.split(splitBy); // use comma as separator  
                System.out.println("First Name= " + personData[0] + ", Last Name= " + personData[1] + ", Address=" + personData[2] + ", City=" + personData[3] + ", State= " + personData[4] + ", Zip Code= " + personData[5] + ", Phone Number= " + personData[6]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /*
     * Edits Person details and writes into the file
     * @throws IOExcetion
     */
    public void updatePerson() throws IOException {
        String updateLine, newAddress = "", newString = "", splitBy = ",", oldContent = "", newCity = "", newState = "", newZipcode = "", newPhone = "";
        //Open AddressBook.csv
        BufferedReader reader = new BufferedReader(new FileReader("src/CSVFiles/AddressBook.csv"));
        System.out.println("Enter the Person Serial you want to Edit");
        int lineNumber = readIn.nextInt();
        //Iterate over all lines and create one string of all
        String allLines = reader.readLine();
        while (allLines != null) {
            oldContent = oldContent + allLines + System.lineSeparator();
            allLines = reader.readLine();
        }
        //Go to the line number specified by user
        BufferedReader read = new BufferedReader(new FileReader("src/CSVFiles/AddressBook.csv"));
        for (int i = 0; i < lineNumber; i++) {
            String line = read.readLine();
        }
        updateLine = read.readLine();
        //Break down the line into string array
        String[] personData = updateLine.split(splitBy);
        newAddress = personData[2];
        newCity = personData[3];
        newState = personData[4];
        newZipcode = personData[5];
        newPhone = personData[6];

        System.out.println("What do you want to Edit 1. Address \n2. City \n3. State \n4. ZipCode \n5.Phone Number");
        int n = readIn.nextInt();
        switch (n) {
            case 1:
                System.out.println("Enter New Address");
                newAddress = readIn.next();
                break;
            case 2:
                System.out.println("Enter New City");
                newCity = readIn.next();
                break;
            case 3:
                System.out.println("Enter New State");
                newState = readIn.next();
                break;
            case 4:
                System.out.println("Enter New Zip Code");
                newZipcode = readIn.next();
                break;
            case 5:
                System.out.println("Enter New Phone Number");
                newPhone = readIn.next();
                break;
            default:
                System.out.println("Invalid");
        }
        //Make a new string with the updated data
        newString = personData[0] + "," + personData[1] + "," + newAddress + "," + newCity + "," + newState + "," + newZipcode + "," + newPhone;
        //Replace the line with the new updated line
        String newContent = oldContent.replaceAll(updateLine, newString);
        FileWriter writer = new FileWriter("src/CSVFiles/AddressBook.csv");
        //Write the whole string again
        writer.write(newContent);
        reader.close();
        writer.close();
    }
    /*
     * Deletes all the details of a Person from file
     * @throws IOException
     */
    public void deletePerson() throws IOException {
        String updateLine = "", newString = "", splitBy = ",", oldContent = "", nextLine = "", line = "nope";
        //Open AddressBook.csv
        BufferedReader reader = new BufferedReader(new FileReader("src/CSVFiles/AddressBook.csv"));
        System.out.println("Enter the Person Serial you want to Delete");
        int lineNumber = readIn.nextInt();
        //Iterate over all lines and create one string of all
        String allLines = reader.readLine();
        while (allLines != null) {
            oldContent = oldContent + allLines + System.lineSeparator();
            allLines = reader.readLine();
        }
        //Go to the line number specified by user
        BufferedReader read = new BufferedReader(new FileReader("src/CSVFiles/AddressBook.csv"));
        for (int i = 0; line != null; i++) {
            line = read.readLine();
            if (i >= lineNumber && line != null) {
                updateLine = updateLine + line + System.lineSeparator();
            }
        }
        BufferedReader readin = new BufferedReader(new FileReader("src/CSVFiles/AddressBook.csv"));
        for (int j = 0; j != lineNumber; j++) {
            nextLine = readin.readLine();
        }
        nextLine = readin.readLine();
        nextLine = readin.readLine();
        while (nextLine != null) {
            newString = newString + nextLine + System.lineSeparator();
            nextLine = readin.readLine();
        }
        String newContent = oldContent.replace(updateLine, newString);
        System.out.println("Person " + lineNumber + " Deleted");
        FileWriter writer = new FileWriter("src/CSVFiles/AddressBook.csv");
        //Write the whole string again
        writer.write(newContent);
        reader.close();
        writer.close();
    }
    /*
     * Overrides equals method
     * @params firstName, lastName
     * returns boolean
     */
    public boolean equals(String firstName, String lastName) {
        String line = "";
        String splitBy = ",";
        try {
            //parsing a CSV file into BufferedReader class constructor  
            BufferedReader bufferead = new BufferedReader(new FileReader("src/CSVFiles/AddressBook.csv"));
            while ((line = bufferead.readLine()) != null) {
                String[] personData = line.split(splitBy); // use comma as separator  
                if (personData[0].equals(firstName) && personData[1].equals(lastName)) {
                    System.out.println("Duplicate found");
                    return true;
                }
            }
         } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    /*
     * Sorts Person by Last Name, City, State or Zipcode
     */
    public void sortContacts() {
        int option;
        while (true) {
            System.out.println("What do you want to sort by \n1. Last Name \n3. City \n4. State\n5. Zip Code");
            option = readIn.nextInt();
            if (!(option < 1 || option > 4 || option == 2)) {
                System.out.println("Invalid");
                break;
            }
        }
         String line = "";
        String cvsSplitBy = ",";
        List < List < String >> personData = new ArrayList < > ();
        try (BufferedReader br = new BufferedReader(new FileReader("src/CSVFiles/AddressBook.csv"))) {
            while ((line = br.readLine()) != null) {
                personData.add(Arrays.asList(line.split(cvsSplitBy)));
            }
            personData.sort(new Comparator < List < String >> () {
                int option2;
                public int compare(List < String > list1, List < String > list2) {
                    return list1.get(option2).compareTo(list2.get(option2));
                }
                public Comparator init(int option) {
                    option2 = option;
                    return this;
                }
            }.init(option));
            for (List < String > rowData: personData) {
                System.out.println(rowData);
            }
         } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Sorted");
    }
    /*
     * Prints Person and their corresponding states or cities
     */
    public void viewPersonDictionary() {
        Dictionary viewPersonByCity = new Hashtable();
        Dictionary viewPersonByState = new Hashtable();
        String line = "";
        String splitBy = ",";
        try {
            //parsing a CSV file into BufferedReader class constructor  
            BufferedReader bufferead = new BufferedReader(new FileReader("src/CSVFiles/AddressBook.csv"));
            while ((line = bufferead.readLine()) != null) {
                String[] personData = line.split(splitBy); // use comma as separator  
                viewPersonByCity.put(personData[3], personData[0] + " " + personData[1]);
                viewPersonByState.put(personData[4], personData[0] + " " + personData[1]);
            }
            System.out.println(viewPersonByCity);
            System.out.println(viewPersonByState);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("1. View Person By State \n2. View Person By City");
        int option = readIn.nextInt();
        if (option == 1) {
            for (Enumeration i = viewPersonByState.keys(); i.hasMoreElements();) {
                String keys = i.nextElement().toString();
                System.out.print("State : " + keys);
                System.out.println(" Person : " + viewPersonByState.get(keys));
            }
        }
        if (option == 2) {
            for (Enumeration i = viewPersonByCity.keys(); i.hasMoreElements();) {
                String keys = i.nextElement().toString();
                System.out.print("City : " + keys);
                System.out.println(" Person : " + viewPersonByCity.get(keys));
            }
        } else
            System.out.println("Invalid");
    }
    /*
     * Searches for Person in a city or state
     */
    public void searchPerson() {

        int option = 0, flag = 0;
        String key = "";
        String line = "";
        String splitBy = ",";
        System.out.println("1. Search By City \n2. Search by State");
        switch (readIn.nextInt()) {
            case 1:
                System.out.println("Enter the City");
                key = readIn.next();
                option = 3;
                break;
            case 2:
                System.out.println("Enter the State");
                key = readIn.next();
                option = 4;
                break;
            default:
                System.out.println("Invalid");
                break;
        }
        try {
            //parsing a CSV file into BufferedReader class constructor  
            BufferedReader bufferead = new BufferedReader(new FileReader("src/CSVFiles/AddressBook.csv"));
            System.out.println("People in " + key + ":");
            while ((line = bufferead.readLine()) != null) {
                String[] personData = line.split(splitBy); // use comma as separator  
                if (personData[option].equals(key)) {
                    System.out.println(personData[0] + " " + personData[1]);
                    flag = 1;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (flag == 0)
            System.out.println("None");
    }
}