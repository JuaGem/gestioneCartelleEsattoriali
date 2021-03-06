<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!doctype html>
<html lang="it">
<head>
    <jsp:include page="../header.jsp" />
    <title>Elemento da eliminare</title>

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
                <dd class="col-sm-9">${delete_contribuente_attr.id}</dd>
            </dl>

            <dl class="row">
                <dt class="col-sm-3 text-right">Titolo:</dt>
                <dd class="col-sm-9">${delete_contribuente_attr.nome}</dd>
            </dl>

            <dl class="row">
                <dt class="col-sm-3 text-right">Genere:</dt>
                <dd class="col-sm-9">${delete_contribuente_attr.cognome}</dd>
            </dl>

            <dl class="row">
                <dt class="col-sm-3 text-right">Codice Fiscale:</dt>
                <dd class="col-sm-9">${delete_contribuente_attr.codFis}</dd>
            </dl>

            <dl class="row">
                <dt class="col-sm-3 text-right">Data di Nascita:</dt>
                <dd class="col-sm-9"><fmt:formatDate type = "date" value = "${delete_contribuente_attr.dataNascita}" /></dd>
            </dl>

          <dl class="row">
                <dt class="col-sm-3 text-right">Indirizzo:</dt>
                <dd class="col-sm-9">${delete_contribuente_attr.indirizzo}</dd>
            </dl>


        </div>


        <div class='card-footer'>
            <form:form modelAttribute="delete_contribuente_attr" method="post" action="executedelete" novalidate="novalidate" >

                <spring:bind path="id">
                    <input type="hidden" name="id" id="id" class="form-control ${status.error ? 'is-invalid' : ''}" value="${delete_contribuente_attr.id }" required>
                </spring:bind>

                <button type="submit" name="submit" value="submit" id="submit" class="btn btn-danger btn-sm-primary">Elimina</button>
                <a href="${pageContext.request.contextPath }/contribuente/" class='btn btn-outline-secondary' style='width:80px'>
                    <i class='fa fa-chevron-left'></i> Back
                </a>

            </form:form>
        </div>
    </div>


    <!-- end main container -->
</main>
<jsp:include page="../footer.jsp" />

</body>
</html>