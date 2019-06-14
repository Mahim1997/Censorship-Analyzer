package hall_management.ui.guest;

public class Student_OfThisGuest {
    private String studentID;
    private String studentFullName;
    private String hallName;
    private String relationWithGuest;

    public Student_OfThisGuest(String studentID, String studentFullName, String hallName, String relationWithGuest) {
        this.studentID = studentID;
        this.studentFullName = studentFullName;
        this.hallName = hallName;
        this.relationWithGuest = relationWithGuest;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getStudentFullName() {
        return studentFullName;
    }

    public void setStudentFullName(String studentFullName) {
        this.studentFullName = studentFullName;
    }

    public String getHallName() {
        return hallName;
    }

    public void setHallName(String hallName) {
        this.hallName = hallName;
    }

 

    public String getRelationWithGuest() {
        return relationWithGuest;
    }

    public void setRelationWithGuest(String relationWithGuest) {
        this.relationWithGuest = relationWithGuest;
    }

    @Override
    public String toString() {
        return "Student_OfThisGuest{" + "studentID=" + studentID + ", studentFullName=" + studentFullName + ", hallName=" + hallName + ", relationWithGuest=" + relationWithGuest + '}';
    }

  
    
    
}
