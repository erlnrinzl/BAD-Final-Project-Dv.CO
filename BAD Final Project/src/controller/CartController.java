package controller;

import java.util.List;

import dao.CartDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Cart;
import model.Donut;

public class CartController {
	private CartDAO cartDAO;
	private ObservableList<Cart> userCart;
	private String userID = "US001";
	
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
		this.userCart.addAll(cartDAO.read(userID));
	}
	
	public Double calculateSubtotal() {
		Double subtotal = 0.0;
		
		for (Cart cart : this.userCart) {
			subtotal += cart.getTotalPrice();
		}
		return subtotal;
	}
	
	public void addCart(Integer qty, Donut donut) {
		Cart cart = new Cart(this.userID, qty, donut);
		this.cartDAO.create(cart);
		this.userCart.add(cart);
		//this.loadUserCart();
	}
	
	// not implemented in assigment
	public void updateCart(Cart cart) {
		this.cartDAO.update(cart);
		//this.userCart.
	}
	
	public void deleteCart(Cart cart) {
		this.userCart.remove(cart);
		//this.loadUserCart();
		this.cartDAO.delete(cart);
	}
}
