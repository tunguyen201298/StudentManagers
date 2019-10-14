import java.text.SimpleDateFormat;  
import java.util.Date;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Manager {

	public static void main(String[] args) {
		
		Connection conn = ConnectionDatabase.getConnection();
		
		//
		Student student = new Student(conn);
		Classes classes = new Classes(conn);
		DayOff dayOff = new DayOff(conn);
		
		Scanner scanner = new Scanner(System.in);
		
		StringBuilder sb = new StringBuilder("");
		sb.append("Chuc nang quan ly: \n");
        sb.append("1. Them 1 sinh vien. \n");
        sb.append("2. Them 1 lop hoc. \n");
        sb.append("3. Dang ky lop cho Sinh Vien. \n");
        sb.append("4. Dang ky ngay nghi cho Sinh Vien. \n");
        sb.append("5. Tim kiem Sinh Vien. \n");
        sb.append("6. Tim kiem lop hoc. \n");
        sb.append("7. Xoa hoc sinh. \n");
        sb.append("8. Xoa lop hoc. \n");
        sb.append("9. Tim kiem ngay nghi hoc sinh. \r\n");
        String text = sb.toString();
		System.out.println(text);
		System.out.println("======================================= \n Chon chuc nang:");
		
		int feature = scanner.nextInt();
		scanner.nextLine();
		
		ResultSet students = student.getStudents();
		
		switch (feature) {
		case 1:
			System.out.println("Them moi sinh vien: \n");
	        System.out.print("\tTen: ");
	        String name = scanner.nextLine();
	        
	        System.out.print("\tTuoi: ");
	        int age = scanner.nextInt();
	        scanner.nextLine();
	        
	        student.insert(name, age);
			break;
		case 2:
			System.out.println("Them moi lop hoc: \n");
	        System.out.print("\tTen: ");
	        String className = scanner.nextLine();
	       
	        
	        classes.insert(className);
	        break;
		case 3: 
			
		   int studentId = 0;
		   int classId = 0;
			
		   ClassStudent classStudent = new ClassStudent(conn);
		   System.out.println("Dang ky lop hoc cho sinh vien: \n");
		   
			try {
				
				System.out.println("  Id     Ten sinh vien\n");
				while(students.next()) {
					   System.out.println("  " + students.getInt("id") + ".     " + students.getString("name") + ".");
				   }
				
				System.out.print("Chon sinh vien theo Id:");
			    studentId = scanner.nextInt();
		        scanner.nextLine();
		        System.out.println("===============================");
		        
		        
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			ResultSet classLists = classes.getClasses();
			   
			try {
				
				System.out.println("  Id     Ten lop hoc\n");
				while(classLists.next()) {
					   System.out.println("  " + classLists.getInt("id") + ".     " + classLists.getString("class_name") + ".");
				   }
				
				System.out.print("Chon lop hoc theo Id:");
				classId = scanner.nextInt();
		        scanner.nextLine();
		        System.out.println("===============================");
		        
		        
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
			if(classId> 0 && studentId > 0) {
				classStudent.insert(classId, studentId);
			}
			break;
		case 4: 
			   int studentayOffId = 0;
			
			   System.out.println("Dang ky ngay nghi cho sinh vien: \n");

				try {
					
					System.out.println("  Id     Ten sinh vien\n");
					while(students.next()) {
						   System.out.println("  " + students.getInt("id") + ".     " + students.getString("name") + ".");
					   }
					
					System.out.print("Chon sinh vien theo Id:");
					studentayOffId = scanner.nextInt();
			        scanner.nextLine();
			        System.out.println("==============================="); 
			        
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				System.out.print("\tNgay nghi (dd/MM/yyyy): ");
		        String day = scanner.nextLine();
		        
		        if(HelperValidator.isThisDateValid(day, "dd/MM/yyyy")) {
		        	dayOff.insert(studentayOffId, day);
		        }else {
		        	System.out.println("Them du lieu that bai. Dinh dang ngay gio sai"); 
		        }
				
			break;
		case 5: 
			
			System.out.println("Tim kiem sinh vien: \n");
			
	        System.out.print("\tId: ");
	        
	        int idSearch = 0;
	        String idCheck = scanner.nextLine();
	        if (HelperValidator.isInteger(idCheck))
	        {
	        	idSearch = Integer.parseInt(idCheck);
	        }
	        
	        System.out.print("\tTen: ");
	        String nameSearch = scanner.nextLine();
	        
	        System.out.print("\tTuoi: ");
	        
	        int ageSearch = 0;
	        String ageCheck = scanner.nextLine();
	        if (HelperValidator.isInteger(ageCheck))
	        {
	        	ageSearch = Integer.parseInt(ageCheck);
	        }
			
	        ResultSet studentSearchs = student.searchs(idSearch, nameSearch, ageSearch);
			
			try {
				System.out.println("KET QUA");
				System.out.println("===============================");
				System.out.println("  Id     Ten sinh vien     Tuoi\n");
				while(studentSearchs.next()) {
					   System.out.println("  " + studentSearchs.getInt("id") + "     " + studentSearchs.getString("name") + "     " + studentSearchs.getInt("age"));
				   }	
		        		        
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			break;
		case 6:
			System.out.println("Tim kiem lop hoc: \n");
			
	        System.out.print("\tTen lop hoc: ");
	        String nameClassSearch = scanner.nextLine();
			
	        ResultSet classtSearchs = classes.searchs(nameClassSearch);
			
			try {
				System.out.println("KET QUA");
				System.out.println("===============================");
				System.out.println("  Id     Ten lop hoc     Si so\n");
				while(classtSearchs.next()) {
					   System.out.println("  " + classtSearchs.getInt("id") + "     " + classtSearchs.getString("class_name") + "     " + classtSearchs.getInt("total"));
				   }	
		        		        
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			break;
		case 7:
			System.out.print("Nhap id sinh vien can xoa:");
			int studentDeleteId = scanner.nextInt();
	        scanner.nextLine();
	        
	        student.delete(studentDeleteId);
			break;
		case 8:
			System.out.print("Nhap id lop can xoa:");
			int classDeleteId = scanner.nextInt();
	        scanner.nextLine();
	        
	        classes.delete(classDeleteId);
			break;
	    case 9:
	    	
	        System.out.print("\tTen sinh vien: ");
	        String studentName = scanner.nextLine();
	    	
			System.out.print("\tNgay nghi (dd/MM/yyyy): ");
	        String dayOffSearch = scanner.nextLine();
	        
	        if(dayOffSearch != null && !dayOffSearch.isEmpty()) {
		        if(HelperValidator.isThisDateValid(dayOffSearch, "dd/MM/yyyy")) {
		        	
		        }else {
		        	System.out.println("Them du lieu that bai. Dinh dang ngay gio sai"); 
		        }
	        }
	        
	        ResultSet dayOffLists = dayOff.searchs(studentName, dayOffSearch);
        	
        	try {
				System.out.println("KET QUA");
				System.out.println("===============================");
				System.out.println("  Id     Ten hoc sinh     Ngay\n");
				while(dayOffLists.next()) {
					   System.out.println("  " + dayOffLists.getInt("id") + "     " + dayOffLists.getString("name") + "     " + dayOffLists.getDate("day_off"));
				   }	
		        		        
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    	break;
		default:
			break;
		}
		

	}

}
