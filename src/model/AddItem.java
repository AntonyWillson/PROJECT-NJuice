package model;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class AddItem extends GridPane {
	
    Popup popUp;
    StackPane content;
    Button btn;
    private Stage mainStage;
	
    void Initialize() {
        popUp = new Popup();
        content = new StackPane();
        btn = new Button();
    }
	
    void Components() {
        btn.setText("Cancel");
        content.getChildren().add(btn);
        popUp.getContent().add(content);
    }
	
    void ArrangeComponents() {
        popUp.show(mainStage,300,600);
    }

    public AddItem(Stage mainStage) {
        this.mainStage = mainStage;
        Initialize();
        Components();
        ArrangeComponents();
    }

    public void show() {
        mainStage.show();
    }
}
