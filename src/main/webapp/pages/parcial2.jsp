<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Start Page</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        
        <h1>Ingrese usuario y contraseña</h1>
        
        <form action="/reclamos" method="post">
            <input type="text" name="user" placeholder="Nombre de usuario">
            <br>
            <input type="text" name="contrasenia" placeholder="contrasenia">
            <input type="submit" value="Enviar Datos">
        </form>
        
        <br>
        
        
        <br>
         <a class="btn btn-warning btn-lg" href="${pageContext.request.contextPath}/">Volver al principio</a>
         
    </body>
</html>