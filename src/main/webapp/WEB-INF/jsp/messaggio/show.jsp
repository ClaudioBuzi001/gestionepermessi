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
					        <h5>Visualizza Messaggio</h5>
					    </div>
					    
					
					    <div class='card-body'>
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Id:</dt>
							  <dd class="col-sm-9">${show_messaggio_attr.id}</dd>
					    	</dl>
					    	
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Oggetto:</dt>
							  <dd class="col-sm-9">${show_messaggio_attr.oggetto}</dd>
					    	</dl>
					    	
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Testo:</dt>
							  <dd class="col-sm-9">${show_messaggio_attr.testo}</dd>
					    	</dl>
					    	
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Data Inserimento:</dt>
							  <dd class="col-sm-9"><fmt:formatDate type = "date" value = "${show_messaggio_attr.dataInserimento}" /></dd>
					    	</dl>
					    	
					    	
					    	<p>
								  <a class="btn btn-primary btn-sm" data-bs-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
								    Info Richiesta Di Permesso
								  </a>
								</p>
								<div class="collapse" id="collapseExample">
								  <div class="card card-body">
								  	<dl class="row">
									  <dt class="col-sm-3 text-right">Tipo Permesso:</dt>
							  		  <dd class="col-sm-9">${show_messaggio_attr.richiestaPermesso.tipoPermesso}</dd>
									  <dt class="col-sm-3 text-right">Data Inizio:</dt>
							  		  <dd class="col-sm-9"><fmt:formatDate type = "date" value = "${show_messaggio_attr.richiestaPermesso.dataInizio}" /></dd>
									  <dt class="col-sm-3 text-right">Approvato:</dt>
							 		  <dd class="col-sm-9">${show_messaggio_attr.richiestaPermesso.approvato}</dd>
							 		  <dt class="col-sm-3 text-right">Nome Dipendente:</dt>
									  <dd class="col-sm-9">${show_messaggio_attr.richiestaPermesso.dipendente.nome}</dd>
									  <dt class="col-sm-3 text-right">Cognome:</dt>
									  <dd class="col-sm-9">${show_messaggio_attr.richiestaPermesso.dipendente.cognome}</dd>
								   	</dl>
								  </div>
								<!-- end info Regista -->
								</div>
					    	
					    </div>
					    
					    <div class='card-footer'>
					        <a href="${pageContext.request.contextPath}/messaggio" class='btn btn-outline-secondary' style='width:80px'>
					            <i class='fa fa-chevron-left'></i> Back
					        </a>
					        <a href="${pageContext.request.contextPath}/richiestaPermesso/show/${show_messaggio_attr.richiestaPermesso.id}" class='btn btn-outline-secondary' style='width:120px'>
					            <i class='fa fa-chevron-left'></i> Visualizza Richiesta di ${show_messaggio_attr.richiestaPermesso.dipendente.nome}
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