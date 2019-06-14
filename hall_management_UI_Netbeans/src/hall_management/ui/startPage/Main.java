package hall_management.ui.startPage;

import hall_management.util.SceneLoader;
import hall_management.config.DEBUG;
import javafx.application.Application;
import javafx.stage.Stage;
import hall_management.ui.login.LoginController;
import hall_management.ui.pushNotification.Notification;
import hall_management.util.Interface.Controller;
import hall_management.util.Interface.Scenes;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.stage.Modality;

public class Main extends Application {

    public static String studentID;
    public static String teacherID;
    public static String staffID;
    public static String adminID;
    public static String guestID;

    public static Stage primaryStage;

    @Override
    public void start(Stage stage) {
        Main.primaryStage = stage;
//        primaryStage.setHeight(height);
//        primaryStage.setWidth(width);
//        SceneLoader.loadScene(SceneLoader.login_ui, this);

//        primaryStage.setOnCloseRequest(((event) -> {

//            Platform.runLater(()->{
//                Notification.push("Thanks", "Credits:\n1.Subangkar Karmaker Shanto\n2.Mahim Mahbub\n", Notification.INFORM, Pos.CENTER);
//            });
//            Platform.runLater(()->{
//                long stattTime = System.currentTimeMillis();
//                while(System.currentTimeMillis()-stattTime < 6000);
//                stage.close();
//            });

//            Main.showCreditStage();
//            SceneLoader.loadSceneInADifferentWindow(Scenes.credits, this);
//            Stage stage2 = SceneLoader.getStageOfNewWindow(Scenes.credits);
//            stage2.setMaximized(true);
//            stage.close();
//        }));
        stage.setTitle("HALL MANAGEMENT SYSTEM");

        if (DEBUG.isDEBUG_ON) {
            System.out.println(DEBUG.isDEBUG_ON);
            LoginController loginController = (LoginController) SceneLoader.loadScene(Scenes.loginPage_ui, this);
            loginController.setType(DEBUG.DEBUG_LOGIN_TYPE());

            stage.show();
            loginController.submitUserName();
            return;
        }

        SceneLoader.loadScene(Scenes.startPage_ui, this);

//        primaryStage.setOnCloseRequest((event) -> {
//            System.exit(69);
//        });

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static void showCreditStage() {
        Parent root = null;

        Stage newStage = new Stage();
        newStage.initModality(Modality.APPLICATION_MODAL);

        FXMLLoader loader = new FXMLLoader();
        Controller controller = null;
        System.out.println("===>>Inside Main Stage.. loadScene() Trying to Load  " + Scenes.credits);
        try {
            loader.setLocation(new Main().getClass().getResource(Scenes.credits));
            root = loader.load();
            controller = loader.getController();
        } catch (Exception ex2) {
            ex2.printStackTrace();
        }

        newStage.setTitle("CREDITS");
        newStage.setFullScreen(true);
        
        newStage.showAndWait();
    }

}
