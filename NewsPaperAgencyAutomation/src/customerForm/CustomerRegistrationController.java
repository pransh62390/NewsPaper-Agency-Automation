package customerForm;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.event.ActionEvent;

import jdbcc.*;

public class CustomerRegistrationController {
	HawkersController hc = new HawkersController();
	PapersController pc = new PapersController();
	CustomerController cc = new CustomerController();
	ArrayList<String> papersList = new ArrayList<String>();
	ArrayList<String> priceList = new ArrayList<String>();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField custmrName;

    @FXML
    private TextField phoneNo;

    @FXML
    private TextField address;

    @FXML
    private ComboBox<String> areas;

    @FXML
    private ComboBox<String> hawkers;

    @FXML
    private ListView<String> papers;

    @FXML
    private ListView<String> selPapers;

    @FXML
    private ListView<String> prices;
    
    @FXML
    private DatePicker dos;
    
    void fillHawkers(String selVal) {
    	ArrayList<String> arr = hc.fetchHawkers(selVal);
    	hawkers.getItems().setAll(arr);
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
    void fillSelPapers(MouseEvent event) {
    	String selprs =  papers.getSelectionModel().getSelectedItem();
		if(papersList.indexOf(selprs)== -1)
		{
			papersList.add(selprs);
			priceList.add(pc.searchRecord(selprs));
		}
    	selPapers.getItems().setAll(papersList);
    	prices.getItems().setAll(priceList);
    }
    
    @FXML
    void delSelPaper(MouseEvent event) {
    	int selpr = selPapers.getSelectionModel().getSelectedIndex();
    	if(selpr<0)
    		return;
    	papersList.remove(selpr);
    	priceList.remove(selpr);
    	selPapers.getItems().remove(selpr);
    	prices.getItems().remove(selpr);
    }
    
    void clear()
    {
    	custmrName.setText("");
    	phoneNo.setText("");
    	address.setText("");
    	dos.setValue(null);
    	areas.setValue("Select");
//    	hawkers.setValue("");
    	hawkers.getItems().setAll(new ArrayList<String>());
    	selPapers.getItems().setAll(new ArrayList<String>());
    	prices.getItems().setAll(new ArrayList<String>());
    }
    
    @FXML
    void doClear(ActionEvent event) {
    	clear();
    }

    @FXML
    void doDelete(ActionEvent event) {
    	showMsg(cc.deleteRecord(custmrName.getText()));
    	clear();
    }

    @FXML
    void doInsert(ActionEvent event) {
    	String selpaper = new String().join(",", papersList);
    	String selprice = new String().join(",", priceList);
    	String s = cc.insertRecord(custmrName.getText(), phoneNo.getText(), address.getText(), areas.getSelectionModel().getSelectedItem(), hawkers.getSelectionModel().getSelectedItem(), selpaper, selprice, ""+dos.getValue(), 0.0f, 0);
    	showMsg(s);
    }

    @FXML
    void doSearch(ActionEvent event) {
    	TextInputDialog dial = new TextInputDialog();
    	dial.setTitle("Search");
    	dial.setHeaderText("Enter valid Phone Number of customer.");
    	ButtonType search = new ButtonType("Search",ButtonData.OK_DONE);
    	dial.getDialogPane().getButtonTypes().set(0, search);
    	dial.showAndWait();
    	if(dial.getOnCloseRequest()==null)
    	{
    		doButton(dial.getEditor().getText());
    		phoneNo.setText(dial.getEditor().getText());
    	}
    }
    
    void doButton(String s) {
//    	if(phoneNo.getText()=="") {
//    		showMsg("Enter Phone No. and then click search");
//    		return;
//    	}
//    	ArrayList<String> arr = cc.searchRecord(phoneNo.getText());
    	ArrayList<String> arr = cc.searchRecord(s);
    	custmrName.setText(arr.get(0));
    	address.setText(arr.get(1));
    	areas.setValue(arr.get(2));
    	hawkers.getItems().setAll(arr.get(3).split(","));
    	selPapers.getItems().setAll(arr.get(4).split(","));
    	prices.getItems().setAll(arr.get(5).split(","));
    	dos.setValue(LocalDate.parse(arr.get(6)));
    }

    @FXML
    void doUpdate(ActionEvent event) {
    	String selpaper = new String().join(",", new ArrayList<String>(selPapers.getItems()));
    	String selprice = new String().join(",", new ArrayList<String>(prices.getItems()));
    	String s = cc.updateRecord(custmrName.getText(), phoneNo.getText(), address.getText(), areas.getSelectionModel().getSelectedItem(), hawkers.getSelectionModel().getSelectedItem(), selpaper, selprice, ""+dos.getValue(), 0.0f, 0);
    	showMsg(s);
    }

    @FXML
    void initialize() {
    	areas.getItems().setAll(new ArrayList<String>(Arrays.asList("Select", "Model Town Phase 1", "Model Town Phase 2", "Model Town Phase 3", "Model Town Phase 4")));
    	areas.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
        	fillHawkers(newValue);
        });
    	
    	papers.getItems().setAll(pc.fetchKeys());
    }
}
