package model;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CheckoutItem extends GridPane {
	
	private Stage mainStage;

	// Label
	Label welcomeLabel;
	
	// Button
	Button logoutBtn;
	
	// Toolbar
	ToolBar toolbar;
	
	// Region
	Region regionToolbar;
	
	// Vbox
	VBox vb;
	
	// Hbox
	HBox hb;
	
	// Border Pane
	BorderPane bp;
	
	// Scene
	Scene checkoutScene;
	
	public void Initialize() {
		// Label
		welcomeLabel = new Label();
		
		// BUtton
		logoutBtn = new Button();
		
		// Toolbar
		toolbar = new ToolBar();
		
		// Region
		regionToolbar = new Region();
		
		// Vbox
		vb = new VBox();
		
		// Hbox
		hb = new HBox();
		
		// border pane
		bp = new BorderPane();
		
		//Scene
		checkoutScene = new Scene(bp,800,600);
		
	}
	
	public void Components() {
		// Label
		welcomeLabel.setText("Hello Antony");
		
		// Button
		logoutBtn.setText("Logout");
		
		//Toolbar
		HBox.setHgrow(regionToolbar, Priority.ALWAYS);
		toolbar.getItems().addAll(logoutBtn);
		toolbar.getItems().add(regionToolbar);
		toolbar.getItems().add(welcomeLabel);
		
		//Grid Pane
		
		// Border Pane
		bp.setTop(toolbar);
	}
	
	public void ArrangeComponents() {
		
	}
	
	public CheckoutItem(Stage mainStage) {
		Initialize();
		Components();
		ArrangeComponents();
		mainStage.setScene(checkoutScene);
		this.mainStage = mainStage;
	}
	
	public void show() {
		
	}

}
