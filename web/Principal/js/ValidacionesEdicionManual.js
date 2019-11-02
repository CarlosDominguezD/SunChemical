$(function () {
    $('#IdConsultarPoliza').click(function (e)
    {
        var Accion = "ConsultarPoliza";
        var IdEmpleado = $('#IdPersona').val();
        var data = {
            accion: Accion,
            idempleado: IdEmpleado
        };
        //enableGif();
        $.ajax({
            type: "POST",
            url: "ServletSunchemical",
            data: data,
            success: function (resul, textStatus, jqXHR)
            {
                //disableGif();
                //alert(resul);
                $('.selector-poliza select').html(resul).fadeIn();
                //LimpiarCampos();                
            },
            error: function (jqXHR, textStatus, errorThrown) {
                disableGif();
                if (jqXHR.status === 0) {
                    alert('Not connect: Verify Network.');
                } else if (jqXHR.status === 404) {
                    alert('Requested page not found [404]');
                } else if (jqXHR.status === 500) {
                    alert('Internal Server Error [500].');
                } else if (textStatus === 'parsererror') {
                    alert('Requested JSON parse failed.');
                } else if (textStatus === 'timeout') {
                    alert('Time out error.');
                } else if (textStatus === 'abort') {
                    alert('Ajax request aborted.');
                } else {
                    alert('Uncaught Error: ' + jqXHR.responseText);
                }
            }
        });
    });
    $('#IdGuardar').click(function (e)
    {
        var Accion = "Update";
        var IdEmpleado = $('#IdPersona').val();
        var IdPoliza = $('#IdPoliza').val();
        var IdMes = $('#IdMes').val();
        var IdAno = $('#IdAnoConsultar').val();
        var IdPagoPoliza = $('#IdPagoPoliza').val();
        var IdDeducionPoliza = $('#IdDeducionPoliza').val();
        var IdObservacion = $('#IdObservacion').val();
        var data = {
            accion: Accion,
            idempleado: IdEmpleado,
            idpoliza: IdPoliza,
            idmes: IdMes,
            idano: IdAno,
            idpagopoliza: IdPagoPoliza,
            iddeducionpoliza: IdDeducionPoliza,
            idobservacion: IdObservacion
        };
        //enableGif();
        $.ajax({
            type: "POST",
            url: "ServletSunchemical",
            data: data,
            success: function (resul, textStatus, jqXHR)
            {
                //disableGif();
                alert(resul);
                LimpiarCampos();
                LoadTabla()
            },
            error: function (jqXHR, textStatus, errorThrown) {
                disableGif();
                if (jqXHR.status === 0) {
                    alert('Not connect: Verify Network.');
                } else if (jqXHR.status === 404) {
                    alert('Requested page not found [404]');
                } else if (jqXHR.status === 500) {
                    alert('Internal Server Error [500].');
                } else if (textStatus === 'parsererror') {
                    alert('Requested JSON parse failed.');
                } else if (textStatus === 'timeout') {
                    alert('Time out error.');
                } else if (textStatus === 'abort') {
                    alert('Ajax request aborted.');
                } else {
                    alert('Uncaught Error: ' + jqXHR.responseText);
                }
            }
        });
    });
    $('#IdEliminarPeriodo').click(function (e)
    {
        var Accion = "EliminarPeriodo";
        var Mes = $('#IdMes').val();
        var Ano = $('#IdAnoConsultar').val();
        var data = {
            accion: Accion,
            mes: Mes,
            ano: Ano
        };
        //enableGif();
        $.ajax({
            type: "POST",
            url: "ServletSunchemical",
            data: data,
            success: function (resul, textStatus, jqXHR)
            {
                //disableGif();
                alert(resul);
                LimpiarCampos();
                LoadTabla();
            },
            error: function (jqXHR, textStatus, errorThrown) {
                disableGif();
                if (jqXHR.status === 0) {
                    alert('Not connect: Verify Network.');
                } else if (jqXHR.status === 404) {
                    alert('Requested page not found [404]');
                } else if (jqXHR.status === 500) {
                    alert('Internal Server Error [500].');
                } else if (textStatus === 'parsererror') {
                    alert('Requested JSON parse failed.');
                } else if (textStatus === 'timeout') {
                    alert('Time out error.');
                } else if (textStatus === 'abort') {
                    alert('Ajax request aborted.');
                } else {
                    alert('Uncaught Error: ' + jqXHR.responseText);
                }
            }
        });
    });
    function enableGif()
    {
        window.onload = document.getElementById("espera").style = "display: block";
        window.onload = document.getElementById("Principal").style = "display: none"
    }
    function disableGif()
    {
        window.onload = document.getElementById("espera").style = "display: none";
        window.onload = document.getElementById("Principal").style = "display: enable"
    }
});