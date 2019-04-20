package ui.main_ui;

import javafx.application.Application;
import javafx.stage.Stage;
import util.Interface.Scenes;
import util.ScenesController.SceneLoader;

public class Main extends Application {

    public static Stage stage;
    public static int DEBUG_MODE = 0;

    private static void debug() {
        System.out.println("Inside DEBUG");
        if (Main.DEBUG_MODE == 0) {
            Debug.debug();
        }
    }

    @Override
    public void start(Stage primaryStage) {

        Main.stage = primaryStage;

        if (Main.DEBUG_MODE == 1) {
            Main.debug();
            return;
        }
        System.out.println("-->>Inside Main.normalStart() .... ");
        SceneLoader.loadScene(Scenes.initial_ui, this);

    }

    public static void main(String[] args) {
        launch(args);
    }

}

class Debug {

    public static void debug() {

    }
}

/*
Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
                Notification.push("HELLO WORLD", "You have clicked button", Notification.SUCCESS, Pos.BOTTOM_RIGHT);
            }
        });
        
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
 */
