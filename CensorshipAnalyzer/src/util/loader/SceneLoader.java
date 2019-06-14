package util.loader;

import main.Main;
import java.io.IOException;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class SceneLoader {

    public static Hashtable<String, Scene> table = new Hashtable<>();

    public static void addThing(String fileName, Scene scene) {
        table.put(fileName, scene);
    }
//
//    public static Scene getScene(String fileName) {
//        return table.getOrDefault(fileName, null);
//    }

    public static Scene getScene(String fileName) {
        try {
            //        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(main.getClass().getResource(fileName));
//        Parent root = loader.load();
            System.out.println("Trying to load the fxml : " + fileName);
            Scenes dummy = new Scenes();
            Parent root = FXMLLoader.load(dummy.getClass().getResource(fileName));
            Scene scene = new Scene(root);
            SceneLoader.addThing(fileName, scene);
//        Main.stage.setScene(scene);
            return scene;
//        Main.stage.show();
        } catch (IOException ex) {
            System.out.println("-->>Inside SceneLoader.loadScene() ... EXCEPTION OCCURED");
            Logger.getLogger(SceneLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static void loadSceneInSameStage(String fileName) {
        try {
            //        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(main.getClass().getResource(fileName));
//        Parent root = loader.load();
            System.out.println("Trying to load the fxml : " + fileName);
            Scenes dummy = new Scenes();
            Parent root = FXMLLoader.load(dummy.getClass().getResource(fileName));
            Scene scene = new Scene(root);//, Main.STAGE_WIDTH, Main.STAGE_HEIGHT);
            SceneLoader.addThing(fileName, scene);
//        Main.stage.setScene(scene);
            
            Main.stage.setScene(scene);
            Main.stage.setHeight(Main.STAGE_HEIGHT);
            Main.stage.setWidth(Main.STAGE_WIDTH);
            Main.stage.setMaximized(true);
            Main.stage.show();

        } catch (IOException ex) {
            System.out.println("-->>Inside SceneLoader.loadScene() ... EXCEPTION OCCURED");
            Logger.getLogger(SceneLoader.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
