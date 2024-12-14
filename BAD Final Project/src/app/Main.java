package app;

import javafx.application.Application;
import javafx.stage.Stage;
import model.User;
import util.RouteManager;
import util.SessionManager;
import view.AdminHome;
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
		RouteManager.init(primaryStage);
		RouteManager.addRoute("login", new LoginView(), "Dv.CO | Login");
		RouteManager.addRoute("register", new RegisterView(), "Dv.CO | Register");
		RouteManager.addRoute("home", new HomeView(), "Dv.CO | Home");
		RouteManager.addRoute("admin_home", new AdminHome(), "Dv.CO | Home");
		RouteManager.addRoute("customer_home", new HomeView(), "Dv.CO | Home");
		RouteManager.addRoute("cart", new CartView(), "Dv.CO | Cart");
		
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
