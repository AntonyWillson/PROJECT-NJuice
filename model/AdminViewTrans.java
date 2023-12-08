package model;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class AdminViewTrans extends GridPane implements EventHandler<ActionEvent> {
	//Menu
	MenuBar menuBar;
	Menu menu1,menuLogout;
	MenuItem menuItem1;
	MenuItem menuItem2,menuItem3;
	
	//Label
	Label viewTransLabel;
	
	//Border
	BorderPane bp;
	
	//Table
	TableView<Transaction> table;
	TableView<Detail> tableDetail;
	
	//Scene
	Scene adminScene;
	private Stage mainStage;
	
	//Vbox
	VBox vbox;
	
	void Initialize() {
		//Menu
		menuBar = new MenuBar();
		menu1 = new Menu();
		menuLogout = new Menu();
		menuItem1 = new MenuItem();
		menuItem2 = new MenuItem();
		menuItem3 = new MenuItem();
		
		// Border
		bp = new BorderPane();
		
		//Label
		viewTransLabel = new Label();
		
		// VBox
		vbox = new VBox(20);
		
		//Table
		table = new TableView<Transaction>();
		tableDetail = new TableView<Detail>();
		//Scene
		adminScene = new Scene(bp,800,600);
		
		
	}
	
	void CreateTable() {
		//TRansaction
		TableColumn<Transaction, String> idCol = new TableColumn<>("Transaction Id");
		idCol.setCellValueFactory(new PropertyValueFactory<Transaction, String>("id"));
		idCol.setMinWidth(100);
		TableColumn<Transaction, String> paymentCol = new TableColumn<>("Payment Type");
		paymentCol.setCellValueFactory(new PropertyValueFactory<Transaction, String>("payment"));
		paymentCol.setMinWidth(100);
		TableColumn<Transaction, String> nameCol = new TableColumn<>("Username");
		nameCol.setCellValueFactory(new PropertyValueFactory<Transaction, String>("name"));
		nameCol.setMinWidth(100);
		
		table.getColumns().addAll(idCol,paymentCol,nameCol);
		
		table.setMaxWidth(300);
		
		//Detail
		TableColumn<Detail, String> idCol1 = new TableColumn<>("Transaction Id");
		idCol1.setCellValueFactory(new PropertyValueFactory<Detail, String>("idCol1"));
		idCol1.setMinWidth(adminScene.getWidth()/8);
		TableColumn<Detail, String> jIdCol = new TableColumn<>("Juice ID");
		jIdCol.setCellValueFactory(new PropertyValueFactory<Detail, String>("jIdCol"));
		jIdCol.setMinWidth(adminScene.getWidth()/8);
		TableColumn<Detail, String> jName = new TableColumn<>("Juice Name");
		jName.setCellValueFactory(new PropertyValueFactory<Detail, String>("jName"));
		jName.setMinWidth(adminScene.getWidth()/5);
		TableColumn<Detail, Integer> qty = new TableColumn<>("Quantity");
		qty.setCellValueFactory(new PropertyValueFactory<Detail, Integer>("qty"));
		qty.setMinWidth(adminScene.getWidth()/8);
		tableDetail.setPlaceholder(new Label("Click on a transaction header to view the transaction detail"));
		
		tableDetail.getColumns().addAll(idCol1,jIdCol,jName,qty);
		
	}

	void Components() {
		//Menu
		menu1.setText("Admin's Dashboard");
		menuLogout.setText("Logout");
		menuItem1.setText("View Transaction");
		menuItem2.setText("Manage Products");
		menuItem3.setText("Logout from admin");
		
		menuBar.getMenus().addAll(menu1,menuLogout);
		menu1.getItems().addAll(menuItem1,menuItem2);
		menuLogout.getItems().addAll(menuItem3);
		
		//Label
		viewTransLabel.setText("View Transaction");
		
		// Edit TExt
		viewTransLabel.setFont(Font.font(null,FontWeight.BOLD,15));
		
		//vbox
		vbox.getChildren().addAll(viewTransLabel,table,tableDetail);
		
		// Grid
		this.add(vbox,0,0);

	}
	
	void addComponents() {
		//Border
		bp.setTop(menuBar);
		bp.setCenter(this);
	}
	void ArrangeComponents() {
		
		this.setAlignment(Pos.CENTER);
		vbox.setAlignment(Pos.CENTER);
		
	}
	
	void SetEvent() {
		menuItem2.setOnAction(this);
		menuItem3.setOnAction(this);
	}
	
	public AdminViewTrans(Stage mainStage) {
		Initialize();
		Components();
		addComponents();
		ArrangeComponents();
		SetEvent();
		CreateTable();
		mainStage.setScene(adminScene);
		this.mainStage = mainStage;
		
	}
	
	public void show() {
		mainStage.show();
	}

	@Override
	public void handle(ActionEvent e) {
		if (e.getSource() == menuItem2) {
			ManageProducts manage = new ManageProducts(mainStage);
			manage.show();
		}else if (e.getSource() == menuItem3) {
			LoginGrid login = new LoginGrid(mainStage);
			login.show();
		}
		
	}

}
