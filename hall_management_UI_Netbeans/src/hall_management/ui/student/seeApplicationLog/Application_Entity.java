package hall_management.ui.student.seeApplicationLog;

import javafx.beans.property.SimpleStringProperty;
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
public class Application_Entity {

    private SimpleStringProperty applicationID;
    private SimpleStringProperty applicationDate;

    private SimpleStringProperty verdictDate;
    private SimpleStringProperty applicationStatus;

    private SimpleStringProperty allotted_room_number;

    private SimpleStringProperty currentRoomID;

    private SimpleStringProperty verdict;
    private SimpleStringProperty hasRoom;
    private SimpleStringProperty studentId;

    private SimpleStringProperty hallID ;
    
    public Application_Entity() {
        this.applicationID = new SimpleStringProperty("DEBUG MODE");//applicationID);
        this.applicationDate = new SimpleStringProperty("DEBUG MODE");//applicationDate);
        this.verdictDate = new SimpleStringProperty("DEBUG MODE");//verdictDate);
        this.applicationStatus = new SimpleStringProperty("DEBUG MODE");//applicationStatus);
        this.allotted_room_number = new SimpleStringProperty("DEBUG MODE");//allotted_room_number);
        this.currentRoomID = new SimpleStringProperty("DEBUG MODE");//currentRoomID);
        this.verdict = new SimpleStringProperty("DEBUG MODE");//verdict);
        this.hasRoom = new SimpleStringProperty("DEBUG MODE");//hasRoom);
        this.studentId = new SimpleStringProperty("DEBUG MODE");//studentId);

    }

    @Override
    public String toString() {
        return "Application_Entity{" + "applicationID=" + applicationID + ", applicationDate=" + applicationDate + ", verdictDate=" + verdictDate + ", applicationStatus=" + applicationStatus + ", allotted_room_number=" + allotted_room_number + ", currentRoomID=" + currentRoomID + ", verdict=" + verdict + ", hasRoom=" + hasRoom + ", studentId=" + studentId + '}';
    }

    public String getHallID() {
        return hallID.get();
    }

    public void setHallID(String hallID) {
        this.hallID = new SimpleStringProperty(hallID);
    }

    public Application_Entity(String applicationID, String applicationDate, String verdictDate, String applicationStatus, String allotted_room_number, String currentRoomID, String verdict, String hasRoom, String studentId) {
        this.applicationID = new SimpleStringProperty(applicationID);
        this.applicationDate = new SimpleStringProperty(applicationDate);
        this.verdictDate = new SimpleStringProperty(verdictDate);
        this.applicationStatus = new SimpleStringProperty(applicationStatus);
        this.allotted_room_number = new SimpleStringProperty(allotted_room_number);
        this.currentRoomID = new SimpleStringProperty(currentRoomID);
        if (verdict == null) {
            this.verdict = new SimpleStringProperty("<Not Applicable>");
        } else {
            this.verdict = new SimpleStringProperty(verdict);
        }
        this.verdict = new SimpleStringProperty(verdict);
        this.hasRoom = new SimpleStringProperty(hasRoom);
        this.studentId = new SimpleStringProperty(studentId);

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
        this.hasRoom.set(hasRoom);
    }

    public void setStudentId(String studentId) {
        this.studentId.set(studentId);
    }

}
