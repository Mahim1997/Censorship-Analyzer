package hall_management.ui.student.seeGuestList;


public class Guest_Log {
    private String visitingDate;
    private String startTime;
    private String endTime;

    public Guest_Log(String visitingDate, String startTime, String endTime) {
        this.visitingDate = visitingDate;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Guest_Log() {

    }

    public String getVisitingDate() {
        return visitingDate;
    }

    public void setVisitingDate(String visitingDate) {
        this.visitingDate = visitingDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
    
    
}
