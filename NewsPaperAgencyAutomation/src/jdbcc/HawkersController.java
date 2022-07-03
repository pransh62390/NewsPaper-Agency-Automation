package jdbcc;

import java.sql.Connection;
import java.sql.Date;

import jdbcc.DatabaseConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class HawkersController {
	Connection con;
	PreparedStatement pstmt;
	
	public HawkersController() {
		con = DatabaseConnection.doConnect();
	}
	
	public String insertRecord(String hname, String picpath, String address, String mobile, String doj, String sareas)
	{
		try {
			pstmt = con.prepareStatement("insert into hawkersmanager values(?, ?, ?, ? ,?, ?)");
			pstmt.setString(1, hname);
			pstmt.setString(2, picpath);
			pstmt.setString(3, address);
			pstmt.setString(4, mobile);
			pstmt.setDate(5, Date.valueOf(doj));
			pstmt.setString(6, sareas);
			pstmt.executeUpdate();
			return "Record inserted...";
		}catch(Exception e){
			e.printStackTrace();
			return "Data Already exists";
		}
	}
	
	public String updateRecord(String hname, String picpath, String address, String mobile, String sareas)
	{
		try {
			pstmt = con.prepareStatement("update hawkersmanager set picpath=?,address=?,mobile=?,sareas=? where hname=?");
			pstmt.setString(5, hname);
			pstmt.setString(1, picpath);
			pstmt.setString(2, address);
			pstmt.setString(3, mobile);
			pstmt.setString(4, sareas);
			int count = pstmt.executeUpdate();
			return count + " Record updated...";
		}catch(Exception e){
			e.printStackTrace();
			return "Check your data again...";
		}
	}
	
	public ArrayList<String> searchRecord(String hname) {
		try {
			pstmt = con.prepareStatement("select * from hawkersmanager where hname=?");
			pstmt.setString(1, hname);
			ResultSet res = pstmt.executeQuery();
			ArrayList<String> arr = new ArrayList<String>();
			while(res.next()){
				arr.add(res.getString("picpath"));
				arr.add(res.getString("address"));
				arr.add(res.getString("mobile"));
				arr.add("" + res.getString("doj"));
				arr.add(res.getString("sareas"));
			}
			return(arr);
		}catch(Exception e) {
			e.printStackTrace();
			return(new ArrayList<String>());
		}
	}
	
	public ArrayList<String> fetchKeys() {
		try {
			pstmt = con.prepareStatement("select hname from hawkersmanager");
			ResultSet res = pstmt.executeQuery();
			ArrayList<String> arr= new ArrayList<String>();
			while(res.next())
			{
				arr.add(res.getString("hname"));
			}
			return(arr);
		}catch(Exception e) {
			e.printStackTrace();
			return(new ArrayList<String>());
		}
	}
	
	public String deleteRecord(String hname) {
		try {
			pstmt = con.prepareStatement("delete from hawkersmanager where hname = ?");
			pstmt.setString(1, hname);
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
	
	public ArrayList<String> fetchHawkers(String area) {
		try {
			pstmt = con.prepareStatement("select hname from hawkersmanager where sareas like \"%" + area + "%\"");
			ResultSet res = pstmt.executeQuery();
			ArrayList<String> arr= new ArrayList<String>();
			while(res.next())
			{
				arr.add(res.getString("hname"));
			}
			return(arr);
		}catch(Exception e) {
			e.printStackTrace();
			return(new ArrayList<String>());
		}
	}
}
