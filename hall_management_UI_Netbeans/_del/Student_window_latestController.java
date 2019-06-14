/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hall_management.ui.student;

import hall_management.util.Controller;
import hall_management.ui.startPage.Main;
import hall_management.util.SceneLoader;
import hall_management.ui.popup.PopUpWindow;
import static hall_management.util.Interface.StudentColumns.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import hall_management.db.queries.Query;
import hall_management.db.queries.Student_Query;

public class Student_window_latestController implements Initializable, Controller {

    String studentId;
    @FXML
    private TextField textField_studentID;
    @FXML
    private ToggleButton toggleButton_edit;
    @FXML
    private TextField textField_fullname;
    @FXML
    private TextField textField_dept;
    @FXML
    private TextField textField_addr;
    @FXML
    private TextField textField_dateOfBirth;
    @FXML
    private TextField textField_religion;
    @FXML
    private TextField textField_fatherName;
    @FXML
    private TextField textField_motherName;
    @FXML
    private TextField textField_gender;
    @FXML
    private TextField textField_bldgrp;
    @FXML
    private TextField textField_contactNo;
    @FXML
    private Button button_save;

    Main mainClass;
    @FXML
    private MenuItem seeAllowedList_MenuItem;
    @FXML
    private MenuItem insertNewGuest_MenuItem;
    @FXML
    private Button button_addGuest;
    @FXML
    private Button button_logout;

    public void setMainClass(Main mainClass) {
        this.mainClass = mainClass;
    }

    public void setStudentInfo(String studentId) {
        this.studentId = studentId;
        System.out.println("Received StdID :: " + this.studentId);
        refreshInfo();

        //            this.student = new Student(this.studentId);
        refreshInfo();// Pass updated parameters
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Invoked args");

        refreshInfo();
        setTextFieldStatusEditable(false);
        button_save.setDisable(true);

    }

    @FXML
    public void seeAllowedGuestList() {
        System.out.println("Displaying ALLOWED GUEST LIST... ");
        SceneLoader.loadScene(Student_SeeGuestList.createSeeHallStudentsScene(studentId));
    }

    public void insertNewGuest() {
        System.out.println("To Do .. Insert New Guest. .. ");
    }

    public void seeGuestLog() {
        System.out.println("TO Do ... See Guest Log... ");
    }

    @FXML
    private void toggleEdit() {

        button_save.setDisable(!toggleButton_edit.isSelected());
        setTextFieldStatusEditable(toggleButton_edit.isSelected());
    }

    // Save Current Text Field's info to DB
    @FXML
    private void updateInfo() {
        ///Updating ... 
        String newAddress = textField_addr.getText();
        String newBirth_date = textField_dateOfBirth.getText();
//        String newBlood_group = textField_bldgrp.getText();
        String newContact_no = textField_contactNo.getText();
        String[] studentInfo = null;
        try {
            studentInfo = Query.getStudentInfo(this.studentId);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        if (newAddress.equals(studentInfo[addressIndex]) == false) {
            Student_Query.updateInfo(studentId, "ADDRESS", newAddress);
            //update address
        }
        if (newBirth_date.equals(studentInfo[birthDateIndex]) == false) {
            if (Student_Query.checkFormat(newBirth_date) == false) {
                PopUpWindow.displayInCheck("Error", "Enter date in DD/MM/YYYY");
                refreshInfo();
                return;
            }
            String s1 = "STR_TO_DATE('" + newBirth_date + "', 'dd/mm/yyyy')";
            Student_Query.updateInfo(studentId, "BIRTH_DATE", s1);
            //update birthdate
        }
        if (newContact_no.equals(studentInfo[contactNumIndex]) == false) {
            Student_Query.updateInfo(studentId, "CONTACT_NO", newContact_no);
            //update birth date
        }
        refreshInfo();

//        setAllTextEditable(false);
        button_save.setDisable(true);
        toggleEdit();
    }

    @FXML
    void addNewGuest() {
        Parent root = null;
        FXMLLoader loader = new FXMLLoader();
        Student_addGuestFormController guestFormController = (Student_addGuestFormController) SceneLoader.loadScene(SceneLoader.student_addGuestForm_ui, this);
        guestFormController.setStudentID(this.studentId);
        guestFormController.setMainClass(mainClass);

    }

    // Query and Load the current Info and Show it into textfields
    public void refreshInfo() {
//        setAllTextEditable(true);
        String[] studentInfo = null;
        try {
            studentInfo = Query.getStudentInfo(this.studentId);
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Inside refreshInfo().... ");
        for (String s : studentInfo) {
            System.out.print(s + " , ");
        }
        System.out.println("");

        System.out.println(this.studentId);
        textField_addr.setText(studentInfo[addressIndex]);
        textField_bldgrp.setText(studentInfo[bloodGrpIndex]);
        textField_contactNo.setText(studentInfo[contactNumIndex]);
        textField_dateOfBirth.setText(studentInfo[birthDateIndex]);
        textField_dept.setText(studentInfo[deptIndex]);
        textField_fatherName.setText(studentInfo[fatherIndex]);
        textField_fullname.setText(studentInfo[nameIndex]);
        textField_gender.setText(studentInfo[genderIndex]);
        textField_motherName.setText(studentInfo[motherIndex]);
        textField_religion.setText(studentInfo[religionIndex]);
        textField_studentID.setText(this.studentId);

//        setTextFieldStatusEditable(true);
//        setAllTextEditable(false);
    }

    void setTextFieldStatusEditable(boolean stat) {
        textField_addr.setEditable(stat);
        textField_bldgrp.setEditable(false);
        textField_contactNo.setEditable(stat);
        textField_dateOfBirth.setEditable(stat);
        textField_dept.setEditable(false);
        textField_fatherName.setEditable(false);
        textField_fullname.setEditable(false);
        textField_gender.setEditable(false);
        textField_motherName.setEditable(false);
        textField_religion.setEditable(false);
        textField_studentID.setEditable(false);
    }

    void setAllTextEditable(boolean stat) {
        textField_addr.setEditable(stat);
        textField_bldgrp.setEditable(stat);
        textField_contactNo.setEditable(stat);
        textField_dateOfBirth.setEditable(stat);
        textField_dept.setEditable(stat);
        textField_fatherName.setEditable(stat);
        textField_fullname.setEditable(stat);
        textField_gender.setEditable(stat);
        textField_motherName.setEditable(stat);
        textField_religion.setEditable(stat);
        textField_studentID.setEditable(stat);
    }

    @FXML
    private void exitToLogin() {
        SceneLoader.closeScene(SceneLoader.CurrentScene());
        SceneLoader.loadPreviousScene(SceneLoader.startPage_ui, this);
    }
}
