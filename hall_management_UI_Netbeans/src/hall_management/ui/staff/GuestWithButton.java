 
package hall_management.ui.staff;
 
import javafx.scene.control.Button;

public class GuestWithButton {
    private String NID;
    private String fullName;
    private Button btn;

    public GuestWithButton(String NID, String fullName, Staff_Security_SeeGuestListController controller) {
        this.NID = NID;
        this.fullName = fullName;
        this.btn = new Button("Enter Guest Log");
        btn.setOnAction((event) -> {
            controller.runEnterToGuestLog(NID);
        });
    }

    public String getNID() {
        return NID;
    }

    public void setNID(String NID) {
        this.NID = NID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }
    
}
