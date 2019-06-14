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
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author esfs
 */
public class HomeScreenController implements Initializable {

 
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        System.out.println("-->>>Controller initialise HomeScreenNormalSBController .... ");
        setUpTexts();
    }

    @FXML
    private void goHome(ActionEvent event) {
    }

    @FXML
    private void goToTestSites(ActionEvent event) {
    }

    @FXML
    private void goToReports(ActionEvent event) {
    }

    @FXML
    private void goToMisTasks(ActionEvent event) {
    }

    @FXML
    private void goToPeriodicUpdates(ActionEvent event) {
    }

    @FXML
    private void goToComparisons(ActionEvent event) {
    }

    private void setUpTexts() {
        /*try {
            String ipAddress = InetAddress.getLocalHost().getHostAddress();
            String macAddress = "8F-FA-23-9F-A3-E2";
            label_ipAddress.setText(ipAddress);
            
            label_netName.setText(User.networkName);
            label_netType.setText(User.networkType);
            
            label_location.setText(User.location);
            label_UserName.setText(User.userFirstName + " " + User.userLastName);
            
            btn_addresses.setText("IP Address: " + ipAddress + "\n" + "MAC Address: " + macAddress);
            btn_networkInfo.setText("Network Name: " + User.networkName + "\n" + "Network Type: " + User.networkType);
            btn_userInfoCurrent.setText("Status: " + "Online" + "\n" + "Mode: " + "Logged User");
            
        } catch (UnknownHostException ex) {
            System.out.println("IP Address paite problems ... ");
        }*/
    }

}
