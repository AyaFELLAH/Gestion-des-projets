package presentationLayer.controllers.authentification;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import businessLayer.UserManagment;
import businessLayer.UserManagmentInterface;
import presentationLayer.models.Account;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		request.setAttribute("loginField" ,"");
		request.setAttribute("passwordField" , "");
		request.getRequestDispatcher("authentification/login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		
		Account account = new Account(login, password);
		UserManagmentInterface userManagment = new UserManagment();
		
		request.setAttribute("loginErreur",null);
		request.setAttribute("passwordErreur", null);
		
		request.setAttribute("loginField",login);
		request.setAttribute("passwordField", password);
		
		int result = userManagment.existeUser(account);
		
		if ( result == 1 ) {
			
			String userLoginCheckBox = request.getParameter("remember-me");
			
			if(userLoginCheckBox != null && userLoginCheckBox.equals("on")) {
				
				Cookie loginCookie = new Cookie("login", login);
				Cookie passwordCookie = new Cookie("password", password);
				
				loginCookie.setMaxAge(3600*24*7); // une duree de 7 jours
				passwordCookie.setMaxAge(3600*24*7);
				
				// pour sauvegarder les cookies on doit les envoyer au navigateur
				response.addCookie(loginCookie);
				response.addCookie(passwordCookie);
				
			} else {
				
				// la suppression des cookies
				Cookie loginCookie = new Cookie("login", login);
				Cookie passwordCookie = new Cookie("password", password);
				
				loginCookie.setMaxAge(0); // une duree de 7 jours
				passwordCookie.setMaxAge(0);
				
				// pour sauvegarder les cookies on doit les envoyer au navigateur
				response.addCookie(loginCookie);
				response.addCookie(passwordCookie);
				
			}
			
			HttpSession session = request.getSession();
			session.setAttribute("user", userManagment.getUser());
			
			String role = userManagment.getUserRole();
			
			if( role.equals("chefProject") ) {
				response.sendRedirect("chefProject/index");
				
			} else if( role.equals("director") ) {
				response.sendRedirect("director/index");
				
			} else {
				response.sendRedirect("devlopper/index");
			}
			
		} else if( result == 0 ){ // gestion erreur de la page login
			request.setAttribute("loginErreur","login est incorrecte !!");
			request.getRequestDispatcher("authentification/login.jsp").forward(request, response);
			request.setAttribute("loginField","");
			request.setAttribute("passwordField", "");
		} else {
			request.setAttribute("passwordErreur", "password est incorrecte !!");
			request.getRequestDispatcher("authentification/login.jsp").forward(request, response);
			request.setAttribute("loginField",login);
			request.setAttribute("passwordField", "");
		}
	}
}




