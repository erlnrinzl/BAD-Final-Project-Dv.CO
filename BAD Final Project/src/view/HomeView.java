package view;

import controller.CartController;
import controller.DonutController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import model.Donut;
import util.SessionManager;

public class HomeView {
	
	private static VBox vLayout, detailLayout;
	private static ListView<String> donutList = new ListView<String>();
	private static DonutController donutController= new DonutController();
	private static CartController cartController = new CartController();
	
	public static Scene render() {
		Label profileLabel = new Label("Hello, " + SessionManager.getUser().getUsername());
		profileLabel.setFont(new Font("Arial Black", 36));		
		
		vLayout = new VBox();
		detailLayout = new VBox();

		vLayout.getChildren().add(profileLabel);
		detailLayout.getChildren().clear();
		
		if (SessionManager.getUser().getRole() != "User") {
			// render admin view
		} else {
			renderCustomerView();
		}
		
		vLayout.setPadding(new Insets(50));
		vLayout.setSpacing(10);
		
		return AppShell.render(vLayout);
	}
	
	private static void renderCustomerView() {
		updateDisplayedDonut();
		Label activeDonutLabel = new Label("Active Donut:");
		
		FlowPane addCartLayout = new FlowPane();
		addCartLayout.getChildren().add(donutList);
		addCartLayout.getChildren().add(detailLayout);
		
		donutList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue != null) {
				Donut selectedDonut = donutController.findDonutByName(newValue);
				
				if (selectedDonut != null) {
					detailLayout.getChildren().clear();
					renderDetail(selectedDonut);
				}
			}
		});
		
		vLayout.getChildren().add(activeDonutLabel);
		vLayout.getChildren().add(addCartLayout);
		
	}
	
	private static void updateDisplayedDonut() {
		donutList.getItems().clear();		
		donutController.loadDonutProducts();
		
		for (Donut donut : donutController.getDonutProducts()) {
			donutList.getItems().add(donut.getDonutName());			
		}
	}
	
	private static void renderDetail(Donut donut) {
		Label productNameLabel = new Label(donut.getDonutName());
		productNameLabel.setFont(new Font("Arial Black", 24));
		Label productDescLabel = new Label(donut.getDonutDescription());
		Label productPriceLabel = new Label("Rp." + String.format("%,.2f", donut.getDonutPrice()));
		productPriceLabel.setFont(new Font("Arial Black", 12));
		
		Spinner<Integer> qtySpinner = new Spinner<>(1, 999, 1);
        qtySpinner.setEditable(true);
        
        Button addCartBtn = new Button("Add to cart");
        addCartBtn.setOnAction(e->{
        	Integer qty = qtySpinner.getValue();
        	cartController.addCart(qty, donut);
        });
		
		detailLayout.getChildren().add(productNameLabel);
		detailLayout.getChildren().add(productDescLabel);
		detailLayout.getChildren().add(productPriceLabel);
		detailLayout.getChildren().add(qtySpinner);
		detailLayout.getChildren().add(addCartBtn);
		detailLayout.setPadding(new Insets(10));
		detailLayout.setSpacing(10);
	}
}
