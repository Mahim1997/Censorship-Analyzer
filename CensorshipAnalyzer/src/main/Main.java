package main;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import networking.JavaUDPServerClient;
import util.loader.SceneLoader;
import ui.model.User;
import util.loader.Scenes;

public class Main extends Application {
    public static double STAGE_WIDTH;
    public static double STAGE_HEIGHT;
    
    public static Stage stage;

    public static int USER_DEBUG = 1;
    public static String TITLE = "Censorship Analysis Tool";

    @Override
    public void start(Stage primaryStage) {
        User.initiateUser();
        Main.stage = primaryStage;
        Main.stage.setTitle(Main.TITLE);

        System.out.println("--->>> In Main.start() ... TRYING to load scene ..... ");
//
        STAGE_WIDTH = stage.getMaxWidth();
        STAGE_HEIGHT = stage.getMaxHeight();
        SceneLoader.loadSceneInSameStage(Scenes.homeScreenFXML);
//        SceneLoader.loadSceneInSameStage(Scenes.testSitesScene);
//        SceneLoader.loadSceneInSameStage(Scenes.dnsRecords);

        stage.setOnCloseRequest((WindowEvent e) -> {
            Platform.exit();
            System.exit(0);
        });
        
    }

    public static void main(String[] args) {
//        testServerPython();
        launch(args);
    }
    
    private static void testServerPython()
    {
        JavaUDPServerClient.sendCommandToPython("Hello Python");
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
