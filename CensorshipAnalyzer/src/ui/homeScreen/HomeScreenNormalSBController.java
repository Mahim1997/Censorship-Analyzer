/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.homeScreen;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author esfs
 */
public class HomeScreenNormalSBController implements Initializable {

    @FXML
    private BorderPane borderPane;
    @FXML
    private Label label_UserName;
    @FXML
    private JFXTextField label_netName;
    @FXML
    private JFXTextField label_netType;
    @FXML
    private JFXTextField label_ipAddress;
    @FXML
    private JFXTextField label_location;
    @FXML
    private JFXTextField label_date;
    @FXML
    private JFXTextField label_time;
    @FXML
    private JFXButton btn_addresses;
    @FXML
    private JFXButton btn_networkInfo;
    @FXML
    private JFXButton btn_briefings;
    @FXML
    private JFXButton btn_userInfoCurrent;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        System.out.println("-->>>Controller initialise HomeScreenNormalSBController .... ");
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

}
