<%-- 
    Document   : Resultados
    Created on : 04-jul-2019, 19:52:27
    Author     : Carlos A Dominguez D
--%>

<%@page import="Modelo.ModeloUsuario"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Controlador.ControladorMeses"%>
<%@page import="Modelo.ModeloMeses"%>
<%@page import="Controlador.ControladorPoliza"%>
<%@page import="Modelo.ModeloPoliza"%>
<%@page import="Controlador.ControladorPersonas"%>
<%@page import="Modelo.ModeloPersonas"%>
<%@page import="java.util.LinkedList"%>
<%@page import="Controlador.ControladorResultados"%>
<%@page import="Modelo.ModeloResultados"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>        
        <%@include file="Principal/Head.html" %>
        <script type="text/javascript" src="Principal/js/jquery.min.js" ></script>
        <script type="text/javascript" src="Principal/js/ValidacionesResultados.js" ></script> 
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
                            <h2>Edicion de Resultados</h2>
                            <div class="clearfix"></div>
                        </div>
                        <div class="x_content">
                            <br />
                            <form class="form-horizontal form-label-left input_mask" action="ResultadosServlet" method="POST" name="ResultadosJSP" id="IdResultadosJSP">
                                <input type="hidden" class="form-control " id="IdAccion" name="Accion">
                                <input type="hidden" class="form-control " id="IdFormato" name="Formato">
                                <div class="form-group">
                                    <label class="control-label col-md-3 col-sm-3 col-xs-12">Filtro Empleado</label>
                                    <div class="col-md-9 col-sm-12 col-xs-12">
                                        <select class="form-control" id="IdPersona" name="Persona">                                             
                                            <%
                                                LinkedList<ModeloPersonas> listModeloPersonases;
                                                ControladorPersonas controladorPersonas = new ControladorPersonas();
                                                listModeloPersonases = controladorPersonas.Read();
                                                for (ModeloPersonas modeloPersona : listModeloPersonases)
                                                {

                                            %>
                                            <option value="<%=modeloPersona.getId()%>"><%=modeloPersona.getApellidos() + " " + modeloPersona.getNombre()%></option>

                                            <%                                                }
                                            %>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-md-3 col-sm-3 col-xs-12">Filtro Tipo Poliza</label>
                                    <div class="col-md-9 col-sm-12 col-xs-12">
                                        <select class="form-control" id="IdPoliza" name="Poliza">                                            
                                            <%
                                                LinkedList<ModeloPoliza> listModeloPoliza;
                                                ControladorPoliza controladorPoliza = new ControladorPoliza();
                                                listModeloPoliza = controladorPoliza.Read();
                                                for (ModeloPoliza modeloPoliza : listModeloPoliza)
                                                {

                                            %>
                                            <option value="<%=modeloPoliza.getId()%>"><%=modeloPoliza.getNombre()%></option>

                                            <%                                                }
                                            %>
                                        </select>
                                    </div>
                                </div>

                                <div class="form-group">
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

                                <div class="form-group">
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
                                <div class="form-group">
                                    <label class="control-label col-md-3 col-sm-3 col-xs-12">Diferencia</label>
                                    <div class="col-md-9 col-sm-12 col-xs-12">
                                        <select class="form-control" id="IdOperador1" name="Operador1">
                                            <option value="<>"><></option>
                                            <option value=">">></option>
                                            <option value=">=">>=</option>
                                            <option value="=">=</option>                                            
                                            <option value="<="><=</option>
                                            <option value="<"><</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-md-3 col-sm-3 col-xs-12">Valor a Consultar</label>
                                    <div class="col-md-9 col-sm-9 col-xs-12">
                                        <input type="number" class="form-control"  id="IdValorConsultar1" name="ValorConsultar1" placeholder="Valor a Consultar">
                                    </div>
                                </div>
                                <!--div class="form-group">
                                    <label class="control-label col-md-3 col-sm-3 col-xs-12">Conector</label>
                                    <div class="col-md-9 col-sm-12 col-xs-12">
                                        <select class="form-control" id="IdConector" name="Conector">
                                            <option value="AND">AND</option>
                                            <option value="OR">OR</option>                                            
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-md-3 col-sm-3 col-xs-12">Diferencia</label>
                                    <div class="col-md-9 col-sm-12 col-xs-12">
                                        <select class="form-control" id="IdOperador2" name="Operador2">
                                            <option value=">">></option>
                                            <option value=">=">>=</option>
                                            <option value="=">=</option>
                                            <option value="<="><=</option>
                                            <option value="<"><</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-md-3 col-sm-3 col-xs-12">Valor a Consultar</label>
                                    <div class="col-md-9 col-sm-9 col-xs-12">
                                        <input type="number" class="form-control"  id="IdValorConsultar2" name="ValorConsultar2" placeholder="Valor a Consultar">
                                    </div>
                                </div-->                                
                                <br/>
                                <br/>
                                <br/>
                                <div class="text-center">
                                    <div class="col-lg-12" style="text-align: center">
                                        <button type="submit" class="btn btn-primary" id="IdFiltrar" name="Filtrar" value="Filtrar">Buscar Resultado</button>                                        
                                    </div>                        
                                </div>                                
                                <br>
                                <br>
                                <br>
                                <br> 
                                <%
                                    LinkedList<ModeloResultados> listResulados = new LinkedList<ModeloResultados>();
                                    listResulados = (LinkedList<ModeloResultados>) request.getAttribute("listModeloResultadoses");
                                    if (listResulados != null)
                                    {
                                %>
                                <div class="form-group">                                    
                                    <div class="col-md-9 col-sm-9 col-xs-12">
                                        <h5><%= request.getAttribute("Fil")%></h5>                                        
                                    </div>
                                </div> 
                                <div class="x_panel">
                                    <table id="datatable" class="table table-striped table-bordered">
                                        <thead>
                                            <tr>
                                                <th></th>
                                                <th></th>
                                                <th>Id</th>
                                                <th>Poliza</th>
                                                <th>Cedula Empleados</th>
                                                <th>Nombre Empleados</th>
                                                <th>Pago</th>
                                                <th>Deduccion</th>
                                                <th>Diferencia</th>                                                
                                                <th></th>                                                
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <%
                                                for (ModeloResultados modeloResultado : listResulados)
                                                {
                                            %>
                                            <tr>
                                                <td><input type="checkbox" class="custom-control-input"  id="IdSelect" name="Select" value="Select"><br></td>                                                
                                                <td><input type="hidden" class="custom-control-input"  id="IdResultadoGetIdSaldos" name="ResultadoGetIdSaldos" value="<%=modeloResultado.getIdSaldos().getId()%>"><br></td>                                                
                                                <td WIDTH = "0" HEIGHT="0"><%=modeloResultado.getId()%></td>                                     
                                                <td WIDTH = "0" HEIGHT="0"><%=modeloResultado.getIdPoliza().getNombre()%></td>                                     
                                                <td WIDTH = "0" HEIGHT="0"><%=modeloResultado.getIdPersona().getCedula()%></td>  
                                                <td WIDTH = "0" HEIGHT="0"><%=modeloResultado.getIdPersona().getApellidos()+ " " + modeloResultado.getIdPersona().getNombre()  %></td>  
                                                <td WIDTH = "0" HEIGHT="0"><%=modeloResultado.getPago()%></td>  
                                                <td WIDTH = "0" HEIGHT="0"><%=modeloResultado.getDeduccion()%></td>  
                                                <td WIDTH = "0" HEIGHT="0"><%=modeloResultado.getDiferencia()%></td>   
                                                <td WIDTH = "10" HEIGHT="0" class="text-center">
                                                    <button type="button" class="btn btn-primary btn-xs" data-toggle="modal" data-target="#exampleModal"   
                                                            data-id="<%=modeloResultado.getId()%>"
                                                            
                                                            data-idpersona="<%=modeloResultado.getIdPersona().getId()%>"
                                                            data-nombrepersona="<%=modeloResultado.getIdPersona().getNombre() + " " + modeloResultado.getIdPersona().getApellidos()%>"
                                                            data-cedula="<%=modeloResultado.getIdPersona().getCedula()%>"
                                                            data-idpoliza="<%=modeloResultado.getIdPoliza().getId()%>"
                                                            data-codigopoliza="<%=modeloResultado.getIdPoliza().getCodigo()%>"
                                                            data-nombrepoliza="<%=modeloResultado.getIdPoliza().getNombre()%>"
                                                            data-deducion="<%=modeloResultado.getDeduccion()%>"
                                                            data-pago="<%=modeloResultado.getPago()%>"
                                                            data-diferencia="<%=modeloResultado.getDiferencia()%>"
                                                            data-debaja="<%=modeloResultado.getIdSaldos().getDebaja()%>"
                                                            data-idmes ="<%= request.getAttribute("IdMes")%>"
                                                            data-idano ="<%= request.getAttribute("IdAno")%>"
                                                            >Editar</button>
                                                </td>                                                
                                            </tr>
                                            <%
                                                }
                                            %>
                                        </tbody>
                                    </table>
                                </div>
                                <div class="text-center">
                                    <div class="col-lg-12" style="text-align: center">
                                        <button type="button" class="btn btn-danger" id="IdBaja" name="Baja" value="Baja">Dar de Baja</button>                                        
                                    </div>                        
                                </div>
                                <%
                                    }
                                %>
                                <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                    <div class="modal-dialog" role="document">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title" id="exampleModalLabel">Editar Resultados</h5>
                                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                    <span aria-hidden="true">&times;</span>
                                                </button>
                                            </div>
                                            <div class="modal-body">
                                                <form class="form-horizontal form-label-left input_mask">
                                                    <div class="form-group">
                                                        <!--div class="col-md-0 col-sm-0 col-xs-12 form-group has-feedback">
                                                            <input type="text" class="form-control" id="IdModalPersonaPoliza" name="ModalPersonaPoliza">
                                                        </div -->
                                                        <div class="col-md-0 col-sm-0 col-xs-12 form-group has-feedback">
                                                            <input type="text" class="form-control" id="IdModalMesFil" name="ModalIdMesFil">
                                                        </div>
                                                        <div class="col-md-0 col-sm-0 col-xs-12 form-group has-feedback">
                                                            <input type="text" class="form-control" id="IdModalAnoFil" name="ModalAnoFil">
                                                        </div>
                                                        <div class="col-md-0 col-sm-0 col-xs-12 form-group has-feedback">
                                                            <input type="text" class="form-control" id="IdModal" name="IdModal">
                                                        </div>
                                                        <div class="col-md-0 col-sm-0 col-xs-12 form-group has-feedback">
                                                            <input type="text" class="form-control " id="IdModalIdPersona" name="ModalIdPersona">
                                                        </div>
                                                        <div class="form-group">
                                                            <label class="control-label col-md-3 col-sm-3 col-xs-12">Cedula</label>
                                                            <div class="col-md-9 col-sm-9 col-xs-12">
                                                                <input type="text" class="form-control " id="IdModalCedula" name="ModalCedula" placeholder="Cedula">
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <label class="control-label col-md-3 col-sm-3 col-xs-12">Nombre Persona</label>
                                                            <div class="col-md-9 col-sm-9 col-xs-12">
                                                                <input type="text" class="form-control has-feedback-left" id="IdModalNombrePersona" name="ModalNombrePersona" placeholder="Nombre Persona">
                                                            </div>
                                                        </div>
                                                        <div class="col-md-0 col-sm-0 col-xs-12 form-group has-feedback">
                                                            <input type="text" class="form-control " id="IdModalIdPoliza" name="ModalIdPoliza">
                                                        </div>
                                                        <div class="form-group">
                                                            <label class="control-label col-md-3 col-sm-3 col-xs-12">Codigo</label>
                                                            <div class="col-md-9 col-sm-9 col-xs-12">
                                                                <input type="text" class="form-control " id="IdModalCodigoPoliza" name="ModalCodigoPoliza" placeholder="Codigo">
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <label class="control-label col-md-3 col-sm-3 col-xs-12">Nombre seguro</label>
                                                            <div class="col-md-9 col-sm-9 col-xs-12">
                                                                <input type="text" class="form-control has-feedback-left" id="IdModalNombrePoliza" name="ModalNombrePoliza" placeholder="Nombre Seguro" value="">
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <label class="control-label col-md-3 col-sm-3 col-xs-12">Deduccion</label>
                                                            <div class="col-md-9 col-sm-9 col-xs-12">
                                                                <input type="text" class="form-control has-feedback-left" id="IdModalDeduccion" name="ModalDeduccion" placeholder="Deduccion" value="">
                                                            </div>
                                                        </div> 
                                                        <div class="form-group">
                                                            <label class="control-label col-md-3 col-sm-3 col-xs-12">Pago</label>
                                                            <div class="col-md-9 col-sm-9 col-xs-12">
                                                                <input type="text" class="form-control has-feedback-left" id="IdModalPago" name="ModalPago" placeholder="Pago" value="">
                                                            </div>
                                                        </div> 

                                                        <div class="form-group">
                                                            <label class="control-label col-md-3 col-sm-3 col-xs-12">Diferencia</label>
                                                            <div class="col-md-9 col-sm-9 col-xs-12">
                                                                <input type="text" class="form-control has-feedback-left" id="IdModalDiferencia" name="ModalDiferencia" placeholder="Diferencia" value="">
                                                            </div>
                                                        </div> 

                                                        <div class="form-group">
                                                            <label class="control-label col-md-3 col-sm-3 col-xs-12">Notas</label>
                                                            <div class="col-md-9 col-sm-9 col-xs-12">
                                                                <textarea class="resizable_textarea form-control" id="IdModalNotas" name="ModalNotas" placeholder="Notas"></textarea>
                                                            </div>
                                                        </div> 

                                                        <div class="form-group">
                                                            <label class="control-label col-md-3 col-sm-3 col-xs-12">Observacion</label>
                                                            <div class="col-md-9 col-sm-9 col-xs-12">
                                                                <textarea class="resizable_textarea form-control" id="IdModalObservacion" name="ModalObservacion" placeholder="Observacion"></textarea>
                                                            </div>
                                                        </div> 

                                                        <!--<div class="col-md-0 col-sm-0 col-xs-12 form-group has-feedback">
                                                            <input type="text" class="form-control has-feedback-left" id="IdModalDeBaja" name="ModalDeBaja" placeholder="Dar de Baja" value="">
                                                            <span class="fa fa-user form-control-feedback left" aria-hidden="true"></span>
                                                        </div>-->
                                                    </div>
                                                    <div class="modal-footer">
                                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
                                                        <button type="button" class="btn btn-success" id="IdUpdate" name="Guardar">Guardar</button>   
                                                    </div>
                                                </form>
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