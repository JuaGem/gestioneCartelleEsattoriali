<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!doctype html>
<html lang="it">
<head>
	<jsp:include page="../header.jsp" />
	<title>Inserisci nuovo</title>
	
	<!-- style per le pagine diverse dalla index -->
    <link href="${pageContext.request.contextPath }/assets/css/global.css" rel="stylesheet">
    <style>
	    .error_field {
	        color: red; 
	    }
	</style>
</head>
<body>
	<jsp:include page="../navbar.jsp" />
	
	<main role="main" class="container">
	
		<%-- se l'attributo in request ha errori --%>
		<spring:hasBindErrors  name="contribuente_insert_attribute">
			<%-- alert errori --%>
			<div class="alert alert-danger " role="alert">
				Attenzione!! Sono presenti errori di validazione
			</div>
		</spring:hasBindErrors>
	
		<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none': ''}" role="alert">
		  ${errorMessage}
		  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
		    <span aria-hidden="true">&times;</span>
		  </button>
		</div>
		
		<div class='card'>
		    <div class='card-header'>
		        <h5>Inserisci nuovo contribuente</h5> 
		    </div>
		    <div class='card-body'>
		    
		    		<h6 class="card-title">I campi con <span class="text-danger">*</span> sono obbligatori</h6>

					<form:form modelAttribute="contribuente_insert_attribute" method="post" action="save" novalidate="novalidate" >
					
						<div class="form-row">
							<div class="form-group col-md-6">
								<label>Nome <span class="text-danger">*</span></label>
								<spring:bind path="nome">
									<input type="text" name="nome" id="nome" class="form-control ${status.error ? 'is-invalid' : ''}" placeholder="Inserire il nome del contribuente" value="${contribuente_insert_attribute.nome }" required>
								</spring:bind>
								<form:errors  path="nome" cssClass="error_field" />
							</div>
							
							<div class="form-group col-md-6">
								<label>Cognome <span class="text-danger">*</span></label>
								<spring:bind path="cognome">
									<input type="text" name="cognome" id="cognome" class="form-control ${status.error ? 'is-invalid' : ''}" placeholder="Inserire il cognome del contribuente" value="${contribuente_insert_attribute.cognome }" required>
								</spring:bind>
								<form:errors  path="cognome" cssClass="error_field" />
							</div>
						</div>
						
						<div class="form-row">	
						
							<fmt:formatDate pattern='yyyy-MM-dd' var="parsedDate" type='date' value='${contribuente_insert_attribute.dataNascita}' />
							<div class="form-group col-md-3">
								<label>Data di Nascita <span class="text-danger">*</span></label>
                        		<spring:bind path="dataNascita">
	                        		<input class="form-control ${status.error ? 'is-invalid' : ''}" id="dataNascita" type="date" placeholder="dd/MM/yy"
	                            		title="formato : gg/mm/aaaa"  name="dataNascita" required 
	                            		value="${parsedDate}" >
	                            </spring:bind>
                            	<form:errors  path="dataNascita" cssClass="error_field" />
							</div>
						
							<div class="form-group col-md-6">
								<label>Codice Fiscale <span class="text-danger">*</span></label>
								<spring:bind path="codFis">
									<input type="text" class="form-control ${status.error ? 'is-invalid' : ''}" name="codFis" id="codFis" placeholder="Inserire il codice fiscale del contribuente" value="${contribuente_insert_attribute.codFis }" required>
								</spring:bind>
								<form:errors  path="codFis" cssClass="error_field" />
							</div>
							
							<div class="form-group col-md-3">
								<label for="indirizzo">Indirizzo <span class="text-danger">*</span></label>
							    <spring:bind path="indirizzo">
								    <input type="text" class="form-control ${status.error ? 'is-invalid' : ''}" name="indirizzo" id="indirizzo" placeholder="Inserire l'indirizzo del contribuente" value="${contribuente_insert_attribute.indirizzo }" required>
							    </spring:bind>
							    <form:errors  path="indirizzo" cssClass="error_field" />
							</div>
							
						</div>
							
						<button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary">Conferma</button>

					</form:form>
		    
			<!-- end card-body -->			   
		    </div>
		</div>	
	
	<!-- end container -->	
	</main>
	<jsp:include page="../footer.jsp" />
	
</body>
</html>