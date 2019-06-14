/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hall_management.ui.admin;

import com.jfoenix.controls.JFXTextField;
import hall_management.util.Interface.Controller;
import hall_management.util.Interface.Scenes;
import hall_management.util.SceneLoader;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author suban
 */
public class modifyEventController implements Initializable,Controller {

    @FXML
    private JFXTextField textLabel_eventID;
    @FXML
    private JFXTextField textLabel_winnerID;
    @FXML
    private JFXTextField textLabel_runnerupID;

    String eventID,winnerID,runnerID;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void submitInfoToUpdate(ActionEvent event) {
        eventID = textLabel_eventID.getText().trim();
        winnerID = textLabel_winnerID.getText().trim();
        runnerID = textLabel_runnerupID.getText().trim();
    }

    @FXML
    private void goBack(ActionEvent event) {
        SceneLoader.closeScene(SceneLoader.CurrentScene());
        SceneLoader.loadPreviousScene(Scenes.admin_ui, this);
    }
    
}
