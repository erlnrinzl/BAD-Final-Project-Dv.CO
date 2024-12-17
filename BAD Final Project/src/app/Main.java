package app;

import javafx.application.Application;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import model.User;
import util.RouteManager;
import util.SessionManager;
import view.AdminHome;
import view.CartView;
import view.HomeView;
import view.LoginView;
import view.RegisterView;
import view.component.AlertComponent;

public class Main extends Application {

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		RouteManager.init(primaryStage);
		RouteManager.addRoute("login", new LoginView(), "Dv.CO | Login");
		RouteManager.addRoute("register", new RegisterView(), "Dv.CO | Register");
		RouteManager.addRoute("admin_home", new AdminHome(), "Dv.CO | Home");
		RouteManager.addRoute("customer_home", new HomeView(), "Dv.CO | Home");
		RouteManager.addRoute("cart", new CartView(), "Dv.CO | Cart");

		try {
			RouteManager.navigate("login");
		} catch (Exception e) {
			e.printStackTrace();
		}

		primaryStage.show();
		primaryStage.setOnCloseRequest(e -> {
			e.consume();

			AlertComponent.confirm("Confirmation", "Are you sure?").ifPresent(response -> {
				if (response == ButtonType.OK)
					primaryStage.close();
			});
		});
	}

}
