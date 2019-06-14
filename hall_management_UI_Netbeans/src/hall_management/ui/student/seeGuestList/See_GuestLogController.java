package hall_management.ui.student.seeGuestList;

import com.jfoenix.controls.JFXButton;
import hall_management.db.queries.Student_Query;
import hall_management.ui.startPage.Main;
import hall_management.util.Interface.Controller;
import hall_management.util.Interface.Scenes;
import hall_management.util.SceneLoader;
import java.net.URL;
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

public class See_GuestLogController implements Initializable, Controller {

    private Text text_UporerPart;
    @FXML
    private TableView tableview;
    @FXML
    private JFXButton button_Back;
    @FXML
    private Text text_Uporer;
    @FXML
    private TableColumn col_visitingDate;
    @FXML
    private TableColumn col_startTime;
    @FXML
    private TableColumn col_endTime;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
//        System.out.println("<><><>==--->>> INSIDE INITIALISER... GUEST NID = " + AllowedGuest_WithButton.Guest_NID_For_Guest_Log);
        String uporer_txt = "Student ID : " + Main.studentID + " , Guest NID : " + AllowedGuest_WithButton.Guest_NID_For_Guest_Log
                + " , Guest Name : " + AllowedGuest_WithButton.Guest_Name_For_Guest_Log;
        System.out.println("UPERER TEXT : " + uporer_txt);
        try {
            loadUporerText();
            loadTableView(AllowedGuest_WithButton.Guest_NID_For_Guest_Log);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void goBack(ActionEvent event) {
//        SceneLoader.loadScene(Scenes.student_see_guest_list, this);
        SceneLoader.closeScene(SceneLoader.CurrentScene());
        SceneLoader.loadPreviousScene(Scenes.student_see_guest_list, this);
    }

    private void loadUporerText() throws Exception {
        String uporer_txt = "Student ID : " + Main.studentID + " , Guest NID : " + AllowedGuest_WithButton.Guest_NID_For_Guest_Log
                + " , Guest Name : " + AllowedGuest_WithButton.Guest_Name_For_Guest_Log;

        text_Uporer.setText(uporer_txt);
    }

    /*
    private String visitingDate;
    private String startTime;
    private String endTime;
     */
    private void loadTableView(String guestNID) throws Exception {
        List<Guest_Log> list = Student_Query.getGuestLogOf(Main.studentID, guestNID);

//        tableview.getColumns().addAll(col_visitingDate, col_startTime, col_endTime);
        ObservableList<Guest_Log> data = FXCollections.observableArrayList(list);

        col_visitingDate.setCellValueFactory(
                new PropertyValueFactory<>("visitingDate")
        );
        col_startTime.setCellValueFactory(
                new PropertyValueFactory<>("startTime")
        );
        col_endTime.setCellValueFactory(
                new PropertyValueFactory<>("endTime")
        );

        tableview.setItems(data);

    }

}
