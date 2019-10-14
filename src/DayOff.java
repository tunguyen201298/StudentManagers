import java.sql.Connection;
import java.util.Date;  
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DayOff {
	
	private Connection conn = null;
	
	public DayOff(Connection conn) {
		this.conn = conn;
	}
	
	public ResultSet getDayOffs() {
		try {
			Statement statement = conn.createStatement();
			String sql = "SELECT * FROM DayOff";
			ResultSet rs = statement.executeQuery(sql);
			return rs;
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("Loi");
			return null;
		}
	}
	
	public ResultSet searchs(String studentName, String dayOff) {
		try {
			Statement statement = conn.createStatement();
			StringBuilder sb = new StringBuilder("SELECT s.*, do.day_off FROM DayOff do LEFT JOIN Student s ON do.student_id = s.id WHERE 1 = 1");

			if(studentName != null && !studentName.isEmpty()) {
				sb.append(" AND s.name LIKE N'%" + studentName + "%'");
			}
			
			if(dayOff != null && !dayOff.isEmpty()) {
				sb.append(" AND do.day_off = '" + dayOff + "'");
			}
			
	        String sql = sb.toString();
	        
			ResultSet rs = statement.executeQuery(sql);
			return rs;
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("Loi");
			return null;
		}
	}
	
	public void insert(int studentId, String dayOff) {
		try {
			Statement statement = conn.createStatement();
			
			String sql = "INSERT INTO DayOff (student_id, day_off) VALUES(" + studentId  + ", '"+ dayOff +"')";
			
			int rs = statement.executeUpdate(sql);
			
			System.out.println(rs);
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
