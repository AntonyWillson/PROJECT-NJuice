package main;

import javafx.application.Application;


import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ToolBar;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import model.LoginGrid;


public class Main extends Application{
	
	
	
	public static void main(String[] args) {
		launch(args);

	}	
	

	@Override
	public void start(Stage mainStage) throws Exception {

		
		LoginGrid login = new LoginGrid(mainStage);
		login.show();
		
	}
	

}
