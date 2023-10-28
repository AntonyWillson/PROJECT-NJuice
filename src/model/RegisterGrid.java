package model;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class RegisterGrid extends GridPane {
	
	private Label registerLabel,NjuiceLabel,usernameLabel,passwordLabel;
	
	private TextField usernameInput;
	private PasswordField passwordInput;
	
	private CheckBox tncCheck;
	
	private Button registerButton;
	
	private VBox vboxUsername;
	private VBox vboxPassword;
	private VBox vboxRegisterLabel;
	private VBox vboxRegisterButton;
	private VBox vboxTncCheck;
	
	void initialize() {
		// Label
		registerLabel = new Label();
		NjuiceLabel = new Label();
		usernameLabel= new Label();
		passwordLabel = new Label();
		
		// Input
		usernameInput = new TextField();
		passwordInput = new PasswordField();
		
		// Check box
		tncCheck = new CheckBox();
		
		// Button
		registerButton = new Button();
		
		// Check box
		tncCheck = new CheckBox();
		
		// VBox
		vboxUsername = new VBox(10);
		vboxPassword = new VBox(10);
		vboxRegisterLabel = new VBox(20);
		vboxRegisterButton = new VBox(30);
		vboxTncCheck = new VBox(30);
	}
	
	void components() {
		// Label
		registerLabel.setText("Register");
		NjuiceLabel.setText("NJuice");
		usernameLabel.setText("Username");
		passwordLabel.setText("Password");
		
		// Text Field
		usernameInput.setPromptText("Enter Username...");
		usernameInput.setPrefWidth(400);
		passwordInput.setPromptText("Enter Password...");
		
		// Button
		registerButton.setText("Login");
		
		// Edit Text
		registerLabel.setFont(Font.font(null,FontWeight.BOLD,50));
		NjuiceLabel.setFont(Font.font(null,FontWeight.BOLD,15));
		
		// VBox
		vboxUsername.getChildren().addAll(usernameLabel,usernameInput);
		vboxPassword.getChildren().addAll(passwordLabel,passwordInput);
		vboxRegisterLabel.getChildren().add(registerLabel);
		vboxRegisterButton.getChildren().addAll(vboxPassword,registerButton);
		vboxTncCheck.getChildren().add(tncCheck);
		
		// Check Box
		tncCheck.setText("I aggre to the terms and conditions of NJuice!");
		
		// Grid Pane
		this.add(vboxRegisterLabel,0, 0);
		this.add(NjuiceLabel, 0, 1);
		this.add(vboxUsername, 0, 2);
		this.add(vboxPassword, 0, 4);
		this.add(vboxTncCheck, 0, 5);
		this.add(vboxRegisterButton, 0, 6);
	}
	
	void arrangecomponents() {
		this.setVgap(10);
		this.setHgap(10);
		this.setAlignment(Pos.CENTER);
	}
	
	public RegisterGrid() {
		initialize();
		components();
		arrangecomponents();
	}
}
