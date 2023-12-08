package model;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class RegisterGrid extends GridPane implements EventHandler<ActionEvent> {
	
	private static int i = 0;
	
	private Label registerLabel,NjuiceLabel,usernameLabel,passwordLabel,errorLabel;
	
	private TextField usernameInput;
	private PasswordField passwordInput;
	
	private CheckBox tncCheck;
	
	private Button registerButton;
	
	private VBox vboxUsername;
	private VBox vboxPassword;
	private VBox vboxRegisterLabel;
	private VBox vboxRegisterButton;
	private VBox vboxTncCheck;
	
	private BorderPane bp;
	private Scene registerScene;

	private Stage mainStage;
	
	// Menu
	MenuBar menuBar;	
	Menu menu1;
	MenuItem menuItem1;
	MenuItem menuItem2;
	
	void initialize() {
		// Label
		registerLabel = new Label();
		NjuiceLabel = new Label();
		usernameLabel= new Label();
		passwordLabel = new Label();
		errorLabel = new Label();
		
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
		vboxRegisterLabel = new VBox(10);
		vboxRegisterButton = new VBox(50);
		vboxTncCheck = new VBox(50);
		
		//Menu
		menuBar = new MenuBar();
		menu1 = new Menu();
		menuItem1 = new MenuItem();
		menuItem2 = new MenuItem();
		
		//Border
		bp = new BorderPane();
		registerScene = new Scene(bp,800,600);
	}
	
	void components() {
		//Menu
		menu1.setText("Dashboard");
		menuItem1.setText("Login");
		menuItem2.setText("Register");
		
		menuBar.getMenus().addAll(menu1);
		menu1.getItems().addAll(menuItem1,menuItem2);
		
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
		registerButton.setText("Register");
		
		// Edit Text
		registerLabel.setFont(Font.font(null,FontWeight.BOLD,50));
		NjuiceLabel.setFont(Font.font(null,FontWeight.SEMI_BOLD,15));
		errorLabel.setStyle("-fx-text-fill: red");
		
		// VBox
		vboxUsername.getChildren().addAll(usernameLabel,usernameInput);
		vboxPassword.getChildren().addAll(passwordLabel,passwordInput);
		vboxRegisterLabel.getChildren().addAll(registerLabel,NjuiceLabel);
		vboxTncCheck.getChildren().add(tncCheck);
		vboxRegisterButton.getChildren().addAll(registerButton);
		
		
		// Check Box
		tncCheck.setText("I aggre to the terms and conditions of NJuice!");
		
		// Grid Pane
		this.add(vboxRegisterLabel,0, 0);
		this.add(vboxUsername, 0, 2);
		this.add(vboxPassword, 0, 4);
		this.add(vboxTncCheck, 0, 5);
		this.add(errorLabel, 0, 6);
		this.add(vboxRegisterButton, 0, 7);
		
		// Border
		bp.setTop(menuBar);
		bp.setCenter(this);
	}
	
	void arrangecomponents() {
		this.setVgap(10);
		this.setHgap(10);
		
		vboxRegisterLabel.setAlignment(Pos.CENTER);
		vboxRegisterButton.setAlignment(Pos.CENTER);
		
		this.setAlignment(Pos.CENTER);
	}
	
	void setEvent() {
		menuItem1.setOnAction(this);
		menuItem2.setOnAction(this);
		registerButton.setOnAction(this);
	}
	
	public RegisterGrid(Stage mainStage) {
		initialize();
		components();
		arrangecomponents();
		setEvent();
		mainStage.setScene(registerScene);
		this.mainStage = mainStage;
	}
	
	public void show() {
		mainStage.show();
	}

	@Override
	public void handle(ActionEvent e) {
		if (e.getSource() == menuItem1) {
			LoginGrid login = new LoginGrid(mainStage);
			login.show();
		}else if (e.getSource() == registerButton) {
			if (usernameInput.getText().isEmpty() || passwordInput.getText().isEmpty()) {
				errorLabel.setText("Please Input all the field");
			}else if (!tncCheck.isSelected()) {
				errorLabel.setText("Please click the tnc");
			}else {
				i++;
				String id = String.format("CU%03d", i);
				System.out.println(id);
		
				LoginGrid login = new LoginGrid(mainStage);
				login.show();	
			}
			
		}
		
	}
}
