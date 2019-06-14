package hall_management.ui.teacher.events;


public class Events_With_Buttons {
    String event_id;
    String event_name;
    String event_year;
    String event_sport;
    String event_type;
    String event_status;
    String hall_id;
    String evenr_supervisorID;
    String winner_team_id;
    String runnerUp_team_id;

    public Events_With_Buttons(String event_id, String event_name, String event_year, String event_sport, String event_type, String event_status, String hall_id, String evenr_supervisorID, String winner_team_id, String runnerUp_team_id) {
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
