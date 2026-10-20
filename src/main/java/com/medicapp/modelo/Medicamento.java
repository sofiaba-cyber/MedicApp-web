/*
 * MediApp - Proyecto web
 *
 * Clase que representa la información de un medicamento
 * dentro del sistema MediApp.
 */
package com.medicapp.modelo;

/**
 * Modelo que representa un medicamento registrado en MediApp.
 *
 * Contiene los atributos necesarios para almacenar y consultar la información
 * de los medicamentos.
 */
public class Medicamento {

    private int idMEDICAMENTO;
    private String Nombre;
    private String descripcion;
    private double precio;
    private int stock;
    private int CATEGORIA_idCATEGORIA;

    /**
     * Constructor vacío de la clase Medicamento.
     */
    public Medicamento() {
    }

    /**
     * Crea un objeto Medicamento con la información obtenida desde la base de
     * datos.
     *
     * @param idMEDICAMENTO identificador del medicamento.
     * @param Nombre nombre del medicamento.
     * @param descripcion descripción del medicamento.
     * @param precio precio del medicamento.
     * @param stock cantidad disponible.
     * @param CATEGORIA_idCATEGORIA identificador de la categoría.
     */
    public Medicamento(int idMEDICAMENTO, String Nombre, String descripcion,
            double precio, int stock, int CATEGORIA_idCATEGORIA) {

        this.idMEDICAMENTO = idMEDICAMENTO;
        this.Nombre = Nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
        this.CATEGORIA_idCATEGORIA = CATEGORIA_idCATEGORIA;
    }

    /**
     * Obtiene el identificador del medicamento.
     *
     * @return identificador del medicamento.
     */
    public int getIdMEDICAMENTO() {
        return idMEDICAMENTO;
    }

    /**
     * Obtiene el nombre del medicamento.
     *
     * @return nombre del medicamento.
     */
    public String getNombre() {
        return Nombre;
    }

    /**
     * Obtiene la descripción del medicamento.
     *
     * @return descripción del medicamento.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Obtiene el precio del medicamento.
     *
     * @return precio del medicamento.
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * Obtiene la cantidad disponible del medicamento.
     *
     * @return cantidad disponible.
     */
    public int getStock() {
        return stock;
    }

    /**
     * Obtiene el identificador de la categoría.
     *
     * @return identificador de la categoría.
     */
    public int getCATEGORIA_idCATEGORIA() {
        return CATEGORIA_idCATEGORIA;
    }
}
