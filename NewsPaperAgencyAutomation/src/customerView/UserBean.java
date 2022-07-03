package customerView;

import java.sql.Date;

public class UserBean 
{
	//fields name same as table columns name
	//must have contructors and gettters and setters
	String cname;
	String address;
	String phoneno;
	String area;
	String hawker;
	String selpapers;
	String selprices;
	Date dos;
	float bill;

	public UserBean(){}  //add it manually

	public UserBean(String cname, String address, String phoneno, String area, String hawker, String selpapers,
			String selprices, Date dos, float bill) {
		this.cname = cname;
		this.address = address;
		this.phoneno = phoneno;
		this.area = area;
		this.hawker = hawker;
		this.selpapers = selpapers;
		this.selprices = selprices;
		this.dos = dos;
		this.bill = bill;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneno() {
		return phoneno;
	}

	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getHawker() {
		return hawker;
	}

	public void setHawker(String hawker) {
		this.hawker = hawker;
	}

	public String getSelpapers() {
		return selpapers;
	}

	public void setSelpapers(String selpapers) {
		this.selpapers = selpapers;
	}

	public String getSelprices() {
		return selprices;
	}

	public void setSelprices(String selprices) {
		this.selprices = selprices;
	}

	public Date getDos() {
		return dos;
	}

	public void setDos(Date dos) {
		this.dos = dos;
	}

	public float getBill() {
		return bill;
	}

	public void setBill(float bill) {
		this.bill = bill;
	}
}
