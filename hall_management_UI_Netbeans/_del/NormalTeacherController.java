/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hall_management.ui.teacher;

import hall_management.util.Interface.Controller;
import hall_management.ui.startPage.Main;
import hall_management.util.Interface.Scenes;
import hall_management.util.SceneLoader;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
 
public class NormalTeacherController implements Initializable, Controller {

    @FXML
    private Button button_seeEvents;
    @FXML
    private Button button_seeApplications;
    @FXML
    private TextField textLabel_teacherID;
    @FXML
    private TextField textLabel_fullName;
    @FXML
    private TextField textLabel_departmentID;
    @FXML
    private TextField textLabel_designation;
    @FXML
    private TextField textLabel_contactNumber;

    private String teacherId ;
    @FXML
    private Button button_logOut;
    @FXML
    private Button button_editContactNumber;
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        System.out.println("Teacher_windowController initialize().. ");
    }    

    @FXML
    private void seeEvents() {
        ///TO DO
        System.out.println("TO DO seeEvents().. ");
    }



    @FXML
    private void seeApplications() {
        System.out.println("TO DO seeApplications()... ");
    }

    public void setTeacherInfo(String userID) throws Exception {
        Main.teacherID = userID ;
        this.teacherId = userID; 
        refreshInfo();
    }
    public void refreshInfo() throws Exception{
        String[] infoTeacher = Teacher.getTeacherInfo(Main.teacherID);
        System.out.println("Num of cols = infoTeacher.size = " + infoTeacher.length);
        System.out.println("Printing infoTeacher... ");
        for(int i=0; i<infoTeacher.length; i++){
            System.out.println("-->> " + infoTeacher[i]);
        }
        textLabel_teacherID.setText(infoTeacher[Teacher.index_ID]);
        textLabel_fullName.setText(infoTeacher[Teacher.index_FullName]);
        textLabel_designation.setText(infoTeacher[Teacher.index_designation]);
        textLabel_departmentID.setText(infoTeacher[Teacher.index_departmentID]);
        textLabel_contactNumber.setText(infoTeacher[Teacher.index_contactNum]);
        setAllTextFieldsNotEditable();
    }
    private void setAllTextFieldsNotEditable(){
        textLabel_departmentID.setEditable(false);
        textLabel_designation.setEditable(false);
        textLabel_fullName.setEditable(false);
        textLabel_teacherID.setEditable(false);
        textLabel_contactNumber.setEditable(true);
    }
    @FXML
    private void logOut() {
        Controller loadScene = SceneLoader.loadScene(Scenes.startPage_ui, this);
    }

    @FXML
    private void editContactNumber() {
        String newContactNumber = textLabel_contactNumber.getText();
//        newContactNumber = 0 + newContactNumber ;
        System.out.println("-->>>>--->>> New contact num is " + newContactNumber);
//        int num = Controller.nullValue;
//        try{
//            num = Integer.parseInt(newContactNumber);
//        }catch(Exception e){
//            PopUpWindow.displayInCheck("Error", "Wrong Format Of Phone Number");
//        }
//        
        try {
            Teacher.updatePhoneNumber(newContactNumber);
            refreshInfo();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
    }
    


}
