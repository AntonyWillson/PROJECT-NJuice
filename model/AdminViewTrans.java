package model;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import util.Connect;

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
	
	//SQL
	ArrayList<Transaction> transactionList = new ArrayList<Transaction>();
	ObservableList<Detail> detailList = FXCollections.observableArrayList();

	   

	Connect connect = Connect.getInstance();
	
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
	
	private void getData(){
		transactionList.clear();
		
		String query = "SELECT * FROM transactionheader";
		connect.rs = connect.executeQuery(query);
		
		try {
			while (connect.rs.next()) {
				String id = connect.rs.getString("TransactionId");
				String name = connect.rs.getString("Username");
				String payment = connect.rs.getString("PaymentType");
				
				Transaction transaction = new Transaction(id, name, payment);
				transactionList.add(transaction);
						
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void getData2(String transSelected) {
		
		
		 String query = "SELECT th.transactionId, mj.JuiceId, JuiceName, Quantity FROM transactionheader th JOIN transactiondetail td ON th.TransactionId = td.TransactionId JOIN MsJuice mj ON mj.JuiceId = td.JuiceId WHERE th.transactionId = '"+transSelected+"'";
		 connect.rs = connect.executeQuery(query);
		 
		 try {
			 detailList.clear();
				while (connect.rs.next()) {
					String idCol1 =  connect.rs.getString("transactionId");
                    String jIdCol=  connect.rs.getString("JuiceId");
                    String jName = connect.rs.getString("JuiceName");
                    int qty = connect.rs.getInt("Quantity");
					
					
					detailList.add(new Detail(idCol1, jIdCol, jName, qty));
							
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 tableDetail.setItems(detailList);
	}
	
	private void refreshTable() {
		getData();
		ObservableList<Transaction> transObj = FXCollections.observableArrayList(transactionList);
		table.setItems(transObj);

	}
	
	
	
	public AdminViewTrans(Stage mainStage) {
		Initialize();
		Components();
		addComponents();
		ArrangeComponents();
		SetEvent();
		CreateTable();
		refreshTable();
		setEventMouse();
		mainStage.setScene(adminScene);
		this.mainStage = mainStage;
		
	}
	
	public void show() {
		mainStage.show();
	}

	void setEventMouse() {
		table.setOnMouseClicked(e -> {
			Transaction transSelected = table.getSelectionModel().getSelectedItem();
			if (transSelected != null) {
				getData2(transSelected.getId());
			}
		});
	}
	@Override
	public void handle(ActionEvent e) {
		
}
}

	

