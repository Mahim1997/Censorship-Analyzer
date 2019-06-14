/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

/**
 *
 * @author viper
 */
public class TestSitesController {

    @FXML
    private Text text_TestingMode;
    @FXML
    private Text text_Mode;
    @FXML
    private JFXTextField label_URLName;
    @FXML
    private JFXToggleButton toggleButton_singleURL;
    @FXML
    private JFXTextField label_FileName;
    @FXML
    private JFXToggleButton toggleButton_singleFILE;
    @FXML
    private JFXToggleButton toggleButton_periodicCheck;
    @FXML
    private CheckBox checkBox_30mins;
    @FXML
    private CheckBox checkBox_1Hour;
    @FXML
    private CheckBox checkBox_2Hours;
    @FXML
    private CheckBox checkBox_Custom;
    @FXML
    private JFXTextField label_URLName1;
    @FXML
    private CheckBox checkBox_AcceptConditions;
    @FXML
    private HBox hbox_File;

    @FXML
    private void goBack(ActionEvent event) {
    }

    @FXML
    private void submitThings(ActionEvent event) {
    }

    @FXML
    private void dnsTesting(ActionEvent event) {
    }

    @FXML
    private void tcpTesting(ActionEvent event) {
    }

    @FXML
    private void httpTesting(ActionEvent event) {
    }

    @FXML
    private void allTesting(ActionEvent event) {
    }
    
}
