package hall_management.ui.student.seeApplicationLog;

import com.jfoenix.controls.JFXButton;
import hall_management.db.queries.Application_Queries;
import hall_management.ui.startPage.Main;
import hall_management.util.Interface.Controller;
import hall_management.util.Interface.Scenes;
import hall_management.util.SceneLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
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

public class See_ApplicationsController implements Initializable, Controller {


    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Text text_EKDOM_UPORER;
    @FXML
    private TableView tableview;
    @FXML
    private TableColumn col_application_id;
    @FXML
    private TableColumn col_application_date;
    @FXML
    private TableColumn col_applicationStatus;
    @FXML
    private TableColumn col_allotted_room_no;
    @FXML
    private TableColumn btnCol;
    @FXML
    private TableColumn col_verdictDate;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        initialiser();
    }

    @FXML
    private void goBack(ActionEvent event) {
//        SceneLoader.loadScene(Scenes.student_ui, this);
        SceneLoader.closeScene(SceneLoader.CurrentScene());
        SceneLoader.loadPreviousScene(Scenes.student_ui, this);
    }

 

    private List<Application_With_Button> getApplicationList(String id) {

        List<Application_Entity> list = new ArrayList<>();
        try {
            list = Application_Queries.loadApplicationsOfThisStudent(id);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        
        List<Application_With_Button> toReturnList = new ArrayList<>();
        Application_Entity entity = new Application_Entity();
        Application_With_Button app = new Application_With_Button();
        for(int i=0; i<list.size(); i++){
            entity = list.get(i);
            
            app = new Application_With_Button(entity.getApplicationID(), entity.getApplicationDate(), entity.getVerdictDate()
                    , entity.getApplicationStatus(), entity.getAllotted_room_number(),entity.getCurrentRoomID(), entity.getVerdict()
                    ,entity.getHasRoom() , entity.getStudentId()
            , this);
            toReturnList.add(app);
            
        }
        
        return toReturnList;
    }

    private void initialiser() {

//        TableColumn col_application_id = new TableColumn("Application ID");
//        TableColumn col_application_date = new TableColumn("Application Date");
//        TableColumn col_verdictDate = new TableColumn("Verdict Date");
//        TableColumn col_applicationStatus = new TableColumn("Application Status");
//        TableColumn col_verdict = new TableColumn("Verdict");
//        TableColumn col_allotted_room_no = new TableColumn("Allotted Room No");
//        TableColumn btnCol = new TableColumn("See Applied Room(s)");
//        TableColumn hasRoomsCol = new TableColumn("Has Multiple Rooms");
        
//        tableview.getColumns().addAll(col_application_id, col_application_date, col_verdictDate, col_applicationStatus,col_allotted_room_no, btnCol);
//        final ObservableList<Application> data = getObservableArrayList(Main.studentID);

//        ObservableList<Application> data = getObservableArrayList(Main.studentID);
        List<Application_With_Button> list = new ArrayList<>();
        list = getApplicationList(Main.studentID);

        ObservableList<Application_With_Button> data = FXCollections.observableArrayList(list);

        col_application_id.setCellValueFactory(
                new PropertyValueFactory<>("applicationID")
        );
        col_application_date.setCellValueFactory(
                new PropertyValueFactory<>("applicationDate")
        );
        col_verdictDate.setCellValueFactory(
                new PropertyValueFactory<>("verdictDate")
        );
        col_applicationStatus.setCellValueFactory(
                new PropertyValueFactory<>("applicationStatus")
        );
//        col_verdict.setCellValueFactory(
//                new PropertyValueFactory<>("verdict")
//        );
        col_allotted_room_no.setCellValueFactory(
                new PropertyValueFactory<>("allotted_room_number")
        );
        btnCol.setCellValueFactory(
                new PropertyValueFactory<>("button")
        );
//        hasRoomsCol.setCellValueFactory(
//                new PropertyValueFactory<>("hasRoom")
//        );
        tableview.setItems(data);

    }

    void runSeeApplicationListForApplicationID(String applicationID) {
        SceneLoader.loadSceneInADifferentWindow(Scenes.student_see_application_room_list, this);
    }

}
