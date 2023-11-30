package model;


import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AddItem extends GridPane {
	
	// Stage
	private Stage mainStage;
	
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
		
		// Scene
		scene = new Scene(bp,600,800);
		
    }
	
    void Components() {
    	//Label
    	juiceLabel.setText("Juice: ");
    	juicePrice.setText("Juice Price: ");
    	juiceDesc.setText("Description: ");
    	juiceQty.setText("Quantity: ");
    	juiceTotPrice.setText("Total Price: ");
    	
    	//Button
    	addBtn.setText("Add Item");
    	
    	//Spinner
    	Qty.setValueFactory(QtySpinnerFactory);
    	
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
    }

    public AddItem(Stage mainStage) {
        Initialize();
        Components();
        ArrangeComponents();
        mainStage.setScene(scene);
        this.mainStage = mainStage;
    }

    public void show() {
        mainStage.show();
    }
}
