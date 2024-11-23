package app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.User;
import util.RouteManager;
import util.SessionManager;
import view.CartView;
import view.HomeView;
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
		RouteManager.addRoute("home", () -> HomeView.render(), "Dv.CO | Home");
		RouteManager.addRoute("cart", () -> CartView.render(), "Dv.CO | Cart");
		
		// to be deleted soon!
		User activeUser = new User(
				"Dummy Name",
				"email@mail.com",
				"pass123",
				"male",
				"Indonesia",
				"08231231231",
				"User",
				10
			);
		activeUser.setUserID("US001");
		SessionManager.login(activeUser);
//		SessionManager.logout();
		
		// start with login page
		try {
			RouteManager.navigate("home");			
		} catch (Exception e) {
			RouteManager.navigate("login");
		}
		primaryStage.show();
//		primaryStage.setOnCloseRequest(e->{
//			Alert alert = new Alert(AlertType.CONFIRMATION);
//			alert.showAndWait();
//		});
		
	}
	

}
