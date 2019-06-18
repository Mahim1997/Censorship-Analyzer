package ui.controllers;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Tab;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import ui.model.User;
import ui.sounds.Notification;
import util.loader.SceneLoader;
import util.loader.Scenes;

public class TestSitesController implements Initializable {

    @FXML
    private Text text_Mode;
    @FXML
    private CheckBox checkBox_1Hour;
    @FXML
    private CheckBox checkBox_2Hours;
    @FXML
    private CheckBox checkBox_Custom;
    @FXML
    private HBox hbox_File;
    @FXML
    private CheckBox checkBox_30Mins;
    @FXML
    private JFXTextField label_hours;
    @FXML
    private Text text_Hours;
    @FXML
    private JFXToggleButton toggleBtn_periodicCheck;
    @FXML
    private CheckBox checkBx_accept;

    private double timeInHours;
    private boolean isPeriodicCheck;
    private boolean isAccepted;
    private boolean isFileCheck;
    private boolean isURLCheck;
    private boolean isCustom;

    private String testingMode = "";
    private String fileNameToTest;
    private String urlNameToTest;

    @FXML
    private Text text_testingMode;
    @FXML
    private JFXTextField textField_url;
    @FXML
    private JFXTextField textField_fileName;
    @FXML
    private Tab tab_url;
    @FXML
    private Tab tab_file;

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
    }

    @FXML
    private void goBack(ActionEvent event) {
        SceneLoader.loadSceneInSameStage(Scenes.homeScreenFXML);
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
        this.text_Mode.setText(User.modeOfAccess);
        this.text_testingMode.setText(this.testingMode);
    }

    private void setTestingMode(String tM) {
        this.testingMode = tM;
        this.text_testingMode.setText(this.testingMode);
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
        this.textField_fileName.setText(file.getAbsolutePath());
        this.fileNameToTest = file.getAbsolutePath();
    }

    private String retrieveThings() {
        //Error checking as well
        if ("".equals(this.testingMode)) {  //Should have a valid TESTING MODE
            Notification.push("Warning", "Should choose one testing type", Notification.WARNING, Pos.BOTTOM_RIGHT);
            return "ERROR";
        }
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
            this.fileNameToTest = this.textField_fileName.getText();
            if (this.fileNameToTest.trim().equals("")) {
                Notification.push("ERROR", "Pick a valid File", Notification.FAILURE);
                return "ERROR";
            }
        } else if (this.isURLCheck) {
            this.urlNameToTest = this.textField_url.getText();
            if (this.urlNameToTest.trim().equals("")) {
                Notification.push("ERROR", "Pick a valid url", Notification.FAILURE);
                return "ERROR";
            }
        }

        if ((this.isAccepted == false) && (this.isFileCheck == true)) { //Should 'ACCEPT' for file_check
            Notification.push("WARNING", "Need to ACCEPT", Notification.WARNING);
            return "ERROR";
        }

        return "SUCCESS";
    }

    @FXML
    private void submitURL(ActionEvent event) {
        if ("".equals(this.testingMode)) {  //Should have a valid TESTING MODE
            Notification.push("Warning", "Should choose one testing type", Notification.WARNING, Pos.BOTTOM_RIGHT);
            return;
        }

        String str = "Test Type: " + this.testingMode + "\n"
                + "Periodic: " + this.isPeriodicCheck + "\n"
                + "Time (periodic, hrs): " + this.timeInHours + "\n";
        String ss = "URL<" + this.urlNameToTest + ">\n";
        String s = str
                + "User name: " + User.name + "\n"
                + "Network Name: " + User.networkName + "\n"
                + "Network Type: " + User.networkType + "\n"
                + ss;
        System.out.println(s);

        //Form a new command
        //Send to Server_UDP
        Notification.push("Passing Through for single URL", str, Notification.SUCCESS, Pos.CENTER);

        //Switch Scene
        SceneLoader.loadSceneInSameStage(Scenes.censoredRecordsFXML);
    }

    @FXML
    private void submitFile(ActionEvent event) {
        String ret = retrieveThings();
        if (ret.equals("SUCCESS") == false) {
            return;
        }

        String str = "Test Type: " + this.testingMode + "\n"
                + "Periodic: " + this.isPeriodicCheck + "\n"
                + "Time (periodic, hrs): " + this.timeInHours + "\n";
        String ss = "File<" + this.fileNameToTest + ">\n";
        String s = str
                + "User name: " + User.name + "\n"
                + "Network Name: " + User.networkName + "\n"
                + "Network Type: " + User.networkType + "\n"
                + ss;
        System.out.println(s);

        //Form a new command
        //Send to Server_UDP
        Notification.push("Passing Through for single URL", str, Notification.SUCCESS, Pos.CENTER);

        //Switch Scene
        SceneLoader.loadSceneInSameStage(Scenes.censoredRecordsFXML);

    }

    @FXML
    private void selectURL_Tab(Event event) {
        this.isURLCheck = true;
        this.isFileCheck = false;
    }

    @FXML
    private void selectFileTab(Event event) {
        this.isFileCheck = true;
        this.isURLCheck = false;
    }

    @FXML
    private void viewFile(ActionEvent event) {
        Stage stage = new Stage();
        Scene scene = null;
        //Load from fxml [TO Do]
    }

}

/*
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
        
        
        //Form a new command
        //Send to Server_UDP

        Notification.push("Passing Through", str, Notification.SUCCESS, Pos.CENTER);
        
        //Switch Scene
        SceneLoader.loadSceneInSameStage(Scenes.censoredRecordsFXML);
 */
