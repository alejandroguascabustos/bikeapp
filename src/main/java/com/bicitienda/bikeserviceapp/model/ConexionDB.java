package com.bicitienda.bikeserviceapp.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {

    private static final String URL = "jdbc:mysql://localhost:3306/bikeservice";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Método alias usado por algunos controladores: mantiene compatibilidad con código existente
    public static Connection getConnection() throws SQLException {
        return conectar();
    }
}
