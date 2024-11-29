package view;

import controller.CartController;
import controller.DonutController;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import model.Donut;
import util.SessionManager;

public class HomeView extends Page {

    private Label profileLabel;
    private Label activeDonutLabel;
    private ListView<String> donutList;
    private VBox detailLayout;
    private FlowPane addCartLayout;
    private DonutController donutController;
    private CartController cartController;

    public HomeView() {
        super();
    }

    @Override
    public void init() {
    	if (SessionManager.getUser() != null) {
    	    profileLabel = new Label("Hello, " + SessionManager.getUser().getUsername());
    	} else {
    	    profileLabel = new Label("Hello, Guest");
    	}
        
        profileLabel.setFont(new Font("Arial Black", 36));
        activeDonutLabel = new Label("Active Donut:");
        
        donutList = new ListView<>();
        detailLayout = new VBox();
        addCartLayout = new FlowPane();
        
        donutController = new DonutController();
        cartController = new CartController();

        cartController.loadUserCart();
    }

    @Override
    public Pane layout() {
        updateDisplayedDonut();

        addCartLayout.getChildren().addAll(donutList, detailLayout);
        addCartLayout.setPadding(new Insets(10));
        addCartLayout.setHgap(20);

        VBox vLayout = new VBox();
        vLayout.getChildren().addAll(profileLabel, activeDonutLabel, addCartLayout);
        vLayout.setPadding(new Insets(50));
        vLayout.setSpacing(10);

        return vLayout;
    }

    @Override
    public void setAction() {
        // Define actions for specific components if needed
    	donutList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                Donut selectedDonut = donutController.findDonutByName(newValue);
                if (selectedDonut != null) {
                    detailLayout.getChildren().clear();
                    renderDetail(selectedDonut);
                }
            }
        });
    }

    private void updateDisplayedDonut() {
        donutList.getItems().clear();
        donutController.loadDonutProducts();

        for (Donut donut : donutController.getDonutProducts()) {
            donutList.getItems().add(donut.getDonutName());
        }
    }

    private void renderDetail(Donut donut) {
        Label productNameLabel = new Label(donut.getDonutName());
        productNameLabel.setFont(new Font("Arial Black", 24));
        
        Label productDescLabel = new Label(donut.getDonutDescription());
        Label productPriceLabel = new Label("Rp." + String.format("%,.2f", donut.getDonutPrice()));
        productPriceLabel.setFont(new Font("Arial Black", 12));

        Spinner<Integer> qtySpinner = new Spinner<>(1, 999, 1);
        qtySpinner.setEditable(true);

        Button addCartBtn = new Button("Add to cart");
        
        addCartBtn.setOnAction(e -> {
            Integer qty = qtySpinner.getValue();
            cartController.addCart(qty, donut);
        });

        detailLayout.getChildren().addAll(productNameLabel, productDescLabel, productPriceLabel, qtySpinner, addCartBtn);
        detailLayout.setPadding(new Insets(10));
        detailLayout.setSpacing(10);
    }
}
