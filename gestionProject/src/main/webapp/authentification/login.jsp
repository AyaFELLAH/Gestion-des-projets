<%@page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="presentationLayer.controllers.authentification.LoginServlet" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="javax.servlet.http.Cookie" %>
    
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>Login</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="" name="keywords">
    <meta content="" name="description">

    <!-- Google Web Fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Heebo:wght@400;500;600;700&display=swap" rel="stylesheet">
    
    <!-- Icon Font Stylesheet -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

    <!-- Customized Bootstrap Stylesheet -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Template Stylesheet -->
    <link href="css/style.css" rel="stylesheet">
</head>

<body>
    <div class="container-fluid position-relative bg-white d-flex p-0">

        <!-- Sign In Start -->
        <div class="container-fluid">
            <div class="row h-100 align-items-center justify-content-center" style="min-height: 100vh;">
                <div class="col-12 col-sm-8 col-md-6 col-lg-5 col-xl-4">
                	
                	<% 
                		Cookie[] cookies = request.getCookies();
                		String password = "", login = "";
                		
                		if(cookies != null) {
                			for(Cookie cookie: cookies) {
                				if(cookie.getName().equals("login")) {
                					login = cookie.getValue();
                					break;
                				}
                			}
							for(Cookie cookie: cookies) {
								if(cookie.getName().equals("password")) {
                					password = cookie.getValue();
                					break;
                				}
                			}
                		}
                	%>
                	
                    <form class="bg-light rounded p-4 p-sm-5 my-4 mx-3" method="POST" action="login">
                        
                        <div class="d-flex align-items-center justify-content-between mb-3">
                           <h3 class="text-danger"><i class="fa fa-hashtag me-2"></i>Se Connecter</h3>
                        </div>
                        
                        <div class="form-floating mb-3">
                        	<%
                        		if(!request.getAttribute("loginField").equals("")){
                        			login = (String)request.getAttribute("loginField");
                        		}
                        	
	                        	if(!request.getAttribute("passwordField").equals("")){
	                    			password = (String)request.getAttribute("passwordField");
	                    		}
                        	%>
                        
                            <input type="email" class="form-control" value="<%=login %>" id="floatingInput" placeholder="name@example.com" name="login" required>
                            <label for="floatingInput">Login</label>
                            <small class="text-danger"><strong>
                            	<%  if(request.getAttribute("loginErreur") != null){
                            	%>
                            		<%=request.getAttribute("loginErreur")  %>
                            	<%	} %>
                            </strong></small>
                        </div>
                        
                        <div class="form-floating mb-4">
                            <input type="password" class="form-control" id="floatingPassword" value="<%=password %>" placeholder="Password" name="password" required>
                            <label for="floatingPassword">Password</label>
                            <small class="text-danger"><strong>
                            	<%  if(request.getAttribute("passwordErreur") != null){
                            	%>
                            		<%=request.getAttribute("passwordErreur")  %>
                            	<%	} %>
                            </strong></small>
                        </div>
                        
                        <div class="d-flex align-items-center justify-content-between mb-4">
                            <div class="form-check">
                                <input type="checkbox" class="form-check-input" name="remember-me"" id="remember-me" <%if(!login.equals("") && request.getAttribute("loginField").equals("") && request.getAttribute("passwordField").equals("") )  {%> checked <%} %>>
                                <label class="form-check-label" for="remember-me">Se souvenir de moi</label>
                            </div>
                        </div>
                        
                        <button type="submit" class="btn btn-danger py-3 w-100 mb-4">Entrer</button>
                    </form>
                    
                </div>
            </div>
        </div>    
    </div>
    
</body>

</html>