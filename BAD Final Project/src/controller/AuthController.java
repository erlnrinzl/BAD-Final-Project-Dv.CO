package controller;

import dao.UserDAO;
import exception.AuthException;
import model.User;
import util.RouteManager;
import util.SessionManager;

public class AuthController {
	private UserDAO dao;

	public AuthController() {
		this.dao = new UserDAO();
	}
	
	public void login(String email, String password) throws AuthException {
		User user = dao.getByEmail(email);
		
		if (user != null && user.getPassword().equals(password)) {
			SessionManager.login(user);
			
			if (user.getRole().equalsIgnoreCase("admin")) {
				RouteManager.navigate("admin_home");
			} else {	
				RouteManager.navigate("customer_home");
			}
			
			return;
		} 
		
		throw new AuthException("Wrong credentials");
	}

	public void register(User user) {
		
	}
	
	public void logout() {
		SessionManager.logout();
		RouteManager.navigate("login");
	}
	
}
