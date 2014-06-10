<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- Fenetre modale de suppression de la mati�re -->
<div id="suppression-etudiant" class="fenetre-modale">
	<section class="module">
		<div class="module-barre">
			<h1 class="centre">Suppression de l'�tudiant n�<c:out value="${ etudiant.id }"/></h1>
		</div>
		<form method="POST" class="form-horizontal">
			<div class="module-form">
			<br/>
			<p>�tes-vous s�r de vouloir supprimer la l'�tudiant : <c:out value="${etudiant.prenom}"/> <c:out value="${etudiant.nom}"/>?</p>
				<input type="hidden" name="id" class="form-control input-sm" value="<c:out value='${ etudiant.id }'/>" size="30" pattern="[0-9]{1,11}" readonly disabled required/>
			</div>				
			<div class="module-control-bas">
				<button type="submit" class="bouton bouton-danger">SUPPRIMER</button>
				<button type="button" class="bouton bouton-default" onclick="supprFenetresModales()">ANNULER</button>
			</div>
		</form>
	</section>
</div>