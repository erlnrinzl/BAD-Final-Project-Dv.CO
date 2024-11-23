package controller;

import java.util.List;

import dao.CartDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Cart;
import model.Donut;
import model.User;

public class CartController {
	private CartDAO cartDAO;
	private ObservableList<Cart> userCart;
	private User user = new User(
			"Dummy Name",
			"email@mail.com",
			"pass123",
			"male",
			"Indonesia",
			"08231231231",
			"User",
			10
		);
	
	
	public CartController() {
		super();
		this.cartDAO = new CartDAO();
		this.userCart = FXCollections.observableArrayList();
		
		//to be removed soon!
		this.user.setUserID("US001");
	}

	public ObservableList<Cart> getUserCart() {
		return this.userCart;
	}

	public void loadUserCart() {
		this.userCart.clear();
		this.userCart.addAll(cartDAO.read(this.user.getUserID()));
	}
	
	public Double calculateSubtotal(ObservableList<Cart> selectedUserCart) {
		Double subtotal = 0.0;
		
		for (Cart cart : selectedUserCart) {
			subtotal += cart.getTotalPrice();
		}
		return subtotal;
	}
	
	public Cart addCart(Integer qty, Donut donut) {
		Cart cart = new Cart(this.user.getUserID(), qty, donut);
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
