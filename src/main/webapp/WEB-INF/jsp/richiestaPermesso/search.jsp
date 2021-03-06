<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!doctype html>
<html lang="it" class="h-100" >
<head>
	<jsp:include page="../header.jsp" />
	<title>Ricerca</title>
	
    
</head>
<body class="d-flex flex-column h-100">
	<!-- Fixed navbar -->
	<jsp:include page="../navbar.jsp"></jsp:include>
	
	<!-- Begin page content -->
	<main class="flex-shrink-0">
	  <div class="container">
	
			<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none': ''}" role="alert">
			  ${errorMessage}
			  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
			    <span aria-hidden="true">&times;</span>
			  </button>
			</div>
			
			<div class='card'>
			    <div class='card-header'>
			        <h5>Ricerca Richieste Permesso</h5> 
			    </div>
			    <div class='card-body'>
	
						<form method="post" action="${pageContext.request.contextPath}/richiestaPermesso/list" class="row g-3">
						
							<div class="col-md-12">
									<label for="tipoPermesso" class="form-label">Tipo Permesso</label>
									    <select class="form-select ${status.error ? 'is-invalid' : ''}" id="tipoPermesso" name="tipoPermesso" required>
									    	<option value="" selected> - Selezionare - </option>
									      	<option value="FERIE" >FERIE</option>
									      	<option value="MALATTIA" >MALATTIA</option>
									    </select>
								</div>
								
								<div class="col-md-6">
								<label for="nome" class="form-label">Codice Certificato</label>
								<input type="text" name="nome" id="nome" class="form-control" placeholder="Inserire il nome" >
							</div>
							
								
								
								<div class="col-md-3">
									<label for="dataInizio" class="form-label">Data di Inizio </label>
                        			<input class="form-control" id="dataInizio" type="date" placeholder="dd/MM/yy"
                            			title="formato : gg/mm/aaaa"  name="dataInizio">
							</div>
								
								<div class="col-md-3">
									<label for="dataFine" class="form-label">Data di Fine </label>
                        			<input class="form-control" id="dataFine" type="date" placeholder="dd/MM/yy"
                            			title="formato : gg/mm/aaaa"  name="dataFine">
							</div>
								
			
								<div >
									<label for="note" class="form-label">Note</label>
										<textarea class="form-control rounded-0" id="note" rows="3" name="note"></textarea>
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