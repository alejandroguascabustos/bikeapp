package com.bicitienda.bikeserviceapp.controller;

import com.bicitienda.bikeserviceapp.model.ConexionDB;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class ServiciosController {

    @FXML
    private Label lblTitulo;
    @FXML
    private TextField txtCliente;
    @FXML
    private ComboBox<String> cmbTipoServicio;
    @FXML
    private TextArea txtDescripcion;
    @FXML
    private Label lblMensaje;

    private String usuarioLogueado;
    private String rolUsuario;

    @FXML
    public void initialize() {
        cmbTipoServicio.getItems().addAll("venta", "mantenimiento");
    }

    // Este método lo llamaremos desde el LoginController
    public void setDatosUsuario(String usuario, String rol) {
        this.usuarioLogueado = usuario;
        this.rolUsuario = rol;
        lblTitulo.setText("Registro de Servicios - " + rol.toUpperCase());
    }

    @FXML
    protected void guardarServicio() {
        String cliente = txtCliente.getText();
        String tipo = cmbTipoServicio.getValue();
        String descripcion = txtDescripcion.getText();

        if (cliente.isEmpty() || tipo == null || descripcion.isEmpty()) {
            lblMensaje.setText("⚠️ Todos los campos son obligatorios.");
            lblMensaje.setStyle("-fx-text-fill: red;");
            return;
        }

        try (Connection conn = ConexionDB.conectar()) {
            String sql = "INSERT INTO servicios (cliente, tipo_servicio, descripcion, mecanico) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, cliente);
            stmt.setString(2, tipo);
            stmt.setString(3, descripcion);
            stmt.setString(4, usuarioLogueado);
            stmt.executeUpdate();

            lblMensaje.setText("✅ Servicio registrado correctamente");
            lblMensaje.setStyle("-fx-text-fill: green;");
            txtCliente.clear();
            txtDescripcion.clear();
            cmbTipoServicio.setValue(null);

        } catch (Exception e) {
            e.printStackTrace();
            lblMensaje.setText("❌ Error al guardar el servicio");
            lblMensaje.setStyle("-fx-text-fill: red;");
        }
    }
}
