package com.bicitienda.bikeserviceapp.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.Parent;
import java.io.IOException;

public class DashboardController {

    @FXML
    private StackPane contenidoCentro;

    // 🔧 Abrir formulario de servicios
    @FXML
    private void abrirServicios() {
        cargarVista("/com/bicitienda/bikeserviceapp/servicio-view.fxml");
    }

    // 📜 Abrir historial
    @FXML
    private void abrirHistorial() {
        cargarVista("/com/bicitienda/bikeserviceapp/historial-view.fxml");
    }

    // 🚪 Cerrar sesión
    @FXML
    private void cerrarSesion() {
        contenidoCentro.getScene().getWindow().hide(); // cierra la ventana
    }

    // 🔄 Método genérico para cambiar la vista central
    private void cargarVista(String rutaFXML) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(rutaFXML));
            Parent vista = loader.load();
            contenidoCentro.getChildren().setAll(vista);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
