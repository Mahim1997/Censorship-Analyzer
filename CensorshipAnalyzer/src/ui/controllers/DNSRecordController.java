package ui.controllers;

import com.jfoenix.controls.JFXTextField;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ui.model.DNSDetails;
import ui.model.Report;

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
    private ImageView imageView_bogonIP;
    @FXML
    private ImageView imageView_privateIP;
    @FXML
    private ImageView imageView_timeout;
    @FXML
    private ImageView imageView_invalidDomain;
    @FXML
    private ImageView imageView_noNameServers;
    @FXML
    private ImageView imageView_localServerRRCodeSet;
    @FXML
    private ImageView imageView_UnknownError;
    @FXML
    private Text text_censoredOrNot;

    private Report report;
    private Stage stage;

    public void setUpThings(Report report, Stage stage) {
        this.report = report;
        this.stage = stage;
    }

    public void showThings() {
        this.text_NetworkName.setText("Network name: " + this.report.getNetworkName());
        this.text_censoredOrNot.setText("IS CENSORED? " + this.report.getIsCensored());
        this.text_time.setText("Time: " + this.report.getTime());
        this.text_testType.setText("Test Type: " + this.report.getTestType());
        this.text_url.setText("URL:" + this.report.getUrl());

        loadIPListLocalDNSServer();
        loadIPListStubby();
        loadIPListMatched();
        loadImages();
    }

    private void loadImages() {
        int errorCode = this.report.getCensorship_code();
        System.out.println("---->>>Inside loadImages() ... errorCode = " + errorCode);
        
        String crossImagePathName = "../../resources/images_testSites/X_transparent.png";
        
        crossImagePathName = "X_transparent.png";
//        crossImagePathName = "../../resources/images_testSites/X_transparent.png";
        
        if(errorCode == 0){
            //all images are to be loaded for NOT
//                this.imageView_UnknownError.setImage(new Image(new FileInputStream(crossImagePathName)));
//            this.imageView_bogonIP.setImage(new Image(crossImagePathName));

        }
        
    }

    private void loadIPListMatched() {
        //Form the matched set
        DNSDetails details_dns = this.report.getDns_details();
        List<JFXTextField> textFieldList = new ArrayList<>();

        for(String ip: details_dns.getMatchedIPs()){
            //each ip is to be loaded in a text field
            JFXTextField textField = new JFXTextField();
            textField.setText(ip);
            textFieldList.add(textField);
        }
        
        //Finally add to the vbox
        this.vbox_matchingIPs.getChildren().addAll(textFieldList);

    }

    private void loadIPListStubby() {
        List<JFXTextField> textFieldList = new ArrayList<>();

        for (String ip : this.report.getDns_details().getListIpStubby()) {
            //each ip is to be loaded in a text field
            JFXTextField textField = new JFXTextField();
            textField.setText(ip);
            textFieldList.add(textField);
        }

        //Finally add to the vbox
        this.vbox_stubbyIPs.getChildren().addAll(textFieldList);
    }

    private void loadIPListLocalDNSServer() {
        List<JFXTextField> textFieldList = new ArrayList<>();

        for (String ip : this.report.getDns_details().getListIpLocalDNSServer()) {
            //each ip is to be loaded in a text field
            JFXTextField textField = new JFXTextField();
            textField.setText(ip);
            textFieldList.add(textField);
        }

        //Finally add to the vbox
        this.vbox_localIPs.getChildren().addAll(textFieldList);
    }

}
