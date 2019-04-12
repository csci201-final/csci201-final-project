package DatabasePackage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseManager {
	
	public static boolean checkDatabaseExists() {
		boolean found = false;
		Connection conn = null;
		try {
			conn = DatabaseConn.getConnection();
			ResultSet databases = conn.getMetaData().getCatalogs();
			String db = "PartyPeople";
			while (databases.next()) {
				if (databases.getString(1).equals(db)) {
					found = true;
				}
			}
		} catch (SQLException sqle) {
			System.out.println("sqle: " + sqle.getMessage());
		} finally {
			try {
				DatabaseConn.closeConnection(conn);
			} catch (SQLException sqle) {
				System.out.println("sqle: " + sqle.getMessage());
			}
		}
		return found;
	}
	
	@SuppressWarnings("null")
	public static void createDatabase() {
		Connection conn = null;
		PreparedStatement ps = null;
		
		String createDB = "CREATE DATABASE PartyPeople IF NOT EXISTS";
		String useDB = "USE PartyPeople";
		
		String userTable = "CREATE TABLE User(" +
							"userID INT(11) PRIMARY KEY AUTO_INCREMENT, " +
							"username VARCHAR(45) NOT NULL, " +
							"password VARCHAR(45) NOT NULL, " +
							"email VARCHAR(45) NOT NULL, " +
							"bio VARCHAR(5000) NOT NULL, " +
							"picPath VARCHAR(200) NOT NULL, " +
							"hostRating FLOAT(3,2), " +
							"pastEvents VARCHAR(1000), " +
							"interestedEvents VARCHAR(1000));";
		
		String eventTable = "CREATE TABLE Event(" +
							"eventID INT(11) PRIMARY KEY AUTO_INCREMENT, " +
							"name VARCHAR(50) NOT NULL, " +
							"place VARCHAR(200) NOT NULL, " +
							"time DATETIME() NOT NULL, " +
							"host INT(11) NOT NULL, " +
							"details VARCHAR(5000) NOT NULL, " +
							"eventRating FLOAT(3,2), " +
							"attendees VARCHAR(1000), " +
							"tags VARCHAR(1000), " +
							"FOREIGN KEY fk1(host) REFERENCES UserInfo(userID));";
		
		String attendTable = "CREATE TABLE Attended(" +
							"eventID INT(11) NOT NULL, " +
							"userID INT(11) NOT NULL, " +
							"rating INT(1) NOT NULL, " +
							"comments VARCHAR(1000), " +
							"FOREIGN KEY fk2(eventID) REFERENCES Event(eventID), " +
							"FOREIGN KEY fk3(userID) REFERENCES UserInfo(userID));";
		
		String interestTable = "CREATE TABLE Interested(" +
							"eventID INT(11) NOT NULL, " +
							"userID INT(11) NOT NULL, " +
							"FOREIGN KEY fk4(eventID) REFERENCES Event(eventID), " +
							"FOREIGN KEY fk5(userID) REFERENCES UserInfo(userID));";
		try {
			conn = DatabaseConn.getConnection();
			conn.setAutoCommit(false);
			ps.addBatch(createDB);
			ps.addBatch(useDB);
			ps.addBatch(userTable);
			ps.addBatch(eventTable);
			ps.addBatch(attendTable);
			ps.addBatch(interestTable);
			ps.executeBatch();
			conn.commit();
			conn.setAutoCommit(true);
		} catch (SQLException sqle) {
			System.out.println("sqle: " + sqle.getMessage());
		} finally {
			try {
				DatabaseConn.closeConnection(conn);
				closeUtil(ps);
			} catch (SQLException sqle) {
				System.out.println("sqle: " + sqle.getMessage());
			}
		}
	}
	
	public static void closeUtil(PreparedStatement ps, ResultSet rs) throws SQLException {
		if (rs != null) {
			rs.close();
		}
		if (ps != null) {
			ps.close();
		}
	}
	
	public static void closeUtil(PreparedStatement ps) throws SQLException {
		if (ps != null) {
			ps.close();
		}
	}
}
