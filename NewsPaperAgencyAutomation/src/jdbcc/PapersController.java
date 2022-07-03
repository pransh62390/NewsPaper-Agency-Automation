package jdbcc;

import java.sql.Connection;
import jdbcc.DatabaseConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class PapersController {
	Connection con;
	PreparedStatement pstmt;
	
	public PapersController() {
		con = DatabaseConnection.doConnect();
	}
	
	public String insertRecord(String paper, float price)
	{
		try {
			pstmt = con.prepareStatement("insert into papers values(?, ?)");
			pstmt.setString(1, paper);
			pstmt.setFloat(2, price);
			pstmt.executeUpdate();
			return "Record inserted...";
		}catch(Exception e){
			e.printStackTrace();
			return "Data Already exists";
		}
	}
	
	public String updateRecord(String paper, float price)
	{
		try {
			pstmt = con.prepareStatement("update papers set price=? where paper=?");
			pstmt.setString(2, paper);
			pstmt.setFloat(1, price);
			int count = pstmt.executeUpdate();
			return count + " Record updated...";
		}catch(Exception e){
			e.printStackTrace();
			return "Check your data again...";
		}
	}
	
	public String searchRecord(String paper) {
		try {
			pstmt = con.prepareStatement("select * from papers where paper=?");
			pstmt.setString(1, paper);
			ResultSet res = pstmt.executeQuery();
			String arr = "";
			while(res.next()){
				arr += res.getFloat("price");
			}
			return(arr);
		}catch(Exception e) {
			e.printStackTrace();
			return("");
		}
	}
	
	public ArrayList<String> fetchKeys() {
		try {
			pstmt = con.prepareStatement("select paper from papers");
			ResultSet res = pstmt.executeQuery();
			ArrayList<String> arr= new ArrayList<String>();
			while(res.next())
			{
				arr.add(res.getString("paper"));
			}
			return(arr);
		}catch(Exception e) {
			e.printStackTrace();
			return(new ArrayList<String>());
		}
	}
	
	public String deleteRecord(String paper) {
		try {
			pstmt = con.prepareStatement("delete from papers where paper = ?");
			pstmt.setString(1, paper);
			int count = pstmt.executeUpdate();
			if(count==0)
			{
				return("Invalid Paper Name");
			}
			else
			{
				return(count+" Records Deleted");
			}
		}catch(Exception e) {
			e.printStackTrace();
			return "Some error occured...";
		}
	}

}
