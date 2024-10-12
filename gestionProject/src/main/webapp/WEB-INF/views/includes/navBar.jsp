 <%@ page import="presentationLayer.models.*" %>
 <%@ page import="java.util.List" %>

<% 	
	session = request.getSession();
	User currentUser = (User) session.getAttribute("user");
	
	List<Notification> notifications = (List<Notification>)request.getAttribute("notifications");
%>
 
 <!-- Navbar Start -->
<nav class="navbar navbar-expand bg-light navbar-light sticky-top px-4 py-0">
  
  <% if (currentUser.getRole().equals("chefProject") || currentUser.getRole().equals("devlopper")) {%>
  
  <a href="#" class="sidebar-toggler flex-shrink-0">
      <i class="fa fa-bars"></i>
  </a>
  <form class="d-none d-md-flex ms-4">
      <input class="form-control border-0" type="search" placeholder="Search">
  </form>
  
  <% } %>
  <div class="navbar-nav align-items-center ms-auto">
  <% if (currentUser.getRole().equals("chefProject") || currentUser.getRole().equals("devlopper")) {%>

   <div class="nav-item dropdown">
   
   		<% int i = 0; %>
   
       <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">
           <i class="fa fa-bell me-lg-2"></i>
           <span class="d-none d-lg-inline-flex">Notification
           <% if(notifications.size() != 0 ) {%>
           <span class="text-warning"> &nbsp;(<%=notifications.size() 
           %>)</span>
           <% } %>
           </span>
       </a>
       <div class="dropdown-menu dropdown-menu-end bg-light border-0 rounded-0 rounded-bottom m-0">
           
           <% for (Notification notification: notifications) { %>
           
           <a href="/gestionProject/notification/<%=notification.getId()%>" class="dropdown-item">
               <h6 class="fw-normal mb-0">
               		<%String notif = notification.getMessage(); %>
                	<%=notif %>
                	...</h6>
               <small><%= notification.getDateEnvoi() %></small>
           </a>
           
           <% } %>
           <hr class="dropdown-divider">
           <a href="/gestionProject/notification/-1" class="dropdown-item text-center">Voir tous les notifications</a>
       </div>
   </div>
  
  <% } %>
  
  <div class="navbar-nav align-items-center ms-auto">
    <div class="nav-item dropdown">
    
      <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">
        <span class="d-none d-lg-inline-flex"> <%= currentUser.getNom() %> <%= currentUser.getPrenom() %></span>
      </a>
      
      <div class="dropdown-menu dropdown-menu-end bg-light border-0 rounded-0 rounded-bottom m-0">
        <hr class="dropdown-divider">
        <a href="/gestionProject/logout" class="dropdown-item"><i class="bi bi-box-arrow-left me-lg-2 me-2" style="font-size:18px;"></i> Deconnexion</a>
      </div>
      
    </div>
  </div>
  </div>

</nav>
<!-- Navbar End -->