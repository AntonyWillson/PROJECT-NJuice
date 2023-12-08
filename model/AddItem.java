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
	    getData3(); 
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

     if (selectedJuiceName != null) {
         // Mendapatkan harga jus dari database berdasarkan nama jus yang dipilih
         String query2 = "SELECT Price, JuiceDescription FROM msjuice WHERE JuiceName = ?";
         try {
             connect.pst = connect.con.prepareStatement(query2);
             connect.pst.setString(1, selectedJuiceName);

             connect.rs = connect.pst.executeQuery();

             if (connect.rs.next()) {
                 int juicePriceValue = connect.rs.getInt("Price");
                 String juiceDescrip = connect.rs.getString("JuiceDescription");
                 juicePrice.setText("Juice Price: " + juicePriceValue);

                 juiceDesc.setText(juiceDescrip);
             }
         } catch (SQLException e) {
             e.printStackTrace();
         }
     } else {
         // Jika tidak ada jus yang dipilih, kosongkan label harga
         juicePrice.setText("Juice Price: ");
     }
 }
  
  private void getData3() {
	  int quantity = Qty.getValue();
	    String selectedJuiceName = juiceName.getValue();

	    if (selectedJuiceName != null) {
	        // Mendapatkan harga jus dari database berdasarkan nama jus yang dipilih
	        String query2 = "SELECT Price FROM msjuice WHERE JuiceName = ?";
	        try {
	            connect.pst = connect.con.prepareStatement(query2);
	            connect.pst.setString(1, selectedJuiceName);

	            connect.rs = connect.pst.executeQuery();

	            if (connect.rs.next()) {
	                int juicePriceValue = connect.rs.getInt("Price");

	                // Hitung total harga
	                int totalJuicePrice = juicePriceValue * quantity;

	                // Set label total harga
	                juiceTotPrice.setText("Total Price: " + totalJuicePrice);
	            }
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	    }
  }
  
  
 
 private void refreshTable() {
  getData();
 }

 public AddItem(Stage mainStage, Stage popupStage) {
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
   CustHomeGrid home = new CustHomeGrid(mainStage);
    popupStage.close();
            
  }
  
 }
}