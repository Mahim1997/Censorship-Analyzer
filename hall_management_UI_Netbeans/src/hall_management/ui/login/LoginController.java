package hall_management.ui.login;

import com.jfoenix.controls.JFXButton;
import hall_management.config.DEBUG;
import hall_management.util.Interface.Type;
import static hall_management.util.Interface.Type.type_Student;
import static hall_management.util.Interface.Type.type_Teacher;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import hall_management.util.Interface.Controller;
import hall_management.ui.startPage.Main;
import hall_management.util.SceneLoader;
import hall_management.ui.popup.PopUpWindow;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import hall_management.db.queries.Checker;
import hall_management.ui.admin.AdminController;
import hall_management.ui.guest.GuestController;
 
import hall_management.ui.pushNotification.Notification;
import hall_management.ui.staff.StaffController;
import hall_management.ui.student.StudentController;
import hall_management.ui.teacher.TeacherController;

import hall_management.util.Interface.Scenes;
import javafx.geometry.Pos;

/**
 * FXML Controller class
 *
 * @author suban
 */
public class LoginController implements Initializable, Type, Controller {

    @FXML
    private JFXTextField text_userID;
    @FXML
    private JFXPasswordField text_password;
    @FXML
    private JFXButton button_signin;
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
        System.out.println("Inside LoginController.initialize() .. Login Page");

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
            case Type.type_Guest:{
                text_userType.setText("GUEST");
                break;
            }
        }
    }

    @FXML
    public void submitUserName() {
        userID = text_userID.getText().trim();
//        password = text_password.getText().trim();
        password = text_password.getText();

        if (DEBUG.isDEBUG_ON) {
            userID = DEBUG.DEBUG_LOGIN_ID();
            password = DEBUG.DEBUG_LOGIN_PASS();
        }

        if (userID.length() < 1 || password.length() < 1) {

            System.out.println(" :: Invalid Login :: ");
            userID = "";
            password = "";
            hasSubmittedLogin = false;

            if (userID.length() < 1) {
                text_userID.getStyleClass().add("wrong-credentials");
            }
            if (password.length() < 1) {
                text_password.getStyleClass().add("wrong-credentials");
            }
            Notification.push("Please provide valid credentials", "ID or Password cannot be empty", Notification.FAILURE, Pos.CENTER);

            return;
        }
        System.out.println("<><><>Inside submitUserName()... ");
        System.out.println("Type :: " + type);
        System.out.println("ID   :: " + userID);
        System.out.println("Pass :: " + password);
        System.out.println("<><><><><><><>Returning submitUserName()... ");

        hasSubmittedLogin = true;

        boolean doesUserNameExist = false;
        try {
            if (type == type_Student) {
                doesUserNameExist = Checker.doesThisIDExist(type_Student, userID);
            } else if (type == type_Teacher) {
                doesUserNameExist = Checker.doesThisIDExist(type, userID);
            } else if (type == type_Staff) {
                doesUserNameExist = Checker.doesThisIDExist(type, userID);
            }
            else if(type == type_Guest){
                doesUserNameExist = Checker.doesThisIDExist(type, userID);
            }
            else if(type==type_Admin){
                doesUserNameExist = userID.equals("admin");
            }

        } catch (SQLException ex) {
            PopUpWindow.displayInCheck("Error!!", "Inside LoginController... ID DOESNOT EXIST!");
        }
        if (doesUserNameExist == false) {
//            PopUpWindow.displayInCheck("Error", "Invalid ID");
            text_userID.getStyleClass().add("wrong-credentials");
            Notification.push("Failed to Log in", "Invalid ID", Notification.FAILURE, Pos.CENTER);

            return;
        }
        text_userID.getStyleClass().add("right-credentials");

        boolean doesPasswordMatch = false;

        try {
            if (type == type_Student) {
                doesPasswordMatch = Checker.doesPasswordMatch(type_Student, userID, password);
            }
            if (type == type_Teacher) {
                doesPasswordMatch = Checker.doesPasswordMatch(type_Teacher, userID, password);
            }
            if (type == type_Staff) {
                doesPasswordMatch = Checker.doesPasswordMatch(type_Staff, userID, password);
            }
            if(type == type_Guest){
                doesPasswordMatch = Checker.doesPasswordMatch(type_Guest, userID, password);
            }
            if(type == type_Admin){
                doesPasswordMatch = password.equals("admin");
            }
        } catch (Exception e) {
            System.out.println("=====----->>>> Inside ui_frontController.submitID().. PROBLEM");
            e.printStackTrace();
        }
        if (doesPasswordMatch == false) {
//            PopUpWindow.displayInCheck("Error", "Wrong Password");
            text_password.getStyleClass().add("wrong-credentials");
            Notification.push("Failed to Log in", "Wrong Password", Notification.FAILURE, Pos.CENTER);
            return;
        }
        text_userID.getStyleClass().add("right-credentials");
        Notification.push("Welcome", ":D " + "Successfully logged into the Hall Management System", Notification.SUCCESS);

        if (type == type_Student) {
            System.out.println(">>Inside ui_frontController.java ...Matched so make new Student Window... ");
//            SceneLoader.loadScene(SceneLoader.student_ui, this);
//            makeMain(SceneLoader.student_ui);
            Main.studentID = userID;
            StudentController stdContoController = (StudentController) SceneLoader.loadScene(Scenes.student_ui, this);
            stdContoController.setStudentInfo(userID);
            stdContoController.setCurrentPass(password);

        }

        ///EDITED NOW
        if (type == type_Teacher) {
//            System.out.println(">>Inside ui_frontController.java ... Matched so make new Teacher Window..");
//            makeMain("teacher_window.fxml");
            Main.teacherID = userID;
            SceneLoader.closeScene(SceneLoader.CurrentScene());
            TeacherController teacherController = (TeacherController) SceneLoader.loadScene(Scenes.teacher_ui, this);
            teacherController.setTeacherID(userID);
            teacherController.setCurrentPass(password);

        }

        if (type == type_Staff) {
            Main.staffID = userID;
            SceneLoader.closeScene(SceneLoader.CurrentScene());
            StaffController staffController = (StaffController) SceneLoader.loadScene(Scenes.staff_ui, this);
            staffController.setStaffId(userID);
            staffController.setCurrentPass(password);
        }
        
        if(type == type_Guest){
            System.out.println("==-->>Inside LoginController.submitUserName().. if(type==type_Guest).");
            Main.guestID = userID;
            SceneLoader.closeScene(SceneLoader.CurrentScene());
            GuestController guestController = (GuestController) SceneLoader.loadScene(Scenes.guest_ui, this);
            guestController.setGuestID(userID);
            guestController.setPassword(password);
        }
        if(type == type_Admin){
            System.out.println("==-->>Inside LoginController.submitUserName().. if(type==type_Guest).");
            Main.adminID = userID;
            SceneLoader.closeScene(SceneLoader.CurrentScene());
            AdminController adminController = (AdminController) SceneLoader.loadScene(Scenes.admin_ui, this);
            adminController.setAdminID(userID);
            adminController.setPassword(password);                        
        }

    }

    @FXML
    private void exitProg(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void backToStartPage(ActionEvent event) {
        SceneLoader.closeScene(SceneLoader.CurrentScene());
        SceneLoader.loadPreviousScene(Scenes.startPage_ui, this);
    }

}
