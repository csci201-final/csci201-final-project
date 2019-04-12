package DatabasePackage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
}
