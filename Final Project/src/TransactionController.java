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
import javafx.scene.input.MouseEvent;

public class TransactionController {

    @FXML // fx:id="customerTable"
    private TableView<Transaction> transactionTable;

    @FXML // fx:id="firstNameColumn"
    private TableColumn<Transaction, String> idColumn;

    @FXML // fx:id="lastNameColumn"
    private TableColumn<Transaction, String> dateColumn;

    @FXML // fx:id="phoneNumberColumn"
    private TableColumn<Transaction, String> totalColumn;
    
    public void changeDateCellEvent(TableColumn.CellEditEvent edittedCell) {
        System.out.println("changeDateCellEvent");

        Transaction transactionSelected = transactionTable.getSelectionModel().getSelectedItem();
        transactionSelected.setDate(edittedCell.getNewValue().toString());
    }
    
    public void changeTotalCellEvent(TableColumn.CellEditEvent edittedCell) {
        System.out.println("changeTotalCellEvent");

        Transaction transactionSelected = transactionTable.getSelectionModel().getSelectedItem();
        transactionSelected.setTotal(edittedCell.getNewValue().toString());
    }
    
    public void cancelEditDateCellEvent(TableColumn.CellEditEvent edittedCell) {
        System.out.println("cancelEditDateCellEvent");
    }

    public void startEditDateCellEvent(TableColumn.CellEditEvent edittedCell) {
        System.out.println("startEditDateCellEvent");

    }
    
    public void userClickedOnTable() {
        ObservableList table = FXCollections.observableArrayList();
    }
    
    public void changeScreenButtonPushed(ActionEvent event) throws IOException {
        //BLANK BLANK BLANK BLANK
        //BLANK BLANK BLANK BLANK
    }

    @FXML //basically works like an onload() method
    protected void initialize() {

        //setting up the columns
        PropertyValueFactory<Transaction, String> idProperty
                = new PropertyValueFactory<Transaction, String>("id");

        PropertyValueFactory<Transaction, String> dateProperty
                = new PropertyValueFactory<Transaction, String>("date");

        PropertyValueFactory<Transaction, String> totalProperty
                = new PropertyValueFactory<Transaction, String>("total");

        idColumn.setCellValueFactory(idProperty);
        dateColumn.setCellValueFactory(dateProperty);
        totalColumn.setCellValueFactory(totalProperty);

        //setting up the table data source
        DataSourceTransaction data = new DataSourceTransaction();
        ObservableList<Transaction> tableItems = data.getData();
        transactionTable.setItems(tableItems);
        
        transactionTable.setEditable(true);
        dateColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        totalColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    }

    @FXML // fx:id="id"
    private TextField idField;

    @FXML // fx:id="id"
    private TextField dateField;

    @FXML // fx:id="id"
    private TextField totalField;

    @FXML
    protected void addTransaction(ActionEvent event) {
        ObservableList<Transaction> data2 = transactionTable.getItems();
        data2.add(new Transaction(
                idField.getText(),
                dateField.getText(),
                totalField.getText()
        ));

        idField.setText("");
        dateField.setText("");
        totalField.setText("");

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("Transactions.csv", false))) {
            String id = "";
            String date = "";
            String total = "";

            String header = "=============,=============,===============\n";
            header += "Transaction ID, Date, Total\n";
            header += "============,=============,===============\n";
            bw.write(header);

            for (Transaction transaction : data2) {
                id = transaction.getId();
                date = transaction.getDate();
                total = transaction.getTotal();
                String newLine = id + "," + date + "," + total;
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
    
    //Saving the transaction information to the Transaction.csv file
    protected void saveTransactions(ObservableList<Transaction> data) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("Transactions.csv", false))) {
            String id = "";
            String date = "";
            String total = "";


            String header = "=============,=============,=============\n";
            header += "Transaction ID, Date, Total\n";
            header += "=============,=============,=============\n";
            bw.write(header);

            for (Transaction transaction : data) {
                id = transaction.getId();
                date = transaction.getDate();
                total = transaction.getTotal();
                String newLine = id + "," + date + "," + total;
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
    protected void deleteTransaction(ActionEvent event) {
        System.out.println("delete Transaction");
        //Check whether item is selected and set value of selected item to Label
        if (transactionTable.getSelectionModel().getSelectedItem() != null) {

            int selectedIndex = transactionTable.getSelectionModel().getSelectedIndex();
            System.out.println("Selected Index: " + selectedIndex);

            if (selectedIndex >= 0) {
                transactionTable.getItems().remove(selectedIndex);
                ObservableList<Transaction> data = transactionTable.getItems();
                saveTransactions(data);
            } 

        }

    }
    
     @FXML
    protected void searchTransactions(ActionEvent event) {
        ObservableList<Transaction> data = transactionTable.getItems();

        ObservableList<Transaction> searchResult = FXCollections.observableArrayList();
        String date = dateField.getText();
        String total = totalField.getText();
        for (Transaction c : data) {

            if (c.getDate().trim() != "" && c.getTotal().trim() != "") {
                if (c.getDate().trim().equalsIgnoreCase(date) && c.getTotal().trim().equalsIgnoreCase(total)) {
                    searchResult.add(c);
                }
            }
            if (c.getDate().trim() != "") {
                if (c.getDate().trim().equalsIgnoreCase(date)) {
                    searchResult.add(c);
                }
            }
            if (c.getTotal().trim() != "") {
                if (c.getTotal().trim().equalsIgnoreCase(total)) {
                    searchResult.add(c);
                }
            }
        }
        transactionTable.setItems(searchResult);
    }
    
    @FXML
    protected void updateTransaction(ActionEvent event) {

        System.out.println("update Transaction");
        int selectedIndex = transactionTable.getSelectionModel().getSelectedIndex();
        System.out.println("Selected Index: " + selectedIndex);

        if (selectedIndex >= 0) {
            ObservableList<Transaction> data = transactionTable.getItems();
            saveTransactions(data);
        } else {
            
        }
    }
    
    public void handle(MouseEvent t) {
        ObservableList table = FXCollections.observableArrayList();
        
        for (int i = 0; i <= transactionTable.getColumns().size(); i++) {
            table.add(transactionTable.getSelectionModel().getSelectedItems().get(0).toString());
        }
            System.out.println("Table row clicked");
        
    }
    
    
}

