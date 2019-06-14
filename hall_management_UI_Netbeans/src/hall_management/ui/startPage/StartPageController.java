/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hall_management.ui.startPage;

import hall_management.config.DEBUG;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

import hall_management.util.Interface.Type;
import hall_management.util.Interface.Controller;
import hall_management.util.SceneLoader;
import hall_management.ui.login.LoginController;
import hall_management.util.Interface.Scenes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/**
 * FXML Controller class
 *
 * @author suban
 */
public class StartPageController implements Initializable, Controller {

    LoginController login_Controller = null;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Start Page");

    }

    @FXML
    private void exitProg(ActionEvent event) {
//        loadCredits(event);
        Main.primaryStage.close();
//        System.exit(0);
    }

    @FXML
    private void setType_Student(ActionEvent event) {
        login_Controller = (LoginController) SceneLoader.loadScene(Scenes.loginPage_ui, this);
        login_Controller.setType(Type.type_Student);
    }

    @FXML
    private void setType_Teacher(ActionEvent event) {
        login_Controller = (LoginController) SceneLoader.loadScene(Scenes.loginPage_ui, this);
        login_Controller.setType(Type.type_Teacher);
    }

    @FXML
    private void setType_Staff(ActionEvent event) {
        login_Controller = (LoginController) SceneLoader.loadScene(Scenes.loginPage_ui, this);
        login_Controller.setType(Type.type_Staff);
    }

    @FXML
    private void setType_Admin(ActionEvent event) {
        login_Controller = (LoginController) SceneLoader.loadScene(Scenes.loginPage_ui, this);
        login_Controller.setType(Type.type_Admin);
    }

    @FXML
    private void setType_Guest(ActionEvent event) {
        login_Controller = (LoginController) SceneLoader.loadScene(Scenes.loginPage_ui, this);
        login_Controller.setType(Type.type_Guest);
    }

    @FXML
    private void loadCredits(ActionEvent event) {
        SceneLoader.loadSceneInADifferentWindow(Scenes.credits,this);
        SceneLoader.getStageOfNewWindow(Scenes.credits).setMaximized(true);        

        
    }
 
    

}
