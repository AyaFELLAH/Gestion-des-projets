<%@page import="java.time.temporal.ChronoUnit"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="presentationLayer.models.*" %>
<%@ page import="java.util.List" %>
<%@ page import="java.sql.Date" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.time.LocalDate" %>

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
		     	
		     	<!-- table -->
		     	
		     	<div class="container-fluid pt-4 px-4">
			        <div class="row g-4">
			          <div class="col-12">
			            <div class="bg-light rounded h-100 p-4">
			              <span class="text-primary">Votre Projets</span>
			              <div class="table-responsive mt-4">
			                <table class="table table-bordered">
			                  <thead>
			                    <tr>
			                      <th scope="col">#</th>
			                      <th scope="col">Nom</th>
			                      <th scope="col">Description</th>
			                      <th scope="col">Durée Reste</th>
			                      <th scope="col"></th>
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
			                      <td> <%= proj.getName() %> </td>
			                      <td> <%= proj.getDescription() %> </td>
			                      <td> 
			                      	<%
			                      		LocalDate localtoday = LocalDate.now();
			                      		Date today = Date.valueOf(localtoday);
			                      	
		                      			//long daysPassed = ChronoUnit.DAYS.between(proj.getDateDemarrage().toLocalDate(), LocalDate.now());
		                      		if (proj.getDateDemarrage().after(today)) {
		                      		%>
		                      		<span class="text-success"><%=proj.getNombreJours() %></span>
		                      		<% } %>   
			                      </td>
			                      
			                      <td class="d-flex justify-content-center">
			                      
			                      	<a class="mt-1 d-flex justify-content-center" href="/gestionProject/chefProject/addtech-meth/<%= proj.getId() %>" >
			                        	<i class="bi bi-arrow-right-circle-fill" style="font-size:25px""></i>
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
			<%@ include file="../includes/scripts.jsp" %>
		<!-- end script files --> 
		
	</body>
</html>