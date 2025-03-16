package ca.senecapolytechnic.inventorymanagementsystem.controller;
import ca.senecapolytechnic.inventorymanagementsystem.models.Inventory;
import ca.senecapolytechnic.inventorymanagementsystem.models.Part;
import ca.senecapolytechnic.inventorymanagementsystem.models.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class AddProductController {
    @FXML
    private TextField productNameField;
    @FXML private TextField productPriceField;
    @FXML private TextField productStockField;
    @FXML private TextField productMinField;
    @FXML private TextField productMaxField;
    @FXML private Label productIDLabel;

    @FXML private TableView<Part> allPartsTable;

    @FXML private TableColumn<Part, Integer> allPartsIDCol;
    @FXML private TableColumn<Part, String> allPartsNameCol;
    @FXML private TableColumn<Part, Integer> allPartsStockCol;
    @FXML private TableColumn<Part, Double> allPartsPriceCol;

    @FXML private TableView<Part> associatedPartsTable;
    @FXML private TableColumn<Part, Integer> assocPartsIDCol;
    @FXML private TableColumn<Part, String> assocPartsNameCol;
    @FXML private TableColumn<Part, Integer> assocPartsStockCol;
    @FXML private TableColumn<Part, Double> assocPartsPriceCol;

    @FXML private TextField searchAvailablePartsField;


    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        searchAvailablePartsField.textProperty().addListener((obs, oldText, newText) -> handleSearchParts());

        Integer generatedProductID = Inventory.generateProductId();
        productIDLabel.setText(generatedProductID.toString());
        // Initialize All Parts Table
        allPartsIDCol.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        allPartsNameCol.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        allPartsStockCol.setCellValueFactory(cellData -> cellData.getValue().stockProperty().asObject());
        allPartsPriceCol.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());

        allPartsTable.setItems(Inventory.getAllParts());

        // Initialize Associated Parts Table
        assocPartsIDCol.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        assocPartsNameCol.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        assocPartsStockCol.setCellValueFactory(cellData -> cellData.getValue().stockProperty().asObject());
        assocPartsPriceCol.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());

        associatedPartsTable.setItems(associatedParts);
    }

    @FXML
    private void onAddPart() {
        Part selectedPart = allPartsTable.getSelectionModel().getSelectedItem();
        if (selectedPart != null) {
            associatedParts.add(selectedPart);
        } else {
            showErrorDialog("No part selected to add.");
        }
    }


    @FXML
    private void onRemovePart() {
        Part selectedPart = associatedPartsTable.getSelectionModel().getSelectedItem();
        if (selectedPart != null) {
            associatedParts.remove(selectedPart);
        } else {
            showErrorDialog("No part selected to remove.");
        }
    }

    @FXML
    private void onSaveProduct() {
        try {
            String name = productNameField.getText().trim();
            double price = Double.parseDouble(productPriceField.getText().trim());
            int stock = Integer.parseInt(productStockField.getText().trim());
            int min = Integer.parseInt(productMinField.getText().trim());
            int max = Integer.parseInt(productMaxField.getText().trim());

            // Validation
            if (min > max) {
                showErrorDialog("Min cannot be greater than Max.");
                return;
            }
            if (stock < min || stock > max) {
                showErrorDialog("Stock must be between Min and Max.");
                return;
            }
            if (associatedParts.isEmpty()){
                showErrorDialog("Must have at least one associated Part.");
                return;
            }
            double totalValParts = 0.0;
            for (Part part : associatedParts){
                totalValParts += part.getPrice();
            }
            if (Double.parseDouble(productPriceField.getText()) < totalValParts){
                showErrorDialog("Total value of parts must be less than product.");
                return;
            }
            // Create new product
            int generatedProductID = Integer.parseInt(productIDLabel.getText());
            Product newProduct = new Product(generatedProductID, name, price, stock, min, max);
            for (Part part : associatedParts) {
                newProduct.addAssociatedPart(part);
            }

            Inventory.addProduct(newProduct);

            Stage stage = (Stage) productNameField.getScene().getWindow();
            stage.close();
        } catch (NumberFormatException e) {
            showErrorDialog("Please enter valid numbers for Stock, Price, Min, and Max.");
        }
    }
    @FXML
    private void handleSearchParts() {
        String searchText = searchAvailablePartsField.getText().trim().toLowerCase();
        ObservableList<Part> filteredParts = FXCollections.observableArrayList();

        if (searchText.isEmpty()) {
            allPartsTable.setItems(Inventory.getAllParts());
            return;
        }

        for (Part part : Inventory.getAllParts()) {
            if (String.valueOf(part.getId()).contains(searchText) || part.getName().toLowerCase().contains(searchText)) {
                filteredParts.add(part);
            }
        }

        allPartsTable.setItems(filteredParts);

        if (filteredParts.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("No Results");
            alert.setHeaderText(null);
            alert.setContentText("No parts found matching: " + searchText);
            alert.showAndWait();
        }
    }

    @FXML
    private void onCancel() {
        Stage stage = (Stage) productNameField.getScene().getWindow();
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
