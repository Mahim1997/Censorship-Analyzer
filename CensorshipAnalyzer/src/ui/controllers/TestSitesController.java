/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import ui.model.User;
import ui.sounds.Notification;
import util.loader.SceneLoader;
import util.loader.Scenes;

/**
 *
 * @author viper
 */
public class TestSitesController implements Initializable {

    @FXML
    private Text text_TestingMode;
    @FXML
    private Text text_Mode;
    @FXML
    private JFXTextField label_URLName;
    @FXML
    private CheckBox checkBox_1Hour;
    @FXML
    private CheckBox checkBox_2Hours;
    @FXML
    private CheckBox checkBox_Custom;
    @FXML
    private HBox hbox_File;

    private String testingMode = "";
    @FXML
    private JFXToggleButton toggleButton_url;
    @FXML
    private JFXToggleButton toggleButton_file;
    @FXML
    private CheckBox checkBox_30Mins;
    @FXML
    private JFXTextField label_hours;
    @FXML
    private JFXTextField label_FileName;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Inside TestSitesController ... ");
        setUpTexts();
        setupFileChooser();
    }

    private void setupFileChooser() {
        FileChooser fC = new FileChooser();

    }

    @FXML
    private void goBack(ActionEvent event) {
        SceneLoader.loadSceneInSameStage(Scenes.homeScreenFXML);
    }

    @FXML
    private void submitThings(ActionEvent event) {
        if ("".equals(this.testingMode)) {
            Notification.push("Warning", "Should choose one testing type", Notification.WARNING, Pos.BOTTOM_RIGHT);
        }
    }

    @FXML
    private void dnsTesting(ActionEvent event) {
        setTestingMode("DNS");
    }

    @FXML
    private void tcpTesting(ActionEvent event) {
        setTestingMode("TCP");
    }

    @FXML
    private void httpTesting(ActionEvent event) {
        setTestingMode("HTTP");
    }

    @FXML
    private void allTesting(ActionEvent event) {
        setTestingMode("All");
    }

    private void setUpTexts() {
        String newText = this.text_Mode.getText();
        newText += User.modeOfAccess;
        this.text_Mode.setText(newText);

        newText = this.text_TestingMode.getText();
        newText += this.testingMode;
        this.text_TestingMode.setText(newText);
    }

    private void setTestingMode(String tM) {
        String newText = "Testing Mode: ";
        newText += tM;
        this.text_TestingMode.setText(newText);
        this.testingMode = tM;
    }

    @FXML
    private void toggleButton_singleURL(ActionEvent event) {
    }

    @FXML
    private void toggleButton_singleFILE(ActionEvent event) {
    }

    @FXML
    private void toggleButton_periodicCheck(ActionEvent event) {
    }

    @FXML
    private void checkFor30Mins(ActionEvent event) {
    }

    @FXML
    private void checkFor1Hour(ActionEvent event) {
    }

    @FXML
    private void checkFor2Hours(ActionEvent event) {
    }

    @FXML
    private void checkForCustom(ActionEvent event) {
    }

    @FXML
    private void checkBox_AcceptConditions(ActionEvent event) {
    }

    @FXML
    private void chooseFile(ActionEvent event) {
    }

}
