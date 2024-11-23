package view;

import controller.CartController;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import model.Cart;

public class CartView {
	private static CartController controller = new CartController();
	
	public static Scene render() {
		controller.loadUserCart();
		
		Label titleLabel = new Label("Your Cart");
		titleLabel.setFont(new Font("Arial Black", 36));
		Label subtotalLabel = new Label("Rp." + String.format("%,.2f",controller.calculateSubtotal()));
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
		
		cartTable.setItems(controller.getUserCart());
		
		Button checkoutBtn = new Button("Checkout");
		
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
