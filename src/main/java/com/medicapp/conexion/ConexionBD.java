package com.medicapp.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    private static final String URL = "jdbc:mysql://localhost:3306/mydb";
    private static final String USUARIO = "root";
    private static final String CONTRASENA = "";

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