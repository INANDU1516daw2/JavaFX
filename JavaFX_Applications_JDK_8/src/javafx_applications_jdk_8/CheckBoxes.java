package javafx_applications_jdk_8;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CheckBoxes extends Application {
    
    Stage window;
    Scene scene;
    Button btn;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("CheckBoxes");
        
        //Checkboxes
        CheckBox box1 = new CheckBox("Bacon");
        CheckBox box2 = new CheckBox("Tuna");
        box2.setSelected(true);
        CheckBox box3 = new CheckBox("Jam");
        
        //Buttons
        btn = new Button("Order now");
        btn.setOnAction(e -> handleOptions(box1, box2, box3));
        
        //Layout
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20, 20, 20, 20));
        layout.getChildren().addAll(box1, box2, box3, btn);
        
        scene = new Scene(layout, 300, 200);
        window.setScene(scene);
        window.show();
    }

    private void handleOptions(CheckBox box1, CheckBox box2, CheckBox box3){
        String message = "";
        
        if(box1.isSelected()){
            message += "Bacon";
        }
        if(box2.isSelected()){
            message += "Tuna";
        }
        if(box3.isSelected()){
            message += "Jam";
        }
        
        System.out.println(message);
    }
    
    public static void main(String[] args) {
        launch(args);
    }

}
