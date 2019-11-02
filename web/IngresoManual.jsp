<%-- 
    Document   : IngresoManual
    Created on : 31-oct-2019, 15:40:36
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

        /*ControladorGrupoPoliza controladorGrupoPoliza = new ControladorGrupoPoliza();
        LinkedList<ModeloGrupoPolizas> listGrupoPolizas = controladorGrupoPoliza.Read();*/
        ControladorPersonasPoliza controladorPersonasPoliza = new ControladorPersonasPoliza();
        LinkedList<ModeloPersonasPolizas> llistPersonasPolizas = controladorPersonasPoliza.Read();

        /*ModeloPersonas modPersonas = new ModeloPersonas();
        modPersonas = (ModeloPersonas) request.getAttribute("modeloPersonas");*/
    %>
    <head>         
        <%@include file="Principal/Head.html" %>     
        <script type="text/javascript" src="Principal/js/jquery.min.js" ></script>
        <script type="text/javascript" src="Principal/js/ValidacionesEdicionManual.js" ></script> 
    </head>
    <body class="nav-md" onload="ListarUser()">
        <%ModeloUsuario modeloUsuarios = (ModeloUsuario) request.getSession().getAttribute("user");
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
                            <h2>Ingreso Manual</h2>
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
                                        <option value ="<%=modeloPersona.getId()%>"><%=modeloPersona.getApellidos() + " " + modeloPersona.getNombre()%></option> 
                                        <%
                                            }
                                        %>  
                                    </select>
                                </div>   
                                <div class="text-center">
                                    <div class="col-lg-12" style="text-align: center">                                        
                                        <button type="button" class="btn btn-success" id="IdConsultarPoliza" name="ConsultarPolizas">Consultar Polizas</button>
                                    </div>                        
                                </div> 
                                <div class="col-md-0 col-sm-0 col-xs-12 form-group has-feedback selector-poliza">                                    
                                    <select class="form-control" id="IdPoliza" name="Poliza">                                            
                                    </select>
                                </div>
                                <div class="col-md-6 col-sm-6 col-xs-12 form-group">
                                    <label class="control-label col-md-3 col-sm-3 col-xs-12">Filtro Mes</label>
                                    <div class="col-md-9 col-sm-12 col-xs-12">
                                        <select class="form-control" id="IdMes" name="Mes">                                                                                        
                                            <option value="Enero">Enero</option>
                                            <option value="Febrero">Febrero</option>
                                            <option value="Marzo">Marzo</option>
                                            <option value="Abril">Abril</option>
                                            <option value="Mayo">Mayo</option>
                                            <option value="Junio">Junio</option>
                                            <option value="Julio">Julio</option>
                                            <option value="Agosto">Agosto</option>
                                            <option value="Septiembre">Septiembre</option>
                                            <option value="Octubre">Octubre</option>
                                            <option value="Noviembre">Noviembre</option>
                                            <option value="Diciembre">Diciembre</option>

                                        </select>
                                    </div>
                                </div>
                                <div class="col-md-6 col-sm-6 col-xs-12 form-group">
                                    <label class="control-label col-md-3 col-sm-3 col-xs-12">Año a Consultar</label>                                    
                                    <div class="col-md-9 col-sm-9 col-xs-12">                                        
                                        <select class="form-control" id="IdAnoConsultar" name="AnoConsultar">                                                                                        
                                            <option value="2019">2019</option>
                                            <option value="2020">2020</option>
                                            <option value="2021">2021</option>
                                            <option value="2022">2022</option>
                                            <option value="2023">2023</option>
                                            <option value="2024">2024</option>
                                            <option value="2025">2025</option>
                                            <option value="2026">2026</option>
                                            <option value="2027">2027</option>
                                            <option value="2028">2028</option>
                                            <option value="2029">2029</option>
                                            <option value="2030">2030</option>

                                        </select>
                                    </div>
                                </div>
                                <div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback">
                                    <input type="text" class="form-control" id="IdPagoPoliza" name="PagoPoliza" placeholder="Pago Poliza" value="">
                                    <span class="fa fa-user form-control-feedback right" aria-hidden="true"></span>
                                </div>  
                                <div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback">
                                    <input type="text" class="form-control" id="IdDeducionPoliza" name="DeducionPoliza" placeholder="Deducion Poliza" value="">
                                    <span class="fa fa-user form-control-feedback right" aria-hidden="true"></span>
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
                                        <!--button type="button" class="btn btn-primary" id="IdNuevo" name="Nuevo">Nuevo</button-->
                                        <button type="button" class="btn btn-success" id="IdGuardar" name="Guardar">Guardar</button>
                                        <!--button type="button" class="btn btn-danger" id="IdEliminar" name="Eliminar">Eliminar</button>
                                        <button type="button" class="btn btn-primary" id="IdCancelar" name="Cancelar">Cancelar</button-->
                                    </div>                        
                                </div>                                
                                <br>
                                <br>
                                <br>
                                <br> 
                                <div align="center" id="espera" style="display: none">
                                    <img src="Principal/images/loading.gif">
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