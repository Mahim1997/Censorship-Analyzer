package hall_management.ui.teacher.applications;

import javafx.scene.control.Button;

public class Room_With_Buttons {

    public String hallId;
    public String roomNum;
    public String roomCapacity;
    public String currentNumStudents;

    public Button button_addRoom;

    public Room_With_Buttons(Application_AcceptedFormController controller) {
        this.button_addRoom = new Button("Add Room");
        this.button_addRoom.setOnAction((event) -> {
//            Application_AcceptedFormController.allottedRoomNumber = this.roomNum;
            controller.runButtonClick(this.roomNum);
        });
    }

    public Room_With_Buttons(String hallId, String roomNum, String roomCapacity, String currentNumStudents, Application_AcceptedFormController controller) {
        this.hallId = hallId;
        this.roomNum = roomNum;
        this.roomCapacity = roomCapacity;
        this.currentNumStudents = currentNumStudents;
        this.button_addRoom = new Button("Add Room");
        this.button_addRoom.setOnAction((event) -> {
            controller.runButtonClick(roomNum);
//            Application_AcceptedFormController.allottedRoomNumber = this.roomNum;
        });
    }

    public String getHallId() {
        return hallId;
    }

    public void setHallId(String hallId) {
        this.hallId = hallId;
    }

    public String getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(String roomNum) {
        this.roomNum = roomNum;
    }

    public String getRoomCapacity() {
        return roomCapacity;
    }

    public void setRoomCapacity(String roomCapacity) {
        this.roomCapacity = roomCapacity;
    }

    public String getCurrentNumStudents() {
        return currentNumStudents;
    }

    public void setCurrentNumStudents(String currentNumStudents) {
        this.currentNumStudents = currentNumStudents;
    }

    public Button getButton_addRoom() {
        return button_addRoom;
    }

    public void setButton_addRoom(Button button_addRoom) {
        this.button_addRoom = button_addRoom;
    }

}
