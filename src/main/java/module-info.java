module ca.senecapolytechnic.inventorymanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens ca.senecapolytechnic.inventorymanagementsystem.controller to javafx.fxml;
    exports ca.senecapolytechnic.inventorymanagementsystem;
}