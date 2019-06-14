/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hall_management.ui;

import Config.DEBUG;
import hall_management.ui.popup.PopUpWindow;
import Interface.Type;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import queries.Checker;
import queries.Query;

public class loginController implements Initializable, Type, Controller {

    
    @FXML
    private JFXTextField text_userID;
    @FXML
    private JFXPasswordField text_password;
    @FXML
    private Button button_signin;
    @FXML
    private Text text_userType;

    
    String userID, password;
    Main mainClass;

    int type = -1;// 0-std, 1-teacher, 2-staff
    boolean hasSubmittedLogin = false;

    public boolean hasValidLogin() {
        return hasSubmittedLogin;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Login Page");

//        button_select_login_std.setSelected(false);
//        button_select_login_tchr.setSelected(false);
//        button_select_login_staff.setSelected(false);
//
//        text_userID.setDisable(true);
//        text_password.setDisable(true);
//
//        button_signin.setDisable(true);
    }

    public String getUserID() {
        if (!hasSubmittedLogin) {
            return null;
        }
        return userID;
    }

    public String getPassword() {
        if (!hasSubmittedLogin) {
            return null;
        }
        return password;
    }

    public void setMain(Main main) {
        this.mainClass = main;

    }

    public void setType(int type) {
        this.type = type;
        switch (this.type) {
            case Type.type_Student: {
                text_userType.setText("STUDENT");
                break;
            }
            case Type.type_Teacher: {
                text_userType.setText("TEACHER");
                break;
            }
            case Type.type_Staff: {
                text_userType.setText("STAFF");
                break;
            }
            case Type.type_Admin: {
                text_userType.setText("ADMINISTRATOR");
                break;
            }
        }
    }

    @FXML
    void submitUserName() {
        userID = text_userID.getText().trim();
        password = text_password.getText().trim();

        if(DEBUG.isDEBUG_ON){
            userID = DEBUG.DEBUG_LOGIN_ID();
            password = DEBUG.DEBUG_LOGIN_PASS();
        }
        
        if (userID.length() < 1 || password.length() < 1) {
            System.out.println(" :: Invalid Login :: ");
            userID = "";
            password = "";
            hasSubmittedLogin = false;
            return;
        }

        System.out.println("Type :: " + type);
        System.out.println("ID   :: " + userID);
        System.out.println("Pass :: " + password);

        hasSubmittedLogin = true;

        boolean doesUserNameExist = false;
        try {
            if (type == type_Student) {
                doesUserNameExist = Checker.doesThisIDExist(type_Student, userID);
            } else if (type == type_Teacher) {
                doesUserNameExist = Checker.doesThisIDExist(type, userID);
            }
        } catch (SQLException ex) {
            PopUpWindow.displayInCheck("Error", "Cant connect to DB");
        }
        if (doesUserNameExist == false) {
            PopUpWindow.displayInCheck("Error", "Invalid ID");
            return;
        }

        boolean doesPasswordMatch = false;

        try {
            if (type == type_Student) {
                doesPasswordMatch = Checker.doesPasswordMatch(type_Student, userID, password);
            }
            if (type == type_Teacher) {
                doesPasswordMatch = Checker.doesPasswordMatch(type_Teacher, userID, password);
            }
        } catch (Exception e) {
            System.out.println("=====----->>>> Inside ui_frontController.submitID().. PROBLEM");
            e.printStackTrace();
        }
        if (doesPasswordMatch == false) {
            PopUpWindow.displayInCheck("Error", "Wrong Password");
            return;
        }
        if (type == type_Student) {
            System.out.println(">>Inside ui_frontController.java ...Matched so make new Student Window... ");
//            makeMain(SceneLoader.student_ui);
            Student_window_latestController stdContoController = (Student_window_latestController) SceneLoader.loadScene(SceneLoader.student_ui, this);
            stdContoController.setStudentInfo(userID);
            Main.studentID = userID;
        }

        ///EDITED NOW
        if (type == type_Teacher) {
//            System.out.println(">>Inside ui_frontController.java ... Matched so make new Teacher Window..");
//            makeMain("teacher_window.fxml");
            Main.teacherID = userID;
//            SceneLoader.loadScene(teacher_ui, this);
            boolean flag = false;
            try {
                flag = Query.isThisTeacherHallHead(userID);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (flag == true) {
                System.out.println("YES THIS IS HALL HEAD... ");
                Teacher_windowController teacherController = (Teacher_windowController) SceneLoader.loadScene(SceneLoader.teacher_ui, this);
                try {
                    teacherController.setTeacherInfo(userID);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else {
                System.out.println("No this is not hall head");
                NormalTeacherController teacherController = (NormalTeacherController) SceneLoader.loadScene(SceneLoader.normalTeacher_ui, this);
                try {
                    teacherController.setTeacherInfo(userID);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
//            Main.studentID = userID ;
//            SceneLoader.loadScene(Teacher.getinitialScene());
    }
//        System.out.println("==>>>Inside submitUserName... Need to check for ID and Pass... ! ><><><><><><><>< ");

    @FXML
    private void exitProg(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void backToStartPage(ActionEvent event) {
        SceneLoader.closeScene(SceneLoader.CurrentScene());
        SceneLoader.loadPreviousScene(SceneLoader.startPage_ui, this);
    }
}
