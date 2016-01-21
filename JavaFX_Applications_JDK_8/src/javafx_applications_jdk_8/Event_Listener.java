package javafx_applications_jdk_8;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Event_Listener extends Application {
    
    Stage window;
    Scene scene;
    Button btn;
    
    @Override
    public void start(Stage primaryStage) {
        
        window = primaryStage;
        
        //DropDownList
        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        
        //getItems returns the Observable object wich u can add items to
        choiceBox.getItems().add("Apples");
        choiceBox.getItems().add("Banana");
        choiceBox.getItems().addAll("Coconut", "Pineable");
        //Set default value
        choiceBox.setValue("Apples");
        
        //Listen for selection changes
        choiceBox.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
            System.out.println("newValue: "+newValue+"  oldValue: "+oldValue); 
        });
    
        //Layout
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20, 20, 20, 20));
        layout.getChildren().addAll(choiceBox);
        
        scene = new Scene(layout, 300, 200);
        window.setScene(scene);
        window.show();
    }

    
    public static void main(String[] args) {
        launch(args);
    }
    
}
