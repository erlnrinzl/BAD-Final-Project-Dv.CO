package app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import util.RouteManager;
import view.LoginView;
import view.RegisterView;

public class Main extends Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
		RouteManager.init(primaryStage);
		
		RouteManager.addRoute("login", () -> LoginView.render(), "Dv.CO | Login");
		RouteManager.addRoute("register", () -> RegisterView.render(), "Dv.CO | Register");
		
		// start with login page
		RouteManager.navigate("login");				
		primaryStage.setTitle("Dv.CO|Login");
		primaryStage.show();
//		primaryStage.setOnCloseRequest(e->{
//			Alert alert = new Alert(AlertType.CONFIRMATION);
//			alert.showAndWait();
//		});
		
	}
	

}
