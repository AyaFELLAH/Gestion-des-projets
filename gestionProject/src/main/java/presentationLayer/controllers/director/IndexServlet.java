package presentationLayer.controllers.director;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import businessLayer.ProjectManagment;
import businessLayer.ProjectManagmentInterface;
import businessLayer.UserManagment;
import businessLayer.UserManagmentInterface;
import presentationLayer.models.User;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet("/IndexServlet")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		if(user != null && user.getRole().equals("director")) {
			
			// sending the chefs
			UserManagmentInterface userManagment = new UserManagment();
			request.setAttribute("chefProjects", userManagment.getChefsProjects());
			
			// sending the projects
			ProjectManagmentInterface projectManagment = new ProjectManagment();
			request.setAttribute("projects", projectManagment.getProjects());
			
			request.getRequestDispatcher("/WEB-INF/views/director/index.jsp").forward(request, response);
	
		} else {
			response.sendRedirect("/gestionProject/login");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
