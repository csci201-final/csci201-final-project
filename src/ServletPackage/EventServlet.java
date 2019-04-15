package ServletPackage;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DatabasePackage.DatabaseInsert;

/**
 * Servlet implementation class EventServlet
 */
@WebServlet("/EventServlet")
public class EventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EventServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat time = new SimpleDateFormat("HH:mm");
		
		String name = (String)request.getAttribute("name");
		String location = (String)request.getAttribute("location");
		Date date = (Date)request.getAttribute("date");
		Timestamp timeBegin = (Timestamp)request.getAttribute("start_time");
		Timestamp timeEnd = (Timestamp)request.getAttribute("end_time");
		String details = (String)request.getAttribute("details");
		String affiliation = (String)request.getAttribute("affiliation");
		String tags = (String)request.getAttribute("tags");
		
		DatabaseInsert.insertEvent((String)request.getSession().getAttribute("username"), name, location, timeBegin, timeEnd, details, affiliation, tags);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
