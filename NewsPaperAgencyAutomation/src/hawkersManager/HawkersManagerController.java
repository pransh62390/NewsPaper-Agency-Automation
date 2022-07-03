package hawkersManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import jdbcc.HawkersController;

public class HawkersManagerController {
	HawkersController hc = new HawkersController();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> hawkers;

    @FXML
    private ImageView img;

    @FXML
    private TextField imgPath;

    @FXML
    private TextField address;

    @FXML
    private TextField phoneNo;

    @FXML
    private DatePicker dateOfJoin;

    @FXML
    private ComboBox<String> areas;

    @FXML
    private TextField servingInAreas;
    
    void setImg() {
    	try {
    		InputStream stream = new FileInputStream(imgPath.getText());
    		Image image = new Image(stream);
    		img.setImage(image );
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    }
    
    void showMsg(String msg)
    {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Its Error...");
		alert.setHeaderText("Header:error");
		alert.setContentText(msg);
		alert.showAndWait();
    }
    
    void fillHawkers()
    {
    	hawkers.getItems().clear();
    	hawkers.getItems().setAll(hc.fetchKeys());
    }

    @FXML
    void browseImg(ActionEvent event) {
    	FileChooser fileChooser = new FileChooser();
    	File file = fileChooser.showOpenDialog(Main.getStage());
    	if(file != null) {
    		imgPath.clear();
    		imgPath.setText(file.getPath());
    	}
    	else
            showMsg("Some error...");
    	setImg();
    }
    
    void clear()
    {
    	imgPath.setText("");
    	address.setText("");
    	phoneNo.setText("");
    	dateOfJoin.setValue(LocalDate.now());
    	servingInAreas.setText("");
    }

    @FXML
    void doClear(ActionEvent event) {
    	clear();
    }

    @FXML
    void doDelete(ActionEvent event) {
    	showMsg(hc.deleteRecord(hawkers.getEditor().getText()));
    	fillHawkers();
    	clear();
    }

    @FXML
    void doSave(ActionEvent event) {
    	showMsg(hc.insertRecord(""+hawkers.getEditor().getText(), imgPath.getText(), address.getText(), phoneNo.getText(), ""+dateOfJoin.getValue(), servingInAreas.getText()));
    	fillHawkers();
    }

    @FXML
    void doSearch(ActionEvent event) {
    	ArrayList<String> arr = hc.searchRecord(hawkers.getEditor().getText());
    	imgPath.setText(arr.get(0));
    	setImg();
    	address.setText(arr.get(1));
    	phoneNo.setText(arr.get(2));
    	dateOfJoin.setValue(LocalDate.parse(arr.get(3)));
    	servingInAreas.setText(arr.get(4));
    }

    @FXML
    void doUpdate(ActionEvent event) {
    	showMsg(hc.updateRecord(""+hawkers.getEditor().getText(), imgPath.getText(), address.getText(), phoneNo.getText(), servingInAreas.getText()));
    }
    
    void doPopulate() {
    	String item = areas.getSelectionModel().getSelectedItem();
    	String prev = servingInAreas.getText();
    	if(prev.length()!=0)
    		prev += "," + item;
    	else
    		prev += item;
    	servingInAreas.setText(prev);
    }

    @FXML
    void initialize() {
    	fillHawkers();
//        imgPath.setText("E:/eclipseProjects/NewsPaperAgencyAutomation/user1_img.png");
    	areas.getItems().setAll(new ArrayList<String>(Arrays.asList("Select", "Model Town Phase 1", "Model Town Phase 2", "Model Town Phase 3", "Model Town Phase 4")));
    	areas.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
        	doPopulate();
        });
    }
}
