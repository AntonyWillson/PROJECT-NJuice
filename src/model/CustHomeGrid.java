package model;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import jfxtras.labs.scene.control.window.Window;

public class CustHomeGrid extends GridPane implements EventHandler<ActionEvent> {
	private Label cartLabel,descCartLabel,welcomeLabel;
	
	private VBox vBoxCartLabel;
	
	private HBox hBoxButton;
	
	private Button addButton,deleteButton,checkoutButton,logoutButton;
	
	private Scene custHomeScene;
	
	private BorderPane bp;

	private Stage mainStage;
	
	//Tool Bar
	private ToolBar toolbar;
	
	//Region
	private Region regionToolbar;
	
	//List
	private ListView<String> cartList;
	
	// Window 
//	Window window;
	
	//Alert
	Alert alert;
	
	// StackPane
//	StackPane popUp;

	void initialize() {
		
		// Label
		cartLabel = new Label();
		descCartLabel = new Label();
		welcomeLabel = new Label();
		
		
		// Vbox
		vBoxCartLabel = new VBox(20);
		
		// Hbox
		hBoxButton = new HBox(20);
		
		// Button
		addButton = new Button();
		deleteButton = new Button();
		checkoutButton = new Button();
		logoutButton = new Button();
		
		// Stack Pane

		
		// Toolbar
		toolbar = new ToolBar();
		
		//Region
		regionToolbar = new Region();
		
		//List
		cartList = new ListView<>();
		
		// Alert
		alert = new Alert(Alert.AlertType.ERROR);
		
		//Border
		bp = new BorderPane();
		
////		// Window
//		window = new Window();
//		window.setMaxHeight(100);
//		window.setMaxWidth(100);
	
//		// StackPane
//		popUp = new StackPane();
		
		// Scene
		custHomeScene = new Scene(bp,800,600);
		
	}
	
	void components() {
		// Label
		cartLabel.setText("Your Cart");
		descCartLabel.setText("Your cart is empty, try adding items!");
		welcomeLabel.setText("Hello Antony");
		
		// Edit Text
		cartLabel.setFont(Font.font(null,FontWeight.BOLD,50));
		descCartLabel.setFont(Font.font(null,FontWeight.NORMAL,10));
		
		// Button
		addButton.setText("Add new item to cart");
		deleteButton.setText("Delete Item from cart");
		checkoutButton.setText("checkout");
		logoutButton.setText("Logout");
		
		// VBox
		//list
		cartList.getItems().addAll("1x Avocado [Rp 75.000]");
		
		if (cartList.getItems().isEmpty()) {
			vBoxCartLabel.getChildren().addAll(cartLabel,descCartLabel);	
		}else {
			vBoxCartLabel.getChildren().addAll(cartLabel,cartList);
			
		}
		
		
		// HBox
		hBoxButton.getChildren().addAll(addButton,deleteButton,checkoutButton);
		
		//Tool Bar
		HBox.setHgrow(regionToolbar, Priority.ALWAYS);
		toolbar.getItems().addAll(logoutButton);
		toolbar.getItems().add(regionToolbar);
		toolbar.getItems().add(welcomeLabel);
		

//		// Window
//		window.getContentPane().getChildren().addAll(vBoxCartLabel);
		
	
//		// Stack Pane
//		popUp.getChildren().add(window);
//		popUp.setAlignment(Pos.CENTER);

		
		// Grid Pane
		this.add(vBoxCartLabel, 0, 0); 
		this.add(hBoxButton, 0, 1); 
		
		// Border Pane
		bp.setTop(toolbar);
		bp.setCenter(this);
//		bp.setRight(window);
		
		
	}
	
	void arrangeComponents() {
		this.setVgap(10);
		this.setHgap(10);
		
		addButton.setPrefWidth(150);
		addButton.setPrefHeight(50);
		
		deleteButton.setPrefWidth(150);
		deleteButton.setPrefHeight(50);
		
		checkoutButton.setPrefWidth(150);
		checkoutButton.setPrefHeight(50);
		
		vBoxCartLabel.setAlignment(Pos.CENTER);
		this.setAlignment(Pos.CENTER);
	}
	
	void setEvent() {
		logoutButton.setOnAction(this);
		addButton.setOnAction(this);
		deleteButton.setOnAction(this);
		checkoutButton.setOnAction(this);
	}
	
	
	public CustHomeGrid(Stage mainStage) {
		initialize();
		components();
		arrangeComponents();
		setEvent();
		mainStage.setScene(custHomeScene);
		this.mainStage = mainStage;
		
	}
	
	public void show() {
		mainStage.show();
	}

	@Override
	public void handle(ActionEvent e) {
		if (e.getSource() == logoutButton) {
			LoginGrid login = new LoginGrid(mainStage);
			login.show();
		}else if (e.getSource() == deleteButton) {
			String selectedValue = cartList.getSelectionModel().getSelectedItem();
			if (selectedValue != null) {
			cartList.getItems().remove(selectedValue);
			}else {
				alert.setTitle("Error");
				alert.setContentText("Please choose which juice to delete");
				alert.show();				
			}
		}else if (e.getSource() == addButton) {

		}else if (e.getSource() == checkoutButton) {
			ObservableList<String> items = cartList.getItems();
			if (items.isEmpty()) {
				alert.setTitle("Error");
				alert.setContentText("Your cart is empty");
				alert.show();
			}
		}
		
	}
}
