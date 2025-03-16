package ca.senecapolytechnic.inventorymanagementsystem.controller;

import ca.senecapolytechnic.inventorymanagementsystem.models.InHouse;
import ca.senecapolytechnic.inventorymanagementsystem.models.Inventory;
import ca.senecapolytechnic.inventorymanagementsystem.models.Outsourced;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;


public class AddPartController {
    @FXML private TextField partNameField;
    @FXML private Label partID;
    @FXML private TextField partStockField;
    @FXML private TextField partPriceField;
    @FXML private TextField partMinField;
    @FXML private TextField partMaxField;
    @FXML private TextField partMachineIDCompanyField;

    @FXML private Label partMachineIDCompanyLabel;
    @FXML private RadioButton partInHouseRadio;
    @FXML private RadioButton partOutsourcedRadio;
    @FXML private ToggleGroup sourceToggle;

    public void initialize(){
        sourceToggle.selectToggle(partInHouseRadio);
        updatePartType();
        Integer generatedPartId = Inventory.generatePartID();
        partID.setText(generatedPartId.toString());
    }
    @FXML
    private void updatePartType() {
        if (partInHouseRadio.isSelected()) {
            partMachineIDCompanyLabel.setText("Machine ID:");
        } else {
            partMachineIDCompanyLabel.setText("Company Name:");
        }
    }
    @FXML
    private void onSave() {
        try {
            String name = partNameField.getText().trim();
            int stock = Integer.parseInt(partStockField.getText().trim());
            double price = Double.parseDouble(partPriceField.getText().trim());
            int min = Integer.parseInt(partMinField.getText().trim());
            int max = Integer.parseInt(partMaxField.getText().trim());

            if (min > max) {
                showErrorDialog("Min cannot be greater than Max.");
                return;
            }
            if (stock < min || stock > max) {
                showErrorDialog("Stock must be between Min and Max.");
                return;
            }

            if (partInHouseRadio.isSelected()) {
                int generatedPartID = Integer.parseInt(partID.getText());
                int machineId = Integer.parseInt(partMachineIDCompanyField.getText().trim());
                Inventory.addPart(new InHouse(generatedPartID, name, price, stock, min, max, machineId));
            } else {
                int generatedPartID = Integer.parseInt(partID.getText());
                String companyName = partMachineIDCompanyField.getText().trim();
                Inventory.addPart(new Outsourced(generatedPartID, name, price, stock, min, max, companyName));
            }

            Stage stage = (Stage) partNameField.getScene().getWindow();
            stage.close();

        } catch (NumberFormatException e) {
            showErrorDialog("Please enter valid numbers for Stock, Price, Min, Max, and Machine ID.");
        }
    }
    @FXML
    private void onCancel() {
        Stage stage = (Stage) partNameField.getScene().getWindow();
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
