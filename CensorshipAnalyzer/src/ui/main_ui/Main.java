package ui.main_ui;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    public static Stage stage;
    public static int DEBUG_MODE = 0;
    public static int USER_DEBUG = 1;
    public static String TITLE = "Censorship Analysis Tool";

    private static void debug() {
        System.out.println("Inside DEBUG");
        if (Main.DEBUG_MODE == 0) {
            Debug.debug();
        }
    }

    @Override
    public void start(Stage primaryStage) {

        Main.stage = primaryStage;
        Main.stage.setTitle(Main.TITLE);

        if (Main.DEBUG_MODE == 1) {
            Main.debug();
            return;
        }
        System.out.println("-->>Inside Main.normalStart() .... ");
        SceneLoader.loadScene(SceneLoader.homeScreenFXML);

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
