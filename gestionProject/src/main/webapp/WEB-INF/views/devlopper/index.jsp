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
		     	
		     <div class="container-fluid pt-4 px-4">
		        <div class="row g-4">
		          <div class="col-12">
		            <div class="bg-light rounded h-100 p-4">
		            <h2>Les projets</h2>
		              <div class="table-responsive mt-4">
		                <table class="table table-bordered">
		                  <thead>
		                    <tr>
		                      <th scope="col">#</th>
		                      <th scope="col">Nom</th>
		                      <th scope="col">Description</th>
		                      <th scope="col">Durée</th>
		                      <th scope="col">Action</th>
		                    </tr>
		                  </thead>
		                  <tbody>
		                  	<% 
		                  		int i = 1;
		                  		List<Project> projects = (List<Project>)request.getAttribute("projects");
		                  		
		                  		// la boucle sur les projets
		                  		for (Project proj: projects) {
		                  	%>
		                  
		                  <tr>
		                  	<td scope="row"> <%= i %> </td>
		                      <% i++; %>         
		                  	<td><%= proj.getName() %></td>
		                    <td><%= proj.getDescription() %></td>
		                  	<td> <%= proj.getDateDemarrage() %>
		                        <br>
		                        <%= proj.getDatelivraison() %></td>
		                    <td class="d-flex justify-content-center">
		                      <a class="w-100 mt-1 d-flex justify-content-center" href="/gestionProject/devlopper/consulterP/<%= proj.getId() %>">
		                        <i class="fas fa-pencil-alt text-primary"></i>
		                      </a>
		                    </td>
		                  </tr>
		                  <% } %>
		                  </tbody>
		                </table>
		              </div>
		            </div>
		          </div>
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