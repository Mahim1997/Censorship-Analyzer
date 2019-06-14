/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hall_management.ui.staff;

import javafx.scene.control.Button;

/**
 *
 * @author esfs
 */
public class Student_ForGuardToSee {
    private String id;
    private String fullName;
    private Button button_seeGuestList;

    public Student_ForGuardToSee(String id, String fullName, Staff_Security_GuardController controller) {
        this.id = id;
        this.fullName = fullName;
        this.button_seeGuestList = new Button("Click to See Guest List");
        this.button_seeGuestList.setOnAction((event) -> {
            controller.runSeeGuestList(id);
        });
    }

    @Override
    public String toString() {
        return "Student_ForGuardToSee{" + "id=" + id + ", fullName=" + fullName + ", button_seeGuestList=" + button_seeGuestList + '}';
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

    public Button getButton_seeGuestList() {
        return button_seeGuestList;
    }

    public void setButton_seeGuestList(Button button_seeGuestList) {
        this.button_seeGuestList = button_seeGuestList;
    }
    
    
}
