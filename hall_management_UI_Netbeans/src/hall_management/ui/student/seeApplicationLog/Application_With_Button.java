package hall_management.ui.student.seeApplicationLog;

import hall_management.ui.student.seeRoomList.See_Application_RoomListController;
import hall_management.util.Interface.Scenes;
import hall_management.util.SceneLoader;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

/*
    private TableColumn<?, ?> column_applicationID;
    @FXML
    private TableColumn<?, ?> column_applicationDate;
    @FXML
    private TableColumn<?, ?> column_verdictDate;
    @FXML
    private TableColumn<?, ?> column_applicationStatus;
    @FXML
    private TableColumn<?, ?> allotted_room_number;
    @FXML
    private TableColumn<?, ?> column_see_Application_Room_List;
 */
public class Application_With_Button {

    public static String seeRoomList_ApplicationID;

    private SimpleStringProperty applicationID;
    private SimpleStringProperty applicationDate;

    private SimpleStringProperty verdictDate;
    private SimpleStringProperty applicationStatus;

    private SimpleStringProperty allotted_room_number;

    private SimpleStringProperty currentRoomID;

    private SimpleStringProperty verdict;
    private SimpleStringProperty hasRoom;
    private SimpleStringProperty studentId;

    private Button button;

    public Application_With_Button() {

        this.applicationID = new SimpleStringProperty("DEBUG MODE");//applicationID);
        this.applicationDate = new SimpleStringProperty("DEBUG MODE");//applicationDate);
        this.verdictDate = new SimpleStringProperty("DEBUG MODE");//verdictDate);
        this.applicationStatus = new SimpleStringProperty("DEBUG MODE");//applicationStatus);
        this.allotted_room_number = new SimpleStringProperty("DEBUG MODE");//allotted_room_number);
        this.currentRoomID = new SimpleStringProperty("DEBUG MODE");//currentRoomID);
        this.verdict = new SimpleStringProperty("<Not Applicable>");//verdict);
        this.hasRoom = new SimpleStringProperty("DEBUG MODE");//hasRoom);
        this.studentId = new SimpleStringProperty("DEBUG MODE");//studentId);
        this.button = new Button("See Room(s)");
    }

    public Application_With_Button(String applicationID, String applicationDate, String verdictDate, String applicationStatus, String allotted_room_number, String currentRoomID, String verdict, String hasRoom, String studentId
    , See_ApplicationsController controller) {
        this.applicationID = new SimpleStringProperty(applicationID);
        this.applicationDate = new SimpleStringProperty(applicationDate);
        this.verdictDate = new SimpleStringProperty(verdictDate);
        this.applicationStatus = new SimpleStringProperty(applicationStatus);
        if (allotted_room_number == null) {
            this.allotted_room_number = new SimpleStringProperty("<NOT APPLICABLE>");
        } else {
            this.allotted_room_number = new SimpleStringProperty(allotted_room_number);
        }
        this.currentRoomID = new SimpleStringProperty(currentRoomID);
        if (verdict == null) {
            this.verdict = new SimpleStringProperty("<Not Applicable>");
        } else {
            this.verdict = new SimpleStringProperty(verdict);
        }
        if (hasRoom.toUpperCase().equals("N")) {
            hasRoom = "NO";
        } else if (hasRoom.toUpperCase().equals("Y")) {
            hasRoom = "YES";
        }
        this.hasRoom = new SimpleStringProperty(hasRoom);
        this.studentId = new SimpleStringProperty(studentId);

        if(this.hasRoom.get().toUpperCase().equals("YES")){
            this.button = new Button("See Room(s)");
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    Application_With_Button.seeRoomList_ApplicationID = applicationID;
                    controller.runSeeApplicationListForApplicationID(applicationID);
//                    See_Application_RoomListController controller = (See_Application_RoomListController) SceneLoader.loadSceneInADifferentWindow(Scenes.student_see_application_room_list, this);

//                See_ApplicationsController.see_Room_List_For_ApplicationID(applicationID);
//                    System.out.println("==-->> BUTTON IS CLICKED!! APPLICATION_ID IS " + seeRoomList_ApplicationID + "\n");

                }
            });        
        }
//        this.button = new Button("See Room(s)");
//        button.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                seeRoomList_ApplicationID = applicationID;
//                See_Application_RoomListController controller = (See_Application_RoomListController) SceneLoader.loadScene(Scenes.student_see_application_room_list, this);
//
////                See_ApplicationsController.see_Room_List_For_ApplicationID(applicationID);
//                System.out.println("==-->> BUTTON IS CLICKED!! APPLICATION_ID IS " + seeRoomList_ApplicationID + "\n");
//
//            }
//        });
    }

    public String getAllotted_room_number() {
        return allotted_room_number.get();
    }

    public String getApplicationID() {
        return applicationID.get();
    }

    public String getApplicationDate() {
        return applicationDate.get();
    }

    public String getVerdictDate() {
        return verdictDate.get();
    }

    public String getApplicationStatus() {
        return applicationStatus.get();
    }

    public String getCurrentRoomID() {
        return currentRoomID.get();
    }

    public String getVerdict() {
        return verdict.get();
    }

    public String getHasRoom() {
        return hasRoom.get();
    }

    public String getStudentId() {
        return studentId.get();
    }

    ///NOW SETTERS!!
    public void setAllotted_room_number(String allotted_room_number) {
        this.allotted_room_number.set(allotted_room_number);
    }

    public void setApplicationID(String applicationID) {
        this.applicationID.set(applicationID);
    }

    public void setApplicationDate(String applicationDate) {
        this.applicationDate.set(applicationDate);
    }

    public void setVerdictDate(String verdictDate) {
        this.verdictDate.set(verdictDate);
    }

    public void setApplicationStatus(String applicationStatus) {
        this.applicationStatus.set(applicationStatus);
    }

    public void setCurrentRoomID(String currentRoomID) {
        this.currentRoomID.set(currentRoomID);
    }

    public void setVerdict(String verdict) {
        this.verdict.set(verdict);
    }

    public void setHasRoom(String hasRoom) {
        if (hasRoom.toUpperCase().equals("N")) {
            hasRoom = "NO";
        } else if (hasRoom.toUpperCase().equals("Y")) {
            hasRoom = "YES";
        }
        this.hasRoom.set(hasRoom);
    }

    public void setStudentId(String studentId) {
        this.studentId.set(studentId);
    }

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }

}
