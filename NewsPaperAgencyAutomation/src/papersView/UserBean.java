package papersView;

//import java.sql.Date;

public class UserBean 
{
	//fields name same as table columns name
//must have contructors and gettters and setters
	String paper;
	float price;

	public UserBean(){}  //add it manually
	public UserBean(String paper, float price) {
//		super();
		this.paper = paper;
		this.price = price;
	}
	public String getPaper() {
		return paper;
	}
	public void setPaper(String paper) {
		this.paper = paper;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
}
