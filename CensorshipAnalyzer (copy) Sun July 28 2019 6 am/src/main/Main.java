package main;

import debugger.Debugger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import ui.model.Network;
import util.loader.SceneLoader;
import ui.model.User;
import util.database.DBHandler;
import util.loader.Scenes;
import util.pythonCodeExecutorAndNetworkInfo.NetworkInfoObtainer;

public class Main extends Application {

    public static double STAGE_WIDTH;
    public static double STAGE_HEIGHT;

    public static Stage stage;

    public static int USER_DEBUG = 1;
    public static String TITLE = "Censorship Analysis Tool";

    public static boolean DEBUG_MODE = false;
    public static boolean SEND_TO_SERVER = true;

    @Override
    public void start(Stage primaryStage) {
//        User.initiateUser();
        Main.stage = primaryStage;
        Main.stage.setTitle(Main.TITLE);

        System.out.println("--->>> In Main.start() ... TRYING to load scene ..... ");
//
        STAGE_WIDTH = stage.getMaxWidth();
        STAGE_HEIGHT = stage.getMaxHeight();
        
        /*
        try {
            Parent root = FXMLLoader.load(this.getClass().getResource("test.fxml"));
            Scene scene = new Scene(root);
            Main.stage.setScene(scene);
            Main.stage.show();
        } catch (IOException ex) {
            System.out.println("======>>> EXCEPTION WHILE LOADING ");
            ex.printStackTrace();
        }
         */
        SceneLoader.loadSceneInSameStage(Scenes.homeScreenFXML);

        
        
//        SceneLoader.loadSceneInSameStage(Scenes.testSitesScene);
//        SceneLoader.loadSceneInSameStage(Scenes.dnsRecords);
        stage.setOnCloseRequest((WindowEvent e) -> {
            Platform.exit();
            DBHandler.closeConnection();
            System.exit(0);
        });

    }

    public static void main(String[] args) {
        if (Main.DEBUG_MODE == true) {
            Debugger.debugProgram();
        } else {
            NetworkInfoObtainer.extractNetworkInfo();

            User.networkName = Network.org_static;
            User.networkType = NetworkInfoObtainer.getNetworkType(User.networkName);
            User.modeOfAccess = Network.status_static;
            launch(args);
        }

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
