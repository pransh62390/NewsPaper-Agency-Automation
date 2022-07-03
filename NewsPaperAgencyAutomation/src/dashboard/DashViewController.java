package dashboard;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class DashViewController {
	 @FXML
	    private Circle papercir;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void billgeneration(MouseEvent event) {
    	try{
    		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/billGeneration/BillGenerationView.fxml"));
    		Parent root=fxmlLoader.load();
			Scene scene = new Scene(root);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.show();
//			Scene scene1=(Scene)papercir.getScene();
//			scene1.getWindow().hide();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    }

    @FXML
    void customer(MouseEvent event) {
    	try{
    		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/customerForm/CustomerRegistration.fxml"));
    		Parent root=fxmlLoader.load();
			Scene scene = new Scene(root);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.show();
//			Scene scene1=(Scene)papercir.getScene();
//			scene1.getWindow().hide();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    }

    @FXML
    void hawker(MouseEvent event) {
    	try{
    		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/hawkersManager/HawkersManager.fxml"));
    		Parent root=fxmlLoader.load();
			Scene scene = new Scene(root);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.show();
//			Scene scene1=(Scene)papercir.getScene();
//			scene1.getWindow().hide();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    }

    @FXML
    void paper(MouseEvent event) {
    	try{
    		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/paperMaster/PapersMaster.fxml"));
    		Parent root=fxmlLoader.load();
			Scene scene = new Scene(root);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.show();
//			Scene scene1=(Scene)papercir.getScene();
//			scene1.getWindow().hide();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    }

    @FXML
    void paybill(MouseEvent event) {
    	try{
    		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/billCollection/BillCollectionView.fxml"));
    		Parent root=fxmlLoader.load();
			Scene scene = new Scene(root);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.show();
//			Scene scene1=(Scene)papercir.getScene();
//			scene1.getWindow().hide();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    }

    @FXML
    void initialize() {

    }
}
