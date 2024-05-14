package anderson.c482.Model;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Jacob Anderson
 * */

/** This is the class for Products.
 * This contains methods for managing Products
 * */
public class Product{
    //declaring Product fields

    //Observable list to show all Parts associated with a Product
    /** This is the field that holds the associatedParts Observable List.
     * The associatedParts is list of all Parts that are associated with an individual Product
     * */
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();

    /**This is the field for a Products id.
     */
    private int id;

    /**This is the field for a Products name.
     */
    private String name;

    /**This is the field for a Products price.
     */
    private double price;

    /**This is the field for a Products stock.
     */
    private int stock;

    /**This is the field for a Products min.
     */
    private int min;

    /**This is the field for a Products max.
     */
    private int max;

    //Constructor for Product
    /** This is the constructor for a Product.
     * Standard constructors setting all the private Fields
     * */
    public Product(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /**This is another constructor for a Product
     * This constructor also can accept the ObservableList of associatedParts
     * */
    public Product(int id, String name, double price, int stock, int min, int max, ObservableList<Part> associatedParts) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
        this.associatedParts = associatedParts;
    }


    //Getters for Product
    /** Gets id from a Product class object.
     * @return Returns id
     * */
    public int getId() {
        return id;
    }

    /** Gets name from a Product class object.
     * @return Returns name
     * */
    public String getName() {
        return name;
    }

    /** Gets price from a Product class object.
     * @return Returns price
     * */
    public double getPrice() {
        return price;
    }

    /** Gets stock from a Product class object.
     * @return Returns stock
     * */
    public int getStock() {
        return stock;
    }

    /** Gets min from a Product class object.
     * @return Returns min
     * */
    public int getMin() {
        return min;
    }

    /** Gets max from a Product class object.
     * @return Returns max
     * */
    public int getMax() {
        return max;
    }

    //Setters for Product
    /** Sets id for a Product class object.
     * @param id the id to set
     * */
    public void setId(int id) {
        this.id = id;
    }

    /** Sets name for a Product class object.
     * @param name the name to set
     * */
    public void setName(String name) {
        this.name = name;
    }

    /** Sets price for a Product class object.
     * @param price the price to set
     * */
    public void setPrice(double price) {
        this.price = price;
    }

    /** Sets stock for a Product class object.
     * @param stock the stock to set
     * */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /** Sets min for a Product class object.
     * @param min the min to set
     * */
    public void setMin(int min) {
        this.min = min;
    }

    /** Sets max for a Product class object.
     * @param max the max to set
     * */
    public void setMax(int max) {
        this.max = max;
    }

    //method for add associatedPart from Part class
    /** This method adds a Part to the associatedParts observable list.
     * Takes a selected Part in AddProductView or ModProductView and adds it the list of associatedPart for a Product class object
     * @param part the Part to add
     * */
    public void addAssociatedPart(Part part){
        associatedParts.add(part);
    }

    /** This method removes a part from the associatedParts observable list.
     * Takes a selected Part in AddProductView or ModProductView and removes it from the list of associatedParts for a Product class object
     * @param selectedAssociatedPart the associated part to remove
     * @return Returns boolean
     * */
    //method for deleting associatedPart
    public boolean deleteAssociatedPart(Part selectedAssociatedPart){
        associatedParts.remove(selectedAssociatedPart);
        return false;
    }

    // Observable list of AssociatedParts for each instance of a Product
    /** This method gets all associatedParts for a Product class object.
     * Returns an observable list of all associated Parts for a Products class object
     * @return  Returns ObservableList
     * */
    public ObservableList<Part> getAllAssociatedParts(){
        return associatedParts;
    }


}
