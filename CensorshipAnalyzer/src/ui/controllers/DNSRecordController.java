 
package ui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ui.model.Report;
import util.loader.SceneLoader;
import util.loader.Scenes;
 
public class DNSRecordController{

 
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
    

    private void goBack(ActionEvent event) {
        stage.close();
//        SceneLoader.loadSceneInSameStage(Scenes.censoredRecordsFXML);
    }

    public void setUpThings(Report report, Stage stage) {
        this.report = report;
        this.stage = stage;
    }

    public void showThings() {
        this.text_NetworkName.setText(this.report.getNetworkName());
        this.text_censoredOrNot.setText(this.report.getIsCensored());
        this.text_time.setText(this.report.getTime());
        this.text_testType.setText(this.report.getTestType());
        this.text_url.setText(this.report.getUrl());
    }

    @FXML
    private void exitWindow(ActionEvent event) {
    }
    
    
    
    
    
}
