/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(function()
{
    $('#IdIngresar').click(function (e)
    {
//        var  user = $('#Idusuario').val();
//        var  password = $('#Idpassword').val();
        if ($('#Idusuario').val() !=="")
        {
            if ($('#Idpassword').val() !== "")
            {
                var data = $('#IdMainJSP').serialize();
                console.log(data);                                     
                $.post("Main",data,function (res, est,jqHXR)
                {   
                    var dt = res;                       
                    if(dt === "true")
                    {
                        location.href = "dashboard.jsp";
                        evt.preventDefault();
                    }
                    else
                    {
                        alert("Por favor verifique el usuario y la contraseña");  
                    }
                });
            }
            else
            {
                alert("Ingrese el Password");       
            }
        }
        else
        {
            alert("Ingrese el Usuario");      
        }
    });
});
