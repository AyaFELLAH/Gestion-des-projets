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
		     	
		     	<!--  ========================= -->
		     	<div class="container-fluid pt-4 px-4">
		        <div class="row g-4">
		          <div class="col-12">
		            <div class="bg-light rounded h-100 p-4">
		     
		     	<% Project projet = (Project) request.getAttribute("projet"); %>
                        <div class="divContentWrapper">
                            <h2 class="mb-4">Informations sur le Projet</h2>
                            <div>
                                <div>
                                    <p><strong> Nom du Projet:</strong><%=projet.getName()%></p>
                                    <p><strong> Date de Démarrage:</strong> <%=projet.getDateDemarrage()%></p>
                                </div>

                                <div>
                                    <p><strong> Date de Livraison:</strong><%=projet.getDatelivraison()%></p>
                                </div>
                                 <p><strong>Description du Projet:</strong><%=projet.getDescription()%> </p>
                                
                            </div>
                        </div>
                        </div>
                        </div>
                        </div>
                        </div>
                       
                        
      			 <div class="container-fluid pt-4 px-4">
		        <div class="row g-4">
		          <div class="col-12">
		            <div class="bg-light rounded h-100 p-4">
		                  			<h2>Les services</h2>
		            
		              <div class="table-responsive mt-4">
     			<table class="table table-bordered">
		                  <thead>
		                    <tr>
		                      <th scope="col">#</th>
		                      <th scope="col">Nom du service</th>
		                      <th scope="col">Description</th>
		                      <th scope="col">Durée (jours)</th>
		                      <th scope="col">Tâches</th>
		  
		                    </tr>
		                  </thead>
		                  <tbody>
		                  	<% 
		                  		int i = 1;
		                    List<Service> services = (List<Service>) request.getAttribute("services");
		                  		
		                  		// la boucle sur les projets
		                  		for (Service service : services) {
		                  	%>
		                  
		                  <tr>
		                  	<td scope="row"> <%= i %> </td>
		                      <% i++; %>         
		                  	<td><%= service.getNom() %></td>
		                    <td><%= service.getDescription() %></td>
		                  	<td> <%= service.getDuree() %></td>
						<td>
						<table class="table table-bordered">
		                  <thead>
		                    <tr>
		                      <th scope="col">Nom de la tache</th>
		                      <th scope="col">Avancement</th>
		                    </tr>
		                  </thead>
		                  <tbody>
		                  	<% 
		                  	
		            		for(Tache tache : service.getTaches()) {
		                  	%>
		            
		                  <tr>      
		                  	<td><%= tache.getNom() %></td>
							<td>
								<input type="number" value="<%=tache.getPourcentage() %>" id="<%=tache.getId() %>" onchange="changeMe(this)">
							</td>
		                   </tr>
		                   <% } %>
						</table>
        				</td>		                  
        			</tr>
		                  
		                  <%} %>
		                  </tbody>
		                </table>
		     	<!-- end bady content -->
	    		</div>
	    	</div>
	    	</div>
	    	</div>
	    	</div>
	    	
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
	
		function changeMe(input) {
			
			if(input.value>100) {
				input.value = 100;
			}
			
			if(input.value<0){
				input.value=0;
			}
			var dataToSend = {
					idTechnologie: input.id,
					technologieValue: input.value,
		           };
				
				$.ajax({
		               type: "POST",
		               url: "/gestionProject/devlopper/consulterP/-1",
		               data: dataToSend,
		               
		               success: function(response) {
		               },
		               
		               error: function(error) {
		                   console.log("erreur dans l'envoi de progress of tache ");
		               }
		           });
			
		}
	
	</script>
</html>