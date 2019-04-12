package DatabasePackage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class DatabaseInsert {
		public static boolean registerUser(String username, String password, String email, String bio, String picPath, String address) {
			// inserts user into database if user does not already exist
			if (DatabaseQuery.getUserID(username) == -1) {
				return false;
			}
			insertUser(username,password,email,bio,picPath,address);
			return true;
		}
	
	
		private static void insertUser(String username, String password, String email, String bio, String picPath, String address) {
			Connection conn = null;
			PreparedStatement ps = null;
			try {
				conn = DatabaseConn.getConnection();
				ps = conn.prepareStatement("INSERT INTO User (username,password,email,bio,picPath,address) VALUES(?,?,?,?,?,?)");
				ps.setString(1, username);
				ps.setString(2, password);
				ps.setString(3, email);
				ps.setString(4, bio);
				ps.setString(5, picPath);
				ps.setString(6, address);
				ps.execute();
			} catch (SQLException sqle) {
				System.out.println("sqle: " + sqle.getMessage());
			} finally {
				try {
					DatabaseConn.closeConnection(conn);
					DatabaseManager.closeUtil(ps);	
				} catch (SQLException sqle) {
					System.out.println("sqle: " + sqle.getMessage());
				}
			}
		}
		
		public static void insertEvent(String username, String eventname, String place, Timestamp time, String details, String tags) {
			Connection conn = null;
			PreparedStatement ps = null;
			try {
				conn = DatabaseConn.getConnection();
				ps = conn.prepareStatement("INSERT INTO Event (name,place,time,host,details,tags) VALUES(?,?,?,?,?,?)");
				ps.setString(1, eventname);
				ps.setString(2, place);
				ps.setTimestamp(3, time);
				ps.setInt(4, DatabaseQuery.getUserID(username));
				ps.setString(5, details);
				ps.setString(6, tags);
				ps.execute();
			} catch (SQLException sqle) {
				System.out.println("sqle: " + sqle.getMessage());
			} finally {
				try {
					DatabaseConn.closeConnection(conn);
					DatabaseManager.closeUtil(ps);
				} catch (SQLException sqle) {
					System.out.println("sqle: " + sqle.getMessage());
				}
			}
		}
		
		public static void insertEvent(String username, String eventname, String place, Timestamp time, String details) {
			Connection conn = null;
			PreparedStatement ps = null;
			try {
				conn = DatabaseConn.getConnection();
				ps = conn.prepareStatement("INSERT INTO Event (name,place,time,host,details) VALUES(?,?,?,?,?)");
				ps.setString(1, eventname);
				ps.setString(2, place);
				ps.setTimestamp(3, time);
				ps.setInt(4, DatabaseQuery.getUserID(username));
				ps.setString(5, details);
				ps.execute();
			} catch (SQLException sqle) {
				System.out.println("sqle: " + sqle.getMessage());
			} finally {
				try {
					DatabaseConn.closeConnection(conn);
					DatabaseManager.closeUtil(ps);
				} catch (SQLException sqle) {
					System.out.println("sqle: " + sqle.getMessage());
				}
			}
		}
		
		public static void insertAttended(String username, String eventname, int rating, String comments) {
			Connection conn = null;
			PreparedStatement ps = null;
			try {
				conn = DatabaseConn.getConnection();
				ps = conn.prepareStatement("INSERT INTO Attended (eventID,userID,rating,comments) VALUES(?,?,?,?)");
				ps.setInt(1, DatabaseQuery.getEventID(eventname));
				ps.setInt(2, DatabaseQuery.getUserID(username));
				ps.setInt(3, rating);
				ps.setString(4, comments);
				ps.execute();
			} catch (SQLException sqle) {
				System.out.println("sqle: " + sqle.getMessage());
			} finally {
				try {
					DatabaseConn.closeConnection(conn);
					DatabaseManager.closeUtil(ps);
				} catch (SQLException sqle) {
					System.out.println("sqle: " + sqle.getMessage());
				}
			}
		}
		
		public static void insertInterested(String username, String eventname) {
			Connection conn = null;
			PreparedStatement ps = null;
			try {
				conn = DatabaseConn.getConnection();
				ps = conn.prepareStatement("INSERT INTO Interested (eventID,userID) VALUES(?,?)");
				ps.setInt(1, DatabaseQuery.getEventID(eventname));
				ps.setInt(2, DatabaseQuery.getUserID(username));
				ps.execute();
			} catch (SQLException sqle) {
				System.out.println("sqle: " + sqle.getMessage());
			} finally {
				try {
					DatabaseConn.closeConnection(conn);
					DatabaseManager.closeUtil(ps);
				} catch (SQLException sqle) {
					System.out.println("sqle: " + sqle.getMessage());
				}
			}
		}
}
