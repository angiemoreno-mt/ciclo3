$(document).ready(function(){
    traerInformacionReservas();
    itemSkate();
    itemClient();
    $("#update").hide()

})

function itemSkate(){
    $.ajax({
        url:"http://144.22.57.76:8080/api/Skate/all",
        type:"GET",
        datatype:"JSON",
        success:function(respuesta){
            let mySelect="<select id='skate' class='form-control mb-3'>";
            mySelect+="<option value='null' id='select-skate'>Selecione una patineta</option>"
            for(i=0;i<respuesta.length;i++){
                mySelect+="<option value="+respuesta[i].id+" id='skate-"+respuesta[i].id+"'>"+ respuesta[i].name+"</option>";
            }
            mySelect+="</select>"
            $("#resultado_Skate").html(mySelect); 
        }
    });
}

function itemClient(){
    $.ajax({
        url:"http://144.22.57.76:8080/api/Client/all",
        type:"GET",
        datatype:"JSON",
        success:function(respuesta){
            let mySelect="<select id='client' class='form-control mb-3'>";
            mySelect+="<option value='null' id='select-client'>Selecione un usuario</option>"
            for(i=0;i<respuesta.length;i++){
                mySelect+="<option value="+respuesta[i].idClient+" id='client-"+respuesta[i].idClient+"'>"+ respuesta[i].name+"</option>";
                
            }
            mySelect+="</select>"
            $("#resultado_Cliente").html(mySelect);
        }
    });
}

function traerInformacionReservas(){
    var objFecha = new Date;
    var dia  = objFecha.getDate();
    var mes  = objFecha.getMonth()+1;
    var ano = objFecha.getFullYear();
    // Devuelve: '1/2/2011':
    //console.log( dia + "/" + mes + "/" + anio );
    $("#startDate").attr("min",ano+"-"+mes+"-"+dia)
    $("#devolutionDate").attr("min",ano+"-"+mes+"-"+dia)
    
    $.ajax({
        url:"http://144.22.57.76:8080/api/Reservation/all",
        type:"GET",
        datatype:"JSON",
        success:function(respuesta){
            console.log(respuesta);
            pintarRespuestaReservas(respuesta);
        }
    });
}

function pintarRespuestaReservas(respuesta){
    let myTable="<table><thead><tr><th>Cliente</th><th>Skate</th><th>Fecha Entrada</th><th>Fecha Devolucion</th><th>Estado Reserva</th><th>Acciones</th></tr></thead>";
    for(i=0;i<respuesta.length;i++){
        myTable+="<tr>";
        myTable+="<td>"+respuesta[i].client.name+"</td>";
        myTable+="<td>"+respuesta[i].skate.name+"</td>";
        myTable+="<td>"+respuesta[i].startDate+"</td>";
        myTable+="<td>"+respuesta[i].devolutionDate+"</td>";
        myTable+="<td>"+respuesta[i].status+"</td>";
        myTable+='<td><a class="btn btn-danger" onclick="borrarInformacionReservas('+respuesta[i].idReservation+')" style="margin: 5px"><i class="las la-trash-alt"></i></a>'+'<a class="btn btn-success" onclick="editarInformacionReservas('+respuesta[i].idReservation+')" style="margin: 5px"><i class="las la-edit"></i></a></td>';
        myTable+="</tr>";
    }
    myTable+="</table>";
    $("#resultadoReservaciones").html(myTable);
}

function guardarInformacionReservas(){
    let var2 = {
        startDate:$("#startDate").val(),
        devolutionDate:$("#devolutionDate").val(),
        skate:{"id":$("#skate").val()},
        client:{"idClient":$("#client").val()},
        status:$("#status").val()
    }

    $.ajax({
        type:'POST',
        contentType: "application/json; charset=utf-8",
        dataType: 'JSON',
        data: JSON.stringify(var2),
        url:"http://144.22.57.76:8080/api/Reservation/save",
    
        success:function(response) {
            console.log(response);
            console.log("Se guardo correctamente");
            alert("Se guardo correctamente");
            window.location.reload()
        },
    
        error: function(jqXHR, textStatus, errorThrown) {
            window.location.reload()
            alert("No se guardo correctamente");
        }
    });
}

function editarInformacionReservas(idElemento){
    $.ajax({
        url:"http://144.22.57.76:8080/api/Reservation/"+idElemento,
        type:"GET",
        datatype:"JSON",
        success:function(respuesta){
            var fechaStartDate = "";
            var fechaDevolutionDate = "";
            for(i = 0; i < respuesta.startDate.length; i++){
                if(respuesta.startDate[i]=="T"){
                    break;
                }else{
                    fechaStartDate += respuesta.startDate[i];
                }
            }

            for(i = 0; i < respuesta.devolutionDate.length; i++){
                if(respuesta.devolutionDate[i]=="T"){
                    break;
                }else{
                    fechaDevolutionDate += respuesta.devolutionDate[i];
                }
            }

            $("#id").val(respuesta.idReservation),
            $("#client-"+respuesta.client.idClient).attr("selected",true),
            $("#startDate").val(fechaStartDate),
            $("#devolutionDate").val(fechaDevolutionDate),
            $("#skate-"+respuesta.skate.id).attr("selected",true),
            $("#"+respuesta.status).attr("selected",true)
            $("#save").hide()
            $("#update").show()
            $("#client").hide()
            $("#skate").hide()
        }
    });
}

function InformacionReservas(){
    let var2 = {
        idReservation:$("#id").val(),
        startDate:$("#startDate").val(),
        devolutionDate:$("#devolutionDate").val(),
        skate:{"id":$("#skate").val()},
        client:{"idClient":$("#client").val()},
        status:$("#status").val()
    }
    console.log(var2);
    $.ajax({
        type:'PUT',
        contentType: "application/json; charset=utf-8",
        dataType: 'JSON',
        data: JSON.stringify(var2),
        url:"http://144.22.57.76:8080/api/Reservation/update",
    
        success:function(response) {
            console.log(response);
            console.log("Se edito correctamente");
            alert("Se edito correctamente");
            window.location.reload()
        },
    
        error: function(jqXHR, textStatus, errorThrown) {
            window.location.reload()
            alert("No se edito correctamente");
        }
    });
}

function borrarInformacionReservas (idElemnto){
    var elemento={
        id:idElemnto
    };

    $.ajax({
        type:'DELETE',
        contentType: "application/json; charset=utf-8",
        dataType: 'JSON',
        data:JSON.stringify(elemento),
        url:"http://144.22.57.76:8080/api/Reservation/"+idElemnto,
        success:function(response){
            console.log(response);
            console.log("Se borro correctamente");
            alert("Se borro correctamente");
            window.location.reload()
        },

        error: function(jqXHR, textStatus, errorThrown) {
            window.location.reload()
            alert("No se borro correctamente");
        }
    })
}