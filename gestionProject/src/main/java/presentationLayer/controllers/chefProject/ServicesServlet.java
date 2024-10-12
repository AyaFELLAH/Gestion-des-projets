package presentationLayer.controllers.chefProject;

import java.io.IOException;
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
import presentationLayer.models.User;

/**
 * Servlet implementation class ServicesServlet
 */
@WebServlet("/ServicesServlet")
public class ServicesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServicesServlet() {
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
			request.setAttribute("notifications", notificationManagment.getUserNotification(user));
			
			request.setAttribute("active", "service");
			
			// sending the projects
			ProjectManagmentInterface projectManagment = new ProjectManagment();
			request.setAttribute("projects", projectManagment.getProjectsByChef(user.getId()));
			
			request.getRequestDispatcher("/WEB-INF/views/chefProject/services.jsp").forward(request, response);
			
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
