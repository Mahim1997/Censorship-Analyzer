package hall_management.ui.teacher.applications;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import hall_management.db.queries.Application_Queries;
import hall_management.db.queries.Functions;
import hall_management.ui.popup.PopUpWindow;
import hall_management.ui.startPage.Main;
import hall_management.ui.student.applySeat.AvailableRoomList;
import hall_management.util.Interface.Controller;
import hall_management.util.Interface.Scenes;
import hall_management.util.SceneLoader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class Application_AcceptedFormController implements Initializable, Controller {

    public static String applicationID;
    public static String hallID;
    public static String[] rooms;
    public static String hasMultipleRooms;
    public static String studentID;
    public static String applicationDate;
//    public  static String applicationStatus;///accepted
    public static String verdictDate;

    @FXML
    private AnchorPane pane;
    @FXML
    private Text text_Uporer;
    @FXML
    private JFXTextField textLabel_applicationID;
    @FXML
    private JFXTextField textLabel_studentID;
    @FXML
    private JFXTextField textLabel_allottedRoomNumber;
    @FXML
    private JFXTextField textLabel_appDate;
    @FXML
    private JFXTextField textLabel_verdictDate;
    @FXML
    private TableView tableview;
    private TableColumn roomNo_col;
    private TableColumn roomCap_col;
    private TableColumn noOfResidents_col;
    private TableColumn btn_addToApplication_col;
    @FXML
    private JFXButton button_acceptApplication;
    @FXML
    private JFXTextField textField_applicationStatus;

    public static String allottedRoomNumber;
    @FXML
    private JFXTextField textField_hasMultipleRooms;
    @FXML
    private AnchorPane pane_Smaller;

//    private String allotted_room_no;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
//        System.out.println("<><>INITIALISE.. this.applicationID = " + Application_AcceptedFormController.applicationID);
        String text = "Application ID: " + Application_AcceptedFormController.applicationID + " , Teacher ID : " + Main.teacherID;
        text_Uporer.setText(text);
        loadTextFields();
        setAllTextFields(false);
        try {
//            loadRooms(Application_AcceptedFormController.applicationID);
//            refreshInfo();
            showRoomListToWindow();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void goBack(ActionEvent event) {
        SceneLoader.closeScene(SceneLoader.CurrentScene());
        SceneLoader.loadPreviousScene(Scenes.teacher_see_Applications, this);
    }

    @FXML
    private void acceptThisApplication(ActionEvent event) {
        try{
//            Application_Queries.
            Application_Queries.updateApplicationField(Application_AcceptedFormController.applicationID, "ALLOTTED_ROOM_NO", textLabel_allottedRoomNumber.getText());
            Application_Queries.updateApplicationField(Application_AcceptedFormController.applicationID, "STATUS", "ACCEPTED");
            PopUpWindow.displaySuccess("SUCCESS!", "APPLICATION ACCEPTED NOW GOING BACK!!", "GO BACK");
            SceneLoader.loadScene(Scenes.teacher_see_Applications, this);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void setAllTextFields(boolean flag) {

        textLabel_applicationID.setEditable(false);

        textLabel_studentID.setEditable(false);

        textLabel_allottedRoomNumber.setEditable(false);

        textLabel_appDate.setEditable(false);

        textLabel_verdictDate.setEditable(false);

        textField_applicationStatus.setEditable(false);

        textField_hasMultipleRooms.setEditable(false);
    }

    private void loadTextFields() {
        Application_AcceptedFormController.hallID = Functions.getHallID(Main.teacherID);
        textLabel_applicationID.setText(Application_AcceptedFormController.applicationID);

        textLabel_studentID.setText(Application_AcceptedFormController.studentID);

        textLabel_allottedRoomNumber.setText("NOT YET!");

        textLabel_appDate.setText(Application_AcceptedFormController.applicationDate);

        textLabel_verdictDate.setText(Application_Queries.getCurrentDate());

        textLabel_allottedRoomNumber.setText(Application_AcceptedFormController.allottedRoomNumber);

        if (hasMultipleRooms.equals("Y")) {
            hasMultipleRooms = "YES";
        } else if (hasMultipleRooms.equals("N")) {
            hasMultipleRooms = "NO";
        }
        textField_hasMultipleRooms.setText(Application_AcceptedFormController.hasMultipleRooms);
    }

 
    private void showRoomListToWindow() throws Exception {
        if (Application_AcceptedFormController.hasMultipleRooms.equals("YES")) {
            getRoomsOfApplication();
        } else {
            getRoomsOfHall(Application_AcceptedFormController.hallID);
        }
    }

    private void getRoomsOfHall(String hallID) throws Exception {
        List<AvailableRoomList> list = new ArrayList<>();
        list = Application_Queries.getListOfRoomsForThisHallID(hallID);
        loadAllColumnsToWindow(list);
    }

    private void getRoomsOfApplication() throws Exception {
        List<AvailableRoomList> list = new ArrayList<>();
        list = Application_Queries.getListOfRoomsForThisApplicationID(applicationID);
        loadAllColumnsToWindow(list);
    }

    private void loadAllColumnsToWindow(List<AvailableRoomList> list) {
        System.out.println("=-->><>><>JUST PRINTING ROOMS .. ");
        List<Room_With_Buttons> listWithButtons = new ArrayList<>();
        AvailableRoomList room;
        Room_With_Buttons roomWithButtons;
        for (int i = 0; i < list.size(); i++) {
            room = list.get(i);
            roomWithButtons = new Room_With_Buttons(Functions.getHallID(Main.teacherID), room.getRoom_no(),
                    room.getRoom_capacity(), room.getCurrent_no_ppl(), this);
            listWithButtons.add(roomWithButtons);
        }

        roomNo_col = new TableColumn("Room No");
        roomCap_col = new TableColumn("Room Capacity");
        noOfResidents_col = new TableColumn("Num Students");
        btn_addToApplication_col = new TableColumn("CHOOSE ROOM");
        
        
        tableview.getColumns().addAll(roomNo_col, roomCap_col, noOfResidents_col, btn_addToApplication_col);
        ObservableList<Room_With_Buttons> data = FXCollections.observableArrayList(listWithButtons);
        /*
        public String hallId;
        public String roomNum;
        public String roomCapacity;
        public String currentNumStudents;

        public Button button_addRoom;
         */
        roomNo_col.setCellValueFactory(
                new PropertyValueFactory<>("roomNum")
        );
        roomCap_col.setCellValueFactory(
                new PropertyValueFactory<>("roomCapacity")
        );
        noOfResidents_col.setCellValueFactory(
                new PropertyValueFactory<>("currentNumStudents")
        );
        btn_addToApplication_col.setCellValueFactory(
                new PropertyValueFactory<>("button_addRoom")
        );
       
        tableview.setItems(data);
    }

    void runButtonClick(String roomNum) {
//        System.out.println("=--->>>BUTTON CLICKED FOR ROOM NUM = " + roomNum);
        textLabel_allottedRoomNumber.setText(roomNum);
    }

   
}
