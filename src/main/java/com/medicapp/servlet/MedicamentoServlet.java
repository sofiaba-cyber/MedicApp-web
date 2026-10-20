package com.medicapp.servlet;

import com.medicapp.conexion.ConexionBD;
import com.medicapp.modelo.Medicamento;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/medicamentos")
public class MedicamentoServlet extends HttpServlet {

    @Override
protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Recibir datos del formulario
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");
        String precio = request.getParameter("precio");
        String stock = request.getParameter("stock");
        String categoria = request.getParameter("categoria");

        String sql = "INSERT INTO MEDICAMENTO "
                + "(Nombre, descripcion, precio, stock, CATEGORIA_idCATEGORIA) "
                + "VALUES (?, ?, ?, ?, ?)";

        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();

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