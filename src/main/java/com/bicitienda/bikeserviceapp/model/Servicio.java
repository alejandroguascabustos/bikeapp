package com.bicitienda.bikeserviceapp.model;

import java.sql.Timestamp;

public class Servicio {
    private int id;
    private String cliente;
    private String tipoServicio;
    private double costo;
    private String descripcion;
    private Timestamp fechaRegistro;

    public Servicio(int id, String cliente, String tipoServicio, double costo, String descripcion, Timestamp fechaRegistro) {
        this.id = id;
        this.cliente = cliente;
        this.tipoServicio = tipoServicio;
        this.costo = costo;
        this.descripcion = descripcion;
        this.fechaRegistro = fechaRegistro;
    }

    public int getId() { return id; }
    public String getCliente() { return cliente; }
    public String getTipoServicio() { return tipoServicio; }
    public double getCosto() { return costo; }
    public String getDescripcion() { return descripcion; }
    public Timestamp getFechaRegistro() { return fechaRegistro; }
}
