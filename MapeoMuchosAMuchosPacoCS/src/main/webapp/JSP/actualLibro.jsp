<%-- 
    Document   : actual
    Created on : 4 feb. 2019, 17:39:49
    Author     : paco
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
        <title>Actualiza el libro</title>
    </head>
    <body>
        <div id="principal">
            <h2>Elige uno para modificar</h2>
            <c:if test = "${listadoLibros == null || listadoLibros.size() < 0}">
                <h1>No hay registros en la tabla</h1>
            </c:if>


            <form method="post" action="Controlador?op=updateLibro">
                <table>
                    <c:forEach var="item" items="${listadoLibros}">
                        <tr>
                            <td><input type="radio" name="registro" value="${item.idLibro}" /></td>
                            <td>${item.titulo}</td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td><input type="submit" value="Actualizar" class="boton" /></td>
                    </tr>
                </table>
                <a href="${contexto}/index.jsp" class="enlace">Menú inicial</a>
            </form>

        </div>
    </body>
</html>
