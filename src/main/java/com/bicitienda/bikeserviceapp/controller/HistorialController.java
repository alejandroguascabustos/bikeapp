package com.bicitienda.bikeserviceapp.controller;

import com.bicitienda.bikeserviceapp.model.ConexionDB;
import com.bicitienda.bikeserviceapp.model.Servicio;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class HistorialController {

    @FXML
    private TableView<Servicio> tablaServicios;

    @FXML
    private TableColumn<Servicio, Integer> colId;

    @FXML
    private TableColumn<Servicio, String> colCliente;

    @FXML
    private TableColumn<Servicio, String> colTipo;

    @FXML
    private TableColumn<Servicio, Double> colCosto;

    @FXML
    private TableColumn<Servicio, String> colDescripcion;

    @FXML
    private TableColumn<Servicio, String> colFecha;

    private ObservableList<Servicio> listaServicios = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCliente.setCellValueFactory(new PropertyValueFactory<>("cliente"));
        colTipo.setCellValueFactory(new PropertyValueFactory<>("tipoServicio"));
        colCosto.setCellValueFactory(new PropertyValueFactory<>("costo"));
        colDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fechaRegistro"));

        cargarDatos();
    }

    private void cargarDatos() {
        listaServicios.clear();

        try (Connection conn = ConexionDB.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM servicios ORDER BY fecha_registro DESC")) {

            while (rs.next()) {
                Servicio servicio = new Servicio(
                        rs.getInt("id"),
                        rs.getString("cliente"),
                        rs.getString("tipo_servicio"),
                        rs.getDouble("costo"),
                        rs.getString("descripcion"),
                        rs.getTimestamp("fecha_registro")
                );
                listaServicios.add(servicio);
            }

            tablaServicios.setItems(listaServicios);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
