package hall_management.ui.teacher.applications;

import hall_management.db.queries.Application_Queries;
import hall_management.db.queries.Functions;
import hall_management.ui.startPage.Main;
import hall_management.ui.student.seeApplicationLog.*;
import hall_management.util.Interface.*;
import hall_management.util.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

public class Teacher_seeApplicationsController implements Initializable, Controller {

    private String applicationID;
    @FXML
    private Text text_Uporer;
    @FXML
    private TableView tableview;

    private String applicationStatus;

    private String studentID;
    private String applicationDate;

    public Button acceptBtn;
    public Button rejectBtn;
//    TableColumn col_application_id = new TableColumn("Application ID");
//    TableColumn col_application_date = new TableColumn("Application Date");
//    TableColumn col_verdictDate = new TableColumn("Verdict Date");
//    TableColumn col_applicationStatus = new TableColumn("Application Status");
//    TableColumn col_verdict = new TableColumn("Verdict");
//    TableColumn col_allotted_room_no = new TableColumn("Allotted Room No");
//    TableColumn hasRoomsCol = new TableColumn("Has Multiple Rooms");
//    TableColumn btnCol = new TableColumn("Accept Column");
//    TableColumn btnCol2 = new TableColumn("Reject Column");
    @FXML
    private TableColumn col_application_id;
    @FXML
    private TableColumn col_application_date;
    @FXML
    private TableColumn col_verdictDate;
    @FXML
    private TableColumn col_applicationStatus;
    @FXML
    private TableColumn col_alllotted_room_no;
    @FXML
    private TableColumn hasRoomsCol;
    @FXML
    private TableColumn btnCol;
    @FXML
    private TableColumn btn2Col;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        String text = "Applications for Teacher ID : " + Main.teacherID + " and Under Hall_Id = " + Functions.getHallID(Main.teacherID);
        String text = "Teacher ID : " + Main.teacherID + ", Hall Name : " + Functions.getHallNAME(Main.teacherID);
        text_Uporer.setText(text);
        try {

            // TODO
            loadApplicationsToSee();
//            buttonLoaders();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void goBack(ActionEvent event) {
        SceneLoader.closeScene(SceneLoader.CurrentScene());
        SceneLoader.loadPreviousScene(Scenes.teacher_ui, this);
    }

    private void loadApplicationsToSee() throws Exception {
        List<Application_For_Teacher> list = loadApplicationsFromQueryWithButtons();
        System.out.println("==-->>Inside loadApplicationsToSee().. printing list.. ");
        for(Application_For_Teacher app: list){
            System.out.println(app.toString());
        }
        showThisListToWindow(list);
    }
//(String applicationID, String applicationDate, String verdictDate, String applicationStatus, String allotted_room_number, String currentRoomID, 
    //String verdict, String hasRoom, String studentId, Teacher_seeApplicationsController cntrol) 

    private List<Application_For_Teacher> loadApplicationsFromQueryWithButtons() throws Exception {
        List<Application_For_Teacher> list = new ArrayList<>();
//        System.out.println("==-->>Inside Teacher_seeApplicationsController.loadApplicationsToSee()... \n");
        List<Application_Entity> listApplications = Application_Queries.getApplicationsUnderTeacher(Main.teacherID);
        Application_Entity app;
        Application_For_Teacher app2;
        for (int i = 0; i < listApplications.size(); i++) {
            app = listApplications.get(i);
            app2 = new Application_For_Teacher(app.getApplicationID(), app.getApplicationDate(), app.getVerdictDate(),
                    app.getApplicationStatus(), app.getAllotted_room_number(), app.getCurrentRoomID(), app.getVerdict(), app.getHasRoom(),
                    app.getStudentId(), this);
            list.add(app2);
        }
        return list;
    }

    private void showThisListToWindow(List<Application_For_Teacher> list) throws Exception {
 
        ObservableList<Application_For_Teacher> data = FXCollections.observableArrayList(list);

        col_application_id.setCellValueFactory(
                new PropertyValueFactory<Application_For_Teacher, String>("applicationID")
        );
        col_application_date.setCellValueFactory(
                new PropertyValueFactory<>("applicationDate")
        );
        col_verdictDate.setCellValueFactory(
                new PropertyValueFactory<>("verdictDate")
        );
        col_applicationStatus.setCellValueFactory(
                new PropertyValueFactory<>("applicationStatus")
        );
//        col_verdict.setCellValueFactory(
//                new PropertyValueFactory<>("verdict")
//        );
        col_alllotted_room_no.setCellValueFactory(
                new PropertyValueFactory<>("allotted_room_number")
        );
        btnCol.setCellValueFactory(
                new PropertyValueFactory<>("acceptButton")
        );
        btn2Col.setCellValueFactory(
                new PropertyValueFactory<>("rejectButton")
        );

        hasRoomsCol.setCellValueFactory(
                new PropertyValueFactory<>("hasRoom")
        );
        tableview.setItems(data);

    }
 
    public void setApplicationID(String appID) throws Exception {
        this.applicationID = appID;
    }

    public void runRejected() throws Exception {
        System.out.println("=->>Teacher_seeApplicationsController.runRejected()this.APPLICAITON_ID = " + this.applicationID);
        this.applicationStatus = Application_Queries.getApplicationStatus(this.applicationID);
        System.out.println("APPLICATION STATUS FOR APP_ID = " + this.applicationID + " is " + this.applicationStatus);
        if (applicationStatus.toUpperCase().equals("PENDING")) {
            Application_Queries.updateApplicationField(this.applicationID, "STATUS", "REJECTED");
        }
        loadApplicationsToSee();

    }

 
    void runAccepted(String applicationID, String applicationStatus, String studentId, String currentRoomID, String hasRoom, String applicationDate) {
        Application_AcceptedFormController.applicationID = applicationID;
        Application_AcceptedFormController.applicationDate = applicationDate;
        Application_AcceptedFormController.studentID = studentId;
        Application_AcceptedFormController.hasMultipleRooms = hasRoom;
        Application_AcceptedFormController controller = (Application_AcceptedFormController) SceneLoader.loadScene(Scenes.teacher_application_AcceptForm, this);

    }

    @FXML
    private void refreshInfo(ActionEvent event) {
        try{
            loadApplicationsToSee();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public String getApplicationStatus() {
        return applicationStatus;
    }

    public void setApplicationStatus(String applicationStatus) {
        this.applicationStatus = applicationStatus;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(String applicationDate) {
        this.applicationDate = applicationDate;
    }
}
