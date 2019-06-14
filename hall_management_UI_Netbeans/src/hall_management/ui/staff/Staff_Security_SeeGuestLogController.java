package hall_management.ui.staff;

import com.jfoenix.controls.JFXTextField;
import hall_management.db.queries.Application_Queries;
import hall_management.db.queries.Staff_Queries;
import hall_management.ui.popup.PopUpWindow;
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
import javafx.scene.text.Text;

public class Staff_Security_SeeGuestLogController implements Initializable, Controller {

    public static String Staff_Guest_NID;
    public static String Staff_StudentID;
    @FXML
    private Text text_Uporer;
    @FXML
    private JFXTextField textField_studentID;
    @FXML
    private JFXTextField textField_guestNID;
    @FXML
    private JFXTextField textField_VistingDate;
    private JFXTextField textField_endTime;

//    private String studentID;
//    private String guestNID;
    private String VistingDate;

    @FXML
    private JFXTextField textLabel_endTime_SECOND;

    @FXML
    private JFXTextField textLabel_startTime_SECOND;

    private int endTime_HOUR;

    private int endTime_MINUTE;

    private int startTime_HOUR;

    private int startTime_MINUTE;

    private String startTimeAM_PM;
    private String endTimeAM_PM;

    @FXML
    private ChoiceBox choiceBox_StartTime_AM_PM;
    @FXML
    private ChoiceBox choiceBox_StartTime_HOUR;
    @FXML
    private ChoiceBox choiceBox_StartTime_MIN;
    @FXML
    private ChoiceBox choiceBox_EndTime_PM_AM;
    @FXML
    private ChoiceBox choiceBox_EndTime_HOUR;
    @FXML
    private ChoiceBox choiceBox_EndTime_MIN;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("==-->>Inside Staff_Security_SeeGuestLogController.initialize().. Staff_Security_SeeGuestLogController.Staff_Guest_NID = "
                + Staff_Guest_NID + " , Student ID : " + Staff_Security_SeeGuestLogController.Staff_StudentID);
        String text = "GUEST LOG of Student ID : " + Staff_StudentID + " , Guest NID = " + Staff_Guest_NID;
        text_Uporer.setText(text);
        try {
            initialiseinfo();
//            submitInfo();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void goBack( ) {
        SceneLoader.closeScene(SceneLoader.CurrentScene());
        SceneLoader.loadPreviousScene(Scenes.staff_SecurityGuard_Guest_List, this);
    }

    private void initialiseinfo() throws Exception {
        setAllEditableFields(true);
        retrieveFieldsFromString();

        choiceBox_EndTime_HOUR.setItems(FXCollections.observableArrayList(
                1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12));
        choiceBox_EndTime_HOUR.setValue((Integer) 1);
        choiceBox_EndTime_HOUR.setOnAction((event) -> {
            endTime_HOUR = (Integer) choiceBox_EndTime_HOUR.getValue();
        });
        choiceBox_EndTime_MIN.setItems(FXCollections.observableArrayList(
                1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35,
                36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59));
        choiceBox_EndTime_MIN.setValue((Integer) 1);
        choiceBox_EndTime_MIN.setOnAction((event) -> {
            endTime_MINUTE = (Integer) choiceBox_EndTime_MIN.getValue();
        });
        choiceBox_StartTime_HOUR.setItems(FXCollections.observableArrayList(
                1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12));
        choiceBox_StartTime_HOUR.setValue((Integer) 1);
        choiceBox_StartTime_HOUR.setOnAction((event) -> {
            startTime_HOUR = (Integer) choiceBox_StartTime_HOUR.getValue();
        });
        choiceBox_StartTime_MIN.setItems(FXCollections.observableArrayList(
                1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36,
                37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59));
        choiceBox_StartTime_MIN.setValue((Integer) 1);
        choiceBox_StartTime_MIN.setOnAction((event) -> {
            startTime_MINUTE = (Integer) choiceBox_StartTime_MIN.getValue();
        });
        choiceBox_EndTime_PM_AM.setItems(FXCollections.observableArrayList(
                 "pm"));
        choiceBox_EndTime_PM_AM.setValue((String) "pm");
//        choiceBox_EndTime_PM_AM.setOnAction((event) -> {
//            endTimeAM_PM = (String) choiceBox_StartTime_HOUR.getValue();
//        });
        choiceBox_StartTime_AM_PM.setItems(FXCollections.observableArrayList(
                "pm"));
        choiceBox_StartTime_AM_PM.setValue((String) "pm");
//        choiceBox_StartTime_AM_PM.setOnAction((event) -> {
//            startTimeAM_PM = (String) choiceBox_StartTime_HOUR.getValue();
//        });
    }

    private void retrieveInfoTime() {
        startTime_HOUR = (Integer) choiceBox_StartTime_HOUR.getValue();
        startTime_MINUTE = (Integer) choiceBox_StartTime_MIN.getValue();
        endTime_HOUR = (Integer) choiceBox_EndTime_HOUR.getValue();
        endTime_MINUTE = (Integer) choiceBox_EndTime_MIN.getValue();
        startTimeAM_PM = (String) choiceBox_StartTime_AM_PM.getValue();
        endTimeAM_PM = (String) choiceBox_EndTime_PM_AM.getValue();
        VistingDate = Application_Queries.getCurrentDate();
    }

    private void setAllEditableFields(boolean flag) {
        textField_studentID.setEditable(false);

        textField_guestNID.setEditable(false);

        textField_VistingDate.setEditable(false);

//        textField_startTime.setEditable(flag);
//
//        textField_endTime.setEditable(flag);
        textLabel_startTime_SECOND.setEditable(false);
        textLabel_endTime_SECOND.setEditable(false);

    }

    private void retrieveFieldsFromString() {
        textField_studentID.setText(Staff_StudentID);
        textField_guestNID.setText(Staff_Guest_NID);
        textField_VistingDate.setText(Application_Queries.getCurrentDate());
    }

    @FXML
    private void addToGuestLog(ActionEvent event) throws Exception {
        retrieveInfoTime();
        String hour = null;
        String minute = null;
        if(startTime_MINUTE < 10){
            minute = "0" + String.valueOf(startTime_MINUTE);
        }else{
            minute = String.valueOf(startTime_MINUTE);
        }
        if (startTimeAM_PM.equals("am")) {
            if (startTime_HOUR == 12) {
                hour = "00";
            } else {
                hour = String.valueOf(startTime_HOUR);
            }
        } else if (startTimeAM_PM.equals("pm")) {
            int x = startTime_HOUR + 12;
            if (startTime_HOUR == 12) {
                hour = "12";
            } else {
                hour = String.valueOf(x);
            }
        }
        String startTime = String.valueOf(hour) + ":" + String.valueOf(minute) + ":" + "00";
        if (endTimeAM_PM.equals("am")) {
            if (endTime_HOUR == 12) {
                hour = "00";
            } else {
                hour = String.valueOf(endTime_HOUR);
            }
        } else if (endTimeAM_PM.equals("pm")) {
            int x = endTime_HOUR + 12;
            if (endTime_HOUR == 12) {
                hour = "12";
            } else {
                hour = String.valueOf(x);
            }
        }
        if(endTime_MINUTE < 10){
            minute = "0" + String.valueOf(endTime_MINUTE);
        }else{
            minute = String.valueOf(endTime_MINUTE);
        }
        String endTime = String.valueOf(hour) + ":" + String.valueOf(minute) + ":" + "00";
        Staff_Queries.addToGuestLog(Staff_Guest_NID, Staff_StudentID, VistingDate, startTime, endTime);
        PopUpWindow.displaySuccess("SUCCES!!", "Added To Guest Log", "CLICK TO CLOSE POP UP!");
        goBack();
    }

}
