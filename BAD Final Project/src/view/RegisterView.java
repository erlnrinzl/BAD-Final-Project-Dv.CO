package view;

import controller.AuthController;
import exception.AuthException;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import model.User;
import util.RouteManager;

public class RegisterView extends Page {
	private AuthController controller;

	private Label registerLabel;
	private Label usernameLabel;
	private Label emailLabel;
	private Label passLabel;
	private Label confirmPassLabel;
	private Label ageLabel;
	private Label genderLabel;
	private Label countryLabel;
	private Label phoneLabel;
	private Label termsLabel;
	private Label loginLabel;
	private Hyperlink loginRouteLink;
	private TextField usernameField;
	private TextField emailField;
	private PasswordField passField;
	private PasswordField confirmPassField;
	private TextField phoneField;
	private ToggleGroup genderGroup;
	private RadioButton maleRadio;
	private RadioButton femaleRadio;
	private ChoiceBox<String> countryChoiceBox;
	private Spinner<Integer> ageSpinner;
	private CheckBox termsCheckbox;
	private Button registerBtn;

	public RegisterView() {
		super();
	}

	@Override
	public void init() {
		controller = new AuthController();

		registerLabel = new Label("REGISTER");
		registerLabel.setFont(new Font("Arial Black", 36));
		usernameLabel = new Label("Username");
		emailLabel = new Label("Email");
		passLabel = new Label("Password");
		confirmPassLabel = new Label("Confirm Password");
		ageLabel = new Label("Age");
		genderLabel = new Label("Gender");
		countryLabel = new Label("Country");
		phoneLabel = new Label("Phone Number");
		termsLabel = new Label("Agree to terms and condition");
		loginLabel = new Label("Already have an account?");
		loginRouteLink = new Hyperlink("Sign In!");
		loginRouteLink.setTextFill(Color.DARKBLUE);

		usernameField = new TextField();
		usernameField.setPromptText("Username");
		emailField = new TextField();
		emailField.setPromptText("Email");
		passField = new PasswordField();
		passField.setPromptText("Password");
		confirmPassField = new PasswordField();
		confirmPassField.setPromptText("Confirm Password");
		phoneField = new TextField();
		phoneField.setPromptText("Phone Number");

		maleRadio = new RadioButton("Male");
		maleRadio.setUserData("Male");
		femaleRadio = new RadioButton("Female");
		femaleRadio.setUserData("Female");
		genderGroup = new ToggleGroup();
		maleRadio.setToggleGroup(genderGroup);
		maleRadio.setSelected(true);
		femaleRadio.setToggleGroup(genderGroup);

		countryChoiceBox = new ChoiceBox<>();
		countryChoiceBox.getItems().addAll("Indonesia", "Malaysia", "Singapore", "Thailand");
		countryChoiceBox.getSelectionModel().selectFirst();

		ageSpinner = new Spinner<>(1, 100, 18);
		ageSpinner.setEditable(true);

		termsCheckbox = new CheckBox();

		registerBtn = new Button("Register");
	}

	@Override
	public Pane layout() {
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
		formLayout.setAlignment(Pos.CENTER);

		FlowPane termsLayout = new FlowPane();
		termsLayout.getChildren().addAll(termsCheckbox, termsLabel);
		termsLayout.setHgap(10);
		termsLayout.setAlignment(Pos.CENTER);

		FlowPane loginRouteLayout = new FlowPane();
		loginRouteLayout.getChildren().addAll(loginLabel, loginRouteLink);
		loginRouteLayout.setHgap(10);
		loginRouteLayout.setAlignment(Pos.CENTER);

		VBox vLayout = new VBox();
		vLayout.getChildren().add(registerLabel);
		vLayout.getChildren().add(formLayout);
		vLayout.getChildren().add(termsLayout);
		vLayout.getChildren().add(registerBtn);
		vLayout.getChildren().add(loginRouteLayout);
		vLayout.setAlignment(Pos.CENTER);
		vLayout.setPadding(new Insets(50));
		vLayout.setSpacing(10);

		return vLayout;
	}

	@Override
	public void setAction() {
		loginRouteLink.setOnMouseClicked(e -> {
			RouteManager.navigate("login");
		});

		registerBtn.setOnAction(e -> {
			try {
				if (!passField.getText().equals(confirmPassField.getText())) {
					throw new AuthException("Confirm Password must be the same as Password");
				}
				
				if (!termsCheckbox.isSelected()) {
					throw new AuthException("Terms and Conditions must be checked");
				}
				
				User user = new User();
				int age;
				
				try {
					age = ageSpinner.getValue();
				} catch (Exception error) {
					throw new AuthException("Please enter valid Age");
				}
				
				user.setUsername(usernameField.getText());
				user.setEmail(emailField.getText());
				user.setPassword(passField.getText());
				user.setAge(age);
				user.setGender(genderGroup.getSelectedToggle().getUserData().toString());
				user.setCountry(countryChoiceBox.getValue());
				user.setPhoneNumber(phoneField.getText());
				user.setRole("User");
				
				controller.register(user);
			} catch (AuthException error) {
				Alert alert = new Alert(AlertType.ERROR);

				alert.setContentText(error.getMessage());
				alert.show();
			}
		});
	}

}
