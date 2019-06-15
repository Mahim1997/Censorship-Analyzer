/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import java.io.File;
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
    private String fileNameToTest;
    private String urlNameToTest;

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
    @FXML
    private Text text_Hours;
    @FXML
    private JFXToggleButton toggleBtn_periodicCheck;

    private double timeInHours;
    private boolean isPeriodicCheck;
    private boolean isAccepted;
    private boolean isFileCheck;
    private boolean isURLCheck;
    private boolean isCustom;

    @FXML
    private CheckBox checkBx_accept;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Inside TestSitesController ... ");
        setUpTexts();

        this.isPeriodicCheck = false;
        this.timeInHours = 0;
        this.isAccepted = false;
        this.isCustom = false;
        this.isFileCheck = false;
        this.isURLCheck = false;
        this.label_FileName.setEditable(false);
        this.label_URLName.setEditable(false);
    }

    @FXML
    private void goBack(ActionEvent event) {
        SceneLoader.loadSceneInSameStage(Scenes.homeScreenFXML);
    }

    private String retrieveThings() {
        //Error checking as well

        if (this.isCustom == true) {
            try {
                String hours = this.label_hours.getText();
                double hour_double = Double.parseDouble(hours); //TO DO ... CHECK: ONLY KEEP INTEGERS !!!
                this.timeInHours = hour_double;
            } catch (Exception e) {
                Notification.push("ERROR", "Time should be in hours", Notification.FAILURE);
                return "ERROR";
            }
        }

        if (((this.isFileCheck == false) && (this.isURLCheck == false)) || ((this.isFileCheck == true) && (this.isURLCheck == true))) {
            Notification.push("ERROR", "Pick any one of File/URL option", Notification.FAILURE);
            return "ERROR";
        }

        if (this.isFileCheck) {
            this.fileNameToTest = this.label_FileName.getText();
            if (this.fileNameToTest.trim().equals("")) {
                Notification.push("ERROR", "Pick a valid File", Notification.FAILURE);
                return "ERROR";
            }
        } else if (this.isURLCheck) {
            this.urlNameToTest = this.label_URLName.getText();
            if (this.urlNameToTest.trim().equals("")) {
                Notification.push("ERROR", "Pick a valid url", Notification.FAILURE);
                return "ERROR";
            }
        }

        if (this.isAccepted == false) {
            Notification.push("WARNING", "Need to ACCEPT", Notification.WARNING);
            return "ERROR";
        }

        return "SUCCESS";
    }

    @FXML
    private void submitThings(ActionEvent event) {
        if ("".equals(this.testingMode)) {
            Notification.push("Warning", "Should choose one testing type", Notification.WARNING, Pos.BOTTOM_RIGHT);
            return;
        }
        String ret = retrieveThings(); //Retrieve the items
        if (!ret.equals("SUCCESS")) {
            return;
        }

        String str = "Test Type: " + this.testingMode + "\n"
                + "Periodic: " + this.isPeriodicCheck + "\n"
                + "Time (periodic, hrs): " + this.timeInHours + "\n";

        String ss = "";
        if(this.isFileCheck == true){
            ss = "File<" + this.fileNameToTest + ">\n";
        }
        else{
            ss = "URL<" + this.urlNameToTest + ">\n";
        }
        
        String s = str
                + "User name: " + User.name + "\n"
                + "Network Name: " + User.networkName + "\n"
                + "Network Type: " + User.networkType + "\n"
                + ss;
        System.out.println(s);

        Notification.push("Passing Through", str, Notification.SUCCESS, Pos.CENTER);
        
        //Switch Scene
        SceneLoader.loadSceneInSameStage(Scenes.censoredRecordsFXML);
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
        if (this.toggleButton_url.isSelected() == true) {
            //Selected
            this.toggleButton_file.setSelected(false);
            this.isURLCheck = true;
            this.label_FileName.clear();
            this.isFileCheck = false;
            this.urlNameToTest = this.label_URLName.getText();
            this.label_URLName.setEditable(true);
        } else {
            this.label_URLName.clear();
            this.urlNameToTest = "";
            this.isURLCheck = false;
        }
    }

    @FXML
    private void toggleButton_singleFILE(ActionEvent event) {
        if (this.toggleButton_file.isSelected() == true) {
            //Selected
            this.toggleButton_url.setSelected(false);
            this.isFileCheck = true;
            this.isURLCheck = false;
            this.label_URLName.clear();
            this.fileNameToTest = this.label_FileName.getText();
        } else {
            this.label_FileName.clear();
            this.fileNameToTest = "";
            this.isFileCheck = false;
        }
    }

    @FXML
    private void toggleButton_periodicCheck(ActionEvent event) {
        if (this.toggleBtn_periodicCheck.isSelected() == true) {
            //Make all below buttons OPAQUE .setOpacity(1)
            this.checkBox_30Mins.setOpacity(1);
            this.checkBox_1Hour.setOpacity(1);
            this.checkBox_2Hours.setOpacity(1);
            this.checkBox_Custom.setOpacity(1);
            this.label_hours.setOpacity(1);
            this.text_Hours.setOpacity(1);
        } else {
            //Make all below buttons NOT OPAQUE .setOpacity(0)
            this.checkBox_30Mins.setOpacity(0);
            this.checkBox_1Hour.setOpacity(0);
            this.checkBox_2Hours.setOpacity(0);
            this.checkBox_Custom.setOpacity(0);
            this.label_hours.setOpacity(0);
            this.text_Hours.setOpacity(0);
        }
    }

    private void uncheckAll() {
        this.checkBox_1Hour.setSelected(false);
        this.checkBox_2Hours.setSelected(false);
        this.checkBox_30Mins.setSelected(false);
        this.checkBox_Custom.setSelected(false);
    }

    @FXML
    private void checkFor30Mins(ActionEvent event) {
        uncheckAll();
        this.checkBox_30Mins.setSelected(true);
        this.timeInHours = 0.50;
    }

    @FXML
    private void checkFor1Hour(ActionEvent event) {
        uncheckAll();
        this.checkBox_1Hour.setSelected(true);
        this.timeInHours = 1.0;
    }

    @FXML
    private void checkFor2Hours(ActionEvent event) {
        uncheckAll();
        this.checkBox_2Hours.setSelected(true);
        this.timeInHours = 2.0;
    }

    @FXML
    private void checkForCustom(ActionEvent event) {
        uncheckAll();
        this.checkBox_Custom.setSelected(true);
        this.isCustom = true;
    }

    @FXML
    private void checkBox_AcceptConditions(ActionEvent event) {
        if (checkBx_accept.isSelected() == true) {
            this.isAccepted = true;
        } else {
            this.isAccepted = false;
        }
    }

    @FXML
    private void chooseFile(ActionEvent event) {
        FileChooser fc = new FileChooser();
        File file = fc.showOpenDialog(null);
        System.out.println("File is : " + file.getAbsolutePath());
        this.label_FileName.setText(file.getAbsolutePath());
        this.fileNameToTest = file.getAbsolutePath();
    }

}
