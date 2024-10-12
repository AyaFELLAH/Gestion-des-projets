<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="presentationLayer.models.*" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>
	<!-- head -->
		<%@ include file="../includes/head-balise.jsp" %>
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
		     	
		     	<div class="container-fluid pt-4 px-4">
		     		<div class="row g-4 bg-light mt-2">
		     			<div class="col-md-6 p-4">
		     				<h4>Informations Personnelles</h4>
		     				<hr>
		     				
		     				<div class="border p-4">
		     					<p>
		     						<strong>Nom :</strong> <span><%=developpeur.getNom() %></span>
		     					</p>
		     					<hr>
		     					<p>
		     						<strong>Prenom :</strong> <span><%=developpeur.getPrenom() %></span>
		     					</p>
		     					<hr>
		     					<p>
		     						<strong>Email :</strong> <span><%=developpeur.getAccount().getLogin() %></span>
		     					</p>
		     				</div>
		     			</div>
		     			<div class="col-md-6 p-4">
		     				<h4>Competences Personnelles</h4>
		     				<hr>
		     				<div class="border p-4">
		     					<h6>Vous maitrisez </h6>
		     					<hr>
		     					<div>
		     						<% 
		     							List<Technology> technologies = (List<Technology>)request.getAttribute("technologies");
		     							for (Technology techno: technologies) { %>
		     								<span><%=techno.getNom() %> ~  </span>
		     					
		     						<% } %>
		     					</div>
		     				</div>
		     			</div>
		     		</div>
		     		<div class="d-flex justify-content-end mt-3">
		     			<a href="/gestionProject/devlopper/modify/profil" class="btn btn-primary">Modifier le profile</a>
		     		</div>
		     	</div>
		     
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
			<%@ include file="../includes/scripts.jsp" %>
			
		<!-- end script files --> 
		
	</body>
</html>