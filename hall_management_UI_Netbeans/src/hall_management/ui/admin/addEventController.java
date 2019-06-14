/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hall_management.ui.admin;

import com.jfoenix.controls.JFXTextField;
import hall_management.db.queries.Query;
import hall_management.db.queries.adminQueries.AdminQueries;
import hall_management.ui.pushNotification.Notification;
import hall_management.util.Interface.Controller;
import hall_management.util.Interface.Scenes;
import hall_management.util.Interface.Table;
import hall_management.util.SceneLoader;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
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
public class addEventController implements Initializable, Controller {

//    private JFXTextField textLabel_fullName;
    @FXML
    private JFXTextField textLabel_year;
    @FXML
    private JFXTextField textLabel_sport;
    private JFXTextField textLabel_hallName;
    @FXML
    private JFXTextField textLabel_superVisID;

    String eventName, year, hallName, superVisorID, sport, status;
    @FXML
    private ChoiceBox choiceBox_status;
    @FXML
    private ChoiceBox choiceBox_hallName;

    boolean checkValid() {
        if (year.isEmpty() || superVisorID.isEmpty() || sport.isEmpty() || status.isEmpty()) {
            return false;
        }
        try {
            Integer.parseInt(superVisorID);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        initialiseChoiceBox();
    }

    boolean isHallNameValid() {
        // not a hall event
        //FOR GLOBAL EVENT IT MAY BE NULL
        if (hallName.isEmpty() && status.equals("LOCAL")) {
            return false;
        }
        return true;

    }

    @FXML
    private void submitInfoToAdd(ActionEvent event) throws Exception {
//        eventName = textLabel_fullName.getText();
        year = textLabel_year.getText();
        sport = textLabel_sport.getText();
        hallName = (String) choiceBox_hallName.getValue();
//        hallName = textLabel_hallName.getText();
        superVisorID = textLabel_superVisID.getText();
        status = (String) choiceBox_status.getValue();
//        String type = hallName.isEmpty() ? "GLOBAL" : "LOCAL";
        String type = status;
        String s = Query.getFieldInfoFromTable(Table.Teacher, superVisorID, "ID");
        if (s == null) {
            Notification.push("Error", "TEACHER ID MUST BE A VALID ONE!", Notification.FAILURE, Pos.CENTER);
            return;
        }
        String event_status = "UPCOMING";// SINCE IT IS JUST ADDED!

        if (!checkValid()) {
            Notification.push("Error", "Provide all fields", Notification.FAILURE, Pos.CENTER);
        }
        if (!isHallNameValid()) {
            Notification.push("Error", "Hall does not Exists", Notification.FAILURE, Pos.CENTER);
        }
//        String hallID =
        s = AdminQueries.insertNewEvent(eventName, year, sport, type, "UPCOMING", hallName, superVisorID);
        if (s.contains("ALREADY")) {
            Notification.push("Error", "Event already added!", Notification.FAILURE, Pos.CENTER);
        } else if (s.contains("SUCCESS")) {
            Notification.push("SUCCESS", "Add another field maybe?", Notification.SUCCESS);
            clear();
        }
    }

    @FXML
    private void clear() {
//        textLabel_fullName.setText("");
        textLabel_year.setText("");
        textLabel_sport.setText("");
        textLabel_hallName.setText("");
        textLabel_superVisID.setText("");
//        textLabel_status.setText("");
    }

    @FXML
    private void goBack(ActionEvent event) {
        SceneLoader.closeScene(SceneLoader.CurrentScene());
        SceneLoader.loadPreviousScene(Scenes.admin_ui, this);
    }

    private void initialiseChoiceBox() {
        choiceBox_status.setItems(FXCollections.observableArrayList(
                "LOCAL", "GLOBAL"));
        choiceBox_status.setValue((String) "LOCAL");
        status = (String) choiceBox_status.getValue();
        choiceBox_status.setOnAction((event) -> {
            status = (String) choiceBox_status.getValue();
        });

        choiceBox_hallName.setItems(FXCollections.observableArrayList(
                "AHSANULLAH", "SUHRAWARDY", "CHATRISOUTH", "CHATRINORTH"));
        choiceBox_hallName.setValue((String) "AHSANULLAH");
        hallName = (String) choiceBox_hallName.getValue();
        choiceBox_hallName.setOnAction((event) -> {
            hallName = (String) choiceBox_status.getValue();
        });
    }

}
