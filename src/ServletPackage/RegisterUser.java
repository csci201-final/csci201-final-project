package ServletPackage;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DatabasePackage.DatabaseInsert;

@WebServlet("/RegisterUser")
public class RegisterUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String confirm = request.getParameter("confirmation");
		String email = request.getParameter("email");
		String bio = request.getParameter("bio");
		String picPath = request.getParameter("picPath");
		String nextPage = "/register.jsp";
		if (!password.equals(confirm)) {
			request.getSession(true).setAttribute("reg_err", "Passwords do not match");
		} else if (DatabaseInsert.registerUser(username, password, email, bio, picPath)) {
			request.getSession(true).setAttribute("reg_err", "Username already exists");
		} else {
			request.getSession(true).setAttribute("loggedin", true);
			request.getSession(true).setAttribute("username", username);
			nextPage = "/HomePage.jsp";
		}
		RequestDispatcher dispatch = getServletContext().getRequestDispatcher(nextPage);
		dispatch.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
