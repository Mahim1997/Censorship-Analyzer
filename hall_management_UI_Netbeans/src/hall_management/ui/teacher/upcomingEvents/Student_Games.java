package hall_management.ui.teacher.upcomingEvents;

import javafx.scene.control.Button;

public class Student_Games {
    String studentID;
    String fullName;
    String student_type;
    String position;
    String hallName;
    String contactNo;
    String team_year;
 
 
    public Student_Games() {
    }
    public Student_Games(String studentID, String fullName, String student_type, String position, String hallName, String contactNo, String team_year) {
        this.studentID = studentID;
        this.fullName = fullName;
        this.student_type = student_type;
        this.position = position;
        this.hallName = hallName;
        this.contactNo = contactNo;
        this.team_year = team_year;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getStudent_type() {
        return student_type;
    }

    public void setStudent_type(String student_type) {
        this.student_type = student_type;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getHallName() {
        return hallName;
    }

    public void setHallName(String hallName) {
        this.hallName = hallName;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getTeam_year() {
        return team_year;
    }

    public void setTeam_year(String team_year) {
        this.team_year = team_year;
    }

    @Override
    public String toString() {
        return "Student_Games{" + "studentID=" + studentID + ", fullName=" + fullName + ", student_type=" + student_type + ", position=" + position + ", hallName=" + hallName + ", contactNo=" + contactNo + ", team_year=" + team_year + '}';
    }



 
    
    
    
}
