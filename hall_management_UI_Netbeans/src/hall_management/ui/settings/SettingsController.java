/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hall_management.ui.settings;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import hall_management.ui.pushNotification.Notification;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author suban
 */
public class SettingsController implements Initializable {

    @FXML
    private JFXPasswordField text_oldPass;
    @FXML
    private JFXPasswordField text_newPass;
    @FXML
    private JFXPasswordField text_confPass;
    @FXML
    private JFXButton button_update;
    @FXML
    private JFXButton button_exit;

    String oldPass;
    String newPass;
    Stage currentStage;

    public void setCurrentStage(Stage currentStage) {
        this.currentStage = currentStage;
        
        this.currentStage.setResizable(false);
        this.currentStage.initStyle(StageStyle.UTILITY);
    }
    
    boolean passUpdated = false;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        oldPass = null;
        newPass = null;
        passUpdated = false;
    }    


    public String getOldPass() {
        return oldPass;
    }

    public void setOldPass(String oldPass) {
        this.oldPass = oldPass;
    }

    public String getNewPass() {
        return newPass;
    }

    public void setNewPass(String newPass) {
        this.newPass = newPass;
    }

    public boolean isPassUpdated() {
        return passUpdated;
    }

    @FXML
    private void submitPass(ActionEvent event) {
        
        if(!text_oldPass.getText().equals(oldPass)){
            Notification.push("Error", "Please Enter Correct Existing Password", Notification.FAILURE, Pos.CENTER);
            return;
        }
        if(text_newPass.getText().isEmpty() || text_confPass.getText().isEmpty())
        {
            Notification.push("Error", "Please Provide all fields", Notification.FAILURE, Pos.CENTER);
            return;
            
        }
        if(!text_newPass.getText().equals(text_confPass.getText())){
            Notification.push("Error", "New Password Does not match with the Confirmed one", Notification.FAILURE, Pos.CENTER);
            return;
        }
        
        newPass = text_newPass.getText();
        passUpdated = true;
        closeWindow();
    }

    @FXML
    private void back(ActionEvent event) {
        passUpdated = false;
        closeWindow();
    }
    
    void closeWindow()
    {
        currentStage.close();
    }
}
