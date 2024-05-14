package anderson.c482.Model;

/**
 *
 * @author Jacob Anderson
 * */

/** This is the class for Outsourced parts.
 * This contains methods for Outsourced parts which inherits from Part class
 * */
public class Outsourced extends Part{

    //declare fields for Outsourced
    /** This is the field for companyName.
     * */
    private String companyName;


    /** This is the constructor for an Outsource class object.
     * */
    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }


    //Getter and Setter for Outsourced companyName field
    /** This method gets companyName.
     * @return Returns companyName
     * */
    public String getCompanyName() {
        return companyName;
    }

    /** This is the method that sets companyName.
     * @param companyName the companyName to set
     * */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
