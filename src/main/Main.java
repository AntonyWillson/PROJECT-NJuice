package main;

import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import model.CustHomeGrid;
import model.LoginGrid;
import model.RegisterGrid;

public class Main extends Application{
	Scene loginScene,registerScene,homeScene;
	BorderPane borderLogin,borderRegister,borderHome;
	LoginGrid login;
	RegisterGrid register;
	CustHomeGrid home;
	
	// Menu
	MenuBar menuBar;	
	Menu menu1;
	MenuItem menuItem1;
	MenuItem menuItem2;
	
	// Tool Bar
	ToolBar toolBar;
	
	// Button
	Button logoutButton;
	
	// Label
	Label welcomeLabel;
	
	// Region
	Region regionToolBar;
	


	private void initialize() {
		
		// Pane
		borderLogin = new BorderPane();
		borderRegister = new BorderPane();
		borderHome = new BorderPane();
		
		home = new CustHomeGrid();
		
		//Menu
		menuBar = new MenuBar();
		menu1 = new Menu();
		menuItem1 = new MenuItem();
		menuItem2 = new MenuItem();
		
		// Scene
		registerScene = new Scene(borderRegister,750,750);
		loginScene = new Scene(borderLogin,750,750);
		homeScene = new Scene(borderHome, 750, 750);
		
		// Tool Bar
		toolBar = new ToolBar();
	
		// Button
		logoutButton = new Button();
		
		// Label
		welcomeLabel = new Label();

		
		// Region
		regionToolBar = new Region();

	}
	
	private void components() {
		
		// Label
		welcomeLabel.setText("Hi, Antony");
		
		// button
		logoutButton.setText("Logout");
	
		
		// Tool bar
		HBox.setHgrow(regionToolBar, Priority.ALWAYS);
		toolBar.getItems().addAll(logoutButton);
		toolBar.getItems().add(regionToolBar);
		toolBar.getItems().add(welcomeLabel);
	}
	
	private void arrangeComponents() {
		
	}

	
	
	public static void main(String[] args) {
		launch(args);

	}	
	

	@Override
	public void start(Stage mainStage) throws Exception {
		initialize();
		components();
		arrangeComponents();
		
		LoginGrid login = new LoginGrid(mainStage);
		login.show();
		
	}
	

}
