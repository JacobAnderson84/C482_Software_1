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

/** This is the class for the AddPartController.
 * This is controller for AddPart view
 * */
public class AddPartController implements Initializable {

    //text fields for Adding Part
    /** This TextField is for adding PartId to a newPart.
     * This TextField is disabled on runtime and is auto generated
     * */
    @FXML
    public TextField addPartIdTxt;

    /** This TextField is for adding a PartName to a new part.
     * This TextField is where a user enters the PartName for a new Part
     * */
    @FXML
    public TextField addPartNameTxt;

    /** This TextField is for adding a PartInv to a new part.
     * This TextField is where a user enters the PartInv for a new Part
     * */
    @FXML
    public TextField addPartInvTxt;

    /** This TextField is for adding a PartPrice to a new part.
     * This TextField is where a user enters the PartPrice for a new Part
     * */
    @FXML
    public TextField addPartPriceTxt;

    /** This TextField is for adding a PartMax to a new part.
     * This TextField is where a user enters the PartMax for a new Part
     * */
    @FXML
    public TextField addPartMaxTxt;

    /** This TextField is for adding a PartMin to a new part.
     * This TextField is where a user enters the PartMin for a new Part
     * */
    @FXML
    public TextField addPartMinTxt;

    /** This TextField is for adding a Part special field (companyName or MachineId).
     * This TextField is where a user enters the Part special field (companyName or MachineId) for a new Part
     * */
    @FXML
    public TextField addPartSpecialTxt;

    //Label for special field. MachineID(InHouse) or CompanyName(Outsource)
    /**This is the label for addPartSpecialText.
     * This field changes depending on what radio button is pressed. Changes between companyName(when Outsourced is clicked) and machineId (when InHouse is clicked)
     * */
    @FXML
    public Label addPartSpecialLabel;


    //Radio Buttons for switching between InHouse and Outsource
    //button IDs for Radio buttons
    /** This RadioButton is for InHouse selection.
     * This RadioButton is used to select InHouse which gives the Part a MachineID
     * */
    @FXML
    public RadioButton addPartInHouseRB;

    /** This RadioButton is for Outsource selection.
     * This RadioButton is used to select Outsource which gives the Part a companyName
     * */
    @FXML
    public RadioButton addPartOutsourceRB;

    /** The toggle group for RadioButtons.
     * The toggle group for the InHouse and Outsource RadioButtons
     * */
    @FXML
    public ToggleGroup partType;

   //method for when radio buttons are clicked, sets the text of the label to correct label
    /** This method sets the SpecialLabel to "Machine ID".
     * When clicked the label for addPartSpecialLabel will change to "Machine ID"
     * @param actionEvent actionEvent for when the RadioButton is clicked
     * */
    @FXML
    public void onActionInHouse(ActionEvent actionEvent) {
        addPartSpecialLabel.setText("Machine ID");
    }

    /** This method sets the SpecialLabel to "Company Name".
     * When clicked the label for addPartSpecialLabel will change to "Company Name"
     * @param actionEvent actionEvent for when the RadioButton is clicked
     * */
    @FXML
    public void onActionOutsource(ActionEvent actionEvent) {
        addPartSpecialLabel.setText("Company Name");
    }

    //initialize
    /**This method initializes the AddPartView.
     * This method brings up the AddPartView
     * @param url url for initializing
     * @param resourceBundle resourceBundle for initializing
     * */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("I am initialized");
    }

    //Buttons for Add Part and display main
    /** This method is for saving and returning to MainView.
     * This method saves the information input into a new Part and add it to the PartsObservableList.
     *  RUNTIME ERROR - When creating error dialog boxes the program would get stuck in an infinite loop of creating error dialog
     *                  Boxes. I first encountered this problem in the AddPartController while trying to handle save a new Part.
     *                  If an input was wrong then an error box would display. After clicking "ok" the error box would pop up again.
     *                  This would happen infinitely and the only way to stop would be exit out of the program. This issue was fixed
     *                  by using If statements to control the flow of the program. This solution was applied to the other controllers.
     *
     * @param actionEvent actionEvent for when the Save button is clicked
     * */
    @FXML
    public void onActionSavePart(ActionEvent actionEvent) throws IOException {
        try {
            int id = Inventory.getNewPartId();
            String name = addPartNameTxt.getText();
            int inv = Integer.parseInt(addPartInvTxt.getText());
            double price = Double.parseDouble(addPartPriceTxt.getText());
            int max = Integer.parseInt(addPartMaxTxt.getText());
            int min = Integer.parseInt(addPartMinTxt.getText());

            if (min >= max) {
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
                if (addPartInHouseRB.isSelected()) {
                    int machineId = Integer.parseInt(addPartSpecialTxt.getText());
                    Inventory.addPart(new InHouse(id, name, price, inv, min, max, machineId));
                } else {
                    String companyName = addPartSpecialTxt.getText();
                    Inventory.addPart(new Outsourced(id, name, price, inv, min, max, companyName));
                }

                Parent root = FXMLLoader.load(getClass().getResource("/anderson/c482/MainView.fxml"));
                Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
                Scene scene = new Scene(root, 1050, 500);
                stage.setTitle("MainView");
                stage.setScene(scene);
                stage.show();
            }
        }
        catch (NumberFormatException e) {
            System.out.println("Please enter valid values in text fields.");
            System.out.println("Exception: " + e);
            System.out.println("Exception: " + e.getMessage());
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please enter valid values in text fields.");
            alert.showAndWait();
        }
    }


    //Button for canceling and DisplayMain
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



}