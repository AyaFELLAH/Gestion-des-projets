package presentationLayer.controllers.chefProject;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;

import businessLayer.NotificationManagment;
import businessLayer.NotificationManagmentInterface;
import businessLayer.ProjectManagment;
import businessLayer.ProjectManagmentInterface;
import businessLayer.TechnologieManagment;
import businessLayer.TechnologieManagmentInterface;
import businessLayer.UserManagment;
import businessLayer.UserManagmentInterface;
import presentationLayer.models.Notification;
import presentationLayer.models.Project;
import presentationLayer.models.Technology;
import presentationLayer.models.User;

/**
 * Servlet implementation class DeveloppersControleServlet
 */
@WebServlet("/DeveloppersControleServlet")
public class DeveloppersControleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeveloppersControleServlet() {
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
		
		if(user != null && user.getRole().equals("chefProject")) {
		
			String developpeursJoined = request.getParameter("developpeurs");
			String[] developpeurs = developpeursJoined.split(",");
			int idProject = Integer.parseInt(request.getParameter("idProject"));
			Date dateReunion = Date.valueOf(request.getParameter("dateReunion"));
			
			ProjectManagmentInterface projectManagment = new ProjectManagment();
			Project project = new Project();
			project.setId(idProject);
			project.setName(projectManagment.getProjectName(idProject));
			project.setDateReunion(dateReunion);
			
			List<User> developpers = new ArrayList<User>();
			UserManagmentInterface userManagment = new UserManagment();
			
			for(String developpeur:developpeurs) {
				
				User developper = userManagment.getUserById(Integer.parseInt(developpeur));
				developpers.add(developper);
			
			}
			
			project.setDeveloppeurs(developpers);
			project.setChef(user);
			
			if(userManagment.affectDeveloppersToProject(project) && projectManagment.updateDateReunion(project)) {
				
				System.out.println("***** developpers are added succesfly *****");
				
			} else {
				System.out.println("***** Erreur : developpers are not added  *****");
			}
			
		}
	}

}
