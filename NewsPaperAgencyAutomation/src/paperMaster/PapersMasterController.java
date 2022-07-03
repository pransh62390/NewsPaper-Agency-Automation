package paperMaster;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import jdbcc.PapersController;

public class PapersMasterController {
	PapersController pc = new PapersController();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> papers;

    @FXML
    private TextField cost;
    
    void showMsg(String msg)
    {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Its Error...");
		alert.setHeaderText("Header:error");
		alert.setContentText(msg);
		alert.showAndWait();
    }
    
    void fillPapers()
    {
    	papers.getItems().clear();
    	papers.getItems().setAll(pc.fetchKeys());
    }

    @FXML
    void doClear(ActionEvent event) {
    	papers.setValue("");
    	cost.setText("");
    }

    @FXML
    void doDelete(ActionEvent event) {
    	showMsg(pc.deleteRecord(""+papers.getEditor().getText()));
    	fillPapers();
    	cost.setText(null);
    }

    @FXML
    void doFetch(ActionEvent event) {
    	String arr = pc.searchRecord(""+papers.getEditor().getText());
    	if(arr.length() != 0)
    		cost.setText(arr);
    	else
    	{
    		cost.setText(null);
    		showMsg("Record not Found...");
    	}
    }

    @FXML
    void doSave(ActionEvent event) {
    	showMsg(pc.insertRecord(""+papers.getEditor().getText(), Float.parseFloat(cost.getText())));
    	fillPapers();
    }

    @FXML
    void doUpdate(ActionEvent event) {
    	showMsg(pc.updateRecord(""+papers.getEditor().getText(), Float.parseFloat(cost.getText())));
    }

    @FXML
    void initialize() {
        fillPapers();
    }
}
