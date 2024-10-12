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
import businessLayer.ProjectManagmentInterface;
import businessLayer.TechnologieManagment;
import businessLayer.TechnologieManagmentInterface;
import businessLayer.UserManagment;
import businessLayer.UserManagmentInterface;
import presentationLayer.models.Technology;
import presentationLayer.models.User;

/**
 * Servlet implementation class ProfilDevServlet
 */
@WebServlet("/ProfilDevServlet")
public class ProfilDevServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    UserManagment usermanagment=new UserManagment();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfilDevServlet() {
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
			// sending the notifications
			NotificationManagmentInterface notificationManagment = new NotificationManagment();
			request.setAttribute("notifications", notificationManagment.getUserNotification(user));
	
			request.setAttribute("active", "profil");
			request.setAttribute("developper", user);
			
			TechnologieManagmentInterface technologieManagment = new TechnologieManagment();
			List<Technology> technologies = technologieManagment.getTechnologiesByDevelopper(user.getId());
			request.setAttribute("technologies", technologies);
			
			for (Technology techno: technologies) { 
				
				System.out.println(techno.getNom());
			}
			
			request.getRequestDispatcher("/WEB-INF/views/devlopper/profilDev.jsp").forward(request, response);
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
