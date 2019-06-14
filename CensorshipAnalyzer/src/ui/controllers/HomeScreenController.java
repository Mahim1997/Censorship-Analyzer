/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import util.loader.SceneLoader;
import util.loader.Scenes;
/**
 * FXML Controller class
 *
 * @author esfs
 */
public class HomeScreenController implements Initializable {

 
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Label label_username;
 
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        System.out.println("-->>>Controller initialise HomeScreenController .... ");
//        setUpTexts();

    }

    private void setUpTexts() {
        
    }

    @FXML
    private void addProfilePicture(ActionEvent event) {
    }

    @FXML
    private void goHome(ActionEvent event) {
    }

    @FXML
    private void testSites(ActionEvent event) {
        System.out.println(" ---->>>>>> TEST SITES Screen loading....");
        
//        System.out.println("javafx.version: " + System.getProperty("javafx.version"));
//        System.out.println("java version: "+System.getProperty("java.version"));
        
        SceneLoader.loadSceneInSameStage(Scenes.testSitesScene);
 
    }

    @FXML
    private void seeReports(ActionEvent event) {
    }

    @FXML
    private void miscallenousTasks(ActionEvent event) {
    }

    @FXML
    private void periodicUpdates(ActionEvent event) {
    }

    @FXML
    private void seeComparisons(ActionEvent event) {
    }

}
