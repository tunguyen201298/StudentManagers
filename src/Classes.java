import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Classes {
	
	private Connection conn = null;
	
	public Classes(Connection conn) {
		this.conn = conn;
	}
	
	public ResultSet getClasses() {
		try {
			Statement statement = conn.createStatement();
			String sql = "SELECT * FROM Classes";
			ResultSet rs = statement.executeQuery(sql);
			return rs;
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("Loi");
			return null;
		}
	}
	
	public ResultSet searchs(String name) {
		try {
			Statement statement = conn.createStatement();
			StringBuilder sb = new StringBuilder("SELECT *, (SELECT COUNT(*) FROM ClassStudent WHERE Classes.id = ClassStudent.class_id) AS total FROM Classes WHERE 1 = 1");

			if(name != null && !name.isEmpty()) {
				sb.append(" AND class_name LIKE N'%" + name + "%'");
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
	
	public void insert(String className) {
		try {
			Statement statement = conn.createStatement();
			
			String sql = "INSERT INTO Classes (class_name) VALUES(N'" + className + "')";
			
			int rs = statement.executeUpdate(sql);
			
			System.out.println(rs);
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public void delete(int id) {
		try {
			Statement statement = conn.createStatement();
			
			String sql = "DELETE FROM Classes WHERE id=" + id;
			
			int rs = statement.executeUpdate(sql);
			
			System.out.println(rs);
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
