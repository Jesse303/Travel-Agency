import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DataSource {

    private final ObservableList<Customer> data
            = FXCollections.observableArrayList();

    public ObservableList<Customer> getData() {
        return data;
    }

    public DataSource() {
        // define the path to your text file
        String myFilePath = "Customers.csv";

        // read and parse the file
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(myFilePath)));
            String line, fisrstName, lastname, phoneNumber, id, email;
            // read through the first two lines to get to the data
            br.readLine();
            br.readLine();
            br.readLine();


            while ((line = br.readLine()) != null) {
                if (line.contains(",")) {

                        // do line by line parsing here
                        line = line.trim();
                        // split the line
                        String[] parts = line.split("[,]");
                        // parse out the name and email
                        fisrstName = parts[0].trim();
                        lastname = parts[1].trim();
                        phoneNumber = parts[2].trim();
                        id = parts[3].trim();
                        email = parts[4].trim();
                        data.add(new Customer(fisrstName, lastname, phoneNumber, id, email));
                    }
            }
            br.close();
        } catch (Exception e) {
            System.out.println("There was an issue parsing the file.");
        }
    }

}

