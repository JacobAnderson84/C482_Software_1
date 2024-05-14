package anderson.c482.controller;

import anderson.c482.Main;
import anderson.c482.Model.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import static anderson.c482.Model.Inventory.*;
/**
 *
 * @author Jacob Anderson
 * */

/** This is the class for the MainController.
 * This is controller for the MainView
 * */
public class MainController implements Initializable {

    //////Part TABLE
    /**This TextField is for searching PartTable.
     * This is text field where user and can search for a Part from the Parts list and display it in the Parts table
     */
    @FXML
    public TextField mainSearchPartTxt;

    //Main view Part Columns
    /** This TableColumn is for displaying PartId in the Part table.
     * This TableColumn displays PartId in the Part table
     * */
    @FXML
    public TableColumn<Part, Integer> mainPartIdCol;

    /** This TableColumn is for displaying PartName in the Part table.
     * This TableColumn displays PartName in the Part table
     * */
    @FXML
    public TableColumn<Part, String> mainPartNameCol;

    /** This TableColumn is for displaying PartInv in the Part table.
     * This TableColumn displays PartInv in the Part table
     * */
    @FXML
    public TableColumn<Part, Integer> mainPartInvCol;

    /** This TableColumn is for displaying PartPrice in the Part table.
     * This TableColumn displays PartPrice in the Part table
     * */
    @FXML
    public TableColumn<Part, Double> mainPartPriceCol;

    //TableView for Parts table
    /** This is the TableView for the PartTable.
     * This TableView displays the PartTable
     * */
    @FXML
    public TableView<Part> mainViewPartTable;


    /////PRODUCT TABLE
    //Main View Product Search

    /**This TextField is for searching ProductTable.
     * This is text field where user and can search for a Product from the Products list and display it in the Product table
     */
    @FXML
    public TextField mainSearchProductTxt;

    //Main View Product Columns
    /** This TableColumn is for displaying ProductId in the Part table.
     * This TableColumn displays ProductId in the Part table
     * */
    @FXML
    public TableColumn<Product, Integer> mainProductIdCol;

    /** This TableColumn is for displaying ProductName in the Part table.
     * This TableColumn displays ProductName in the Part table
     * */
    @FXML
    public TableColumn<Product, String> mainProductNameCol;

    /** This TableColumn is for displaying ProductInv in the Part table.
     * This TableColumn displays ProductInv in the Part table
     * */
    @FXML
    public TableColumn<Product, Integer> mainProductInvCol;

    /** This TableColumn is for displaying ProductPrice in the Part table.
     * This TableColumn displays ProductPrice in the Part table
     * */
    @FXML
    public TableColumn<Product, Double> mainProductPriceCol;

    //TableView for Product table
    /** This is the TableView for the ProductTable.
     * This TableView displays the ProductTable
     * */
    @FXML
    public TableView<Product> mainViewProductTable;

    /** This method initializes the MainView.
     * This method is used to initialize the MainView each times it's called and populate the different tables
     * @param url url for initializing
     * @param resourceBundle resource bundle for initializing
     * */
    //Populating the tables for each time MainView is called on
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("I am initialized");
        //table for parts
        mainViewPartTable.setItems(Inventory.getAllParts());
        mainPartIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        mainPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        mainPartInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        mainPartPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        //table for products
        mainViewProductTable.setItems(Inventory.getAllProducts());
        mainProductIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        mainProductNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        mainProductInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        mainProductPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }


    /** This method displays AddPart view.
     * This method is used whenever the AddPart view is called upon
     * @param actionEvent actionEvent when the Add button is clicked
     * */
    @FXML
    public void onActionDisplayAddPart(ActionEvent actionEvent) throws IOException {
        System.out.println("Add Part Button Clicked");
        Parent root = FXMLLoader.load(getClass().getResource("/anderson/c482/AddPart.fxml"));
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 600, 400);
        stage.setTitle("AddPartView");
        stage.setScene(scene);
        stage.show();
    }


    /** This method displays ModifyPart view.
     * This method is used whenever the ModifyPart is called upon. The selectedPart is used to retrieve a part and send it over to the ModifyPartController along with it's index.
     * @param actionEvent actionEvent when the Modify button is clicked
     * */
    @FXML
    public void onActionDisplayModifyPart(ActionEvent actionEvent) throws IOException {
        try{
            Part selectedPart = (Part)mainViewPartTable.getSelectionModel().getSelectedItem();
            if (selectedPart == null){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Please select a Part to modify.");
                alert.showAndWait();
            }
            int index = mainViewPartTable.getSelectionModel().getSelectedIndex();
            ModifyPartController.sendData(selectedPart, index);
        }
        //can get rid of this
        catch(NullPointerException e){
            System.out.println("Please select Part to modify");
            System.out.println("Exception: " + e);
            System.out.println("Exception: " + e.getMessage());
        }


        System.out.println("Modify Part Button Clicked");
        Parent root = FXMLLoader.load(getClass().getResource("/anderson/c482/ModifyPart.fxml"));
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 600, 400);
        stage.setTitle("ModifyPartView");
        stage.setScene(scene);
        stage.show();
    }


    /** This method displays AddProduct view.
     * This method is used whenever the AddProduct is called upon
     * @param actionEvent actionEvent when the Modify button is clicked
     * */
    @FXML
    public void onActionDisplayAddProduct(ActionEvent actionEvent) throws IOException {
        System.out.println("Add Product Button Clicked");
        Parent root = FXMLLoader.load(getClass().getResource("/anderson/c482/AddProduct.fxml"));
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 800, 550);
        stage.setTitle("AddProductView");
        stage.setScene(scene);
        stage.show();
    }

    /** This method displays ModifyProduct view.
     * This method is used whenever the ModifyProduct is called upon. The selectedProduct is used to retrieve a product and send it over to the ModifyProductController along with it's index
     * @param actionEvent actionEvent when the Modify button is clicked
     * */
    @FXML
    public void onActionDisplayModifyProduct(ActionEvent actionEvent) throws IOException {
        Product selectedProduct = (Product)mainViewProductTable.getSelectionModel().getSelectedItem();
        if(selectedProduct == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please select Product to modify.");
            alert.showAndWait();
        }
        int index = mainViewProductTable.getSelectionModel().getSelectedIndex();
        ModifyProductController.sendData(selectedProduct, index);

        System.out.println("Modify Product Button Clicked");
        Parent root = FXMLLoader.load(getClass().getResource("/anderson/c482/ModifyProduct.fxml"));
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 800, 550);
        stage.setTitle("ModifyProductView");
        stage.setScene(scene);
        stage.show();
    }

    /** This method deletes a part from the Parts observable list.
     * This Method takes the selectedPart and deletes it from the Parts observable list
     * @param actionEvent actionEvent when the Delete button is clicked
     * */
    @FXML
    public void onActionDeletePart(ActionEvent actionEvent) {
        System.out.println("Delete Part Button Clicked");
        Part selectedPart = (Part)mainViewPartTable.getSelectionModel().getSelectedItem();
        //Alert box to confirm the delete action
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete the selected Part?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            Inventory.deletePart(selectedPart);
        }
        else {
            //cancel button was pressed
        }

    }


    /** This method deletes a Product from the Products observable list.
     * This Method takes the selectedProduct and deletes it from the Products observable list
     * @param actionEvent actionEvent when the Delete button is clicked
     * */
    @FXML
    public void onActionDeleteProduct(ActionEvent actionEvent) {
        System.out.println("Delete Product Button Clicked");
        Product selectedProduct = (Product) mainViewProductTable.getSelectionModel().getSelectedItem();
        if (selectedProduct.getAllAssociatedParts().size() != 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Cannot be deleted because Product has Parts associated with it");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete the selected Product?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                Inventory.deleteProduct(selectedProduct);
            }
        }
    }

    /** This method search the Part table on the Mainview.
     * This method takes an input from a text field and searches the Parts table for matching input and displays on Part table
     * @param actionEvent actionEvent for when search text field is used
     * */
    @FXML
    public void onInputSearchPart(ActionEvent actionEvent) {
        try {
            //Get input in search bar
            String input = mainSearchPartTxt.getText();
            //Create list holding search request for name
            ObservableList<Part> parts = lookupPartName(input);
            //if no Name was found check to see if search is for ID
            if (parts.size() == 0) {
                int intInput = Integer.parseInt(input);
                Part searchedPart = lookupPartId(intInput);
                if (searchedPart != null) {
                    parts.add(searchedPart);
                }
                else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setContentText("Please enter part name or ID");
                    alert.showAndWait();
                }
            }
            //Populate Table with results of search
            mainViewPartTable.setItems(parts);
        }
        catch(NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please enter a part name or ID");
            alert.showAndWait();
        }
    }

    /** This method search the Product table on the Mainview.
     * This method takes an input from a text field and searches the Product list for matching input and displays in Product table
     * @param actionEvent actionEvent for when search text field is used
     * */
    @FXML
    public void onActionSearchProduct(ActionEvent actionEvent) {
        try {
            //Get input in search bar
            String input = mainSearchProductTxt.getText();
            //Create list holding the search request for name
            ObservableList<Product> products = lookupProductName(input);
            //if no Name was found check to see if search is for ID
            if (products.size() == 0) {
                int intInput = Integer.parseInt(input);
                Product searchedProduct = lookupProductId(intInput);
                if (searchedProduct != null) {
                    products.add(searchedProduct);
                }
                else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setContentText("Please enter a product name or ID");
                    alert.showAndWait();
                }
            }
            //Populate Table with results of search
            mainViewProductTable.setItems(products);
        }
        catch (NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please enter a product name or ID");
            alert.showAndWait();
        }
    }

    /** This method exits the Program.
     * @param actionEvent actionEvent for when button is pressed.
     * */
    public void onActionExit(ActionEvent actionEvent) {
        System.exit(0);
    }
}