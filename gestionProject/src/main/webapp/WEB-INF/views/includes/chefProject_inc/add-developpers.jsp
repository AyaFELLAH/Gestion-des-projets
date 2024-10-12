<script>
		
	function saveDeveloppers(event) {
		
		event.preventDefault();
		
		var idProject = $("#idProject").val();
		var developpeurs = $('input[name="developpersSelected"]:checked').map(function() {
			return this.value;
		}).get();
		
		var dateReunion = $("#dateReunion").val();
		
		if(developpeurs.length == 0) {
			alert("Veuillez choisir au moins un developpeur !");
			return false;
		}
		
		var dataToSend = {
			idProject: idProject,
			developpeurs: developpeurs.join(','),
			dateReunion: dateReunion
           };
		
		console.log(developpeurs);
		
		$.ajax({
               type: "POST",
               url: "/gestionProject/chefProject/DeveloppersControleServlet",
               data: dataToSend,
               
               success: function(response) {
               	window.location.href = "/gestionProject/chefProject/index";
               },
               
               error: function(error) {
                   console.log("erreur dans l'envoi de dev et techs");
               }
           });
	}


</script>
		
		