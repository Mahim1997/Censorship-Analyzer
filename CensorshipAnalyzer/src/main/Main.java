package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import util.loader.SceneLoader;
import ui.model.User;
import util.loader.Scenes;

public class Main extends Application {

    public static Stage stage;
    
    public static int USER_DEBUG = 1;
    public static String TITLE = "Censorship Analysis Tool";
 

    @Override
    public void start(Stage primaryStage) {
        User.initiateUser();
        Main.stage = primaryStage;
        Main.stage.setTitle(Main.TITLE);
 
        System.out.println("--->>>TRYING to load scene ..... ");
//            System.out.println("-->>Inside Main.normalStart() .... ");
        Scene loadScene = SceneLoader.loadScene(Scenes.homeScreenFXML, this);
        if (loadScene == null) {
            System.out.println("<><><><><><><><><> Scene get null returned val");
            return ;
        }

        stage.setScene(loadScene);
        stage.setMaximized(true);
        Main.stage.show();

    }

    public static void main(String[] args) {
        launch(args);
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
