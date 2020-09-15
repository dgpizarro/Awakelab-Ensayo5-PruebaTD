<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Comentarios</title>
 <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body> 
           
        <div class="container-fluid col-12">
             
		      <div class="list-group">
		      <c:forEach var="c" items="${com}">
				  <div class="list-group-item list-group-item-action flex-column align-items-start">
				    <div class="d-flex w-100 justify-content-between">
				      <h5 class="mb-1 text-primary"><c:out value="${c.getName()}" /></h5>
				      <small>ID: <c:out value="${c.getId()}" /></small>
				    </div>
				    <p class="mb-1"><c:out value="${c.getBody()}" /></p>
				    <small><c:out value="${c.getEmail()}" /></small>
				  </div>
			  </c:forEach>
            </div>
            
        </div>

</body>
</html>