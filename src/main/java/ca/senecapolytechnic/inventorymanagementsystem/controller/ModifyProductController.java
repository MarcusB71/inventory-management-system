package ca.senecapolytechnic.inventorymanagementsystem.controller;

import ca.senecapolytechnic.inventorymanagementsystem.models.Inventory;
import ca.senecapolytechnic.inventorymanagementsystem.models.Part;
import ca.senecapolytechnic.inventorymanagementsystem.models.Product;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class ModifyProductController {
    @FXML private TextField nameField, stockField, priceField, minField, maxField;
    @FXML private TableView<Part> allPartsTable, associatedPartsTable;

    @FXML private TableColumn<Part, Integer> allPartsIDCol;
    @FXML private TableColumn<Part, String> allPartsNameCol;
    @FXML private TableColumn<Part, Integer> allPartsStockCol;
    @FXML private TableColumn<Part, Double> allPartsPriceCol;

    @FXML private TableColumn<Part, Integer> assocPartsIDCol;
    @FXML private TableColumn<Part, String> assocPartsNameCol;
    @FXML private TableColumn<Part, Integer> assocPartsStockCol;
    @FXML private TableColumn<Part, Double> assocPartsPriceCol;

    @FXML private Label IDLabel;
    private Product selectedProduct;
    private ObservableList<Part> tempAssociatedParts = FXCollections.observableArrayList();
    private ObservableList<Part> partsToRemove = FXCollections.observableArrayList();

    public void setProduct(Product product) {
        selectedProduct = product;
        IDLabel.setText(String.valueOf(product.getId()));
        nameField.setText(product.getName());
        stockField.setText(String.valueOf(product.getStock()));
        priceField.setText(String.valueOf(product.getPrice()));
        minField.setText(String.valueOf(product.getMin()));
        maxField.setText(String.valueOf(product.getMax()));
        tempAssociatedParts.setAll(product.getAssociatedParts());

        allPartsIDCol.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        allPartsNameCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        allPartsStockCol.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getStock()).asObject());
        allPartsPriceCol.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getPrice()).asObject());

        allPartsTable.setItems(Inventory.getAllParts());

        assocPartsIDCol.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        assocPartsNameCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        assocPartsStockCol.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getStock()).asObject());
        assocPartsPriceCol.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getPrice()).asObject());

        associatedPartsTable.setItems(tempAssociatedParts);
    }

    @FXML
    private void onAddPart() {
        Part selectedPart = allPartsTable.getSelectionModel().getSelectedItem();
        if (selectedPart != null && !tempAssociatedParts.contains(selectedPart)) {
            tempAssociatedParts.add(selectedPart);
        }
    }

    @FXML
    private void onRemovePart() {
        Part selectedPart = associatedPartsTable.getSelectionModel().getSelectedItem();
        if (selectedPart != null) {
            tempAssociatedParts.remove(selectedPart);
            partsToRemove.add(selectedPart);
        }

    }

    @FXML
    private void onSave() {
        try {
            int id = Integer.parseInt(IDLabel.getText());
            String name = nameField.getText().trim();
            int stock = Integer.parseInt(stockField.getText().trim());
            double price = Double.parseDouble(priceField.getText().trim());
            int min = Integer.parseInt(minField.getText().trim());
            int max = Integer.parseInt(maxField.getText().trim());

            // Set the new values for selectedProduct directly
            selectedProduct.setName(name);
            selectedProduct.setStock(stock);
            selectedProduct.setPrice(price);
            selectedProduct.setMin(min);
            selectedProduct.setMax(max);

            for (Part part : partsToRemove) {
                selectedProduct.deleteAssociatedPart(part);
            }

            // Update the associated parts list of the product
            selectedProduct.getAssociatedParts().setAll(tempAssociatedParts);

            // Optionally, you can re-add parts to the product's associated parts
            for (Part part : tempAssociatedParts) {
                selectedProduct.addAssociatedPart(part);
            }
            // Update the product in the inventory (no need to create a new product)
            Inventory.updateProduct(id, selectedProduct);

            closeWindow();

        } catch (NumberFormatException e) {
            showErrorDialog("Please enter valid numbers for Name, Stock, Price, Min, and Max.");
        }
    }


    @FXML
    private void onCancel() {
        closeWindow();
    }

    private void closeWindow() {
        Stage stage = (Stage) IDLabel.getScene().getWindow();
        stage.close();
    }
    private void showErrorDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
