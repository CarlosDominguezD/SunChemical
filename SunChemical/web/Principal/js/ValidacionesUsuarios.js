/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(function()
{
    
    $('#IdGuardar').click(function(e)
    {
        var InfoOK = ValidaDatos();
        if (InfoOK === "True")
        {
            $('#IdAccion').val('Guardar');
            alert('#IdAccion');
            var data = $('#IdUsuariosJSP').serialize();
            console.log(data);
            $.post("UsuariosServlet", data, function(res, est, jqHXR)
            {
                var dt = res;
                if (dt === "true")
                {
                    alert("Dato guardado");
                    location.href = "Usuarios.jsp";
                    
                    evt.preventDefault();
                }
                else
                {
                    alert("Error Guardar el empleado");
                }
            });
        }
        else
        {
            alert("Favor completar todos los campos")
        }

    });

    function  ValidaDatos()
    {
        var res = "False";
        if ($('#IdCodigo').val() !== "")
        {
            if ($('#IdNombre').val() !== "")
            {
                if ($('#IdUsuario').val() !== "")
                {
                    if ($('#IdPassword').val() !== "")
                    {
                        res = "True";
                    }
                    else
                    {
                        var res = "False";
                    }
                }
                else
                {
                    var res = "False";
                }
            }
            else
            {
                var res = "False";
            }
        }
        else
        {
            var res = "False";
        }
        return res;
    }

    function  ListarUser()
    {
        alert("hola carlos");
    }
});

