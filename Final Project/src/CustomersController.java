import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.cell.TextFieldTableCell;
import static javafx.scene.input.KeyCode.T;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * Sample Skeleton for 'TableView.fxml' Controller Class
 */
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;


public class CustomersController {

    @FXML // fx:id="customerTable"
    private TableView<Customer> customerTable;

    @FXML // fx:id="idColumn"
    private TableColumn<Customer, String> idColumn;
        
    @FXML // fx:id="firstNameColumn"
    private TableColumn<Customer, String> firstNameColumn;

    @FXML // fx:id="lastNameColumn"
    private TableColumn<Customer, String> lastNameColumn;

    @FXML // fx:id="phoneNumberColumn"
    private TableColumn<Customer, String> phoneNumberColumn;
    
    @FXML // fx:id="emailColumn"
    private TableColumn<Customer, String> emailColumn;
    

    public void changeFirstNameCellEvent(TableColumn.CellEditEvent edittedCell) {
        System.out.println("changeFirstNameCellEvent");

        Customer customerSelected = customerTable.getSelectionModel().getSelectedItem();
        customerSelected.setFirstName(edittedCell.getNewValue().toString());
    }

    public void cancelEditFirstNameCellEvent(TableColumn.CellEditEvent edittedCell) {
        System.out.println("cancelEditFirstNameCellEvent");
    }

    public void startEditFirstNameCellEvent(TableColumn.CellEditEvent edittedCell) {
        System.out.println("startEditFirstNameCellEvent");

    }
    
    public void userClickedOnTable() {
        ObservableList table = FXCollections.observableArrayList();
    }
    
    public void changeLastNameCellEvent(TableColumn.CellEditEvent edittedCell) {
        System.out.println("changeLastNameCellEvent");
        Customer customerSelected = customerTable.getSelectionModel().getSelectedItem();
        customerSelected.setLastName(edittedCell.getNewValue().toString());
    }

    public void changeScreenButtonPushed(ActionEvent event) throws IOException {
        //BLANK BLANK BLANK BLANK
        //BLANK BLANK BLANK BLANK
    }
        
    @FXML //basically works like an onload() method
    protected void initialize() {
        //set up the columns in the table
        idColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("id"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("lastName"));
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("phone"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("email"));
        //setting up the table data source
        DataSource data = new DataSource();
        ObservableList<Customer> tableItems = data.getData();
        customerTable.setItems(tableItems);

        //Update the table to allow for the first and last name fields
        //to be editable
        customerTable.setEditable(true);
        firstNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        lastNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    }

    protected int getNewId() {
        // define the path to your text file
        String myFilePath = "Customers.csv";
        File f = new File(myFilePath);
        if (f.exists() && !f.isDirectory()) {
            String line, id = "";

            try {
                BufferedReader br = new BufferedReader(new FileReader(new File(myFilePath)));

                while ((line = br.readLine()) != null) {
                    if (line.contains(",")) {
                        line = line.trim();
                        // split the line and get the first part which is the current last id
                        String[] parts = line.split("[,]");
                        id = parts[0].trim();
                    }
                }
                br.close();
            } catch (Exception e) {
                System.out.println("There was an issue parsing the file.");
            }
            int newID = Integer.parseInt(id);
            return ++newID;
        } else {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("The File does not exist...");
            alert.setHeaderText("Creating a New File");
            alert.setContentText("We will assign 1 to the first record!");
            alert.showAndWait().ifPresent(rs -> {
                if (rs == ButtonType.OK) {
                    System.out.println("Pressed OK.");
                }
            });
            return 1;
        }
    }

    @FXML // fx:id="firstNameField"
    private TextField firstNameField;

    @FXML // fx:id="lastNameField"
    private TextField lastNameField;

    @FXML // fx:id="phoneNumberField"
    private TextField phoneNumberField;
    
    @FXML // fx:id="idField"
    private TextField idField;
        
    @FXML // fx:id="emailField"
    private TextField emailField;

    //Expression used to validate the email field
    
    public boolean validateEmail(String email) {
        Pattern pattern = Pattern.compile("[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}");
        Matcher matcher = pattern.matcher(email.toUpperCase());
            if (matcher.matches()) {
                return true; }
            else {
                   return false;
            }
    } 
  
    //Expression used to validate the phone number field
    protected boolean validatePhoneNumber(String phone) {
        Pattern pattern = Pattern.compile("\\d{3}-\\d{3}-\\d{4}");
        Matcher matcher = pattern.matcher(phone.toUpperCase());
            if (matcher.matches()) {
                return true; }
            else {
                return false;
        }
    }
    

    
    @FXML
    protected void addCustomer(ActionEvent event) {
        
        boolean validEmail = validateEmail(emailField.getText());
        boolean validPhoneNumber = validatePhoneNumber(phoneNumberField.getText());
        
        if (!validEmail || !validPhoneNumber) {
            if (!validEmail) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("The Email is not valid");
                alert.setHeaderText("Please edit the email address to the correct format");
                alert.showAndWait().ifPresent(rs -> {
                    if (rs == ButtonType.OK) {
                        System.out.println("Pressed OK.");
                    }
                });
            }

            if (!validPhoneNumber) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("The Phone Number is not valid");
                alert.setHeaderText("Please edit the phone number to the correct format");
                alert.showAndWait().ifPresent(rs -> {
                    if (rs == ButtonType.OK) {
                        System.out.println("Pressed OK.");
                    }
                });
            }
         }
            else {
        
        ObservableList<Customer> data = customerTable.getItems();
        data.add(new Customer(
                Integer.toString(getNewId()),
                firstNameField.getText(),
                lastNameField.getText(),
                phoneNumberField.getText(),
                emailField.getText()
        ));

        firstNameField.setText("");
        lastNameField.setText("");
        phoneNumberField.setText("");
        emailField.setText("");
        
        saveCustomers(data);
        }
    }
        
    //Saving the customer information to the Customers.csv file
    protected void saveCustomers(ObservableList<Customer> data) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("Customers.csv", false))) {
            String id = "";
            String firstName = "";
            String lastName = "";
            String phoneNumber = "";
            String email = "";

            String header = "=============,=============,=============,=============,===============\n";
            header += "ID Number, First Name, Last Name, Phone Number, Email\n";
            header += "=============,=============,=============,=============,===============\n";
            bw.write(header);

            for (Customer customer : data) {
                id = customer.getId();
                firstName = customer.getFirstName();
                lastName = customer.getLastName();
                phoneNumber = customer.getPhone();
                email = customer.getEmail();
                String newLine = id + "," + firstName + "," + lastName + "," + phoneNumber + "," + email;
                bw.write(newLine);
                bw.newLine();
            }

            if (bw != null) {
                bw.close();
            }
        } catch (IOException e) {
            e.printStackTrace();

        } finally {

        }
    }
    
    @FXML
    protected void deleteCustomer(ActionEvent event) {
        System.out.println("delete Customer");
        //Check whether item is selected and set value of selected item to Label
        if (customerTable.getSelectionModel().getSelectedItem() != null) {

            int selectedIndex = customerTable.getSelectionModel().getSelectedIndex();
            System.out.println("Selected Index: " + selectedIndex);

            if (selectedIndex >= 0) {
                customerTable.getItems().remove(selectedIndex);
                ObservableList<Customer> data = customerTable.getItems();
                saveCustomers(data);
            } 

        }

    }
    
    @FXML
    protected void updateCustomer(ActionEvent event) {

        System.out.println("update Customer");
        int selectedIndex = customerTable.getSelectionModel().getSelectedIndex();
        System.out.println("Selected Index: " + selectedIndex);

        if (selectedIndex >= 0) {
            ObservableList<Customer> data = customerTable.getItems();
            saveCustomers(data);
        } else {
            
        }
    }
    
    public void handle(MouseEvent t) {
        ObservableList table = FXCollections.observableArrayList();
        
        for (int i = 0; i <= customerTable.getColumns().size(); i++) {
            table.add(customerTable.getSelectionModel().getSelectedItems().get(0).toString());
        }
            System.out.println("Table row clicked");
        
    }

    @FXML
    protected void searchCustomers(ActionEvent event){
        ObservableList<Customer> data = customerTable.getItems();
        
        ObservableList<Customer> searchResult = FXCollections.observableArrayList();
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        for (Customer c : data ) {
            
            if (c.getFirstName().trim() != "" && c.getLastName().trim() != ""){
                if (c.getFirstName().trim().equalsIgnoreCase(firstName) && c.getLastName().trim().equalsIgnoreCase(lastName)) {
                    searchResult.add(c);
                }
                
            }
            if (c.getFirstName().trim() != ""){
                if (c.getFirstName().trim().trim().equalsIgnoreCase(firstName)){
                    searchResult.add(c);
                }
            }
            if (c.getLastName().trim() != ""){
                if (c.getLastName().trim().equalsIgnoreCase(lastName)) {
                    searchResult.add(c);
                }
            }
        }
        customerTable.setItems(searchResult);
    }
    
}
    
 
