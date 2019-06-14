/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hall_management.util;

import hall_management.util.Interface.Controller;
import javafx.scene.Scene;

/**
 *
 * @author suban
 */
public class SceneInfo {
    public String fxmlName;
    public Scene scene;
    public Controller controller;

    public String getFxmlName() {
        return fxmlName;
    }

    public void setFxmlName(String fxmlName) {
        this.fxmlName = fxmlName;
    }

    public SceneInfo(String fxmlName, Scene scene, Controller controller) {
        this.fxmlName = fxmlName;
        this.scene = scene;
        this.controller = controller;
    }

    public SceneInfo(Scene scene, Controller controller) {
        this.scene = scene;
        this.controller = controller;
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }
    
    
    
}
