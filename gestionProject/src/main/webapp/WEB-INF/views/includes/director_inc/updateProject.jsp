<%@ page import="presentationLayer.models.*" %>
<%@ page import="java.util.List" %>

<% 
List<Project> dbProjects = (List<Project>)request.getAttribute("projects");
for (Project project:dbProjects){ %>
  
   <!-- here we add the update formuler of each project, but we hide it -->
   <div class="justify-content-center" id="id<%=project.getId()%>" style="display:none; background-color: rgba(0, 0, 0,0.4); position: relative; min-height: 100vh; z-index: 9999999999;">

		<form class="card bg-light w-75 mt-4 mb-4" method="POST" action="modifyP">
			<input type="hidden" name="idProject" value="<%=project.getId()%>">
	      <div class="card-header">
	        <h3 class="card-title text-primary mt-2">Modifier Projet</h3>
	      </div>

		  <div class="card-body container w-100 row">

		      <div class="col-md-6 b-2" id="infosProject">
		          <h6 style="border-bottom: 1px solid black;"><strong>Infos Projet</strong></h6>

    			  <div class="form-group mb-3">
		            <span>Nom </span>
		            <input type="text" class="form-control" name="nameProject<%=project.getId()%>" id="nameProject<%=project.getId()%>" value="<%=project.getName()%>"/>
		            <small class="text-danger" id="erreurProjectName"></small>
		          </div>
		          <div class="form-group mb-3">
		            <span>Description</span>
		            <textarea class="form-control" name="descriptionProject<%=project.getId()%>" id="descriptionProject<%=project.getId()%>" rows="2" placeholder="Ecrire ..."><%= project.getDescription() %></textarea>
		          	<small class="text-danger" id="erreurdescriptionProject"></small>
		          </div>
    
		          <div class="form-group">
		            <span>Date demarrage</span>
		            <input type="date" class="form-control" name="dateDemrrageProject<%=project.getId()%>" value="<%=project.getDateDemarrage()%>"/>
		          </div>
    
		          <div class="form-group">
		            <span>Date livraison</span>
		            <input type="date" class="form-control" name="dateLivraisonProject<%=project.getId()%>" value="<%=project.getDatelivraison()%>"/>
		          </div>
		    
		          <div class="form-group">
		            <span>Nombre jours</span>
		            <input type="number" class="form-control" name="nombreJoursProject<%=project.getId()%>" value="<%=project.getNombreJours()%>"/>
		          </div>

        	</div>
        
        	<div class="col-md-6">
          		<h6 style="border-bottom: 1px solid black; margin-bottom: 4px;" ><strong>Infos Client</strong></h6>
         
		          <div class="form-group">
		            <span>Cin</span>
		            <input type="hidden" name="oldClientCin<%=project.getId()%>" value="<%=project.getClient().getCin()%>"> 
		            <input type="text" class="form-control" name="cinClient<%=project.getId()%>" value="<%=project.getClient().getCin()%>"/>
		          </div>

		          <div class="form-group">
		            <span>Nom</span>
		            <input type="text" class="form-control" name="lastNameClient<%=project.getId()%>" value="<%=project.getClient().getNom()%>"/>
		          </div>

		          <div class="form-group">
		            <span>Prenom</span>
		            <input type="text" class="form-control" name="firstNameProject<%=project.getId()%>" value="<%=project.getClient().getPrenom()%>"/>
		          </div>

		          <div class="form-group">
		            <span>Telephone</span>
		            <input type="text" class="form-control" name="telephoneClient<%=project.getId()%>" value="<%=project.getClient().getTelephone()%>"/>
		          </div>

          	<h6 style="border-bottom:1px solid black;" class="mt-4"><strong>Chef Projet</strong></h6>

          	<div class="form-group mt-3">
            	<select name="chefProject<%=project.getId()%>" id="" class="form-select">
             <%
             	List<User> chefsProject1 = (List<User>)request.getAttribute("chefProjects");
     			for(User chef : chefsProject1){ 
     				if(chef.getId() == project.getChef().getId()) { 	
     		%>
     		  		<option value="<%= chef.getId() %>" selected="selected"><%= chef.getNom()%> <%= chef.getPrenom() %></option>
     				<% } else { %>
     				<option value="<%= chef.getId() %>"><%= chef.getNom()%> <%= chef.getPrenom() %></option>
     		 <% 	}
     			}%>
     		
           		</select>
         	</div>
        </div>
      </div>

      <div class="w-100 mb-4 d-flex justify-content-end mt-0">
        <span  class="btn btn-danger w-25"  style="margin-right:4px;"  onclick="stopModifyingProject(<%=project.getId()%>)">Annuler</span>
        <input type="submit"class="btn btn-primary w-25" style="margin-right:4%;" value="Modifier">
      </div>

    </form>
  </div>
  
<% 
} %>
