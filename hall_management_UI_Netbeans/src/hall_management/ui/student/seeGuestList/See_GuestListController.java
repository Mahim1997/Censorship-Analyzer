package hall_management.ui.student.seeGuestList;

import com.jfoenix.controls.JFXButton;
import hall_management.db.queries.Query;
import hall_management.ui.pushNotification.Notification;
import hall_management.ui.startPage.Main;
import static hall_management.ui.student.seeGuestList.AllowedGuest_WithButton.Guest_NID_For_Guest_Log;
import static hall_management.ui.student.seeGuestList.AllowedGuest_WithButton.Guest_Name_For_Guest_Log;
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
import javafx.scene.text.Text;

public class See_GuestListController implements Initializable, Controller {

    private Text text_UporerPart;
    @FXML
    private JFXButton button_Back;

    private String uporerPartErText;
    @FXML
    private TableView tableview;
    @FXML
    private TableColumn col_nid;
    @FXML
    private TableColumn col_fullName;
    @FXML
    private TableColumn col_relation;
    @FXML
    private TableColumn col_add;
    @FXML
    private TableColumn col_contactNo;
    @FXML
    private TableColumn btnCol;
    @FXML
    private TableColumn btn_removeGuestCol;
    @FXML
    private Text text_Uporer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try {
            runInitialQuery();
            loadTableView();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void goBack(ActionEvent event) {
//        SceneLoader.loadScene(Scenes.student_ui, this);
        SceneLoader.closeScene(SceneLoader.CurrentScene());
        SceneLoader.loadPreviousScene(Scenes.student_ui, this);
    }

    private void loadTableView() throws Exception {
        List<AllowedGuest> allowedGuestList = Query.getAllowedGuests(Main.studentID);

        List<AllowedGuest> list = new ArrayList<>();
        for (int i = 0; i < allowedGuestList.size(); i++) {
            AllowedGuest gst = allowedGuestList.get(i);
            AllowedGuest newGest = new AllowedGuest(this);
            newGest.setGuestAddress(gst.getGuestAddress());
            newGest.setGuestContactNumber(gst.getGuestContactNumber());
            newGest.setGuestFullName(gst.getGuestFullName());
            newGest.setNID(gst.getNID());
            newGest.setRelationWithStudent(gst.getRelationWithStudent());
            newGest.setStudentId(gst.getStudentId());
            list.add(newGest);
        }

        initialiseTableViewUsing(list);

    }

    private void runInitialQuery() throws Exception {
        String[] arr = Query.findHallIDAndNameOfStudent(Main.studentID);
        String hall_id = arr[0];
        String hall_name = arr[1];
        uporerPartErText = "Student ID : " + Main.studentID + " , Hall Name : " + hall_name;
        System.out.println("UPORER TEXT IS " + uporerPartErText);
        text_Uporer.setText(uporerPartErText);
    }

    /*
        public String NID ;
    public String studentId;
    public String relationWithStudent;
    public String guestFullName;
    public String guestAddress;
    public String guestContactNumber;
     */
    private void initialiseTableViewUsing(List<AllowedGuest> list) {

//        ObservableList<Application> data = getObservableArrayList(Main.studentID);
        ObservableList<AllowedGuest> data = FXCollections.observableArrayList(list);

        col_nid.setCellValueFactory(
                new PropertyValueFactory<>("NID")
        );
        col_fullName.setCellValueFactory(
                new PropertyValueFactory<>("guestFullName")
        );
        col_relation.setCellValueFactory(
                new PropertyValueFactory<>("relationWithStudent")
        );
        col_add.setCellValueFactory(
                new PropertyValueFactory<>("guestAddress")
        );
        col_contactNo.setCellValueFactory(
                new PropertyValueFactory<>("guestContactNumber")
        );

        btnCol.setCellValueFactory(
                new PropertyValueFactory<>("button")
        );
        btn_removeGuestCol.setCellValueFactory(
                new PropertyValueFactory<>("btn_delete")
        );

        tableview.setItems(data);
    }

    void runSeeGuestLog(String NID, String guestFullName) {
        Guest_NID_For_Guest_Log = NID;
        Guest_Name_For_Guest_Log = guestFullName;
        SceneLoader.closeScene(SceneLoader.CurrentScene());
//            System.out.println("<><><> See Guest Log for guestNID = " + Guest_NID_For_Guest_Log);
        SceneLoader.loadScene(Scenes.student_see_guestLog, this);
    }

    void removeGuest(String NID)throws Exception{
        Query.removeGuest(NID, Main.studentID);
        Notification.push("SUCCESS!", "Guest is removed!", Notification.SUCCESS);
        loadTableView();
    }

}
