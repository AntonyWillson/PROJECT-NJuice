package model;



import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import util.Connect;

public class ManageProducts extends GridPane implements EventHandler<ActionEvent> {
	int i = 1;
	Random rand = new Random();
	
	//Menu
	MenuBar menuBar;
	Menu menu1,menuLogout;
	MenuItem menuItem1;
	MenuItem menuItem2,menuItem3;

	//Vbox
	VBox vbox,vb2,vb3,vb4;
	
	//hbox
	HBox hbox,hbox1,hbox2,hbox3,hb,hb2;
	
	//Buitton
	Button insertBtn,updateBtn,removeBtn;
	
	//Region
	Region space,space2;
	
	//Border
	BorderPane bp;

	//Scene
	Scene manageScene;
	private Stage mainStage;

	// Combo Box
	ComboBox<String> productId;

	//Spinner
	Spinner<Integer> price;
	SpinnerValueFactory<Integer> priceSpinnerFactory;

	//Text field & Area
	TextField nameField;
	TextArea descArea;
	
	//Alert
	Alert alert;

	// Observable list
	private ObservableList<Products> productsList;
	 
	//Label
	Label manageLabel,idLabel,nameLabel,priceLabel,descLabel,idLabel2;

	//Table
	TableView<Products> tableProducts;
	
	//SQL
	ArrayList<Products> productList = new ArrayList<Products>();
	Connect connect = Connect.getInstance();

	void Initialize() {
		//Menu
		menuBar = new MenuBar();
		menu1 = new Menu();
		menuLogout = new Menu();
		menuItem1 = new MenuItem();
		menuItem2 = new MenuItem();
		menuItem3 = new MenuItem();

		//Region
		space = new Region();
		space2 = new Region();
		
		//Vbox
		vbox = new VBox();
		vb2 = new VBox();
		vb3 = new VBox();
		vb4 = new VBox();
		
		// Hbox
		hbox = new HBox();
		hbox1 = new HBox();
		hbox2 = new HBox();
		hbox3 = new HBox();
		hb = new HBox();
		hb2 = new HBox();
		
		//Button
		insertBtn = new Button();
		removeBtn = new Button();
		updateBtn = new Button();

		//Label
		manageLabel = new Label();
		idLabel = new Label();
		nameLabel = new Label();
		descLabel = new Label();
		priceLabel = new Label();
		idLabel2 = new Label();
		
		// Obeervable List
//		productsList = FXCollections.observableArrayList();

		//Table
		tableProducts = new TableView<Products>();

		//Combo box
		productId = new ComboBox<>();
		
		//Alert
		alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error");
		alert.setContentText("Please fill all the field");

		// Spinner
		price = new Spinner<>();
		priceSpinnerFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(10000,10000000,10000,1);
		
		// Text Field & Area
		nameField = new TextField();
		descArea = new TextArea();
		
		//Border
		bp = new BorderPane();
		
		//Scene
		manageScene = new Scene(bp,800,600);
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
		
		//BUtton
		insertBtn.setText("Insert Juice");
		updateBtn.setText("Update Juice");
		removeBtn.setText("Remove Juice");

		// Label
		manageLabel.setText("Manage Products");
		idLabel.setText("Product ID");
		idLabel2.setText("to delete/remove : ");
		priceLabel.setText("Price:");
		nameLabel.setText("Product Name:");
		descLabel.setText("Product Description:");
		
		// Text Field & Area
		nameField.setPromptText("Insert product name to be created");
		descArea.setPromptText("Insert the new product text description, min 10 & max 100");
		//Edit Text
		manageLabel.setFont(Font.font(null,FontWeight.BOLD,15));

		//Spinner
		price.setValueFactory(priceSpinnerFactory);
		
		
		hb2.getChildren().addAll(space,tableProducts,space2);
		
		//Vbox
		vbox.getChildren().addAll(manageLabel,hb2);
		vb2.getChildren().addAll(insertBtn,updateBtn,removeBtn);
		vb4.getChildren().addAll(idLabel,idLabel2);
		

		//Hbox
		hbox.getChildren().addAll(vb4,productId);
		hbox1.getChildren().addAll(priceLabel,price);
		hbox2.getChildren().addAll(nameLabel,nameField);
		hbox3.getChildren().addAll(descLabel,descArea);
		
		hb.getChildren().addAll(this,vb2);
		
		//Grid
		this.add(hbox, 0, 0);
		this.add(hbox1, 0, 1);
		this.add(hbox2, 0, 2);
		this.add(hbox3, 0, 3);
		
		vb3.getChildren().addAll(vbox,hb);
	}

	void AddComponents() {
		bp.setTop(menuBar);
		bp.setCenter(vb3);
		
	}

	void CreateTable() {
		// Product Table
		TableColumn<Products, String> idCol = new TableColumn<Products, String>("Juice ID");
		  idCol.setCellValueFactory(new PropertyValueFactory<Products, String>("juiceID"));
		  idCol.setMinWidth(100);
		  
		  TableColumn<Products, String> nameCol = new TableColumn<Products, String>("Juice Name");
		  nameCol.setCellValueFactory(new PropertyValueFactory<Products, String>("juiceName"));
		  nameCol.setMinWidth(150);
		  
		  TableColumn<Products, Integer> priceCol = new TableColumn<Products, Integer>("Price");
		  priceCol.setCellValueFactory(new PropertyValueFactory<Products, Integer>("juicePrice"));
		  priceCol.setMinWidth(10);
		  
		  TableColumn<Products, String> descCol = new TableColumn<Products, String>("Juice Description");
		  descCol.setCellValueFactory(new PropertyValueFactory<Products, String>("juiceDescription"));
		  descCol.setMinWidth(200);
		  
		  tableProducts.getColumns().addAll(idCol,nameCol,priceCol,descCol);
		  
		  tableProducts.setItems(productsList);
		  
	}

	void ArrangeComponents() {
		this.setAlignment(Pos.CENTER);
		vbox.setAlignment(Pos.CENTER);
		this.setVgap(20);
		vbox.setSpacing(20);
		
		//Hbox
		hbox.setSpacing(68);
		hbox1.setSpacing(140);
		hbox2.setSpacing(90);
		hbox3.setSpacing(60);
		hb.setSpacing(10);
		hb.setAlignment(Pos.CENTER);
		hb2.setAlignment(Pos.CENTER);
		
		vb2.setAlignment(Pos.CENTER);
		vb2.setSpacing(20);
		vb3.setSpacing(10);
		
//		vbox.setPadding(new Insets(0,0,20,0));
		vb3.setPadding(new Insets(0,0,20,0));
		
		//Text Field
		nameField.setPrefWidth(300);
		descArea.setPrefWidth(300);
		
		// BUtton
		insertBtn.setPrefSize(100, 50);
		updateBtn.setPrefSize(100, 50);
		removeBtn.setPrefSize(100, 50);
	}


	void SetEvent() {
		menuItem1.setOnAction(this);
		menuItem3.setOnAction(this);
		insertBtn.setOnAction(this);
		removeBtn.setOnAction(this);
		updateBtn.setOnAction(this);
	}
	
	private void getData(){
		productList.clear();
		
		String query = "SELECT * FROM msjuice";
		connect.rs = connect.executeQuery(query);
		
		try {
			while (connect.rs.next()) {
				String id = connect.rs.getString("JuiceID");
				String name = connect.rs.getString("JuiceName");
				int price = connect.rs.getInt("Price");
				String desc = connect.rs.getString("JuiceDescription");
				
				Products product = new Products(id, name, price, desc);
				productList.add(product);
						
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void refreshTable() {
		getData();
		ObservableList<Products> productObj = FXCollections.observableArrayList(productList);
		tableProducts.setItems(productObj);
	}
	public ManageProducts(Stage mainStage) {
		Initialize();
		Components();
		ArrangeComponents();
		AddComponents();
		CreateTable();
		SetEvent();
		refreshTable();
		mainStage.setScene(manageScene);
		this.mainStage = mainStage;
	}

	public void show() {
		mainStage.show();
	}

	@Override
	public void handle(ActionEvent e) {
		
		
		if (e.getSource() == menuItem1) {
			AdminViewTrans view = new AdminViewTrans(mainStage);
			view.show();
		}else if (e.getSource() == menuItem3) {
			LoginGrid login = new LoginGrid(mainStage);
			login.show();
			
		}else if (e.getSource() == insertBtn) {
			if (nameField.getText().isEmpty() || descArea.getText().isEmpty() || descArea.getText().length() < 10 || descArea.getText().length() > 100  ) {
				alert.show();
				 
			}else {
				String id = String.format("JU%03d", i);
				i++;
				String juiceName = nameField.getText();
				int juicePrice = price.getValue();
				String juiceDescription = descArea.getText();
				
				Products p = new Products(id, juiceName, juicePrice, juiceDescription);
				tableProducts.getItems().add(p);
				
				productId.getItems().add(p.getJuiceID());
				
				nameField.clear();
				descArea.clear();
				
//				System.out.println("Salah insert");
				
			}
		}else if (e.getSource() == removeBtn) {
			if (productId.getValue() == null) {
				alert.show();
			}else {
				 String selectedProductId = productId.getValue();
			     Products selectedProduct = null;

			        // Cari produk yang sesuai dengan juiceId
			        for (Products product : productsList) {
			            if (product.getJuiceID().equals(selectedProductId)) {
			                selectedProduct = product;
			                System.out.println("siuhadu");
			                break;
			            }
			        }

			        if (selectedProduct != null) {
			            tableProducts.getItems().remove(selectedProduct);
			            productsList.remove(selectedProduct); 
			        }
			        
			        productId.getItems().remove(selectedProductId);
	                productId.setValue(null);
	                

	                int maxId = 0;
	                for (Products product : productsList) {
	                    String juiceId = product.getJuiceID();
	                    int idNumber = Integer.parseInt(juiceId.substring(2));
	                    maxId = Math.max(maxId, idNumber);
	                }
	                
	                i = maxId +1;
			}
	    
	
		}else if (e.getSource() == updateBtn) {
			if (productId.getValue() == null) {
				alert.show();
			}else {
				String selectedProductId = productId.getValue();
		        int newPrice = price.getValue();

		        // Cari produk yang sesuai dengan juiceId
		        for (Products product : productsList) {
		            if (product.getJuiceID().equals(selectedProductId)) {
		                // Perbarui harga produk
		                product.setJuicePrice(newPrice);

		                // Perbarui tampilan tabel
		                tableProducts.refresh();
		                break;
		            }
		        }
			}
		}

	}

}
