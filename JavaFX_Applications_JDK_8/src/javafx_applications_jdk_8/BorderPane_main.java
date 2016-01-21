package javafx_applications_jdk_8;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BorderPane_main extends Application {

    Stage window;
    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        
        window = primaryStage;
        window.setTitle("BorderPane_main");
        
        HBox topMenu = new HBox();
        Button btnA = new Button("File");
        Button btnB = new Button("Edit");
        Button btnC = new Button("View");
        topMenu.getChildren().addAll(btnA, btnB, btnC);
        
        VBox leftMenu = new VBox();
        Button btnD = new Button("D");
        Button btnE = new Button("E");
        Button btnF = new Button("F");
        leftMenu.getChildren().addAll(btnD, btnE, btnF);
        
        BorderPane borderpane = new BorderPane();
        borderpane.setTop(topMenu);
        borderpane.setLeft(leftMenu);
        
        Scene scene = new Scene(borderpane, 300, 250);
        window.setScene(scene);
        window.show();
        
    }
    
    
    
}
