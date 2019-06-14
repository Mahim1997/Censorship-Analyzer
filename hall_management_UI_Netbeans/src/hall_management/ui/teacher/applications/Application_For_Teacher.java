package hall_management.ui.teacher.applications;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;

public class Application_For_Teacher {

    private SimpleStringProperty applicationID;
    private SimpleStringProperty applicationDate;

    private SimpleStringProperty verdictDate;
    private SimpleStringProperty applicationStatus;

    private SimpleStringProperty allotted_room_number;

    private SimpleStringProperty currentRoomID;

    private SimpleStringProperty verdict;
    private SimpleStringProperty hasRoom;
    private SimpleStringProperty studentId;
    private SimpleStringProperty hallID;

    private Button acceptButton;
    private Button rejectButton;

    private SimpleStringProperty room1;
    private SimpleStringProperty room2;
    private SimpleStringProperty room3;
    private SimpleStringProperty room4;

    Teacher_seeApplicationsController controller;

    public Application_For_Teacher(Teacher_seeApplicationsController cntrol) {
        acceptButton = new Button("Accept");
        rejectButton = new Button("Reject");
        this.controller = cntrol;

    }

    @Override
    public String toString() {
        return "Application_For_Teacher{" + "applicationID=" + applicationID + ", applicationDate=" + applicationDate + ", verdictDate=" + verdictDate + ", applicationStatus=" + applicationStatus + ", allotted_room_number=" + allotted_room_number + ", currentRoomID=" + currentRoomID + ", verdict=" + verdict + ", hasRoom=" + hasRoom + ", studentId=" + studentId + ", acceptButton=" + acceptButton + ", rejectButton=" + rejectButton + '}';
    }

    public String getHallID() {
        return hallID.get();
    }

    public void setHallID(String hallID) {
        this.hallID = new SimpleStringProperty(hallID);
    }

    public Application_For_Teacher(String applicationID, String applicationDate, String verdictDate, String applicationStatus, String allotted_room_number, String currentRoomID, String verdict, String hasRoom, String studentId, Teacher_seeApplicationsController cntrol) {
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
        this.acceptButton = new Button("VERDICT");
        this.rejectButton = new Button("Reject");
        this.controller = cntrol;
//        controller.buttonLoaders();
        acceptButton.setOnAction((event) -> {
            try {
                controller.setApplicationID(this.getApplicationID());
                controller.setApplicationStatus(this.getApplicationStatus());
//                Application_AcceptedFormController.applicationID = this.getApplicationID();
//            controller.acceptBtn = this.acceptButton;
//            controller.rejectBtn = this.rejectButton;
                controller.runAccepted(this.getApplicationID(), this.getApplicationStatus(), this.getStudentId(), 
                        this.getCurrentRoomID(), this.getHasRoom(), this.getApplicationDate());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        rejectButton.setOnAction((event) -> {
            try {
                controller.setApplicationID(this.getApplicationID());
                controller.setApplicationStatus(this.getApplicationStatus());
//            controller.acceptBtn = this.acceptButton;
//            controller.rejectBtn = this.rejectButton;
                controller.runRejected(applicationID, applicationStatus, studentId, currentRoomID, hasRoom, applicationDate);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
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

    public Button getAcceptButton() {
        return acceptButton;
    }

    public void setAcceptButton(Button acceptButton) {
        this.acceptButton = acceptButton;
    }

    public Button getRejectButton() {
        return rejectButton;
    }

    public void setRejectButton(Button rejectButton) {
        this.rejectButton = rejectButton;
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
