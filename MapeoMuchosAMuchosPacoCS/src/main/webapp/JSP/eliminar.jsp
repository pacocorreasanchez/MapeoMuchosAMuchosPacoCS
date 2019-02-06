<%-- 
    Document   : eliminar
    Created on : 5 feb. 2019, 17:10:57
    Author     : paco
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="ROBOTS" content="NOARCHIVE"> 
        <meta name="generator" content="NetBeans"> 
        <meta name="referrer" content="always"> 
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Borrado</title>
        <link rel="stylesheet" type="text/css" href="${estilos}" />
    </head>
    <body>

        <div id="principal">
            <h2>Elige uno para eliminar</h2>



            <form method="post" action="Controlador?op=delete">
                <table>
                    <c:if test = "${listadoAutores == null || listadoAutores.size() < 0}">
                        <h1>No hay registros en la tabla</h1>
                    </c:if>
                    <c:forEach var="item" items="${listadoAutores}">
                        <tr>
                            <td><input type="radio" name="registro" value="${item.idAutor}" /></td>
                            <td>${item.nombre}</td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td><input type="submit" value="Eliminar" class="boton" /></td>
                    </tr>
                </table>
                <a href="${contexto}/index.jsp" class="enlace">Menú inicial</a>
            </form>

        </div>


    </body>
</html>
