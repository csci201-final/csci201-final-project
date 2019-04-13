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
			conn = DatabaseConn.getConnection();
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
			conn = DatabaseConn.getConnection();
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
			conn = DatabaseConn.getConnection();
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
			conn = DatabaseConn.getConnection();
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
	
	public static Vector<Integer> getAttending(int eventID) {
		Vector<Integer> attending = new Vector<Integer>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DatabaseConn.getConnection();
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
	
	public static Vector<Integer> getNotInterested(int eventID) {
		Vector<Integer> notInterested = new Vector<Integer>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DatabaseConn.getConnection();
			ps = conn.prepareStatement("SELECT userID FROM NotInterested WHERE eventID=?");
			ps.setInt(1, eventID);
			rs = ps.executeQuery();
			while (rs.next()) {
				notInterested.add(rs.getInt("userID"));
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
		return notInterested;
	}
	
	public static Vector<Event> getCurrentEvents() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Vector<Event> currentEvents = new Vector<Event>();
		try {
			conn = DatabaseConn.getConnection();
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
					Vector<Integer> notInterested = getNotInterested(eventID);
					
					Event e = new Event(hostID, day, beginTime, endTime, name, place, tags, affiliation, details, attending, interested, notInterested);
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
}
