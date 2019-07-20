package ui.controllers;

import com.jfoenix.controls.JFXTextField;
import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ui.model.DNSDetails;
import ui.model.Report;
import ui.model.User;

public class DNSRecordController {

    @FXML
    private Text text_url;
    @FXML
    private Text text_NetworkName;
    @FXML
    private Text text_testType;
    @FXML
    private Text text_networkType;
    @FXML
    private Text text_time;
    @FXML
    private VBox vbox_localIPs;
    @FXML
    private VBox vbox_stubbyIPs;
    @FXML
    private VBox vbox_matchingIPs;

    @FXML
    private Text text_censoredOrNot;

    private Report report;

    private Stage stage;
    @FXML
    private Text text_bognoIP;
    @FXML
    private Text text_privateIP;
    @FXML
    private Text text_timeout;
    @FXML
    private Text text_invalidDomain;
    @FXML
    private Text text_noNameServers;
    @FXML
    private Text text_localServerRRCodeSet;
    @FXML
    private Text text_UnknownError;
    @FXML
    private Text text_details;

    public void setUpThings(Report report, Stage stage) {
        this.report = report;
        this.stage = stage;
    }

    public void showThings() {
        this.text_NetworkName.setText("Network Name: " + User.networkName);
        this.text_networkType.setText("Network Type:" + User.networkType);
        
        this.text_censoredOrNot.setText("IS CENSORED? " + this.report.getIs_censored());
        this.text_time.setText("Time: " + this.report.getTime_stamp());
        this.text_testType.setText("Test Type: " + this.report.getTest_type());
        this.text_url.setText("URL:" + this.report.getUrl());

        loadIPListLocalDNSServer();
        loadIPListStubby();
        loadIPListMatched();
        loadImages();
    }

    private void loadImages() {

//        String crossImagePathName = "../../resources/images_testSites/X_transparent.png";
//        String crossImagePathName = "resources/images_testSites/D.png";
        String whatToDisplay = "NO";
//        if (errorCode == 0) {
        this.text_UnknownError.setText(whatToDisplay);
        this.text_bognoIP.setText(whatToDisplay);
        this.text_invalidDomain.setText(whatToDisplay);
        this.text_localServerRRCodeSet.setText(whatToDisplay);
        this.text_noNameServers.setText(whatToDisplay);
        this.text_privateIP.setText(whatToDisplay);
        this.text_timeout.setText(whatToDisplay);
//        }
    }

    private void formDetailsUsingErrorCode(int errorCode) {
        String text;// = "" ;
        switch (errorCode) {
            case 110:
                text = "Local DNS Server may be down";
                break;
            case 111:
                text = "No such domain";
                this.text_invalidDomain.setText("YES");
                break;
            case 112:
                text = "Timeout ... ";
                this.text_timeout.setText("YES");
                break;
            case 113:
                text = "Local DNS Server returns Bogon IP";
                this.text_bognoIP.setText("YES");
                break;
            case 114:
                text = "Local DNS Server returns public IP but no response from Stubby";
                break;
            case 115:
                text = "Local DNS Server returns Bogon IP";
                this.text_bognoIP.setText("YES");
                break;
            case 116:
                text = "Local DNS Server returns public IP [NOT CENSORED]";
                break;
            default:
                text = "Not censored";
                break;
        }
        this.text_details.setText("Details:" + text);
    }

    private void loadIPListMatched() {
        //Form the matched set
        DNSDetails details_dns = this.report.getDns_details();
        List<JFXTextField> textFieldList = new ArrayList<>();

        for (String ip : details_dns.getMatchedIPs()) {
            //each ip is to be loaded in a text field
            JFXTextField textField = new JFXTextField();
            textField.setText(ip);
            textField.setEditable(false);
            textField.setStyle("-fx-text-fill: #000000; ");//-fx-font-size: 16px;");
            textFieldList.add(textField);
        }

        //Finally add to the vbox
        this.vbox_matchingIPs.getChildren().addAll(textFieldList);

    }

    private void loadIPListStubby() {
        List<JFXTextField> textFieldList = new ArrayList<>();

        for (String ip : this.report.getDns_details().getIp_address_list_stubby()) {
            //each ip is to be loaded in a text field
            JFXTextField textField = new JFXTextField();
            textField.setText(ip);
            textField.setEditable(false);
            textField.setStyle("-fx-text-fill: #000000; ");//-fx-font-size: 16px;");
            textFieldList.add(textField);
        }

        //Finally add to the vbox
        this.vbox_stubbyIPs.getChildren().addAll(textFieldList);
    }

    private void loadIPListLocalDNSServer() {
        List<JFXTextField> textFieldList = new ArrayList<>();

        for (String ip : this.report.getDns_details().getIp_address_list_local()) {
            //each ip is to be loaded in a text field
            JFXTextField textField = new JFXTextField();
            textField.setText(ip);
            textField.setEditable(false);
            textField.setStyle("-fx-text-fill: #000000; ");//-fx-font-size: 16px;");

            textFieldList.add(textField);
        }

        //Finally add to the vbox
        this.vbox_localIPs.getChildren().addAll(textFieldList);
    }

}
