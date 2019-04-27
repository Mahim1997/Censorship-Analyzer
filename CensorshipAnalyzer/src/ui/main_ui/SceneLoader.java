package ui.main_ui;

import ui.homeScreen.Main;
import java.io.IOException;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class SceneLoader {

//    public static String homeScreenFXML = "../homeScreen/homeScreenNormalSB.fxml";
    public static String homeScreenFXML = "homeScreenNormalSB.fxml";
    
    public static Hashtable<String, Scene> table = new Hashtable<>();

    public static void addThing(String fileName, Scene scene) {
        table.put(fileName, scene);
    }

    public static Scene getScene(String fileName) {
        return table.getOrDefault(fileName, null);
    }

    /*
           System.out.println("===>>Inside SceneLoader .. loadScene() Trying to Load  " + fxmlName);
        try {
            loader.setLocation(object.getClass().getResource(fxmlName));
            root = loader.load();
            controller = loader.getController();
        } catch (Exception ex2) {
            ex2.printStackTrace();
        }
     */
    public static Scene loadScene(String fileName, Main main) {
        try {
            //        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(main.getClass().getResource(fileName));
//        Parent root = loader.load();
            Parent root = FXMLLoader.load(main.getClass().getResource(fileName));
            Scene scene = new Scene(root);
//        Main.stage.setScene(scene);
            return scene;
//        Main.stage.show();
        } catch (IOException ex) {
            System.out.println("-->>Inside SceneLoader.loadScene() ... ");
            Logger.getLogger(SceneLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
