 <%@ page import="presentationLayer.models.*" %>
<%@ page import="java.util.List" %>
 
 <form id="new-service" style="display:none;" onsubmit="sendServices(event)">
   <div class="row border p-2">
   	<input type="hidden" value="<%=request.getAttribute("idProject") %>" name="idProject" id="idProject">
   	<div class="col-md-6 col-xl-6">
            <div class="rounded h-100 p-4">
                <h6 class="mb-4">Nouvelle service</h6>
                <div class="border p-2">
                    <div class="row mb-2">
                        <label for="name-service" class="col-sm-2 col-form-label">Nom</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="name-service" name="name-service" required>
                        </div>
                    </div>
                    <div class="row mb-2">
                        <label for="description-service" class="col-sm-2 col-form-label" required>Details</label>
                        <div class="col-sm-10">
                            <textarea class="form-control" id="description-service" name="description-service" rows="2" required></textarea>
                        </div>
                    </div>
                    <div class="row mb-1">
                        <label for="nombre-jours-service" class="col-sm-2 col-form-label" required>Nombre Jours</label>
                        <div class="col-sm-10">
                            <input type="number" class="form-control" name="nombre-jours-service" id="nombre-jours-service" required>
                        </div>
                    </div>
                                                
                </div>
            </div>
        </div>
        <div class="col-md-6">
        	<div class="rounded h-100 p-4">
                <h6 class="mb-4">Ses Taches</h6>
                <div id="tacheContainer" class="border p-2">
                	<input type="hidden" value="1" id="numberOfTaches">
                  <div class="border p-2">
                     <div class="row mb-2">
                         <label class="col-sm-2 col-form-label">Nom</label>
                         <div class="col-sm-10">
                             <input type="text" class="form-control" name="tache-nom[]" required>
                         </div>
                     </div>
                     <div class="row mb-3">
                         <label  class="col-sm-2 col-form-label" required>Details</label>
                         <div class="col-sm-10">
                             <textarea class="form-control" rows="2" name="tache-description[]" required></textarea>
                         </div>
                     </div>
                     <div class="row mb-2" id="projectDeveloppers">
                         
                         <div class="col-sm-12">
                             <select class="form-select"  name="tache-developpeur[]"  required>
                             	<option selected="selected" disabled >-- Choisir son developpeur --</option>

		                       	<% 
		                       	List<User> developpeurs = (List<User>)request.getAttribute("developpeurs");
		                       	for (User developpeur:developpeurs) {%>
		                       		<option value="<%=developpeur.getId()%>"><%=developpeur.getNom() %> <%=developpeur.getPrenom() %></option>
		                       	<%} %>
		                     </select>
                         </div>
                     </div>
                 </div>
                </div>
                <div class="d-flex justify-content-end mt-4">
                	<span class="btn btn-sm btn-primary" onclick="addTache()">Ajouter tache</span>
                </div>
                
            </div>
        </div>
   	
   </div>
   
   	<div>
		<input type="submit" class="btn btn-primary btn-sm mt-4" value="Enregistrer" id="btn-add-service">
	</div>
   
</form>

<script>


	function sendServices(event) {
		event.preventDefault();
		
		var idProject = $('#idProject').val();
		var serviceName = $('#name-service').val();
		var serviceDescription = $('#description-service').val();
		var serviceJours = $('#nombre-jours-service').val();
		
		var tachesName = $('input[name="tache-nom[]"]').map(function(){
			return $(this).val();
		}).get();
		
		var tachesDescription = $('textarea[name="tache-description[]"]').map(function(){
			return $(this).val();
		}).get();
		
		var tachesDeveloppeur = $('select[name="tache-developpeur[]"]').map(function(){
			return $(this).val();
		}).get();
		
		
		var dataToSend = {
			idProject: idProject,
			serviceName: serviceName,
			serviceDescription: serviceDescription,
			serviceJours: serviceJours,
			tachesName: tachesName.join(","),
			tachesDescription: tachesDescription.join(","),
			tachesDeveloppeur: tachesDeveloppeur.join(",")
           };
			
			
		$.ajax({
              type: "POST",
              url: "/gestionProject/chefProject/affecte-services/0",
              data: dataToSend,
              
              success: function(response) {
           	   		window.location.href = "/gestionProject/chefProject/affecte-services/"+idProject;
              },
              
              error: function(error) {
                  console.log("erreur dans l'envoi de service ");
              }
          });
	}


	
	var select = document.getElementById("projectDeveloppers");
	
	function addTache() {
		var numberOfTaches = document.getElementById("numberOfTaches");
		var n = numberOfTaches.value;
		numberOfTaches.value = n+1;
		
		var html = `
			<div class="border p-2 mt-4">
				<div class="row mb-2">
		        	<label for="tache-nom" class="col-sm-2 col-form-label">Nom</label>
			        <div class="col-sm-10">
			            <input type="text" class="form-control" name="tache-nom[]" required>
			        </div>
		    	</div>
		    	
		    	<div class="row mb-3">
		            <label  class="col-sm-2 col-form-label" required>Details</label>
		            <div class="col-sm-10">
		                <textarea class="form-control" rows="2" name="tache-description[]" required></textarea>
		            </div>
		        </div>		    
		    `;
		    
		    html += select.innerHTML;
		    html += `</div>`;
	    	
		 var tacheContainer = document.getElementById("tacheContainer");
		 tacheContainer.insertAdjacentHTML('beforeend', html);
		
	}

</script>



