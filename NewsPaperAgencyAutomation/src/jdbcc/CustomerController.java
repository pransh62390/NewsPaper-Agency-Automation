package jdbcc;

import java.sql.Connection;
import java.sql.Date;

import jdbcc.DatabaseConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CustomerController {
	Connection con;
	PreparedStatement pstmt;
	
	public CustomerController() {
		con = DatabaseConnection.doConnect();
	}
	
	public String insertRecord(String cname, String phoneNo, String address, String area, String hawker, String selpapers, String selprices, String dos, float bill, int status)
	{
		try {
			pstmt = con.prepareStatement("insert into customerdata values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			pstmt.setString(1, cname);
			pstmt.setString(2, phoneNo);
			pstmt.setString(3, address);
			pstmt.setString(4, area);
			pstmt.setString(5, hawker);
			pstmt.setString(6, selpapers);
			pstmt.setString(7, selprices);
			pstmt.setDate(8, Date.valueOf(dos));
			pstmt.setFloat(9, bill);
			pstmt.setInt(10, status);
			pstmt.executeUpdate();
			return "Record inserted...";
		}catch(Exception e){
			e.printStackTrace();
			return "Data Already exists";
		}
	}
	
	public String updateRecord(String cname, String phoneNo, String address, String area, String hawker, String selpapers, String selprices, String dos, float bill, int status)
	{
		try {
			pstmt = con.prepareStatement("update customerdata set cname=?,address=?,hawker=?,area=?,selpapers=?,selprices=?,dos=?,bill=?,status=? where phoneno=?");
			pstmt.setString(10, phoneNo);
			pstmt.setString(1, cname);
			pstmt.setString(2, address);
			pstmt.setString(3, hawker);
			pstmt.setString(4, area);
			pstmt.setString(5, selpapers);
			pstmt.setString(6, selprices);
			pstmt.setDate(7, Date.valueOf(dos));
			pstmt.setFloat(8, bill);
			pstmt.setInt(9, status);
			int count = pstmt.executeUpdate();
			return count + " Record updated...";
		}catch(Exception e){
			e.printStackTrace();
			return "Check your data again...";
		}
	}
	
	public ArrayList<String> searchRecord(String phoneno) {
		try {
			pstmt = con.prepareStatement("select * from customerdata where phoneno=?");
			pstmt.setString(1, phoneno);
			ResultSet res = pstmt.executeQuery();
			ArrayList<String> arr = new ArrayList<String>();
			while(res.next()){
				arr.add(res.getString("cname"));
				arr.add(res.getString("address"));
				arr.add(res.getString("area"));
				arr.add(res.getString("hawker"));
				arr.add(res.getString("selpapers"));
				arr.add(res.getString("selprices"));
				arr.add(""+res.getString("dos"));
			}
			return(arr);
		}catch(Exception e) {
			e.printStackTrace();
			return(new ArrayList<String>());
		}
	}
	
	public ArrayList<String> fetchKeys() {
		try {
			pstmt = con.prepareStatement("select cname from customerdata");
			ResultSet res = pstmt.executeQuery();
			ArrayList<String> arr= new ArrayList<String>();
			while(res.next())
			{
				arr.add(res.getString("cname"));
			}
			return(arr);
		}catch(Exception e) {
			e.printStackTrace();
			return(new ArrayList<String>());
		}
	}
	
	public String deleteRecord(String cname) {
		try {
			pstmt = con.prepareStatement("delete from customerdata where cname = ?");
			pstmt.setString(1, cname);
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
	
	public String updateBill(String phoneno, float bill, int status, String dos) {
		try {
			pstmt = con.prepareStatement("update customerdata set dos=?,bill=?,status=? where phoneno=?");
			pstmt.setString(4, phoneno);
			pstmt.setDate(1, Date.valueOf(dos));
			pstmt.setFloat(2, bill);
			pstmt.setInt(3, status);
			int count = pstmt.executeUpdate();
			return count + " Record updated...";
		}catch(Exception e){
			e.printStackTrace();
			return "Check your data again...";
		}
	}
	
	public float getAmount(String phoneno) {
		float bill = 0.0f;
		try {
			pstmt = con.prepareStatement("select bill from customerdata where phoneno=?");
			pstmt.setString(1, phoneno);
			ResultSet r =  pstmt.executeQuery();
			r.next();
			bill = r.getFloat("bill");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return bill;
	}
	
	public String updateStatus(String phoneno, int status, float bill) {
		try {
			pstmt = con.prepareStatement("update customerdata set status=?, bill=? where phoneno=?");
			pstmt.setString(3, phoneno);
			pstmt.setInt(1, status);
			pstmt.setFloat(2, bill);
			int count = pstmt.executeUpdate();
			return count + " Record updated...";
		}catch(Exception e){
			e.printStackTrace();
			return "Check your data again...";
		}
	}
}
