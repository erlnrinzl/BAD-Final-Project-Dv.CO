package view;

import controller.CartController;
import controller.TransactionHeaderController;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import model.Cart;

public class CartView {
	private static CartController cartController = new CartController();
	private static TransactionHeaderController transactionController = new TransactionHeaderController();
	private static Cart selectedCart;
	private static ObservableList<Cart> selectedUserCarts;
	
	public static Scene render() {
		cartController.loadUserCart();
		selectedUserCarts = FXCollections.observableArrayList();
		
		Label titleLabel = new Label("Your Cart");
		titleLabel.setFont(new Font("Arial Black", 36));
		Label subtotalLabel = new Label("Rp." + String.format("%,.2f", cartController.calculateSubtotal(selectedUserCarts)));
		subtotalLabel.setFont(new Font("Arial Black", 12));
		
		TableView<Cart> cartTable= new TableView<Cart>();
		TableColumn<Cart, String> nameCol = new TableColumn<Cart, String>("Name");
		TableColumn<Cart, String> priceCol = new TableColumn<Cart, String>("Price");
		TableColumn<Cart, Integer> qtyCol = new TableColumn<Cart, Integer>("Quantity");
		TableColumn<Cart, String> totalCol = new TableColumn<Cart, String>("Total");
		
		nameCol.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getDonut().getDonutName()));
		priceCol.setCellValueFactory(cellData -> new ReadOnlyStringWrapper("Rp." + String.format("%,.2f", cellData.getValue().getDonut().getDonutPrice())));
		qtyCol.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getQuantity()).asObject());
		totalCol.setCellValueFactory(cellData -> new ReadOnlyStringWrapper("Rp." + String.format("%,.2f", cellData.getValue().getTotalPrice())));
		
		cartTable.getColumns().addAll(nameCol, priceCol, qtyCol, totalCol);
		
		cartTable.setItems(cartController.getUserCart());
		
		cartTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue != null) {
				selectedCart = newValue;
				
				//wrap inside array
				selectedUserCarts.clear();
				selectedUserCarts.add(selectedCart);
				subtotalLabel.setText("Rp." + String.format("%,.2f", cartController.calculateSubtotal(selectedUserCarts)));
			}
		});
		
		Button checkoutBtn = new Button("Checkout");
		checkoutBtn.setOnMouseClicked(e->{
			if (selectedCart != null) {
				transactionController.createTransaction(selectedUserCarts);
				Alert checkoutSuccessAlert = new Alert(AlertType.INFORMATION);
				checkoutSuccessAlert.setContentText("Checkout success");
				checkoutSuccessAlert.showAndWait();
			} else {
				Alert checkoutFailAlert = new Alert(AlertType.ERROR);
				checkoutFailAlert.setContentText("No item selected for checkout");
				checkoutFailAlert.showAndWait();
			}
		});
		
		VBox cartLayout = new VBox();
		cartLayout.getChildren().add(titleLabel);
		cartLayout.getChildren().add(cartTable);
		cartLayout.getChildren().add(subtotalLabel);
		cartLayout.getChildren().add(checkoutBtn);
		cartLayout.setPadding(new Insets(50));
		cartLayout.setSpacing(10);
		cartLayout.setAlignment(Pos.CENTER);
		
		return AppShell.render(cartLayout);
	}
}
