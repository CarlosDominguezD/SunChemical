<%-- 
    Document   : Pruebas
    Created on : 04-jul-2019, 19:50:06
    Author     : Carlos A Dominguez D
--%>
<%@page import="Controlador.ControladorPoliza"%>
<%@page import="Modelo.ModeloPoliza"%>
<%@page import="Controlador.ControladorPersonas"%>
<%@page import="Modelo.ModeloPersonas"%>
<%@page import="java.util.LinkedList"%>
<%@page import="Controlador.ControladorResultados"%>
<%@page import="Modelo.ModeloResultados"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- Meta, title, CSS, favicons, etc. -->
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>DataTables | Gentelella</title>

        <!-- Bootstrap -->
        <link href="Principal/vendors/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
        <!-- Font Awesome -->
        <link href="Principal/vendors/font-awesome/css/font-awesome.min.css" rel="stylesheet">
        <!-- NProgress -->
        <link href="Principal/vendors/nprogress/nprogress.css" rel="stylesheet">
        <!-- iCheck -->
        <link href="Principal/vendors/iCheck/skins/flat/green.css" rel="stylesheet">
        <!-- Datatables -->
        <link href="Principal/vendors/datatables.net-bs/css/dataTables.bootstrap.min.css" rel="stylesheet">
        <link href="Principal/vendors/datatables.net-buttons-bs/css/buttons.bootstrap.min.css" rel="stylesheet">
        <link href="Principal/vendors/datatables.net-fixedheader-bs/css/fixedHeader.bootstrap.min.css" rel="stylesheet">
        <link href="Principal/vendors/datatables.net-responsive-bs/css/responsive.bootstrap.min.css" rel="stylesheet">
        <link href="Principal/vendors/datatables.net-scroller-bs/css/scroller.bootstrap.min.css" rel="stylesheet">

        <!-- Custom Theme Style -->
        <link href="Principal/build/css/custom.min.css" rel="stylesheet">
                <script type="text/javascript" src="Principal/js/jquery.min.js" ></script>
        <script type="text/javascript" src="Principal/js/ValidacionesResultados.js" ></script> 
        
    </head>

    <body class="nav-md">
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
                            <form id="IdResultadosJSP" class="form-horizontal form-label-left input_mask">

                                <input type="hidden" class="form-control " id="IdAccion" name="Accion">
                                <input type="hidden" class="form-control " id="IdFormato" name="Formato">
                                <%
                                    LinkedList<ModeloPersonas> listModepersoans;
                                    ControladorPersonas controladorPersonas = new ControladorPersonas();
                                    listModepersoans = controladorPersonas.Read();
                                %>
                                <div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback">
                                    <label class="control-label col-md-3 col-sm-1 ">Filtro Empleado</label>
                                    <div class="col-md-9 col-sm-12 col-xs-12">
                                        <select class="form-control">
                                            <%
                                                for (ModeloPersonas modeloPersona : listModepersoans)
                                                {
                                            %>
                                            <option value="<%=modeloPersona.getId()%>"><%=modeloPersona.getNombre() + " " + modeloPersona.getApellidos()%></option>
                                            <%
                                                }
                                            %>
                                        </select>
                                    </div>
                                </div>
                                        <%
                                                LinkedList<ModeloPoliza> listModeloPolizas;
                                                ControladorPoliza controladorPoliza = new ControladorPoliza();
                                                listModeloPolizas = controladorPoliza.Read();
                                        %>
                                <div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback">
                                    <label class="control-label col-md-3 col-sm-1 ">Filtro Tipo Poliza</label>
                                    <div class="col-md-9 col-sm-12 col-xs-12">
                                        <select class="form-control">
                                            <%
                                                for (ModeloPoliza modeloPoliza : listModeloPolizas)
                                                {
                                            %>
                                            <option value="<%=modeloPoliza.getId()%>"><%=modeloPersona.getNombre() + " " + modeloPersona.getApellidos()%></option>
                                            <%
                                                }
                                            %>
                                        </select>
                                    </div>
                                </div>                                                       
                                <br/>
                                <br/>
                                <br/>
                                <br/>
                                <br/>
                                <br/>
                                <div class="text-center">
                                    <div class="col-lg-12" style="text-align: center">
                                        <button type="button" class="btn btn-primary">Filtrar</button>                                        
                                    </div>                        
                                </div>                                
                                <br>
                                <br>
                                <br>
                                <br> 
                                <div class="x_panel">
                                    <table id="datatable" class="table table-striped table-bordered">                                  
                                        <thead>
                                            <tr>
                                                <th>Id</th>
                                                <th>Poliza</th>
                                                <th>Cedula Empleados</th>
                                                <th>Nombre Empleados</th>
                                                <th>Deduccion</th>
                                                <th>Pago</th>
                                                <th>Diferencia</th>
                                                <th>Dar de Baja</th>                                                
                                                <th></th>                                                
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <%
                                                ControladorResultados controladorResultados = new ControladorResultados();
                                                LinkedList<ModeloResultados> listResulados = controladorResultados.Read();
                                                for (ModeloResultados modeloResultado : listResulados)
                                                {
                                            %>
                                            <tr>
                                                <td WIDTH = "0" HEIGHT="0"><%=modeloResultado.getId()%></td>                                     
                                                <td WIDTH = "0" HEIGHT="0"><%=modeloResultado.getIdPersona_Poliza().getModeloPoliza().getNombre()%></td>                                     
                                                <td WIDTH = "0" HEIGHT="0"><%=modeloResultado.getIdPersona_Poliza().getModeloPersonas().getCedula()%></td>  
                                                <td WIDTH = "0" HEIGHT="0"><%=modeloResultado.getIdPersona_Poliza().getModeloPersonas().getNombre() + " " + modeloResultado.getIdPersona_Poliza().getModeloPersonas().getApellidos()%></td>  
                                                <td WIDTH = "0" HEIGHT="0"><%=modeloResultado.getDeduccion()%></td>  
                                                <td WIDTH = "0" HEIGHT="0"><%=modeloResultado.getPago()%></td>  
                                                <td WIDTH = "0" HEIGHT="0"><%=modeloResultado.getDiferencia()%></td>  
                                                <td WIDTH = "0" HEIGHT="0"><%=modeloResultado.getIdSaldos().getDebaja()%></td>  
                                                <td WIDTH = "10" HEIGHT="0" class="text-center">
                                                    <button type="button" class="btn btn-primary btn-xs" data-toggle="modal" data-target="#exampleModal"   
                                                            data-id="<%=modeloResultado.getId()%>"
                                                            data-idpersona="<%=modeloResultado.getIdPersona_Poliza().getModeloPersonas().getId()%>"
                                                            data-nombrepersona="<%=modeloResultado.getIdPersona_Poliza().getModeloPersonas().getNombre() + " " + modeloResultado.getIdPersona_Poliza().getModeloPersonas().getApellidos()%>"
                                                            data-cedula="<%=modeloResultado.getIdPersona_Poliza().getModeloPersonas().getCedula()%>"
                                                            data-idpoliza="<%=modeloResultado.getIdPersona_Poliza().getModeloPoliza().getId()%>"
                                                            data-codigopoliza="<%=modeloResultado.getIdPersona_Poliza().getModeloPoliza().getCodigo()%>"
                                                            data-nombrepoliza="<%=modeloResultado.getIdPersona_Poliza().getModeloPoliza().getNombre()%>"
                                                            data-deducion="<%=modeloResultado.getDeduccion()%>"
                                                            data-pago="<%=modeloResultado.getPago()%>"
                                                            data-diferencia="<%=modeloResultado.getDiferencia()%>"
                                                            data-debaja="<%=modeloResultado.getIdSaldos().getDebaja()%>"
                                                            >Editar</button>
                                                </td>                                                
                                            </tr>
                                            <%
                                                }
                                            %>
                                        </tbody>
                                    </table>
                                </div>
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
                                                <form class="form-horizontal form-label-left input_mask" action="ModalResultados" method="POST" name="ModalResultadosJSP" id="IdModalResultadosJSP" >
                                                    <div class="form-group">
                                                        <div class="col-md-0 col-sm-0 col-xs-12 form-group has-feedback">
                                                            <input type="hidden" class="form-control" id="IdModal" name="IdModal">
                                                        </div>
                                                        <div class="col-md-0 col-sm-0 col-xs-12 form-group has-feedback">
                                                            <input type="hidden" class="form-control " id="IdModalIdPersona" name="ModalIdPersona">
                                                        </div>
                                                        <div class="col-md-3 col-sm-3 col-xs-12 form-group has-feedback">
                                                            <input type="text" class="form-control " id="IdModalCedula" name="ModalCedula" placeholder="Cedula">
                                                        </div>
                                                        <div class="col-md-9 col-sm-9 col-xs-12 form-group has-feedback">
                                                            <input type="text" class="form-control has-feedback-left" id="IdModalNombrePersona" name="ModalNombrePersona" placeholder="Nombre Persona">
                                                            <span class="fa fa-user form-control-feedback left" aria-hidden="true"></span>
                                                        </div>                                                        
                                                        <div class="col-md-0 col-sm-0 col-xs-12 form-group has-feedback">
                                                            <input type="hidden" class="form-control " id="IdModalIdPoliza" name="ModalIdPoliza">
                                                        </div>
                                                        <div class="col-md-3 col-sm-3 col-xs-12 form-group has-feedback">
                                                            <input type="text" class="form-control " id="IdModalCodigoPoliza" name="ModalCodigoPoliza" placeholder="Codigo">
                                                        </div>
                                                        <div class="col-md-9 col-sm-9 col-xs-12 form-group has-feedback">
                                                            <input type="text" class="form-control has-feedback-left" id="IdModalNombrePoliza" name="ModalNombrePoliza" placeholder="Nombre" value="">
                                                            <span class="fa fa-user form-control-feedback left" aria-hidden="true"></span>
                                                        </div>
                                                        <div class="col-md-4 col-sm-4 col-xs-12 form-group has-feedback">
                                                            <input type="text" class="form-control has-feedback-left" id="IdModalDeduccion" name="ModalDeduccion" placeholder="Deduccion" value="">
                                                            <span class="fa fa-user form-control-feedback left" aria-hidden="true"></span>
                                                        </div>
                                                        <div class="col-md-4 col-sm-4 col-xs-12 form-group has-feedback">
                                                            <input type="text" class="form-control has-feedback-left" id="IdModalPago" name="ModalPago" placeholder="Pago" value="">
                                                            <span class="fa fa-user form-control-feedback left" aria-hidden="true"></span>
                                                        </div>
                                                        <div class="col-md-4 col-sm-4 col-xs-12 form-group has-feedback">
                                                            <input type="text" class="form-control has-feedback-left" id="IdModalDiferencia" name="ModalDiferencia" placeholder="Diferencia" value="">
                                                            <span class="fa fa-user form-control-feedback left" aria-hidden="true"></span>
                                                        </div>                                                        
                                                        <div class="col-md-0 col-sm-0 col-xs-12 form-group has-feedback">
                                                            <textarea class="resizable_textarea form-control" id="IdModalObservacion" name="ModalObservacion" placeholder="Observacion"></textarea>
                                                        </div>
                                                        <div class="col-md-0 col-sm-0 col-xs-12 form-group has-feedback">
                                                            <input type="text" class="form-control has-feedback-left" id="IdModalDeBaja" name="ModalDeBaja" placeholder="Dar de Baja" value="">
                                                            <span class="fa fa-user form-control-feedback left" aria-hidden="true"></span>
                                                        </div>
                                                    </div>
                                                </form>
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
                                                <button type="button" class="btn btn-success" id="IdUpdate" name="Guardar">Guardar</button>   
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
</html>