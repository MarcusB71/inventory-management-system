package ca.senecapolytechnic.inventorymanagementsystem.models;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class Product {
    private final IntegerProperty id;
    private final StringProperty name;
    private final DoubleProperty price;
    private final IntegerProperty stock;
    private final IntegerProperty min;
    private final IntegerProperty max;
    private final ObservableList<Part> associatedParts = FXCollections.observableArrayList();

    public Product(Integer id, String name, Double price, Integer stock, Integer min, Integer max) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleDoubleProperty(price);
        this.stock = new SimpleIntegerProperty(stock);
        this.min = new SimpleIntegerProperty(min);
        this.max = new SimpleIntegerProperty(max);
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public double getPrice() {
        return price.get();
    }

    public void setPrice(double price) {
        this.price.set(price);
    }

    public DoubleProperty priceProperty() {
        return price;
    }

    public int getStock() {
        return stock.get();
    }

    public void setStock(int stock) {
        this.stock.set(stock);
    }

    public IntegerProperty stockProperty() {
        return stock;
    }

    public int getMin() {
        return min.get();
    }

    public void setMin(int min) {
        this.min.set(min);
    }

    public IntegerProperty minProperty() {
        return min;
    }

    public int getMax() {
        return max.get();
    }

    public void setMax(int max) {
        this.max.set(max);
    }

    public IntegerProperty maxProperty() {
        return max;
    }
    public ObservableList<Part> getAssociatedParts() {
        return associatedParts;
    }

    public void addAssociatedPart(Part part) {
        associatedParts.add(part);
    }

    public void deleteAssociatedPart(Part part) {
        associatedParts.remove(part);
    }
}
