<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ZPareo - Liste des �tudiants</title>
        <link type="text/css" rel="stylesheet" href="<c:url value="/assets/scss/style.css"/>"/>
    </head>
    <body>
        <div id="site-wrap">
            <div id="aside-wrap">
                <div class="aside__user-info">
                    <span class="aside__user-info__picture"></span>
                    <span class="aside__user-info__name"><c:out value="${fn:toUpperCase(sessionScope.sessionAdministrateur.nom)}"></c:out><br/><c:out value="${fn:toUpperCase(sessionScope.sessionAdministrateur.prenom)}"></c:out></span>
                </div>
                <nav class="aside__nav">
                    <a href="<c:url value="/ai/administrateur"/>">
                        <span class="icon-profile"></span>
                        <span>ADMINISTRATEURS</span>
                    </a>
                    <a href="<c:url value="/ai/etudiant"/>">
                        <span class="icon-graduate"></span>
                        <span>ETUDIANTS</span>
                    </a>
                    <a href="<c:url value="/ai/groupe"/>">
                        <span  class="icon-addressbook"></span>
                        <span>GROUPES</span>
                    </a>
                    <a href="<c:url value="/ai/matiere"/>">
                        <span class="icon-presentation"></span>
                        <span>MATIERES</span>
                    </a>
                    <a href="<c:url value="/ai/professeur"/>">
                        <span class="icon-suitcase3"></span>
                        <span>PROFESSEURS</span>
                    </a>
                    <a href="<c:url value="/deconnexion"/>">
                        <span class="icon-switch2"></span>
                        <span>DECONNEXION</span>
                    </a>
                </nav>
            </div>
            <div id="main-wrap" class="main">
                <div class="main__head">
                    <h1 class="main__head__title">Liste des �tudiants</h1>
                    <p class="main__head__desc"><c:out value="${ nbEtudiants }"/> �tudiants enregistr�s</p>
                    <button type="button" class="btn btn--success main__head__control" onclick="displayRespModal('ai/etudiant/creation',300)">AJOUTER UN ETUDIANT</button>
                </div>
                <div class="main__content">
                    <div class="mod mod--lg">
                        <form action="http://localhost:8080/ZPareo/ai/etudiant" method="GET" class="form--inline">
                            <table>
                                <thead>
                                    <tr class="tr--ref">
                                        <th class="sortable">REFERENCE</th>
                                        <th class="sortable">NOM</th>
                                        <th class="sortable">PRENOM</th>
                                        <th class="sortable">ADRESSE MAIL</th>
                                        <th class="sortable">GROUPE</th>
                                        <th>ACTION</th>
                                    </tr>
                                    <tr class="tr--search">
                                        <th><input type="text" name="id"  size="10" pattern="[0-9]+" placeholder="Reference" x-moz-errormessage="Veuillez entrez une r�f�rence correcte"/></th>
                                        <th><input type="text" name="nom"  size="20" pattern="[A-Za-z ]+" placeholder="Nom" x-moz-errormessage="Veuillez entrer un nom correct"/></th>
                                        <th><input type="text" name="prenom"  size="20" pattern="[A-Za-z ]+" placeholder="Prenom" x-moz-errormessage="Veuillez entrer un prenom correct"/></th>
                                        <th><input type="text" name="adresseMail"  size="25" pattern="[A-Za-z0-9@.-_]+" placeholder="Adresse mail" x-moz-errormessage="Veuillez entrer une adresse mail correct"/></th>
                                        <th>
                                            <select name="groupe">
                                                <option value="" selected="selected">Tous</option>
                                                <c:forEach items="${ listeGroupes }" var="groupe">
                                                    <option value="${ groupe.id }"><c:out value="${ groupe.nom }"/></option>
                                                </c:forEach>
                                            </select>
                                        </th>
                                        <th><button type="submit" class="btn btn--primary">RECHERCHER</button></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${ listeEtudiants }" var="etudiant" >
                                        <tr class="tr--md">
                                            <td><c:out value="${ etudiant.id }"/></td>
                                            <td><c:out value="${ etudiant.nom }"/></td>
                                            <td><c:out value="${ etudiant.prenom }"/></td>
                                            <td><c:out value="${ etudiant.adresseMail }"/></td>
                                            <td><c:out value="${ etudiant.groupe.nom }"/></td>
                                            <td>
                                                <div class="btn-group">
                                                    <button type="button" class="btn btn--icon" onclick="displayRespModal('ai/etudiant/edition?id=<c:out value="${ etudiant.id }"/>',300)"><span class="icon-edit"></span></button>
                                                	<button type="button" class="btn btn--icon" onclick="displayRespModal('ai/etudiant/reinit-mot-de-passe?id=<c:out value="${ etudiant.id }"/>','auto')"><span class="icon-rotate"></span></button>
                                                	<button type="button" class="btn btn--icon" onclick="displayRespModal('ai/etudiant/suppression?id=<c:out value="${ etudiant.id }"/>','auto')"><span class="icon-trashcan"></span></button>
                                                </div>
                                            </td>
                                        </tr>
                                     </c:forEach>
                                </tbody>
                            </table>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <div id="modal" class="modal"></div>   
        <script type="text/javascript" src="<c:url value="/assets/js/jquery.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/assets/js/highcharts.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/assets/js/jquery-ui.custom.min.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/assets/js/jquery.tablesorter.min.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/assets/js/jquery.ui.datepicker-fr.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/assets/js/script.js"/>"></script> 
    </body>  
</html>