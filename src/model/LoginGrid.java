package model;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class LoginGrid extends GridPane {
	
	private Label loginLabel,NjuiceLabel,usernameLabel,passwordLabel,welcomeLabel;
	
	private TextField usernameInput;
	private PasswordField passwordInput;
	
	public Button loginButton,logoutButton;

	private VBox vboxUsername;
	private VBox vboxPassword;
	private VBox vboxLoginLabel;
	private VBox vboxLoginButton;
	
	private HBox hboxToolbar;
	
	private BorderPane borderHome;
	
	
	// Tool bar
	private ToolBar toolBar;
	
	

	void initialize() {
		// Label
		loginLabel = new Label();
		NjuiceLabel = new Label();
		usernameLabel = new Label();
		passwordLabel = new Label();
		welcomeLabel = new Label();
		
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
		
		// Hbox
		hboxToolbar = new HBox(50);
		
		// Border Pane
		borderHome = new BorderPane();
		
		// Button
		logoutButton = new Button();
		
		// Tool Bar
		toolBar = new ToolBar();
		
		
		
	}
	
	 void components() {
		// Label
		loginLabel.setText("Login");
		NjuiceLabel.setText("NJuice");
		usernameLabel.setText("Username");
		passwordLabel.setText("Password");
		welcomeLabel.setText("hi,.......");
		
		// Text Field
		usernameInput.setPromptText("Enter Username...");
		usernameInput.setPrefWidth(400);
		passwordInput.setPromptText("Enter Password...");
		
		// Button
		loginButton.setText("Login");
		
		// Edit Text
		loginLabel.setFont(Font.font(null,FontWeight.BOLD,50));
		NjuiceLabel.setFont(Font.font(null,FontWeight.SEMI_BOLD,15));
		
		// VBox
		vboxUsername.getChildren().addAll(usernameLabel,usernameInput);
		vboxPassword.getChildren().addAll(passwordLabel,passwordInput);
		vboxLoginLabel.getChildren().addAll(loginLabel,NjuiceLabel);
		vboxLoginButton.getChildren().addAll(loginButton);
		
		// Grid Pane
		this.add(vboxLoginLabel,0, 0);
		this.add(vboxUsername, 0, 2);
		this.add(vboxPassword, 0, 4);
		this.add(vboxLoginButton, 0, 6);

	}
	
	void arrangeComponents() {
		this.setVgap(10);
		this.setHgap(10);
		
		
		toolBar.setStyle("-fx-spacing: 750px;");

		vboxLoginButton.setAlignment(Pos.CENTER);
		vboxLoginLabel.setAlignment(Pos.CENTER);
		this.setAlignment(Pos.CENTER);

	}

	
	public LoginGrid() {
		initialize();
		components();
		arrangeComponents();
		

		
	
	}
	
	public Button getLoginButton() {
		return loginButton;
		
	}

}
