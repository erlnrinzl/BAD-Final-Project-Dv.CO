package view;

import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class AppShell {
	private static MenuBar menuBar = new MenuBar();
	private static String userID = null;
	private static String userRole = "User";
	
	public static Scene render(Pane childPane) {
		clearMenus();
		
		if (userID != null) {
			if (userRole != "User") {
				renderAdminMenu();
			} else {
				renderCustomerMenu();
			}
		} else {		
			renderGuestMenu();
		}
		
		BorderPane rootLayout = new BorderPane();
		rootLayout.setTop(menuBar);
		rootLayout.setCenter(childPane);
		
		return new Scene(rootLayout);
	}
	
	private static void renderCustomerMenu() {
		Menu navMenu = new Menu("Dashboard");
		Menu logoutMenu = new Menu("Logout");
		MenuItem logoutSubMenu = new MenuItem("Login");
		MenuItem homeSubMenu = new MenuItem("Home");
		MenuItem cartSubMenu = new MenuItem("Cart");
		
		navMenu.getItems().addAll(homeSubMenu, cartSubMenu);
		logoutMenu.getItems().add(logoutSubMenu);		
		menuBar.getMenus().addAll(navMenu, logoutMenu);
	}
	
	private static void renderAdminMenu() {
		Menu logoutMenu = new Menu("Logout");
		MenuItem logoutSubMenu = new MenuItem("Login");
		
		logoutMenu.getItems().add(logoutSubMenu);
		menuBar.getMenus().add(logoutMenu);
	}
	
	private static void renderGuestMenu() {
		Menu accountMenu = new Menu("Menu");
		MenuItem loginSubMenu = new MenuItem("Login");
		MenuItem registerSubMenu = new MenuItem("Register");
		
		accountMenu.getItems().addAll(loginSubMenu, registerSubMenu);
		menuBar.getMenus().add(accountMenu);
	}
	
	private static void clearMenus() {
	    menuBar.getMenus().clear();
	}
}
