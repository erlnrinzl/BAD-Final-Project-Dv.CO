package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import util.RouteManager;

public class LoginView {
	public static Scene render() {
		Label loginLabel = new Label("LOGIN");
		loginLabel.setFont(new Font("Arial Black", 36));
		
		Label emailLabel = new Label("Email");
		Label passLabel = new Label("Password");
		
		Label registerLabel = new Label("Don't have an account?");
		Label registerRouteLabel = new Label("Sign Up!");
		registerRouteLabel.setTextFill(Color.DARKBLUE);
		registerRouteLabel.setOnMouseClicked(e->{
			RouteManager.navigate("register");
		});
		
		TextField emailField = new TextField();
		emailField.setPromptText("Email");
		PasswordField passField= new PasswordField();
		passField.setPromptText("Password");
		
		Button loginBtn = new Button("Login");
		
		
		GridPane gridLayout = new GridPane();
		gridLayout.add(emailLabel, 0, 0);
		gridLayout.add(emailField, 1, 0);
		gridLayout.add(passLabel, 0, 1);
		gridLayout.add(passField, 1, 1);
		gridLayout.setAlignment(Pos.CENTER);
		gridLayout.setPadding(new Insets(10));
		gridLayout.setHgap(20);
		gridLayout.setVgap(10);
		
		FlowPane fpLayout = new FlowPane();
		fpLayout.getChildren().addAll(registerLabel, registerRouteLabel);
		fpLayout.setAlignment(Pos.CENTER);
		fpLayout.setPadding(new Insets(10));
		fpLayout.setHgap(10);
		
		VBox vLayout = new VBox();
	    vLayout.getChildren().add(loginLabel);
	    vLayout.getChildren().add(gridLayout);
	    vLayout.getChildren().add(loginBtn);
	    vLayout.getChildren().add(fpLayout);
		vLayout.setAlignment(Pos.CENTER);
		vLayout.setPadding(new Insets(50));
		vLayout.setSpacing(10);
		
		return new Scene(vLayout);
	}

}
