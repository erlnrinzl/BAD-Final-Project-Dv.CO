package view;

import javafx.geometry.Insets;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class AdminHome extends Page {

	public AdminHome() {
		super();
	}
	
	@Override
	public void init() {
		
	}

	@Override
	public Pane layout() {
		Text title = new Text("This is Admin Homepage");
		
		VBox container = new VBox();
		container.getChildren().addAll(title);
		container.setPadding(new Insets(50));
		container.setSpacing(10);

        return container;
	}

	@Override
	public void setAction() {
		
	}

}
