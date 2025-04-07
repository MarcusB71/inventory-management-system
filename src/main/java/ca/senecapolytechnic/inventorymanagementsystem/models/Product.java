package ca.senecapolytechnic.inventorymanagementsystem.models;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Product implements Serializable {
    private int id;
    private String name;
    private Double price;
    private int stock;
    private int min;
    private int max;
    private List<Part> associatedParts;

    public Product(Integer id, String name, Double price, Integer stock, Integer min, Integer max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
        this.associatedParts = new ArrayList<>();
    }

    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject();  // Write the non-transient fields
        oos.writeObject(new ArrayList<>(associatedParts));  // Convert ObservableList to ArrayList
    }

    // Custom deserialization: Convert ArrayList back to ObservableList
    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject();  // Read the non-transient fields
        this.associatedParts = FXCollections.observableArrayList((List<Part>) ois.readObject());  // Convert ArrayList back to ObservableList
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public ObservableList<Part> getAssociatedParts() {
        return FXCollections.observableArrayList(associatedParts);
    }

    public void addAssociatedPart(Part part) {
        associatedParts.add(part);
    }

    public void deleteAssociatedPart(Part part) {
        associatedParts.remove(part);
    }
}
