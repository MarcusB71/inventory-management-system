package ca.senecapolytechnic.inventorymanagementsystem.controller;

import ca.senecapolytechnic.inventorymanagementsystem.models.Inventory;
import ca.senecapolytechnic.inventorymanagementsystem.models.Part;
import ca.senecapolytechnic.inventorymanagementsystem.models.Product;
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

    public void setProduct(Product product) {
        selectedProduct = product;
        IDLabel.setText(String.valueOf(product.getId()));
        nameField.setText(product.getName());
        stockField.setText(String.valueOf(product.getStock()));
        priceField.setText(String.valueOf(product.getPrice()));
        minField.setText(String.valueOf(product.getMin()));
        maxField.setText(String.valueOf(product.getMax()));
        tempAssociatedParts.setAll(product.getAssociatedParts());

        allPartsIDCol.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        allPartsNameCol.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        allPartsStockCol.setCellValueFactory(cellData -> cellData.getValue().stockProperty().asObject());
        allPartsPriceCol.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());

        allPartsTable.setItems(Inventory.getAllParts());

        assocPartsIDCol.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        assocPartsNameCol.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        assocPartsStockCol.setCellValueFactory(cellData -> cellData.getValue().stockProperty().asObject());
        assocPartsPriceCol.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());

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
        }
    }

    @FXML
    private void onSave() {
        try {
            selectedProduct.setName(nameField.getText());
            selectedProduct.setStock(Integer.parseInt(stockField.getText()));
            selectedProduct.setPrice(Double.parseDouble(priceField.getText()));
            selectedProduct.setMin(Integer.parseInt(minField.getText()));
            selectedProduct.setMax(Integer.parseInt(maxField.getText()));
            selectedProduct.getAssociatedParts().setAll(tempAssociatedParts);
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
