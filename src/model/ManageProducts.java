package model;



import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class ManageProducts extends GridPane implements EventHandler<ActionEvent> {
	//Menu
	MenuBar menuBar;
	Menu menu1,menuLogout;
	MenuItem menuItem1;
	MenuItem menuItem2,menuItem3;

	//Vbox
	VBox vbox,vb2,vb3;
	
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


	//Label
	Label manageLabel,idLabel,nameLabel,priceLabel,descLabel;

	//Table
	TableView<Products> tableProducts;

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
		
		

		//Table
		tableProducts = new TableView<Products>();

		//Combo box
		productId = new ComboBox<>();

		// Spinner
		price = new Spinner<>();
		priceSpinnerFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,1000000000,10000,1);
		
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
		idLabel.setText("Product ID to delete/remove");
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
		
		//Hbox
		hbox.getChildren().addAll(idLabel,productId);
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
		TableColumn<Products, String> jIdCol = new TableColumn<>("Juice ID");
		jIdCol.setCellValueFactory(new PropertyValueFactory<Products, String>("jIdCol"));
		jIdCol.setMinWidth(manageScene.getWidth()/8);

		TableColumn<Products, String> jName = new TableColumn<>("Juice Name");
		jName.setCellValueFactory(new PropertyValueFactory<Products, String>("jNameCol"));
		jName.setMinWidth(manageScene.getWidth()/8);
		TableColumn<Products, Integer> priceCol = new TableColumn<>("Price");
		priceCol.setCellValueFactory(new PropertyValueFactory<Products, Integer>("priceCol"));
		priceCol.setMinWidth(manageScene.getWidth()/8);

		TableColumn<Products, String> jDescCol = new TableColumn<>("Juice Description");
		jDescCol.setCellValueFactory(new PropertyValueFactory<Products, String>("jDescCol"));
		jDescCol.setMinWidth(manageScene.getWidth()/4);
		tableProducts.getColumns().addAll(jIdCol,jName,priceCol,jDescCol);
	}

	void ArrangeComponents() {
		this.setAlignment(Pos.CENTER);
		vbox.setAlignment(Pos.CENTER);
		this.setVgap(20);
		vbox.setSpacing(20);
		
		//Hbox
		hbox.setSpacing(20);
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
	}
	public ManageProducts(Stage mainStage) {
		Initialize();
		Components();
		ArrangeComponents();
		AddComponents();
		CreateTable();
		SetEvent();
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
		}

	}

}
