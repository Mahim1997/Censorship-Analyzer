/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hall_management.util;

import hall_management.util.Interface.Controller;
import hall_management.config.Config;
import hall_management.ui.startPage.Main;
import java.util.Hashtable;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 *
 * @author suban
 */
public class SceneLoader {

    private static String current_ui = null;

    private static Hashtable<String, SceneInfo> hashtable = new Hashtable<>();
    public static Scene Scene_Student_Window = null;

    public static Hashtable<String, Stage> hashTableOfNewWindows = new Hashtable<>();
    
    private static boolean isMaximizedState = false;

    public static String CurrentScene() {
        return current_ui;
    }

    private static boolean addKeyAndValue(String fxmlName, Scene scene, Controller controller) {
        SceneInfo sceneInfo = new SceneInfo(scene, controller);
        hashtable.put(fxmlName, sceneInfo);

        System.out.println("Scene added :: " + hashtable.get(fxmlName).getFxmlName() + " Total :: " + hashtable.size());
        return true;
    }

    private static boolean removeScene(String fxmlName)//, Scene scene, Controller controller
    {
//        SceneInfo sceneInfo = new SceneInfo(scene, controller);
        System.out.println("Inside SceneLoader.removeScene() ==>> Scene removed :: " + hashtable.get(fxmlName).getController() + " Total :: " + (hashtable.size() - 1));
        return hashtable.remove(fxmlName) != null;
    }

    public static Controller getController(String fxmlName) {
        return hashtable.get(fxmlName).getController();
    }

    public static Scene getScene(String fxmlName) {
        return hashtable.get(fxmlName).getScene();
    }

    public static void loadSceneInADifferentWindow(Scene scene) {
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public static void loadSceneInADifferentWindow(String fxmlName, Object object) {
        Stage stage = new Stage();
        Scene scene = null;
        Parent root = null;
        FXMLLoader loader = new FXMLLoader();
        Controller controller = null;
        System.out.println("===>>Inside SceneLoader .. loadScene() Trying to Load  " + fxmlName);
        try {
            loader.setLocation(object.getClass().getResource(fxmlName));
            root = loader.load();
            controller = loader.getController();
        } catch (Exception ex2) {
            ex2.printStackTrace();
        }
        SceneLoader.addKeyAndValueOfNewWindow(fxmlName, stage);
//        SceneLoader.addKeyAndValue(fxmlName, scene, controller);
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
//        return controller;
//        closeButton.setOnAction((event) -> {
//            stage.close();
//        });
    }
    public static void closeNewWindow(String fxmlName){
        Stage stage = getStageOfNewWindow(fxmlName);
        if(stage != null){
            stage.close();
        }
    }
    public static Stage getStageOfNewWindow(String fxmlName){
        return hashTableOfNewWindows.get(fxmlName);
    }
    private static void addKeyAndValueOfNewWindow(String fxmlName, Stage stage){
        hashTableOfNewWindows.put(fxmlName, stage);
    }
    public static void loadScene(Scene scene) {
        isMaximizedState = Main.primaryStage.isMaximized();
        Main.primaryStage.setScene(scene);
        if (Config.isAlwaysMaximizedScreen) {
            Main.primaryStage.setMaximized(false);
            Main.primaryStage.setMaximized(true);
        }
    }

    public static Controller loadScene(String fxmlName, Object object) {

        isMaximizedState = Main.primaryStage.isMaximized();

        Parent root = null;
        FXMLLoader loader = new FXMLLoader();
        Controller controller = null;
        System.out.println("===>>Inside SceneLoader .. loadScene() Trying to Load  " + fxmlName);
        try {
            loader.setLocation(object.getClass().getResource(fxmlName));
            root = loader.load();
            controller = loader.getController();
        } catch (Exception ex2) {
            ex2.printStackTrace();
        }

        Scene scene = new Scene(root);
        SceneLoader.addKeyAndValue(fxmlName, scene, controller);
        Main.primaryStage.setScene(scene);
//        Main.primaryStage.setMaximized(isMaximizedState);

        if (Config.isAlwaysMaximizedScreen) {
            Main.primaryStage.setMaximized(false);
            Main.primaryStage.setMaximized(true);
        }

//        Main.primaryStage.setTitle(fxmlName);
        current_ui = fxmlName;

        return controller;
    }

    public static void closeScene(String fxmlName) {

        System.out.println("===>>Inside SceneLoader .. closeScene() Trying to Close  " + fxmlName);

        removeScene(fxmlName);
        current_ui = null;
    }

    public static void loadPreviousScene(String fxmlName, Object classRef) {
        if (!hashtable.containsKey(fxmlName)) {
            System.out.println("Loading new one instead of previous one");
            loadScene(fxmlName, classRef);
            return;
        }

        Main.primaryStage.setScene(getScene(fxmlName));
        if (Config.isAlwaysMaximizedScreen) {
            Main.primaryStage.setMaximized(false);
            Main.primaryStage.setMaximized(true);
        }
        current_ui = fxmlName;
    }
}
