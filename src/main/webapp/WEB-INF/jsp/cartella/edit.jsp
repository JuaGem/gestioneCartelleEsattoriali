<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!doctype html>
<html lang="it">
<head>
	<jsp:include page="../header.jsp" />
	<title>Modifica elemento</title>
	
	<!-- style per le pagine diverse dalla index -->
    <link href="${pageContext.request.contextPath }/assets/css/global.css" rel="stylesheet">
    
    <link rel="stylesheet" href="${pageContext.request.contextPath }/assets/css/jqueryUI/jquery-ui.min.css" />
	<style>
		.ui-autocomplete-loading {
			background: white url("../assets/img/jqueryUI/anim_16x16.gif") right center no-repeat;
		}
		.error_field {
	        color: red; 
	    }
	</style>
    
</head>
<body>
	<jsp:include page="../navbar.jsp" />
	
	<main role="main" class="container">
	
		<%-- se l'attributo in request ha errori --%>
		<spring:hasBindErrors  name="update_cartella_attr">
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
		        <h5>Modifica elemento</h5> 
		    </div>
		    <div class='card-body'>

					<form:form method="post" modelAttribute="update_cartella_attr" action="saveupdate" novalidate="novalidate" >
					
						<div class="form-row">
							<div class="form-group col-md-6">
								<label for="descrizione">Descrizione</label>
								<spring:bind path="descrizione">
									<input type="text" name="descrizione" id="descrizione" class="form-control ${status.error ? 'is-invalid' : ''}" placeholder="Inserire la descrizione" value="${update_cartella_attr.descrizione }" required>
								</spring:bind>
								<spring:bind path="id">
                           			 <input type="hidden" name="id" id="id" class="form-control${status.error ? 'is-invalid' : ''}" value="${update_cartella_attr.id }" required>
                        		</spring:bind>
								<form:errors  path="descrizione" cssClass="error_field" />
							</div>
							
							<div class="form-group col-md-6">
								<label for="importo">Importo</label>
								<spring:bind path="importo">
									<input type="number" class="form-control ${status.error ? 'is-invalid' : ''}" name="importo" id="importo" placeholder="Inserire l'importo" value="${update_cartella_attr.importo }" required>
								</spring:bind>
								<form:errors  path="importo" cssClass="error_field" />
							</div>
						</div>
						
						<div class="form-row">	
							
							<label for="statoCartella">Stato: </label>
							<spring:bind path="statoCartella">
								<select class="form-control ${status.error ? 'is-invalid' : ''}" id="statoCartella" name="statoCartella" required>
							      	<c:forEach items="${stati_list_attribute}" var="statoItem">
							      		<option value="${statoItem}"${update_cartella_attr.statoCartella == statoItem ? 'selected' : ''}>${statoItem}</option>
							      	</c:forEach>
					    		</select>
				    		</spring:bind>
							<form:errors  path="statoCartella" cssClass="error_field" />
							
  						<div class="form-group col-md-6">	
  								<label for="contribuenteSearchInput">Contribuente:</label>
  							<spring:bind path="contribuente">
  									<input class="form-control ${status.error ? 'is-invalid' : ''}" type="text" id="contribuenteSearchInput"
  										name="contribuenteInput" value="${update_cartella_attr.contribuente.nome}${empty update_cartella_attr.contribuente.nome?'':' '}${update_cartella_attr.contribuente.cognome}">
  								</spring:bind>
  								<input type="hidden" name="contribuente" id="contribuenteId" value="${update_cartella_attr.contribuente.id }">
  								<form:errors  path="contribuente" cssClass="error_field" />
	  					</div>
  						</div>
  						
<%-- 							      	<c:forEach items="${contribuente_list_attribute }" var="contribuenteItem"> --%>
<%-- 							      		<option value="${contribuenteItem.id}" ${update_cartella_attr.contribuente.id == contribuenteItem.id?'selected':''} >${contribuenteItem.nome } ${contribuenteItem.cognome }</option> --%>
<%-- 							      	</c:forEach> --%>
						
						<button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary">Modifica</button>
					</form:form>
					
					<%-- FUNZIONE JQUERY UI PER AUTOCOMPLETE --%>
				    <script>
						$("#contribuenteSearchInput").autocomplete({
							 source: function(request, response) {
							        $.ajax({
							            url: "../contribuente/searchContribuentiAjax",
							            datatype: "json",
							            data: {
							                term: request.term,   
							            },
							            success: function(data) {
							                response($.map(data, function(item) {
							                    return {
								                    label: item.label,
								                    value: item.value
							                    }
							                }))
							            }
							        })
							    },
							//quando seleziono la voce nel campo deve valorizzarsi la descrizione
						    focus: function(event, ui) {
						        $("#contribuenteSearchInput").val(ui.item.label)
						        return false
						    },
						    minLength: 2,
						    //quando seleziono la voce nel campo hidden deve valorizzarsi l'id
						    select: function( event, ui ) {
						    	$('#contribuenteId').val(ui.item.value);
						    	//console.log($('#registaId').val())
						        return false;
						    }
						});
					</script>
					<!-- end script autocomplete -->	
					
		    
			<!-- end card-body -->			   
		    </div>
		</div>	
	
	<!-- end container -->	
	</main>
	<jsp:include page="../footer.jsp" />
	
</body>
</html>