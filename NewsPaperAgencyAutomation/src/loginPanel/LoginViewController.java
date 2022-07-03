package loginPanel;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class LoginViewController {
	static int count;
	
	public LoginViewController() {
		count = 3;
	}
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private PasswordField pswd;
    
    void showMsg(String msg)
    {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Its Error...");
		alert.setHeaderText("Header:error");
		alert.setContentText(msg);
		alert.showAndWait();
		if(alert.onCloseRequestProperty()==null)
			alert.close();
    }

    @FXML
    void checkPswd(ActionEvent event) {
    	if(pswd.getText().equals("HiPal")) {
    		try{
    			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/dashboard/DashView.fxml"));
        		Parent root=fxmlLoader.load(); 
    			Scene scene = new Scene(root);
    			Stage stage=new Stage();
    			stage.setScene(scene);
    			stage.show();
    			//to hide the opened window
    			/*
    			   Scene scene1=(Scene)btnpm.getScene();
    			   scene1.getWindow().hide();
    			 */
    		}
    		catch(Exception e)
    		{
    			e.printStackTrace();
    		}
    	}
    	else {
    		if(count < 0) {
    			showMsg("So many wrong attempts. Restart the app then try again.");
    		}
    		else {
    			showMsg("Check Your password again."+count+" attempts left.");
        		count -= 1;
    		}
    		if(count == -1) {
    			pswd.setText("");
    			pswd.setEditable(false);
    			showMsg("So many wrong attempts. Restart the app then try again.");
    		}
    	}
    }

    @FXML
    void initialize() {
    	
    }
}
