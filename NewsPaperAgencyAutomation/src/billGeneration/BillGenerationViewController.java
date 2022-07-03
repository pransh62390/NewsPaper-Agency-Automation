package billGeneration;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import jdbcc.*;

public class BillGenerationViewController {

	CustomerController cc = new CustomerController();
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField amount;
    
    @FXML
    private Button close;


    @FXML
    private DatePicker currentDate;

    @FXML
    private TextField daysSkipped;

    @FXML
    private TextField phoneNo;

    @FXML
    private TextField totalprice;

    @FXML
    private TextField totalDays;

    @FXML
    private DatePicker startDate;

    @FXML
    void close(ActionEvent event) {
    	Stage s = Main.getStage();
    	s.close();
    }

    @FXML
    void doSave(ActionEvent event) {
    	String skipped = daysSkipped.getText();
    	if(skipped == "")
    	{
    		showMsg("Enter skipped days First..");
    		return;
    	}
    	getDays(event);
    	genBill(event);
    	showMsg( cc.updateBill(phoneNo.getText(), Float.parseFloat(amount.getText()), 1, ""+currentDate.getValue().plusDays(1)));
    }

    @FXML
    void doSearch(ActionEvent event) {
    	ArrayList<String> arr = cc.searchRecord(phoneNo.getText());
    	ArrayList<String> prices = new ArrayList<String>(Arrays.asList(arr.get(5).split(",")));
    	float sum = 0.0f;
    	for (String s : prices) {
			sum += Float.parseFloat(s);
		}
    	totalprice.setText(""+sum);
    	startDate.setValue(LocalDate.parse(arr.get(6)));
    }

    @FXML
    void genBill(ActionEvent event) {
    	float amount = (float)(Float.parseFloat(totalprice.getText())*Integer.parseInt(totalDays.getText()));
    	this.amount.setText(""+amount);
    }
    
    void showMsg(String msg)
    {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Its Error...");
		alert.setHeaderText("Header:error");
		alert.setContentText(msg);
		alert.showAndWait();
    }
    
    public static long getDifferenceDays(Date d1, Date d2) {
        long diff = d2.getTime() - d1.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    @FXML
    void getDays(ActionEvent event) {
    	String skipped = daysSkipped.getText();
    	if(skipped == "")
    	{
    		showMsg("Enter skipped days First..");
    		return;
    	}
    	long days = getDifferenceDays(Date.valueOf(startDate.getValue()), Date.valueOf(currentDate.getValue()));
    	totalDays.setText(""+(days-Integer.parseInt(skipped)));
    }

    @FXML
    void initialize() {
    	currentDate.setValue(LocalDate.now());
    }
}
