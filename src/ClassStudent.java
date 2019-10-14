import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ClassStudent {
	
	private Connection conn = null;
	
	public ClassStudent(Connection conn) {
		this.conn = conn;
	}
	
	public void insert(int classId, int studentId) {
		try {
			Statement statement = conn.createStatement();
			
			String sql = "INSERT INTO ClassStudent (class_id, student_id) VALUES(" + classId + ", " + studentId + ")";
			
			int rs = statement.executeUpdate(sql);
			
			System.out.println(rs);
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
