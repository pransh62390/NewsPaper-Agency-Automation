package hawkersManager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class Main extends Application {
	static Stage myStage;
	@Override
	public void start(Stage primaryStage) {
		myStage = primaryStage;
		try {
			Parent root=FXMLLoader.load(getClass().getResource("HawkersManager.fxml"));
			primaryStage.setResizable(false);
			Scene scene = new Scene(root,450,450);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Hawker's Manager");
			primaryStage.show();
			
		} 
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Stage getStage() {
		return myStage;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
