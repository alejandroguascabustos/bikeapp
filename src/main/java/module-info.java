module com.bicitienda.bikeserviceapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    // Sólo declarar los módulos que realmente están en pom.xml (evita errores en tiempo de compilación)
    requires org.controlsfx.controls; // controlsfx está declarado en pom.xml
    // Necesario para usar JDBC (java.sql)
    requires java.sql;
    // Necesario si usamos java.util.logging en el código
    requires java.logging;
    // Si en el futuro añades more libs (formsfx, validatorfx, ikonli, tilesfx, fxgl), añade sus dependencias en pom.xml

    opens com.bicitienda.bikeserviceapp to javafx.fxml;
    // Abrir el paquete de controladores a javafx.fxml para que FXMLLoader pueda acceder a los controladores
    opens com.bicitienda.bikeserviceapp.controller to javafx.fxml;
    // Exportar el paquete de controladores y modelos si otros módulos o pruebas lo requieren
    exports com.bicitienda.bikeserviceapp.controller;
    exports com.bicitienda.bikeserviceapp;
}