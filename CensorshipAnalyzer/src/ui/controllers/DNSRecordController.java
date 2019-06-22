 
package ui.controllers;

import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import util.loader.SceneLoader;
import util.loader.Scenes;
 
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
    
    

    
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
 
        System.out.println("---->>> Initializing ... DNSRecordController .... ");
        this.vbox_localIPs.getChildren().add(new JFXTextField("IP 1"));
    }

    @FXML
    private void goBack(ActionEvent event) {
        SceneLoader.loadSceneInSameStage(Scenes.censoredRecordsFXML);
    }
    
    
    
}
