<%-- 
    Document   : Calculos
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
        <script type="text/javascript" src="Principal/js/ValidacionesCalculos.js" ></script> 
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
                            <h2>Calculos</h2>
                            <div class="clearfix"></div>
                        </div>
                        <div class="x_content">                            
                            <form class="form-horizontal form-label-left input_mask" action="ResultadosServlet" method="POST" name="ResultadosJSP" id="IdResultadosJSP">
                                <input type="hidden" class="form-control " id="IdAccion" name="Accion">
                                <input type="hidden" class="form-control " id="IdFormato" name="Formato">
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
                                <br/>
                                <br/>
                                <br/>
                                <div class="text-center">
                                    <div class="col-lg-12" style="text-align: center">
                                        <!--<button type="submit" class="btn btn-primary" id="IdSumaResultados" name="Filtrar" value="SumaResultados">Calcular</button>-->
                                        <button type="submit" class="btn btn-primary" id="IdSumaResultados" name="Filtrar" value="SumaResultados">Calcular</button>                                        
                                    </div>                        
                                </div>                                
                                <br>
                                <br>
                                <br>
                                <br> 
                                <%
                                    LinkedList<ModeloPoliza> listResulados = new LinkedList<ModeloPoliza>();
                                    listResulados = (LinkedList<ModeloPoliza>) request.getAttribute("ListaPoliza");
                                    if (listResulados != null)
                                    {
                                %>
                                <div class="x_panel">
                                    <table id="datatable" class="table table-striped table-bordered">                                  
                                        <thead>                                           
                                            <tr>
                                                <th></th>
                                                <th></th>
                                                <th>Nombre Poliza</th>
                                                <th>Valor Total</th>                                                
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <%
                                                int sumaTotales = 0;
                                                for (ModeloPoliza modeloResultados : listResulados)
                                                {
                                                    sumaTotales = sumaTotales + modeloResultados.getSuma();
                                            %>
                                            <tr>                                                                                    
                                                <td WIDTH = "1" HEIGHT="1"><input type="hidden" class="form-control"  id="IdNombrePolizas" name="NombrePolizas" value="<%=modeloResultados.getNombre()%>"></td>                                                                                     
                                                <td WIDTH = "1" HEIGHT="1"><input type="hidden" class="form-control"  id="IdValoresPolizas" name="ValoresPolizas" value="<%=modeloResultados.getSuma()%>"></td>                                                                                     
                                                <td WIDTH = "0" HEIGHT="0"><%=modeloResultados.getNombre()%></td>                                                                                     
                                                <td WIDTH = "0" HEIGHT="0"><%=modeloResultados.getSuma()%></td>                                                
                                            </tr>
                                            <%
                                                }
                                            %>                                                                                        
                                        </tbody>
                                    </table>                                    
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-md-3 col-sm-3 col-xs-12">Total Calculado</label>
                                    <div class="col-md-9 col-sm-9 col-xs-12">
                                        <input type="text" class="form-control"  id="IdTotalCalculado" name="TotalCalculado" placeholder="Total Calculado" value="<%= sumaTotales%>">
                                    </div>
                                </div>  
                                <div class="form-group">
                                    <label class="control-label col-md-3 col-sm-3 col-xs-12">Total Sap</label>
                                    <div class="col-md-9 col-sm-9 col-xs-12">
                                        <input type="text" class="form-control"  id="IdTotalSap" name="TotalSap" placeholder="Total Sap">
                                    </div>
                                </div>  
                                <br>
                                <br>
                                <div class="text-center">
                                    <div class="col-lg-12" style="text-align: center">
                                        <button type="button" class="btn btn-dark" id="IdComparar" name="Comparar" value="Comparar">Comparar Valores </button>                                        
                                    </div>                        
                                </div>                                     
                                <br>
                                <br>
                                <div class="form-group">
                                    <label class="control-label col-md-3 col-sm-3 col-xs-12">Diferencia</label>
                                    <div class="col-md-9 col-sm-9 col-xs-12">
                                        <input type="text" class="form-control"  id="IdDiferencia" name="Diferencia" placeholder="Diferencia">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-md-3 col-sm-3 col-xs-12">Observacion</label>
                                    <div class="col-md-9 col-sm-9 col-xs-12">
                                        <textarea class="resizable_textarea form-control" id="IdObservacion" name="Observacion" placeholder="Observacion"></textarea>
                                    </div>
                                </div>
                                <br/>
                                <br/>
                                <div class="text-center">
                                    <div class="col-lg-12" style="text-align: center">
                                        <button type="submit" class="btn btn-dark" id="IdBtnExport" name="BtnExport" value="BtnExport">Exportar</button>                                        
                                    </div>                        
                                </div>                                     
                                <br>
                                <br>                 
                                <%
                                    }
                                %>                                
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <%@include file="Principal/Script.html" %>
        </div>
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