/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import ui.model.Network;
import util.loader.SceneLoader;
import util.loader.Scenes;
import util.pythonCodeExecute.PythonCodeExecutor;

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
        setUpTexts();

    }

    private void setUpTexts() {
        //TODO 
        //Setup a server here ... ask python [command] for the things to display
        //When retrieving from server .. display the info
        //Do this setUpTexts() procedure call periodically ... say after every 5 minutes (eg.)
        PythonCodeExecutor pce = new PythonCodeExecutor();
        List<String> result = pce.getOutput("python3.6 isp_info_final.py");
        System.out.println(result);
        if (result.size() == 11) {
            Network.status = result.get(0);
            Network.asn = result.get(1);
            Network.city = result.get(2);
            Network.continent = result.get(3);
            Network.country = result.get(4);
            Network.latitude = result.get(5);
            Network.longitude = result.get(6);
            Network.org = result.get(7);
            Network.postal = result.get(8);
            Network.region = result.get(9);
            Network.carrier = result.get(10);
            Network.global_ip = Network.getGlobalIP();
            
            label_status.setText(result.get(0));
            label_asn.setText(result.get(1));
            label_city.setText(result.get(2));
            label_continent.setText(result.get(3));
            label_country.setText(result.get(4));
            label_latitude.setText(result.get(5));
            label_longitude.setText(result.get(6));
            label_org.setText(result.get(7));
            label_postal.setText(result.get(8));
            label_region.setText(result.get(9));
            label_carrier.setText(result.get(10));
        } else {

            Network.status = "Offline"; //.get(0);
            Network.global_ip = "NA";
            Network.asn = "NA"; //.get(1);
            Network.city = "NA"; //.get(2);
            Network.continent = "NA"; //.get(3);
            Network.country = "NA"; //.get(4);
            Network.latitude = "NA"; //.get(5);
            Network.longitude = "NA"; //.get(6);
            Network.org = "NA"; //.get(7);
            Network.postal = "NA"; //.get(8);
            Network.region = "NA"; //.get(9);
            Network.carrier = "NA"; //.get(10);

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

//        System.out.println("javafx.version: " + System.getProperty("javafx.version"));
//        System.out.println("java version: "+System.getProperty("java.version"));
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

}
