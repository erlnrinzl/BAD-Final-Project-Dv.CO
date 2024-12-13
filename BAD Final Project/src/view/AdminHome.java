package view;

import controller.DonutController;
import exception.FormException;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import model.Donut;
import util.SessionManager;

public class AdminHome extends Page {

	private DonutController controller;
	private TableView<Donut> table;
	private ObservableList<Donut> donuts;
	private Label profileLabel;
	private Label activeDonutLabel;
	private Donut activeDonut = null;
	private VBox form;
	private Label donutNameLabel;
	private TextField donutNameField;
	private Label donutDescriptionLabel;
	private TextArea donutDescriptionField;
	private Label donutPriceLabel;
	private TextField donutPriceField;
	private HBox buttons;
	private Button addButton;
	private Button updateButton;
	private Button deleteButton;

	public AdminHome() {
		super();
	}

	@Override
	public void init() {
		controller = new DonutController();
		controller.loadDonutProducts();
		donuts = controller.getDonutProducts();

		profileLabel = new Label("Hello, " + SessionManager.getUser().getUsername());
		profileLabel.setFont(new Font("Arial Black", 36));

		activeDonutLabel = new Label("Active Donut:");

		this.initializeTable();
		this.initializeForm();
	}

	@Override
	public Pane layout() {
		VBox container = new VBox();

		container.getChildren().addAll(profileLabel, activeDonutLabel, table, form, buttons);
		container.setPadding(new Insets(32));
		container.setSpacing(10);

		return container;
	}

	@Override
	public void setAction() {
		table.getSelectionModel().selectedItemProperty().addListener((observable, old, donut) -> {
			activeDonut = donut;
			this.updateForm(donut);
		});

		addButton.setOnMouseClicked(e -> {
			try {
				String name = donutNameField.getText();
				String description = donutDescriptionField.getText();
				Double price = Double.parseDouble(donutPriceField.getText());

				Donut donut = new Donut();
				donut.setDonutName(name);
				donut.setDonutDescription(description);
				donut.setDonutPrice(price);

				controller.create(donut);
				donuts.add(donut);

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setContentText("Donut added successfully");

				alert.show();
			} catch (FormException error) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setContentText(error.getMessage());

				alert.show();
			}

		});

		updateButton.setOnMouseClicked(e -> {
			try {
				if (activeDonut == null)
					throw new FormException("Please select a donut!");

				int index = donuts.indexOf(activeDonut);
				Double price = Double.parseDouble(donutPriceField.getText());

				activeDonut.setDonutName(donutNameField.getText());
				activeDonut.setDonutDescription(donutDescriptionField.getText());
				activeDonut.setDonutPrice(price);

				controller.update(activeDonut);
				donuts.set(index, activeDonut);
				table.getSelectionModel().select(activeDonut);

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setContentText("Donut updated successfully");

				alert.show();
			} catch (FormException error) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setContentText("Please select a donut");

				alert.show();

			}
		});

		deleteButton.setOnMouseClicked(e -> {
			try {
				if (activeDonut == null)
					throw new FormException("Please select a donut!");

				controller.delete(activeDonut);
				donuts.remove(activeDonut);
				table.getSelectionModel().clearSelection();

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setContentText("Donut deleted successfully");

				alert.show();
			} catch (FormException error) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setContentText("Please select a donut");

				alert.show();
			}
		});
	}

	private void initializeTable() {
		table = new TableView<>(donuts);

		TableColumn<Donut, String> idColumn = new TableColumn<>("Donut ID");
		TableColumn<Donut, String> nameColumn = new TableColumn<>("Donut Name");
		TableColumn<Donut, String> descriptionColumn = new TableColumn<>("Donut Description");
		TableColumn<Donut, Double> priceColumn = new TableColumn<>("Donut Price");

		idColumn.setCellValueFactory(new PropertyValueFactory<>("donutID"));
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("donutName"));
		descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("donutDescription"));
		priceColumn.setCellValueFactory(new PropertyValueFactory<>("donutPrice"));

		table.getColumns().addAll(idColumn, nameColumn, descriptionColumn, priceColumn);
	}

	private void initializeForm() {
		VBox nameGroup = new VBox();
		donutNameLabel = new Label("Donut Name");
		donutNameLabel.setFont(new Font("Arial Black", 14));
		donutNameField = new TextField();
		nameGroup.setSpacing(5);
		nameGroup.getChildren().addAll(donutNameLabel, donutNameField);

		VBox descriptionGroup = new VBox();
		donutDescriptionLabel = new Label("Donut Description");
		donutDescriptionLabel.setFont(new Font("Arial Black", 14));
		donutDescriptionField = new TextArea();
		descriptionGroup.setSpacing(5);
		descriptionGroup.getChildren().addAll(donutDescriptionLabel, donutDescriptionField);

		VBox priceGroup = new VBox();
		donutPriceLabel = new Label("Donut Price");
		donutPriceLabel.setFont(new Font("Arial Black", 14));
		donutPriceField = new TextField();
		priceGroup.setSpacing(5);
		priceGroup.getChildren().addAll(donutPriceLabel, donutPriceField);

		form = new VBox();
		form.setSpacing(10);
		form.getChildren().addAll(nameGroup, descriptionGroup, priceGroup);

		addButton = new Button("Add Donut");
		updateButton = new Button("Update Donut");
		deleteButton = new Button("Delete Donut");

		buttons = new HBox();
		buttons.setSpacing(10);
		buttons.getChildren().addAll(addButton, updateButton, deleteButton);
	}

	private void updateForm(Donut donut) {
		if (donut == null) {
			donutNameField.clear();
			donutDescriptionField.clear();
			donutPriceField.clear();
		} else {
			donutNameField.setText(activeDonut.getDonutName());
			donutDescriptionField.setText(activeDonut.getDonutDescription());
			donutPriceField.setText(activeDonut.getDonutPrice().toString());
		}
	}

}
