package hall_management.ui.student.seeGuestList;

import hall_management.ui.student.*;
import static hall_management.ui.student.seeGuestList.AllowedGuest_WithButton.Guest_NID_For_Guest_Log;
import static hall_management.ui.student.seeGuestList.AllowedGuest_WithButton.Guest_Name_For_Guest_Log;
import hall_management.util.Interface.Scenes;
import hall_management.util.SceneLoader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Button;

public class AllowedGuest {

    public String NID;
    public String studentId;
    public String relationWithStudent;
    public String guestFullName;
    public String guestAddress;
    public String guestContactNumber;

    Button button;
    Button btn_delete;

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }

    public Button getBtn_delete() {
        return btn_delete;
    }

    public void setBtn_delete(Button btn_delete) {
        this.btn_delete = btn_delete;
    }

    public AllowedGuest(String NID, String studentId, String relationWithStudent, String guestFullName, String guestAddress, String guestContactNumber) {
        this.NID = NID;
        this.studentId = studentId;
        this.relationWithStudent = relationWithStudent;
        this.guestFullName = guestFullName;
        this.guestAddress = guestAddress;
        this.guestContactNumber = guestContactNumber;
    }

    public AllowedGuest(See_GuestListController controller) {
        this.button = new Button("See Log");
        button.setOnAction((event) -> {
            controller.runSeeGuestLog(this.NID, this.guestFullName);
           
//            Guest_NID_For_Guest_Log = NID;
//            Guest_Name_For_Guest_Log = guestFullName;
////            System.out.println("<><><> See Guest Log for guestNID = " + Guest_NID_For_Guest_Log);
//            SceneLoader.loadScene(Scenes.student_see_guestLog, this);

        });
        this.btn_delete = new Button("Remove Guest");
        btn_delete.setOnAction((event) -> {
            try {
                controller.removeGuest(this.NID);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

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
