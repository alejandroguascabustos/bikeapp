package com.bicitienda.bikeserviceapp.controller;

import com.bicitienda.bikeserviceapp.model.ConexionDB;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.sql.*;

public class LoginController {

    @FXML private TextField txtUsuario;
    @FXML private PasswordField txtContrasena;
    @FXML private Label lblMensaje;

    @FXML
    private void handleLogin() {
        String usuario = txtUsuario.getText();
        String contrasena = txtContrasena.getText();

        if (usuario.isEmpty() || contrasena.isEmpty()) {
            lblMensaje.setText("Por favor ingrese usuario y contraseña.");
            return;
        }

        try (Connection conn = ConexionDB.getConnection()) {
            String sql = "SELECT * FROM usuarios WHERE usuario = ? AND contrasena = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, usuario);
            stmt.setString(2, contrasena);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String rol = rs.getString("rol");
                lblMensaje.setText("✅ Bienvenido " + rol);

                // Aquí podrías abrir la vista principal según el rol
                // Ejemplo:
                // if (rol.equals("administrador")) { abrirMenuAdmin(); }

            } else {
                lblMensaje.setText("❌ Credenciales incorrectas.");
            }
        } catch (SQLException e) {
            lblMensaje.setText("Error de conexión.");
            e.printStackTrace();
        }
    }
}
