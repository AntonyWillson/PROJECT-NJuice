package model;

import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import util.Connect;

public class CheckoutItem extends GridPane implements EventHandler<ActionEvent> {

	private Stage mainStage;

	// Label
	Label welcomeLabel, checkoutLabel,item1Label,totLabel,payLabel;

	// Button
	Button logoutBtn,cancelBtn,checkoutBtn;

	// Toolbar
	ToolBar toolbar;

	//Radio button
	RadioButton payType1,payType2,payType3;
	ToggleGroup payGroup;

	// Region
	Region regionToolbar;

	// Vbox
	VBox vb,vb2,vb3,vb4,vb5;

	//Alert
	Alert alert;

	// Hbox
	HBox hb,hb2;

	// Border Pane
	BorderPane bp;

	// Scene
	Scene checkoutScene;

	private String loginUsername;

	private ListView<String> cartList;

	public void Initialize() {
		// Label
		welcomeLabel = new Label();
		checkoutLabel = new Label();
		item1Label = new Label();
		totLabel = new  Label();
		payLabel = new Label();

		// BUtton
		logoutBtn = new Button();
		cancelBtn = new Button();
		checkoutBtn = new Button();

		// Toolbar
		toolbar = new ToolBar();

		// Radio BUtton
		payType1 = new RadioButton();
		payType2 = new RadioButton();
		payType3 = new RadioButton();
		payGroup = new ToggleGroup();

		// Region
		regionToolbar = new Region();

		// Vbox
		vb = new VBox();
		vb2 = new VBox();
		vb3 = new VBox();
		vb4 = new VBox();
		vb5 = new VBox();

		// Hbox
		hb = new HBox();
		hb2 = new HBox();

		// border pane
		bp = new BorderPane();

		//Scene
		checkoutScene = new Scene(bp,800,600);

	}

	public void Components() {
		// Label
		welcomeLabel.setText("Hello Antony");
		checkoutLabel.setText("Checkout");
		checkoutLabel.setFont(Font.font(null,FontWeight.EXTRA_BOLD,40));

		totLabel.setText("Total Price: ");
		payLabel.setText("Payment Type:");

		// Button
		logoutBtn.setText("Logout");
		cancelBtn.setText("Cancel");
		checkoutBtn.setText("Checkout");

		//Toolbar
		HBox.setHgrow(regionToolbar, Priority.ALWAYS);
		toolbar.getItems().addAll(logoutBtn);
		toolbar.getItems().add(regionToolbar);
		toolbar.getItems().add(welcomeLabel);

		//Radio Button
		payType1.setText("Cash");
		payType2.setText("Debit");
		payType3.setText("Credit");
		payGroup.getToggles().addAll(payType1,payType2,payType3);

		//Hbox
		hb.getChildren().addAll(payType1,payType2,payType3);
		hb2.getChildren().addAll(cancelBtn,checkoutBtn);

		//Vbox
		vb.getChildren().addAll(checkoutLabel);
		vb2.getChildren().addAll(item1Label,totLabel);
		vb3.getChildren().addAll(payLabel,hb);
		vb4.getChildren().add(hb2);

		//grid pane
		this.add(vb, 0, 0);
		this.add(vb2, 0, 1);
		this.add(vb3, 0, 2);
		this.add(vb4, 0, 3);
		this.add(hb2, 0,4);

		vb5.getChildren().add(this);

		// Border Pane
		bp.setTop(toolbar);
		bp.setCenter(this);
	}

	public void ArrangeComponents() {
		this.setAlignment(Pos.CENTER);
		this.setVgap(10);

		//VBox
		vb.setAlignment(Pos.CENTER);
		vb2.setSpacing(5);
		vb3.setSpacing(5);

		//Button
		cancelBtn.setPrefSize(100, 50);
		checkoutBtn.setPrefSize(100,50);

		//hBox
		hb2.setAlignment(Pos.CENTER);
		hb2.setSpacing(10);
		hb.setSpacing(50);

	}

	public void SetEvent() {
		cancelBtn.setOnAction(this);
		checkoutBtn.setOnAction(this);
		logoutBtn.setOnAction(this);


	}

	private String generateTransactionId() {
		Connect connect = Connect.getInstance();
		int maxId = 0;

		String query = "SELECT MAX(CAST(SUBSTRING(TransactionId, 3) AS SIGNED)) AS MaxId FROM transactionheader";
		connect.rs = connect.executeQuery(query);

		try {
			if (connect.rs.next()) {
				maxId = connect.rs.getInt("MaxId");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		String nextId = String.format("TR%03d", maxId + 1);
		return nextId;
	}

	void checkoutData() {
		Connect connect = Connect.getInstance();
		String transactionId = generateTransactionId();
		String paymentType = ((RadioButton) payGroup.getSelectedToggle()).getText();

		String insertHeaderQuery = "INSERT INTO transactionheader (TransactionId, Username, PaymentType) VALUES (?, ?, ?)";
		try {
			connect.pst = connect.con.prepareStatement(insertHeaderQuery);
			connect.pst.setString(1, transactionId);  
			connect.pst.setString(2, loginUsername);
			connect.pst.setString(3, paymentType);
			connect.pst.executeUpdate();
			System.out.println(paymentType);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		checkoutData2();

		cartList.getItems().clear();

		String deleteCartDetailQuery = "DELETE FROM cartdetail WHERE Username = ?";
		try {
			connect.pst = connect.con.prepareStatement(deleteCartDetailQuery);
			connect.pst.setString(1, loginUsername);
			connect.pst.executeUpdate();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Message");
		alert.setContentText("All items checked out successfully, please proceed further...");
		alert.show();
	}

	void checkoutData2() {
		Connect connect = Connect.getInstance();
		String transactionId = generateTransactionId();
		String insertDetailQuery = "INSERT INTO transactiondetail (TransactionId, JuiceId, Quantity) VALUES (?, (SELECT JuiceId FROM msjuice WHERE JuiceName = ? LIMIT 1), ?)";

		try {

			String selectedValue = item1Label.getText();

			int indexOfX = selectedValue.indexOf("x");
			int indexOfHyphen = selectedValue.indexOf("-");
			if (indexOfX != -1 && indexOfHyphen != -1) {
				String name = selectedValue.substring(indexOfX + 2, indexOfHyphen - 1).trim();
				System.out.println("Extracted Name: " + name);

				int startIndex = selectedValue.indexOf("x") + 1;
				int endIndex = selectedValue.indexOf(" ");

				if (startIndex != -1 && endIndex != -1) {
					String quantityString = selectedValue.substring(startIndex, endIndex).trim();


					int quantity = Integer.parseInt(quantityString);
					System.out.println(name);
					System.out.println(quantity);

					connect.pst = connect.con.prepareStatement(insertDetailQuery);
					connect.pst.setString(1, transactionId);
					connect.pst.setString(2, name);
					connect.pst.setInt(3, quantity);
					connect.pst.executeUpdate();
				}
			}
		} catch (SQLException e1) {
			e1.printStackTrace();


		}
	}


	void getData(String username) {
		Connect connect = Connect.getInstance();
		String query = "SELECT cd.Username, cd.Quantity, mj.JuiceName, cd.Quantity * mj.Price AS 'Total Price' FROM msuser mu JOIN cartdetail cd ON mu.Username = cd.Username JOIN msjuice mj ON mj.JuiceId = cd.JuiceId WHERE cd.Username = ?";

		VBox itemsVBox = new VBox();

		try {
			connect.pst = connect.con.prepareStatement(query);
			connect.pst.setString(1, loginUsername);
			connect.rs = connect.pst.executeQuery();

			while (connect.rs.next()) {
				String juiceName = connect.rs.getString("JuiceName");
				int quantity = connect.rs.getInt("Quantity");
				int totalPrice = connect.rs.getInt("Total Price");

				String row = String.format("%d x %s - [Rp. %d]", quantity, juiceName, totalPrice);

				Label label = new Label(row); 
				itemsVBox.getChildren().add(label);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		vb2.getChildren().clear();
		vb2.getChildren().add(itemsVBox);
	}

	void RefreshTable() {
		getData(loginUsername);
	}

	public CheckoutItem(Stage mainStage, String loginUsername, ListView<String> cartList) {
		this.cartList = cartList;
		this.loginUsername = loginUsername;
		Initialize();
		Components();
		ArrangeComponents();
		SetEvent();
		RefreshTable();
		mainStage.setScene(checkoutScene);
		this.mainStage = mainStage;
	}

	public void show() {

	}

	@Override
	public void handle(ActionEvent e) {
		if (e.getSource() == checkoutBtn) {
			if (payGroup.getSelectedToggle() == null) {
				alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error");
				alert.setContentText("Please select payment type");
				alert.show();
			}else{
				checkoutData();
			}
		}else if (e.getSource() == cancelBtn) {
			CustHomeGrid home = new CustHomeGrid(mainStage,loginUsername);
			home.show();
		}else if (e.getSource() == logoutBtn) {
			LoginGrid login = new LoginGrid(mainStage);
			login.show();
		}

	}

}
