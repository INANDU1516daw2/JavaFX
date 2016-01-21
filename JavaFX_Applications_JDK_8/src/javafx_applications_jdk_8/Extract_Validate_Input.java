package javafx_applications_jdk_8;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Extract_Validate_Input extends Application {
    
    Stage window;
    Scene scene;
    Button btn;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("Extract and Validate Input");
        
        TextField nameInput = new TextField();
        
        btn = new Button("Click me");
        btn.setOnAction(e -> isInt(nameInput, nameInput.getText()));
        
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20, 20, 20, 20));
        layout.getChildren().addAll(nameInput, btn);
        
        scene = new Scene(layout, 300, 250);
        window.setScene(scene);
        window.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private boolean isInt(TextField input, String message) {
        try{
            int age = Integer.parseInt(message);
            System.out.println("User is: " + age);
            return true;
        }catch(NumberFormatException e){
            System.err.println("Error " + message + " is not a number!");
            return false;
        }
    }
    
}
