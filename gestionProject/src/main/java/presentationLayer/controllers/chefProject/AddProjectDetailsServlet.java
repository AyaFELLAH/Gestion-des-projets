package presentationLayer.controllers.chefProject;

import java.io.IOException;
import java.io.PrintWriter;
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

import com.mysql.cj.xdevapi.JsonArray;

import businessLayer.NotificationManagment;
import businessLayer.NotificationManagmentInterface;
import businessLayer.ProjectManagment;
import businessLayer.ProjectManagmentInterface;
import businessLayer.TechnologieManagment;
import businessLayer.TechnologieManagmentInterface;
import businessLayer.UserManagment;
import businessLayer.UserManagmentInterface;
import presentationLayer.models.Project;
import presentationLayer.models.Technology;
import presentationLayer.models.User;

/**
 * Servlet implementation class AddProjectDetailsServlet
 */
@WebServlet("/AddProjectDetailsServlet")
public class AddProjectDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddProjectDetailsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		if(user != null && user.getRole().equals("chefProject")) {
			String pathInfo = request.getPathInfo();
			String id = pathInfo.substring(1);
			request.setAttribute("active", "project");
			// the id is the id of the project we will modify
			int idProject = Integer.parseInt(id);
			
			// sending the techs
			TechnologieManagmentInterface technologieManagment = new TechnologieManagment();
			request.setAttribute("techs", technologieManagment.getTechnologies());
			
			// sending the meths
			ProjectManagmentInterface projectManagment = new ProjectManagment();
			request.setAttribute("meths", projectManagment.getProjectMethodologies());
			request.setAttribute("idProject", idProject);
			
			// sending the notifications
			NotificationManagmentInterface notificationManagment = new NotificationManagment();
			request.setAttribute("notifications", notificationManagment.getUserNotification(user));
			
			request.getRequestDispatcher("/WEB-INF/views/chefProject/addProjectDetails.jsp").forward(request, response);
	
		} else {
			response.sendRedirect("/gestionProject/login");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String techsJoined = request.getParameter("techs");
		String[] techs = techsJoined.split(",");
		String meth = request.getParameter("meth");
		int idProject = Integer.parseInt(request.getParameter("idProject"));
		
		Project project = new Project();
		project.setId(idProject);
		project.setMethodologie(meth);
		
		List<Technology> technologies = new ArrayList<Technology>();
		TechnologieManagmentInterface technologieManagment = new TechnologieManagment();
		
		for(String tech:techs) {
			
			Technology technologie = technologieManagment.getTechnologyById(Integer.parseInt(tech));
			technologies.add(technologie);
		
		}
		
		project.setTechnologies(technologies);
		
		ProjectManagmentInterface projectManagment = new ProjectManagment();
		
		if(projectManagment.addMethAndTech(project) == 1) {
			
			UserManagmentInterface userManagment = new UserManagment();
			List<User> developpers = userManagment.getDeveloppersByTechnologies(project.getTechnologies());
			
			JSONArray jsonDeveloppers = new JSONArray();
			
			for(User developper: developpers) {
				
				JSONObject jsonDev = new JSONObject(developper);
				jsonDeveloppers.put(jsonDev);
			}
			
			response.setContentType("application/json");
			response.getWriter().write(jsonDeveloppers.toString());
			
			System.out.println("succes");
			
		} else {
			System.out.println("no");
		}
		
	}

}
