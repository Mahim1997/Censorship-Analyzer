/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
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
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        System.out.println("-->>>Controller initialise HomeScreenController .... ");
        setUpTexts();

    }

    private void setUpTexts() {
        //TODO 
        //Setup a server here ... ask python [command] for the things to display
        //When retrieving from server .. display the info
        //Do this setUpTexts() procedure call periodically ... say after every 5 minutes (eg.)
    }

    @FXML
    private void addProfilePicture(ActionEvent event) {
        //To do ... steps 
        //1. Choose an image file [Implement filechooser here]
        //2. Make a copy of the image [Copy/Save the file in this folder/package]
        //3. Load the image by putting in the above imageView
    }

    @FXML
    private void goHome(ActionEvent event) {
        //Load home page
        SceneLoader.loadSceneInSameStage(Scenes.homeScreenFXML);
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
        //TODO
        //Load Scene seeReports ... 
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
