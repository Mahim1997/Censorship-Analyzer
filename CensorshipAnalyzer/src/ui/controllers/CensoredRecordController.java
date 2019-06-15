 
package ui.controllers;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;
 
 
public class CensoredRecordController implements Initializable{

    @FXML
    private TableView<?> tableView;
    @FXML
    private JFXTextField textField_url;
    @FXML
    private JFXTextField textField_timeStart;
    @FXML
    private JFXTextField textField_timeEnd;
    @FXML
    private JFXToggleButton toggleButton_NetworkType;
    @FXML
    private JFXToggleButton toggleButtonCensored;
    @FXML
    private ChoiceBox<?> choiceBox_NetworkName;
    @FXML
    private ChoiceBox<?> choiceBox_TestType;
    @FXML
    private ChoiceBox<?> choiceBox_censoredType;
    @FXML
    private Text text_whichPage;

    @FXML
    private void toggleNetworkType(ActionEvent event) {
    }

    @FXML
    private void toggleCensored(ActionEvent event) {
    }

    @FXML
    private void refreshInfo(ActionEvent event) {
    }

    @FXML
    private void nextPage(ActionEvent event) {
    }

    @FXML
    private void previousPage(ActionEvent event) {
    }

    @FXML
    private void goBack(ActionEvent event) {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("--->>> Initializing CensoredRecordController.java ....");
    }
    
}
