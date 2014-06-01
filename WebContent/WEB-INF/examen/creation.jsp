<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- Fenetre modale de creation d'un examen -->
<div id="creation-examen" class="fenetre-modale">
	<section class="module">
		<div class="module-barre">
			<h1 class="centre">Ajout d'un examen</h1>
		</div>
		<form action="http://localhost:8080/ZPareo/examen/creation" method="POST" class="form-horizontal">
			<div class="module-form">
 				<select name="format" class="form-control input-sm">
                	<option disabled="disabled" selected="selected">S�lectionner un type</option>
                	<option value="1">Oral</option>
                	<option value="2">Ecrit</option>
			    </select>
                <input type="text" name="nom" class="form-control input-sm" pattern=".{5,55}" placeholder="Nom" x-moz-errormessage="Veuillez entrer un nom correct"/>
                <input type="date" name="date" class="form-control input-sm" placeholder="Date de l'examen" x-moz-errormessage="Veuillez entrer une date correct"/>
                <select name="groupe" class="form-control input-sm">
                	<option disabled="disabled" selected="selected">S�lectionner un groupe</option>
			        <c:forEach items="${ sessionScope.sessionProfesseur.listeGroupes }" var="groupe">
			        	<option value="${ groupe.id }">
			            	 <c:out value="${ groupe.nom }"/>
			            </option>
			        </c:forEach>
			    </select>
                <select name="matiere" class="form-control input-sm">
                	<option disabled="disabled" selected="selected">S�lectionner une mati�re</option>
			        <c:forEach items="${ sessionScope.sessionProfesseur.listeMatieres }" var="matiere">
			        	<option value="${ matiere.id }">
			            	 <c:out value="${ matiere.nom }"/>
			            </option>
			        </c:forEach>
			   	</select>
			</div>				
			<div class="module-control-bas">
				<button type="submit" class="bouton bouton-primary">AJOUTER</button>
				<button type="button" class="bouton bouton-default" onclick="supprFenetresModales()">ANNULER</button>
			</div>
		</form>
	</section>
</div>