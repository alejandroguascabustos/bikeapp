package com.bicitienda.bikeserviceapp.controller;

import com.bicitienda.bikeserviceapp.model.ConexionDB;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginController {

    private static final Logger LOGGER = Logger.getLogger(LoginController.class.getName());

    @FXML
    private TextField txtUsuario;
    @FXML
    private PasswordField txtContrasena;
    @FXML
    private Label lblMensaje;

    @FXML
    protected void onLogin() {
        String usuario = txtUsuario.getText();
        String contrasena = txtContrasena.getText();

        if (usuario == null || usuario.isEmpty() || contrasena == null || contrasena.isEmpty()) {
            lblMensaje.setText("Por favor ingresa usuario y contraseña");
            return;
        }

        String sql = "SELECT rol FROM usuarios WHERE usuario = ? AND contrasena = ?";

        try (Connection conn = ConexionDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario);
            stmt.setString(2, contrasena);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String rol = rs.getString("rol");
                    abrirVentanaServicios(usuario, rol);

                    // Cierra la ventana de login
                    Stage stage = (Stage) txtUsuario.getScene().getWindow();
                    stage.close();

                } else {
                    lblMensaje.setText("Usuario o contraseña incorrectos");
                }
            }

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error al conectar o consultar la base de datos", e);
            lblMensaje.setText("Error de conexión con la base de datos");
        }
    }

    private void abrirVentanaServicios(String usuario, String rol) throws Exception {
        // Verificar que el recurso FXML existe antes de cargar
        URL fxmlUrl = getClass().getResource("/com/bicitienda/bikeserviceapp/servicio-view.fxml");
        if (fxmlUrl == null) {
            LOGGER.severe("FXML de servicios no encontrado en /com/bicitienda/bikeserviceapp/servicio-view.fxml");
            throw new IllegalStateException("Vista de servicios no encontrada");
        }
        FXMLLoader loader = new FXMLLoader(fxmlUrl);
        Scene scene = new Scene(loader.load());

        ServiciosController controller = loader.getController();
        controller.setDatosUsuario(usuario, rol);

        Stage stage = new Stage();
        stage.setTitle("BikeService - Panel de " + rol);
        stage.setScene(scene);
        stage.show();
    }




}
