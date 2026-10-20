package com.medicapp.modelo;

public class Medicamento {

    private int idMEDICAMENTO;
    private String Nombre;
    private String descripcion;
    private double precio;
    private int stock;
    private int CATEGORIA_idCATEGORIA;

    public Medicamento() {
    }

    public Medicamento(int idMEDICAMENTO, String Nombre, String descripcion,
            double precio, int stock, int CATEGORIA_idCATEGORIA) {

        this.idMEDICAMENTO = idMEDICAMENTO;
        this.Nombre = Nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
        this.CATEGORIA_idCATEGORIA = CATEGORIA_idCATEGORIA;
    }

    public int getIdMEDICAMENTO() {
        return idMEDICAMENTO;
    }

    public String getNombre() {
        return Nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public int getStock() {
        return stock;
    }

    public int getCATEGORIA_idCATEGORIA() {
        return CATEGORIA_idCATEGORIA;
    }
}