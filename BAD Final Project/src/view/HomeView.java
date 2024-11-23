package view;

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
	private static String userRole = "User";
	private static DonutController controller = new DonutController();
	
	public static Scene render() {
		Label profileLabel = new Label("Hello, " + "(replace with username)");
		profileLabel.setFont(new Font("Arial Black", 36));		
		
		vLayout = new VBox();
		vLayout.getChildren().add(profileLabel);
		
		if (userRole != "User") {
			// render admin view
		} else {
			renderCustomerView();
		}
		
		vLayout.setPadding(new Insets(50));
		vLayout.setSpacing(10);
		
		return new AppShell().render(vLayout);
	}
	
	private static void renderCustomerView() {
		updateDisplayedDonut();
		Label activeDonutLabel = new Label("Active Donut:");
		
		FlowPane addCartLayout = new FlowPane();
		addCartLayout.getChildren().add(donutList);
		
		donutList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue != null) {
				Donut selectedDonut = controller.findDonutByName(newValue);
				
				if (selectedDonut != null) {
	                if (detailLayout != null) {
	                    addCartLayout.getChildren().remove(detailLayout);
	                }
	                
					renderDetail(selectedDonut);
					addCartLayout.getChildren().add(detailLayout);
				}
			}
		});
		
		vLayout.getChildren().add(activeDonutLabel);
		vLayout.getChildren().add(addCartLayout);
		
	}
	
	private static void updateDisplayedDonut() {
		donutList.getItems().clear();		
		controller.loadDonutProducts();
		
		for (Donut donut : controller.getDonutProducts()) {
			donutList.getItems().add(donut.getDonutName());			
		}
	}
	
	private static void renderDetail(Donut donut) {
		Label productNameLabel = new Label(donut.getDonutName());
		productNameLabel.setFont(new Font("Arial Black", 24));
		Label productDescLabel = new Label(donut.getDonutDescription());
		Label productPriceLabel = new Label("Rp." + donut.getDonutPrice().toString());
		productPriceLabel.setFont(new Font("Arial Black", 12));
		
		Spinner<Integer> qtySpinner = new Spinner<>(1, 999, 1);
        qtySpinner.setEditable(true);
        
        Button addCartBtn = new Button("Add to cart");
        addCartBtn.setOnAction(e->{
        	//do something here
        });
		
		detailLayout = new VBox();
		detailLayout.getChildren().add(productNameLabel);
		detailLayout.getChildren().add(productDescLabel);
		detailLayout.getChildren().add(productPriceLabel);
		detailLayout.getChildren().add(qtySpinner);
		detailLayout.getChildren().add(addCartBtn);
		detailLayout.setPadding(new Insets(10));
		detailLayout.setSpacing(10);
	}
}
