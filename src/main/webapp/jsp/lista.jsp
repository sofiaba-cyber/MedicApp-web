<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.medicapp.modelo.Medicamento" %>

<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <title>MediApp - Lista de medicamentos</title>
</head>

<body>

    <h1>Lista de medicamentos</h1>

    <table border="1">

        <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Descripción</th>
            <th>Precio</th>
            <th>Stock</th>
            <th>Categoría</th>
        </tr>

        <%
            List<Medicamento> medicamentos =
                    (List<Medicamento>) request.getAttribute("medicamentos");

            if (medicamentos != null && !medicamentos.isEmpty()) {

                for (Medicamento medicamento : medicamentos) {
        %>

        <tr>
            <td><%= medicamento.getIdMEDICAMENTO() %></td>
            <td><%= medicamento.getNombre() %></td>
            <td><%= medicamento.getDescripcion() %></td>
            <td>$<%= medicamento.getPrecio() %></td>
            <td><%= medicamento.getStock() %></td>
            <td><%= medicamento.getCATEGORIA_idCATEGORIA() %></td>
        </tr>

        <%
                }

            } else {
        %>

        <tr>
            <td colspan="6">No hay medicamentos registrados.</td>
        </tr>

        <%
            }
        %>

    </table>

    <br>

    <a href="../formulario-medicamento.html">
        Registrar medicamento
    </a>

</body>

</html>