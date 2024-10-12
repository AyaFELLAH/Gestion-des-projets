package presentationLayer.controllers.director;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import businessLayer.ProjectManagment;
import businessLayer.ProjectManagmentInterface;

/**
 * Servlet implementation class VerifyProjectServlet
 */
public class VerifyProjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VerifyProjectServlet() {
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
		
		String nameProject = request.getParameter("projectName");
		ProjectManagmentInterface projectManagment = new ProjectManagment();
		int nombreOcc = projectManagment.verifyProjectNameExistance(nameProject);
	
		JSONObject jsonNumber = new JSONObject();
		try {
			jsonNumber.put("nombreOCC", nombreOcc);
		} catch (JSONException e) {
			System.err.println("***** erreur d'envoie de nombre de noms *****");
		}
		
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(jsonNumber.toString());
	}
}
