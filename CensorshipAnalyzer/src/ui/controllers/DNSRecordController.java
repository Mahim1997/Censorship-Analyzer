/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.controllers;

import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 *
 * @author viper
 */
public class DNSRecordController implements Initializable{

 
    @FXML
    private Text text_url;
    @FXML
    private Text text_NetworkName;
    @FXML
    private Text text_date;
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
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
 
        this.vbox_localIPs.getChildren().add(new JFXTextField("IP 1"));
    }
    
    
    
}
