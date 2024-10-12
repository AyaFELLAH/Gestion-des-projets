package presentationLayer.controllers.notification;

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
import businessLayer.UserManagment;
import businessLayer.UserManagmentInterface;
import presentationLayer.models.Notification;
import presentationLayer.models.User;

/**
 * Servlet implementation class NotificationServlet
 */
@WebServlet("/NotificationServlet")
public class NotificationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NotificationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		if(user != null &&( user.getRole().equals("chefProject") || user.getRole().equals("devlopper") ) ) {
			
			String pathInfo = request.getPathInfo();
			String id = pathInfo.substring(1);

			NotificationManagmentInterface notificationManagment = new NotificationManagment();
			// sending the notifications
			request.setAttribute("notifications", notificationManagment.getUserNotification(user));
			
			if (id.equals("-1") ) {
				
				// sending all notifications
				request.setAttribute("userNotifications", notificationManagment.getAllUserNotification(user));
				
			} else {
				
				List<Notification> notifications = new ArrayList<Notification>();
				
				// update notification to be seen
				if (notificationManagment.updateNotificationStatusByID(Integer.parseInt(id)) == 1) {
				
					// sending the specific notification
					Notification notification = notificationManagment.getUserNotificationByID(Integer.parseInt(id));
					notifications.add(notification);
					
				}
				request.setAttribute("userNotifications", notifications);
				
			}
			request.setAttribute("active", "");
			// sending the notifications
			request.setAttribute("notifications", notificationManagment.getUserNotification(user));
			request.getRequestDispatcher("/WEB-INF/views/notifications/notification.jsp").forward(request, response);
	
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
