<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!doctype html>
<html lang="it" class="h-100" >
	 <head>

	 	<!-- Common imports in pages -->
	 	<jsp:include page="../header.jsp" />
	 	
	   <title>Visualizza Elemento</title>
	   
	 </head>
	   <body class="d-flex flex-column h-100">
	   
	   		<!-- Fixed navbar -->
	   		<jsp:include page="../navbar.jsp"></jsp:include>
	    
			
			<!-- Begin page content -->
			<main class="flex-shrink-0">
			  <div class="container">
			  
			  		<div class='card'>
					    <div class='card-header'>
					        <h5>Visualizza Richiesta permesso</h5>
					    </div>
					    
					
					    <div class='card-body'>
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Id:</dt>
							  <dd class="col-sm-9">${show_richiesta_attr.id}</dd>
					    	</dl>
					    	
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Tipo Permesso:</dt>
							  <dd class="col-sm-9">${show_richiesta_attr.tipoPermesso}</dd>
					    	</dl>
					    	
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Data Inizio:</dt>
							  <dd class="col-sm-9"><fmt:formatDate type = "date" value = "${show_richiesta_attr.dataInizio}" /></dd>
					    	</dl>
					    	
					    	<c:if test="${show_richiesta_attr.dataFine != null }">
						    	<dl class="row">
								  <dt class="col-sm-3 text-right">Data Fine:</dt>
								  <dd class="col-sm-9"><fmt:formatDate type = "date" value = "${show_richiesta_attr.dataFine}" /></dd>
						    	</dl>
					    	</c:if>
					    	
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Approvato:</dt>
							  <dd class="col-sm-9">${show_richiesta_attr.approvato}</dd>
					    	</dl>
					    	
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Note:</dt>
							  <dd class="col-sm-9">${show_richiesta_attr.note}</dd>
					    	</dl>
					    	
					    	<c:if test="${show_richiesta_attr.tipoPermesso == 'MALATTIA' }">
						    	<dl class="row">
								  <dt class="col-sm-3 text-right">Codice Certificato:</dt>
								  <dd class="col-sm-9">${show_richiesta_attr.codiceCertificato}</dd>
						    	</dl>
						    	<dl class="row">
								  <dt class="col-sm-3 text-right">Allegato:</dt>
								  <dd class="col-sm-9">${show_richiesta_attr.attachment}</dd>
						    	</dl>
					    	</c:if>
					    	
					    	<!-- info Dipendente, Solo quando si ? in BO -->
					    	<sec:authorize access="hasRole('BO_USER')">
						    	<p>
								  <a class="btn btn-primary btn-sm" data-bs-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
								    Info Dipendente
								  </a>
								</p>
								<div class="collapse" id="collapseExample">
								  <div class="card card-body">
								  	<dl class="row">
									  <dt class="col-sm-3 text-right">Nome</dt>
									  <dd class="col-sm-9">${show_richiesta_attr.dipendente.nome}</dd>
									  <dt class="col-sm-3 text-right">Cognome</dt>
									  <dd class="col-sm-9">${show_richiesta_attr.dipendente.cognome}</dd>
									  <dt class="col-sm-3 text-right">Codice Fiscale</dt>
									  <dd class="col-sm-9">${show_richiesta_attr.dipendente.codiceFiscale}</dd>
								   	</dl>
								  </div>
								<!-- end info Regista -->
								</div>
							</sec:authorize>
					    	
					    <!-- end card body -->
					    </div>
					    
					    <div class='card-footer'>
					        <a href="${pageContext.request.contextPath}/richiestaPermesso" class='btn btn-outline-secondary' style='width:80px'>
					            <i class='fa fa-chevron-left'></i> Back
					        </a>
					    </div>
					<!-- end card -->
					</div>	
			  
			    
			  <!-- end container -->  
			  </div>
			  
			</main>
			
			<!-- Footer -->
			<jsp:include page="../footer.jsp" />
	  </body>
</html>