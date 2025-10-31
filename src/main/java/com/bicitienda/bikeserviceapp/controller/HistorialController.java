package com.bicitienda.bikeserviceapp.controller;

import com.bicitienda.bikeserviceapp.model.ConexionDB;
import com.bicitienda.bikeserviceapp.model.Servicio;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.sql.*;

public class HistorialController {

    @FXML private TableView<Servicio> tablaServicios;
    @FXML private TableColumn<Servicio, String> colCliente;
    @FXML private TableColumn<Servicio, String> colTipo;
    @FXML private TableColumn<Servicio, String> colFecha;
    @FXML private TableColumn<Servicio, Double> colCosto;

    private ObservableList<Servicio> listaServicios = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        colCliente.setCellValueFactory(data -> data.getValue().clienteProperty());
        colTipo.setCellValueFactory(data -> data.getValue().tipoProperty());
        colFecha.setCellValueFactory(data -> data.getValue().fechaProperty());
        colCosto.setCellValueFactory(data -> data.getValue().costoProperty().asObject());

        cargarServicios();
    }

    @FXML
    private void onActualizar() {
        cargarServicios();
    }

    @FXML
    private void onVolver() {
        // Aquí podrías volver al menú principal o cerrar la ventana
        tablaServicios.getScene().getWindow().hide();
    }

    private void cargarServicios() {
        listaServicios.clear();

        try (Connection conn = ConexionDB.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT cliente, tipo_servicio, fecha, costo FROM servicios")) {

            while (rs.next()) {
                listaServicios.add(new Servicio(
                        rs.getString("cliente"),
                        rs.getString("tipo_servicio"),
                        rs.getString("fecha"),
                        rs.getDouble("costo")
                ));
            }

            tablaServicios.setItems(listaServicios);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
