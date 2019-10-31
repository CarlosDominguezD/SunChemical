<%-- 
    Document   : Dashboard
    Created on : 04-jul-2019, 19:50:06
    Author     : Carlos A Dominguez D
--%>

<%@page import="Modelo.ModeloUsuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>        
        <%@include file="Principal/Head.html" %>        
    </head>
    <body class="nav-md">
        <%
            ModeloUsuario modeloUsuarios = (ModeloUsuario) request.getSession().getAttribute("user");
            //String nombre = modeloUsuarios.getNombre().toString();
            if (modeloUsuarios != null)
            {
        %> 
        <%@include file="Principal/Body.html" %>
        <div class="right_col" role="main">           
        </div>
        <%@include file="Principal/Script.html" %>    
        <%
        } else
        {
        %> 
        <br>
        <h1  class="text-center">El Usuario no ha iniciado seccion</h1>
         <%@include file="index.jsp" %>
        <%
            }
        %>  
    </body>
    <%@include file="Principal/Validaciones.html" %>
</html>