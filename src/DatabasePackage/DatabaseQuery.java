package DatabasePackage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Vector;

import EventPackage.Event;

public class DatabaseQuery {
	
	
	public static int validateUser(String username, String password) {
		int success = -1; // user not found
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DatabaseConn.getConnection("PartyPeople");
			ps = conn.prepareStatement("SELECT * FROM User WHERE username=?");
			ps.setString(1, username);
			rs = ps.executeQuery();
			if (rs.next()) {
				if (rs.getString("password").equals(password)) {
					success = 1; // user found and password matches
				} else {
					success = -2; // user found but password doesn't match
				}
			}
		} catch (SQLException sqle) {
			System.out.println("sqle: " + sqle.getMessage());
		} finally {
			try {
				DatabaseConn.closeConnection(conn);
				DatabaseManager.closeUtil(ps,rs);
			} catch (SQLException sqle) {
				System.out.println("sqle: " + sqle.getMessage());
			}
		}
		return success;
	}
	
	public static int getUserID(String username) {
		int id = -1;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DatabaseConn.getConnection("PartyPeople");
			ps = conn.prepareStatement("SELECT userID FROM User WHERE username=?");
			ps.setString(1, username);
			rs = ps.executeQuery();
			if (rs.next()) {
				id = rs.getInt("userID");
			}
		} catch (SQLException sqle) {
			System.out.println("sqle: " + sqle.getMessage());
		} finally {
			try {
				DatabaseConn.closeConnection(conn);
				DatabaseManager.closeUtil(ps,rs);
			} catch (SQLException sqle) {
				System.out.println("sqle: " + sqle.getMessage());
			}
		}
		return id;
	}
	
	public static int getEventID(String eventname) {
		int id = -1;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DatabaseConn.getConnection("PartyPeople");
			ps = conn.prepareStatement("SELECT eventID FROM Event WHERE name=?");
			ps.setString(1, eventname);
			rs = ps.executeQuery();
			if (rs.next()) {
				id = rs.getInt("eventID");
			}
		} catch (SQLException sqle) {
			System.out.println("sqle: " + sqle.getMessage());
		} finally {
			try {
				DatabaseConn.closeConnection(conn);
				DatabaseManager.closeUtil(ps,rs);
			} catch (SQLException sqle) {
				System.out.println("sqle: " + sqle.getMessage());
			}
		}
		return id;
	}
	
	public static Vector<Integer> getInterested(int eventID) {
		Vector<Integer> interested = new Vector<Integer>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DatabaseConn.getConnection("PartyPeople");
			ps = conn.prepareStatement("SELECT userID FROM Interested WHERE eventID=?");
			ps.setInt(1, eventID);
			rs = ps.executeQuery();
			while (rs.next()) {
				interested.add(rs.getInt("userID"));
			}
		} catch (SQLException sqle) {
			System.out.println("sqle: " + sqle.getMessage());
		} finally {
			try {
				DatabaseConn.closeConnection(conn);
				DatabaseManager.closeUtil(ps,rs);
			} catch (SQLException sqle) {
				System.out.println("sqle: " + sqle.getMessage());
			}
		}
		return interested;
	}
	
	public static String getPicPath(String username) {
		String path = "";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DatabaseConn.getConnection("PartyPeople");
			ps = conn.prepareStatement("SELECT picPath FROM User WHERE username=?");
			ps.setString(1, username);
			rs = ps.executeQuery();
			if (rs.next()) {
				path = rs.getString("picPath");
			}
		} catch (SQLException sqle) {
			System.out.println("sqle: " + sqle.getMessage());
		} finally {
			try {
				DatabaseConn.closeConnection(conn);
				DatabaseManager.closeUtil(ps,rs);
			} catch (SQLException sqle) {
				System.out.println("sqle: " + sqle.getMessage());
			}
		}
		return path;
	}
	
	public static Vector<Integer> getAttending(int eventID) {
		Vector<Integer> attending = new Vector<Integer>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DatabaseConn.getConnection("PartyPeople");
			ps = conn.prepareStatement("SELECT userID FROM Attending WHERE eventID=?");
			ps.setInt(1, eventID);
			rs = ps.executeQuery();
			while (rs.next()) {
				attending.add(rs.getInt("userID"));
			}
		} catch (SQLException sqle) {
			System.out.println("sqle: " + sqle.getMessage());
		} finally {
			try {
				DatabaseConn.closeConnection(conn);
				DatabaseManager.closeUtil(ps,rs);
			} catch (SQLException sqle) {
				System.out.println("sqle: " + sqle.getMessage());
			}
		}
		return attending;
	}
	
	public static Vector<Integer> getNotAttending(int eventID) {
		Vector<Integer> notAttending = new Vector<Integer>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DatabaseConn.getConnection("PartyPeople");
			ps = conn.prepareStatement("SELECT userID FROM NotAttending WHERE eventID=?");
			ps.setInt(1, eventID);
			rs = ps.executeQuery();
			while (rs.next()) {
				notAttending.add(rs.getInt("userID"));
			}
		} catch (SQLException sqle) {
			System.out.println("sqle: " + sqle.getMessage());
		} finally {
			try {
				DatabaseConn.closeConnection(conn);
				DatabaseManager.closeUtil(ps,rs);
			} catch (SQLException sqle) {
				System.out.println("sqle: " + sqle.getMessage());
			}
		}
		return notAttending;
	}
	
	public static Vector<Event> getInterested_User(String username){
		Vector<Event> events = new Vector<Event>();
		int user_ID = getUserID(username);
		Vector<Event> allEvents = getCurrentEvents();
		for(Event e: allEvents) {
			Vector<Integer> interested = getInterested(e.getEventID());
			if(interested.contains(user_ID))
				events.add(e);
		}
		return events;
	}
	
	public static Vector<Event> getAttending_User(String username){
		Vector<Event> events = new Vector<Event>();
		int user_ID = getUserID(username);
		Vector<Event> allEvents = getCurrentEvents();
		for(Event e: allEvents) {
			Vector<Integer> interested = getAttending(e.getEventID());
			if(interested.contains(user_ID))
				events.add(e);
		}
		return events;
	}
	public static Vector<Event> getNotAttending_User(String username){
		Vector<Event> events = new Vector<Event>();
		int user_ID = getUserID(username);
		Vector<Event> allEvents = getCurrentEvents();
		for(Event e: allEvents) {
			Vector<Integer> interested = getNotAttending(e.getEventID());
			if(interested.contains(user_ID))
				events.add(e);
		}
		return events;
	}
	
	public static Vector<Event> getCurrentEvents() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Vector<Event> currentEvents = new Vector<Event>();
		try {
			conn = DatabaseConn.getConnection("PartyPeople");
			ps = conn.prepareStatement("SELECT * FROM Event");
			rs = ps.executeQuery();
			
			Timestamp curTime = new Timestamp(System.currentTimeMillis());
			
			while (rs.next()) {
				Timestamp begin = rs.getTimestamp("timeBegin");
				if (begin.after(curTime)) {
					int eventID = rs.getInt("eventID");
					int hostID = rs.getInt("host");
					SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
					SimpleDateFormat time = new SimpleDateFormat("HH:mm");
					Timestamp end = rs.getTimestamp("timeEnd");
					String beginTime = time.format(begin);
					String endTime = time.format(end);
					String day = date.format(begin);
					String name = rs.getString("name");
					String place = rs.getString("place");
					String details = rs.getString("details");
					String affiliation = rs.getString("affiliation");
					String tags = rs.getString("tags");
					Vector<Integer> interested = getInterested(eventID);
					Vector<Integer> attending = getAttending(eventID);
					Vector<Integer> notAttending = getNotAttending(eventID);
					
					Event e = new Event(eventID, hostID, day, beginTime, endTime, name, place, tags, affiliation, details, attending, interested, notAttending);
					currentEvents.add(e);
				}
			} 
		} catch (SQLException sqle) {
			System.out.println("sqle: " + sqle.getMessage());
		} finally {
			try {
				DatabaseConn.closeConnection(conn);
				DatabaseManager.closeUtil(ps,rs);
			} catch (SQLException sqle) {
				System.out.println("sqle: " + sqle.getMessage());
			}
		}
		return currentEvents;
	}
	
	public static Vector<Event> searchEvents(String search_string) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Vector<Event> currentEvents = new Vector<Event>();
		try {
			conn = DatabaseConn.getConnection("PartyPeople");
			ps = conn.prepareStatement("SELECT * FROM Event");
			rs = ps.executeQuery();
			
			Timestamp curTime = new Timestamp(System.currentTimeMillis());
			
			while (rs.next()) {
				Timestamp begin = rs.getTimestamp("timeBegin");
				if (begin.after(curTime)) {
					int eventID = rs.getInt("eventID");
					int hostID = rs.getInt("host");
					SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
					SimpleDateFormat time = new SimpleDateFormat("HH:mm");
					Timestamp end = rs.getTimestamp("timeEnd");
					String beginTime = time.format(begin);
					String endTime = time.format(end);
					String day = date.format(begin);
					String name = rs.getString("name");
					String place = rs.getString("place");
					String details = rs.getString("details");
					String affiliation = rs.getString("affiliation");
					String tags = rs.getString("tags");
					Vector<Integer> interested = getInterested(eventID);
					Vector<Integer> attending = getAttending(eventID);
					Vector<Integer> notAttending = getNotAttending(eventID);
					
					
					Event e = new Event(eventID, hostID, day, beginTime, endTime, name, place, tags, affiliation, details, attending, interested, notAttending);
					if(beginTime.contains(search_string))
						currentEvents.add(e);
					else if(endTime.contains(search_string))
						currentEvents.add(e);
					else if(day.contains(search_string))
						currentEvents.add(e);
					else if(name.contains(search_string))
						currentEvents.add(e);
					else if(place.contains(search_string))
						currentEvents.add(e);
					else if(details.contains(search_string))
						currentEvents.add(e);
					else if(affiliation.contains(search_string))
						currentEvents.add(e);
					else if(tags.contains(search_string))
						currentEvents.add(e);
					else
						return null;
						
				}
			} 
		} catch (SQLException sqle) {
			System.out.println("sqle: " + sqle.getMessage());
		} finally {
			try {
				DatabaseConn.closeConnection(conn);
				DatabaseManager.closeUtil(ps,rs);
			} catch (SQLException sqle) {
				System.out.println("sqle: " + sqle.getMessage());
			}
		}
		return currentEvents;
	}
	
	public static Vector<Event> getUserEvents(String username) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Vector<Event> userEvents = new Vector<Event>();
		try {
			conn = DatabaseConn.getConnection("PartyPeople");
			ps = conn.prepareStatement("SELECT * FROM Event WHERE userID=?");
			ps.setInt(1, getUserID(username));
			rs = ps.executeQuery();
			
			while (rs.next()) {
				int eventID = rs.getInt("eventID");
				int hostID = rs.getInt("host");
				SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
				SimpleDateFormat time = new SimpleDateFormat("HH:mm");
				Timestamp begin = rs.getTimestamp("timeBegin");
				Timestamp end = rs.getTimestamp("timeEnd");
				String beginTime = time.format(begin);
				String endTime = time.format(end);
				String day = date.format(begin);
				String name = rs.getString("name");
				String place = rs.getString("place");
				String details = rs.getString("details");
				String affiliation = rs.getString("affiliation");
				String tags = rs.getString("tags");
				Vector<Integer> interested = getInterested(eventID);
				Vector<Integer> attending = getAttending(eventID);
				Vector<Integer> notAttending = getNotAttending(eventID);
				
				Event e = new Event(eventID, hostID, day, beginTime, endTime, name, place, tags, affiliation, details, attending, interested, notAttending);
				userEvents.add(e);
			} 
		} catch (SQLException sqle) {
			System.out.println("sqle: " + sqle.getMessage());
		} finally {
			try {
				DatabaseConn.closeConnection(conn);
				DatabaseManager.closeUtil(ps,rs);
			} catch (SQLException sqle) {
				System.out.println("sqle: " + sqle.getMessage());
			}
		}
		return userEvents;
	}
	
	@SuppressWarnings("resource")
	public static Vector<Event> getUserInterested(String username) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Vector<Event> userInterested = new Vector<Event>();
		try {
			conn = DatabaseConn.getConnection("PartyPeople");
			ps = conn.prepareStatement("SELECT * FROM Interested WHERE userID=?");
			ps.setInt(1, getUserID(username));
			rs = ps.executeQuery();
			
			while (rs.next()) {
				int eventID = rs.getInt("eventID");
				ps = conn.prepareStatement("SELECT * FROM Event WHERE eventID=?");
				ps.setInt(1, eventID);
				rs = ps.executeQuery();
				if (rs.next()) {
					int hostID = rs.getInt("host");
					SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
					SimpleDateFormat time = new SimpleDateFormat("HH:mm");
					Timestamp begin = rs.getTimestamp("timeBegin");
					Timestamp end = rs.getTimestamp("timeEnd");
					String beginTime = time.format(begin);
					String endTime = time.format(end);
					String day = date.format(begin);
					String name = rs.getString("name");
					String place = rs.getString("place");
					String details = rs.getString("details");
					String affiliation = rs.getString("affiliation");
					String tags = rs.getString("tags");
					Vector<Integer> interested = getInterested(eventID);
					Vector<Integer> attending = getAttending(eventID);
					Vector<Integer> notAttending = getNotAttending(eventID);
					Event e = new Event(eventID, hostID, day, beginTime, endTime, name, place, tags, affiliation, details, attending, interested, notAttending);
					userInterested.add(e);
				}
			} 
		} catch (SQLException sqle) {
			System.out.println("sqle: " + sqle.getMessage());
		} finally {
			try {
				DatabaseConn.closeConnection(conn);
				DatabaseManager.closeUtil(ps,rs);
			} catch (SQLException sqle) {
				System.out.println("sqle: " + sqle.getMessage());
			}
		}
		return userInterested;
	}
	
	@SuppressWarnings("resource")
	public static Vector<Event> getUserAttending(String username) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Vector<Event> userAttending = new Vector<Event>();
		try {
			conn = DatabaseConn.getConnection("PartyPeople");
			ps = conn.prepareStatement("SELECT * FROM Attending WHERE userID=?");
			ps.setInt(1, getUserID(username));
			rs = ps.executeQuery();
			
			while (rs.next()) {
				int eventID = rs.getInt("eventID");
				ps = conn.prepareStatement("SELECT * FROM Event WHERE eventID=?");
				ps.setInt(1, eventID);
				rs = ps.executeQuery();
				if (rs.next()) {
					int hostID = rs.getInt("host");
					SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
					SimpleDateFormat time = new SimpleDateFormat("HH:mm");
					Timestamp begin = rs.getTimestamp("timeBegin");
					Timestamp end = rs.getTimestamp("timeEnd");
					String beginTime = time.format(begin);
					String endTime = time.format(end);
					String day = date.format(begin);
					String name = rs.getString("name");
					String place = rs.getString("place");
					String details = rs.getString("details");
					String affiliation = rs.getString("affiliation");
					String tags = rs.getString("tags");
					Vector<Integer> interested = getInterested(eventID);
					Vector<Integer> attending = getAttending(eventID);
					Vector<Integer> notAttending = getNotAttending(eventID);
					Event e = new Event(eventID, hostID, day, beginTime, endTime, name, place, tags, affiliation, details, attending, interested, notAttending);
					userAttending.add(e);
				}
			} 
		} catch (SQLException sqle) {
			System.out.println("sqle: " + sqle.getMessage());
		} finally {
			try {
				DatabaseConn.closeConnection(conn);
				DatabaseManager.closeUtil(ps,rs);
			} catch (SQLException sqle) {
				System.out.println("sqle: " + sqle.getMessage());
			}
		}
		return userAttending;
	}
	
	@SuppressWarnings("resource")
	public static Vector<Event> getUserNotAttending(String username) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Vector<Event> userNotAttending = new Vector<Event>();
		try {
			conn = DatabaseConn.getConnection("PartyPeople");
			ps = conn.prepareStatement("SELECT * FROM NotAttending WHERE userID=?");
			ps.setInt(1, getUserID(username));
			rs = ps.executeQuery();
			
			while (rs.next()) {
				int eventID = rs.getInt("eventID");
				ps = conn.prepareStatement("SELECT * FROM Event WHERE eventID=?");
				ps.setInt(1, eventID);
				rs = ps.executeQuery();
				if (rs.next()) {
					int hostID = rs.getInt("host");
					SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
					SimpleDateFormat time = new SimpleDateFormat("HH:mm");
					Timestamp begin = rs.getTimestamp("timeBegin");
					Timestamp end = rs.getTimestamp("timeEnd");
					String beginTime = time.format(begin);
					String endTime = time.format(end);
					String day = date.format(begin);
					String name = rs.getString("name");
					String place = rs.getString("place");
					String details = rs.getString("details");
					String affiliation = rs.getString("affiliation");
					String tags = rs.getString("tags");
					Vector<Integer> interested = getInterested(eventID);
					Vector<Integer> attending = getAttending(eventID);
					Vector<Integer> notAttending = getNotAttending(eventID);
					Event e = new Event(eventID, hostID, day, beginTime, endTime, name, place, tags, affiliation, details, attending, interested, notAttending);
					userNotAttending.add(e);
				}
			} 
		} catch (SQLException sqle) {
			System.out.println("sqle: " + sqle.getMessage());
		} finally {
			try {
				DatabaseConn.closeConnection(conn);
				DatabaseManager.closeUtil(ps,rs);
			} catch (SQLException sqle) {
				System.out.println("sqle: " + sqle.getMessage());
			}
		}
		return userNotAttending;
	}
}
