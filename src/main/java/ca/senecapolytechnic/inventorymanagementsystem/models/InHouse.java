package ca.senecapolytechnic.inventorymanagementsystem.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class InHouse extends Part{
    private final IntegerProperty machineID = new SimpleIntegerProperty();

    public InHouse(Integer id, String name, Double price, Integer stock, Integer min, Integer max, Integer machineID) {
        super(id, name, price, stock, min, max);
        this.machineID.set(machineID);
    }
    public IntegerProperty machineIDProperty() { return machineID; }

    public int getMachineID() { return machineID.get(); }
    public void setMachineID(int machineID) { this.machineID.set(machineID); }
}
