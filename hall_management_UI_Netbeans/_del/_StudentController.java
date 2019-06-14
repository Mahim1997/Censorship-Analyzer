package hall_management.ui.student;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import hall_management.db.queries.Query;
import hall_management.db.queries.Student_Query;
import hall_management.ui.startPage.Main;
import hall_management.util.Controller;
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
import hall_management.util.SceneLoader;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.Tab;
import javafx.scene.text.Text;

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
    private Tab label_Hall;
    @FXML
    private JFXButton button_edit;
    @FXML
    private JFXButton button_save;
    @FXML
    private JFXButton button_addNewGuest;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sceneButtons = new JFXButton[TAB_SIZE];

        sceneButtons[TAB_INDEX_Profile] = button_loadProfile;
        sceneButtons[TAB_INDEX_Hall] = button_loadHall;
        sceneButtons[TAB_INDEX_Guests] = button_loadGuest;
        sceneButtons[TAB_INDEX_Events] = button_loadEvents;
        sceneButtons[TAB_INDEX_Teams] = button_loadTeams;
        sceneButtons[TAB_INDEX_Settings] = button_loadSettings;

        highlightButton(TAB_INDEX_Profile);

        setEditable(false);
        refreshInfo();
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

    @FXML
    private void loadSettings() {
        int prevSelTabIndex = tabPane.getSelectionModel().getSelectedIndex();

        highlightButton(TAB_INDEX_Settings);

        System.out.println("Load Settings Window");

        highlightButton(prevSelTabIndex);
    }

    @FXML
    private void loadProfile() {
        tabPane.getSelectionModel().select(TAB_INDEX_Profile);
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
        SceneLoader.loadPreviousScene(SceneLoader.startPage_ui, this);
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
        setEditable(false);

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
    private void applyForSeat(ActionEvent event) {

    }

    @FXML
    private void seeAppLog(ActionEvent event) {

    }

    @FXML
    private void seeGuestList() {
        System.out.println("<><><><>Inside .. seeGuestList()...");

        SceneLoader.loadScene(Student_SeeGuestList.createSeeHallStudentsScene(Main.studentID));

        System.out.println("Returning seeGuestList().. <><><><>");
    }

    @FXML
    private void submitInfoToUpdate() throws Exception {
        String newName = textField_fullname.getText();
        
        String []fullName = newName.split(" ");
        String firstName = null; String lastName = null;
        if(fullName.length > 1){
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
        Student_Query.updateInfo(firstName, lastName, newBirthDAY, newBirthMON, newBirthYEAR, newReligion, newFatherName, newMotherName
        , newGender, newBloodGrp, newContactNum, newAddress);
        System.out.println("<><><>Now refreshingInfo ... <><><> \n");
        
        refreshInfo();
        setEditable(false);
    }
    
    @FXML
    private void editInfo() {
        System.out.println("==>>>INSIDE editInfo().. ");
        setEditable(true);
    }

    private void setEditable(boolean flag) {
        textField_dateOfBirth_day.setEditable(flag);
        textField_dateOfBirth_month.setEditable(flag);
        textField_dateOfBirth_year.setEditable(flag);

        textField_dept.setEditable(false); //CANNOT EDIT DEPARTMENT ID
        textField_fatherName.setEditable(flag);
        textField_fullname.setEditable(flag);
//        textField_gender.setEditable(flag);
        textField_motherName.setEditable(flag);
        textField_contactNo.setEditable(flag);
        textField_addr.setEditable(flag);
//        textField_religion.setEditable(flag);

        textField_studentID.setEditable(false);//CANNOT EDIT STUDENT ID
    }

    @FXML
    private void addNewGuest( ) {
        System.out.println("<><>Inside StudentControllerClass ... ADD NEW GUEST!!");
        SceneLoader.loadScene(SceneLoader.student_newGuestForm, this);
//        SceneLoader.loadScene(SceneLoader.student_addGuestForm_ui, this);
    }

}
