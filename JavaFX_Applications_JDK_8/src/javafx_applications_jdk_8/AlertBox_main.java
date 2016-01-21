package javafx_applications_jdk_8;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class AlertBox_main extends Application {
    
    Stage window;
    Button btn;
    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        
        window = primaryStage;
        window.setTitle("Zoo");
        
        window.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
        });
        
        btn = new Button("click me");
        
//        btn.setOnAction(e -> AlertBox.display("alert box","hi there!"));
        
        btn.setOnAction(e -> {
           boolean result = ConfirmBox.display("ConirmBox", "Please confirm");
            System.out.println(result);
        });
        
        StackPane layout = new StackPane();
        
        layout.getChildren().add(btn);
        
        Scene scene = new Scene(layout, 300, 250);
        window.setScene(scene);
        window.show();
    }

    private void closeProgram() {
        Boolean answer = ConfirmBox.display("CloseWindow", "Sure u w exit ?");
        if(answer){
            System.out.println("closing program");
            window.close();
        }else{
            System.out.println("close program declined");
        }
    }

}
