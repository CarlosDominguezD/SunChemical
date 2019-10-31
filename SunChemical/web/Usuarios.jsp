<%-- 
    Document   : Usuarios
    Created on : 04-jul-2019, 19:50:06
    Author     : Carlos A Dominguez D
--%>

<%@page import="java.util.LinkedList"%>
<%@page import="Modelo.ModeloUsuario"%>
<%@page import="Controlador.ControladorUsuarios"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>        
        <%@include file="Principal/Head.html" %>       
        <script type="text/javascript" src="Principal/js/jquery.min.js" ></script>
        <script type="text/javascript" src="Principal/js/ValidacionesUsuarios.js" ></script>    
    </head>
    <body class="nav-md" onload="ListarUser()">
        <%@include file="Principal/Body.html" %>
        <div class="right_col" role="main">
            <div class="">            
                <div class="clearfix"></div>
                <div class="row">
                    <div class="x_panel">
                        <div class="x_title">
                            <h2>Usuarios</h2>
                            <div class="clearfix"></div>
                        </div>
                        <div class="x_content">
                            <br />                            
                            <form class="form-horizontal form-label-left input_mask" action="Usuarios" method="POST" name="UsuariosJSP" enctype="multipart/form-data" id="IdUsuariosJSP" >
                                <div>
                                    <input type="hidden" id="Id" name="Id" >                                    
                                </div>
                                <div>
                                    <input type="hidden" id="IdAccion" name="Accion" >                                    
                                </div>
                                <div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback">
                                    <input type="text" class="form-control has-feedback-left" id="IdCodigo" name="Codigo" placeholder="Codigo">
                                    <span class="fa fa-user form-control-feedback left" aria-hidden="true"></span>
                                </div>
                                <div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback">
                                    <input type="text" class="form-control" id="IdNombre" name="Nombre" placeholder="Nombre">
                                    <span class="fa fa-user form-control-feedback right" aria-hidden="true"></span>
                                </div>    
                                <div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback">
                                    <input type="text" class="form-control has-feedback-left" id="IdUsuario" name="Usuario" placeholder="Usuario">
                                    <span class="fa fa-user form-control-feedback left" aria-hidden="true"></span>
                                </div>
                                <div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback">
                                    <input type="password" class="form-control" id="IdPassword" name="Password" placeholder="Password">
                                    <span class="fa fa-user form-control-feedback right" aria-hidden="true"></span>
                                </div>
                                <br/>
                                <br/>
                                <br/>
                                <br/>
                                <br/>
                                <br/>
                                <div class="text-center">
                                    <div class="col-lg-12" style="text-align: center">
                                        <button type="button" class="btn btn-primary" id="IdNuevo" name="Nuevo">Nuevo</button>
                                        <button type="button" class="btn btn-success" id="IdGuardar" name="Guardar">Guardar</button>
                                        <button type="button" class="btn btn-primary" id="IdBuscar" name="Buscar">Buscar</button>
                                        <button type="button" class="btn btn-primary" id="IdCancelar" name="Cancelar">Cancelar</button>
                                    </div>                        
                                </div>                                
                                <br>
                                <br>
                                <br>
                                <br> 
                                <div class="x_panel">

                                    <table>                                  
                                        <thead>
                                            <tr>
                                                <th>Id</th>
                                                <th>Codigo</th>
                                                <th>Nombre</th> 
                                                <th>Usuario</th> 
                                                <th></th>
                                                <th></th>
                                            </tr>
                                        </thead>
                                        <!-- adobestock-->
                                        <%
                                            ControladorUsuarios controladorUsuarios = new ControladorUsuarios();
                                            LinkedList<ModeloUsuario> listUsuarios = controladorUsuarios.Read();
                                        %>
                                        <tbody>
                                            <%
                                                for (ModeloUsuario modeloUsuario : listUsuarios)
                                                {
                                            %>                                          
                                            <tr>
                                                <td WIDTH = "300" HEIGHT="0"><%=modeloUsuario.getId()%></td>                                     
                                                <td WIDTH = "300" HEIGHT="0"><%=modeloUsuario.getCodigo()%></td>                                     
                                                <td WIDTH = "800" HEIGHT="0"><%=modeloUsuario.getNombre()%></td>
                                                <td WIDTH = "300" HEIGHT="0"><%=modeloUsuario.getUsuario()%></td>                                     
                                                <td WIDTH = "10" HEIGHT="0" class="text-center">
                                                    <button class="btn btn-dark" type="button" id="IdModificar" name="Modificar">Modificar</button>                     
                                                </td>
                                                <td WIDTH = "10" HEIGHT="0" class="text-center">
                                                    <button class="btn btn-danger" type="button" id="IdEliminar" name="Eliminar">Eliminar</button>                    
                                                </td>
                                            </tr>                                            
                                                <%
                                                    }
                                                %>                                            
                                        </tbody>
                                    </table>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="Principal/Script.html" %>         
    </body>
    <%@include file="Principal/Validaciones.html" %>        
</html>