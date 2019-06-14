package hall_management.ui.teacher.upcomingEvents;

import hall_management.ui.teacher.events.SeeEventsSuperviedController;
import hall_management.ui.teacher.events.SeeTeamsController;
import javafx.scene.control.Button;

public class Teams {

    String team_id;
    String team_name;
    String team_year;
    String team_sport;
    String team_type;
    String hallID;
    String hallName;
    String captainStudentID;
    String position;

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
    Button verdictButton;

    Button btn_makeWinner;
    Button btn_makeRunnerUp;

    Button btn_click_See_Students;
    
    public Teams(SeeTeamsController controller){
        btn_click_See_Students = new Button("SEE STUDENTS");
        btn_click_See_Students.setOnAction((event) -> {
            controller.runOnButtonClick(team_id, team_name);
        });
    }

    public Button getBtn_click_See_Students() {
        return btn_click_See_Students;
    }

    public void setBtn_click_See_Students(Button btn_click_See_Students) {
        this.btn_click_See_Students = btn_click_See_Students;
    }
            

    public Teams(String team_id, String team_name, String team_year, String team_sport, String team_type,
            String hallID, String hallName, String captainStudentID, WinnerAndRunnerUpSelectroController controller) {
        this.team_id = team_id;
        this.team_name = team_name;
        this.team_year = team_year;
        this.team_sport = team_sport;
        this.team_type = team_type;
        this.hallID = hallID;
        this.hallName = hallName;
        this.captainStudentID = captainStudentID;

        this.btn_makeWinner = new Button("Make Winner");
        btn_makeWinner.setOnAction((event) -> {
            controller.runMakeWinner(this.team_id, this.team_name, this.team_sport, this.captainStudentID);
        });

        this.btn_makeRunnerUp = new Button("Make Runner Up");
        btn_makeRunnerUp.setOnAction((event) -> {
            controller.makeRunnerUp(this.team_id, this.team_name, this.team_sport, this.captainStudentID);

        });
    }

    public Teams(WinnerAndRunnerUpSelectroController controller) {
        this.btn_makeWinner = new Button("Make Winner");
        btn_makeWinner.setOnAction((event) -> {
            controller.runMakeWinner(this.team_id, this.team_name, this.team_sport, this.captainStudentID);
        });

        this.btn_makeRunnerUp = new Button("Make Runner Up");
        btn_makeRunnerUp.setOnAction((event) -> {
            controller.makeRunnerUp(this.team_id, this.team_name, this.team_sport, this.captainStudentID);

        });
    }

    public Button getBtn_makeWinner() {
        return btn_makeWinner;
    }

    public void setBtn_makeWinner(Button btn_makeWinner) {
        this.btn_makeWinner = btn_makeWinner;
    }

    public Button getBtn_makeRunnerUp() {
        return btn_makeRunnerUp;
    }

    public void setBtn_makeRunnerUp(Button btn_makeRunnerUp) {
        this.btn_makeRunnerUp = btn_makeRunnerUp;
    }

    public Teams(String team_id, String team_name, String team_year, String team_sport, String team_type, String hallID, String hallName, String captainStudentID) {
        this.team_id = team_id;
        this.team_name = team_name;
        this.team_year = team_year;
        this.team_sport = team_sport;
        this.team_type = team_type;
        this.hallID = hallID;
        this.hallName = hallName;
        this.captainStudentID = captainStudentID;
    }

    public String getHallName() {
        return hallName;
    }

    public void setHallName(String hallName) {
        this.hallName = hallName;
    }

    public Teams() {

    }

    @Override
    public String toString() {
        return "Teams{" + "team_id=" + team_id + ", team_name=" + team_name + ", team_year=" + team_year + ", team_sport=" + team_sport + ", team_type=" + team_type + ", hallID=" + hallID + ", hallName=" + hallName + ", captainStudentID=" + captainStudentID + ", verdictButton=" + verdictButton + '}';
    }

    public Teams(Manage_teamsController controller, String textButton) {
        this.verdictButton = new Button(textButton);
        verdictButton.setOnAction((event) -> {
            controller.runVerdictWindow(this.team_id, this.team_sport);
        });
    }

    public String getTeam_id() {
        return team_id;
    }

    public void setTeam_id(String team_id) {
        this.team_id = team_id;
    }

    public String getTeam_name() {
        return team_name;
    }

    public void setTeam_name(String team_name) {
        this.team_name = team_name;
    }

    public String getTeam_year() {
        return team_year;
    }

    public void setTeam_year(String team_year) {
        this.team_year = team_year;
    }

    public String getTeam_sport() {
        return team_sport;
    }

    public void setTeam_sport(String team_sport) {
        this.team_sport = team_sport;
    }

    public String getTeam_type() {
        return team_type;
    }

    public void setTeam_type(String team_type) {
        this.team_type = team_type;
    }

    public String getHallID() {
        return hallID;
    }

    public void setHallID(String hallID) {
        this.hallID = hallID;
    }

    public String getCaptainStudentID() {
        return captainStudentID;
    }

    public void setCaptainStudentID(String captainStudentID) {
        this.captainStudentID = captainStudentID;
    }

    public Button getVerdictButton() {
        return verdictButton;
    }

    public void setVerdictButton(Button verdictButton) {
        this.verdictButton = verdictButton;
    }

}
