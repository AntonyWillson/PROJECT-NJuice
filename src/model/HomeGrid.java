package model;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class HomeGrid extends GridPane {
	private Label cartLabel,descCartLabel;
	
	private VBox vBoxCartLabel;
	
	private HBox hBoxButton;
	
	private Button addButton,deleteButton,checkoutButton;
	

	void initialize() {
		
		// Label
		cartLabel = new Label();
		descCartLabel = new Label();
		
		
		// Vbox
		vBoxCartLabel = new VBox(20);
		
		// Hbox
		hBoxButton = new HBox(20);
		
		// Button
		addButton = new Button();
		deleteButton = new Button();
		checkoutButton = new Button();
		
	}
	
	void components() {
		// Label
		cartLabel.setText("Your Cart");
		descCartLabel.setText("Your cart is empty, try adding items!");
		
		// Edit Text
		cartLabel.setFont(Font.font(null,FontWeight.BOLD,50));
		descCartLabel.setFont(Font.font(null,FontWeight.NORMAL,10));
		
		// Button
		addButton.setText("Add new item to cart");
		deleteButton.setText("Delete Item from cart");
		checkoutButton.setText("checkout");
		
		// VBox
		vBoxCartLabel.getChildren().addAll(cartLabel,descCartLabel);
		
		// HBox
		hBoxButton.getChildren().addAll(addButton,deleteButton,checkoutButton);
		
		// Grid Pane
		this.add(vBoxCartLabel, 0, 0); 
		this.add(hBoxButton, 0, 1); 
		
		
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
	
	public HomeGrid() {
		initialize();
		components();
		arrangeComponents();
	}
}
