<%-- 
    Document   : EjecutorReportes
    Created on : 11-jul-2019, 20:52:48
    Author     : Carlos A Dominguez D
--%>

<%@page import="java.util.LinkedList"%>
<%@page import="Controlador.ControladorReportes"%>
<%@page import="Modelo.ModeloReportes"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>         
        <%@include file="Principal/Head.html" %>     
        <script type="text/javascript" src="Principal/js/jquery.min.js" ></script>
        <script type="text/javascript" src="Principal/js/ValidacionesReportes.js" ></script> 
    </head>
    <body class="nav-md" onload="ListarUser()">
        <%@include file="Principal/Body.html" %>
        <div class="right_col" role="main">
            <div class="">            
                <div class="clearfix"></div>
                <div class="row">
                    <div class="x_panel">
                        <div class="x_title">
                            <h2>Generador de Reportes</h2>
                            <div class="clearfix"></div>
                        </div>
                        <div class="x_content">
                            <br />
                            <form action="EjecutarReporteServlet" method="GET"> 
                                
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
                                <br/>
                                <br/>
                                <br/>
                                <br/>
                                <br/>
                                <br/>
                                <div class="text-center">
                                    <div class="col-lg-12" style="text-align: center">                
                                        <button type="submit" class="btn btn-primary" id="IdDescargar" name="Descargar">Descargar</button>
                                    </div>                        
                                </div>  
                                
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
                                                <th>Nombre Reporte</th>
                                                <th>PlantillaURL</th>
                                                <th>SQL</th>                                                
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
                                <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                    <div class="modal-dialog" role="document">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title" id="exampleModalLabel">New message</h5>
                                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                    <span aria-hidden="true">&times;</span>
                                                </button>
                                            </div>
                                            <div class="modal-body">
                                                <form class="form-horizontal form-label-left input_mask">
                                                    <div class="form-group">
                                                        <div>
                                                            <input type="hidden" class="form-control" id="IdModal" name="IdModal">
                                                        </div>

                                                        <div class="col-md-0 col-sm-0 col-xs-12 form-group has-feedback">
                                                            <input type="text" class="form-control has-feedback-left" id="IdModalNombre" name="ModalNombre" placeholder="Nombre" value="">
                                                            <span class="fa fa-user form-control-feedback left" aria-hidden="true"></span>
                                                        </div>                                                        
                                                        <div class="col-md-0 col-sm-0 col-xs-12 form-group has-feedback">
                                                            <input type="text" class="form-control has-feedback-left" id="IdModalPlantillaUrl" name="ModalPlantillaUrl" placeholder="Poliza" value="">
                                                            <span class="fa fa-user form-control-feedback left" aria-hidden="true"></span>
                                                        </div>
                                                        <div class="col-md-0 col-sm-0 col-xs-12 form-group has-feedback">
                                                            <textarea class="resizable_textarea form-control" id="IdModalSql" name="ModalSql" placeholder="Sql"></textarea>
                                                        </div>
                                                    </div>
                                                </form>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
                                                    <button type="submit" class="btn btn-success" id="IdEjecutar" name="Ejecutar">Ejecutar</button>  
                                                </div> 
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
    </body>
    <%@include file="Principal/Validaciones.html" %>        
</html>