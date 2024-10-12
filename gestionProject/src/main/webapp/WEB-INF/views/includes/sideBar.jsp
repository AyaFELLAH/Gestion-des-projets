<%@ page import="presentationLayer.models.*" %>

<% 	
	session = request.getSession();
	User user = (User) session.getAttribute("user");
%>

<!-- Sidebar Start -->

<div class="sidebar pe-4 pb-3">
  <nav class="navbar navbar-dark">
  
    <a href="#" class="navbar-brand mx-5 mb-3 mt-4">
      <h3 class="text-primary"><i class="fa fa-hashtag me-2"></i>Projets</h3>
    </a>
    
    <div class="d-flex align-items-center ms-4 mb-4">
    
      <div class="position-relative">
        <i class="bi bi-person-fill btn-square bg-primary text-light"></i>
        <div class="bg-success rounded-circle border border-2 border-white position-absolute end-0 bottom-0 p-1"></div>
      </div>
      
      <div class="ms-3">
        <h6 class="mb-0"> <%= user.getNom() %> <%= user.getPrenom() %> </h6>
        <% if(user.getRole().equals("director")) { %>
        <span> Directeur </span>
        <% } else if (user.getRole().equals("chefProject")) { %>
         <span> Chef Du Projet </span>
         <% } else {%>
         <span> Developpeur </span>
         <% } %>
      </div>
      
    </div>
    
    <div class="navbar-nav w-100">
    <%
    	if(user.getRole().equals("chefProject")) {
    		if(request.getAttribute("active").equals("service")){
    		%>
      	<a href="/gestionProject/chefProject/index" class="nav-item nav-link"><i class="bi bi-bookmarks-fill me-2"></i>Projets</a>
      	<a href="/gestionProject/chefProject/services" class="nav-item nav-link active"><i class="bi bi-bounding-box me-2"></i>Services</a>
    	<% } else if(request.getAttribute("active").equals("project")) {%>
    	<a href="/gestionProject/chefProject/index" class="nav-item nav-link active"><i class="bi bi-bookmarks-fill me-2"></i>Projets</a>
      	<a href="/gestionProject/chefProject/services" class="nav-item nav-link"><i class="bi bi-bounding-box me-2"></i>Services</a>
    	<% } else {
    	%>
    	<a href="/gestionProject/chefProject/index" class="nav-item nav-link"><i class="bi bi-bookmarks-fill me-2"></i>Projets</a>
      	<a href="/gestionProject/chefProject/services" class="nav-item nav-link"><i class="bi bi-bounding-box me-2"></i>Services</a>
      
    <%} } else if(user.getRole().equals("director")) {%>
      <a href="/gestionProject/director/index" class="nav-item nav-link active"><i class="bi bi-bookmarks-fill me-2"></i>Projets</a>
      <a href="#" class="nav-item nav-link"><i class="bi bi-people-fill me-2"></i>Chefs Projets</a>
      <a href="#" class="nav-item nav-link"><i class="bi bi-person-lines-fill me-2"></i>Developpeurs</a>  
    <% } %>
    
    <%
    	if(user.getRole().equals("devlopper")) {
    		if(request.getAttribute("active").equals("profil")){
    %>
      <a href="/gestionProject/devlopper/index" class="nav-item nav-link"><i class="bi bi-bookmarks-fill me-2"></i>Projets</a>
      <a href="/gestionProject/devlopper/profilDev" class="nav-item nav-link active"><i class="bi bi-gear-fill me-2"></i>Profile</a>  
    	<% } else if (request.getAttribute("active").equals("projet")){ %>
    	<a href="/gestionProject/devlopper/index" class="nav-item nav-link active"><i class="bi bi-bookmarks-fill me-2"></i>Projets</a>
      <a href="/gestionProject/devlopper/profilDev" class="nav-item nav-link"><i class="bi bi-gear-fill me-2"></i>Profile</a>  
    <% } else {%>
    	<a href="/gestionProject/devlopper/index" class="nav-item nav-link"><i class="bi bi-bookmarks-fill me-2"></i>Projets</a>
      <a href="/gestionProject/devlopper/profilDev" class="nav-item nav-link"><i class="bi bi-gear-fill me-2"></i>Profile</a>  
    <% }
    }  %>
    
    </div>
    
  </nav>
</div>
<!-- Sidebar End -->