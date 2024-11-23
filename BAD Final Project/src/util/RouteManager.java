package util;

import java.util.HashMap;
import java.util.function.Supplier;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class RouteManager {
	private static Stage primaryStage;
	private static final HashMap<String, Route> routes = new HashMap<>();
	private static String currentRoute;
	
	public static void init(Stage stage) {
		primaryStage = stage;
	}
	
	public static void addRoute(String name, Supplier<Scene> sceneSupplier, String title) {
		routes.put(name, new Route(sceneSupplier, title));
	}
	
	public static void navigate(String routeName) {
		if (!routes.containsKey(routeName)) {
			throw new IllegalArgumentException("Route " + routeName + " not found");
		}
		
		Route route = routes.get(routeName);
		currentRoute = routeName;
		
		primaryStage.setScene(route.sceneSupplier.get());
		primaryStage.setTitle(route.title);
	}
	
	private static class Route {
		Supplier<Scene> sceneSupplier;
		String title;
		
		Route(Supplier<Scene> sceneSupplier, String title) {
			this.sceneSupplier = sceneSupplier;
			this.title = title;
		}
	}

	public static String getCurrentRoute() {
		return currentRoute;
	}

	public static void setCurrentRoute(String currentRoute) {
		RouteManager.currentRoute = currentRoute;
	}
	
}
