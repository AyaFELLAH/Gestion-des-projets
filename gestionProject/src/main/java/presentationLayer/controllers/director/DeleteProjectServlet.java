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
import presentationLayer.models.User;

/**
 * Servlet implementation class DeleteProjectServlet
 */
@WebServlet("/DeleteProjectServlet")
public class DeleteProjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteProjectServlet() {
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
			

			String pathInfo = request.getPathInfo();
			String id = pathInfo.substring(1);
			
			// the id is the id of the project we will drop
			int idProject = Integer.parseInt(id);
			
			ProjectManagmentInterface projectManagment = new ProjectManagment();
			if( projectManagment.deleteProject(idProject) == 1 ) {
				response.sendRedirect("/gestionProject/director/index");
			} else {
				
				response.setContentType("text/html");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().println("<h4 style='color:red;'>Un erreur est produit lors de la suppression ....</h4>");
			}
	
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
