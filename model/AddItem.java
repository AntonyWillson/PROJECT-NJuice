package model;


import java.sql.SQLException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import jfxtras.labs.scene.control.window.Window;
import util.Connect;

public class AddItem extends GridPane implements EventHandler<ActionEvent> {

	// Stage
	private Stage mainStage;
	private Stage popupStage;

	//Label
	Label juiceLabel,juicePrice,juiceDesc,juiceQty,juiceTotPrice;

	//Spinner
	Spinner<Integer> Qty;
	SpinnerValueFactory<Integer> QtySpinnerFactory;

	//Button
	Button addBtn;

	// Combo Box
	ComboBox<String> juiceName;

	// Border Pane
	BorderPane bp;

	//Vbox
	VBox vb;

	// HBox
	HBox hb;

	//Scene 
	Scene scene;

	// Window
	Window window;

	//Stack Pane
	StackPane popUp;

	Connect connect = Connect.getInstance();
	private String loginUsername;

	void Initialize() {

		//Label
		juiceLabel = new  Label();
		juiceDesc = new  Label();
		juicePrice = new  Label();
		juiceQty = new  Label();
		juiceTotPrice = new  Label();

		// Spinner
		Qty = new Spinner<>();
		QtySpinnerFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,1000000000,1,1);

		// Button
		addBtn = new Button();

		// COmbo Box
		juiceName = new ComboBox<String>();

		// Border Pane
		bp = new BorderPane();

		//Vbox
		vb = new VBox();

		//Hbox
		hb = new HBox();

		//// // Window
		window = new Window();


		//  // StackPane
		popUp = new StackPane();

		// Scene
		scene = new Scene(popUp,600,600);
		window.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));



	}

	void Components() {
		//Label
		juiceLabel.setText("Juice: ");
		//  juicePrice.setText("Juice Price: ");
		juiceDesc.setText("Description: ");
		juiceDesc.setWrapText(true);
		juiceQty.setText("Quantity: ");
		juiceTotPrice.setText("Total Price: ");

		//Button
		addBtn.setText("Add Item");


		//Spinner
		Qty.setValueFactory(QtySpinnerFactory);
		Qty.valueProperty().addListener((observable, oldValue, newValue) -> {
			getData2(); 
		});

		//Hbox
		hb.getChildren().addAll(juiceName,juicePrice);

		//Vbox
		vb.getChildren().addAll(juiceLabel,hb,juiceDesc,juiceQty,Qty,juiceTotPrice,addBtn);

	}

	void ArrangeComponents() {
		bp.setCenter(vb);
		hb.setAlignment(Pos.CENTER);
		vb.setAlignment(Pos.CENTER);

		hb.setSpacing(20);
		vb.setSpacing(20);

		//  // Window
		window.getContentPane().getChildren().addAll(bp);
		window.setTitle("Add new item");

		//  // Stack Pane
		popUp.getChildren().add(window);
	}

	public void SetEvent() {
		addBtn.setOnAction(this);
		juiceName.setOnAction(event -> getData2());
	}

	private void getData() {
		String query1 = "SELECT JuiceName FROM msjuice"; 
		connect.rs = connect.executeQuery(query1);


		try {
			while (connect.rs.next()) {
				String name = connect.rs.getString("JuiceName");
				juiceName.getItems().add(name);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		getData2();
	}  

	private void getData2() {
		String selectedJuiceName = juiceName.getValue();
		int quantity = Qty.getValue();

		if (selectedJuiceName != null) {

			String query2 = "SELECT Price, JuiceDescription FROM msjuice WHERE JuiceName = ?";
			try {
				connect.pst = connect.con.prepareStatement(query2);
				connect.pst.setString(1, selectedJuiceName);

				connect.rs = connect.pst.executeQuery();

				if (connect.rs.next()) {
					int juicePriceValue = connect.rs.getInt("Price");
					String juiceDescrip = connect.rs.getString("JuiceDescription");

					int totalJuicePrice = juicePriceValue * quantity;

					juiceTotPrice.setText("Total Price: " + totalJuicePrice);
					juicePrice.setText("Juice Price: " + juicePriceValue);

					juiceDesc.setText(juiceDescrip);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {

			juicePrice.setText("Juice Price: ");
		}
	}

	public void saveCartItem(String username, String juiceName, int quantity, int juicePriceValue) {
	    // Check if the item already exists in the cart
	    String checkQuery = "SELECT * FROM cartdetail WHERE Username = ? AND JuiceId = (SELECT JuiceId FROM msjuice WHERE JuiceName = ?)";
	    try {
	        connect.pst = connect.con.prepareStatement(checkQuery);
	        connect.pst.setString(1, username);
	        connect.pst.setString(2, juiceName);
	        connect.rs = connect.pst.executeQuery();

	        if (connect.rs.next()) {
	            int oldQuantity = connect.rs.getInt("Quantity");
	            int newQuantity = oldQuantity + quantity;


	            String updateQuery = "UPDATE cartdetail SET Quantity = ? WHERE Username = ? AND JuiceId = (SELECT JuiceId FROM msjuice WHERE JuiceName = ?)";
	            connect.pst = connect.con.prepareStatement(updateQuery);
	            connect.pst.setInt(1, newQuantity);
	            connect.pst.setString(2, username);
	            connect.pst.setString(3, juiceName);
	            connect.pst.executeUpdate();
	        } else {

	            String insertQuery = "INSERT INTO cartdetail (Username, JuiceId, Quantity) VALUES (?, (SELECT JuiceId FROM msjuice WHERE JuiceName = ?), ?)";
	            connect.pst = connect.con.prepareStatement(insertQuery);
	            connect.pst.setString(1, username);
	            connect.pst.setString(2, juiceName);
	            connect.pst.setInt(3, quantity);
	            connect.pst.executeUpdate();
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}





	private void refreshTable() {
		getData();

	}

	public AddItem(Stage mainStage, Stage popupStage,String loginUsername) {
		this.loginUsername = loginUsername;
		Initialize();
		Components();
		ArrangeComponents();
		SetEvent();
		refreshTable();

		this.popupStage = popupStage;
		this.mainStage = mainStage;

	}

	public Scene getScenes() {
		return scene;
	}

	public void show() {
		mainStage.show();
	}

	@Override
	public void handle(ActionEvent e) {
		if (e.getSource() == addBtn) {
			if (e.getSource() == addBtn) {
				String selectedJuiceName = juiceName.getValue();
				int quantity = Qty.getValue();

				if (selectedJuiceName != null && quantity > 0) {
					int juicePriceValue;
					try {
						juicePriceValue = connect.rs.getInt("Price");
						getData2();
						saveCartItem(loginUsername, selectedJuiceName, quantity, juicePriceValue * quantity);
						CustHomeGrid home = new CustHomeGrid(mainStage, loginUsername);
						home.addItem(quantity + " x " + selectedJuiceName + " - [Rp. " + juicePriceValue * quantity + "]",false);
						popupStage.close();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		}
	}
}


