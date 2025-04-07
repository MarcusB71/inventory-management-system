package ca.senecapolytechnic.inventorymanagementsystem.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Inventory implements Serializable {
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();
    private static int partIdCounter = 1;
    private static int productIdCounter = 1;

    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }

//    public static void writeObject(ObjectOutputStream oos) throws IOException {
//        oos.defaultWriteObject();  // Write the non-transient fields
//        oos.writeObject(new ArrayList<>(allParts));  // Convert ObservableList to ArrayList
//        oos.writeObject(new ArrayList<>(allProducts));  // Convert ObservableList to ArrayList
//    }

    // Custom deserialization: Convert List back to ObservableList
//    public void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
//        ois.defaultReadObject();  // Read the non-transient fields
//        allParts = (List<Part>) ois.readObject();  // Read List and assign to allParts
//        allProducts = (List<Product>) ois.readObject();  // Read List and assign to allProducts
//    }

    public static void addPart(Part part) {
        allParts.add(part);
    }
    public static void addProduct(Product product) {
        allProducts.add(product);
    }

    public static boolean updatePart(int id, Part updatedPart) {
        for (int i = 0; i < allParts.size(); i++) {
            if (allParts.get(i).getId() == id) {
                allParts.set(i, updatedPart);
                return true;
            }
        }
        return false;
    }
    public static boolean updateProduct(int id, Product updatedProduct) {
        for (int i = 0; i < allProducts.size(); i++) {
            if (allProducts.get(i).getId() == id) {
                allProducts.set(i, updatedProduct);
                return true;
            }
        }
        return false;
    }

    public static boolean deletePart(Part part) {
        return allParts.remove(part);
    }
    public static boolean deleteProduct(Product product) {
        return allProducts.remove(product);
    }

    public static Part lookupPartById(int id) {
        for (Part part : allParts) {
            if (part.getId() == id) {
                return part;
            }
        }
        return null;
    }
    public static Product lookupProductById(int id) {
        for (Product product : allProducts) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    public static ObservableList<Part> lookupPartByName(String name) {
        ObservableList<Part> filteredParts = FXCollections.observableArrayList();
        for (Part part : allParts) {
            if (part.getName().toLowerCase().contains(name.toLowerCase())) {
                filteredParts.add(part);
            }
        }
        return filteredParts;
    }
    public static ObservableList<Product> lookupProductByName(String name) {
        ObservableList<Product> filteredProducts = FXCollections.observableArrayList();
        for (Product product : allProducts) {
            if (product.getName().toLowerCase().contains(name.toLowerCase())) {
                filteredProducts.add(product);
            }
        }
        return filteredProducts;
    }

    public static int generatePartID() {
        return partIdCounter++;
    }
    public static int generateProductId() {
        return productIdCounter++;
    }

    public static void setAllParts(ObservableList<Part> parts) { allParts = parts; }
    public static void setAllProducts(ObservableList<Product> products) { allProducts = products; }
}
