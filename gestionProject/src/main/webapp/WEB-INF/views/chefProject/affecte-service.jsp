<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="presentationLayer.models.*" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>
	<!-- head -->
		<%@ include file="../includes/chefProject_inc/head-balise.jsp" %>
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
		     	
		     	<!-- table -->
		     	
		     	<div class="container-fluid pt-4 px-4">
			        <div class="row g-4">
			          <div class="col-12">
			            <div class="bg-light rounded h-100 p-4">
			              <span class="text-primary">Services de projet</span>
			              <div class="table-responsive mt-4">
			                <table class="table table-bordered">
			                  <thead>
			                    <tr>
			                      <th scope="col">#</th>
			                      <th scope="col">Nom</th>
			                      <th scope="col">Description</th>
			                      <th scope="col">Durée</th>
			                      <th scope="col">Taches & Developpeurs</th>
			                    </tr>
			                  </thead>
			                  <tbody>
			                  	<%
			                  		int i = 1;
			                  		List<Service> services = (List<Service>)request.getAttribute("services");
			                  		
			                  		// la boucle sur les projets
			                  		for (Service service: services) {
			                  	%>
			                    <tr>
			                      <td scope="row"> <%= i %> </td>
			                      <% i++; %>               
			                      <td> <%= service.getNom() %> </td>
			                      <td> <%= service.getDescription() %> </td>
			                      <td> <%= service.getDuree() %> </td>
			                      
			                      <td>
			                      	<table class="table table-bordered">
					                  <thead>
					                    <tr>
					                      <th scope="col">Tache</th>
					                      <th scope="col">Developpeur</th>
					                      <th scope="col">Avancement</th>
					                    </tr>
					                  </thead>
			                  		  <tbody>
			                      		
				                      <% for(Tache tache: service.getTaches()) { %>
				                      	<tr>
				                      		<td><%=tache.getNom()%></td>
				                      		<td><%=tache.getDeveloppeur().getNom()%> <%=tache.getDeveloppeur().getPrenom() %></td>
				                      		<td><%=tache.getPourcentage() %> (%)</td>
				                      	</tr>
				                      <% } %>
				                      </tbody>
				                     </table>
			                      </td>
			                      
			                    </tr>
			                    
			                    <% } %>
			                  </tbody>
			                </table>
			              </div>
			              
			              <!-- adding service form -->
								<%@ include file="../includes/chefProject_inc/add-service.jsp" %>
			              <!-- end adding service form -->			             
			              	
		              	<div>
		            		<button class="btn btn-primary btn-sm mt-4" id="btn-add-service" onclick="addService(this)">Ajouter service</button>
		            	</div>
			              
			            </div>
			          </div>
			        </div>
			     </div>		     	
		     		
		     	<!-- end table -->
	    		
	    		<!-- Footer Start -->
			     	<%@ include file="../includes/footer.jsp" %>
			    <!-- Footer End -->
			    
			</div>
		   	<!-- Content End -->
	    
		    <!-- Back to Top -->
		    <a href="#" class="btn btn-lg btn-primary btn-lg-square back-to-top"><i class="bi bi-arrow-up"></i></a>
		  	
		</div>
		
		<!-- script files -->
			<%@ include file="../includes/chefProject_inc/scripts.jsp" %>
		<!-- end script files --> 
		
		<script>
		     	
     		function addService(btn) {
     			btn.style.display="none";
     			document.getElementById("new-service").style.display="";
     		}
     	
     	</script>
		
	</body>
</html>