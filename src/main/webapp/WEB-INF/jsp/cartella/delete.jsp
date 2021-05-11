<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!doctype html>
<html lang="it">
<head>
	<jsp:include page="../header.jsp" />
	<title>Visualizza elemento da eliminare</title>
	
	<!-- style per le pagine diverse dalla index -->
    <link href="${pageContext.request.contextPath }/assets/css/global.css" rel="stylesheet">
    
</head>
<body>
	<jsp:include page="../navbar.jsp" />
	
	<main role="main" class="container">
		
		<div class='card'>
		    <div class='card-header'>
		        Visualizza dettaglio da eliminare
		    </div>
		
		    <div class='card-body'>
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Id:</dt>
				  <dd class="col-sm-9">${delete_cartella_attr.id}</dd>
		    	</dl>
		    	
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Titolo:</dt>
				  <dd class="col-sm-9">${delete_cartella_attr.descrizione}</dd>
		    	</dl>
		    	
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Genere:</dt>
				  <dd class="col-sm-9">${delete_cartella_attr.importo}</dd>
		    	</dl>
		    	
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Stato:</dt>
				  <dd class="col-sm-9">${delete_cartella_attr.statoCartella}</dd>
		    	</dl>
		    	
		    	<!-- info Contribuente -->
		    	<p>
				  <a class="btn btn-primary btn-sm" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
				    Info Contribuente
				  </a>
				</p>
				<div class="collapse" id="collapseExample">

				  <div class="card card-body">
				  	<dl class="row">
					  <dt class="col-sm-3 text-right">Nome:</dt>
					  <dd class="col-sm-9">${delete_cartella_attr.contribuente.nome}</dd>
				   	</dl>
				   	<dl class="row">
					  <dt class="col-sm-3 text-right">Cognome:</dt>
					  <dd class="col-sm-9">${delete_cartella_attr.contribuente.cognome}</dd>
				   	</dl>
				   	<dl class="row">
					  <dt class="col-sm-3 text-right">Indirizzo:</dt>
					  <dd class="col-sm-9">${delete_cartella_attr.contribuente.indirizzo}</dd>
				   	</dl>
					<dl class="row">
					  <dt class="col-sm-3 text-right">Codice Fiscale:</dt>
					  <dd class="col-sm-9">${delete_cartella_attr.contribuente.codFis}</dd>
					</dl>

				  </div>
				</div>
				<!-- end info Contribuente -->
		    	
		    </div>
		    
	    <div class='card-footer'>
            <form:form modelAttribute="delete_cartella_attr" method="post" action="executechangestato" novalidate="novalidate" >
                <spring:bind path="id">
                    <input type="hidden" name="id" id="id" class="form-control ${status.error ? 'is-invalid' : ''}" value="${delete_cartella_attr.id }" required>
                </spring:bind>
                <a href="${pageContext.request.contextPath }/cartella/" class='btn btn-outline-secondary' style='width:80px'>
                    <i class='fa fa-chevron-left'></i> Back
                </a>
                <c:choose>
				  <c:when test="${delete_cartella_attr.statoCartella == 'INVALIDATA'}">
                     <button type="submit" class='btn btn-outline-secondary' style='color: black;background-color: red'>Invalida</button>
                  </c:when>    
		          <c:otherwise>
                       <button type="submit" class='btn btn-outline-secondary' style='color: black;background-color: green'>Valida</button>
                       <br />
				  </c:otherwise>
			   </c:choose>
            </form:form>
        </div>
    </div>	
	
	
	
	<!-- end main container -->	
	</main>
	<jsp:include page="../footer.jsp" />
	
</body>
</html>