package hall_management.ui.student.formingTeamApply;

import javafx.scene.Scene;

public class Students_OfThisHall {
    public String id;
    public String fullName;
    public String type;

    public Students_OfThisHall(String id, String fullName, String type) {
        this.id = id;
        this.fullName = fullName;
        this.type = type;
    }

    public Students_OfThisHall() {
       
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    
}
