package anderson.c482.controller;

import anderson.c482.Model.Inventory;
import anderson.c482.Model.Part;
import anderson.c482.Model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.Optional;
import java.util.ResourceBundle;

import static anderson.c482.Model.Inventory.lookupPartId;
import static anderson.c482.Model.Inventory.lookupPartName;

/**
 *
 * @author Jacob Anderson
 * */

/** This is the class for AddProductController.
 * This is controller for AddProduct view
 * */
public class AddProductController implements Initializable {

    /** This method returns the tAssociatedParts.
     * This method is used for getting all the tAssociatedParts
     * @return returns tAssociatedParts
     * */
    public ObservableList<Part> getAssociatedParts() {
        return tAssociatedParts;
    }

    /**This method takes set the tAssociatedParts list.
     * This method takes the associatedParts from a product and adds it to the temporary list for
     * */
    public void setAssociatedParts(ObservableList<Part> associatedParts) {
        this.tAssociatedParts = associatedParts;
    }

    /** This ObservableList is for associatedParts for each Product.
     * This ObservableList holds a temporary list which is used to interact with the AssociatedParts observable list from the Product class
     * */
    private ObservableList<Part> tAssociatedParts = FXCollections.observableArrayList();

    //text fields for adding Product
    /** This TextField is for adding a ProductId to a new product.
     * This TextField is disabled on runtime and the Id is auto generated
     * */
    @FXML
    public TextField addProductIdTxt;

    /** This TextField is for adding a ProductName to a new product.
     * This TextField is where a user enters the ProductName for a new Product
     * */
    @FXML
    public TextField addProductNameTxt;

    /** This TextField is for adding a ProductInv to a new product.
     * This TextField is where a user enters the ProductInv for a new Product
     * */
    @FXML
    public TextField addProductInvTxt;

    /** This TextField is for adding a ProductPrice to a new product.
     * This TextField is where a user enters the ProductPrice for a new Product
     * */
    @FXML
    public TextField addProductPriceTxt;

    /** This TextField is for adding a ProductMax to a new product.
     * This TextField is where a user enters the ProductMax for a new Product
     * */
    @FXML
    public TextField addProductMaxTxt;

    /** This TextField is for adding a ProductMin to a new product.
     * This TextField is where a user enters the ProductMin for a new Product
     * */
    @FXML
    public TextField addProductMinTxt;

    //search bar
    /** This TextField is for searching the PartTable.
     * This TextField lets user enter a Name or ID to search for a Part
     * */
    @FXML
    public TextField addProductSearchTxt;

    //table view and columns for adding Parts to a Product
    /**This TableColumn displays the PartID in the PartsTable.
     * This TableColumn displays the PartID in the PartsTable. The table contains the entire Parts list
     * */
    @FXML
    public TableColumn<Part, Integer> addProductPartIdCol;

    /**This TableColumn displays the PartName in the PartsTable.
     * This TableColumn displays the PartName in the PartsTable. The table contains the entire Parts list
     * */
    @FXML
    public TableColumn<Part,String> addProductPartNameCol;

    /**This TableColumn displays the PartInv in the PartsTable.
     * This TableColumn displays the PartInv in the PartsTable. The table contains the entire Parts list
     * */
    @FXML
    public TableColumn<Part, Integer> addProductPartInvCol;

    /**This TableColumn displays the PartPrice in the PartsTable.
     * This TableColumn displays the PartPrice in the PartsTable. The table contains the entire Parts list
     * */
    @FXML
    public TableColumn<Part, Double> addProductPartPriceCol;

    /** This is the TableView for displaying all the Parts.
     * This table displays all the available Parts in the program
     * */
    @FXML
    public TableView<Part> addProductPartTable;

    //table view and columns for associated Parts for an instance of Product
    /**This TableColumn displays the PartID in the AssociatedPartsTable.
     * This TableColumn displays the PartID in the AssociatedPartsTable. The table contains the associatedParts for the selected Product.
     * */
    @FXML
    public TableColumn<Part, Integer> addProductAssociatedID;

    /**This TableColumn displays the PartName in the AssociatedPartsTable.
     * This TableColumn displays the PartName in the AssociatedPartsTable. The table contains the associatedParts for the selected Product.
     * */
    @FXML
    public TableColumn<Part, String> addProductAssociatedName;

    /**This TableColumn displays the PartInv in the AssociatedPartsTable.
     * This TableColumn displays the PartInv in the AssociatedPartsTable. The table contains the associatedParts for the selected Product.
     * */
    @FXML
    public TableColumn<Part, Integer> addProductAssociatedInv;

    /**This TableColumn displays the PartPrice in the AssociatedPartsTable.
     * This TableColumn displays the PartPrice in the AssociatedPartsTable. The table contains the associatedParts for the selected Product.
     * */
    @FXML
    public TableColumn<Part, Double> addProductAssociatedPrice;

    /** This is the TableView for displaying all the associatedParts.
     * This table displays all the associatedParts for the selected Product
     * */
    @FXML
    public TableView<Part> addProductAssociatedTable;

    /**This method initializes the AddProductView.
     * This method populates the partTable and associatedPartsTable
     * @param url url for initializing
     * @param resourceBundle resourceBundle for initializing
     * */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("I am initialized");
        //Add Product table
        addProductPartTable.setItems(Inventory.getAllParts());
        addProductPartIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        addProductPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        addProductPartInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        addProductPartPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        //associated part table
       // addProductAssociatedTable.setItems(Product.getAllAssociatedParts());
        addProductAssociatedTable.setItems(tAssociatedParts);
        addProductAssociatedID.setCellValueFactory(new PropertyValueFactory<>("id"));
        addProductAssociatedName.setCellValueFactory(new PropertyValueFactory<>("name"));
        addProductAssociatedInv.setCellValueFactory(new PropertyValueFactory<>("stock"));
        addProductAssociatedPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

    }

    //Button for Add Part to a Product
    /** This method adds a Part to the selected Product.
     * This method takes a Part from the Parts table and adds it to the AssociatedPartsList of a Product
     * @param actionEvent actionEvent for when the Add button is clicked
     * */
    @FXML
    public void onActionAddPartToProduct(ActionEvent actionEvent) {
        Part selectedPart = (Part)addProductPartTable.getSelectionModel().getSelectedItem();
        if(selectedPart == null){
            return;
        }
        tAssociatedParts.add(selectedPart);
    }

    //Button for Removing Part from Product
    /** This method removes an associatedPart from the selcted Product.
     * This method removes a Part from a products AssociatedPartsList
     * @param actionEvent actionEvent for when the Remove button is clicked
     * */
    @FXML
    public void onActionRemovePartFromProduct(ActionEvent actionEvent) {
        Part selectedPart = (Part)addProductAssociatedTable.getSelectionModel().getSelectedItem();
        if(selectedPart == null){
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to remove the selected Part?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            tAssociatedParts.remove(selectedPart);
        }
    }

    //Buttons to save Changes and DisplayMain
    /** This method is for saving and returning to MainView.
     * This method saves the information input into a new Product and add it to the ProductsObservableList
     * @param actionEvent actionEvent for when the Save button is clicked
     * */
    @FXML
    public void onActionSaveProductDisplayMain(ActionEvent actionEvent) throws IOException {
        try{
            int id = Inventory.getNewProductId();
            String name = addProductNameTxt.getText();
            int inv = Integer.parseInt(addProductInvTxt.getText());
            double price = Double.parseDouble(addProductPriceTxt.getText());
            int max = Integer.parseInt(addProductMaxTxt.getText());
            int min = Integer.parseInt(addProductMinTxt.getText());

            if (min >= max) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Minimum cannot be greater than Max.");
                alert.showAndWait();
            }
            else if (inv > max || inv < min){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Inventory cannot be outside the min-max range");
                alert.showAndWait();
            }
            else{
                Product p1 = new Product(id, name, price, inv, min, max, tAssociatedParts);
                Inventory.addProduct(p1);
                System.out.println(p1.getAllAssociatedParts().size());

                Parent root = FXMLLoader.load(getClass().getResource("/anderson/c482/MainView.fxml"));
                Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
                Scene scene = new Scene(root, 1050, 500);
                stage.setTitle("MainView");
                stage.setScene(scene);
                stage.show();
            }

        }
        catch(NumberFormatException e){
            System.out.println("Please enter valid values in text fields.");
            System.out.println("Exception: " + e);
            System.out.println("Exception: " + e.getMessage());
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please enter valid values in text fields.");
            alert.showAndWait();
        }

    }

    //Button to cancel and display main
    /** This method is for returning to MainView.
     * This method return user to MainView and cancels any inputs into the text fields or changes to associatedPartsList
     * @param actionEvent actionEvent for when the Cancel button is clicked
     * */
    @FXML
    public void onActionCancel(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/anderson/c482/MainView.fxml"));
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1050, 500);
        stage.setTitle("MainView");
        stage.setScene(scene);
        stage.show();
    }

    //Method for using search bar
    /** This method search the Part table on the AddProductView.
     * This method takes an input from a text field and searches the Parts table for matching input and displays on Part table
     * @param actionEvent actionEvent for when search text field is used
     * */
    @FXML
    public void onActionSearchPart(ActionEvent actionEvent) {
        try {
            //Get input in search bar
            String input = addProductSearchTxt.getText();
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
                    alert.setContentText("Please enter a part name or ID");
                    alert.showAndWait();
                }
            }
            //Populate Table with results of search
            addProductPartTable.setItems(parts);
        }
        catch(NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please enter a part name or ID");
            alert.showAndWait();
        }
    }
}