<%-- 
    Document   : GrupoPoliza
    Created on : 08-jul-2019, 14:55:47
    Author     : Carlos A Dominguez D
--%>

<%@page import="Modelo.ModeloUsuario"%>
<%@page import="Modelo.ModeloGrupoPolizas"%>
<%@page import="Controlador.ControladorGrupoPoliza"%>
<%@page import="Controlador.ControladorPersonasPoliza"%>
<%@page import="Controlador.ControladorPersonas"%>
<%@page import="java.util.LinkedList"%>
<%@page import="Controlador.ControladorPoliza"%>
<%@page import="Modelo.ModeloPersonasPolizas"%>
<%@page import="Modelo.ModeloPoliza"%>
<%@page import="Modelo.ModeloPersonas"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%
        ControladorPoliza controladorPoliza = new ControladorPoliza();
        LinkedList<ModeloPoliza> listpolPolizas = controladorPoliza.Read();;
    %>
    <head>         
        <%@include file="Principal/Head.html" %>     
        <script type="text/javascript" src="Principal/js/jquery.min.js" ></script>
        <script type="text/javascript" src="Principal/js/ValidacionesGrupoPoliza.js" ></script> 
    </head>
    <body class="nav-md" onload="ListarUser()">
        <%
            ModeloUsuario modeloUsuarios = (ModeloUsuario) request.getSession().getAttribute("user");
            //String nombre = modeloUsuarios.getNombre().toString();
            if (modeloUsuarios != null) {
        %> 
        <%@include file="Principal/Body.html" %>
        <div class="right_col" role="main">
            <div class="">            
                <div class="clearfix"></div>
                <div class="row">
                    <div class="x_panel">
                        <div class="x_title">
                            <h2>Grupo Poliza</h2>
                            <div class="clearfix"></div>
                        </div>
                        <div class="x_content">
                            <br />
                            <form class="form-horizontal form-label-left input_mask" action="GrupoPoliza" method="POST" name="GrupoPolizaJSP" enctype="multipart/form-data" id="IdGrupoPolizaJSP" >                            
                                <%
                                    if (listpolPolizas != null) {
                                %>
                                <div>
                                    <input type="hidden" id="IdAccion" name="Accion" >     
                                    <input type="hidden" id="Id" name="Id" >     
                                    <input type="hidden" id="IdPol" name="Pol" >     
                                </div>                                                                
                                <div class="col-md-0 col-sm-0 col-xs-12 form-group has-feedback">                                    
                                    <select class="form-control" id="IdPoliza" name="Poliza">    
                                        <%
                                            for (ModeloPoliza modeloPoliza : listpolPolizas) {
                                        %>
                                        <option value="<%=modeloPoliza.getId()%>"><%=modeloPoliza.getNombre()%></option> 
                                        <%
                                            }
                                        %>
                                    </select>
                                </div>
                                <div class="col-md-0 col-sm-0 col-xs-12 form-group has-feedback">
                                    <input type="text" class="form-control" id="IdDescripcion" name="Descripcion" placeholder="Descripcion" value="">
                                    <span class="fa fa-user form-control-feedback right" aria-hidden="true"></span>
                                </div>  
                                <div class="col-md-0 col-sm-0 col-xs-12 form-group has-feedback">
                                    <input type="text" class="form-control" id="IdCodigoNomina" name="CodigoNomina" placeholder="Codigo Nomina" value="">
                                    <span class="fa fa-user form-control-feedback right" aria-hidden="true"></span>
                                </div>  
                                <div class="col-md-0 col-sm-0 col-xs-12 form-group has-feedback">
                                    <input type="text" class="form-control" id="IdCodigoReintegro" name="CodigoReintegro" placeholder="Codigo Reintegro" value="">
                                    <span class="fa fa-user form-control-feedback right" aria-hidden="true"></span>
                                </div>  
                                <div class="col-md-0 col-sm-0 col-xs-12 form-group has-feedback">
                                    <input type="text" class="form-control" id="IdCodigoCorredor" name="CodigoCorredor" placeholder="Codigo Corredor" value="">
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
                                                ControladorGrupoPoliza controladorGrupoPoliza = new ControladorGrupoPoliza();
                                                LinkedList<ModeloGrupoPolizas> listmModeloGrupoPoliza = controladorGrupoPoliza.Read();
                                                if (listmModeloGrupoPoliza.size() > 0) {
                                            %>
                                            <tr>
                                                <th>Id</th>
                                                <th>Poliza</th>
                                                <th>Descripcion</th>
                                                <th>Codigo Deduccion</th>
                                                <th>Codigo Reintegro</th>
                                                <th>Codigo Cobro</th>                                                
                                                <th></th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <%
                                                for (ModeloGrupoPolizas modeloGrupoPoliza : listmModeloGrupoPoliza) {
                                            %> 
                                            <tr>
                                                <td WIDTH = "300" HEIGHT="0"><%=modeloGrupoPoliza.getId()%></td>   
                                                <td WIDTH = "300" HEIGHT="0"><%=modeloGrupoPoliza.getModeloPoliza().getNombre()%></td>   
                                                <td WIDTH = "800" HEIGHT="0"><%=modeloGrupoPoliza.getDescripcion()%></td>
                                                <td WIDTH = "800" HEIGHT="0"><%=modeloGrupoPoliza.getCodigoNomina()%></td> 
                                                <td WIDTH = "800" HEIGHT="0"><%=modeloGrupoPoliza.getCodigoReintegro()%></td> 
                                                <td WIDTH = "800" HEIGHT="0"><%=modeloGrupoPoliza.getCodigoCorredor()%></td> 
                                                <td WIDTH = "10" HEIGHT="0" class="text-center">
                                                    <!--<button class="massshow-modal btn btn-dark btn-xs" data-toggle="modal" data-target="#exampleModal"   -->
                                                    <button class="massshow-modal btn btn-dark btn-xs"   
                                                            data-id="<%=modeloGrupoPoliza.getId()%>"
                                                            data-idpoliza="<%=modeloGrupoPoliza.getModeloPoliza().getId()%>"
                                                            data-nombrepoliza="<%=modeloGrupoPoliza.getModeloPoliza().getNombre()%>" 
                                                            data-descripcion="<%=modeloGrupoPoliza.getDescripcion()%>" 
                                                            data-codigonomina="<%=modeloGrupoPoliza.getCodigoNomina()%>"
                                                            data-codigoreintegro="<%=modeloGrupoPoliza.getCodigoReintegro()%>"
                                                            data-codigocorredor="<%=modeloGrupoPoliza.getCodigoCorredor()%>"
                                                            type="button" id="IdModificar" name="Modificar">Selecionar</button>                     
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
        } else {
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