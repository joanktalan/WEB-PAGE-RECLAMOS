<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Start Page</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        
        <h1>Ingrese ID que desea ver</h1>
        
        <form action="/verLogins" method="post">
            <input type="text" name="id" placeholder="Id de usuario">
            <br>
            <input type="submit" value="Enviar Datos">
        </form>
        
       
        <br>
        <a class="btn btn-warning btn-lg" href="${pageContext.request.contextPath}/">Volver al principio</a>
    </body>
</html>
