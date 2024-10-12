package presentationLayer.controllers.director;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import businessLayer.ProjectManagment;
import businessLayer.ProjectManagmentInterface;
import businessLayer.UserManagment;
import presentationLayer.models.Client;
import presentationLayer.models.Project;
import presentationLayer.models.User;

/**
 * Servlet implementation class UpdateProjectServlet
 */
@WebServlet("/UpdateProjectServlet")
public class UpdateProjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateProjectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		if(user != null && user.getRole().equals("director")) {
			
			int idProject = Integer.parseInt(request.getParameter("idProject"));
			String oldClientCin = request.getParameter("oldClientCin"+idProject);
			
			String nameProjet = request.getParameter("nameProject"+idProject);
			String descriptionProject = request.getParameter("descriptionProject"+idProject);
			String dateDemrrageProject = request.getParameter("dateDemrrageProject"+idProject);
			String dateLivraisonProject = request.getParameter("dateLivraisonProject"+idProject);
			int nombreJoursProject = Integer.parseInt(request.getParameter("nombreJoursProject"+idProject));
			String cinClient = request.getParameter("cinClient"+idProject);
			String lastNameClient = request.getParameter("lastNameClient"+idProject);
			String firstNameProject = request.getParameter("firstNameProject"+idProject);
			String telephoneClient = request.getParameter("telephoneClient"+idProject);
			int idChefProject = Integer.parseInt(request.getParameter("chefProject"+idProject));
			
			// convertir les dates
			Date dateDemrrageProjectChanged = Date.valueOf(dateDemrrageProject);
			Date dateLivraisonProjectChanged = Date.valueOf(dateLivraisonProject);
			
			Client client = new Client(lastNameClient, firstNameProject, cinClient, telephoneClient);
			User chef = new User();
			chef.setId(idChefProject);
			
			Project projet = new Project(idProject,nameProjet, descriptionProject, nombreJoursProject, dateDemrrageProjectChanged, dateLivraisonProjectChanged, chef, client);
			
			ProjectManagmentInterface projectManagment = new ProjectManagment();
			if(projectManagment.updateProject(projet,oldClientCin) == 1) {
				response.sendRedirect("/gestionProject/director/index");
			} else {
				response.setContentType("text/html");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().println("<h4 style='color:red;'>Un erreur est produit lors de la modification ....</h4>");
			}
			
		} else {
			response.sendRedirect("/gestionProject/login");
		}
		
	}

}
