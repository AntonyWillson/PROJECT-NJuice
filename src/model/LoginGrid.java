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
	
	private VBox vBox;
	
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
		vBox = new VBox();
	}
	
	void components() {
		// Label
		loginLabel.setText("Login");
		NjuiceLabel.setText("NJuice");
		usernameLabel.setText("Username");
		passwordLabel.setText("Password");
		
		// Text Field
		usernameInput.setPromptText("Enter Username...");
		passwordInput.setPromptText("Enter Password...");
		
		// Button
		loginButton.setText("Login");
		
		// Edit Text
		loginLabel.setFont(Font.font(null,FontWeight.BOLD,25));
		
		// Vbox
		vBox.getChildren().addAll(loginLabel,NjuiceLabel);
		
		// Grid Pane
		this.addRow(0, vBox);
//		this.addRow(1, NjuiceLabel);
		this.addRow(2, usernameLabel);
		this.addRow(3, usernameInput);
		this.addRow(4, passwordLabel);
		this.addRow(5, passwordInput);
		this.addRow(6, loginButton);
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
