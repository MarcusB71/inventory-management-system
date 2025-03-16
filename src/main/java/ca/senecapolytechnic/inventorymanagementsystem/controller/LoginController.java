package ca.senecapolytechnic.inventorymanagementsystem.controller;

import ca.senecapolytechnic.inventorymanagementsystem.models.Login;
import ca.senecapolytechnic.inventorymanagementsystem.utility.SceneLoader;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class LoginController {
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;

    public void initialize(){
    }

    @FXML
    public void handleLogin() {
        String enteredUsername = usernameField.getText();
        String enteredPassword = passwordField.getText();
        Login user = new Login(enteredUsername, enteredPassword);

        if (user.validate()) {
            SceneLoader.loadScene((Stage) usernameField.getScene().getWindow(), "/ca/senecapolytechnic/inventorymanagementsystem/main-view.fxml");
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid credentials");
            alert.setContentText("Please enter a valid username and password.");
            alert.showAndWait();
        }
    }
}
