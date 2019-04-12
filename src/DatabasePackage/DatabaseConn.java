package DatabasePackage;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DatabaseConn {
	private static final String USER = "root";
	private static final String PASSWORD = "password";
    private static ComboPooledDataSource cpds = new ComboPooledDataSource();
 
    static {
        try {
            cpds.setDriverClass("com.mysql.cj.jdbc.Driver");
            cpds.setJdbcUrl("jdbc:mysql://localhost:3306");
            cpds.setUser(USER);
            cpds.setPassword(PASSWORD);
        } catch (PropertyVetoException pve) {
            System.out.println("pve: " + pve.getMessage());
        }
    }
     
    public static Connection getConnection() throws SQLException {
        return cpds.getConnection();
    }
     
    public static void closeConnection(Connection conn) throws SQLException {
		if (conn != null) {
			conn.close();
		}
    }
    
    private DatabaseConn(){}
}