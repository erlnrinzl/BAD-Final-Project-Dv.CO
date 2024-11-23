package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import util.RouteManager;

public class RegisterView {
	
	public static Scene render() {
		Label registerLabel = new Label("REGISTER");
		registerLabel.setFont(new Font("Arial Black", 36));
		
		Label usernameLabel = new Label("Username");
		Label emailLabel = new Label("Email");
		Label passLabel = new Label("Password");
		Label confirmPassLabel = new Label("Confirm Password");
		Label ageLabel = new Label("Age");
		Label genderLabel = new Label("Gender");
		Label countryLabel = new Label("Country");
		Label phoneLabel = new Label("Phone Number");
		
		Label termsLabel = new Label("Agree to terms and condition");
		Label loginLabel = new Label("Already have an account?");
		Label loginRouteLabel = new Label("Sign In!");
		loginRouteLabel.setTextFill(Color.DARKBLUE);
		loginRouteLabel.setOnMouseClicked(e->{
			RouteManager.navigate("login");
		});
		
		TextField usernameField = new TextField();
		usernameField.setPromptText("Username");
		TextField emailField = new TextField();
		emailField.setPromptText("Email");
		PasswordField passField= new PasswordField();
		passField.setPromptText("Password");
		PasswordField confirmPassField= new PasswordField();
		confirmPassField.setPromptText("Confirm Password");
		TextField phoneField = new TextField();
		phoneField.setPromptText("Phone Number");
		
		RadioButton maleRadio = new RadioButton("Male");
		RadioButton femaleRadio = new RadioButton("Female");
		ToggleGroup genderGroup = new ToggleGroup();
		maleRadio.setToggleGroup(genderGroup);
		femaleRadio.setToggleGroup(genderGroup);

		ChoiceBox<String> countryChoiceBox = new ChoiceBox<>();
		countryChoiceBox.getItems().addAll("Indonesia", "Malaysia", "Singapore", "Thailand");
		
		Spinner<Integer> ageSpinner = new Spinner<>(1, 100, 18);
        ageSpinner.setEditable(true);
        
        CheckBox termsCheckbox = new CheckBox();
        
        Button registerBtn= new Button("Register");
        registerBtn.setOnAction(e->{
        	//do something here
        });
        
        FlowPane flowGenderLayout = new FlowPane();
        flowGenderLayout.getChildren().addAll(maleRadio, femaleRadio);
        flowGenderLayout.setHgap(10);
        
        GridPane formLayout = new GridPane();
        formLayout.add(usernameLabel, 0, 0);
        formLayout.add(usernameField, 1, 0);
        formLayout.add(emailLabel, 0, 1);
        formLayout.add(emailField, 1, 1);
        formLayout.add(passLabel, 0, 2);
        formLayout.add(passField, 1, 2);
        formLayout.add(confirmPassLabel, 0, 3);
        formLayout.add(confirmPassField, 1, 3);
        formLayout.add(ageLabel, 0, 4);
        formLayout.add(ageSpinner, 1, 4);
        formLayout.add(genderLabel, 0, 5);
        formLayout.add(flowGenderLayout, 1, 5);
        formLayout.add(countryLabel, 0, 6);
        formLayout.add(countryChoiceBox, 1, 6);
        formLayout.add(phoneLabel, 0, 7);
        formLayout.add(phoneField, 1, 7);
        formLayout.setVgap(10);
        formLayout.setHgap(20);
        
        FlowPane termsLayout = new FlowPane();
        termsLayout.getChildren().addAll(termsCheckbox, termsLabel);
        termsLayout.setHgap(10);
        
        FlowPane loginRouteLayout = new FlowPane();
        loginRouteLayout.getChildren().addAll(loginLabel, loginRouteLabel);
        loginRouteLayout.setHgap(10);
        
        VBox vLayout = new VBox();
        vLayout.getChildren().add(registerLabel);
        vLayout.getChildren().add(formLayout);
        vLayout.getChildren().add(termsLayout);
        vLayout.getChildren().add(registerBtn);
        vLayout.getChildren().add(loginRouteLayout);
		vLayout.setAlignment(Pos.CENTER);
		vLayout.setPadding(new Insets(50));
		vLayout.setSpacing(10);
        
		return new AppShell().render(vLayout);
	}
}
