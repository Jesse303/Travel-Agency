import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class DataSourceProducts {
    
    private final ObservableList<Products> data
            = FXCollections.observableArrayList();
    
    public ObservableList<Products> getData() {
        return data;
   }
    
    public DataSourceProducts() {
        // define the path to your text file
        String myFilePath = "Products.csv";

        // read and parse the file
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(myFilePath)));
            String line, prodID, packageDeal, price;
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
                        prodID = parts[0].trim();
                        packageDeal = parts[1].trim();
                        price = parts[2].trim();
                        data.add(new Products(prodID, packageDeal, price));
                    }
            }
            br.close();
        } catch (Exception e) {
            System.out.println("There was an issue parsing the file.");
        }
    }

    
    
    
    
}
