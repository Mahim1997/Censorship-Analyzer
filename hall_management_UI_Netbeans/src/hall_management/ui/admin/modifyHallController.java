/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hall_management.ui.admin;

import com.jfoenix.controls.JFXTextField;
import hall_management.db.queries.adminQueries.AdminQueries;
import hall_management.ui.pushNotification.Notification;
import hall_management.util.Interface.Controller;
import hall_management.util.Interface.Scenes;
import hall_management.util.SceneLoader;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
public class modifyHallController implements Initializable, Controller {

    String hall_name;

    private JFXTextField textLabel_hallID;
    @FXML
    private JFXTextField textLabel_provostID;
    @FXML
    private JFXTextField start_day;
    @FXML
    private JFXTextField start_month;
    @FXML
    private JFXTextField start_year;

    String hallID, provostID, start_date;
    @FXML
    private ChoiceBox choiceBox_hallName;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        try {
            initialiseChoiceBox();
            initialiseDate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void submitInfoToUpdate(ActionEvent event) {

//        hallID = textLabel_hallID.getText();
        hall_name = (String) choiceBox_hallName.getValue();
        provostID = textLabel_provostID.getText();

        if (hall_name.isEmpty() || provostID.isEmpty() || start_day.getText().isEmpty() || start_month.getText().isEmpty() || start_year.getText().isEmpty()) {
            Notification.push("FAILED", "Fill up all fields", Notification.FAILURE, Pos.CENTER);
            return;
        }

        int day, mon, year;
        try {
            day = Integer.parseInt(start_day.getText());
            mon = Integer.parseInt(start_month.getText());
            year = Integer.parseInt(start_year.getText());
        } catch (NumberFormatException e) {
            Notification.push("FAILED", "Invalid Date Format", Notification.FAILURE, Pos.CENTER);
            return;
        }

        if (day > 31 || day < 1 || mon > 12 || mon < 1) {
            Notification.push("FAILED", "Invalid Date Format", Notification.FAILURE, Pos.CENTER);
            return;
        }

        start_date = "" + day + "/" + mon + "/" + year;

        // update query
        try {
            AdminQueries.updateHallProvost(hall_name, provostID, start_date);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void goBack(ActionEvent event) {
        SceneLoader.closeScene(SceneLoader.CurrentScene());
        SceneLoader.loadPreviousScene(Scenes.admin_ui, this);
    }

    private void initialiseChoiceBox() throws Exception {

        choiceBox_hallName.setItems(FXCollections.observableArrayList(
                "AHSANULLAH", "SUHRAWARDY", "CHATRISOUTH", "CHATRINORTH"));
        choiceBox_hallName.setValue((String) "AHSANULLAH");
        hall_name = (String) choiceBox_hallName.getValue();
        choiceBox_hallName.setOnAction((event) -> {
            hall_name = (String) choiceBox_hallName.getValue();
        });
    }

    private void initialiseDate() {
        start_day.setEditable(false);
        start_month.setEditable(false);
        start_year.setEditable(false);

        DateFormat dateFormat = new SimpleDateFormat("DD");
        Calendar cal = Calendar.getInstance();
        String day = dateFormat.format(cal.getTime());
        dateFormat = new SimpleDateFormat("MM");
        cal = Calendar.getInstance();
        String month = dateFormat.format(cal.getTime());
        dateFormat = new SimpleDateFormat("YYYY");
        cal = Calendar.getInstance();
        String year = dateFormat.format(cal.getTime());

        start_day.setText(day);
        start_month.setText(month);
        start_year.setText(year);
    }

}
