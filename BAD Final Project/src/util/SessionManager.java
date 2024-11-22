package util;

import model.User;

public class SessionManager {
	private static User user;
	
	public static void login(User user) {
		SessionManager.user = user;
	}
	
	public static void logout() {
		SessionManager.user = null;
	}
	
	public static boolean isLoggedIn() {
		return user != null;
	}
	
	public static User getUser() {
		return user;
	}
}
