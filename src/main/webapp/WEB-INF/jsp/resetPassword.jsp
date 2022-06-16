<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!doctype html>
<html lang="it" class="h-100" >
	 <head>
	 
	 	<!-- Common imports in pages -->
	 	<jsp:include page="./header.jsp" />
	 	 <style>
		    .error_field {
		        color: red; 
		    }
		</style>
	   
	   <title>Inserisci Nuovo Elemento</title>
	 </head>
	   <body class="d-flex flex-column h-100">
	   
	   		<!-- Fixed navbar -->
	   		<jsp:include page="./navbar.jsp"></jsp:include>
	    
			
			<!-- Begin page content -->
			<main class="flex-shrink-0">
			  <div class="container">
			  
			  		
			  		<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none':'' }" role="alert">
					  ${errorMessage}
					  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
					</div>
			  
			  <div class='card'>
				    <div class='card-header'>
				        <h5>Cambia Password</h5> 
				    </div>
				    <div class='card-body'>
		
							<h6 class="card-title">I campi con <span class="text-danger">*</span> sono obbligatori</h6>
		
		
							<form:form   action="${pageContext.request.contextPath}/CustomPassword/ResetPasswordCustom" method="post" novalidate="novalidate" class="row g-3">
					
								
								<div class="col-md-6">
									<label for="nome" class="form-label">Vecchia Password <span class="text-danger">*</span></label>
										<input type="text" name="vecchiaPassword" id="vecchiaPassword" class="form-control ${status.error ? 'is-invalid' : ''}" placeholder="Inserire la vecchia password" required>
									<form:errors  path="errorMessage" cssClass="error_field" />
								</div>
								
								<div class="col-md-6">
									<label for="nome" class="form-label">Inserisci nuova Password <span class="text-danger">*</span></label>
										<input type="text" name="nuovaPassword" id="nuovaPassword" class="form-control ${status.error ? 'is-invalid' : ''}" placeholder="Inserire la nuova password" required>
									<form:errors  path="errorMessage" cssClass="error_field" />
								</div>
								
								
								<div class="col-md-6">
									<label for="nome" class="form-label">Conferma nuova Password <span class="text-danger">*</span></label>
										<input type="text" name="nuovaPasswordConferma" id="nuovaPasswordConferma" class="form-control ${status.error ? 'is-invalid' : ''}" placeholder="Conferma la nuova password" required>
									<form:errors  path="errorMessage" cssClass="error_field" />
								</div>
								
								
								<div class="col-12">
									<input type="hidden" name="idUtente" value="${utente_id_password.id}">
									<input type="submit" value="confrma">
									<input class="btn btn-outline-warning" type="reset" value="Ripulisci">
								</div>
		
						</form:form>
  
				    
				    
					<!-- end card-body -->			   
				    </div>
				<!-- end card -->
				</div>		
					  
			    
			  <!-- end container -->  
			  </div>
			  
			</main>
			
			<!-- Footer -->
			<jsp:include page="./footer.jsp" />
	  </body>
</html>