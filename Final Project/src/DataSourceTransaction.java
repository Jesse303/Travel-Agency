import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DataSourceTransaction {

    private final ObservableList<Transaction> data
            = FXCollections.observableArrayList();

    public ObservableList<Transaction> getData() {
        return data;
    }
    

    public DataSourceTransaction() {
        // define the path to your text file
        String myFilePath = "Transactions.csv";

        // read and parse the file
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(myFilePath)));
            String line, id, date, total;
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
                        id = parts[0].trim();
                        date = parts[1].trim();
                        total = parts[2].trim();
                        data.add(new Transaction(id, date, total));
                    }
            }
            br.close();
        } catch (Exception e) {
            System.out.println("There was an issue parsing the file.");
        }
    }
    
    
    

}