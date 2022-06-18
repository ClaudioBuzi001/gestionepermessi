<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!doctype html>
<html lang="it" class="h-100">
<head>
	<jsp:include page="../header.jsp" />
	<title>Pagina dei risultati</title>
	
</head>
<body class="d-flex flex-column h-100">
	<jsp:include page="../navbar.jsp" />
	
	<!-- Begin page content -->
	<main class="flex-shrink-0">
	  <div class="container">
	
			<div class="alert alert-success alert-dismissible fade show  ${successMessage==null?'d-none':'' }" role="alert">
			  ${successMessage}
			  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
			</div>
			<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none':'' }" role="alert">
			  ${errorMessage}
			  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
			</div>
			
			<div class='card'>
			    <div class='card-header'>
			        <h5>Lista dei Dipendenti</h5> 
			    </div>
			    <div class='card-body'>
			    	<a href="${pageContext.request.contextPath}/dipendente/search" class='btn btn-outline-secondary' >
				            <i class='fa fa-chevron-left'></i> Torna alla Ricerca
				        </a>
				      <sec:authorize access="hasRole('BO_USER')">
				      <a href="${pageContext.request.contextPath}/dipendente/insert" class='btn btn-outline-secondary' >
				            <i class='fa fa-chevron-left'></i> Add New
				        </a>
				      </sec:authorize>
			    
			        <div class='table-responsive'>
			            <table class='table table-striped ' >
			                <thead>
			                    <tr>
			                        <th>Nome</th>
			                        <th>Cognome</th>
			                        <th>Email</th>
			                        <th>Codice Fiscale</th>
			                        <th>Data di Nascita</th>
			                        <th>Data di Assunzine</th>
			                        <th>Azioni</th>
			                    </tr>
			                </thead>
			                <tbody>
			                	<c:forEach items="${dipendente_list_attribute}" var="dipendenteItem">
									<tr>
										<td>${dipendenteItem.nome}</td>
										<td>${dipendenteItem.cognome }</td>
										<td>${dipendenteItem.email}</td>
										<td>${dipendenteItem.codiceFiscale}</td>
										<td><fmt:formatDate type = "date" value = "${dipendenteItem.dataNascita }" /></td>
										<td><fmt:formatDate type = "date" value = "${dipendenteItem.dataAssunzione }" /></td>
										<td>
											<a class="btn btn-sm btn-outline-secondary" href="${pageContext.request.contextPath}/dipendente/show/${dipendenteItem.id}">Visualizza</a>
											<sec:authorize access="hasRole('BO_USER')">
											<a class="btn btn-sm btn-outline-secondary" href="${pageContext.request.contextPath}/dipendente/edit/${dipendenteItem.id}">Modifica</a>
											</sec:authorize>
										</td>
									</tr>
								</c:forEach>
			                </tbody>
			            </table> 
			        </div>
			   
				<!-- end card-body -->			   
			    </div>
			</div>	
	
		</div>	
	<!-- end container -->	
	</main>
	<jsp:include page="../footer.jsp" />
	
</body>
</html>