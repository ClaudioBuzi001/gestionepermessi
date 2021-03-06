<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!doctype html>
<html lang="it" class="h-100" >
<head>
	<jsp:include page="../header.jsp" />
	<style>
		    .error_field {
		        color: red; 
		    }
		</style>
	<title>Ricerca</title>
	
    
</head>
<body class="d-flex flex-column h-100">
	<!-- Fixed navbar -->
	<jsp:include page="../navbar.jsp"></jsp:include>
	
	<!-- Begin page content -->
	<main class="flex-shrink-0">
	  <div class="container">
	  
	  	<%-- se l'attributo in request ha errori --%>
					<spring:hasBindErrors  name="search_utente_attr">
						<%-- alert errori --%>
						<div class="alert alert-danger " role="alert">
							Attenzione!! Sono presenti errori di validazione
						</div>
					</spring:hasBindErrors>
			  
			  		<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none':'' }" role="alert">
					  ${errorMessage}
					  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
					</div>
	
			<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none': ''}" role="alert">
			  ${errorMessage}
			  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
			    <span aria-hidden="true">&times;</span>
			  </button>
			</div>
			
			<div class='card'>
			    <div class='card-header'>
			        <h5>Ricerca Dipendenti</h5> 
			    </div>
			    <div class='card-body'>
	
						<form  method="post" action="${pageContext.request.contextPath}/messaggio/list" class="row g-3">
						
							<div class="col-md-6">
								<label for="nome" class="form-label">Testo</label>
								<input type="text" name="testo" id="testo" class="form-control" placeholder="Inserire il testo" >
							</div>
							
							<div class="col-md-6">
								<label for="cognome" class="form-label">Oggetto</label>
								<input type="text" name="oggetto" id="oggetto" class="form-control" placeholder="Inserire L' oggetto" >
							</div>
							
							<div class="col-md-3">
									<label for="dataInserimento" class="form-label">Data di Inserimento</label>
                        			<input class="form-control" id="dataInserimento" type="date" placeholder="dd/MM/yy"
                            			title="formato : gg/mm/aaaa"  name="dataInserimento">
							</div>
							
							<div class="col-12">	
								<button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary">Conferma</button>
								<input class="btn btn-outline-warning" type="reset" value="Ripulisci">

							</div>
	
							
						</form>
			    
				<!-- end card-body -->			   
			    </div>
			</div>	
	
		</div>
	<!-- end container -->	
	</main>
	<jsp:include page="../footer.jsp" />
	
</body>
</html>