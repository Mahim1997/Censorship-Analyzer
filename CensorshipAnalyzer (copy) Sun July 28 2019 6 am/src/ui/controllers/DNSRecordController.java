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
    private Text text_invalidDomain;
    private Text text_noNameServers;
    private Text text_localServerRRCodeSet;
    @FXML
    private Text text_UnknownError;
    @FXML
    private Text text_details;
    @FXML
    private Text text_nxDomain;
    @FXML
    private Text text_stubbyFailed;
    @FXML
    private Text text_loopback;
    @FXML
    private Text text_multicast;
    @FXML
    private Text text_broadcast;
    @FXML
    private Text text_noAnswerPkt;
    @FXML
    private Text text_localServerTimeout;
    @FXML
    private Text text_nonOVerlappingIPList;
    @FXML
    private Text text_8_to_24_bit_matched;
    @FXML
    private Text text_midboxHopCount;

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
        loadFields();
    }

    private void loadFields() {
//        String whatToDisplay = "NO";
        DNSDetails dns = this.report.getDns_details();
        this.text_8_to_24_bit_matched.setText(dns.getIs_is_first_8_to_24_bit_match());
        this.text_UnknownError.setText(dns.getIs_unknown_error());
        this.text_bognoIP.setText(dns.getIs_bogon());
        
        this.text_nxDomain.setText(dns.getIs_nxDomain());
        this.text_stubbyFailed.setText(dns.getIs_stubby_failed());
       
        this.text_timeout.setText(dns.getIs_timeout_local_server());
        this.text_loopback.setText(dns.getIs_loopback());
        this.text_multicast.setText(dns.getIs_multicast());
        this.text_broadcast.setText(dns.getIs_broadcast());
        this.text_privateIP.setText(dns.getIs_private());
        this.text_UnknownError.setText(dns.getIs_unknown_error());
        this.text_noAnswerPkt.setText(dns.getIs_noAnswerPacket());
        this.text_localServerTimeout.setText(dns.getIs_timeout_local_server());
        this.text_nonOVerlappingIPList.setText(dns.getIs_non_overlapping_ip_list());
        this.text_midboxHopCount.setText(dns.getMiddle_box_hop_count());
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
