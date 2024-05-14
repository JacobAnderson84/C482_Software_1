package anderson.c482.Model;

/**
 *
 * @author Jacob Anderson
 * */

/** This is the class for InHouse parts.
 * This contains methods for InHouse parts which inherits from Part class
 * */
public class InHouse extends Part {
    //declare private fields
    /** This is the field for machineId.
     * */
    private int machineId;

    /** This is the constructor for an InHouse class object.
     * */
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId) {
        super(id, name, price, stock, min, max);
        this.machineId = machineId;
    }

    /** This method gets MachineId.
     * @return Returns machineId
     * */
    //Getter and Setter for InHouse machineId field
    public int getMachineId() {
        return machineId;
    }

    /** This is the method that sets machineId.
     * @param machineId the machineId to set
     * */
    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }



}







