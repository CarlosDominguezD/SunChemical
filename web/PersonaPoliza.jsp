<%-- 
    Document   : PersonaPoliza
    Created on : 08-jul-2019, 14:55:47
    Author     : Carlos A Dominguez D
--%>

<%@page import="Modelo.ModeloGrupoPolizas"%>
<%@page import="Controlador.ControladorGrupoPoliza"%>
<%@page import="Controlador.ControladorGrupoPolizas"%>
<%@page import="Modelo.ModeloUsuario"%>
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
        ControladorPersonas controladorPersonas = new ControladorPersonas();
        LinkedList<ModeloPersonas> listPersonas = controladorPersonas.Read();

        ControladorPoliza controladorPoliza = new ControladorPoliza();
        LinkedList<ModeloPoliza> listpolPolizas = controladorPoliza.Read();
        
        ControladorGrupoPoliza controladorGrupoPoliza = new ControladorGrupoPoliza();
        LinkedList<ModeloGrupoPolizas> listGrupoPolizas = controladorGrupoPoliza.Read();

        ControladorPersonasPoliza controladorPersonasPoliza = new ControladorPersonasPoliza();
        LinkedList<ModeloPersonasPolizas> llistPersonasPolizas = controladorPersonasPoliza.Read();

        //ModeloPersonas modPersonas = new ModeloPersonas();
        //modPersonas = (ModeloPersonas) request.getAttribute("modeloPersonas");
    %>
    <head>         
        <%@include file="Principal/Head.html" %>     
        <script type="text/javascript" src="Principal/js/jquery.min.js" ></script>
        <script type="text/javascript" src="Principal/js/ValidacionesPersonaPoliza.js" ></script> 
    </head>
    <body class="nav-md" onload="ListarUser()">
        <%            ModeloUsuario modeloUsuarios = (ModeloUsuario) request.getSession().getAttribute("user");
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
                            <h2>Persona Poliza</h2>
                            <div class="clearfix"></div>
                        </div>
                        <div class="x_content">
                            <br />
                            <form class="form-horizontal form-label-left input_mask" action="PersonaPoliza" method="POST" name="PersonaPolizaJSP" enctype="multipart/form-data" id="IdPersonaPolizaJSP" >                            
                                <%
                                    if (listPersonas != null)
                                    {
                                %>
                                <div>

                                    <input type="hidden" id="Id" name="Id" value="">                                    
                                </div>
                                <div>
                                    <input type="hidden" id="IdAccion" name="Accion" >                                    
                                </div> 
                                <div class="col-md-0 col-sm-0 col-xs-12 form-group has-feedback">
                                    <select class="form-control" id="IdPersona" name="Persona">                                                                                                                                
                                        <%
                                            for (ModeloPersonas modeloPersona : listPersonas)
                                            {
                                        %>
                                        <option value="<%=modeloPersona.getId()%>"><%=modeloPersona.getApellidos() + " " + modeloPersona.getNombre()%></option> 
                                        <%
                                            }
                                        %>  
                                    </select>
                                </div>                                
                                <div class="col-md-0 col-sm-0 col-xs-12 form-group has-feedback">                                    
                                    <select class="form-control" id="IdPoliza" name="Poliza">    
                                        <%
                                            for (ModeloGrupoPolizas modeloGrupoPolizas : listGrupoPolizas)
                                            {
                                        %>
                                        <option value="<%=modeloGrupoPolizas.getId()%>"><%=modeloGrupoPolizas.getDescripcion()%></option> 
                                        <%
                                            }
                                        %>
                                    </select>
                                </div>
                                <div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback">
                                    <input type="text" class="form-control" id="IdValorPoliza" name="ValorPoliza" placeholder="Cobro Mensual" value="">
                                    <span class="fa fa-user form-control-feedback right" aria-hidden="true"></span>
                                </div>  
                                <div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback">
                                    <select class="form-control" id="IdEstado" name="Estado">                                                                                        
                                        <option>Activo</option>
                                        <option>Finalizada</option>                                        
                                    </select>
                                </div>                                  
                                <div class="col-md-0 col-sm-0 col-xs-12 form-group has-feedback">
                                    <textarea class="resizable_textarea form-control" id="IdObservacion" name="Observacion" placeholder="Observacion"></textarea>
                                </div>
                                <%
                                } else
                                {
                                %>                                                                                          
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
                                                //ControladorPersonas controladorPersonas = new ControladorPersonas();
                                                //LinkedList<ModeloPersonas> listPersonas = controladorPersonas.Read();
                                                if (listPersonas.size() > 0)
                                                {
                                            %>
                                            <tr>
                                                <th>Id</th>
                                                <th>Nombre Persona</th>
                                                <th>Nombre Poliza</th>
                                                <th>Valor Poliza</th>
                                                <th>Observacion</th>
                                                <th>Estado</th>
                                                <th></th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <%
                                                for (ModeloPersonasPolizas modeloPersonasPoliza : llistPersonasPolizas)
                                                {
                                            %>
                                            <tr>
                                                <td WIDTH = "0" HEIGHT="0"><%=modeloPersonasPoliza.getId()%></td>                                     
                                                <td WIDTH = "0" HEIGHT="0"><%=modeloPersonasPoliza.getModeloPersonas().getNombre() + " " + modeloPersonasPoliza.getModeloPersonas().getApellidos()%></td>                                     
                                                <td WIDTH = "0" HEIGHT="0"><%=modeloPersonasPoliza.getCodigoPoliza().getDescripcion()%></td>  
                                                <td WIDTH = "0" HEIGHT="0"><%=modeloPersonasPoliza.getValorPoliza()%></td>  
                                                <td WIDTH = "0" HEIGHT="0"><%=modeloPersonasPoliza.getObservacion()%></td>  
                                                <td WIDTH = "0" HEIGHT="0"><%=modeloPersonasPoliza.getActivo()%></td>  
                                                <td WIDTH = "10" HEIGHT="0" class="text-center">
                                                    <!--<button type="button" class="massshow-modal btn btn-dark btn-xs" data-toggle="modal" data-target="#exampleModal"   -->
                                                    <button type="button" class="massshow-modal btn btn-dark btn-xs"  
                                                            data-id="<%=modeloPersonasPoliza.getId()%>"
                                                            data-idpersona="<%=modeloPersonasPoliza.getModeloPersonas().getId()%>"
                                                            data-persona="<%=modeloPersonasPoliza.getModeloPersonas().getNombre() + " " + modeloPersonasPoliza.getModeloPersonas().getApellidos()%>"                                                            
                                                            data-idpoliza="<%=modeloPersonasPoliza.getCodigoPoliza().getId()%>"
                                                            data-poliza="<%=modeloPersonasPoliza.getCodigoPoliza().getDescripcion()%>"
                                                            data-valor="<%=modeloPersonasPoliza.getValorPoliza()%>"
                                                            data-observacion="<%=modeloPersonasPoliza.getObservacion()%>"
                                                            data-estado="<%=modeloPersonasPoliza.getActivo()%>"                                                                                                                        
                                                            >Seleccionar</button>
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