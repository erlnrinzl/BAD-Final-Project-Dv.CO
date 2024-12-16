package view;

import controller.CartController;
import controller.TransactionHeaderController;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import model.Cart;
import view.component.AlertComponent;

public class CartView extends Page {

	private CartController cartController;
	private TransactionHeaderController transactionController;
	private Cart selectedCart;
	private ObservableList<Cart> selectedUserCarts;
	private Label titleLabel;
	private Label subtotalLabel;
	private TableView<Cart> cartTable;
	private Button checkoutBtn;

	public CartView() {
		super();
	}

	@Override
	public void init() {
		cartController = new CartController();
		transactionController = new TransactionHeaderController();

		cartController.loadUserCart();
		selectedUserCarts = FXCollections.observableArrayList();
		selectedUserCarts.clear();
		selectedCart = null;

		titleLabel = new Label("Your Cart");
		titleLabel.setFont(new Font("Arial Black", 36));

		subtotalLabel = new Label();
		updateSubtotalLabel();

		cartTable = new TableView<>();
		setupCartTable();

		checkoutBtn = new Button("Checkout");
	}

	@Override
	public Pane layout() {
		VBox cartLayout = new VBox();
		cartLayout.getChildren().addAll(titleLabel, cartTable, subtotalLabel, checkoutBtn);
		cartLayout.setPadding(new Insets(50));
		cartLayout.setSpacing(10);
		cartLayout.setAlignment(Pos.CENTER);

		return cartLayout;
	}

	@Override
	public void setAction() {
		cartTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue != null) {
				selectedCart = newValue;
				selectedUserCarts.clear();
				selectedUserCarts.add(selectedCart);
				updateSubtotalLabel();
			}
		});

		checkoutBtn.setOnMouseClicked(e -> {
			if (selectedCart != null) {
				transactionController.createTransaction(selectedUserCarts);
				cartController.deleteCart(selectedCart);

				AlertComponent.success("Success", "Checkout success");

				selectedCart = null;
				updateSubtotalLabel();
				cartTable.setItems(cartController.getUserCart());
			} else {
				AlertComponent.error("Checkout Failed", "Error", "No item selected for checkout");
			}
		});
	}

	private void setupCartTable() {
		TableColumn<Cart, String> nameCol = new TableColumn<>("Name");
		nameCol.setCellValueFactory(
				cellData -> new ReadOnlyStringWrapper(cellData.getValue().getDonut().getDonutName()));

		TableColumn<Cart, String> priceCol = new TableColumn<>("Price");
		priceCol.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(
				"Rp." + String.format("%,.2f", cellData.getValue().getDonut().getDonutPrice())));

		TableColumn<Cart, Integer> qtyCol = new TableColumn<>("Quantity");
		qtyCol.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getQuantity()).asObject());

		TableColumn<Cart, String> totalCol = new TableColumn<>("Total");
		totalCol.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(
				"Rp." + String.format("%,.2f", cellData.getValue().getTotalPrice())));

		cartTable.getColumns().addAll(nameCol, priceCol, qtyCol, totalCol);
		cartTable.setItems(cartController.getUserCart());
	}

	private void updateSubtotalLabel() {
		subtotalLabel.setText("Rp." + String.format("%,.2f", cartController.calculateSubtotal(selectedUserCarts)));
		subtotalLabel.setFont(new Font("Arial Black", 12));
	}
}
