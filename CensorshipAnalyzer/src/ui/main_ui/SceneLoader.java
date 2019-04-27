package ui.main_ui;

import java.util.Hashtable;
import javafx.scene.Scene;

public class SceneLoader {

    public static String homeScreenFXML = "home-screen.fxml";

    public static Hashtable<String, Scene> table = new Hashtable<>();

    public static void addThing(String fileName, Scene scene) {
        table.put(fileName, scene);
    }

    public static Scene getScene(String fileName) {
        return table.getOrDefault(fileName, null);
    }
    
    public static void loadScene(String fileName){
        Scene scene = getScene(fileName);
        Main.stage.setScene(scene);
        Main.stage.show();
    }
    

}
