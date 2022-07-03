module NewsPaperAgencyAutomation {
	requires javafx.controls;
	requires java.sql;
	requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.base;
	
	opens application to javafx.graphics, javafx.fxml;
	opens paperMaster to javafx.graphics, javafx.fxml;
	opens hawkersManager to javafx.graphics, javafx.fxml;
	opens customerForm to javafx.graphics, javafx.fxml;
	opens billGeneration to javafx.graphics, javafx.fxml;
	opens billCollection to javafx.graphics, javafx.fxml;
	opens loginPanel to javafx.graphics, javafx.fxml;
	opens dashboard to javafx.graphics, javafx.fxml;
	
	opens papersView to javafx.graphics, javafx.fxml, javafx.base;
	opens hawkersView to javafx.graphics, javafx.fxml, javafx.base;
	opens customerView to javafx.graphics, javafx.fxml, javafx.base;
}
