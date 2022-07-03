package hawkersView;

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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import jdbcc.DatabaseConnection;


public class TableFormViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<UserBean> tblUsers;
    
    @FXML
    private ComboBox<String> areas;
    
    Connection con;
    
    public <T> TableColumn<UserBean, T> columns(String title, String val, T temp){
    	TableColumn<UserBean, T>col=new TableColumn<UserBean, T>(title);//any thing
    	col.setCellValueFactory(new PropertyValueFactory<UserBean, T>(val));
    	col.setMinWidth(100);
    	return col;
    }

    @FXML
    void doFetchAll(ActionEvent event) {
    	tblUsers.getColumns().clear();
    	tblUsers.getColumns().addAll(columns("HawkerName", "hname", ""), columns("HawkerAddress", "address", ""), columns("HawkerMobile", "mobile", ""), columns("HawkerSareas", "sareas", ""), columns("doj", "doj", new Date(2, 10, 2001)));
    	
    	//get and fill records in table
    	ObservableList<UserBean> aryList=showAllRecords();
    	tblUsers.setItems(null);
    	tblUsers.setItems(aryList);//fill array in table
    }
    
    ObservableList<UserBean> showAllRecords()
	{
    	ObservableList<UserBean> aryList=FXCollections.observableArrayList();
		try{
		 PreparedStatement	pstmt=con.prepareStatement("select * from hawkersmanager");
			ResultSet table= pstmt.executeQuery();
			//table is basically array of Objects
			while(table.next())//moves the cursor in next row if no row found
			{
				String hname=table.getString("hname");
				String address=table.getString("address");
				String mobile=table.getString("mobile");
				String sareas=table.getString("sareas");
				Date doj =table.getDate("doj");
				UserBean obj=new UserBean(hname, address, mobile, sareas, doj);
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
    void doFilter(ActionEvent event) {
    	tblUsers.getColumns().clear();
    	tblUsers.getColumns().addAll(columns("HawkerName", "hname", ""), columns("HawkerAddress", "address", ""), columns("HawkerMobile", "mobile", ""), columns("HawkerSareas", "sareas", ""), columns("doj", "doj", new Date(2, 10, 2001)));
    	
    	//get and fill records in table
    	ObservableList<UserBean> aryList=showRecords();
    	tblUsers.setItems(null);
    	tblUsers.setItems(aryList);//fill array in table
    }
    
    ObservableList<UserBean> showRecords()
	{
    	ObservableList<UserBean> aryList=FXCollections.observableArrayList();
		try{
		 PreparedStatement	pstmt=con.prepareStatement("select * from hawkersmanager where sareas like \"%" + areas.getValue() + "%\"");
			ResultSet table= pstmt.executeQuery();
			//table is basically array of Objects
			while(table.next())//moves the cursor in next row if no row found
			{
				String hname=table.getString("hname");
				String address=table.getString("address");
				String mobile=table.getString("mobile");
				String sareas=table.getString("sareas");
				Date doj =table.getDate("doj");
				UserBean obj=new UserBean(hname, address, mobile, sareas, doj);
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