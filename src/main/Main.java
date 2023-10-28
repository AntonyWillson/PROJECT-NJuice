package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.LoginGrid;

public class Main extends Application{
	Scene mainScene;
	BorderPane border;
	LoginGrid Login;
	
	// Menu
	MenuBar menuBar;	
	Menu menu1;
	MenuItem menuItem1;
	MenuItem menuItem2;
	
	void initialize() {
		
		// Pane
		border = new BorderPane();
		Login = new LoginGrid();
		
		//Menu
		menuBar = new MenuBar();
		menu1 = new Menu();
		menuItem1 = new MenuItem();
		menuItem2 = new MenuItem();
		
		// Scene
		mainScene = new Scene(border,750,750);
	}
	
	void components() {
		//Menu
		menu1.setText("Dashboard");
		menuItem1.setText("Login");
		menuItem2.setText("Register");
		
		menuBar.getMenus().addAll(menu1);
		menu1.getItems().addAll(menuItem1,menuItem2);
		
		// Border
		border.setTop(menuBar);
		border.setCenter(Login);
		
	}
	
	void arrangeComponents() {
		
	}
	public static void main(String[] args) {
		launch(args);

	}	

	@Override
	public void start(Stage mainStage) throws Exception {
		initialize();
		components();
		arrangeComponents();
		
		mainStage.setScene(mainScene);
		mainStage.show();
		
	}

}
