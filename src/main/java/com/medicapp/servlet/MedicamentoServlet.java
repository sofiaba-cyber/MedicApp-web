/*
 * MediApp - Proyecto web
 *
 * Framework seleccionado: Java Web con Maven y Apache Tomcat.
 * Se seleccionó esta tecnología porque MediApp es una aplicación web
 * que permite gestionar medicamentos y consultar información desde
 * un navegador. Maven facilita la administración de dependencias
 * y Apache Tomcat permite ejecutar los Servlets y las páginas JSP.
 */
package com.medicapp.servlet;

import com.medicapp.conexion.ConexionBD;
import com.medicapp.modelo.Medicamento;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/medicamentos")
/**
 * Controlador principal para gestionar el registro y consulta de medicamentos
 * en MediApp.
 *
 * Recibe las solicitudes del usuario, procesa los datos, se comunica con la
 * base de datos y envía la información a las páginas JSP correspondientes.
 */
public class MedicamentoServlet extends HttpServlet {

    /**
     * Consulta los medicamentos registrados en la base de datos y envía la
     * información a la página lista.jsp.
     *
     * @param request solicitud enviada por el usuario.
     * @param response respuesta enviada al navegador.
     * @throws ServletException si ocurre un error en el Servlet.
     * @throws IOException si ocurre un error de entrada o salida.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        if ("eliminar".equals(accion)) {

    String id = request.getParameter("id");

    String sqlEliminar =
            "DELETE FROM MEDICAMENTO WHERE idMEDICAMENTO = ?";

    try {

        Connection conexion = ConexionBD.conectar();

        PreparedStatement statement =
                conexion.prepareStatement(sqlEliminar);

        statement.setInt(1, Integer.parseInt(id));

        statement.executeUpdate();

        statement.close();
        conexion.close();

        response.sendRedirect("medicamentos");

        return;

    } catch (SQLException | NumberFormatException e) {

        request.setAttribute("error", e.getMessage());

        request.getRequestDispatcher("/jsp/error.jsp")
                .forward(request, response);

        return;
    }
}
if ("editar".equals(accion)) {

    String id = request.getParameter("id");

    String sqlEditar = "SELECT idMEDICAMENTO, Nombre, descripcion, precio, "
            + "stock, CATEGORIA_idCATEGORIA "
            + "FROM MEDICAMENTO WHERE idMEDICAMENTO = ?";

    try {

        Connection conexion = ConexionBD.conectar();

        PreparedStatement statement = conexion.prepareStatement(sqlEditar);

        statement.setInt(1, Integer.parseInt(id));

        ResultSet resultado = statement.executeQuery();

        if (resultado.next()) {

            Medicamento medicamento = new Medicamento(
                    resultado.getInt("idMEDICAMENTO"),
                    resultado.getString("Nombre"),
                    resultado.getString("descripcion"),
                    resultado.getDouble("precio"),
                    resultado.getInt("stock"),
                    resultado.getInt("CATEGORIA_idCATEGORIA")
            );

            request.setAttribute("medicamento", medicamento);

            resultado.close();
            statement.close();
            conexion.close();

            request.getRequestDispatcher("/jsp/editar-medicamento.jsp")
                    .forward(request, response);

            return;
        }

        resultado.close();
        statement.close();
        conexion.close();

    } catch (SQLException | NumberFormatException e) {

        request.setAttribute("error", e.getMessage());

        request.getRequestDispatcher("/jsp/error.jsp")
                .forward(request, response);

        return;
    }
}
        String sql = "SELECT idMEDICAMENTO, Nombre, descripcion, precio, stock, "
                + "CATEGORIA_idCATEGORIA FROM MEDICAMENTO";

        List<Medicamento> medicamentos = new ArrayList<>();

        try {

            Connection conexion = ConexionBD.conectar();

            PreparedStatement statement = conexion.prepareStatement(sql);

            ResultSet resultado = statement.executeQuery();

            while (resultado.next()) {

                Medicamento medicamento = new Medicamento(
                        resultado.getInt("idMEDICAMENTO"),
                        resultado.getString("Nombre"),
                        resultado.getString("descripcion"),
                        resultado.getDouble("precio"),
                        resultado.getInt("stock"),
                        resultado.getInt("CATEGORIA_idCATEGORIA")
                );

                medicamentos.add(medicamento);
            }

            resultado.close();
            statement.close();
            conexion.close();

            request.setAttribute("medicamentos", medicamentos);

            request.getRequestDispatcher("/jsp/lista.jsp")
                    .forward(request, response);

        } catch (SQLException e) {

            request.setAttribute("error", e.getMessage());

            request.getRequestDispatcher("/jsp/error.jsp")
                    .forward(request, response);
        }
    }

    /**
     * Recibe los datos del formulario, registra el medicamento en la base de
     * datos y muestra la página de confirmación.
     *
     * @param request datos enviados desde el formulario.
     * @param response respuesta enviada al navegador.
     * @throws ServletException si ocurre un error en el Servlet.
     * @throws IOException si ocurre un error de entrada o salida.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Recibir datos del formulario
        String accion = request.getParameter("accion");

if ("actualizar".equals(accion)) {

    String id = request.getParameter("id");
    String nombre = request.getParameter("nombre");
    String descripcion = request.getParameter("descripcion");
    String precio = request.getParameter("precio");
    String stock = request.getParameter("stock");
    String categoria = request.getParameter("categoria");
    
    // Validar que el precio sea mayor que cero.
if (Double.parseDouble(precio) <= 0) {

    request.setAttribute("error",
            "El precio debe ser mayor que cero.");

    request.getRequestDispatcher("/jsp/error.jsp")
            .forward(request, response);

    return;
}

// Validar que el stock no sea negativo.
if (Integer.parseInt(stock) < 0) {

    request.setAttribute("error",
            "El stock no puede ser negativo.");

    request.getRequestDispatcher("/jsp/error.jsp")
            .forward(request, response);

    return;
}

    String sqlActualizar = "UPDATE MEDICAMENTO SET "
            + "Nombre = ?, descripcion = ?, precio = ?, stock = ?, "
            + "CATEGORIA_idCATEGORIA = ? "
            + "WHERE idMEDICAMENTO = ?";

    try {

        Connection conexion = ConexionBD.conectar();

        PreparedStatement statement =
                conexion.prepareStatement(sqlActualizar);

        statement.setString(1, nombre);
        statement.setString(2, descripcion);
        statement.setDouble(3, Double.parseDouble(precio));
        statement.setInt(4, Integer.parseInt(stock));
        statement.setInt(5, Integer.parseInt(categoria));
        statement.setInt(6, Integer.parseInt(id));

        statement.executeUpdate();

        statement.close();
        conexion.close();

        response.sendRedirect("medicamentos");

        return;

    } catch (SQLException | NumberFormatException e) {

        request.setAttribute("error", e.getMessage());

        request.getRequestDispatcher("/jsp/error.jsp")
                .forward(request, response);

        return;
    }
}
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");
        String precio = request.getParameter("precio");
        String stock = request.getParameter("stock");
        String categoria = request.getParameter("categoria");
        
        // Validar la longitud máxima del nombre.
if (nombre.length() > 100) {

    request.setAttribute("error",
            "El nombre del medicamento no puede superar los 100 caracteres.");

    request.getRequestDispatcher("/jsp/error.jsp")
            .forward(request, response);

    return;
}

// Validar la longitud máxima de la descripción.
if (descripcion != null && descripcion.length() > 255) {

    request.setAttribute("error",
            "La descripción no puede superar los 255 caracteres.");

    request.getRequestDispatcher("/jsp/error.jsp")
            .forward(request, response);

    return;
}
// Validar que el precio sea mayor que cero.
if (Double.parseDouble(precio) <= 0) {

    request.setAttribute("error",
            "El precio debe ser mayor que cero.");

    request.getRequestDispatcher("/jsp/error.jsp")
            .forward(request, response);

    return;
}

// Validar que el stock no sea negativo.
if (Integer.parseInt(stock) < 0) {

    request.setAttribute("error",
            "El stock no puede ser negativo.");

    request.getRequestDispatcher("/jsp/error.jsp")
            .forward(request, response);

    return;
}
        String sql = "INSERT INTO MEDICAMENTO "
                + "(Nombre, descripcion, precio, stock, CATEGORIA_idCATEGORIA) "
                + "VALUES (?, ?, ?, ?, ?)";

        response.setContentType("text/html;charset=UTF-8");


        try {

            Connection conexion = ConexionBD.conectar();

            PreparedStatement statement = conexion.prepareStatement(sql);

            statement.setString(1, nombre);
            statement.setString(2, descripcion);
            statement.setDouble(3, Double.parseDouble(precio));
            statement.setInt(4, Integer.parseInt(stock));
            statement.setInt(5, Integer.parseInt(categoria));

            statement.executeUpdate();

            statement.close();
            conexion.close();

            // Enviar los datos al JSP de confirmación
            request.setAttribute("nombre", nombre);
            request.setAttribute("descripcion", descripcion);
            request.setAttribute("precio", precio);
            request.setAttribute("stock", stock);
            request.setAttribute("categoria", categoria);

// Mostrar la página JSP
            request.getRequestDispatcher("/jsp/confirmacion.jsp")
                    .forward(request, response);

        } catch (SQLException | NumberFormatException e) {

            // Enviar el mensaje de error al JSP
            request.setAttribute("error", e.getMessage());

            // Mostrar la página de error
            request.getRequestDispatcher("/jsp/error.jsp")
                    .forward(request, response);
        }
    }
}
