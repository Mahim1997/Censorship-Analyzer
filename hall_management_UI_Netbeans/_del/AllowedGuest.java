package hall_management.ui.student;

public class AllowedGuest {
    public String NID ;
    public String studentId;
    public String relationWithStudent;
    public String guestFullName;
    public String guestAddress;
    public String guestContactNumber;

    public AllowedGuest(String NID, String studentId, String relationWithStudent, String guestFullName, String guestAddress, String guestContactNumber)
    {
        this.NID = NID;
        this.studentId = studentId;
        this.relationWithStudent = relationWithStudent;
        this.guestFullName = guestFullName;
        this.guestAddress = guestAddress;
        this.guestContactNumber = guestContactNumber;
    }

    public AllowedGuest() {
        
    }

    @Override
    public String toString() {
        return "AllowedGuest{" + "NID=" + NID + ", studentId=" + studentId + ", relationWithStudent=" + relationWithStudent + ", guestFullName=" + guestFullName + ", guestAddress=" + guestAddress + ", guestContactNumber=" + guestContactNumber + '}';
    }

    public String getNID() {
        return NID;
    }

    public void setNID(String NID) {
        this.NID = NID;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getRelationWithStudent() {
        return relationWithStudent;
    }

    public void setRelationWithStudent(String relationWithStudent) {
        this.relationWithStudent = relationWithStudent;
    }

    public String getGuestFullName() {
        return guestFullName;
    }

    public void setGuestFullName(String guestFullName) {
        this.guestFullName = guestFullName;
    }

    public String getGuestAddress() {
        return guestAddress;
    }

    public void setGuestAddress(String guestAddress) {
        this.guestAddress = guestAddress;
    }

    public String getGuestContactNumber() {
        return guestContactNumber;
    }

    public void setGuestContactNumber(String guestContactNumber) {
        this.guestContactNumber = guestContactNumber;
    }
    
    
}
