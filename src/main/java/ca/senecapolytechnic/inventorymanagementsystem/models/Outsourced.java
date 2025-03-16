package ca.senecapolytechnic.inventorymanagementsystem.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Outsourced extends Part{

    private final StringProperty companyName = new SimpleStringProperty();
    public Outsourced(Integer ID, String name, Double price, Integer stock, Integer min, Integer max, String companyName) {
        super(ID, name, price, stock, min, max);
        this.companyName.set(companyName);
    }
    public StringProperty companyNameProperty() { return companyName; }

    public String getCompanyName() { return companyName.get(); }
    public void setCompanyName(String companyName) { this.companyName.set(companyName); }
}
