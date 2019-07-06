package ui.controllers;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Tab;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import networking.JavaUDPServerClient;
import ui.model.User;
import ui.sounds.Notification;
import util.commands.CommandGenerator;
import util.database.DBHandler;
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
    private String absoluteFilePathNameToTest;
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

    private String fileNameNormalToTest;

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
        this.isAccepted = checkBx_accept.isSelected() == true;
    }

    @FXML
    private void chooseFile(ActionEvent event) {
        FileChooser fc = new FileChooser();
        File file = fc.showOpenDialog(null);
        System.out.println("File is : " + file.getAbsolutePath());
//        this.textField_fileName.setText(file.getName());    //Just the name is to be displayed
        this.textField_fileName.setText(file.getAbsolutePath());    //Just the name is to be displayed

        this.absoluteFilePathNameToTest = file.getAbsolutePath();
        this.fileNameNormalToTest = file.getName();
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
            this.absoluteFilePathNameToTest = this.textField_fileName.getText();
            if (this.absoluteFilePathNameToTest.trim().equals("")) {
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
        try {
            if ("".equals(this.testingMode)) {  //Should have a valid TESTING MODE
                Notification.push("Warning", "Should choose one testing type", Notification.WARNING, Pos.BOTTOM_RIGHT);
                return;
            }
            this.urlNameToTest = this.textField_url.getText();
            //Form a new command
            String command = CommandGenerator.getCommand(urlNameToTest, testingMode, false, "NULL", false, false, 0);

            System.out.println("Command to send \n" + command);

            //Send to Server_UDP   .... done in the controller class
//            JavaUDPServerClient.sendCommandToPython(command);
//        Notification.push("Passing Through for single URL", command, Notification.SUCCESS, Pos.CENTER);
//Switch Scene

            String fileNameFXMLToLoad = Scenes.censoredRecordsWaitingFXML;
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(this.getClass().getResource(fileNameFXMLToLoad));

            Parent root = loader.load();
            
            CensoredRecordController_Waiting controller = (CensoredRecordController_Waiting) loader.getController();
            controller.setUpInitial(false, this.urlNameToTest, command, -1, -1);

            SceneLoader.loadSceneInSameStage(root);

        } catch (IOException ex) {
            System.out.println("--->>EXCEPTION in TestSitesController.java line 272 ... ");
            Logger.getLogger(TestSitesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void submitFile(ActionEvent event) {
        try {
            String ret = retrieveThings();
            if (ret.equals("SUCCESS") == false) {
                return;
            }

            String fileNameFXMLToLoad = Scenes.censoredRecordsWaitingFXML;
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(this.getClass().getResource(fileNameFXMLToLoad));

            Parent root = loader.load();
            CensoredRecordController_Waiting controller = (CensoredRecordController_Waiting) loader.getController();
            int idxCurrentMaxReport = DBHandler.getLatestReportID();
            controller.setUpInitial(true, this.fileNameNormalToTest, this.absoluteFilePathNameToTest, (idxCurrentMaxReport + 1), -1);

            SceneLoader.loadSceneInSameStage(root);

        } catch (IOException ex) {
            System.out.println("\n--->>> EXCEPTION IN TestSitesController.submitFile function() .... ");
        }

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
        try {
            Stage stage = new Stage();
            //Load from fxml [TO Do] In a new Stage
            this.absoluteFilePathNameToTest = this.textField_fileName.getText();
            this.fileNameNormalToTest = trimName(this.absoluteFilePathNameToTest);

            System.out.println("BEFORE .... this.absPathFile = " + this.absoluteFilePathNameToTest + " , this.normalFileName = " + this.fileNameNormalToTest);

            String fileNameFXMLToLoad = Scenes.fileViewFXML;
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(this.getClass().getResource(fileNameFXMLToLoad));
            Parent root = loader.load();

            FileViewerController controller = (FileViewerController) loader.getController();
            controller.setFileName(this.fileNameNormalToTest, this.absoluteFilePathNameToTest);
            controller.showData();

            Scene scene = new Scene(root);//, Main.STAGE_WIDTH, Main.STAGE_HEIGHT);
            stage.setScene(scene);
            stage.setMaximized(false);
            stage.show();
        } catch (IOException ex) {
            Notification.push("Error", "Error in loading file", Notification.FAILURE, Pos.BOTTOM_RIGHT);
        }

    }

    private String trimName(String s) {
        String[] sArr = s.split("/");

        String normalTrimmedFileName = sArr[sArr.length - 1];

//        System.out.println("-->File Name : (In TestSitesController.java) is " + normalTrimmedFileName);
        return normalTrimmedFileName;
    }

}
