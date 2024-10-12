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
		     	
		     	<!-- body -->
		     	<form class="container-fluid pt-4 px-4" id="addtch-meth-form" onsubmit="getDeveloppers(event)">
			     	<input type="hidden" value="<%=request.getAttribute("idProject") %>" id="idProject" >
			     	<div class="row">
				     	<div class="col-sm-12 col-xl-6">
		                    <div class="rounded h-100 p-4 bg-light">
		                        <h6 class="mb-4">Methodologie</h6>
	                            <fieldset class="row mt-3 mb-3">
	                            	<div class="col-sm-10">
	                            
		                            <% 
		                            	List<String> meths = (List<String>)request.getAttribute("meths");
		                            	for(String meth:meths) {
		                            %>
	                            
	                                
	                                    <div class="form-check" >
	                                        <input class="form-check-input" type="radio" name="meth"
	                                            id="meth-<%=meth%>" value="<%=meth%>" required>
	                                        <label class="form-check-label" for="meth-<%=meth%>">
	                                            <%=meth%>
	                                        </label>
	                                    </div>
	                                <% } %>
	                                    
	                                </div>
	                            </fieldset>
		                    </div>
		                </div>
		                
		                <div class="col-sm-12 col-xl-6">
	                       <div class="rounded h-100 p-4 bg-light" >
		                        <h6 class="mb-4">Technologies</h6>
		                        
		                        <% 
		                            	List<Technology> techs = (List<Technology>)request.getAttribute("techs");
		                            	int i = 0;
		                        		for(Technology tech:techs) {
		                            	
		                            %>
	                            
			                        <div class="form-check form-check-inline">
			                            <input class="form-check-input" name="techs" type="checkbox" id="<%=tech.getId()%>" value="<%=tech.getId()%>" >
			                            <label class="form-check-label" for="<%=tech.getId()%>"><%=tech.getNom() %></label>
			                        </div>
			                        
			                        
		                        <%  } %>
	                    	</div>
	                    </div>
                    </div>
                    
                    <div class="row mt-3">
                    	<div class="col-sm-12 col-xl-12  d-flex justify-content-end">
                    		<button type="submit" class="btn btn-primary w-25">valider</button>
                    	</div>
                    </div>
                    
                </form>
                
                <form class="container-fluid pt-4 px-4"  id="developper_form" onsubmit="saveDeveloppers(event)">
			     	
			     </form>
		     	
		     	<!-- end body -->
	    		
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
		
		<!-- code ajax d'ajout de meth & techs -->
			<%@ include file="../includes/chefProject_inc/meth-techs.jsp" %>
		<!-- end code ajax d'ajout de meth & techs -->
		
		<!-- code ajax d'ajout des developpeurs -->
			<%@ include file="../includes/chefProject_inc/add-developpers.jsp" %>
		<!-- end code ajax d'ajout des developpeurs -->
		
	</body>
</html>