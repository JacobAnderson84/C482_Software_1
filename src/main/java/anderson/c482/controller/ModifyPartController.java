package anderson.c482.controller;

import anderson.c482.Model.InHouse;
import anderson.c482.Model.Inventory;
import anderson.c482.Model.Outsourced;
import anderson.c482.Model.Part;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 *
 * @author Jacob Anderson
 * */

/** This is the class for ModifyPartController.
 * This is controller for ModifyPart view
 * */
public class ModifyPartController implements Initializable {

    //text fields for Modifying Part
    /** This TextField PartId to a Part.
     * This TextField is disabled on runtime and is auto generated
     * */
    @FXML
    public TextField modPartIdTxt;

    /** This TextField is for modifying a PartName.
     * This TextField is where a user enters the PartName
     * */
    @FXML
    public TextField modPartNameTxt;

    /** This TextField is for modifying a PartInv.
     * This TextField is where a user enters the PartInv
     * */
    @FXML
    public TextField modPartInvTxt;

    /** This TextField is for modifying a PartPrice.
     * This TextField is where a user enters the PartPrice
     * */
    @FXML
    public TextField modPartPriceTxt;

    /** This TextField is for modifying a PartMax.
     * This TextField is where a user enters the PartMax
     * */
    @FXML
    public TextField modPartMaxTxt;

    /** This TextField is for modifying a PartMin.
     * This TextField is where a user enters the PartMin
     * */
    @FXML
    public TextField modPartMinTxt;

    /** This TextField is for modifying a Parts special field (companyName or MachineId).
     * This TextField is where a user enters the Parts special field (companyName or MachineId)
     * */
    @FXML
    public TextField modPartSpecialTxt;

    //label for special field. will switch between MachineID(InHouse) or CompanyName(Outsource)
    /**This is the label for addPartSpecialText.
     * This field changes depending on what radio button is pressed. Changes between companyName(when Outsourced is clicked) and machineId (when InHouse is clicked)
     * */
    @FXML
    public Label modPartSpecialLabel;


    //Radio Buttons for Modifying Part. switching between InHouse and Outsource
    /** This RadioButton is for InHouse selection.
     * This RadioButton is used to select InHouse which gives the Part a MachineID
     * */
    @FXML
    public RadioButton modPartInHouseRB;

    /** This RadioButton is for Outsource selection.
     * This RadioButton is used to select Outsource which gives the Part a companyName
     * */
    @FXML
    public RadioButton modPartOutsourceRB;

    //Used for receiving Part from main screen to be modified
    /** This Field is for receiving a Part from MainView.
     * This Field is used when the MainController is sending a Part to be modified on the ModifyPartView
     * */
    public static Part partToModify;

    /**This field is for receiving a Parts index.
     * This Field is used when the MainController is sending a Part to be modified on the ModifyPartView. The index is used to keep track of location in the PartsObservableList
     * */
    public static int partToModifyIndex;

    /** This method sets the SpecialLabel to "Machine ID".
     * When clicked the label modPartSpecialLabel will change to "Machine ID"
     * @param actionEvent actionEvent for when the RadioButton is clicked
     * */
    //method for radio button. switches special field to InHouse
    @FXML
    public void onActionInHouse(ActionEvent actionEvent) {
        modPartSpecialLabel.setText("Machine ID");
    }

    /** This method sets the SpecialLabel to "Company Name".
     * When clicked the label modPartSpecialLabel will change to "CompanyName"
     * @param actionEvent actionEvent for when the RadioButton is clicked
     * */
    //method for radio button. switches special field to Outsource
    @FXML
    public void onActionOutsource(ActionEvent actionEvent) {
        modPartSpecialLabel.setText("Company Name");
    }


    //initialize
    /**This method initializes the ModPartView.
     * The partToModify Field is used to take data received from MainController and populate the appropriate text and tables.
     * @param url url for initializing
     * @param resourceBundle resourceBundle for initializing
     * */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("I am initialized");

        //adding the received Parts values to the fields
        modPartIdTxt.setText(String.valueOf(partToModify.getId()));
        modPartNameTxt.setText(partToModify.getName());
        modPartInvTxt.setText(String.valueOf(partToModify.getStock()));
        modPartPriceTxt.setText(String.valueOf(partToModify.getPrice()));
        modPartMaxTxt.setText(String.valueOf(partToModify.getMax()));
        modPartMinTxt.setText(String.valueOf(partToModify.getMin()));

        if(partToModify instanceof InHouse) {
            modPartSpecialTxt.setText(String.valueOf(((InHouse)partToModify).getMachineId()));
            modPartSpecialLabel.setText("Machine ID");
        }
        else{
            modPartOutsourceRB.setSelected(true);
            modPartSpecialTxt.setText(((Outsourced)partToModify).getCompanyName());
            modPartSpecialLabel.setText("Company Name");
        }

    }

    //Buttons for Save the part that has been modified and displays MainView
    /** This method is for saving and returning to MainView.
     * This method saves the information input into a new Part and add it to the PartsObservableList
     * @param actionEvent actionEvent for when the Save button is clicked
     * */
    @FXML
    public void onActionSavePartDisplayMain(ActionEvent actionEvent) throws IOException {
        try{
            int id = Integer.parseInt(modPartIdTxt.getText());
            String name = modPartNameTxt.getText();
            int inv = Integer.parseInt(modPartInvTxt.getText());
            double price = Double.parseDouble(modPartPriceTxt.getText());
            int max = Integer.parseInt(modPartMaxTxt.getText());
            int min = Integer.parseInt(modPartMinTxt.getText());

            if (min >= max){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Minimum cannot be greater than Max.");
                alert.showAndWait();
            }
            else if (inv > max || inv < min) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Inventory cannot be outside the min-max range");
                alert.showAndWait();
            }
            else {
                if(modPartInHouseRB.isSelected()) {
                    int machineId = Integer.parseInt(modPartSpecialTxt.getText());
                    Part p1 = new InHouse(id, name, price, inv, min, max, machineId);
                    Inventory.updatePart(partToModifyIndex, p1);
                }
                else {

                    String companyName = modPartSpecialTxt.getText();
                    Part p1 = new Outsourced(id, name, price, inv, min, max, companyName);
                    Inventory.updatePart(partToModifyIndex, p1);
                }

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

    //Button for cancel and display main
    /** This method is for returning to MainView.
     * This method return user to MainView and cancels any inputs into the text fields
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

    //Method for receiving data from Main controller
    /**This method is for receiving data from MainView.
     * This method takes the selectedPart from the MainView and assigns its index and information to provided Fields.
     * @param index index of the selectedPart location in the PartsObservableList
     * @param p holds the information from the selectedPart from MainView
     * */
    public static void sendData(Part p, int index){
        partToModify = p;
        partToModifyIndex = index;
    }

}