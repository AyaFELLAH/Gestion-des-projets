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
		     	
		     	<!-- table -->
		     	
		     	<div class="container-fluid pt-4 px-4">
			        <div class="row g-4">
			          <div class="col-12">
			            <div class="bg-light rounded h-100 p-4">
			              <span class="text-primary">Notifications</span>
			              <div class="table-responsive mt-4">
			                <table class="table table-bordered">
			                  <thead>
			                    <tr>
			                      <th scope="col">#</th>
			                      <th scope="col">Date</th>
			                      <th scope="col">Contenu</th>
			                      <th scope="col">Emetteur</th>
			                    </tr>
			                  </thead>
			                  <tbody>
			                  	<%
			                  		int i = 1;
			                  		List<Notification> userNotifications = (List<Notification>)request.getAttribute("userNotifications");
			                  		
			                  		// la boucle sur les projets
			                  		for (Notification notif: userNotifications) {
			                  	%>
			                    <tr>
			                      <td scope="row"> <%= i %> </td>
			                      <% i++; %>                      
			                      <td> <%= notif.getDateEnvoi()%> </td>
			                      <td> <%= notif.getMessage() %> </td>
			                      <td> 
			                      	<%=notif.getEmetteur().getNom() %> <%=notif.getEmetteur().getPrenom() %>
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