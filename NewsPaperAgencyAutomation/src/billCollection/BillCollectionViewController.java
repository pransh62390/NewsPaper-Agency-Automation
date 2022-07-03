package billCollection;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import jdbcc.CustomerController;

public class BillCollectionViewController {
	
	CustomerController cc = new CustomerController();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField amount;

    @FXML
    private TextField phoneNo;
    
    void showMsg(String msg)
    {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Its Error...");
		alert.setHeaderText("Header:error");
		alert.setContentText(msg);
		alert.showAndWait();
    }

    @FXML
    void doPaid(ActionEvent event) {
    	showMsg(cc.updateStatus(phoneNo.getText(), 0, 0.0f));
    }

    @FXML
    void doSearch(ActionEvent event) {
    	amount.setText(""+cc.getAmount(phoneNo.getText()));
    }

    @FXML
    void initialize() {
        
    }
}
