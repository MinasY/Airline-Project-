package Project;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;


public class LogIn extends Application {
	
	Stage window;
	Button btnLogIn, btnClose, btnReg;

	public static void main(String[] args) {
		
		launch(args);
		//launches application

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		//method to start the application
		try {
			window = primaryStage;
			window.setTitle("Airline Test");
			VBox btnpane = new VBox();
			btnLogIn = new Button("Log In");
			btnLogIn.setOnAction(e ->);
			btnClose = new Button("Close");
			btnClose.setOnAction(e -> );
			btnReg = new Button("Register Account");
			btnReg.setOnAction(e -> );
			
			
			btnpane.getChildren().addAll(btnLogIn, btnClose, btnReg);
			
			Scene scene = new Scene(btnpane, 300, 250);
			window.setScene(scene);
			window.show();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}

}
