module com.bicitienda.bikeserviceapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.bicitienda.bikeserviceapp.controller to javafx.fxml;
    exports com.bicitienda.bikeserviceapp;
}