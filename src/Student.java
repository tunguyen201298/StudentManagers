import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Student {
	
	private Connection conn = null;
	
	public Student(Connection conn) {
		this.conn = conn;
	}
	
	public ResultSet getStudents() {
		try {
			Statement statement = conn.createStatement();
			String sql = "SELECT * FROM Student";
			ResultSet rs = statement.executeQuery(sql);
			return rs;
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("Loi");
			return null;
		}
	}
	
	public ResultSet searchs(int id, String name, int age) {
		try {
			Statement statement = conn.createStatement();
			StringBuilder sb = new StringBuilder("SELECT * FROM Student WHERE 1 = 1");
			
			if(id > 0) {
				sb.append(" AND id = " + id);
			}
			
			if(name != null && !name.isEmpty()) {
				sb.append(" AND name LIKE N'%" + name + "%'");
			}
			
			if(age > 0) {
				sb.append(" AND age = " + age);
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
	
	public void insert(String name, int age) {
		try {
			Statement statement = conn.createStatement();
			
			String sql = "INSERT INTO Student (name, age) VALUES(N'" + name + "', " + age + ")";
			
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
			
			String sql = "DELETE FROM Student WHERE id=" + id;
			
			int rs = statement.executeUpdate(sql);
			
			System.out.println(rs);
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
