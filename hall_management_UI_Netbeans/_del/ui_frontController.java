package hall_management_ui;

import Enhancements.PopUpWindow;
import Interface.Type;
import static hall_management_ui.SceneLoader.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import queries.Checker;
import queries.Query;

public class ui_frontController implements Initializable, Type, Controller {

    String userID, password;

    @FXML
    private ToggleButton button_select_login_std;
    @FXML
    private ToggleButton button_select_login_tchr;
    @FXML
    private ToggleButton button_select_login_staff;
    @FXML
    private TextField text_userID;
    @FXML
    private PasswordField text_password;
    @FXML
    private Button button_signin;

//    private void handleButtonAction(ActionEvent event) {
//        //System.out.println("You clicked me!");
//        //label.setText("Hello World!");
//    }
    Main mainClass;

    int type = -1;// 0-std, 1-teacher, 2-staff
    boolean hasSubmittedLogin = false;

    public boolean hasValidLogin() {
        return hasSubmittedLogin;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Invoked args");

        button_select_login_std.setSelected(false);
        button_select_login_tchr.setSelected(false);
        button_select_login_staff.setSelected(false);

        text_userID.setDisable(true);
        text_password.setDisable(true);

        button_signin.setDisable(true);
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

    public void initialize() {
        System.out.println("Invoked empty one");
    }

    public void setMain(Main main) {
        this.mainClass = main;

    }

    @FXML
    private void selectType_student() {
        button_select_login_std.setSelected(!button_select_login_std.isSelected());
        button_select_login_tchr.setSelected(false);
        button_select_login_staff.setSelected(false);

        text_userID.setDisable(!button_select_login_std.isSelected());
        text_password.setDisable(!button_select_login_std.isSelected());

        button_signin.setDisable(!button_select_login_std.isSelected());

        if (button_select_login_std.isSelected()) {
//            type = 0 ;
            type = type_Student;
        }
    }

    @FXML
    private void selectType_teacher() {
        button_select_login_std.setSelected(false);
        button_select_login_tchr.setSelected(!button_select_login_tchr.isSelected());
        button_select_login_staff.setSelected(false);

        text_userID.setDisable(!button_select_login_tchr.isSelected());
        text_password.setDisable(!button_select_login_tchr.isSelected());

        button_signin.setDisable(!button_select_login_tchr.isSelected());

        if (button_select_login_tchr.isSelected()) {
//            type=1;
            type = type_Teacher;
        }
    }

    @FXML
    private void selectType_staff() {
        button_select_login_std.setSelected(false);
        button_select_login_tchr.setSelected(false);
        button_select_login_staff.setSelected(!button_select_login_staff.isSelected());

        text_userID.setDisable(!button_select_login_staff.isSelected());
        text_password.setDisable(!button_select_login_staff.isSelected());

        button_signin.setDisable(!button_select_login_staff.isSelected());

        if (button_select_login_staff.isSelected()) {
//            type=2;
            type = type_Staff;
        }
    }

    @FXML
    void submitUserName() {
        userID = text_userID.getText().trim();
        password = text_password.getText().trim();

//        userID = "7000";
//        password = "1432";
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
}
