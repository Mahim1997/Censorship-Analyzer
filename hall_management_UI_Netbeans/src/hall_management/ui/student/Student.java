package hall_management.ui.student;



public class Student {
    
    public String id;
    public String fullName;
    public String dept_Id;
    public String address;
    public String birthDate;
    public String religion;
    public String fatherName;
    public String motherName;
    public String contactNumber;
    public String gender;
    public String bloodGrp;

    public String type;

    public String currentRoomNumber;

    public String allocationDate ;

    public String hallName ;
    
    public String hallID ;

    public String getHallName() {
        return hallName;
    }

    public void setHallName(String hallName) {
        this.hallName = hallName;
    }

    public String getHallID() {
        return hallID;
    }

    public void setHallID(String hallID) {
        this.hallID = hallID;
    }
    
    public String getAllocationDate() {
        return allocationDate;
    }

    public void setAllocationDate(String allocationDate) {
        this.allocationDate = allocationDate;
    }
    
    
    
    
    public String getCurrentRoomNumber() {
        return currentRoomNumber;
    }

    public void setCurrentRoomNumber(String currentRoomNumber) {
        this.currentRoomNumber = currentRoomNumber;
    }
    
    
    
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    public Student(){
        
    }
    
    public Student(String id, String fullName, String dept_Id, String address, String birthDate, String religion, String fatherName, String motherName, String contactNumber, String gender, String bloodGrp) {
        this.id = id;
        this.fullName = fullName;
        this.dept_Id = dept_Id;
        this.address = address;
        this.birthDate = birthDate;
        this.religion = religion;
        this.fatherName = fatherName;
        this.motherName = motherName;
        this.contactNumber = contactNumber;
        this.gender = gender;
        this.bloodGrp = bloodGrp;
    }

    public String getBloodGrp() {
        return bloodGrp;
    }

    public void setBloodGrp(String bloodGrp) {
        this.bloodGrp = bloodGrp;
    }
    
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDept_Id() {
        return dept_Id;
    }

    public void setDept_Id(String dept_Id) {
        this.dept_Id = dept_Id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    @Override
    public String toString() {
        return "Student{" + "id=" + id + ", fullName=" + fullName + ", dept_Id=" + dept_Id + ", address=" + address + ", birthDate=" + birthDate + ", religion=" + religion + ", fatherName=" + fatherName + ", motherName=" + motherName + ", contactNumber=" + contactNumber + ", gender=" + gender + ", bloodGrp=" + bloodGrp + ", type=" + type + ", currentRoomNumber=" + currentRoomNumber + ", allocationDate=" + allocationDate + ", hallName=" + hallName + ", hallID=" + hallID + '}';
    }

    
    
    
}
