<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="presentationLayer.models.*" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>
	<!-- head -->
		<%@ include file="../includes/developpeur_inc/head-balise.jsp" %>
	<!-- end head -->
	
	<body>
	
		<div id="indexContainer">
		
			<!-- side bar -->
		  		<%@ include file="../includes/sideBar.jsp" %>
		  	<!-- end side bar -->
		  	
		  	<!-- Content Start -->
	    	<div class="content">
	    		
	    		<!-- nav bar -->
		     		<%@ include file="../includes/navBar.jsp" %>
		     	<!-- end nav bar -->
		     	
		     	<!-- bady content -->
		     	
		     	<% User developpeur = (User)request.getAttribute("developper"); %>
		     	
		     	<form class="container-fluid pt-4 px-4" onsubmit="submitForm(event)">
		     		<div class="row g-4 bg-light mt-2">
		     			<div class="col-md-6 p-4">
		     				<h4>Informations Personnelles</h4>
		     				<hr>
		     				<input type="hidden" value="<%=developpeur.getAccount().getPassword() %> id="password">
		     				<div class="border p-4">
		     					<div class="row mb-2">
			                        <label  class="col-sm-2 col-form-label">Nom</label>
			                        <div class="col-sm-10">
			                            <input type="text" class="form-control" value="<%=developpeur.getNom() %>" id="nom-developper" name="nom-developper" required>
			                        </div>
			                    </div>
			                    <div class="row mb-2">
			                        <label  class="col-sm-2 col-form-label" required>Prenom</label>
			                        <div class="col-sm-10">
			                            <input type="text" class="form-control" value="<%=developpeur.getPrenom() %>" id="prenom-developper" name="prenom-developper" required>
			                        </div>
			                    </div>
			                    <div class="row mb-1">
			                        <label  class="col-sm-2 col-form-label" required>Email</label>
			                        <div class="col-sm-10">
			                            <input type="email" class="form-control" name="email-developper" value="<%=developpeur.getAccount().getLogin() %>" id="email-developper" required>
			                        </div>
			                    </div>
		     				</div>
		     			</div>
		     			<div class="col-md-6 p-4">
		     				<h4>Competences Personnelles</h4>
		     				<hr>
		     				<div class="border p-4" id="technologiesContainer">
		     					
		     				</div>
		     				<div class="mt-3 d-flex justify-content">
		     					<span class="btn btn-primary" onclick="addTechnology()">Ajouter technologie</span>
		     				</div>
		     			</div>
		     		</div>
		     		<div class="d-flex justify-content-end mt-3">
		     			<input type="submit" value="changer le profile" class="btn btn-primary">
		     		</div>
		     	</form>
		     
		     	<!-- end bady content -->
	    	
	    		<!-- Footer Start -->
			     	<%@ include file="../includes/footer.jsp" %>
			    <!-- Footer End -->
			    
			</div>
		   	<!-- Content End -->
	    
		    <!-- Back to Top -->
		    <a href="#" class="btn btn-lg btn-primary btn-lg-square back-to-top"><i class="bi bi-arrow-up"></i></a>
	  	
		</div>
		
		
		<!-- script files -->
			<%@ include file="../includes/developpeur_inc/scripts.jsp" %>
			
		<!-- end script files --> 
		
	</body>
	
	
	<script>
	
	function addTechnology() {
		
		var html = `
			<div class="border mb-2 p-2">
				<div class="row mb-2">
		        	<label for="tache-nom" class="col-sm-2 col-form-label">Nom</label>
			        <div class="col-sm-10">
			            <input type="text" class="form-control" name="technology-nom[]" required>
			        </div>
		    	</div>
		    	<div class="row mb-3">
		            <label  class="col-sm-2 col-form-label" required>Details</label>
		            <div class="col-sm-10">
		                <textarea class="form-control" rows="2" name="technology-description[]" required></textarea>
		            </div>
		        </div>	
		    		    
		    </div>`;
	    	
		 var tacheContainer = document.getElementById("technologiesContainer");
		 tacheContainer.insertAdjacentHTML('beforeend', html);
	}
	
	</script>
	
	
	<script>
	
		function submitForm(event) {
			event.preventDefault();
			
			
			if(confirm("Voulez-vous effectuer ces modification ??")) {
				var nomDev = $('#nom-developper').val();
				var prenomDev = $('#prenom-developper').val();
				var emailDev = $('#email-developper').val();
				
				var techsName = $('input[name="technology-nom[]"]').map(function(){
					return $(this).val();
				}).get();
			
				var techsDescription = $('textarea[name="technology-description[]"]').map(function(){
					return $(this).val();
				}).get();
				
				
				var dataToSend = {
						nomDev: nomDev,
						prenomDev: prenomDev,
						emailDev: emailDev,
						techsName: techsName.join(","),
						techsDescription: techsDescription.join(",")
			           };
						
					$.ajax({
			              type: "POST",
			              url: "/gestionProject/devlopper/modify/profil",
			              data: dataToSend,
			              
			              success: function(response) {
			           	   		window.location.href = "/gestionProject/devlopper/profilDev";
			              },
			              
			              error: function(error) {
			            	  alert("no");
			                  console.log("erreur dans l'envoi de developper modifications ");
			              }
			          });
			}			
		}
	
	</script>
</html>