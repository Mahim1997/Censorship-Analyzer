/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hall_management.ui.admin;

import com.jfoenix.controls.JFXTextField;
import hall_management.ui.pushNotification.Notification;
import hall_management.util.Interface.Controller;
import hall_management.util.Interface.Scenes;
import hall_management.util.SceneLoader;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.ChoiceBox;

/**
 * FXML Controller class
 *
 * @author suban
 */
public class deleteLogsController implements Initializable, Controller {

    @FXML
    private ChoiceBox<?> choiceLogType;
    @FXML
    private JFXTextField start_day;
    @FXML
    private JFXTextField start_month;
    @FXML
    private JFXTextField start_year;
    @FXML
    private JFXTextField end_day;
    @FXML
    private JFXTextField end_month;
    @FXML
    private JFXTextField end_year;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void proceedDelete(ActionEvent event) {
//        String type = choiceLogType.getSelectionModel().getSelectedItem();

        String startDate, endDate;

        try{
            startDate = "" + Integer.parseInt(start_day.getText()) + "/" + Integer.parseInt(start_month.getText()) + "/" + Integer.parseInt(start_year.getText());
            endDate = "" + Integer.parseInt(end_day.getText()) + "/" + Integer.parseInt(end_month.getText()) + "/" + Integer.parseInt(end_year.getText());
        }
        catch(NumberFormatException e){
            Notification.push("Invalid Date", "Provide Valid Date", Notification.FAILURE, Pos.CENTER);
            return;
        }
        // check endDate>startDate if so then
        
        // execute Delete Query
        
        Notification.push("Successful", "Deleted logs", Notification.SUCCESS, Pos.CENTER);
    }

    @FXML
    private void goBack(ActionEvent event) {
        SceneLoader.closeScene(SceneLoader.CurrentScene());
        SceneLoader.loadPreviousScene(Scenes.admin_ui, this);
    }

    boolean isLessDate(String d1,String d2){
        return false;
    }
}
