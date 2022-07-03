package papersView;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
    
    Connection con;

    @FXML
    void doFetchAll(ActionEvent event) {
    	//price, paper
    	tblUsers.getColumns().clear();
    	tblUsers.getColumns().addAll(columns("PaperName", "paper", ""), columns("Price", "price", 0.0f));
    	
    	//get and fill records in table
    	ObservableList<UserBean> aryList=showAllRecords();
    	tblUsers.setItems(aryList);//fill array in table
    	
    }
    
    public <T> TableColumn<UserBean, T> columns(String title, String val, T temp){
    	TableColumn<UserBean, T>col=new TableColumn<UserBean, T>(title);//any thing
    	col.setCellValueFactory(new PropertyValueFactory<UserBean, T>(val));
    	col.setMinWidth(100);
    	return col;
    }
    
    ObservableList<UserBean> showAllRecords()
	{
    	ObservableList<UserBean> aryList=FXCollections.observableArrayList();
		try{
		 PreparedStatement	pstmt=con.prepareStatement("select * from papers");
			ResultSet table= pstmt.executeQuery();
			//table is basically array of Objects
			while(table.next())//moves the cursor in next row if no row found
			{
				String paper=table.getString("paper");
				float price=table.getFloat("price");
				UserBean obj=new UserBean(paper, price);
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
    	con=DatabaseConnection.doConnect();
    }
}
/*1. create connection
2. create bean class
3. fetch and create array of objects of ObservableList and return it
4. add Columns in table - see line no. 37
5. call the function
6. and fill data in table
*/