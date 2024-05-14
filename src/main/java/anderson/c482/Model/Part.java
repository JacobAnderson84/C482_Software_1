package anderson.c482.Model;
/**
 *
 * @author Jacob Anderson
 * */

/** This is the abstract class for Parts.
 * This contains methods managing Parts
 * */
//Class for Parts
public abstract class Part {
    //declare Part fields
    /**This is the field for a Parts id.
     */
    private int id;

    /**This is the field for a Parts name.
     */
    private String name;

    /**This is the field for a Parts price.
     */
    private double price;

    /**This is the field for a Parts stock.
     */
    private int stock;

    /**This is the field for a Parts min.
     */
    private int min;

    /**This is the field for a Parts max.
     */
    private int max;

    //constructor for Part
    /** This is the constructor for a Part.
     * Standard constructor setting all the private Fields
     * */
    public Part(int id, String name, double price, int stock, int min, int max){
        this.id=id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    // Getters for Part Class
    /** Gets id from a Part class object.
     * @return Returns id
     * */
    public int getId(){
        return id;
    }

    /** Gets name from a Part class object.
     * @return Returns name
     * */
    public String getName(){
        return name;
    }

    /** Gets price from a Part class object.
     * @return Returns price
     * */
    public double getPrice(){
        return price;
    }

    /** Gets stock from a Part class object.
     * @return Returns stock
     * */
    public int getStock() {
        return stock;
    }

    /** Gets max from a Part class object.
     * @return Returns max
     * */
    public int getMax() {
        return max;
    }

    /** Gets min from a Part class object.
     * @return Returns min
     * */
    public int getMin() {
        return min;
    }

    //Setters for Part Class
    /** Sets id for a Part class object.
     * @param id the id to set
     * */
    public void setId(int id) {
        this.id = id;
    }

    /** Sets name for a Part class object.
     * @param name the name to set
     * */
    public void setName(String name) {
        this.name = name;
    }

    /** Sets price for a Part class object.
     * @param price the price to set
     * */
    public void setPrice(double price) {
        this.price = price;
    }

    /** Sets stock for a Part class object.
     * @param stock the stock to set
     * */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /** Sets min for a Part class object.
     * @param min the min to set
     * */
    public void setMin(int min) {
        this.min = min;
    }

    /** Sets max for a Part class object.
     * @param max the max to set
     * */
    public void setMax(int max) {
        this.max = max;
    }
}
