/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hall_management.ui.teacher;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import hall_management.db.queries.Query;
import hall_management.db.queries.Update_Query;
import hall_management.ui.popup.PopUpWindow;
import hall_management.ui.settings.SettingsController;
import hall_management.ui.startPage.Main;
import hall_management.util.Interface.Controller;
import hall_management.util.Interface.Scenes;
import hall_management.util.Interface.Table;
import hall_management.util.SceneLoader;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author suban
 */
public class TeacherController implements Initializable, Controller {

    @FXML
    private JFXTabPane tabPane;
    @FXML
    private JFXTextField textField_gender;
    @FXML
    private JFXButton button_save;
    @FXML
    private JFXButton button_refresh;
    @FXML
    private JFXToggleButton toggleButton_edit;
    @FXML
    private Tab label_Hall;
    @FXML
    private Text text_HallName;
    @FXML
    private JFXButton button_loadProfile;
    @FXML
    private JFXButton button_loadHall;
    @FXML
    private JFXButton button_loadEvents;
    @FXML
    private JFXButton button_loadSettings;
    @FXML
    private JFXTextField textLabel_teacherID;
    @FXML
    private JFXTextField textLabel_fullName;
    @FXML
    private JFXTextField textLabel_departmentID;
    @FXML
    private JFXTextField textLabel_designation;
    @FXML
    private JFXTextField textLabel_contactNumber;
    @FXML
    private JFXButton button_hallDetail;
    @FXML
    private JFXButton button_applications;

    JFXButton[] sceneButtons = null;
    private int counter = 0;
    String teacherID = null;
    String currentPass;

    private String new_first_name;
    private String new_last_name;
    private String new_contact_no;

    boolean hallHead;

    public String getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(String teacherID) {
        this.teacherID = teacherID;
        refreshInfo();
        loadHallManagerInfo();
        label_Hall.setDisable(!isHallHead());
        button_loadHall.setDisable(!isHallHead());

        if (!isHallHead()) {
            TAB_INDEX_Events -= 1;
            TAB_INDEX_Settings -= 1;
            TAB_SIZE -= 1;
            tabPane.getTabs().remove(TAB_INDEX_Hall);
        }
    }

    public void setCurrentPass(String currentPass) {
        this.currentPass = currentPass;
    }

    public boolean isHallHead() {
        return hallHead;
    }

    public void setHallHead(boolean hallHead) {
        this.hallHead = hallHead;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        setTeacherID(Main.teacherID);
        System.out.println("Teacher COntroller Init");

        sceneButtons = new JFXButton[TAB_SIZE];

        sceneButtons[TAB_INDEX_Profile] = button_loadProfile;
        sceneButtons[TAB_INDEX_Hall] = button_loadHall;
        sceneButtons[TAB_INDEX_Events] = button_loadEvents;
        sceneButtons[TAB_INDEX_Settings] = button_loadSettings;

        highlightButton(TAB_INDEX_Profile);

        button_save.setDisable(true);
        setAllTextFieldsEditable(false);
        refreshInfo();
    }

    private boolean areAllFieldsComplete() {
        boolean flag = false;
        flag = new_contact_no.equals("") || new_contact_no == null || new_first_name.equals("") || new_first_name == null
                || new_last_name.equals("") || new_last_name == null;

        return !flag;
    }

    @FXML
    private void submitInfoToUpdate() throws Exception {
        System.out.println("<><><><><><>Inside TeacherController.submitInfoToUpdate().... ");

        new_contact_no = textLabel_contactNumber.getText();
        String newFullName = textLabel_fullName.getText();
        String[] names = newFullName.split(" ");
        if (names.length < 2) {
            PopUpWindow.displayInCheck("ERROR!!", "Name Format Wrong!!");
            return;
        }
        new_first_name = names[0];
        new_last_name = names[1];
        if (areAllFieldsComplete() == false) {
            PopUpWindow.displayInCheck("ERROR", "All Fields Must Be Complete");
            return;
        }

        Update_Query.updateInfo(Table.Teacher, "CONTACT_NO", new_contact_no, Main.teacherID);
        Update_Query.updateInfo(Table.Teacher, "FIRST_NAME", new_first_name, Main.teacherID);
        Update_Query.updateInfo(Table.Teacher, "LAST_NAME", new_last_name, Main.teacherID);

        System.out.println("submitInfoToUpdate().. DONE!! Now RefreshInfo called.. ");
        refreshInfo();
        System.out.println("<><><><><>\n\n");
    }

    @FXML
    private void refreshInfo() {
        String[] infoTeacher = null;
        try {
            infoTeacher = Teacher.getTeacherInfo(Main.teacherID);
        } catch (SQLException ex) {
            Logger.getLogger(TeacherController.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Num of cols = infoTeacher.size = " + infoTeacher.length);
        System.out.println("Printing infoTeacher... ");
        for (String infoTeacher1 : infoTeacher) {
            System.out.println("-->> " + infoTeacher1);
        }
        textLabel_teacherID.setText(infoTeacher[Teacher.index_ID]);
        textLabel_fullName.setText(infoTeacher[Teacher.index_FullName]);
        textLabel_designation.setText(infoTeacher[Teacher.index_designation]);
        textLabel_departmentID.setText(infoTeacher[Teacher.index_departmentID]);
        textLabel_contactNumber.setText(infoTeacher[Teacher.index_contactNum]);
        textField_gender.setText(infoTeacher[Teacher.index_Gender]);

        setAllTextFieldsEditable(false);
        toggleButton_edit.setSelected(false);
    }

    @FXML
    private void editInfo() {
        setAllTextFieldsEditable(toggleButton_edit.isSelected());
//        System.out.println("<><><>Inside editInfo...count =  " + counter);
//        counter++;
        boolean flag = toggleButton_edit.isSelected();

        if (!flag) {
            return;
        }

//        System.out.println("==-->> NOW QUERY WILL BE EXECUTED!!!");
//        System.out.println("<><><><>\n\n");
    }

    @FXML
    private void loadSettings() {
        int prevSelTabIndex = tabPane.getSelectionModel().getSelectedIndex();

        highlightButton(TAB_INDEX_Settings);

        System.out.println("Load Settings Window");
        changePassWindow();

        highlightButton(prevSelTabIndex);
    }

    @FXML
    private void loadProfile() {
        tabPane.getSelectionModel().select(TAB_INDEX_Profile);
        highlightButton(TAB_INDEX_Profile);
    }

    @FXML
    private void loadHall() throws Exception {

        if (!isHallHead()) {
            return;
        }

        tabPane.getSelectionModel().select(TAB_INDEX_Hall);

        highlightButton(TAB_INDEX_Hall);

    }

    @FXML
    private void loadEvents() {
        tabPane.getSelectionModel().select(TAB_INDEX_Events);
        highlightButton(TAB_INDEX_Events);
    }

    @FXML
    private void logOutSession() {
        SceneLoader.closeScene(SceneLoader.CurrentScene());
        SceneLoader.loadPreviousScene(Scenes.startPage_ui, this);
    }

    // to be updated
    @FXML
    private void seeHallStudents() {
//        Scene scene = Teacher_clickSeeHallStudents.createSeeHallStudentsScene();
//        Main.primaryStage.setTitle("Students of Hall!!");
//        SceneLoader.loadScene(scene);
        SceneLoader.loadScene(Scenes.teacher_see_students_hall, this);
    }

    // to be implemented
    @FXML
    private void seeApplications() {
        SceneLoader.loadScene(Scenes.teacher_see_Applications, this);
    }

    void changePassWindow() {
        Stage newWindow = new Stage();
        newWindow.initModality(Modality.APPLICATION_MODAL);

        Parent root = null;
        FXMLLoader loader = new FXMLLoader();
        SettingsController controller = null;
        System.out.println("===>>Inside TeacherController .. loadScene() Trying to Load  " + Scenes.settings_panel);
        try {
            loader.setLocation(this.getClass().getResource(Scenes.settings_panel));
            root = loader.load();
            controller = (SettingsController) loader.getController();
        } catch (Exception ex2) {
            ex2.printStackTrace();
        }

        Scene scene = new Scene(root);

        newWindow.setScene(scene);

        controller.setOldPass(currentPass);
        controller.setCurrentStage(newWindow);

        newWindow.showAndWait();

        if (controller.isPassUpdated()) {
            // Update COdes here
            String newPass = controller.getNewPass();
            try {
                Update_Query.updatePassword(Table.Teacher, Main.teacherID, newPass);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    void loadHallManagerInfo() {
        try {
            String[] arr = Query.findHallIDAndName(teacherID);
//        String hall_id = arr[0];
            String hall_name = arr[1];
            System.out.println("Administrated Hall Name :: " + hall_name);
            if (hall_name == null) {
                hall_name = "<NOT HEAD OF HALL>";
                hallHead = false;

            } else {
                hallHead = true;
            }
            text_HallName.setText(hall_name);
        } catch (Exception ex) {
//            Logger.getLogger(TeacherController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void setAllTextFieldsEditable(boolean stat) {
        textLabel_departmentID.setEditable(false);
        textLabel_designation.setEditable(false);
        textLabel_fullName.setEditable(stat);
        textLabel_teacherID.setEditable(false);
        textLabel_contactNumber.setEditable(stat);
        textField_gender.setEditable(false);
        button_save.setDisable(!stat);
    }

    private void setButtonPropertiesDefault() {
        for (int i = 0; i < TAB_SIZE; i++) {
            sceneButtons[i].getStyleClass().remove("toolbar-button-selected");
            sceneButtons[i].getStyleClass().add("toolbar-button");
//            sceneButtons[i].getStyleClass().add("button:hover");
        }
    }

    private void highlightButton(int buttonIndex) {
        setButtonPropertiesDefault();
        sceneButtons[buttonIndex].getStyleClass().remove("toolbar-button");
//        sceneButtons[buttonIndex].getStyleClass().remove("button:hover");
        sceneButtons[buttonIndex].getStyleClass().add("toolbar-button-selected");
//        sceneButtons[buttonIndex].setStyle("-fx-background-color: "+" -fx-blue-light");

    }

    final int TAB_INDEX_Profile = 0;
    final int TAB_INDEX_Hall = 1;
    int TAB_INDEX_Events = 2;
    int TAB_INDEX_Settings = 3;
    int TAB_SIZE = 4;

    @FXML
    private void seeEventsSuperviesdByYou() {
//        SceneLoader.closeScene(SceneLoader.CurrentScene());
        SceneLoader.loadSceneInADifferentWindow(Scenes.teacher_seeEvents, this);
    }

    @FXML
    private void manageUpComingEvents() {
        SceneLoader.loadSceneInADifferentWindow(Scenes.teacher_manage_upcoming_events, this);
    }
}
