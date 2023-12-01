package model;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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

public class CheckoutItem extends GridPane {
	
	private Stage mainStage;

	// Label
	Label welcomeLabel, checkoutLabel,item1Label,item2Label,item3Label,totLabel,payLabel;
	
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
	
	// Hbox
	HBox hb,hb2;
	
	// Border Pane
	BorderPane bp;
	
	// Scene
	Scene checkoutScene;
	
	public void Initialize() {
		// Label
		welcomeLabel = new Label();
		checkoutLabel = new Label();
		item1Label = new Label();
		item2Label = new Label();
		item3Label = new Label();
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
		item1Label.setText("1x Avocado [1 x Rp. 23500]");
		item2Label.setText("1x Banana [1 x Rp. 423500]");
		item3Label.setText("1x Apple [1 x Rp. 213500]");
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
		vb2.getChildren().addAll(item1Label,item2Label,item3Label,totLabel);
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
	
	public CheckoutItem(Stage mainStage) {
		Initialize();
		Components();
		ArrangeComponents();
		mainStage.setScene(checkoutScene);
		this.mainStage = mainStage;
	}
	
	public void show() {
		
	}

}
