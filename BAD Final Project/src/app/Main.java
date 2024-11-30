package app;

import java.util.List;

import dao.UserDAO;
import javafx.application.Application;
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
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
		RouteManager.init(primaryStage);
		RouteManager.addRoute("login", new LoginView(), "Dv.CO | Login");
		RouteManager.addRoute("register", new RegisterView(), "Dv.CO | Register");
		RouteManager.addRoute("home", new HomeView(), "Dv.CO | Home");
		RouteManager.addRoute("cart", new CartView(), "Dv.CO | Cart");
		
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
