package ca.senecapolytechnic.inventorymanagementsystem.controller;

import ca.senecapolytechnic.inventorymanagementsystem.models.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.Optional;

public class MainViewController {
    @FXML private TableView<Part> partsTable;
    @FXML private TableColumn<Part, Integer> partIDCol;
    @FXML private TableColumn<Part, String> partNameCol;
    @FXML private TableColumn<Part, Integer> partStockCol;
    @FXML private TableColumn<Part, Double> partPriceCol;

    @FXML private TableView<Product> productsTable;
    @FXML private TableColumn<Product, Integer> productIDCol;
    @FXML private TableColumn<Product, String> productNameCol;
    @FXML private TableColumn<Product, Integer> productStockCol;
    @FXML private TableColumn<Product, Double> productPriceCol;

    @FXML private TextField searchPartsField;
    @FXML private TextField searchProductsField;

    public void initialize(){
        partIDCol.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        partNameCol.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        partStockCol.setCellValueFactory(cellData -> cellData.getValue().stockProperty().asObject());
        partPriceCol.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());

        productIDCol.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        productNameCol.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        productStockCol.setCellValueFactory(cellData -> cellData.getValue().stockProperty().asObject());
        productPriceCol.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());


        Part part1 = new InHouse(Inventory.generatePartID(), "Wheel", 20.99, 15, 1, 30, 10231);
        Part part2 = new InHouse(Inventory.generatePartID(), "Seat", 55.00, 10, 1, 15, 10322);
        Part part3 = new Outsourced(Inventory.generatePartID(), "Handlebar", 25.50, 25, 5, 50, "BikeCo");
        Part part4 = new InHouse(Inventory.generatePartID(), "Engine", 250.00, 12, 1, 15, 10325);
        Part part5 = new Outsourced(Inventory.generatePartID(), "Battery", 150.50, 25, 5, 50, "BatteryCo");
        Part part6 = new InHouse(Inventory.generatePartID(), "Steering Wheel", 40.00, 10, 1, 15, 1102);
        Part part7 = new Outsourced(Inventory.generatePartID(), "Mirror", 20.50, 25, 5, 50, "MirrorCo");
        Part part8 = new InHouse(Inventory.generatePartID(), "Light bulb", 10.00, 25, 1, 35, 1022);

        Inventory.addPart(part1);
        Inventory.addPart(part2);
        Inventory.addPart(part3);
        Inventory.addPart(part4);
        Inventory.addPart(part5);
        Inventory.addPart(part6);
        Inventory.addPart(part7);
        Inventory.addPart(part8);

        Product bike = new Product(Inventory.generateProductId(), "Mountain Bike", 499.99, 5, 1, 10);
        bike.addAssociatedPart(part1);
        bike.addAssociatedPart(part4);
        bike.addAssociatedPart(part5);
        Product snowmobile = new Product(Inventory.generateProductId(), "Snowmobile", 3599.99, 2, 1, 5);
        snowmobile.addAssociatedPart(part2);
        snowmobile.addAssociatedPart(part3);
        snowmobile.addAssociatedPart(part5);
        Product wheelchair = new Product(Inventory.generateProductId(), "Wheelchair", 1599.99, 5, 1, 10);
        wheelchair.addAssociatedPart(part2);
        wheelchair.addAssociatedPart(part6);
        wheelchair.addAssociatedPart(part7);
        Product moped = new Product(Inventory.generateProductId(), "Moped", 1899.99, 3, 1, 9);
        moped.addAssociatedPart(part1);
        moped.addAssociatedPart(part4);
        moped.addAssociatedPart(part5);
        Product RCCar = new Product(Inventory.generateProductId(), "RC Car", 99.99, 2, 1, 13);
        RCCar.addAssociatedPart(part5);
        RCCar.addAssociatedPart(part6);
        RCCar.addAssociatedPart(part7);
        Product electricBike = new Product(Inventory.generateProductId(), "Electric Bike", 1699.99, 5, 1, 10);
        electricBike.addAssociatedPart(part4);
        electricBike.addAssociatedPart(part7);
        electricBike.addAssociatedPart(part8);
        Product roadBike = new Product(Inventory.generateProductId(), "Road Bike", 1199.99, 3, 1, 5);
        roadBike.addAssociatedPart(part2);
        roadBike.addAssociatedPart(part1);
        Product fourByFour = new Product(Inventory.generateProductId(), "Four by Four", 3229.99, 2, 1, 3);
        fourByFour.addAssociatedPart(part3);
        fourByFour.addAssociatedPart(part4);
        Product unicycle = new Product(Inventory.generateProductId(), "Unicycle", 199.99, 3, 1, 10);
        unicycle.addAssociatedPart(part3);
        unicycle.addAssociatedPart(part4);
        Product goKart = new Product(Inventory.generateProductId(), "Go Kart", 1499.99, 3, 1, 10);
        goKart.addAssociatedPart(part6);
        goKart.addAssociatedPart(part7);
        goKart.addAssociatedPart(part8);

        Inventory.addProduct(bike);
        Inventory.addProduct(snowmobile);
        Inventory.addProduct(wheelchair);
        Inventory.addProduct(moped);
        Inventory.addProduct(RCCar);
        Inventory.addProduct(electricBike);
        Inventory.addProduct(roadBike);
        Inventory.addProduct(fourByFour);
        Inventory.addProduct(unicycle);
        Inventory.addProduct(goKart);

        loadPartsTable();
        loadProductsTable();
        searchPartsField.textProperty().addListener((obs, oldText, newText) -> searchPart(newText));
        searchProductsField.textProperty().addListener((obs, oldText, newText) -> searchProduct(newText));

    };
    public void addProduct(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ca/senecapolytechnic/inventorymanagementsystem/add-product.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Add Part");
            stage.setScene(new Scene(root));
            stage.showAndWait();
            loadPartsTable();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void addPart(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ca/senecapolytechnic/inventorymanagementsystem/add-part.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Add Part");
            stage.setScene(new Scene(root));
            stage.showAndWait();
            loadPartsTable();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void modifyProduct() {
        Product selectedProduct = productsTable.getSelectionModel().getSelectedItem();
        if (selectedProduct != null) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ca/senecapolytechnic/inventorymanagementsystem/modify-product.fxml"));
            Parent root = loader.load();

            ModifyProductController controller = loader.getController();
            controller.setProduct(selectedProduct);

            Stage stage = new Stage();
            stage.setTitle("Modify Product");
            stage.setScene(new Scene(root));
            stage.showAndWait();
            loadProductsTable();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }else {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText("No Product Selected");
        alert.setContentText("Please select a Product to modify.");
        alert.showAndWait();
    }
    }
    public void modifyPart(){
        Part selectedPart = partsTable.getSelectionModel().getSelectedItem();
        if (selectedPart != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/ca/senecapolytechnic/inventorymanagementsystem/modify-part.fxml"));
                Parent root = loader.load();

                ModifyPartController controller = loader.getController();
                controller.setPart(selectedPart);

                Stage stage = new Stage();
                stage.setTitle("Modify Part");
                stage.setScene(new Scene(root));
                stage.showAndWait();
                loadPartsTable();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("No Part Selected");
            alert.setContentText("Select a part to modify");
            alert.showAndWait();
        }
    }
    public void deleteProduct(){
        Product selectedProduct = productsTable.getSelectionModel().getSelectedItem();
        if (!selectedProduct.getAssociatedParts().isEmpty()){
            showErrorDialog("Cannot delete a Product which has parts associated.");
            return;
        }
        if (showDeleteConfirmation()){
            Inventory.deleteProduct(selectedProduct);
            loadProductsTable();
        }
    }
    public void deletePart(){
        Part selectedPart = partsTable.getSelectionModel().getSelectedItem();
        Inventory.deletePart(selectedPart);
        loadPartsTable();
    }

    private void searchPart(String query) {
        ObservableList<Part> filteredParts = FXCollections.observableArrayList();
        if (query.isEmpty()) {
            loadPartsTable();
        } else {
            try {
                int id = Integer.parseInt(query);
                Part foundPart = Inventory.lookupPartById(id);
                if (foundPart != null) filteredParts.add(foundPart);
            } catch (NumberFormatException e) {
                filteredParts = Inventory.lookupPartByName(query);
            }
            partsTable.setItems(filteredParts);
        }
    }
    private void searchProduct(String query) {
        ObservableList<Product> filteredProducts = FXCollections.observableArrayList();
        if (query.isEmpty()) {
            loadProductsTable();
        } else {
            try {
                int id = Integer.parseInt(query);
                Product foundProduct = Inventory.lookupProductById(id);
                if (foundProduct != null) filteredProducts.add(foundProduct);
            } catch (NumberFormatException e) {
                filteredProducts = Inventory.lookupProductByName(query);
            }
            productsTable.setItems(filteredProducts);
        }
    }

    public void loadPartsTable(){
        partsTable.setItems(Inventory.getAllParts());
    };
    public void loadProductsTable(){
        productsTable.setItems(Inventory.getAllProducts());
    };
    private void showErrorDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    private boolean showDeleteConfirmation() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Product");
        alert.setHeaderText("Confirm Deletion");
        alert.setContentText("Are you sure you want to delete this product?");

        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;
    }
}
