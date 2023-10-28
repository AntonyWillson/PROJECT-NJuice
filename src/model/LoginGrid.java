package model;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class LoginGrid extends GridPane {
	
	private Label loginLabel,NjuiceLabel,usernameLabel,passwordLabel;
	
	private TextField usernameInput;
	private PasswordField passwordInput;
	
	private Button loginButton;

	private VBox vboxUsername;
	private VBox vboxPassword;
	private VBox vboxLoginLabel;
	private VBox vboxLoginButton;
	


	
	void initialize() {
		// Label
		loginLabel = new Label();
		NjuiceLabel = new Label();
		usernameLabel = new Label();
		passwordLabel = new Label();
		
		// Text Field
		usernameInput = new TextField();
		passwordInput = new PasswordField();
		
		// Button
		loginButton = new Button();
		
		// VBox
		vboxUsername = new VBox(10);
		vboxPassword = new VBox(10);
		vboxLoginLabel = new VBox(20);
		vboxLoginButton = new VBox(30);
		
	}
	
	void components() {
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
		NjuiceLabel.setFont(Font.font(null,FontWeight.BOLD,15));
		
		// VBox
		
		vboxUsername.getChildren().addAll(usernameLabel,usernameInput);
		vboxPassword.getChildren().addAll(passwordLabel,passwordInput);
		vboxLoginLabel.getChildren().add(loginLabel);
		vboxLoginButton.getChildren().addAll(vboxPassword,loginButton);
		
		// Grid Pane
		this.add(vboxLoginLabel,0, 0);
		this.add(NjuiceLabel, 0, 1);
		this.add(vboxUsername, 0, 2);
		this.add(vboxPassword, 0, 4);
		this.add(vboxLoginButton, 0, 6);
		
	}
	
	void arrangeComponents() {
		this.setVgap(10);
		this.setHgap(10);
		this.setAlignment(Pos.CENTER);

	}
	
	public LoginGrid() {
		initialize();
		components();
		arrangeComponents();
	}
}
