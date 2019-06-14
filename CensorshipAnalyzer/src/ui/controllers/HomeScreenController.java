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
 
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        System.out.println("-->>>Controller initialise HomeScreenController .... ");
//        setUpTexts();
        loadVBoxLeft();
    }

    private void setUpTexts() {
        
    }

    private void loadVBoxLeft() {
        System.out.println("Loading VBOX Left ... ");
        VBox root = null;
        try{
            root = FXMLLoader.load(getClass().getResource(Scenes.vBoxFXML));
        }catch(Exception e){
            System.out.println("--->>>EXCEPTION In LOADING V BOX left ... ");
            e.printStackTrace();
        }
        anchorPane.getChildren().add(root);
        root.setMaxHeight(anchorPane.getMaxHeight());
        root.setPrefHeight(anchorPane.getMaxHeight());
    }

}
