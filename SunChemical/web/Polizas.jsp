<%-- 
    Document   : Polizas
    Created on : 04-jul-2019, 19:50:06
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
                                    <input type="text" class="form-control has-feedback-left" id="inputSuccess2" placeholder="Codigo">
                                    <span class="fa fa-user form-control-feedback left" aria-hidden="true"></span>
                                </div>
                                <div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback">
                                    <input type="text" class="form-control" id="inputSuccess3" placeholder="Nombre">
                                    <span class="fa fa-user form-control-feedback right" aria-hidden="true"></span>
                                </div>    
                                <br/>
                                <br/>
                                <br/>
                                <br/>
                                <br/>
                                <br/>
                                <div class="text-center">
                                    <div class="col-lg-12" style="text-align: center">
                                        <button type="button" class="btn btn-primary">Nuevo</button>
                                        <button class="btn btn-success" type="reset">Guardar</button>
                                        <button type="submit" class="btn btn-primary">Buscar</button>
                                        <button type="submit" class="btn btn-primary">Cancelar</button>
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
                                                <th>Codigo</th>
                                                <th>Nombre</th>                                                 
                                                <th></th>
                                                <th></th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td WIDTH = "0" HEIGHT="0">1</td>                                     
                                                <td WIDTH = "0" HEIGHT="0">16928919</td>                                     
                                                <td WIDTH = "0" HEIGHT="0">Seguro</td>                                                
                                                <td WIDTH = "10" HEIGHT="0" class="text-center">
                                                    <div class="btn btn-dark" id="btnEliminar">Modificar</div>                                         
                                                </td>
                                                <td WIDTH = "10" HEIGHT="0" class="text-center">
                                                    <div class="btn btn-danger" id="btnEliminar">Eliminar</div>                                         
                                                </td>
                                            </tr>
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
    </body>
    <%@include file="Principal/Validaciones.html" %>        
</html>