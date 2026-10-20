<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <title>MediApp - Confirmación</title>
</head>

<body>

    <h1>Medicamento registrado correctamente</h1>

    <p><strong>Nombre:</strong> ${nombre}</p>

    <p><strong>Descripción:</strong> ${descripcion}</p>

    <p><strong>Precio:</strong> $${precio}</p>

    <p><strong>Stock:</strong> ${stock}</p>

    <p><strong>Categoría:</strong> ${categoria}</p>

    <br>

    <a href="../formulario-medicamento.html">
        Registrar otro medicamento
    </a>

    <br><br>

    <a href="../medicamentos">
        Ver lista de medicamentos
    </a>

</body>

</html>
