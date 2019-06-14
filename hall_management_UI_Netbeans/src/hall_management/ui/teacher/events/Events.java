package hall_management.ui.teacher.events;

import hall_management.ui.student.events.ParticipatedEventsController;
import hall_management.ui.teacher.upcomingEvents.ManageUpcomingEventsController;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Button;

public class Events {

    private String event_id;
    private String event_name;
    private String event_year;
    private String event_sport;
    private String event_type;
    private String event_status;
    private String hall_id;
    private String evenr_supervisorID;
    private String winner_team_id;
    private String runnerUp_team_id;
    private String event_hallName;
    private Button button;
    private Button button_Manage;

    private Button btn_changeEventStatus;

    public Button btn_student_seeTeams;

    public Events(ParticipatedEventsController controller) {
        this.btn_student_seeTeams = new Button("SEE TEAM");
        this.btn_student_seeTeams.setOnAction((event) -> {
            controller.runButtonClick(event_id, event_sport);
        });
    }

    public Button getBtn_student_seeTeams() {
        return btn_student_seeTeams;
    }

    public void setBtn_student_seeTeams(Button btn_student_seeTeams) {
        this.btn_student_seeTeams = btn_student_seeTeams;
    }

    public Button getBtn_changeEventStatus() {
        return btn_changeEventStatus;
    }

    public void setBtn_changeEventStatus(Button btn_changeEventStatus) {
        this.btn_changeEventStatus = btn_changeEventStatus;
    }

    public String getEvent_hallName() {
        return event_hallName;
    }

    public void setEvent_hallName(String event_hallName) {
        this.event_hallName = event_hallName;
    }

    public Button getButton_Manage() {
        return button_Manage;
    }

    public void setButton_Manage(Button button_Manage) {
        this.button_Manage = button_Manage;
    }

    public Button getButton() {
        return button;
    }

    @Override
    public String toString() {
        return "Events{" + "event_id=" + event_id + ", event_name=" + event_name + ", event_year=" + event_year + ", event_sport=" + event_sport + ", event_type=" + event_type + ", event_status=" + event_status + ", hall_id=" + hall_id + ", evenr_supervisorID=" + evenr_supervisorID + ", winner_team_id=" + winner_team_id + ", runnerUp_team_id=" + runnerUp_team_id + ", event_hallName=" + event_hallName + ", button=" + button + ", button_Manage=" + button_Manage + ", btn_changeEventStatus=" + btn_changeEventStatus + '}';
    }

    public void setButton(Button button) {
        this.button = button;
    }

    public Events() {

    }

    public Events(ManageUpcomingEventsController controller) {
        this.button_Manage = new Button("Manage Teams");
        button_Manage.setOnAction((event) -> {
            controller.runManageTeamsForEvent(event_id, event_status);
        });
        this.btn_changeEventStatus = new Button("Finish Event");
        btn_changeEventStatus.setOnAction((event) -> {
            try {
                controller.runChangeStatus(event_id);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    public Events(SeeEventsSuperviedController controller) {
        this.button = new Button("See Teams Participated");
        button.setOnAction((event) -> {
            controller.runOnButtonClick(event_id);
        });

    }

    public Events(String event_id, String event_name, String event_year, String event_sport, String event_type, String event_status, String hall_id, String evenr_supervisorID, String winner_team_id, String runnerUp_team_id) {
        this.event_id = event_id;
        this.event_name = event_name;
        this.event_year = event_year;
        this.event_sport = event_sport;
        this.event_type = event_type;
        this.event_status = event_status;
        this.hall_id = hall_id;
        this.evenr_supervisorID = evenr_supervisorID;
        this.winner_team_id = winner_team_id;
        this.runnerUp_team_id = runnerUp_team_id;
    }

    public String getEvent_id() {
        return event_id;
    }

    public void setEvent_id(String event_id) {
        this.event_id = event_id;
    }

    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public String getEvent_year() {
        return event_year;
    }

    public void setEvent_year(String event_year) {
        this.event_year = event_year;
    }

    public String getEvent_sport() {
        return event_sport;
    }

    public void setEvent_sport(String event_sport) {
        this.event_sport = event_sport;
    }

    public String getEvent_type() {
        return event_type;
    }

    public void setEvent_type(String event_type) {
        this.event_type = event_type;
    }

    public String getEvent_status() {
        return event_status;
    }

    public void setEvent_status(String event_status) {
        this.event_status = event_status;
    }

    public String getHall_id() {
        return hall_id;
    }

    public void setHall_id(String hall_id) {
        this.hall_id = hall_id;
    }

    public String getEvenr_supervisorID() {
        return evenr_supervisorID;
    }

    public void setEvenr_supervisorID(String evenr_supervisorID) {
        this.evenr_supervisorID = evenr_supervisorID;
    }

    public String getWinner_team_id() {
        return winner_team_id;
    }

    public void setWinner_team_id(String winner_team_id) {
        this.winner_team_id = winner_team_id;
    }

    public String getRunnerUp_team_id() {
        return runnerUp_team_id;
    }

    public void setRunnerUp_team_id(String runnerUp_team_id) {
        this.runnerUp_team_id = runnerUp_team_id;
    }

}
