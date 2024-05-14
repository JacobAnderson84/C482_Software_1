package anderson.c482.Model;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Jacob Anderson
 * */

/** This is the class for Inventory.
 * This contains methods for managing Products and Parts
 * */
//Class for Inventory implements Part and Product classes
public class Inventory {
    //Private fields
    /** This is the field that holds the allParts Observable List.
     * The allParts is list of all Parts objects
     * */
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();

    /** This is the field that holds the allProducts Observable List.
     * The allProducts is list of all Product objects
     * */
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    /** This is the field for partId.
     * Used when generating unique Ids for Parts
     * */
    private static int partId = 1;

    /** This is the field for productId.
     * Used when generating unique Ids for Products
     * */
    private static int prodictId = 1;


    //Public methods
    /** This method adds a new Part to the allParts list.
     * @param newPart newPart to add
     * */
    public static void addPart(Part newPart){
        allParts.add(newPart);
    }

    /** This method adds a new Product to the allProducts list.
     * @param newProduct newProduct to add
     * */
    //add product to allProducts list
    public static void addProduct(Product newProduct){
        allProducts.add(newProduct);
    }

    //LOOKUP Part by ID
    /** This method looks up a Part using a partId.
     * @param partId the id too be looked for
     * */
   public static Part lookupPartId(int partId){
        ObservableList<Part> allParts = Inventory.getAllParts();
        for(int i = 0; i < allParts.size(); i++ ){
            Part input = allParts.get(i);

            if(input.getId() == partId){
                return input;
            }
        }
        return null;
    }

    /** This method looks up a Product using a productId.
     * @param productId the id too be looked for
     * */
    public static Product lookupProductId(int productId){
        ObservableList<Product> allProducts = Inventory.getAllProducts();
        for(int i = 0; i < allProducts.size(); i++){
            Product input = allProducts.get(i);

            if(input.getId() == productId){
                return input;
            }
        }
        return null;
    }

    /** This method looks up a Part using a partName.
     * @param partName the name too be looked for
     * @return Returns the Part looked up
     * */
    public static  ObservableList<Part> lookupPartName(String partName){
        ObservableList<Part> namedPart = FXCollections.observableArrayList();

        ObservableList<Part> allParts = Inventory.getAllParts();

        for(Part input : allParts) {
            if (input.getName().contains(partName)){
                namedPart.add(input);
            }
        }
        return namedPart;
    }

    /** This method looks up a Product using a productName.
     * @param productName the name too be looked for
     * @return Returns the Product looked up
     * */
    public static ObservableList<Product> lookupProductName(String productName){
        ObservableList<Product> namedProduct = FXCollections.observableArrayList();

        ObservableList<Product> allProducts = Inventory.getAllProducts();

        for(Product input : allProducts){
            if(input.getName().contains(productName)){
                namedProduct.add(input);
            }
        }
        return namedProduct;
    }

    /** This method updates a Part in the allParts list.
     * @param selectedPart Part to update
     * */
    public static void updatePart(int index, Part selectedPart){
        allParts.set(index, selectedPart);
    }

    /** This method updates a Product in the allProducts list.
     * @param newProduct newProduct to update
     * */
    public static void updateProduct(int index, Product newProduct){
        allProducts.set(index, newProduct);
    };

    /** This method deletes a Part from the allParts list.
     * @param selectedPart The Part to delete
     * @return Returns boolean
     * */
    public static boolean deletePart(Part selectedPart){
        allParts.remove(selectedPart);
        System.out.println("Selected Part Deleted");
        System.out.println("New size of Parts list " + allParts.size());
        return false;
    }

    /** This method deletes a Product from the allProducts list.
     * @param selectedProduct The Product to delete
     * @return Returns Boolean
     * */
    public static boolean deleteProduct(Product selectedProduct){
        allProducts.remove(selectedProduct);
        System.out.println("Selected Product Deleted");
        System.out.println("New size of Products list " + allProducts.size());
        return false;
    }

    /** This method gets the allParts list.
     * @return Returns allParts
     * */
    public static ObservableList<Part> getAllParts(){
        return allParts;
    }

    /** This method gets the allProducts list.
     * @return Returns allProducts
     * */
    public static ObservableList<Product> getAllProducts(){
        return allProducts;
    }


    /** This method is used for creating unique partId.
     * When called upon it increments productId by 1
     * @return  Returns partId
     * */
    public static int getNewPartId(){
        return partId++;
    }

    /** This method is used for creating unique productId.
     * When called upon it increments productId by 1
     * @return  Returns productId
     * */
    public static int getNewProductId() { return prodictId++; }


}
