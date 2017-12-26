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

public class ProductsController {

    @FXML // fx:id="productsTable"
    private TableView<Products> productsTable;

    @FXML // fx:id="prodIDColumn"
    private TableColumn<Products, String> prodIDColumn;

    @FXML // fx:id="packageDealColumn"
    private TableColumn<Products, String> packageDealColumn;

    @FXML // fx:id="priceColumn"
    private TableColumn<Products, String> priceColumn;
    
    public void changePackageCellEvent(TableColumn.CellEditEvent edittedCell) {
        System.out.println("changePackageCellEvent");

        Products productSelected = productsTable.getSelectionModel().getSelectedItem();
        productSelected.setPackageDeal(edittedCell.getNewValue().toString());
    }
    
    public void changePriceCellEvent(TableColumn.CellEditEvent edittedCell) {
        System.out.println("changePackageCellEvent");

        Products productSelected = productsTable.getSelectionModel().getSelectedItem();
        productSelected.setPrice(edittedCell.getNewValue().toString());
    }
    
    public void cancelEditPackageCellEvent(TableColumn.CellEditEvent edittedCell) {
        System.out.println("cancelEditPackageCellEvent");
    }

    public void startEditPackageCellEvent(TableColumn.CellEditEvent edittedCell) {
        System.out.println("startEditPackageCellEvent");

    }

    @FXML //basically works like an onload() method
    protected void initialize() {

        //setting up the columns
        PropertyValueFactory<Products, String> prodIDProperty
                = new PropertyValueFactory<Products, String>("prodID");

        PropertyValueFactory<Products, String> packageDealProperty
                = new PropertyValueFactory<Products, String>("packageDeal");

        PropertyValueFactory<Products, String> priceProperty
                = new PropertyValueFactory<Products, String>("price");

        prodIDColumn.setCellValueFactory(prodIDProperty);
        packageDealColumn.setCellValueFactory(packageDealProperty);
        priceColumn.setCellValueFactory(priceProperty);

        //setting up the table data source
        DataSourceProducts data = new DataSourceProducts();
        ObservableList<Products> tableItems = data.getData();
        productsTable.setItems(tableItems);
        
        productsTable.setEditable(true);
        packageDealColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        priceColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        
    }

    @FXML // fx:id="prodIDField"
    private TextField prodIDField;

    @FXML // fx:id="packageDealField"
    private TextField packageDealField;

    @FXML // fx:id="priceField"
    private TextField priceField;

    @FXML
    protected void addProducts(ActionEvent event) {
        ObservableList<Products> data = productsTable.getItems();
        data.add(new Products(
                prodIDField.getText(),
                packageDealField.getText(),
                priceField.getText()
        ));

        prodIDField.setText("");
        packageDealField.setText("");
        priceField.setText("");

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("Products.csv", false))) {
            String prodID = "";
            String packageDeal = "";
            String price = "";

            String header = "=============,=============,===============\n";
            header += "Product ID, Package Deals, Price\n";
            header += "============,=============,===============\n";
            bw.write(header);

            for (Products products : data) {
                prodID = products.getProdID();
                packageDeal = products.getPackageDeal();
                price = products.getPrice();
                String newLine = prodID + "," + packageDeal + "," + price;
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
    protected void saveProducts(ObservableList<Products> data) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("Products.csv", false))) {
            String prodID = "";
            String packageDeal = "";
            String price = "";


            String header = "=============,=============,=============\n";
            header += "Product ID, Package Deals, Prices\n";
            header += "=============,=============,=============\n";
            bw.write(header);

            for (Products products : data) {
                prodID = products.getProdID();
                packageDeal = products.getPackageDeal();
                price = products.getPrice();
                String newLine = prodID + "," + packageDeal + "," + price;
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
    protected void deleteProducts(ActionEvent event) {
        System.out.println("delete Products");
        //Check whether item is selected and set value of selected item to Label
        if (productsTable.getSelectionModel().getSelectedItem() != null) {

            int selectedIndex = productsTable.getSelectionModel().getSelectedIndex();
            System.out.println("Selected Index: " + selectedIndex);

            if (selectedIndex >= 0) {
                productsTable.getItems().remove(selectedIndex);
                ObservableList<Products> data = productsTable.getItems();
                saveProducts(data);
            } 

        }

    }
    
    @FXML
    protected void searchProducts(ActionEvent event) {
        ObservableList<Products> data = productsTable.getItems();

        ObservableList<Products> searchResult = FXCollections.observableArrayList();
        String packageDeal = packageDealField.getText();
        String price = priceField.getText();
        for (Products c : data) {

            if (c.getPackageDeal().trim() != "" && c.getPrice().trim() != "") {
                if (c.getPackageDeal().trim().equalsIgnoreCase(packageDeal) && c.getPrice().trim().equalsIgnoreCase(price)) {
                    searchResult.add(c);
                }
            }
            if (c.getPackageDeal().trim() != "") {
                if (c.getPackageDeal().trim().equalsIgnoreCase(packageDeal)) {
                    searchResult.add(c);
                }
            }
            if (c.getPrice().trim() != "") {
                if (c.getPrice().trim().equalsIgnoreCase(price)) {
                    searchResult.add(c);
                }
            }
        }
        productsTable.setItems(searchResult);
    }
    
        @FXML
    protected void updateProducts(ActionEvent event) {

        System.out.println("update Product");
        int selectedIndex = productsTable.getSelectionModel().getSelectedIndex();
        System.out.println("Selected Index: " + selectedIndex);

        if (selectedIndex >= 0) {
            ObservableList<Products> data = productsTable.getItems();
            saveProducts(data);
        } else {
            
        }
    }
    
    public void handle(MouseEvent t) {
        ObservableList table = FXCollections.observableArrayList();
        
        for (int i = 0; i <= productsTable.getColumns().size(); i++) {
            table.add(productsTable.getSelectionModel().getSelectedItems().get(0).toString());
        }
            System.out.println("Table row clicked");
        
    }

}
