package ca.senecapolytechnic.inventorymanagementsystem.controller;

import ca.senecapolytechnic.inventorymanagementsystem.models.InHouse;
import ca.senecapolytechnic.inventorymanagementsystem.models.Inventory;
import ca.senecapolytechnic.inventorymanagementsystem.models.Outsourced;
import ca.senecapolytechnic.inventorymanagementsystem.models.Part;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ModifyPartController {
    @FXML private TextField  nameField, stockField, priceField, minField, maxField, machineIDCompanyField;
    @FXML private RadioButton inHouseRadio, outsourcedRadio;
    @FXML private Label machineIDCompanyLabel, IDLabel;
    private Part selectedPart;

    public void setPart(Part part) {
        selectedPart = part;
        IDLabel.setText(String.valueOf(part.getId()));
        nameField.setText(part.getName());
        stockField.setText(String.valueOf(part.getStock()));
        priceField.setText(String.valueOf(part.getPrice()));
        minField.setText(String.valueOf(part.getMin()));
        maxField.setText(String.valueOf(part.getMax()));

        if (part instanceof InHouse) {
            inHouseRadio.setSelected(true);
            machineIDCompanyLabel.setText("Machine ID:");
            machineIDCompanyField.setText(String.valueOf(((InHouse) part).getMachineID()));
        } else {
            outsourcedRadio.setSelected(true);
            machineIDCompanyLabel.setText("Company Name:");
            machineIDCompanyField.setText(((Outsourced) part).getCompanyName());
        }
    }
    @FXML
    private void updatePartType() {
        if (inHouseRadio.isSelected()) {
            machineIDCompanyLabel.setText("Machine ID:");
        } else {
            machineIDCompanyLabel.setText("Company Name:");
        }
    }
    @FXML
    private void onSave() {
        int id = Integer.parseInt(IDLabel.getText());
        String name = nameField.getText().trim();
        int stock = Integer.parseInt(stockField.getText().trim());
        double price = Double.parseDouble(priceField.getText().trim());
        int min = Integer.parseInt(minField.getText().trim());
        int max = Integer.parseInt(maxField.getText().trim());

        if (min > max) {
            showErrorDialog("Min cannot be greater than Max.");
            return;
        }
        if (stock < min || stock > max) {
            showErrorDialog("Stock must be between Min and Max.");
            return;
        }

        Part updatedPart;
        if (inHouseRadio.isSelected()) {
            int machineId = Integer.parseInt(machineIDCompanyField.getText());
            updatedPart = new InHouse(id, name, price, stock, min, max, machineId);
        } else {
            String companyName = machineIDCompanyField.getText();
            updatedPart = new Outsourced(id, name, price, stock, min, max, companyName);
        }

        Inventory.updatePart(id, updatedPart);
        closeWindow();
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
