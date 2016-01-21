package javafx_applications_jdk_8;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    
    Stage window;
    Scene scene_1, scene_2;
    Button button_1, button_2;
    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
    
        window = primaryStage;
        
        //Button 1
        Label label_1 = new Label("First scene");
        button_1 = new Button("Go to scene_2");
        button_1.setOnAction((ActionEvent e) -> {
          System.out.println("button_1 clicked");
          window.setScene(scene_2);
          window.setTitle("scene 2");
        });
        //e = event
        //button_1.setOnAction(e -> System.out.println("button_1 clicked!"));
        
        //Layout 1 - children r laid out in vertical column
        VBox layout_1 = new VBox(20);
        layout_1.getChildren().addAll(label_1, button_1);
        scene_1 = new Scene(layout_1, 200, 200);
        
        //Button 2
        Label label_2 = new Label("Second scene");
        button_2 = new Button("Go to scene_1");
        button_2.setOnAction((ActionEvent e) -> {
          System.out.println("button_2 clicked");
          window.setScene(scene_1);
        });
        
        //Layout 2
        StackPane layout_2 = new StackPane();
        layout_2.getChildren().add(button_2);
        scene_2 = new Scene(layout_2, 600, 300);
        
        //Default scene
        window.setScene(scene_1);
        window.setTitle("scene 1");
        window.show();
       
    }
    
}
