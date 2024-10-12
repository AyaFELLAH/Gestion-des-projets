<script>
	function getDeveloppers(event){
		event.preventDefault();
		
		var idProject = $("#idProject").val();
		var meth = document.querySelector('input[name="meth"]:checked').value;
		var techs = $('input[name="techs"]:checked').map(function() {
			return this.value;
		}).get();
		
		if(techs.length == 0) {
			alert("Veuillez choisir au moins une technologie !");
			return false;
		}
		
		var dataToSend = {
              techs: techs.join(','),
              idProject: idProject,
              meth: meth
           };

           // Effectuer la requête AJAX
           $.ajax({
               type: "POST",
               url: "/gestionProject/chefProject/addtech-meth/*",
               data: dataToSend,
               
               success: function(response) {
            	   
				var content = `
				
				<div class="row">
                          
                      <div class="col-sm-12 col-xl-12">
                       <div class="bg-light rounded h-100 p-4">
                           <h6 class="mb-4">Developpeurs maitrisent les technologies</h6>
                           <table class="table table-bordered">
                               <thead>
                                   <tr>
                                       <th scope="col">#</th>
                                       <th scope="col">Prenom</th>
                                       <th scope="col">Nom</th>
                                       <th scope="col">Technologies</th>
                                       <th scope="col">Selection</th>
                                   </tr>
                               </thead>
                               <tbody>`;
			                 for( var i = 0; i<response.length; i++){
			                	 content = content + `
			                	 
			                	 <tr>
                                      <th scope="row">`;
                                      content += (i+1);
                                      content += `</th>
                                      <td>`;
                                      content += response[i].prenom;
                                      content +=`</td>
                                      <td>`;
                                      content += response[i].nom;
                                      content += `</td>
                                      <td>
			                	 `;
			                	 
			                	 for (var j = 0; j<response[i].technologies.length; j++) {
			                		 content = content + response[i].technologies[j].nom;
			                		 content += ` . 
			                		 
			                		 `
			                	 }
                               
                               content = content + 
                               			`</td>
                               		<td>
                                  		<div class="form-check form-switch">
                                      		<input class="form-check-input" value="`;
                                      		content += response[i].id;
                                      		content += `" type="checkbox" role="switch" name="developpersSelected"
	                               			id="flexSwitchCheckDefault">
	                               		</div>
                                  	</td>
                               </tr>`;
			                 }
                                   
                content = content + `
                               </tbody>
                           </table>
                           <div class="form-group mt-2">
			     				<span style="color: black;">Date réunion</span>
           						<input type="date" class="form-control w-50" name="dateReunion" id="dateReunion" required/>
			     			</div>
                       </div>
                   </div>
		     	</div>
		     	
		     	<div class="row mt-3">
                   	<div class="col-sm-12 col-xl-12  d-flex justify-content-end">
                   		<button type="submit" class="btn btn-primary w-25">Enregistrer</button>
                   	</div>
                   </div>
				
				`;
				
               	$('#developper_form').html("");
               	$('#developper_form').append(content);
               	
              //Obtenir la date d'aujourd'hui au format ISO (AAAA-MM-JJ)
            	var today = new Date().toISOString().split('T')[0];
            	
            	// Définir la valeur minimale du champ de date
            	document.getElementById('dateReunion').min = today;
               	
               },
               
               error: function(error) {
                   console.log("erreur dans l'envoi de meths et techs");
               }
           });
	}
	
	
</script>
		
		