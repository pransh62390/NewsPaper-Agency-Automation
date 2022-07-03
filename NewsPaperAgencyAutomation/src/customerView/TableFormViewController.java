package customerView;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import jdbcc.DatabaseConnection;

public class TableFormViewController {

	Connection con;
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<UserBean> tblUsers;

    @FXML
    private ComboBox<String> areas;

    @FXML
    private RadioButton paid;
    
    @FXML
    private ToggleGroup pay;
    
    public <T> TableColumn<UserBean, T> columns(String title, String val, T temp){
    	TableColumn<UserBean, T>col=new TableColumn<UserBean, T>(title);//any thing
    	col.setCellValueFactory(new PropertyValueFactory<UserBean, T>(val));
    	col.setMinWidth(100);
    	return col;
    }
    
    void showMsg(String msg)
    {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Its Error...");
		alert.setHeaderText("Header:error");
		alert.setContentText(msg);
		alert.showAndWait();
    }

    @FXML
    void doFetchAll(ActionEvent event) {
    	tblUsers.getColumns().clear();
    	tblUsers.getColumns().addAll(columns("CustomerName", "cname", ""), columns("CustomerAddress", "address", ""), columns("CustomerMobile", "phoneno", ""), columns("CustomerArea", "area", ""), columns("Hawker", "hawker", ""), columns("SelPapers", "selpapers", ""), columns("SelPrices", "selprices", ""), columns("dos", "dos", new Date(2, 10, 2001)), columns("Bill", "bill", 0.0f));
    	
    	//get and fill records in table
    	tblUsers.setItems(null);
    	ObservableList<UserBean> aryList = showAllRecords();
    	tblUsers.setItems(aryList);//fill array in table
    }

    @FXML
    void doFind(ActionEvent event) {
    	tblUsers.getColumns().clear();
    	tblUsers.getColumns().addAll(columns("CustomerName", "cname", ""), columns("CustomerAddress", "address", ""), columns("CustomerMobile", "phoneno", ""), columns("CustomerArea", "area", ""), columns("Hawker", "hawker", ""), columns("SelPapers", "selpapers", ""), columns("SelPrices", "selprices", ""), columns("dos", "dos", new Date(2, 10, 2001)), columns("Bill", "bill", 0.0f));
    	
    	//get and fill records in table
    	tblUsers.setItems(null);
    	ObservableList<UserBean> aryList = showRecords();
    	tblUsers.setItems(aryList);//fill array in table
    }

    @FXML
    void doShowAll(ActionEvent event) {
    	ObservableList<UserBean> aryList;
    	RadioButton rb = (RadioButton) pay.getSelectedToggle();
    	try {
	    	if(rb.getText().equals("Paid"))
	    		aryList = showPaidRecords(0);
	    	else
	    		aryList = showPaidRecords(1);
    	}catch(Exception e) {
    			showMsg("Select One Value From Paid/ Un-Paid");
    			return;
    		}
    	tblUsers.getColumns().clear();
    	tblUsers.getColumns().addAll(columns("CustomerName", "cname", ""), columns("CustomerAddress", "address", ""), columns("CustomerMobile", "phoneno", ""), columns("CustomerArea", "area", ""), columns("Hawker", "hawker", ""), columns("SelPapers", "selpapers", ""), columns("SelPrices", "selprices", ""), columns("dos", "dos", new Date(2, 10, 2001)), columns("Bill", "bill", 0.0f));
    	
    	//get and fill records in table
    	tblUsers.setItems(null);
    	tblUsers.setItems(aryList);//fill array in table
    }
    
    ObservableList<UserBean> showAllRecords()
	{
    	ObservableList<UserBean> aryList=FXCollections.observableArrayList();
		try{
		 PreparedStatement	pstmt=con.prepareStatement("select * from customerdata");
			ResultSet table= pstmt.executeQuery();
			//table is basically array of Objects
			while(table.next())//moves the cursor in next row if no row found
			{
				String cname=table.getString("cname");
				String address=table.getString("address");
				String phoneno=table.getString("phoneno");
				String area=table.getString("area");
				String hawker=table.getString("hawker");
				String selpapers=table.getString("selpapers");
				String selprices=table.getString("selprices");
				Date dos =table.getDate("dos");
				float bill = table.getFloat("bill");
				int status = table.getInt("status");
				UserBean obj=new UserBean(cname, address, phoneno, area, hawker, selpapers, selprices, dos, bill);
				aryList.add(obj);
			}
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return aryList;
	}
    
    ObservableList<UserBean> showRecords()
	{
    	ObservableList<UserBean> aryList=FXCollections.observableArrayList();
		try{
		 PreparedStatement	pstmt=con.prepareStatement("select * from customerdata where area like \"%" + areas.getValue() + "%\"");
			ResultSet table= pstmt.executeQuery();
			//table is basically array of Objects
			while(table.next())//moves the cursor in next row if no row found
			{
				String cname=table.getString("cname");
				String address=table.getString("address");
				String phoneno=table.getString("phoneno");
				String area=table.getString("area");
				String hawker=table.getString("hawker");
				String selpapers=table.getString("selpapers");
				String selprices=table.getString("selprices");
				Date dos =table.getDate("dos");
				float bill = table.getFloat("bill");
				int status = table.getInt("status");
				UserBean obj=new UserBean(cname, address, phoneno, area, hawker, selpapers, selprices, dos, bill);
				aryList.add(obj);
			}
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return aryList;
	}
    
    ObservableList<UserBean> showPaidRecords(int status)
	{
    	ObservableList<UserBean> aryList=FXCollections.observableArrayList();
		try{
			PreparedStatement	pstmt=con.prepareStatement("select * from customerdata where status=?");
			pstmt.setInt(1, status);
			ResultSet table= pstmt.executeQuery();
			//table is basically array of Objects
			while(table.next())//moves the cursor in next row if no row found
			{
				String cname=table.getString("cname");
				String address=table.getString("address");
				String phoneno=table.getString("phoneno");
				String area=table.getString("area");
				String hawker=table.getString("hawker");
				String selpapers=table.getString("selpapers");
				String selprices=table.getString("selprices");
				Date dos =table.getDate("dos");
				float bill = table.getFloat("bill");
				UserBean obj=new UserBean(cname, address, phoneno, area, hawker, selpapers, selprices, dos, bill);
				aryList.add(obj);
			}
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return aryList;
	}

    @FXML
    void initialize() {
    	areas.getItems().setAll(new ArrayList<String>(Arrays.asList("Select", "Model Town Phase 1", "Model Town Phase 2", "Model Town Phase 3", "Model Town Phase 4")));
    	con=DatabaseConnection.doConnect();
    }
}
