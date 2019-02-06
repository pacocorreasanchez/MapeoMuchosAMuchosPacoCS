<%-- 
    Document   : index
    Created on : 28 ene. 2019, 17:48:29
    Author     : paco
--%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="contexto" scope="application" value="${pageContext.request.contextPath}" />
<c:set var="estilos" scope="application" value="${contexto}/CSS/estilos.css" />
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="ROBOTS" content="NOARCHIVE"> 
        <meta name="generator" content="NetBeans"> 
        <meta name="referrer" content="always"> 
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" type="text/css" href="${estilos}"/>
        <title>Index</title>
    </head>
    <body>

        <div id="principal">
            <center>
                <h2>Elige una opción:</h2>

                <div id="menuPrincipal">
                    <li><a href="${contexto}/Eleccion?op=add" class="enlace">Insertar</a></li>
                    <hr>
                    <li><a href="${contexto}/Eleccion?op=listAutores" class="enlace">Listar autores</a></li>
                    <li><a href="${contexto}/Eleccion?op=listLibros" class="enlace">Listar libros</a></li>
                    <hr>
                    <li><a href="${contexto}/Eleccion?op=delete" class="enlace">Eliminar</a></li>
                    <hr>
                    <li><a href="${contexto}/Eleccion?op=updateAutor" class="enlace">Actualizar autor</a></li>
                    <li><a href="${contexto}/Eleccion?op=updateLibro" class="enlace">Actualizar libro</a></li>
                </div>
            </center>
        </div>

    </body>
</html>
