import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionDatabase {
	
	public static Connection getConnection() {
		Connection conn = null;
		
	    String sqlInstanceName = "DESKTOP-U574UCI\\\\SQLEXPRESS:1433";
	    String database = "StudentManager";
	    String userName = "sa";
	    String password = "123456";
		
        // Create a variable for the connection string.
        String connectionUrl = "jdbc:sqlserver://" + sqlInstanceName + ";databaseName=" + database + ";user=" + userName + ";password=" + password;
        
        try{
        	conn = DriverManager.getConnection(connectionUrl); 
        	
        	if (conn != null) {
                System.out.println("Connected");
            }else {
            	System.out.println("Khong ket noi duoc voi database");
            }
        	
            return conn;
        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Khong ket noi duoc voi database");
            return conn;
        }
    }
}
