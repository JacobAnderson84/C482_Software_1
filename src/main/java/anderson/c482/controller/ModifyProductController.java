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
import java.util.Optional;
import java.util.ResourceBundle;

import static anderson.c482.Model.Inventory.lookupPartId;
import static anderson.c482.Model.Inventory.lookupPartName;

/**
 *
 * @author Jacob Anderson
 * */

/** This is the class for ModifyProductController.
 * This is controller for ModifyProduct view
 * */

public class ModifyProductController implements Initializable {

    //text fields for Modify Product
    /** This TextField is for modifying a ProductId.
     * This TextField is disabled on runtime and the ID is auto generated
     * */
    @FXML
    public TextField modProductIdTxt;

    /** This TextField is for modifying a ProductName.
     * This TextField is where a user enters the ProductName
     * */
    @FXML
    public TextField modProductNameTxt;

    /** This TextField is for modifying a ProductInv.
     * This TextField is where a user enters the ProductInv
     * */
    @FXML
    public TextField modProductInvTxt;

    /** This TextField is for modifying a ProductPrice.
     * This TextField is where a user enters the ProductPrice
     * */
    @FXML
    public TextField modProductPriceTxt;

    /** This TextField is for modifying a ProductMax.
     * This TextField is where a user enters the ProductMax
     * */
    @FXML
    public TextField modProductMaxTxt;

    /** This TextField is for modifying a ProductMin.
     * This TextField is where a user enters the ProductMin
     * */
    @FXML
    public TextField modProductMinTxt;

    //search bar for Modify Product
    /** This TextField is for searching the PartTable.
     * This TextField lets user enter a Name or ID to search for a Part
     * */
    @FXML
    public TextField modProductSearchTxt;

    //table view for adding Parts for each Product
    /**This TableColumn displays the PartID in the PartsTable.
     * This TableColumn displays the PartID in the PartsTable. The table contains the entire Parts list
     * */
    @FXML
    public TableColumn<Part, Integer> modProductPartIdCol;

    /**This TableColumn displays the PartName in the PartsTable.
     * This TableColumn displays the PartName in the PartsTable. The table contains the entire Parts list
     * */
    @FXML
    public TableColumn<Part, String> modProductPartNameCol;

    /**This TableColumn displays the PartInv in the PartsTable.
     * This TableColumn displays the PartInv in the PartsTable. The table contains the entire Parts list
     * */
    @FXML
    public TableColumn<Part, Integer> modProductPartInvCol;

    /**This TableColumn displays the PartPrice in the PartsTable.
     * This TableColumn displays the PartPrice in the PartsTable. The table contains the entire Parts list
     * */
    @FXML
    public TableColumn<Part, Double> modProductPartPriceCol;

    /** This is the TableView for displaying all the Parts.
     * This table displays all the available Parts in the program
     * */
    @FXML
    public TableView<Part> modProductPartTable;

    //table view for removing Associated Parts from each Product
    /**This TableColumn displays the PartID in the AssociatedPartsTable.
     * This TableColumn displays the PartID in the AssociatedPartsTable. The table contains the associatedParts for the selected Product.
     * */
    @FXML
    public TableColumn<Part, Integer> modProductAssociatedId;

    /**This TableColumn displays the PartName in the AssociatedPartsTable.
     * This TableColumn displays the PartName in the AssociatedPartsTable. The table contains the associatedParts for the selected Product.
     * */
    @FXML
    public TableColumn<Part, String> modProductAssociatedName;

    /**This TableColumn displays the PartInv in the AssociatedPartsTable.
     * This TableColumn displays the PartInv in the AssociatedPartsTable. The table contains the associatedParts for the selected Product.
     * */
    @FXML
    public TableColumn<Part, Integer> modProductAssociatedInv;

    /**This TableColumn displays the PartPrice in the AssociatedPartsTable.
     * This TableColumn displays the PartPrice in the AssociatedPartsTable. The table contains the associatedParts for the selected Product.
     * */
    @FXML
    public TableColumn<Part, Double> modProductAssociatedPrice;

    /** This is the TableView for displaying all the associatedParts.
     * This table displays all the associatedParts for the selected Product
     * */
    @FXML
    public TableView<Part> modProductAssociatedTable;

    //Used for receiving data from another controller
    /** This Field is for receiving a Product from MainView.
     * This Field is used when the MainController is sending a Product to be modified on the ModifyProductView
     * */
    public static Product prodToModify;

    /**This field is for receiving a Products index.
     * This Field is used when the MainController is sending a Product to be modified on the ModifyProductView. The index is used to keep track of location in the ProductObservableList
     * */
    public static int prodToModifyIndex;

    //used as a temporary list holding data when sent from another controller
    /** This ObservableList is for associatedParts for each Product.
     * This ObservableList holds a temporary list which is used to interact with the AssociatedParts observable list from the Product class
     * */
    private ObservableList<Part> tAssociatedParts = FXCollections.observableArrayList();


    //initialize
    /**This method initializes the ModProductView.
     * This method populates the partTable and associatedPartsTable. The prodToModify Field is used to take data received from MainCController and populate the appropriate text and tables.
     * @param url url for initializing
     * @param resourceBundle resourceBundle for initializing
     * */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("I am initialized");
        //Table for adding Parts to a Product
        modProductPartTable.setItems(Inventory.getAllParts());
        modProductPartIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        modProductPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        modProductPartInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        modProductPartPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        //enhanced loop to pull AssociatedParts from
        for(Part temp: prodToModify.getAllAssociatedParts()) {
            tAssociatedParts.add(temp);
        }
        System.out.println("***" + tAssociatedParts.size());
        //table for AssociatedParts for selected Product
        modProductAssociatedTable.setItems(tAssociatedParts);
        modProductAssociatedId.setCellValueFactory(new PropertyValueFactory<>("id"));
        modProductAssociatedName.setCellValueFactory(new PropertyValueFactory<>("name"));
        modProductAssociatedInv.setCellValueFactory(new PropertyValueFactory<>("stock"));
        modProductAssociatedPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        //Adding in Product info for all txt fields
        modProductIdTxt.setText(String.valueOf(prodToModify.getId()));
        modProductNameTxt.setText(prodToModify.getName());
        modProductInvTxt.setText(String.valueOf(prodToModify.getStock()));
        modProductPriceTxt.setText(String.valueOf(prodToModify.getPrice()));
        modProductMaxTxt.setText(String.valueOf(prodToModify.getMax()));
        modProductMinTxt.setText(String.valueOf(prodToModify.getMin()));

    }

    //Button to Add Part to Product
    /** This method adds a Part to the selected Product.
     * This method takes a Part from the Parts table and adds it to the associatedPartsList of a Product
     * @param actionEvent actionEvent for when the add button is clicked
     * */
    @FXML
    public void onActionAddPartToProduct(ActionEvent actionEvent) {
        Part selectedPart = (Part)modProductPartTable.getSelectionModel().getSelectedItem();
        if(selectedPart == null){
            return;
        }
        tAssociatedParts.add(selectedPart);

    }

    //Button for Remove Part from Product
    /** This method removes an associatedPart from the selcted Product.
     * This method removes a Part from a products AssociatedPartsList
     * @param actionEvent actionEvent for when the Remove button is clicked
     * */
    @FXML
    public void onActionRemovePartFromProduct(ActionEvent actionEvent) {
        Part selectedPart = (Part)modProductAssociatedTable.getSelectionModel().getSelectedItem();
        if(selectedPart == null){
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to remove the selected Part?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            tAssociatedParts.remove(selectedPart);
        }
    }

    //Buttons for Save and display main
    /** This method is for saving and returning to MainView.
     * This method saves the information changed into the existing Product and updates the ProductsObservableList
     * @param actionEvent actionEvent for when the Save button is clicked
     * */
    @FXML
    public void onActionSaveProductDisplayMain(ActionEvent actionEvent) throws IOException {
        try{
            int id = Integer.parseInt(modProductIdTxt.getText());
            String name = modProductNameTxt.getText();
            int inv = Integer.parseInt(modProductInvTxt.getText());
            double price = Double.parseDouble(modProductPriceTxt.getText());
            int max = Integer.parseInt(modProductMaxTxt.getText());
            int min = Integer.parseInt(modProductMinTxt.getText());

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
            else {
                Product p1 = new Product(id, name, price, inv, min, max, tAssociatedParts);
                Inventory.updateProduct(prodToModifyIndex, p1);

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

    //button for cancel and display main
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

    /** This method search the Part table on the ModifyProductview.
     * This method takes an input from a text field and searches the associatedParts table for matching input and displays on associatedParts table
     * @param actionEvent actionEvent for when search text field is used
     * */
    public void onActionSearchPart(ActionEvent actionEvent) {
        try {
            //Get input in search bar
            String input = modProductSearchTxt.getText();
            //Create list holding search request for name
            ObservableList<Part> parts = lookupPartName(input);
            //if no Name was found check to see if search is for ID
            if (parts.size() == 0) {
                int intInput = Integer.parseInt(input);
                Part searchedPart = lookupPartId(intInput);
                if (searchedPart != null) {
                    parts.add(searchedPart);
                }
                else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setContentText("Please enter a part name or ID");
                    alert.showAndWait();
                }
            }
            //Populate Table with results of search
            modProductPartTable.setItems(parts);
        }
        catch(NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please enter a part name or ID");
            alert.showAndWait();
        }
    }

    //method for receiving data from main controller
    /**This method is for receiving data from MainView.
     * This method takes the selectedProduct from the MainView and assigns its index and information to provided Fields.
     * @param index index of the selectedProduct location in the ProductsObservableList
     * @param p holds the information from the selectedProduct from MainView
     * */
    public static void sendData(Product p, int index){
        prodToModify = p;
        prodToModifyIndex = index;

    }
}