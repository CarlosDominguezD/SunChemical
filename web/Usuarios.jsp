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
    <%
        ModeloUsuario modUsuario = new ModeloUsuario();
        modUsuario = (ModeloUsuario) request.getAttribute("modeloUsuario");
    %>
    <head>        
        <%@include file="Principal/Head.html" %>       
        <script type="text/javascript" src="Principal/js/jquery.min.js" ></script>
        <script type="text/javascript" src="Principal/js/ValidacionesUsuarios.js" ></script>    
    </head>
    <body class="nav-md" onload="ListarUser()">
        <%
            ModeloUsuario modeloUsuarios = (ModeloUsuario) request.getSession().getAttribute("user");
            //String nombre = modeloUsuarios.getNombre().toString();
            if (modeloUsuarios != null)
            {
        %> 
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
                                <%
                                    if (modUsuario != null)
                                    {
                                %>
                                <div>
                                    <input type="hidden" id="Id" name="Id" value="<%=modUsuario.getId()%>">                                    
                                </div>
                                <div>
                                    <input type="hidden" id="IdAccion" name="Accion" >                                    
                                </div>                                
                                <div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback">
                                    <input type="text" class="form-control has-feedback-left" id="IdCodigo" name="Codigo" placeholder="Codigo" value="<%=modUsuario.getCodigo()%>">
                                    <span class="fa fa-user form-control-feedback left" aria-hidden="true"></span>
                                </div>
                                <div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback">
                                    <input type="text" class="form-control" id="IdNombre" name="Nombre" placeholder="Nombre" value="<%=modUsuario.getNombre()%>">
                                    <span class="fa fa-user form-control-feedback right" aria-hidden="true"></span>
                                </div>    
                                <div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback">
                                    <input type="text" class="form-control has-feedback-left" id="IdUsuario" name="Usuario" placeholder="Usuario" value="<%=modUsuario.getUsuario()%>">
                                    <span class="fa fa-user form-control-feedback left" aria-hidden="true"></span>
                                </div>
                                <div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback">
                                    <input type="password" class="form-control" id="IdPassword" name="Password" placeholder="Password" value="<%=modUsuario.getPassword()%>">
                                    <span class="fa fa-user form-control-feedback right" aria-hidden="true"></span>
                                </div>
                                <%
                                } else
                                {
                                %>
                                <div>
                                    <input type="hidden" id="Id" name="Id" value="">                                    
                                </div>
                                <div>
                                    <input type="hidden" id="IdAccion" name="Accion" >                                    
                                </div> 
                                <div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback">
                                    <input type="text" class="form-control has-feedback-left" id="IdCodigo" name="Codigo" placeholder="Codigo" value="">
                                    <span class="fa fa-user form-control-feedback left" aria-hidden="true"></span>
                                </div>
                                <div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback">
                                    <input type="text" class="form-control" id="IdNombre" name="Nombre" placeholder="Nombre" value="">
                                    <span class="fa fa-user form-control-feedback right" aria-hidden="true"></span>
                                </div>    
                                <div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback">
                                    <input type="text" class="form-control has-feedback-left" id="IdUsuario" name="Usuario" placeholder="Usuario" value="">
                                    <span class="fa fa-user form-control-feedback left" aria-hidden="true"></span>
                                </div>
                                <div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback">
                                    <input type="password" class="form-control" id="IdPassword" name="Password" placeholder="Password" value="">
                                    <span class="fa fa-user form-control-feedback right" aria-hidden="true"></span>
                                </div>
                                <%
                                    }
                                %>
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
                                        <!--<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal" data-whatever="">Consultar</button> -->                                       
                                        <button type="button" class="btn btn-danger" id="IdEliminar" name="Eliminar">Eliminar</button>                    
                                        <button type="button" class="btn btn-primary" id="IdCancelar" name="Cancelar">Cancelar</button>
                                    </div>                        
                                </div>                                
                                <br>
                                <br>
                                <br>
                                <br> 
                                <div class="x_panel">
                                    <table id="datatable" class="table table-striped table-bordered">                                  
                                        <thead>
                                            <%
                                                ControladorUsuarios controladorUsuarios = new ControladorUsuarios();
                                                LinkedList<ModeloUsuario> listUsuarios = controladorUsuarios.Read();
                                                if (listUsuarios.size() > 0)
                                                {
                                            %>
                                            <tr>
                                                <th>Id</th>
                                                <th>Codigo</th>
                                                <th>Nombre</th> 
                                                <th>Usuario</th> 
                                                <th></th>
                                            </tr>
                                        </thead>                             
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
                                                    <button class="massshow-modal btn btn-dark btn-xs"  
                                                            data-id="<%=modeloUsuario.getId()%>"
                                                            data-codigo="<%=modeloUsuario.getCodigo()%>"
                                                            data-nombre="<%=modeloUsuario.getNombre()%>"
                                                            data-usuario="<%=modeloUsuario.getUsuario()%>"
                                                            data-password="<%=modeloUsuario.getPassword()%>"
                                                            type="button" id="IdModificar" name="Modificar">Seleccionar</button>                     
                                                </td>
                                            </tr>                                          
                                            <%
                                                    }
                                                }
                                            %>                                            
                                        </tbody>
                                    </table>
                                </div>
                                <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                    <div class="modal-dialog" role="document">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title" id="exampleModalLabel">Buscar Usuario</h5>
                                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                </button>
                                            </div>
                                            <div class="modal-body">
                                                <form>
                                                    <div class="form-group">
                                                        <label for="recipient-name" class="col-form-label">Codigo usuario</label>
                                                        <input type="text" class="form-control" name="CodigoBuscar" id="IdCodigoBuscar">
                                                    </div>                                                    
                                                </form>
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                                <button type="button" class="btn btn-primary" id="IdBuscar" name="Buscar">Buscar</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </form>
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
        <h1  class="text-center">El Usuario no ha iniciado seccion</h1>
        <%@include file="index.jsp" %>
        <%
            }
        %>  
    </body>
    <%@include file="Principal/Validaciones.html" %>        
</html>