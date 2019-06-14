/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hall_management.ui.admin;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import hall_management.db.queries.adminQueries.AdminQueries;
import hall_management.ui.pushNotification.Notification;
import hall_management.util.Interface.Controller;
import hall_management.util.Interface.Scenes;
import hall_management.util.Interface.Table;
import hall_management.util.SceneLoader;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;

/**
 * FXML Controller class
 *
 * @author suban
 */
public class updatePersonController implements Initializable, Controller {

    @FXML
    private JFXTextField textLabel_teacherID;
    @FXML
    private JFXTextField textLabel_desigTeacher;
    @FXML
    private JFXToggleButton toogle_retired_teacher;
    @FXML
    private JFXTextField textLabel_studentID;
    @FXML
    private JFXToggleButton toogle_grad_std;
    @FXML
    private JFXTextField textLabel_staff;
    @FXML
    private JFXTextField textLabel_staffJob;

    String t_id, t_des;
    String s_id;
    String stf_id, stf_job;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void goBack(ActionEvent event) {
        SceneLoader.closeScene(SceneLoader.CurrentScene());
        SceneLoader.loadPreviousScene(Scenes.admin_ui, this);
    }

    @FXML
    private void submitInfoToUpdateTeacher() throws Exception {
        t_id = textLabel_teacherID.getText();
        t_des = textLabel_desigTeacher.getText();
        if (toogle_retired_teacher.isSelected() == false) {
            System.out.println("=->TOGGLE BUTTON IS FALSE SO RETURN!");
            textLabel_teacherID.clear();
            textLabel_desigTeacher.clear();
            return;
        }
        if (t_id.isEmpty() || t_des.isEmpty()) {
            Notification.push("FAILED", "Fill up all fields", Notification.FAILURE, Pos.CENTER);
            return;
        }
//        String s = AdminQueries.updateField(Table.Teacher, "DESIGNATION", "ID", t_id, "RETIRED");
        String s = AdminQueries.updateField(Table.Teacher, "DESIGNATION", "RETIRED", "ID", t_id);
        if (s.toUpperCase().contains("DOESNOT")) {
            Notification.push("ERROR!", "ID DOESNOT EXIST", Notification.FAILURE);
            textLabel_teacherID.clear();
            textLabel_desigTeacher.clear();
            return;
        } else {
            Notification.push("SUCCESS!", "Update someone else maybe?", Notification.SUCCESS);
            textLabel_teacherID.clear();
            textLabel_desigTeacher.clear();
        }
    }

    @FXML
    private void submitInfoToUpdateStudent(ActionEvent event) throws Exception {
        s_id = textLabel_studentID.getText();

        if (s_id.isEmpty()) {
            Notification.push("FAILED", "Fill up all fields", Notification.FAILURE, Pos.CENTER);
            return;
        }

        if (!toogle_grad_std.isSelected()) {
            return;
        }
        // otherwise update stat
        String s = AdminQueries.updateField(Table.Student, "TYPE", "GRADUATE", "ID", s_id);
        if (s.toUpperCase().contains("DOESNOT")) {
            Notification.push("ERROR!", "ID DOESNOT EXIST", Notification.FAILURE);
            textLabel_studentID.clear();
            return;
        } else {
            Notification.push("SUCCESS!", "Update someone else maybe?", Notification.SUCCESS);
            textLabel_studentID.clear();
        }
    }

    @FXML
    private void submitInfoToUpdateStaff(ActionEvent event) throws Exception {
        stf_id = textLabel_staff.getText();
        stf_job = textLabel_staffJob.getText();

        if (stf_id.isEmpty() || stf_job.isEmpty()) {
            Notification.push("FAILED", "Fill up all fields", Notification.FAILURE, Pos.CENTER);
            return;
        }

        String s = AdminQueries.updateField(Table.Staff, "JOB_TYPE", stf_job, "ID", stf_id);
        if (s.toUpperCase().contains("DOESNOT")) {
            Notification.push("ERROR!", "ID DOESNOT EXIST", Notification.FAILURE);
            textLabel_staff.clear();
            textLabel_staffJob.clear();
            return;
        } else {
            Notification.push("SUCCESS!", "Update someone else maybe?", Notification.SUCCESS);
            textLabel_staff.clear();
            textLabel_staffJob.clear();
        }

    }

}
