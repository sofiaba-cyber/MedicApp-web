<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <title>MediApp - Editar medicamento</title>
</head>

<body>

    <h1>Editar medicamento</h1>

    <form action="../medicamentos" method="post">

        <input type="hidden" name="accion" value="actualizar">

        <input type="hidden" name="id" value="${medicamento.idMEDICAMENTO}">

        <label for="nombre">Nombre del medicamento:</label>
        <br>
        <input type="text"
               id="nombre"
               name="nombre"
               value="${medicamento.nombre}"
               required>

        <br><br>

        <label for="descripcion">Descripción:</label>
        <br>
        <textarea id="descripcion"
                  name="descripcion">${medicamento.descripcion}</textarea>

        <br><br>

        <label for="precio">Precio:</label>
        <br>
        <input type="number"
               id="precio"
               name="precio"
               step="0.01"
               value="${medicamento.precio}"
               required>

        <br><br>

        <label for="stock">Stock:</label>
        <br>
        <input type="number"
               id="stock"
               name="stock"
               value="${medicamento.stock}"
               required>

        <br><br>

        <label for="categoria">ID de categoría:</label>
        <br>
        <input type="number"
               id="categoria"
               name="categoria"
               value="${medicamento.CATEGORIA_idCATEGORIA}"
               required>

        <br><br>

        <button type="submit">
            Actualizar medicamento
        </button>

    </form>

    <br>

    <a href="../medicamentos">
        Volver a la lista
    </a>

</body>

</html>