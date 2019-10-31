<%-- 
    Document   : Personas
    Created on : 04-jul-2019, 19:50:06
    Author     : Carlos A Dominguez D
--%>

<%@page import="Modelo.ModeloUsuario"%>
<%@page import="Controlador.ControladorPersonas"%>
<%@page import="java.util.LinkedList"%>
<%@page import="Modelo.ModeloPersonas"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%
        ModeloPersonas modPersonas = new ModeloPersonas();
        modPersonas = (ModeloPersonas) request.getAttribute("modeloPersonas");
    %>
    <head>         
        <%@include file="Principal/Head.html" %>     
        <script type="text/javascript" src="Principal/js/jquery.min.js" ></script>
        <script type="text/javascript" src="Principal/js/ValidacionesPersonas.js" ></script> 
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
                            <h2>Personas</h2>
                            <div class="clearfix"></div>
                        </div>
                        <div class="x_content">
                            <br />
                            <form class="form-horizontal form-label-left input_mask" action="Personas" method="POST" name="PersonasJSP" enctype="multipart/form-data" id="IdPersonasJSP" >                            
                                <%
                                    if (modPersonas != null)
                                    {
                                %>
                                <div>
                                    <input type="hidden" id="Id" name="Id" value="<%=modPersonas.getId()%>">                                    
                                </div>
                                <div>
                                    <input type="hidden" id="IdAccion" name="Accion" >                                    
                                </div> 
                                <div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback">
                                    <input type="text" class="form-control has-feedback-left" id="IdCodigo" name="Codigo" placeholder="Cedula" value="<%=modPersonas.getCedula()%>">
                                    <span class="fa fa-user form-control-feedback left" aria-hidden="true"></span>
                                </div>
                                <div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback">
                                    <input type="text" class="form-control" id="IdNombre" name="Nombre" placeholder="Nombre" value="<%=modPersonas.getNombre()%>">
                                    <span class="fa fa-user form-control-feedback right" aria-hidden="true"></span>
                                </div>  
                                <div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback">
                                    <input type="text" class="form-control has-feedback-left" id="IdApellido" name="Apellido" placeholder="Apellido" value="<%=modPersonas.getApellidos()%>">
                                    <span class="fa fa-user form-control-feedback left" aria-hidden="true"></span>
                                </div>
                                <div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback">
                                    <label class="control-label col-md-3 col-sm-1 ">Estado</label>
                                    <div class="col-md-9 col-sm-12 col-xs-12">
                                        <select class="form-control" id="IdEstado" name="Estado">                                            
                                            <option><%=modPersonas.getEstado()%></option>
                                            <option>Activo</option>
                                            <option>Retirado</option>
                                        </select>
                                    </div>
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
                                    <input type="text" class="form-control has-feedback-left" id="IdCodigo" name="Codigo" placeholder="Cedula" value="">
                                    <span class="fa fa-user form-control-feedback left" aria-hidden="true"></span>
                                </div>
                                <div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback">
                                    <input type="text" class="form-control" id="IdNombre" name="Nombre" placeholder="Nombre" value="">
                                    <span class="fa fa-user form-control-feedback right" aria-hidden="true"></span>
                                </div>  
                                <div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback">
                                    <input type="text" class="form-control has-feedback-left" id="IdApellido" name="Apellido" placeholder="Apellido" value="">
                                    <span class="fa fa-user form-control-feedback left" aria-hidden="true"></span>
                                </div>
                                <div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback">
                                    <label class="control-label col-md-3 col-sm-1 ">Estado</label>
                                    <div class="col-md-9 col-sm-12 col-xs-12">
                                        <select class="form-control" id="IdEstado" name="Estado">                                                                                        
                                            <option>Activo</option>
                                            <option>Retirado</option>
                                        </select>
                                    </div>
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
                                                ControladorPersonas controladorPersonas = new ControladorPersonas();
                                                LinkedList<ModeloPersonas> listPersonas = controladorPersonas.Read();
                                                if (listPersonas.size() > 0)
                                                {
                                            %>
                                            <tr>
                                                <th>Id</th>
                                                <th>Cedula</th>
                                                <th>Nombre</th>
                                                <th>Apellido</th>
                                                <th>Estado</th>
                                                <th></th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <%
                                                for (ModeloPersonas modeloPersonas : listPersonas)
                                                {
                                            %>
                                            <tr>
                                                <td WIDTH = "0" HEIGHT="0"><%=modeloPersonas.getId()%></td>                                     
                                                <td WIDTH = "0" HEIGHT="0"><%=modeloPersonas.getCedula()%></td>                                     
                                                <td WIDTH = "0" HEIGHT="0"><%=modeloPersonas.getNombre()%></td>  
                                                <td WIDTH = "0" HEIGHT="0"><%=modeloPersonas.getApellidos()%></td>  
                                                <td WIDTH = "0" HEIGHT="0"><%=modeloPersonas.getEstado()%></td>  
                                                <td WIDTH = "10" HEIGHT="0" class="text-center">
                                                    <button class="massshow-modal btn btn-dark btn-xs"  
                                                            data-id="<%=modeloPersonas.getId()%>"
                                                            data-codigo="<%=modeloPersonas.getCedula()%>"
                                                            data-nombre="<%=modeloPersonas.getNombre()%>"
                                                            data-apellido="<%=modeloPersonas.getApellidos()%>"
                                                            data-estado="<%=modeloPersonas.getEstado()%>"
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