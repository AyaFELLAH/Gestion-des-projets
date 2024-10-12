
<%@ page import="presentationLayer.models.*" %>
<%@ page import="java.util.List" %>

<div class="justify-content-center" id="addProject">
    
    <form class="card bg-light w-75 mt-4 mb-4" method="POST" action="addProject" id="addProjectForm"  onsubmit="verifierChamps(event)">
        
      <div class="card-header">
        <h3 class="card-title text-primary mt-2">Ajouter projet</h3>
      </div>

      <div class="card-body container w-100 row">

        <div class="col-md-6 b-2" id="infosProject">
          <h6 style="border-bottom: 1px solid black;"><strong>Infos Projet</strong></h6>

          <div class="form-group mb-3">
            <span>Nom </span>
            <input type="text" class="form-control" name="nameProject"  id="nameProject" required/>
          </div>
          <div class="form-group mb-3">
            <span>Description</span>
            <textarea class="form-control" name="descriptionProject" id="descriptionProject" required rows="2" placeholder="Ecrire ..." ></textarea>
          </div>
          
          <div class="form-group mb-3">
            <span>Date demarrage</span>
            <input type="date" class="form-control" name="dateDemrrageProject" required  id="addProjectDateDemarrage" min="" onchange="changeMinDatelivraison()"/>
          </div>
          
          <div class="form-group">
            <span>Date livraison</span>
            <input type="date" class="form-control" name="dateLivraisonProject" required  min="" id="addProjectDateLivraison"/>
          </div>
          
          <div class="form-group">
            <span>Nombre jours</span>
            <input type="number" class="form-control" required name="nombreJoursProject" id="nombreJoursProject"/>
          </div>

        </div>
        <div class="col-md-6">
          <h6 style="border-bottom: 1px solid black; margin-bottom: 4px;" ><strong>Infos Client</strong></h6>
         
          <div class="form-group">
            <span>Cin</span>
            <input type="text" class="form-control" required name="cinClient"/>
          </div>

          <div class="form-group">
            <span>Nom</span>
            <input type="text" class="form-control" required  name="lastNameClient"/>
          </div>

          <div class="form-group">
            <span>Prenom</span>
            <input type="text" class="form-control" value="" name="firstNameProject"/>
          </div>

          <div class="form-group">
            <span>Telephone</span>
            <input type="text" class="form-control" value="" name="telephoneClient"/>
          </div>

          <h6 style="border-bottom:1px solid black;" class="mt-4"><strong>Chef Projet</strong></h6>

          <div class="form-group mt-3">
            <select name="chefProject" id="" class="form-select" required>
              <option value="" selected="selected" disabled >-- selectionnez --</option>
              
             <%
             	List<User> chefsProject = (List<User>)request.getAttribute("chefProjects");
     			for(User chef : chefsProject){ %>
     		  		<option value="<%= chef.getId() %>"><%= chef.getNom()%> <%= chef.getPrenom() %></option>
     		
     		 <% } %>
              
            </select>
          </div>

        </div>

      </div>

      <div class="w-100 mb-4 d-flex justify-content-end mt-0">
        <span  class="btn btn-danger w-25" style="margin-right:4px;"  onclick="stopAddProject()">Annuler</span>
        <input type="submit"class="btn btn-primary w-25 mr-4" style="margin-right:4%;"  value="Ajouter">
      </div>

    </form>
</div>


<script type="text/javascript">

	//to desactivate the presvious days
	
	//Obtenir la date d'aujourd'hui au format ISO (AAAA-MM-JJ)
	var today = new Date().toISOString().split('T')[0];
	
	// Définir la valeur minimale du champ de date
	document.getElementById('addProjectDateLivraison').min = today;
	document.getElementById('addProjectDateDemarrage').min = today;

	
	
	function changeMinDatelivraison(){
		//Obtenir la date d'aujourd'hui au format ISO (AAAA-MM-JJ)
		var minday = document.getElementById('addProjectDateDemarrage').value;
		
		// Définir la valeur minimale du champ de date
		document.getElementById('addProjectDateLivraison').min = minday;
	}

function verifierChamps(event){
  		
	event.preventDefault();
	var test = false;
	var Demarrage = $('#addProjectDateDemarrage').val();
	var Livraison = $('#addProjectDateLivraison').val();
	
	var nombreJours = $('#nombreJoursProject').val();
	
	var dateDemarrage = new Date(Demarrage);
	var dateLivraison = new Date(Livraison);
	dateDemarrage.setTime(dateDemarrage.getTime()+ nombreJours*24*60*60*1000);
	
	if(dateDemarrage.getTime()>dateLivraison.getTime()) {
		alert("Le nombre de jours depasse la date de livraison !");
		$('#nombreJoursProject').val('');
		return;
	}
	
	var projectName = $('#nameProject').val();
	
	var dataToSend = {
		projectName: projectName,
	};
		
	$.ajax({
        type: "POST",
        url: "/gestionProject/chefProject/verify/projectName",
        data: dataToSend,
        
        success: function(response) {
        	
        	if(!(response[13] == 0)) {
				alert("Ce projet existe déjà dans la base !! ");
			} else {
				document.getElementById('addProjectForm').submit();
			}
        },
        
        error: function(error) {
        	
            console.log("erreur de verification de nom de projet ");
        }
    
    });	
}

</script>
  