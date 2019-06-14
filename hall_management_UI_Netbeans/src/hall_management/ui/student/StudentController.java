package hall_management.ui.student;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import hall_management.db.queries.Application_Queries;
import hall_management.db.queries.Query;
import hall_management.db.queries.Student_Query;
import hall_management.db.queries.Update_Query;
import hall_management.ui.pushNotification.Notification;
import hall_management.ui.settings.SettingsController;
import hall_management.ui.startPage.Main;
import hall_management.ui.student.applySeat.ApplySeatFormController;
import hall_management.util.Interface.Controller;
import hall_management.util.Interface.Scenes;
import static hall_management.util.Interface.StudentColumns.addressIndex;
import static hall_management.util.Interface.StudentColumns.birthDateIndex;
import static hall_management.util.Interface.StudentColumns.bloodGrpIndex;
import static hall_management.util.Interface.StudentColumns.contactNumIndex;
import static hall_management.util.Interface.StudentColumns.deptIndex;
import static hall_management.util.Interface.StudentColumns.fatherIndex;
import static hall_management.util.Interface.StudentColumns.genderIndex;
import static hall_management.util.Interface.StudentColumns.motherIndex;
import static hall_management.util.Interface.StudentColumns.nameIndex;
import static hall_management.util.Interface.StudentColumns.religionIndex;
import hall_management.util.Interface.Table;
import hall_management.util.SceneLoader;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.Tab;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class StudentController implements Initializable, Controller {

    @FXML
    private JFXButton button_loadProfile;
    @FXML
    private JFXButton button_loadHall;
    @FXML
    private JFXButton button_loadGuest;
    @FXML
    private JFXButton button_loadEvents;
    @FXML
    private JFXButton button_loadTeams;
    @FXML
    private JFXButton button_loadSettings;
    @FXML
    private JFXTabPane tabPane;

    JFXButton[] sceneButtons = null;

    String studentId;
    String currentPass;
    Main mainClass;

    @FXML
    private JFXTextField textField_studentID;
    @FXML
    private JFXTextField textField_fullname;
    @FXML
    private JFXTextField textField_dept;
    @FXML
    private JFXTextField textField_addr;
    @FXML
    private JFXTextField textField_fatherName;
    @FXML
    private JFXTextField textField_motherName;
    @FXML
    private JFXTextField textField_contactNo;
    @FXML
    private SplitMenuButton textField_religion;
    @FXML
    private SplitMenuButton textField_gender;
    @FXML
    private SplitMenuButton textField_bldgrp;
    @FXML
    private JFXButton button_refresh;
    @FXML
    private JFXButton button_applySeat;
    @FXML
    private JFXTextField textField_dateOfBirth_day;
    @FXML
    private JFXTextField textField_dateOfBirth_month;
    @FXML
    private JFXTextField textField_dateOfBirth_year;
    @FXML
    private FontAwesomeIconView seeGuestList_;
    @FXML
    private Text text_HallName;

    @FXML
    private JFXTextField label_residentType;
    @FXML
    private JFXTextField label_currentRoomNo;
    @FXML
    private JFXTextField label_allocationDate;
    @FXML
    private JFXButton button_save;
    @FXML
    private JFXButton button_addNewGuest;
    @FXML
    private JFXToggleButton toggleButton_edit;
    @FXML
    private JFXButton button_seeGuest;
//    @FXML
//    private Tab tab_prof;
//    @FXML
//    private Tab tab_hall;
//    @FXML
//    private Tab tab_guest;
//    @FXML
//    private Tab tab_events;
//    @FXML
//    private Tab tab_teams;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        refreshInfo();
        sceneButtons = new JFXButton[TAB_SIZE];

        sceneButtons[TAB_INDEX_Profile] = button_loadProfile;
        sceneButtons[TAB_INDEX_Hall] = button_loadHall;
        sceneButtons[TAB_INDEX_Guests] = button_loadGuest;
        sceneButtons[TAB_INDEX_Events] = button_loadEvents;
        sceneButtons[TAB_INDEX_Teams] = button_loadTeams;
        sceneButtons[TAB_INDEX_Settings] = button_loadSettings;

        highlightButton(TAB_INDEX_Profile);

        setEditable(false);
        button_save.setDisable(true);

        refreshInfo();
//        Platform.runLater(()->{
//            highlightButton(tabPane.getSelectionModel().getSelectedIndex());
//        });
    }

    public void setMainClass(Main mainClass) {
        this.mainClass = mainClass;
    }

    public void setStudentInfo(String studentId) {
        this.studentId = studentId;
        System.out.println("Inside setStudentInfo ... Received StdID :: " + Main.studentID);
//        refreshInfo();

        //            this.student = new Student(this.studentId);
        refreshInfo();// Pass updated parameters
    }

    public void setCurrentPass(String currentPass) {
        this.currentPass = currentPass;
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
//        if (tabPane.getSelectionModel().getSelectedIndex() != TAB_INDEX_Profile) {
        tabPane.getSelectionModel().select(TAB_INDEX_Profile);
//        }
        highlightButton(TAB_INDEX_Profile);
    }

    @FXML
    private void loadHall() throws Exception {
        Student student = new Student();
        tabPane.getSelectionModel().select(TAB_INDEX_Hall);

        student = Query.getStudentHallRelatedInfo(Main.studentID);
        text_HallName.setText(student.getHallName());
        label_residentType.setText(student.getType());
        label_currentRoomNo.setText(student.getCurrentRoomNumber());
        label_allocationDate.setText(student.getAllocationDate());

        highlightButton(TAB_INDEX_Hall);

    }

    @FXML
    private void loadGuest() {
        tabPane.getSelectionModel().select(TAB_INDEX_Guests);
        highlightButton(TAB_INDEX_Guests);
    }

    @FXML
    private void loadEvents() {
        tabPane.getSelectionModel().select(TAB_INDEX_Events);
        highlightButton(TAB_INDEX_Events);
    }

    @FXML
    private void loadTeams() {
        tabPane.getSelectionModel().select(TAB_INDEX_Teams);
        highlightButton(TAB_INDEX_Teams);
    }

    @FXML
    private void logOutSession() {
        SceneLoader.closeScene(SceneLoader.CurrentScene());
        SceneLoader.loadPreviousScene(Scenes.startPage_ui, this);
    }

    // Query and Load the current Info and Show it into textfields
    @FXML
    public void refreshInfo() {
//        setAllTextEditable(true);
        String[] studentInfo = null;

        try {
            studentInfo = Query.getStudentInfo(Main.studentID);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        System.out.println("<><><> Inside StudentController... refreshInfo().... ");
        for (String s : studentInfo) {
            System.out.print(s + " , ");
        }
        System.out.println("<><><><>");

//        System.out.println(this.studentId);
        textField_addr.setText(studentInfo[addressIndex]);
        textField_bldgrp.setText(studentInfo[bloodGrpIndex]);
        textField_contactNo.setText(studentInfo[contactNumIndex]);
        System.out.println(studentInfo[birthDateIndex]);
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-mm-yyyy");
//        System.out.println(LocalDate.now());//parse(studentInfo[birthDateIndex], formatter)
//        System.out.println(LocalDate.parse("06-01-1994", formatter));//
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        formatter.setLenient(false);
        String dateInString = studentInfo[birthDateIndex];

        Date date = null;
        try {
            date = formatter.parse(dateInString);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }

        textField_dateOfBirth_day.setText(String.valueOf(date.getDate()));
        textField_dateOfBirth_month.setText(String.valueOf(date.getMonth() + 1));
        textField_dateOfBirth_year.setText(String.valueOf(date.getYear() + 1900));

        textField_dept.setText(studentInfo[deptIndex]);
        textField_fatherName.setText(studentInfo[fatherIndex]);
        textField_fullname.setText(studentInfo[nameIndex]);
        textField_gender.setText(studentInfo[genderIndex]);
        textField_motherName.setText(studentInfo[motherIndex]);
        textField_religion.setText(studentInfo[religionIndex]);

        textField_studentID.setText(Main.studentID);

        setEditButtonOn(false);
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
    final int TAB_INDEX_Guests = 2;
    final int TAB_INDEX_Events = 3;
    final int TAB_INDEX_Teams = 4;
    final int TAB_INDEX_Settings = 5;
    final int TAB_SIZE = 6;

    @FXML
    private void applyForSeat(ActionEvent event) throws Exception {
//        SceneLoader.loadScene(Scenes.student_newGuestForm, this);
        boolean flag = Application_Queries.checkWhetherStudentHasPendingApplications(Main.studentID);
        if(flag == true){
            Notification.push("WARNING!", "YOU ALREADY HAVE PENDING NOTIFICATIONS.. PLEASE WAIT FOR TEACHER!", Notification.WARNING);
            return;
        }
        ApplySeatFormController apc = (ApplySeatFormController) SceneLoader.loadScene(Scenes.student_application_ui, this);
        apc.setStudentID(studentId);
    }

    @FXML
    private void seeAppLog(ActionEvent event) {
        SceneLoader.loadScene(Scenes.student_see_application_ui, this);
    }

    @FXML
    private void seeGuestList() {
//        System.out.println("<><><><>Inside .. seeGuestList()...");

//        SceneLoader.loadScene(Student_SeeGuestList.createSeeHallStudentsScene(Main.studentID));
        SceneLoader.loadScene(Scenes.student_see_guest_list, this);
//        System.out.println("Returning seeGuestList().. <><><><>");
    }

    @FXML
    private void submitInfoToUpdate() throws Exception {
        String newName = textField_fullname.getText();

        String[] fullName = newName.split(" ");
        String firstName = null;
        String lastName = null;
        if (fullName.length > 1) {
            firstName = fullName[0];
            lastName = fullName[1];
        }

        String newAddress = textField_addr.getText();

        String newBirthDAY = textField_dateOfBirth_day.getText();
        String newBirthMON = textField_dateOfBirth_month.getText();
        String newBirthYEAR = textField_dateOfBirth_year.getText();

        String newReligion = textField_religion.getText();

        String newFatherName = textField_fatherName.getText();
        String newMotherName = textField_motherName.getText();

        String newGender = textField_gender.getText();

        String newBloodGrp = textField_bldgrp.getText();

        String newContactNum = textField_contactNo.getText();

        System.out.println("<>Inside StudentController.submitInfoToUpdate() .... ");
        Student_Query.updateInfo(firstName, lastName, newBirthDAY, newBirthMON, newBirthYEAR, newReligion, newFatherName, newMotherName,
                newGender, newBloodGrp, newContactNum, newAddress);
        System.out.println("<><><>Now refreshingInfo ... <><><> \n");

        refreshInfo();
        setEditable(false);
    }

    @FXML
    private void editInfo() {
        System.out.println("==>>>INSIDE editInfo().. ");
        setEditButtonOn(toggleButton_edit.isSelected());
    }

    private void setEditButtonOn(boolean stat) {
        toggleButton_edit.setSelected(stat);
        setEditable(toggleButton_edit.isSelected());
        button_save.setDisable(!toggleButton_edit.isSelected());
    }

    private void setEditable(boolean flag) {
        textField_dateOfBirth_day.setEditable(flag);
        textField_dateOfBirth_month.setEditable(flag);
        textField_dateOfBirth_year.setEditable(flag);

//        textField_dept.setEditable(false); //CANNOT EDIT DEPARTMENT ID
        textField_fatherName.setEditable(flag);
        textField_fullname.setEditable(flag);
//        textField_gender.setEditable(flag);
        textField_motherName.setEditable(flag);
        textField_contactNo.setEditable(flag);
        textField_addr.setEditable(flag);
//        textField_religion.setEditable(flag);

//        textField_studentID.setEditable(false);//CANNOT EDIT STUDENT ID
    }

    @FXML
    private void addNewGuest() {
        SceneLoader.loadScene(Scenes.student_newGuestForm, this);
    }

    private void setTabSelectionModel() {
//        tab_prof.set
    }

    void changePassWindow() {
        Stage newWindow = new Stage();
        newWindow.initModality(Modality.APPLICATION_MODAL);

        Parent root = null;
        FXMLLoader loader = new FXMLLoader();
        SettingsController controller = null;
        System.out.println("===>>Inside StudentController .. loadScene() Trying to Load  " + Scenes.settings_panel);
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
                Update_Query.updatePassword(Table.Student, Main.studentID, newPass);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @FXML
    private void seeParticipatedEvents( ) {
        SceneLoader.loadSceneInADifferentWindow(Scenes.students_events_participated, this);
    }

    @FXML
    private void form_a_team( ) {
//        SceneLoader.closeScene(SceneLoader.CurrentScene());
        SceneLoader.loadScene(Scenes.student_form_a_team, this);
    }

    @FXML
    private void see_upcomingEvents( ) {
        SceneLoader.loadSceneInADifferentWindow(Scenes.student_see_upcoming_events, this);
    }

    @FXML
    private void seeTeamsOfUpcomingEvents() {
        SceneLoader.loadSceneInADifferentWindow(Scenes.student_see_teams_upcoming_events, this);
    }

}
