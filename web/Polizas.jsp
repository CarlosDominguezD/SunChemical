<%-- 
    Document   : Polizas
    Created on : 04-jul-2019, 19:50:06
    Author     : Carlos A Dominguez D
--%>

<%@page import="Modelo.ModeloUsuario"%>
<%@page import="java.util.LinkedList"%>
<%@page import="Controlador.ControladorPoliza"%>
<%@page import="Modelo.ModeloPoliza"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%
        ModeloPoliza modPoliza = new ModeloPoliza();
        modPoliza = (ModeloPoliza) request.getAttribute("modeloPoliza");
    %>
    <head>        
        <%@include file="Principal/Head.html" %>   
        <script type="text/javascript" src="Principal/js/jquery.min.js" ></script>
        <script type="text/javascript" src="Principal/js/ValidacionesPoliza.js" ></script>
    </head>
    <body class="nav-md">
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
                            <h2>Polizas</h2>
                            <div class="clearfix"></div>
                        </div>
                        <div class="x_content">
                            <br />
                            <form class="form-horizontal form-label-left input_mask" action="Polizas" method="POST" name="PolizasJSP" enctype="multipart/form-data" id="IdPolizasJSP" >
                                <%
                                    if (modPoliza != null)
                                    {
                                %>
                                <div>
                                    <input type="hidden" id="Id" name="Id" value="<%=modPoliza.getId()%>">                                    
                                </div>
                                <div>
                                    <input type="hidden" id="IdAccion" name="Accion" >                                     
                                </div>   
                                <div class="col-md-0 col-sm-0 col-xs-12 form-group has-feedback">
                                    <input type="text" class="form-control" id="IdNombre" name="Nombre" placeholder="Nombre" value="<%=modPoliza.getNombre()%>">
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
                                <div class="col-md-0 col-sm-0 col-xs-12 form-group has-feedback">
                                    <input type="text" class="form-control" id="IdNombre" name="Nombre" placeholder="Nombre" value="">
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
                                                ControladorPoliza controladorPoliza = new ControladorPoliza();
                                                LinkedList<ModeloPoliza> listPoliza = controladorPoliza.Read();
                                                if (listPoliza.size() > 0)
                                                {
                                            %>
                                            <tr>
                                                <th>Id</th>
                                                <th>Nombre</th>                                                 
                                                <th></th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <%
                                                for (ModeloPoliza modeloPoliza : listPoliza)
                                                {
                                            %> 
                                            <tr>
                                                <td WIDTH = "300" HEIGHT="0"><%=modeloPoliza.getId()%></td>                                     
                                                <td WIDTH = "800" HEIGHT="0"><%=modeloPoliza.getNombre()%></td>                                                
                                                <td WIDTH = "10" HEIGHT="0" class="text-center">
                                                    <button class="massshow-modal btn btn-dark btn-xs"  
                                                            data-id="<%=modeloPoliza.getId()%>"
                                                            data-codigo="<%=modeloPoliza.getCodigo()%>"
                                                            data-nombre="<%=modeloPoliza.getNombre()%>"
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