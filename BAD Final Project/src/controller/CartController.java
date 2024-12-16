package controller;

import java.util.List;

import dao.CartDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Cart;
import model.Donut;
import model.User;
import util.SessionManager;

public class CartController {
	private CartDAO cartDAO;
	private ObservableList<Cart> userCart;

	public CartController() {
		this.cartDAO = new CartDAO();
		this.userCart = FXCollections.observableArrayList();
	}

	public ObservableList<Cart> getUserCart() {
		return this.userCart;
	}

	public void loadUserCart() {
		User user = SessionManager.getUser();
		String userID = user.getUserID();

		List<Cart> carts = cartDAO.read(userID);

		this.userCart.clear();
		this.userCart.addAll(carts);
	}

	public Double calculateSubtotal(ObservableList<Cart> selectedUserCart) {
		Double subtotal = 0.0;

		for (Cart cart : selectedUserCart) {
			subtotal += cart.getTotalPrice();
		}

		return subtotal;
	}

	public Cart addCart(Integer quantity, Donut donut) {
		Cart existingCart = null;

		for (Cart cart : this.userCart) {
			if (cart.getDonutID().equals(donut.getDonutID())) {
				existingCart = cart;
				break;
			}
		}

		if (existingCart != null) {
			existingCart.setQuantity(existingCart.getQuantity() + quantity);
			this.cartDAO.update(existingCart);
			
			return existingCart;
		}

		User user = SessionManager.getUser();
		String userID = user.getUserID();
		
		Cart newCart = new Cart(userID, quantity, donut);
		this.cartDAO.create(newCart);
		this.userCart.add(newCart);
		
		return newCart;
	}

	// not implemented in case assignment
	public Cart updateCart(Cart cart) {
		this.cartDAO.update(cart);
		
		return cart;
	}

	public Cart deleteCart(Cart cart) {
		this.userCart.remove(cart);
		this.cartDAO.delete(cart);
		
		return cart;
	}
	
}
