package presentationLayer.controllers.devlopper;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import businessLayer.NotificationManagment;
import businessLayer.NotificationManagmentInterface;
import businessLayer.ProjectManagment;
import businessLayer.ServiceManagment;
import businessLayer.TacheManagment;
import businessLayer.TacheManagmentInterface;
import presentationLayer.models.Project;
import presentationLayer.models.Service;
import presentationLayer.models.Tache;
import presentationLayer.models.User;

/**
 * Servlet implementation class ConsulterProjetServiceServlet
 */
@WebServlet("/ConsulterProjetServiceServlet")
public class ConsulterProjetServiceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ServiceManagment servicemanagment = new ServiceManagment();
	ProjectManagment projectmanagment=new ProjectManagment();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConsulterProjetServiceServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		if(user != null && user.getRole().equals("devlopper")) {
			
			String pathInfo = request.getPathInfo();
			int projectId = Integer.parseInt(pathInfo.substring(1));
	
			// TODO Auto-generated method stub 
			Project projet=projectmanagment.getProjectByid(projectId);
			
			List<Service> services = servicemanagment.getServicesByProject(projectId);
			request.setAttribute("projet", projet);
			request.setAttribute("services", services);
	
			// sending the notifications
			NotificationManagmentInterface notificationManagment = new NotificationManagment();
			request.setAttribute("notifications", notificationManagment.getUserNotification(user));
			request.setAttribute("active", "projet");
			
			request.getRequestDispatcher("/WEB-INF/views/devlopper/consulterProjet.jsp").forward(request, response);
		
		} else {
			response.sendRedirect("/gestionProject/login");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int idTache = Integer.parseInt(request.getParameter("idTechnologie"));
		int progress = Integer.parseInt(request.getParameter("technologieValue"));
		TacheManagmentInterface tacheManagment = new TacheManagment();
		
		tacheManagment.changeTacheProgress(idTache, progress);
	}

}
