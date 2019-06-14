/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hall_management.ui.admin;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import hall_management.db.queries.adminQueries.AdminQueries;
import hall_management.ui.pushNotification.Notification;
import hall_management.util.Interface.Controller;
import hall_management.util.Interface.Scenes;
import hall_management.util.SceneLoader;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.SplitMenuButton;

/**
 * FXML Controller class
 *
 * @author suban
 */
public class insertPersonController implements Initializable, Controller {

    @FXML
    private JFXTextField textField_studentID;
    @FXML
    private JFXButton button_refresh;
    @FXML
    private JFXTextField textLabel_teacherID;
    @FXML
    private JFXButton button_save1;
    @FXML
    private JFXButton button_refresh1;
    @FXML
    private JFXTextField textLabel_staffID;
    @FXML
    private JFXTextField textField_dept_std;
    @FXML
    private JFXTextField textField_addr_std;
    @FXML
    private JFXTextField textField_fatherName_std;
    @FXML
    private JFXTextField textField_motherName_std;
    @FXML
    private JFXTextField textField_contactNo_std;
    @FXML
    private JFXTextField textField_dateOfBirth_day_std;
    @FXML
    private JFXTextField textField_dateOfBirth_month_std;
    @FXML
    private JFXTextField textField_dateOfBirth_year_std;

    @FXML
    private JFXTextField text_firstName_std;
    @FXML
    private JFXTextField text_lastName_std;
    @FXML
    private JFXPasswordField textField_pass_std;

    @FXML
    private JFXTextField textLabel_departmentID_teacher;
    @FXML
    private JFXTextField textLabel_designation_teacher;
    @FXML
    private JFXTextField textField_contactNo_teacher;
    @FXML
    private JFXTextField text_firstName_teacher;
    @FXML
    private JFXTextField text_lastName_teacher;
    @FXML
    private JFXPasswordField textField_pass_teacher;

    @FXML
    private JFXTextField textLabel_contactNo_stf;
    @FXML
    private JFXTextField textLabel_jobType_stf;
    @FXML
    private JFXPasswordField text_pass_stf;

    @FXML
    private ChoiceBox choiceBox_religion_std;
    @FXML
    private ChoiceBox choiceBox_gender_std;
    @FXML
    private ChoiceBox choiceBox_hallName_std;
    @FXML
    private ChoiceBox choiceBox_bloodGrp_std;
    @FXML
    private ChoiceBox choiceBox_type_std;

    private String religion_std;
    private String gender_std;
    private String hallName_std;
    private String bloodGrp_std;
    private String type_std;
    @FXML
    private ChoiceBox choiceBox_gender_teacher;

    String id_tcr;
    String fName_tcr;
    String lName_tcr;
    String dept_tcr;
    String desig_tcr;
    String contact_tcr;
    String password_tcr;
    String gender_tcr;
    @FXML
    private JFXTextField textField_fullName_staff;
    @FXML
    private ChoiceBox choiceBox_hallName_staff;

    String hallName_staff;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        setAllEditable(true);
        initialiseChoiceBox();
    }

    @FXML
    private void submitStudent() throws Exception {
        String dept_std;
        String addr_std;
        String fatherName_std;
        String motherName_std;
        String contactNo_std;
        String dateOfBirth_day_std;
        String dateOfBirth_month_std;
        String dateOfBirth_year_std;
        String firstName_std;
        String lastName_std;
        String studentID;

        dept_std = textField_dept_std.getText();
        addr_std = textField_addr_std.getText();
        fatherName_std = textField_fatherName_std.getText();
        motherName_std = textField_motherName_std.getText();
        contactNo_std = textField_contactNo_std.getText();
        dateOfBirth_day_std = textField_dateOfBirth_day_std.getText();
        dateOfBirth_month_std = textField_dateOfBirth_month_std.getText();
        dateOfBirth_year_std = textField_dateOfBirth_year_std.getText();
        firstName_std = text_firstName_std.getText();
        lastName_std = text_lastName_std.getText();
        studentID = textField_studentID.getText();
        
        religion_std = (String) choiceBox_religion_std.getValue();
        String x = "Pick a Religion";
        if(religion_std.equals(x)){
            Notification.push("WARNING!", "Pick a Religion", Notification.WARNING);
            return ;
        }
        //    private MenuButton 
//        String gender_std = textField_gender_std.getText();
        //    private SplitMenuButton
//        String religion_std = textField_religion_std.getText();
        //    private SplitMenuButton 
//        String blood_std = textField_bldgrp_std.getText();

//    private JFXPasswordField 
        String pass_std = textField_pass_std.getText();
        String dateOfBirth = AdminQueries.makeDateOfBirth(dateOfBirth_year_std, dateOfBirth_month_std, dateOfBirth_day_std);
        String s = AdminQueries.insertStudent(studentID, firstName_std, lastName_std, dept_std,
                addr_std, bloodGrp_std, dateOfBirth, type_std, hallName_std, religion_std, gender_std, pass_std, fatherName_std, motherName_std, contactNo_std);
        
        if (s.contains("QUERY_SUCCESS")) {
            Notification.push("SUCCESS!", "ADD ANOTHER PERSON MAYBE?", Notification.SUCCESS);
            clearStudent();
        } else if (s.contains("EXCEPTION")) {
            Notification.push("WARNING!", "CHECK DATES!", Notification.WARNING);
        } else if (s.contains("QUERY_PROBLEM")) {
            Notification.push("WARNING!", "PROBLEM IN INSERTION!", Notification.WARNING);
        } else {
            Notification.push("WARNING!", "THIS ID ALREADY EXISTS!", Notification.WARNING);
        }
    }

    @FXML
    private void clearStudent() {
        textField_dept_std.clear();

        textField_addr_std.clear();

        textField_fatherName_std.clear();

        textField_motherName_std.clear();

        textField_contactNo_std.clear();

        textField_dateOfBirth_day_std.clear();

        textField_dateOfBirth_month_std.clear();

        textField_dateOfBirth_year_std.clear();

        text_firstName_std.clear();

        text_lastName_std.clear();

        textField_studentID.clear();
    }

    @FXML
    private void submitTeacher() throws Exception {
        /*
        String id_tcr;
    String fName_tcr;
    String lName_tcr;
    String dept_tcr;
    String desig_tcr;
    String contact_tcr;
    String password_tcr;
    String gender_tcr;
    
         */
        id_tcr = textLabel_teacherID.getText();
        fName_tcr = text_firstName_teacher.getText();
        lName_tcr = text_lastName_teacher.getText();
        dept_tcr = textLabel_departmentID_teacher.getText();
        desig_tcr = textLabel_designation_teacher.getText();
        contact_tcr = textField_contactNo_teacher.getText();
        password_tcr = textField_pass_teacher.getText();
        String s = AdminQueries.insertNewTeacher(id_tcr, fName_tcr, lName_tcr, dept_tcr, desig_tcr, contact_tcr, password_tcr, gender_tcr);
        if (s.toUpperCase().contains("QUERY_SUCCESS")) {
            Notification.push("SUCCESS!", "Add Another Person??", Notification.SUCCESS);
            clearTeacher();
        } else if (s.toUpperCase().contains("ALREADY_PRESENT")) {
            Notification.push("WARNING", "THIS ID IS TAKEN", Notification.WARNING);
        }

    }

    @FXML
    private void clearTeacher() {
        textLabel_departmentID_teacher.clear();

        textLabel_designation_teacher.clear();

        textField_contactNo_teacher.clear();

        text_firstName_teacher.clear();

        text_lastName_teacher.clear();
        textField_pass_teacher.clear();
        textLabel_teacherID.clear();
    }

    @FXML
    private void addStaff() throws Exception {
        String staffID = textLabel_staffID.getText();

        String contactNo = textLabel_contactNo_stf.getText();

        String jobType = textLabel_jobType_stf.getText();

        String pass = text_pass_stf.getText();
        String fullName = textField_fullName_staff.getText();
        hallName_staff = (String) choiceBox_hallName_staff.getValue();
        String s = AdminQueries.insertNewStaff(staffID, fullName, jobType, contactNo, pass, hallName_staff);
        if (s.toUpperCase().contains("QUERY_SUCCESS")) {
            Notification.push("SUCCESS!", "Add Another Person??", Notification.SUCCESS);
            clearStaff();
        } else if (s.toUpperCase().contains("ALREADY_PRESENT")) {
            Notification.push("WARNING", "THIS ID IS TAKEN", Notification.WARNING);
        }
    }

    @FXML
    private void clearStaff() {
        textLabel_staffID.clear();

        textLabel_contactNo_stf.clear();

        textLabel_jobType_stf.clear();

        text_pass_stf.clear();
        textField_fullName_staff.clear();
    }

    @FXML
    private void goBack() {
        SceneLoader.closeScene(SceneLoader.CurrentScene());
        SceneLoader.loadPreviousScene(Scenes.admin_ui, this);
    }

    private void setAllEditable(boolean b) {
        textField_dept_std.setEditable(b);

        textField_addr_std.setEditable(b);

        textField_fatherName_std.setEditable(b);

        textField_motherName_std.setEditable(b);

        textField_contactNo_std.setEditable(b);

        textField_dateOfBirth_day_std.setEditable(b);

        textField_dateOfBirth_month_std.setEditable(b);

        textField_dateOfBirth_year_std.setEditable(b);

        text_firstName_std.setEditable(b);

        text_lastName_std.setEditable(b);

        textField_studentID.setEditable(b);

        //TEACHER
        textLabel_departmentID_teacher.setEditable(b);

        textLabel_designation_teacher.setEditable(b);

        textField_contactNo_teacher.setEditable(b);

        text_firstName_teacher.setEditable(b);

        text_lastName_teacher.setEditable(b);
        textField_pass_teacher.setEditable(b);
        textLabel_teacherID.setEditable(b);

        //STAFF
    }

    private void initialiseChoiceBox() {
        choiceBox_bloodGrp_std.setItems(FXCollections.observableArrayList(
                "A+", "A-", "O+", "O-", "AB+", "AB-"));
        choiceBox_bloodGrp_std.setValue((String) "A+");
        bloodGrp_std = (String) choiceBox_bloodGrp_std.getValue();
        choiceBox_bloodGrp_std.setOnAction((event) -> {
            bloodGrp_std = (String) choiceBox_bloodGrp_std.getValue();
        });

        choiceBox_gender_std.setItems(FXCollections.observableArrayList(
                "MALE", "FEMALE"));
        choiceBox_gender_std.setValue((String) "MALE");
        gender_std = (String) choiceBox_gender_std.getValue();
        choiceBox_gender_std.setOnAction((event) -> {
            gender_std = (String) choiceBox_gender_std.getValue();
        });

        choiceBox_hallName_std.setItems(FXCollections.observableArrayList(
                "AHSANULLAH", "SUHRAWARDY", "CHATRISOUTH", "CHATRINORTH"));
        choiceBox_hallName_std.setValue((String) "AHSANULLAH");
        hallName_std = (String) choiceBox_hallName_std.getValue();
        choiceBox_hallName_std.setOnAction((event) -> {
            hallName_std = (String) choiceBox_hallName_std.getValue();
        });

        choiceBox_religion_std.setItems(FXCollections.observableArrayList(
                "ISLAM", "HINDUISM", "BUDHISM", "SIKHISM", "CHRISTIANITY"));
        choiceBox_religion_std.setValue((String) "Pick a Religion");
        religion_std = (String) choiceBox_religion_std.getValue();
        choiceBox_religion_std.setOnAction((event) -> {
            religion_std = (String) choiceBox_religion_std.getValue();
        });

        choiceBox_type_std.setItems(FXCollections.observableArrayList(
                "ATTACHED", "RESIDENT"));
        choiceBox_type_std.setValue((String) "ATTACHED");
        type_std = (String) choiceBox_type_std.getValue();
        choiceBox_type_std.setOnAction((event) -> {
            type_std = (String) choiceBox_type_std.getValue();
        });

        ///FOR TEACHER
        choiceBox_gender_teacher.setItems(FXCollections.observableArrayList(
                "MALE", "FEMALE"));
        choiceBox_gender_teacher.setValue((String) "MALE");
        gender_tcr = (String) choiceBox_gender_teacher.getValue();
        choiceBox_gender_teacher.setOnAction((event) -> {
            gender_tcr = (String) choiceBox_gender_teacher.getValue();
        });

        choiceBox_hallName_staff.setItems(FXCollections.observableArrayList(
                "SELECT A HALL", "AHSANULLAH", "SUHRAWARDY", "CHATRISOUTH", "CHATRINORTH"));
//        choiceBox_hallName_staff.setValue((String) "\"SELECT A HALL\"");
        hallName_staff = (String) choiceBox_hallName_staff.getValue();
        choiceBox_hallName_staff.setOnAction((event) -> {
            hallName_staff = (String) choiceBox_hallName_std.getValue();
        });

    }

}
