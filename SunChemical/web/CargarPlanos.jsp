<%-- 
    Document   : CargarPlanos
    Created on : 04-jul-2019, 19:50:06
    Author     : Carlos A Dominguez D
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>        
        <%@include file="Principal/Head.html" %>        
    </head>
    <body class="nav-md">
        <%@include file="Principal/Body.html" %>
        <div class="right_col" role="main">
            <div class="">            
                <div class="clearfix"></div>
                <div class="row">
                    <div class="x_panel">
                        <div class="x_title">
                            <h2>Planos</h2>
                            <div class="clearfix"></div>
                        </div>
                        <div class="x_content">
                            <br />
                            <form class="form-horizontal form-label-left input_mask">            <div id="tituloh3">
                                    <h3 class="form-signin-heading" align="center"> Favor de selecionar un archivo </h3>
                                </div>            
                                <div align="center" id="file">
                                    <input type="file" name="archivo" accept="aplication/txt" autofocus class="btn btn-lg btn-primary">
                                </div>   
                                <div>
                                    <h1>                    
                                    </h1>
                                </div>
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
    </body>
    <%@include file="Principal/Validaciones.html" %>        
</html>