package javafx_applications_jdk_8;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DropDownMenu extends Application {
    
    Stage window;
    Scene scene;
    Button btn;
    
    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        window.setTitle("DropDownMenu");
        
        //DropDownList
        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        
        //getItems returns the Observable object wich u can add items to
        choiceBox.getItems().add("Apples");
        choiceBox.getItems().add("Banana");
        choiceBox.getItems().addAll("Coconut", "Pineable");
        
        //Set default value
        choiceBox.setValue("Apples");
        
        //Buttons
        btn = new Button("Order now");
        btn.setOnAction(e -> getChoice(choiceBox));
        
        //Layout
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20, 20, 20, 20));
        layout.getChildren().addAll(choiceBox, btn);
        
        scene = new Scene(layout, 300, 200);
        window.setScene(scene);
        window.show();
    }

    //To get the value of the selected item
    private void getChoice(ChoiceBox<String> choiceBox){
        String food = choiceBox.getValue();
        System.out.println(food);
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
