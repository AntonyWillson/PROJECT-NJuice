package model;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class LoginGrid extends GridPane implements EventHandler<ActionEvent> {
	
	private Label loginLabel,NjuiceLabel,usernameLabel,passwordLabel,errorLabel;
	
	private TextField usernameInput;
	private PasswordField passwordInput;
	
	public Button loginButton,logoutButton;

	private VBox vboxUsername;
	private VBox vboxPassword;
	private VBox vboxLoginLabel;
	private VBox vboxLoginButton;
	
	private BorderPane bp;
	private Scene loginScene;
	
	// Menu
	MenuBar menuBar;	
	Menu menu1;
	MenuItem menuItem1;
	MenuItem menuItem2;
	
	// Tool bar
	private ToolBar toolBar;

	private Stage mainStage;
	
	
	
	

	void initialize() {
		// Label
		loginLabel = new Label();
		NjuiceLabel = new Label();
		usernameLabel = new Label();
		passwordLabel = new Label();

		errorLabel = new Label();
		
		// Text Field
		usernameInput = new TextField();
		passwordInput = new PasswordField();
		
		// Button
		loginButton = new Button();
		
		// VBox
		vboxUsername = new VBox(10);
		vboxPassword = new VBox(10);
		vboxLoginLabel = new VBox(10);
		vboxLoginButton = new VBox(50);
		
		//Menu
		menuBar = new MenuBar();
		menu1 = new Menu();
		menuItem1 = new MenuItem();
		menuItem2 = new MenuItem();
		
		
		// Button
		logoutButton = new Button();
		
		// Tool Bar
		toolBar = new ToolBar();
		
		// Border Pane
		bp = new BorderPane();
		
		loginScene = new Scene(bp,800,600);
		
		
		
	}
	
	 void components() {
		 //Menu
		 menu1.setText("Dashboard");
		 menuItem1.setText("Login");
		 menuItem2.setText("Register");

		 menuBar.getMenus().addAll(menu1);
		 menu1.getItems().addAll(menuItem1,menuItem2);
			
		// Label
		loginLabel.setText("Login");
		NjuiceLabel.setText("NJuice");
		usernameLabel.setText("Username");
		passwordLabel.setText("Password");

		
		// Text Field
		usernameInput.setPromptText("Enter Username...");
		usernameInput.setPrefWidth(400);
		passwordInput.setPromptText("Enter Password...");
		
		// Button
		loginButton.setText("Login");
		
		// Edit Text
		loginLabel.setFont(Font.font(null,FontWeight.BOLD,50));
		NjuiceLabel.setFont(Font.font(null,FontWeight.SEMI_BOLD,15));
		errorLabel.setStyle("-fx-text-fill: red");
		
		// VBox
		vboxUsername.getChildren().addAll(usernameLabel,usernameInput);
		vboxPassword.getChildren().addAll(passwordLabel,passwordInput);
		vboxLoginLabel.getChildren().addAll(loginLabel,NjuiceLabel);
		vboxLoginButton.getChildren().addAll(loginButton);
		
		// Grid Pane
		this.add(vboxLoginLabel,0, 0);
		this.add(vboxUsername, 0, 2);
		this.add(vboxPassword, 0, 4);
		this.add(errorLabel, 0, 5);
		this.add(vboxLoginButton, 0, 6);

	}
	 
	 private void addComponents() {
		 bp.setTop(menuBar);
		 bp.setCenter(this);
	 }
	
	void arrangeComponents() {
		this.setVgap(10);
		this.setHgap(10);
		
		
		toolBar.setStyle("-fx-spacing: 750px;");

		vboxLoginButton.setAlignment(Pos.CENTER);
		vboxLoginLabel.setAlignment(Pos.CENTER);
		this.setAlignment(Pos.CENTER);

	}
	
	private void setEvent() {
		menuItem1.setOnAction(this);
		menuItem2.setOnAction(this);
		loginButton.setOnAction(this);
	}

	
	public LoginGrid(Stage mainStage) {
		initialize();
		components();
		arrangeComponents();
		addComponents();
		setEvent();
		mainStage.setScene(loginScene);
		this.mainStage = mainStage;
		
	
	}
	
	public void show() {
		mainStage.show();
	}

	@Override
	public void handle(ActionEvent e) {
		if (e.getSource() == menuItem2) {
			RegisterGrid register = new RegisterGrid(mainStage);
			register.show();
		}else if (e.getSource() == loginButton) {
			if (usernameInput.getText().isEmpty() || passwordInput.getText().isEmpty()) {
				errorLabel.setText("Please Input all the field");
			}else if (usernameInput.getText().equals("admin")) {
				AdminViewTrans admin = new AdminViewTrans(mainStage);
				admin.show();
			}else if (usernameInput.getText().equals("customer")) {
				CustHomeGrid cust = new CustHomeGrid(mainStage);
				cust.show();
			}
				
			
		}
		
	}

}
