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
		super();
		this.cartDAO = new CartDAO();
		this.userCart = FXCollections.observableArrayList();
	}

	public ObservableList<Cart> getUserCart() {
		return this.userCart;
	}

	public void loadUserCart() {
		this.userCart.clear();
		this.userCart.addAll(cartDAO.read(SessionManager.getUser().getUserID()));
	}
	
	public Double calculateSubtotal(ObservableList<Cart> selectedUserCart) {
		Double subtotal = 0.0;
		
		for (Cart cart : selectedUserCart) {
			subtotal += cart.getTotalPrice();
		}
		return subtotal;
	}
	
	public Cart addCart(Integer qty, Donut donut) {
		Cart cart = new Cart(SessionManager.getUser().getUserID(), qty, donut);
		this.cartDAO.create(cart);
		this.userCart.add(cart);
		//this.loadUserCart();
		return cart;
	}
	
	// not implemented in case assigment
	public Cart updateCart(Cart cart) {
		this.cartDAO.update(cart);
		//this.userCart.
		return cart;
	}
	
	public Cart deleteCart(Cart cart) {
		this.userCart.remove(cart);
		//this.loadUserCart();
		this.cartDAO.delete(cart);
		return cart;
	}
}
