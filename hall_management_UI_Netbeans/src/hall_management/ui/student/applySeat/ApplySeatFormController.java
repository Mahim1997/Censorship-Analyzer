package hall_management.ui.student.applySeat;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import hall_management.db.queries.Application_Queries;
import hall_management.db.queries.Query;
import hall_management.ui.popup.PopUpWindow;
import hall_management.ui.startPage.Main;
import hall_management.ui.student.Student;
import hall_management.util.Interface.Controller;
import hall_management.util.Interface.Scenes;
import hall_management.util.Interface.Table;
import hall_management.util.SceneLoader;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class ApplySeatFormController implements Initializable, Controller {

    @FXML
    private JFXButton button_apply;
    @FXML
    private JFXTextField textField_hallName;
    @FXML
    private JFXTextField textField_currentRoomNo;
    @FXML
    private JFXTextField textField_room_no_1;
    @FXML
    private JFXTextField textField_room_no_2;
    @FXML
    private JFXTextField textField_room_no_3;
    @FXML
    private JFXTextField textField_room_no_4;
    @FXML
    private JFXTextField textField_applicationDate;
    @FXML
    private JFXTextField textField_studentID;
    @FXML
    private JFXCheckBox checkBox_SelectiveRoom;
    @FXML
    private JFXButton button_showAvailable_RoomList;
    @FXML
    private JFXButton button_Back;

    private String room_no_1;
    private String room_no_2;
    private String room_no_3;
    private String room_no_4;

    private String applicationID;
    private String hall_id;
    private String current_room_no;
    
    String studentID;
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        setAllEditable(false);
        System.out.println("<><><><>Inside ApplySeatFormController...");

    }

    private void loadInitialInfo() throws Exception {
        Student std = null;
        std = Query.getStudentHallRelatedInfo(studentID);
        hall_id = std.getHallID();
        current_room_no = std.getCurrentRoomNumber();

        textField_hallName.setText(std.getHallName());
        textField_currentRoomNo.setText(std.getCurrentRoomNumber());
        textField_studentID.setText(studentID);
        textField_applicationDate.setText(Application_Queries.getCurrentDate());
    }

    public JFXButton getButton_showAvailable_RoomList() {
        return button_showAvailable_RoomList;
    }

    public void setButton_showAvailable_RoomList(JFXButton button_showAvailable_RoomList) {
        this.button_showAvailable_RoomList = button_showAvailable_RoomList;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
        try {
            loadInitialInfo();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            applicationID = Application_Queries.getNextApplicationID();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    
    private boolean check_Whether_Available_Rooms_are_Right() throws Exception {
        int maxRooms = 4;
        String[] rooms = new String[maxRooms];
        getTextFromTextFields();
        rooms[0] = room_no_1;
        rooms[1] = room_no_2;
        rooms[2] = room_no_3;
        rooms[3] = room_no_4;
        boolean flag = true;
        try{
            int x;
            for(int i=0; i<rooms.length; i++){
                if(rooms[i].equals("") == false){
                    x = Integer.parseInt(rooms[i]);
                }
            }
        }catch(Exception e){
            PopUpWindow.displayInCheck("ERROR!", "ROOMS MUST BE IN NUMBERS!!");
            return false;
        }
        
        for (int i = 0; i < maxRooms; i++) {
            System.out.println("<><>ROOMS ARE .. " + rooms[i]);
            if (rooms[i].equals("")) {
                System.out.println("<><><THIS ROOM IS NULL idx = " + i);
            }
        }
        System.out.println("<><><>CHECKING check_Whether_Available_Rooms_are_Right() ... \n");
        for (int i = 0; i < maxRooms; i++) {
            if (rooms[i].equals("")) {
                continue;
            } else {
//                rooms[i] = "' " + rooms[i] + " '";
                flag = Query.isIn(Table.Room, rooms[i]);
                if (flag == false) {
                    PopUpWindow.displayInCheck("ERROR!", "Room Number(s) are wrong!");
                    return flag;
                }
            }
        }

        return flag;
    }

    private void getTextFromTextFields() {
        room_no_1 = textField_room_no_1.getText();
        room_no_2 = textField_room_no_2.getText();
        room_no_3 = textField_room_no_3.getText();
        room_no_4 = textField_room_no_4.getText();
    }

    @FXML
    private void send_this_application() throws Exception {
        //On Clicking button_apply
        if (check_Whether_Available_Rooms_are_Right() == false) {
//            PopUpWindow.displayInCheck("ERROR!", "Room Number(s) are wrong!");
            clearScreen();
            return;
        }
        try {
            if (checkBox_SelectiveRoom.isSelected() == true) {
                send_with_roomList();
            } else {
                send_normal(false);
            }

        } catch (Exception e) {
            e.printStackTrace();
            PopUpWindow.displayInCheck("EXCEPTION OCCURED!", "Application CANNOT BE SENT !!");
        }
        PopUpWindow.displaySuccess("SUCCESS!!", "ADD ANOTHER APPLICATION AGAIN!!", "ADD ANOTHER APPLICATION");
        try {
            applicationID = Application_Queries.getNextApplicationID();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        Application_Queries.insertIntoApplication_WithAvailableRooms();
    }

    @FXML
    private void show_Available_Room_list() throws Exception {
        AvailableRoomList.openAvailableRoomList();
    }

    @FXML
    private void goBack() {
//        SceneLoader.loadScene(Scenes.student_ui, this);
        SceneLoader.closeScene(SceneLoader.CurrentScene());
        SceneLoader.loadPreviousScene(Scenes.student_ui, this);
    }

    private void clearScreen() {
//        textField_hallName.setText("");

//        textField_currentRoomNo.setText("");
        textField_room_no_1.setText(null);

        textField_room_no_2.setText(null);

        textField_room_no_3.setText(null);

        textField_room_no_4.setText(null);

//        textField_applicationDate.setText("");
//        textField_studentID.setText("");
    }

    private void setAllEditable(boolean flag) {
        textField_hallName.setEditable(false);

        textField_currentRoomNo.setEditable(false);

        textField_room_no_1.setEditable(flag);

        textField_room_no_2.setEditable(flag);

        textField_room_no_3.setEditable(flag);

        textField_room_no_4.setEditable(flag);

        textField_applicationDate.setEditable(false);

        textField_studentID.setEditable(false);
    }

    @FXML
    private void makeFieldsEditable() {
        if (checkBox_SelectiveRoom.isSelected() == true) {
            setAllEditable(true);
        } else {
            setAllEditable(false);
        }
    }

    private void send_with_roomList() {
        boolean flag = true;
        getTextFromTextFields();
        try {
            flag = check_Whether_Available_Rooms_are_Right();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (flag == false) {
            PopUpWindow.displayInCheck("ERROR!!", "Room Number(s) Are Wrong!");
            clearScreen();
            return;
        }
        if (flag == true) {
            send_normal(true);
            try {
                if (room_no_1.equals("") == false) {
                    Application_Queries.insert_into_Room_List(applicationID, hall_id, room_no_1);
                }
                if (room_no_2.equals("") == false) {
                    Application_Queries.insert_into_Room_List(applicationID, hall_id, room_no_2);
                }
                if (room_no_3.equals("") == false) {
                    Application_Queries.insert_into_Room_List(applicationID, hall_id, room_no_3);
                }
                if (room_no_4.equals("") == false) {
                    Application_Queries.insert_into_Room_List(applicationID, hall_id, room_no_4);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void send_normal(boolean hasRoom) {
        boolean flag = true;

        char has_ROOM;
        if (hasRoom == false) {
            has_ROOM = 'N';
        } else {
            has_ROOM = 'Y';
        }
        String application_date = Application_Queries.getCurrentDate();
        try {
            Application_Queries.insert_Into_Application(applicationID, Main.studentID, hall_id, application_date, has_ROOM, current_room_no);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
