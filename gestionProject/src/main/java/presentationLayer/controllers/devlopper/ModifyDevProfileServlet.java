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
import businessLayer.TechnologieManagment;
import businessLayer.TechnologieManagmentInterface;
import businessLayer.UserManagment;
import businessLayer.UserManagmentInterface;
import presentationLayer.models.Account;
import presentationLayer.models.Technology;
import presentationLayer.models.User;

/**
 * Servlet implementation class ModifyDevProfileServlet
 */

public class ModifyDevProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyDevProfileServlet() {
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
			
			request.getRequestDispatcher("/WEB-INF/views/devlopper/ModifyProfile.jsp").forward(request, response);
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
		
		if(user != null && user.getRole().equals("devlopper")) {
			
			String nomDev = request.getParameter("nomDev");
			String prenomDev = request.getParameter("prenomDev");
			String emailDev = request.getParameter("emailDev");
			
			User developpeur = new User();
			developpeur.setId(user.getId());
			
			developpeur.setNom(nomDev);
			developpeur.setPrenom(prenomDev);
			developpeur.setRole("devlopper");
			
			Account account = new Account();
			account.setLogin(emailDev);
			developpeur.setAccount(account);
			
			UserManagmentInterface userManagment = new UserManagment();
			if(userManagment.updateDevelopper(developpeur) == 1) {
				System.out.println("***** developper apdated ****");
				session.setAttribute("user", developpeur);
				
				String technologiesNameJoined = request.getParameter("techsName");
				String[] technologiesName = technologiesNameJoined.split(",");
				
				String techsDescriptionJoined = request.getParameter("techsDescription");
				String[] techsDescription = techsDescriptionJoined.split(",");
				
				TechnologieManagmentInterface technologieManagment = new TechnologieManagment();
				
				for (int i=0; i<techsDescription.length; i++) {
					Technology technology = new Technology();
					technology.setNom(technologiesName[i]);
					technology.setIdDevelopper(user.getId());
					technology.setDescription(techsDescription[i]);
					
					technologieManagment.addTechnology(technology);
					
					
				}
				
			} else {
				 System.out.println("***** le developpeur n'est pas modifié *****");
			}
			
		} else {
			response.sendRedirect("/gestionProject/login");
		}
	}

}
