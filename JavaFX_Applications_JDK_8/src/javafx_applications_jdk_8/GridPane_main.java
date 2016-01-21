package javafx_applications_jdk_8;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class GridPane_main extends Application {
    
    Stage window;
    
    @Override
    public void start(Stage primaryStage) {
        
       window = primaryStage;
       window.setTitle("JavaFX");
            
       GridPane grid = new GridPane();
       grid.setPadding(new Insets(10,10,10,10));
       grid.setVgap(8); //Vertial spacing
       grid.setHgap(10); //Horizontal spacing
       
       Label nameLabel = new Label("Username:");
       GridPane.setConstraints(nameLabel, 0, 0);
       
       TextField nameInput = new TextField("username");
       GridPane.setConstraints(nameInput, 1, 0);
       
       Label passLabel = new Label("Password:");
       GridPane.setConstraints(passLabel, 0, 1);
       
       TextField passInput = new TextField();
       GridPane.setConstraints(nameInput, 1, 0);
       passInput.setPromptText("password");
       GridPane.setConstraints(passInput, 1, 1);
       
       Button loginButton = new Button("Log in");
       GridPane.setConstraints(loginButton, 1, 2);
       
       grid.getChildren().addAll(nameLabel, nameInput, passLabel, passInput, loginButton);
       
       
       Scene scene = new Scene(grid, 300, 120);
       window.setScene(scene);
       window.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
