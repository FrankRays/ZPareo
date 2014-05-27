<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- Fenetre modale de d�tails d'un professeur -->
<div id="details-professeur" class="fenetre-modale">
	<section class="module">
		<div class="module-barre">
			<h1 class="centre">Informations g�nerales</h1>
		</div>
		<div class="module-form">
 			<dl class="dl-horizontal">
 				<dt>Nom : </dt>
 				<dd><c:out value='${ professeur.nom }'/></dd>
 				<dt>Pr�nom : </dt>
 				<dd><c:out value='${ professeur.prenom }'/></dd>
 				<dt>Identifiant : </dt>
 				<dd><c:out value='${ professeur.adresseMail }'/></dd>
 				<dt>Groupe(s) : </dt>
 				<dd>
 					<c:forEach items="${ listeGroupes }" var="groupe">
			    		<c:forEach items="${ professeur.listeGroupes }" var="professeurGroupe">
			        		<c:if test="${ professeurGroupe.id == groupe.id }"><c:out value="${ groupe.nom }"/>, </c:if>			                      
			        	</c:forEach>
			   		</c:forEach>
 				</dd>
 				<dt>Mati�re(s) enseign�e(s) : </dt>
 				<dd>
 					<c:forEach items="${ listeMatieres }" var="matiere">
			    		<c:forEach items="${ professeur.listeMatieres }" var="professeurMatiere">
			        		<c:if test="${ professeurMatiere.id == matiere.id }"><c:out value="${ matiere.nom }, "/></c:if>			                      
			        	</c:forEach>
			   		</c:forEach>
 				</dd>
 			</dl>
		</div>				
		<div class="module-control-bas">
				<button type="submit" class="bouton bouton-primary" onclick="affFormEdition('professeur',<c:out value="${ professeur.id }"/>,600)" >EDITER</button>
				<button type="button" class="bouton bouton-default" onclick="supprFenetresModales()">FERMER</button>
			</div>
	</section>
</div>