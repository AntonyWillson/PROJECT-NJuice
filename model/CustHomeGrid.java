package model;

import java.sql.SQLException;

import javafx.collections.FXCollections;
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
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import jfxtras.labs.scene.control.window.Window;
import util.Connect;

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

	// StackPane
	StackPane popUp;
	Scene scene2;

	Stage popupStage;

	//Alert
	Alert alert;

	// Obersavle List
	ObservableList<String> cartItems = FXCollections.observableArrayList();


	private String loginUsername;


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

		// stage
		popupStage = new Stage();
		popupStage.initModality(Modality.APPLICATION_MODAL);


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



		// Scene
		custHomeScene = new Scene(bp,800,600);


		popUp = new StackPane();

	}

	void components() {

		// Label
		cartLabel.setText("Your Cart");
		descCartLabel.setText("Your cart is empty, try adding items!");
		welcomeLabel.setText("Hi, "+loginUsername);

		// Edit Text
		cartLabel.setFont(Font.font(null,FontWeight.BOLD,50));
		descCartLabel.setFont(Font.font(null,FontWeight.NORMAL,10));

		// Button
		addButton.setText("Add new item to cart");
		deleteButton.setText("Delete Item from cart");
		checkoutButton.setText("checkout");
		logoutButton.setText("Logout");

		// VBox

		// HBox
		hBoxButton.getChildren().addAll(addButton,deleteButton,checkoutButton);

		//Tool Bar
		HBox.setHgrow(regionToolbar, Priority.ALWAYS);
		toolbar.getItems().addAll(logoutButton);
		toolbar.getItems().add(regionToolbar);
		toolbar.getItems().add(welcomeLabel);


		// Grid Pane
		this.add(vBoxCartLabel, 0, 0); 
		this.add(hBoxButton, 0, 1); 

		// Border Pane
		bp.setTop(toolbar);
		bp.setCenter(this);


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

	void getData(String username) {
		Connect connect = Connect.getInstance();
		String query = "SELECT cd.Username, cd.Quantity, mj.JuiceName, cd.Quantity * mj.Price AS 'Total Price' FROM msuser mu JOIN cartdetail cd ON mu.Username = cd.Username JOIN msjuice mj ON mj.JuiceId = cd.JuiceId WHERE cd.Username = ?";

		try {
			connect.pst = connect.con.prepareStatement(query);
			connect.pst.setString(1, loginUsername);
			connect.rs = connect.pst.executeQuery();

			while (connect.rs.next()) {
				String juiceName = connect.rs.getString("JuiceName");
				int quantity = connect.rs.getInt("Quantity");
				int totalPrice = connect.rs.getInt("Total Price");

				String row = String.format("%d x %s - [Rp. %d]", quantity, juiceName, totalPrice);
				cartItems.add(row);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	void RemoveData(String username, String selectedValue) {
		Connect connect = Connect.getInstance();
		String query = "DELETE FROM cartdetail WHERE Username = ? AND JuiceId = (SELECT JuiceId FROM msjuice WHERE JuiceName = ?)";

		try {
			connect.pst = connect.con.prepareStatement(query);
			connect.pst.setString(1, username);
			connect.pst.setString(2, selectedValue);
			connect.pst.executeUpdate();
			cartItems.remove(selectedValue);

			cartList.getItems().remove(selectedValue);

			cartList.getSelectionModel().clearSelection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	void RefreshTable() {
		vBoxCartLabel.getChildren().clear();
		if (cartItems.isEmpty()) {
			vBoxCartLabel.getChildren().addAll(cartLabel, descCartLabel);
		} else {
			vBoxCartLabel.getChildren().addAll(cartLabel, cartList);
		}

		// Menjadi
		cartList.getItems().setAll(cartItems);
	}

	void addItem(String item, boolean addToListView) {
	    if (!cartItems.contains(item)) {
	        cartItems.add(item);
	        if (addToListView) {
	            RefreshTable();
	        }
	    }
	}


	public CustHomeGrid(Stage mainStage, String loginUsername) {
		this.loginUsername = loginUsername;
		initialize();
		components();
		arrangeComponents();
		setEvent();
		getData(loginUsername);
		RefreshTable();
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
				int i = selectedValue.indexOf("x");
				int j = selectedValue.indexOf("-");
				if (i != -1 && j != -1) {
					String name = selectedValue.substring(i + 2, j - 1).trim();

					RemoveData(loginUsername, name);
					cartList.getItems().remove(selectedValue);
				} 
			}else {
				alert.setTitle("Error");
				alert.setContentText("Please choose which juice to delete");
				alert.show();
			}
		}else if (e.getSource() == addButton) {
			AddItem add = new AddItem(mainStage,popupStage,loginUsername);
			add.show();

			scene2 = add.getScenes();
			popupStage.setScene(scene2);
			popupStage.show();


		}else if (e.getSource() == checkoutButton) {
			ObservableList<String> items = cartList.getItems();
			if (items.isEmpty()) {
				alert.setTitle("Error");
				alert.setContentText("Your cart is empty");
				alert.show();
			}else {
				CheckoutItem checkout = new CheckoutItem(mainStage,loginUsername,cartList);
				checkout.show();
			}
		}

	}
}
