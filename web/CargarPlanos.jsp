<%-- 
    Document   : CargarPlanos
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
        <script type="text/javascript" src="Principal/js/jquery.min.js" ></script>
        <script type="text/javascript" src="Principal/js/ValidacionesPlano.js" ></script> 
        <div class="right_col" role="main">
            <div class="">            
                <div class="clearfix"></div>
                <div class="row">
                    <div class="x_panel">
                        <div class="x_title">
                            <h2>Carga Planos</h2>
                            <div class="clearfix"></div>
                        </div>
                        <div class="x_content">
                            <br />
                            <form action="CargarPlanoServlet" method="POST" enctype="multipart/form-data" id="IdCargarPlanoServlet">
                                <div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback">
                                    <label class="control-label col-md-3 col-sm-1 ">Formatos a Cargar</label>
                                    <div class="col-md-9 col-sm-12 col-xs-12">
                                        <select class="form-control" id="IdFormato" name="Formato">
                                            <!--<option>Personas</option>
                                            <option>Poliza</option>-->
                                            <option>Cobros</option>                                            
                                            <option>Deduccion</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback">
                                    <input type="file" name="archivo" id="Idarchivo" accept="aplication/txt" autofocus class="btn btn-lg btn-primary">
                                </div>
                                <br>
                                <br>
                                <br>
                                <br>
                                <br>
                                <br>
                                <div align="center" id="botonCargar">
                                    <input type="submit" value="Cargar" class="btn btn-lg btn-primary" onclick = "enableGif()">       
                                </div>
                                <div align="center" id="espera" style="display: none">
                                    <img src="Principal/images/loading.gif">
                                </div>
                            </form>
                            <script type="text/javascript">
                                function enableGif()
                                {
                                    window.onload = document.getElementById("espera").style = "display: block";
                                    window.onload = document.getElementById("botonCargar").style = "display: none"
                                    window.onload = document.getElementById("file").style = "display: none"
                                    window.onload = document.getElementById("tituloh3").style = "display: none"
                                }
                            </script>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="Principal/Script.html" %>
        <%
        } else
        {
        %> 
        <br>
        <h1  class="text-center">El Usuario no ha iniciado sesion</h1>
        <%@include file="index.jsp" %>
        <%
            }
        %>  
    </body>
    <%@include file="Principal/Validaciones.html" %>        
</html>