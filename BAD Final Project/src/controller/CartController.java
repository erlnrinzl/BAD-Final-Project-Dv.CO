package controller;

import java.util.List;

import dao.CartDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Cart;

public class CartController {
	private CartDAO cartDAO;
	private ObservableList<Cart> userCart;
	private String userID = "US001";
	
	public CartController() {
		super();
		this.cartDAO = new CartDAO();
		userCart = FXCollections.observableArrayList();
	}

	public ObservableList<Cart> getUserCart() {
		return userCart;
	}

	public void loadUserCart() {
		List<Cart> testCart = cartDAO.read(userID);
		userCart.clear();
		userCart.addAll(cartDAO.read(userID));
	}
	
	public Double calculateSubtotal() {
		Double subtotal = 0.0;
		
		for (Cart cart : this.userCart) {
			subtotal += cart.getTotalPrice();
		}
		
		return subtotal;
	}
}
