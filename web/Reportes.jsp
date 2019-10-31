<%-- 
    Document   : Reportes
    Created on : 12-jul-2019, 8:04:32
    Author     : Carlos A Dominguez D
--%>

<%@page import="Modelo.ModeloUsuario"%>
<%@page import="Controlador.ControladorReportes"%>
<%@page import="Modelo.ModeloReportes"%>
<%@page import="java.util.LinkedList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>   
    <head>         
        <%@include file="Principal/Head.html" %>     
        <script type="text/javascript" src="Principal/js/jquery.min.js" ></script>
        <script type="text/javascript" src="Principal/js/ValidacionesReportes.js" ></script> 
    </head>
    <body class="nav-md" onload="ListarUser()">
        <%
            ModeloUsuario modeloUsuarios = (ModeloUsuario) request.getSession().getAttribute("user");
            //String nombre = modeloUsuarios.getNombre().toString();
            if (modeloUsuarios != null)
            {
        %> 
        <%
            ModeloReportes modReportes = new ModeloReportes();
            modReportes = (ModeloReportes) request.getAttribute("modeloReportes");
        %>
        <%@include file="Principal/Body.html" %>
        <div class="right_col" role="main">
            <div class="">            
                <div class="clearfix"></div>
                <div class="row">
                    <div class="x_panel">
                        <div class="x_title">
                            <h2>Configuracion de Reportes</h2>
                            <div class="clearfix"></div>
                        </div>
                        <div class="x_content">
                            <br />
                            <form class="form-horizontal form-label-left input_mask" action="Reportes" method="POST" name="ReportesJSP" enctype="multipart/form-data" id="IdReportesJSP" >                            
                                <%
                                    if (modReportes != null)
                                    {
                                %>
                                <div>
                                    <input type="hidden" id="Id" name="Id" value="<%=modReportes.getId()%>">                                    
                                </div>
                                <div>
                                    <input type="hidden" id="IdAccion" name="Accion" >                                    
                                </div>
                                <div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback">
                                    <input type="text" class="form-control has-feedback-left" id="IdNombre" name="Nombre" placeholder="Nombre Reporte" value="<%=modReportes.getNombre()%>">
                                    <span class="fa fa-user form-control-feedback left" aria-hidden="true"></span>
                                </div>
                                <div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback">
                                    <input type="text" class="form-control" id="IdPlantillaUrl" name="PlantillaUrl" placeholder="Plantilla URL" value="<%=modReportes.getPlantillaURL()%>">
                                    <span class="fa fa-user form-control-feedback right" aria-hidden="true"></span>
                                </div>  
                                <div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback">
                                    <textarea class="resizable_textarea form-control" id="IdSql" name="Sql" placeholder="Sentencia SQL"></textarea>                                    
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
                                    <input type="text" class="form-control has-feedback-left" id="IdNombre" name="Nombre" placeholder="Nombre Reporte" value="">
                                    <span class="fa fa-user form-control-feedback left" aria-hidden="true"></span>
                                </div>
                                <div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback">
                                    <input type="text" class="form-control" id="IdPlantillaUrl" name="PlantillaUrl" placeholder="Plantilla URL" value="">
                                    <span class="fa fa-user form-control-feedback right" aria-hidden="true"></span>
                                </div>  
                                <div class="col-md-0 col-sm-0 col-xs-12 form-group has-feedback">
                                    <textarea class="resizable_textarea form-control" id="IdSql" name="Sql" placeholder="Sentencia SQL"></textarea>                                    
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
                                                ControladorReportes controladorReportes = new ControladorReportes();
                                                LinkedList<ModeloReportes> listReportes = controladorReportes.Read();
                                                if (listReportes.size() > 0)
                                                {
                                            %>
                                            <tr>
                                                <th>Id</th>                                                
                                                <th>Nombre</th>
                                                <th>Ruta URL</th>
                                                <th>Sentencia SQL</th>
                                                <th></th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <%
                                                for (ModeloReportes modeloReportes : listReportes)
                                                {
                                            %>
                                            <tr>
                                                <td WIDTH = "0" HEIGHT="0"><%=modeloReportes.getId()%></td>                                     
                                                <td WIDTH = "0" HEIGHT="0"><%=modeloReportes.getNombre()%></td>                                     
                                                <td WIDTH = "0" HEIGHT="0"><%=modeloReportes.getPlantillaURL()%></td>  
                                                <td WIDTH = "0" HEIGHT="0"><%=modeloReportes.getSql()%></td>                                                  
                                                <td WIDTH = "10" HEIGHT="0" class="text-center">
                                                    <button class="massshow-modal btn btn-dark btn-xs"  
                                                            data-id="<%=modeloReportes.getId()%>"
                                                            data-nombre="<%=modeloReportes.getNombre()%>"
                                                            data-plantillaurl="<%=modeloReportes.getPlantillaURL()%>"
                                                            data-sql="<%=modeloReportes.getSql()%>"                                                            
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