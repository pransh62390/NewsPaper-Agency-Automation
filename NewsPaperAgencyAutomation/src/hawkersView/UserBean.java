package hawkersView;

import java.sql.Date;

public class UserBean 
{
	//fields name same as table columns name
	//must have contructors and gettters and setters
	String hname;
	String address;
	String mobile;
	String sareas;
	Date doj;

	public UserBean(){}  //add it manually
	public UserBean(String hname, String address, String mobile, String sareas, Date doj) {
		this.hname = hname;
		this.address = address;
		this.mobile = mobile;
		this.sareas = sareas;
		this.doj = doj;
	}
	public String getHname() {
		return hname;
	}
	public void setHname(String hname) {
		this.hname = hname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getSareas() {
		return sareas;
	}
	public void setSareas(String sareas) {
		this.sareas = sareas;
	}
	public Date getDoj() {
		return doj;
	}
	public void setDoj(Date doj) {
		this.doj = doj;
	}
	
}
