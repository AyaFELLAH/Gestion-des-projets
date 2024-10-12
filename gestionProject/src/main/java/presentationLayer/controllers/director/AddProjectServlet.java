package presentationLayer.controllers.director;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import businessLayer.NotificationManagment;
import businessLayer.NotificationManagmentInterface;
import businessLayer.ProjectManagment;
import businessLayer.ProjectManagmentInterface;
import businessLayer.UserManagment;
import presentationLayer.models.*;

/**
 * Servlet implementation class AddProjectServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/AddProjectServlet" })
public class AddProjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddProjectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		if(user != null && user.getRole().equals("director")) {
			
			String nameProjet = request.getParameter("nameProject");
			String descriptionProject = request.getParameter("descriptionProject");
			String dateDemrrageProject = request.getParameter("dateDemrrageProject");
			String dateLivraisonProject = request.getParameter("dateLivraisonProject");
			int nombreJoursProject = Integer.parseInt(request.getParameter("nombreJoursProject"));
			String cinClient = request.getParameter("cinClient");
			String lastNameClient = request.getParameter("lastNameClient");
			String firstNameProject = request.getParameter("firstNameProject");
			String telephoneClient = request.getParameter("telephoneClient");
			int idChefProject = Integer.parseInt(request.getParameter("chefProject"));
			
			// convertir les dates
			Date dateDemrrageProjectChanged = Date.valueOf(dateDemrrageProject);
			Date dateLivraisonProjectChanged = Date.valueOf(dateLivraisonProject);
			
			Client client = new Client(lastNameClient, firstNameProject, cinClient, telephoneClient);
			User chef = new User();
			chef.setId(idChefProject);
			
			Project projet = new Project(nameProjet, descriptionProject, nombreJoursProject, dateDemrrageProjectChanged, dateLivraisonProjectChanged, chef, client);
			
			ProjectManagmentInterface addProject = new ProjectManagment();
			
			if(addProject.addProject(projet) == 1) {
				
				// now we added the project we should send a notification to chef Project
				Notification notification = new Notification();
				notification.setIdEmetteur(user.getId());
				notification.setIdRecepteur(idChefProject);
				String message = "Vous avez affecté au projet ~ "+nameProjet+" ~, vous avez "+nombreJoursProject+" à partir de "+dateDemrrageProject;
				System.out.println(message);
				notification.setMessage(message);
				
				NotificationManagmentInterface notificationManagment = new NotificationManagment();
				notificationManagment.addNotification(notification);

				response.sendRedirect("/gestionProject/director/index");
			}
		
		} else {
			response.sendRedirect("/gestionProject/login");
		}
		
	}

}
