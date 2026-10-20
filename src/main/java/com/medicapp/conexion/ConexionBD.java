/*
 * MediApp - Proyecto web
 *
 * Clase encargada de establecer la conexión entre la aplicación
 * y la base de datos MySQL utilizada por MediApp.
 */
package com.medicapp.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Gestiona la conexión de MediApp con la base de datos MySQL.
 *
 * Utiliza JDBC para establecer la comunicación con el servidor de base de
 * datos.
 */
public class ConexionBD {

    private static final String URL = "jdbc:mysql://localhost:3306/mydb";
    private static final String USUARIO = "root";
    private static final String CONTRASENA = "";

    /**
     * Establece y retorna una conexión con la base de datos.
     *
     * @return conexión activa con la base de datos, o null si ocurre un error.
     */
    public static Connection conectar() {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conexion = DriverManager.getConnection(
                    URL,
                    USUARIO,
                    CONTRASENA
            );

            System.out.println("Conexion exitosa con la base de datos MediApp.");

            return conexion;

        } catch (ClassNotFoundException e) {

            System.out.println("ERROR: No se encontro el controlador JDBC de MySQL.");
            e.printStackTrace();

        } catch (SQLException e) {

            System.out.println("ERROR al conectar con MySQL.");
            e.printStackTrace();
        }

        return null;
    }
}
