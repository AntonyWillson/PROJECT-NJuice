package main;



import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.LoginGrid;
import model.RegisterGrid;

public class Main extends Application{
	Scene loginScene,registerScene,homeScene;
	BorderPane borderLogin,borderRegister,borderHome;
	LoginGrid Login;
	RegisterGrid Register;
	
	// Menu
	MenuBar menuBar;	
	Menu menu1;
	MenuItem menuItem1;
	MenuItem menuItem2;

	void initialize() {
		
		// Pane
		borderLogin = new BorderPane();
		borderRegister = new BorderPane();
		borderHome = new BorderPane();
		Register = new RegisterGrid();
		Login = new LoginGrid();
		
		//Menu
		menuBar = new MenuBar();
		menu1 = new Menu();
		menuItem1 = new MenuItem();
		menuItem2 = new MenuItem();
		
		// Scene
		registerScene = new Scene(borderRegister,750,750);
		loginScene = new Scene(borderLogin,750,750);
		homeScene = new Scene(borderHome, 750, 750);
		

	}
	
	void components() {
		//Menu
		menu1.setText("Dashboard");
		menuItem1.setText("Login");
		menuItem2.setText("Register");
		
		menuBar.getMenus().addAll(menu1);
		menu1.getItems().addAll(menuItem1,menuItem2);
		
		// Border Login
		borderRegister.setTop(menuBar);
		borderLogin.setCenter(Login);
		
		// Border Register
		borderRegister.setTop(menuBar);
		borderRegister.setCenter(Register);
		
		
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
		
		menuItem1.setOnAction(event ->{
			mainStage.setScene(loginScene);
			borderLogin.setTop(menuBar);
		});
		
		menuItem2.setOnAction(event ->{
			mainStage.setScene(registerScene);
			borderRegister.setTop(menuBar);
		});
		
		
		mainStage.setScene(registerScene);
		mainStage.show();
		
	}

}
