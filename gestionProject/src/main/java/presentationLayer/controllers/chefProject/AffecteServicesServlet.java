package presentationLayer.controllers.chefProject;

import java.io.IOException;
import java.util.ArrayList;
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
import businessLayer.ProjectManagmentInterface;
import businessLayer.ServiceManagment;
import businessLayer.ServiceManagmentInterface;
import businessLayer.UserManagment;
import businessLayer.UserManagmentInterface;
import presentationLayer.models.Service;
import presentationLayer.models.Tache;
import presentationLayer.models.User;

/**
 * Servlet implementation class AffecteServicesServlet
 */
@WebServlet("/AffecteServicesServlet")
public class AffecteServicesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AffecteServicesServlet() {
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
		
			// sending the notifications
			NotificationManagmentInterface notificationManagment = new NotificationManagment();
			ServiceManagmentInterface serviceManagment = new ServiceManagment();
			UserManagmentInterface userManagment = new UserManagment();
			
			String pathInfo = request.getPathInfo();
			int idProject = Integer.parseInt(pathInfo.substring(1));
			List<Service> services = serviceManagment.getServicesByProject(idProject);
			List<User> developpeurs = userManagment.getDeveloppersByProject(""+idProject);
			
			request.setAttribute("idProject", idProject);
			request.setAttribute("developpeurs", developpeurs);
			request.setAttribute("services", services);
			request.setAttribute("notifications", notificationManagment.getUserNotification(user));
			request.setAttribute("active", "service");
			request.getRequestDispatcher("/WEB-INF/views/chefProject/affecte-service.jsp").forward(request, response);
			
		} else {
			response.sendRedirect("/gestionProject/login");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		if(user != null && user.getRole().equals("chefProject")) {
			
			String serviceName = request.getParameter("serviceName");
			String serviceDescription = request.getParameter("serviceDescription");
			int idProject = Integer.parseInt(request.getParameter("idProject"));
			int serviceJours = Integer.parseInt(request.getParameter("serviceJours"));
			
			
			String tachesNameJoined = request.getParameter("tachesName");
			String[] tachesName = tachesNameJoined.split(",");

			
			String tachesDescriptionJoined = request.getParameter("tachesDescription");
			String[] tachesDescription = tachesDescriptionJoined.split(",");

			
			String tachesDeveloppeurJoined = request.getParameter("tachesDeveloppeur");
			String[] tachesDeveloppeur = tachesDeveloppeurJoined.split(",");
			List<Integer> developpeurs = new ArrayList<Integer>();

			for (String dev:tachesDeveloppeur) {
				developpeurs.add(Integer.parseInt(dev));
			}
		
			// now we create the objects
			
			// service
			Service service = new Service();
			service.setNom(serviceName);
			service.setDescription(serviceDescription);
			service.setDuree(serviceJours);
			service.setIdProject(idProject);
			
			List<Tache> taches = new ArrayList<Tache>();
			for (int i=0;i<tachesName.length ; i++) {
				Tache tache = new Tache();
				tache.setNom(tachesName[i]);
				tache.setIdDeveloppeur(developpeurs.get(i));
				tache.setDescription(tachesDescription[i]);
				
				taches.add(tache);
			}
			
			service.setTaches(taches);
			
			ServiceManagmentInterface serviceManagment = new ServiceManagment();
			if(serviceManagment.addService(service) == 1) {
				System.out.println("***** La service et ses taches sont ajoutées *****");
			} else {
				System.out.println("***** Erreur : lors d'insertion de service et taches *****");
			}
			
		} else {
			response.sendRedirect("/gestionProject/login");
		}
	}

}
