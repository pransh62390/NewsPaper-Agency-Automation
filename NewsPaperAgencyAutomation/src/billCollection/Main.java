package billCollection;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {
	static Stage pas;
	@Override
	public void start(Stage primaryStage) {
		pas = primaryStage;
		try {
			Parent root=FXMLLoader.load(getClass().getResource("BillCollectionView.fxml"));
			primaryStage.setResizable(false);
			Scene scene = new Scene(root,450,450);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Bill Generation");
			primaryStage.show();
		} 
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Stage getStage() {
		return pas;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
