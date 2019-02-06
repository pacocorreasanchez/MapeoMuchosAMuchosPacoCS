<%-- 
    Document   : listarAutores
    Created on : 5 feb. 2019, 17:12:23
    Author     : paco
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="ROBOTS" content="NOARCHIVE"> 
        <meta name="generator" content="NetBeans"> 
        <meta name="referrer" content="always"> 
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" type="text/css" href="${estilos}"/>
        <title>Listado de autores</title>
    </head>
    <body>
        <div id="principal">
            <center>
                <div class="contenedor">
                    <h2 class="me">Listado de autores y sus libros</h2>
                </div>
            </center>
            <c:if test = "${listadoAutores == null || listadoAutores.size() < 0}">
                <h1>No hay registros en la tabla</h1>
            </c:if>
            <ul>
                <c:forEach var="autor" items="${listadoAutores}">
                    <div id="listadoAutores">
                        Autor: ${autor.nombre}<br>
                                Libros:<br>
                        <c:forEach var="libro" items="${autor.libros}">
                            - ${libro.titulo}<br>
                        </c:forEach>
                    </div>
                    <hr>
                </c:forEach>
            </ul>
            <br />
            <center>
                <p><a href="${contexto}/index.jsp" class="enlace">Menú inicial</a></p>
            </center>
        </div>
    </body>
</html>
