<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Portal</title>
<!-- DataTable -->
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <script src=" https://cdn.datatables.net/1.10.21/js/jquery.dataTables.min.js"></script>
    <script src="https://cdn.datatables.net/1.10.21/js/dataTables.bootstrap4.min.js"></script>
    <script src="https://cdn.datatables.net/responsive/2.2.5/js/dataTables.responsive.min.js"></script>
    <script src="https://cdn.datatables.net/responsive/2.2.5/js/responsive.bootstrap4.min.js"></script>
    <script src="https://cdn.datatables.net/plug-ins/1.10.21/dataRender/datetime.js"></script> 
    <script src="https://momentjs.com/downloads/moment-with-locales.min.js"></script> 
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.21/css/dataTables.bootstrap4.min.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/responsive/2.2.5/css/responsive.bootstrap4.min.css">
 <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    
</head>
<body class="bg-light">
    
     <nav class="navbar navbar-expand-md navbar-dark bg-primary fixed-top">
        <span class="navbar-brand" >Diario Electrónico</span>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault"
            aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
    </nav>


    <main role="main" class="container py-5">
    
        <div class="pt-5 text-center">
            <h1 class="display-4">Últimas publicaciones</h1>
             <a class="btn btn-outline-primary my-4 btn-lg" id="link1" href="${pageContext.request.contextPath}/cargarDatos">Cargar</a>
        </div>
                        
         <div class="row pt-4">
            <div class="col-12 bg-light">
                <div id="contenedor1">
                    <table id="tabla1" class="table table-sm display responsive" style="width:100%">
                        <thead class="thead-light">
                            <tr>
                                <th class="col-12" > <h5>Publicaciones</h5> </th>
                             
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="p" items="${posts}">
                                <tr>
                                    <td> 
                                     <div class="row">
				                        <div class="col-8 col-md-9 col-lg-10">
				                             <h6 class="font-weight-bold"> <c:out value="${p.getPost().getTitle()}" /></h6> 
                                            <span>Usuario:&nbsp;<c:out value="${p.getUser().getName()}" /></span>
				                        </div>
				                        <div class="col-4 col-md-3 col-lg-2 my-auto d-flex justify-content-center">
				                            <c:if test="${p.getN_comments() == 0}">
									            <button type="button" class="btn btn-primary btn-sm " disabled>
									            Comentarios <span class="badge badge-light"><c:out value="${p.getN_comments()}"/></span></button>
									        </c:if>
				                            <c:if test="${p.getN_comments() > 0}">
                                                <a class="link2 btn btn-primary btn-sm " href="${pageContext.request.contextPath}/comentarios/<c:out value="${p.getPost().getId()}"/>">
                                            Comentarios <span class="badge badge-light"><c:out value="${p.getN_comments()}"/></span></a> 
                                            </c:if>     
				                        </div>
				                    </div>                                                                 
                                    </td>
                                             
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        
        
     <!-- Modal -->
        <div class="modal fade" id="myModal" role="dialog">
            <div class="modal-dialog modal-lg modal-dialog-centered">
                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title"></h5>
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                    </div>
                    <div class="modal-body px-0">
                    </div>
                </div>
            </div>
        </div>
        

    
    </main>

<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<script type="text/javascript">

$('.link2').click(function (e) {
    e.preventDefault();
    $('.modal-title').html("Comentarios");
    $('.modal-body').load(this.href, function () {
        $('#myModal').modal({ show: true });
    });
});

$(document).ready(function () {

    $('#tabla1').DataTable({
    	"order": [],
        "columnDefs": [{targets: 0, orderable: false}],
        "language": { "url": "https://cdn.datatables.net/plug-ins/1.10.21/i18n/Spanish.json" }
    });

    $('#contenedor1').css("width", "100%");
    $('#contenedor1').css("margin", "0");
    $('#contenedor1').css("font-size", "0.9em");


});

</script>

</body>
</html>