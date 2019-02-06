<%-- 
    Document   : insertar
    Created on : 5 feb. 2019, 16:29:48
    Author     : paco
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="ROBOTS" content="NOARCHIVE"> 
        <meta name="generator" content="NetBeans"> 
        <meta name="referrer" content="always"> 
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" type="text/css" href="${estilos}"/>
        <title>Insertar</title>
    </head>
    <body>
        <div id="principal">
            <center>
                <h2>Insertar  datos</h2>
                <form method="post" action="Controlador?op=add">
                    <table>
                        <tr>
                            <td>Nombre del autor:</td>
                            <td><input type="text" name="nombre" /></td>
                        </tr>  
                        <tr>
                            <td>Título del libro 1:</td>
                            <td><input type="text" name="libro1" /></td>
                        </tr>
                        <tr>
                            <td>Título del libro 2:</td>
                            <td><input type="text" name="libro2" /></td>
                        </tr>
                        <tr>
                            <td>Título del libro 3:</td>
                            <td><input type="text" name="libro3" /></td>
                        </tr>
                        <tr>
                            <td><input type="submit" name="enviar" value="Enviar" class="boton" /></td>
                        </tr>
                    </table>
                    <a href="${contexto}/index.jsp" class="enlace">Menú inicial</a>
                </form>
            </center>
        </div>
    </body>
</html>
