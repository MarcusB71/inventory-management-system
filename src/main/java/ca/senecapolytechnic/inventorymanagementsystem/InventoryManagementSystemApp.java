/**********************************************
 Workshop # 4&5
 Course: APD545
 Last Name: Brown
 First Name: Marcus
 ID: 127900223
 Section: NBB
 This assignment represents my own work in accordance with Seneca Academic Policy.
 Signature
 Date: March 16, 2025
 **********************************************/
package ca.senecapolytechnic.inventorymanagementsystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class InventoryManagementSystemApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(InventoryManagementSystemApp.class.getResource("/ca/senecapolytechnic/inventorymanagementsystem/login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 300, 300);
        stage.setTitle("Inventory Management System");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}