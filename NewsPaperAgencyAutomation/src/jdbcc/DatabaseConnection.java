package jdbcc;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
	public static Connection doConnect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/newspaperagency","root","");
			System.out.println("Connected...");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
	public static void main(String[] args) {
		Connection con = doConnect();
		if(con==null)
			System.out.println("Not Connected");
		else
			System.out.println("Connected");
	}
}
