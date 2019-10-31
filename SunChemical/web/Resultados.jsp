<%-- 
    Document   : Resultados
    Created on : 04-jul-2019, 19:52:27
    Author     : Carlos A Dominguez D
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>        
        <%@include file="Principal/Head.html" %>        
    </head>
    <body class="nav-md">
        <%@include file="Principal/Body.html" %>
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
                            <form class="form-horizontal form-label-left input_mask">
                                <div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback">
                                    <label class="control-label col-md-3 col-sm-1 ">Filtro Empleado</label>
                                    <div class="col-md-9 col-sm-12 col-xs-12">
                                        <select class="form-control">
                                            <option>Choose option</option>
                                            <option>Option one</option>
                                            <option>Option two</option>
                                            <option>Option three</option>
                                            <option>Option four</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback">
                                    <label class="control-label col-md-3 col-sm-1 ">Filtro Tipo Poliza</label>
                                    <div class="col-md-9 col-sm-12 col-xs-12">
                                        <select class="form-control">
                                            <option>Choose option</option>
                                            <option>Option one</option>
                                            <option>Option two</option>
                                            <option>Option three</option>
                                            <option>Option four</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback">
                                    <label class="control-label col-md-3 col-sm-1 ">Filtro Poliza</label>
                                    <div class="col-md-9 col-sm-12 col-xs-12">
                                        <select class="form-control">
                                            <option>Choose option</option>
                                            <option>Option one</option>
                                            <option>Option two</option>
                                            <option>Option three</option>
                                            <option>Option four</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback">
                                    <label class="control-label col-md-3 col-sm-1 ">Filtro Fecha Inicio</label>
                                    <div class="col-md-9 col-sm-12 col-xs-12">
                                        <select class="form-control">
                                            <option>Choose option</option>
                                            <option>Option one</option>
                                            <option>Option two</option>
                                            <option>Option three</option>
                                            <option>Option four</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback">
                                    <label class="control-label col-md-3 col-sm-1 ">Filtro Fecha Fin</label>
                                    <div class="col-md-9 col-sm-12 col-xs-12">
                                        <select class="form-control">
                                            <option>Choose option</option>
                                            <option>Option one</option>
                                            <option>Option two</option>
                                            <option>Option three</option>
                                            <option>Option four</option>
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

                                    <table class="table" id="Tabla">                                  
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
                                            <tr>
                                                <td WIDTH = "0" HEIGHT="0">1</td>                                     
                                                <td WIDTH = "0" HEIGHT="0">Sura</td>                                     
                                                <td WIDTH = "0" HEIGHT="0">16928919</td>  
                                                <td WIDTH = "0" HEIGHT="0">Carlos A Dominguez D</td>  
                                                <td WIDTH = "0" HEIGHT="0">150.000</td>  
                                                <td WIDTH = "0" HEIGHT="0">150.000</td>  
                                                <td WIDTH = "0" HEIGHT="0">0</td>  
                                                <td WIDTH = "0" HEIGHT="0"></td>  
                                                <td WIDTH = "10" HEIGHT="0" class="text-center">
                                                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModalCenter">                                                    
                                                        Editar
                                                    </button>
                                                </td>                                                
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                                <div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                                    <div class="modal-dialog modal-dialog-centered" role="document">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title" id="exampleModalLongTitle">Modal title</h5>
                                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                    <span aria-hidden="true">&times;</span>
                                                </button>
                                            </div>
                                            <div class="modal-body">
                                                ...
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                                <button type="button" class="btn btn-primary">Save changes</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="text-center">
                                    <div class="col-lg-12" style="text-align: center">
                                        <button type="button" class="btn btn-primary">Exportar</button>                                        
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