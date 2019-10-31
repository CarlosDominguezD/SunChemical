<%-- 
    Document   : Pruebas
    Created on : 04-jul-2019, 19:50:06
    Author     : Carlos A Dominguez D
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">

        <script>
            window.onload = function() {

                // creamos los eventos para cada elemento con la clase "boton"
                let elementos = document.getElementsByClassName("boton");
                        for (let i = 0; i < elementos.length;
                        i++
                        )
                {

                    // cada vez que se haga clic sobre cualquier de los elementos,
                    // ejecutamos la función obtenerValores()
                    elementos[i].addEventListener("click", obtenerValores);
                }
            }

            // funcion que se ejecuta cada vez que se hace clic
            function obtenerValores(e) {
                var valores = "";

                // vamos al elemento padre (<tr>) y buscamos todos los elementos <td>
                // que contenga el elemento padre
                var elementosTD = e.srcElement.parentElement.getElementsByTagName("td");

                // recorremos cada uno de los elementos del array de elementos <td>
                for (let i = 0; i < elementosTD.length;
                i++
                )
                {

                    // obtenemos cada uno de los valores y los ponemos en la variable "valores"
                    valores += elementosTD[i].innerHTML + "\n";
                }

                alert(valores);
            }
        </script>

        <style>
            .boton {border:1px solid #808080;cursor:pointer;padding:2px 5px;color:Blue;}
        </style>
    </head>

    <body>
        <h2>My First JavaScript</h2>
        <!--
        la idea principal es traer los datos de una fila, yo lo hago de la siguiente forma en la parte de html de boton le traigo un ejemplo 
        -->
        <button class="massshow-modal btn btn-info" 
                data-id="34"
                data-nombre="marcos"
                data-apellido="alberto"

                ><span class="glyphicon glyphicon-edit"></span> Editar</button>

        <button class="massshow-modal btn btn-info" 
                data-id="31"
                data-nombre="marcos_3"
                data-apellido="alberto_4"

                ><span class="glyphicon glyphicon-edit"></span> Editar</button>

    <script type="text/javascript" src="https://code.jquery.com/jquery-3.3.1.min.js"></script>


    <input type='text' class='form-control' id='id_mass' maxlength='45'   required='required' autofocus>
    <input type='text' class='form-control' id='nombre_mass' maxlength='45'   required='required' autofocus>
    <input type='text' class='form-control' id='apellido_mass' maxlength='45'   required='required' autofocus>

    <script type='text/javascript'>

            // Show a post
            $(document).on('click', '.massshow-modal', function() {
                //$('.modal-descripcion').text('Vista de los Datos');
                //$('#msdelete').text(' ');
                $('#id_mass').val($(this).data('id'));
                $('#nombre_mass').val($(this).data('nombre'));
                $('#apellido_mass').val($(this).data('apellido'));

                //$('#massModal').modal('show');
                //$('#acciones').attr('class', 'btn btn-info hibe');
                //$('#acciones').text('Visible');
                //$('#acciones').attr('disabled');

            });
    </script>


</body>
</html> 