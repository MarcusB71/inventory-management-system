package ca.senecapolytechnic.inventorymanagementsystem.controller;

import ca.senecapolytechnic.inventorymanagementsystem.models.*;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import java.io.*;
import java.sql.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        partIDCol.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        partNameCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        partStockCol.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getStock()).asObject());
        partPriceCol.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getPrice()).asObject());

        productIDCol.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        productNameCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        productStockCol.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getStock()).asObject());
        productPriceCol.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getPrice()).asObject());


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

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    @FXML
    public void handleSaveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data.dat"))) {

            oos.writeObject(new ArrayList<Part>(Inventory.getAllParts()));
            oos.writeObject(new ArrayList<Product>(Inventory.getAllProducts()));
            System.out.println("Data saved to file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleLoadFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data.dat"))) {
            // Read the parts and products from the file
            ArrayList<Part> loadedParts = (ArrayList<Part>) ois.readObject();
            ArrayList<Product> loadedProducts = (ArrayList<Product>) ois.readObject();

            // Clear current inventory data before loading new data
            Inventory.getAllParts().clear();
            Inventory.getAllProducts().clear();

            // Add the loaded parts and products to the inventory
            Inventory.getAllParts().addAll(loadedParts);
            Inventory.getAllProducts().addAll(loadedProducts);

            System.out.println("Data loaded from file.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    @FXML
    public void handleWriteToDB() {
        String dbLocation = "jdbc:sqlite:C:\\Users\\Marcus\\IdeaProjects\\InventoryManagementSystem\\src\\main\\java\\ca\\senecapolytechnic\\inventorymanagementsystem\\database\\test.db";
        try (Connection conn = DriverManager.getConnection(dbLocation)) {
            Statement stmt = conn.createStatement();
            // delete tables so they can be overwritten
            stmt.execute("DELETE FROM product_parts");
            stmt.execute("DELETE FROM parts");
            stmt.execute("DELETE FROM products");

            String createPartsTable = """
            CREATE TABLE IF NOT EXISTS parts (
                id INT PRIMARY KEY,
                name VARCHAR(255) NOT NULL,
                price DECIMAL(10,2) NOT NULL,
                stock INT NOT NULL,
                min INT NOT NULL,
                max INT NOT NULL,
                machineID INT NULL,
                companyName VARCHAR(255) NULL
            )
        """;
            stmt.execute(createPartsTable);

            String createProductsTable = """
            CREATE TABLE IF NOT EXISTS products (
                id INT PRIMARY KEY,
                name VARCHAR(255) NOT NULL,
                price DECIMAL(10,2) NOT NULL,
                stock INT NOT NULL,
                min INT NOT NULL,
                max INT NOT NULL
            )
        """;
            stmt.execute(createProductsTable);

            String createProductPartsTable = """
            CREATE TABLE IF NOT EXISTS product_parts (
                product_id INT NOT NULL,
                part_id INT NOT NULL,
                PRIMARY KEY (product_id, part_id),
                FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE,
                FOREIGN KEY (part_id) REFERENCES parts(id) ON DELETE CASCADE
            )
        """;
            stmt.execute(createProductPartsTable);

            // Save parts
            String partSql = "INSERT INTO parts (id, name, price, stock, min, max, machineID, companyName) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement partStmt = conn.prepareStatement(partSql)) {
                for (Part part : Inventory.getAllParts()) {
                    partStmt.setInt(1, part.getId());
                    partStmt.setString(2, part.getName());
                    partStmt.setDouble(3, part.getPrice());
                    partStmt.setInt(4, part.getStock());
                    partStmt.setInt(5, part.getMin());
                    partStmt.setInt(6, part.getMax());

                    // Check if part is InHouse or Outsourced
                    if (part instanceof InHouse) {
                        partStmt.setInt(7, ((InHouse) part).getMachineID());
                        partStmt.setString(8, null); // Set company name as null for InHouse
                    } else if (part instanceof Outsourced) {
                        partStmt.setInt(7, 0); // Set machineID as 0 for Outsourced
                        partStmt.setString(8, ((Outsourced) part).getCompanyName());
                    }
                    partStmt.addBatch();
                }
                partStmt.executeBatch();
            }

            // Save products (similar to parts)
            String productSql = "INSERT INTO products (id, name, price, stock, min, max) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement productStmt = conn.prepareStatement(productSql)) {
                for (Product product : Inventory.getAllProducts()) {
                    productStmt.setInt(1, product.getId());
                    productStmt.setString(2, product.getName());
                    productStmt.setDouble(3, product.getPrice());
                    productStmt.setInt(4, product.getStock());
                    productStmt.setInt(5, product.getMin());
                    productStmt.setInt(6, product.getMax());
                    productStmt.addBatch();
                }
                productStmt.executeBatch();
            }

            // Product associated Parts table
                String productPartSql = "INSERT INTO product_parts (product_id, part_id) VALUES (?, ?)";
            try (PreparedStatement productPartStmt = conn.prepareStatement(productPartSql)){

                for (Product product : Inventory.getAllProducts()) {
                    for (Part part : product.getAssociatedParts()) {
                        productPartStmt.setInt(1, product.getId());
                        productPartStmt.setInt(2, part.getId());
                        productPartStmt.addBatch();
                    }
                }

                productPartStmt.executeBatch(); // Execute all insertions at once for efficiency
            } catch (SQLException e) {
                e.printStackTrace();
            }

            System.out.println("Data saved to database successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to save data to the database.");
        }
    }
    @FXML
    public void handleLoadFromDB() {
        String dbLocation = "jdbc:sqlite:C:\\Users\\Marcus\\IdeaProjects\\InventoryManagementSystem\\src\\main\\java\\ca\\senecapolytechnic\\inventorymanagementsystem\\database\\test.db";
        try (Connection conn = DriverManager.getConnection(dbLocation)) {
            // Load parts
            String partSql = "SELECT * FROM parts";
            try (PreparedStatement partStmt = conn.prepareStatement(partSql);
                 ResultSet rs = partStmt.executeQuery()) {

                List<Part> parts = new ArrayList<>();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    double price = rs.getDouble("price");
                    int stock = rs.getInt("stock");
                    int min = rs.getInt("min");
                    int max = rs.getInt("max");

                    // Check if part is InHouse or Outsourced
                    if (rs.getString("companyName") == null) {
                        // InHouse part
                        int machineID = rs.getInt("machineID");
                        parts.add(new InHouse(id, name, price, stock, min, max, machineID));
                    } else {
                        // Outsourced part
                        String companyName = rs.getString("companyName");
                        parts.add(new Outsourced(id, name, price, stock, min, max, companyName));
                    }
                }
                Inventory.setAllParts(FXCollections.observableArrayList(parts));
            }

            // Load products (similar to parts)
            String productSql = "SELECT * FROM products";
            try (PreparedStatement productStmt = conn.prepareStatement(productSql);
                 ResultSet rs = productStmt.executeQuery()) {

                List<Product> products = new ArrayList<>();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    double price = rs.getDouble("price");
                    int stock = rs.getInt("stock");
                    int min = rs.getInt("min");
                    int max = rs.getInt("max");
                    products.add(new Product(id, name, price, stock, min, max));
                }
                Inventory.setAllProducts(FXCollections.observableArrayList(products));
            }

            try {
                String sql = """
            SELECT pp.product_id, p.id, p.name, p.price, p.stock, p.min, p.max, p.machineID, p.companyName 
            FROM parts p
            JOIN product_parts pp ON p.id = pp.part_id
        """;

                try (PreparedStatement stmt = conn.prepareStatement(sql);
                     ResultSet rs = stmt.executeQuery()) {

                    while (rs.next()) {
                        int productId = rs.getInt("product_id");
                        int partId = rs.getInt("id");
                        String name = rs.getString("name");
                        double price = rs.getDouble("price");
                        int stock = rs.getInt("min");
                        int min = rs.getInt("min");
                        int max = rs.getInt("max");
                        Integer machineId = rs.getObject("machineID", Integer.class); // Nullable
                        String companyName = rs.getString("companyName"); // Nullable

                        Part part;
                        if (companyName != null) {
                            part = new Outsourced(partId, name, price, stock, min, max, companyName);
                        } else {
                            part = new InHouse(partId, name, price, stock, min, max, machineId);
                        }

                        for (Product product : Inventory.getAllProducts()) {
                            if (productId == product.getId()){
                                product.addAssociatedPart(part);
                            }
                        }
                    }
                    loadProductsTable();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            System.out.println("Data loaded from database successfully!");
            handleRefresh(); // Update the UI after loading data
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to load data from the database.");
        }
    }
        @FXML
        public void handleRefresh(){
            loadPartsTable();
            loadProductsTable();
        }
}
