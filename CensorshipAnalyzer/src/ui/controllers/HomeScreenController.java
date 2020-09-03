/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.controllers;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import ui.model.Network;
import util.database.DBHandler;
import util.loader.SceneLoader;
import util.loader.Scenes;
import util.pythonCodeExecutorAndNetworkInfo.NetworkInfoObtainer;

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

    @FXML
    private Label label_asn;
    @FXML
    private Label label_city;
    @FXML
    private Label label_continent;
    @FXML
    private Label label_country;
    @FXML
    private Label label_latitude;
    @FXML
    private Label label_longitude;
    @FXML
    private Label label_org;
    @FXML
    private Label label_postal;
    @FXML
    private Label label_region;
    @FXML
    private Label label_carrier;
    @FXML
    private Label label_status;
    public static boolean IS_START = true;
    @FXML
    private JFXButton btn_refreshInfo;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        System.out.println("-->>>Controller initialise HomeScreenController .... ");

        if (IS_START == true) {
            refreshTexts();
            IS_START = false;
        }
        setUpTexts();
        new Thread(new ThreadRunner(this)).start();

    }

    public List<String> result = new ArrayList<>();

    public void refreshTexts() {
        this.result = NetworkInfoObtainer.extractNetworkInfo();
    }

    public void setUpTexts() {

        if (Network.asn_static.toUpperCase().equals("NA") == false) {
            label_status.setText(Network.status_static);
            label_asn.setText(Network.asn_static);
            label_city.setText(Network.city_static);
            label_continent.setText(Network.continent_static);
            label_country.setText(Network.country_static);
            label_latitude.setText(Network.latitude_static);
            label_longitude.setText(Network.longitude_static);
            label_org.setText(Network.org_static);
            label_postal.setText(Network.postal_static);
            label_region.setText(Network.region_static);
            label_carrier.setText(Network.carrier_static);
        } else {

            label_status.setText("Offline");
            label_asn.setText("NA");
            label_city.setText("NA");
            label_continent.setText("NA");
            label_country.setText("NA");
            label_latitude.setText("NA");
            label_longitude.setText("NA");
            label_org.setText("NA");
            label_postal.setText("NA");
            label_region.setText("NA");
            label_carrier.setText("NA");
        }

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

        SceneLoader.loadSceneInSameStage(Scenes.testSitesScene);

    }

    @FXML
    private void seeReports(ActionEvent event) {
        //TODO
        //Load Scene seeReports ... 
        SceneLoader.loadSceneInSameStage(Scenes.censoredRecordsFXML);
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

    @FXML
    private void refreshInfoHome(ActionEvent event) {
        System.out.println("-->>REFRESHING INFO");
        this.btn_refreshInfo.setOpacity(0);
        this.refreshTexts();
        this.setUpTexts();
        this.btn_refreshInfo.setOpacity(1);
    }

}

class ThreadRunner implements Runnable {

    HomeScreenController controller;

    public long time_now;
    public long time_start;
//    public long time_delta = 5000; //5 seconds
    public long time_delta = 30 * 1000; //30 seconds
    public static boolean IS_FIRST_TIME = true;

    public ThreadRunner(HomeScreenController controller) {
        this.controller = controller;
        this.time_start = System.currentTimeMillis();
    }

    @Override
    public void run() {

        while (true) {
            //what it does ...
            this.time_now = System.currentTimeMillis();
//            System.out.println("===+++===>> this.timenow = " + this.time_now + " , this.startTime = " + this.time_start);
            if ((this.time_now - time_start) >= time_delta) {
                this.time_start = this.time_now;
//                System.out.println("-->>REFRESHING INFO HOMEPAGE CONTROLLER ... ");
                Platform.runLater(() -> {
                    controller.refreshTexts();
                    controller.setUpTexts();
                });
                if (IS_FIRST_TIME == true) {
                    DBHandler.openConnection();
                    DBHandler.formConnection_ID();
                    DBHandler.closeConnection(); // ??
                    IS_FIRST_TIME = false;
                }
            }
        }

    }

}
