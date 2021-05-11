<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="it">
<head>
	<jsp:include page="../header.jsp" />
	<title>Pagina dei risultati</title>
	
	<!-- style per le pagine diverse dalla index -->
    <link href="${pageContext.request.contextPath }/assets/css/global.css" rel="stylesheet">
    
</head>
<body>
	<jsp:include page="../navbar.jsp" />
	
	<main role="main" class="container">
	
		<div class="alert alert-success alert-dismissible fade show ${successMessage==null?'d-none': ''}" role="alert">
		  ${successMessage}
		  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
		    <span aria-hidden="true">&times;</span>
		  </button>
		</div>
		<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none': ''}" role="alert">
		  ${errorMessage}
		  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
		    <span aria-hidden="true">&times;</span>
		  </button>
		</div>
		
		<div class='card'>
		    <div class='card-header'>
		        <h5>Lista dei risultati</h5> 
		    </div>
		    <div class='card-body'>
		    	<a class="btn btn-primary " href="${pageContext.request.contextPath }/cartella/insert">Add New</a>
		    	<a href="${pageContext.request.contextPath }/cartella/search" class='btn btn-outline-secondary' >
			            <i class='fa fa-chevron-left'></i> Torna alla Ricerca
			        </a>
		    
		        <div class='table-responsive'>
		            <table class='table table-striped ' >
		                <thead>
		                    <tr>
		                        <th>Descrizione</th>
		                        <th>Importo</th>
		                        <th>Stato Cartella</th>
		                        <th>Azioni</th>
		                    </tr>
		                </thead>
		                <tbody>
		                	<c:forEach items="${cartelle_list_attribute }" var="cartellaItem">
								<tr class=${cartellaItem.statoCartella == "IN_CONTENZIOSO" ? "table-danger":"" }>
									<td>${cartellaItem.descrizione }</td>
									<td>${cartellaItem.importo }</td>
									<td>${cartellaItem.statoCartella }</td>
									<td>
										<a class="btn  btn-sm btn-outline-secondary" href="${pageContext.request.contextPath }/cartella/show/${cartellaItem.id }">Visualizza</a>
										<a class="btn  btn-sm btn-outline-primary ml-2 mr-2" href="${pageContext.request.contextPath }/cartella/edit/${cartellaItem.id }">Edit</a>
										<c:if test = "${cartellaItem.statoCartella != 'INVALIDATA'}">
                                  			  <a id="changeStatoLink_#_${cartellaItem.id }" class="btn btn-outline-danger btn-sm link-for-modal" data-toggle="modal" data-target="#confirmDeleteModal">Elimina</a>
                                		</c:if>
                                		<c:if test = "${cartellaItem.statoCartella == 'INVALIDATA'}">
                                    		<p style="color: red; display: inline-block">Eliminata!</p>
                                		</c:if>
									</td>
								</tr>
							</c:forEach>
		                </tbody>
		            </table>
		        </div>
		   
			<!-- end card-body -->			   
		    </div>
		</div>	
	
	
	<!-- end container -->	
	</main>
	
			<div class="modal fade" id="confirmDeleteModal" tabindex="-1" role="dialog" aria-labelledby="confirmDeleteModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="confirmDeleteModalLabel">Conferma eliminazione</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                Confermi il cambio di stato della cartella esattoriale?
            </div>
            <form method="post" action="${pageContext.request.contextPath }/cartella/cambiastato" >
                <div class="modal-footer">
                    <input type="hidden" name="idCartellaEsattorialeForChangingStato" id="idCartellaEsattorialeForChangingStato">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Chiudi</button>
                    <input type="submit" value="Continua"  class="btn btn-primary">
                </div>
            </form>
        </div>
    </div>
</div>
<!-- end Modal -->
	<script type="text/javascript">
    	$(".link-for-modal").click(function(){
        	<!-- mi prendo il numero che poi sarà l'id. Il 18 è perché 'changeStatoLink#' è appunto lungo 18  -->
        	var callerId = $(this).attr('id').substring(18);
        	<!-- imposto nell'hidden del modal l'id da postare alla servlet -->
        	$('#idCartellaEsattorialeForChangingStato').val(callerId);
    	});
	</script>
	
	<jsp:include page="../footer.jsp" />
	
</body>
</html>