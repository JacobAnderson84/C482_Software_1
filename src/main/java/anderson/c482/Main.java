package anderson.c482;

import anderson.c482.Model.*;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


/**This Class creates an inventory management system applications.
 *  FUTURE ENHANCEMENT- When adding a new Part or Product when the save button is clicked the program calls the method to
 *                      auto generate the ID and also increment it by 1 for future uses. If there is an invalid input into
 *                      any of the text fields the program will alert user with a dialog box and return user to the AddView
 *                      to fix any input errors. The auto generate ID is still called upon so the ID keeps incrementing.
 *                      A future enhancement would be to stop the auto increment when an error dialog box is shown. This will
 *                      keep the Ids contiguous.
 * src/JavaDocs
 * @author Jacob Anderson
 * */
public class Main extends Application {
    /** This brings up the main view.
     * This gets called to bring up the main view of the application during start up
     * */
    @Override
    public void start(Stage stage) throws IOException {
        //orignal
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("MainView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1050, 500);
        stage.setTitle("MainView");
        stage.setScene(scene);
        stage.show();
    }

    /** This is the main method.
     * This is the first method that gets called when the program runs. It also contains some test data
     * */
    public static void main(String[] args) {
        //Test Data
        //Parts
        Part screw = new InHouse(Inventory.getNewPartId(), "Screw", 0.50, 100, 0, 1000, 01);
        Inventory.addPart(screw);
        Part nail = new InHouse(Inventory.getNewPartId(),"Nail",0.10,75,0,1000,02);
        Inventory.addPart(nail);
        Part rubber = new Outsourced(Inventory.getNewPartId(),"Rubber",0.50,50,0,500,"Global Suppliers");
        Inventory.addPart(rubber);
        Part metalCable = new Outsourced(Inventory.getNewPartId(),"Metal Cable",1.50,55,0,200,"Global Suppliers");
        Inventory.addPart(metalCable);
        Part wood = new Outsourced(Inventory.getNewPartId(), "Wood", 2.00, 100, 0, 400, "Wood Supplier");
        Inventory.addPart(wood);

        //Products
        Product birdFeeder = new Product(Inventory.getNewProductId(), "Bird Feeder", 10.00, 2, 0, 20);
        Inventory.addProduct(birdFeeder);
        Product hangingPlanter = new Product(Inventory.getNewProductId(),"Hanging Planter",15.00,5,0,20);
        Inventory.addProduct(hangingPlanter);


        launch();

    }

}