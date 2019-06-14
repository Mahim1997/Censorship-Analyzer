/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hall_management.ui.teacher.upcomingEvents;

import hall_management.db.queries.Query;
import hall_management.db.queries.adminQueries.AdminQueries;
import hall_management.db.queries.more_queries.Event_Queries;
import hall_management.ui.startPage.Main;
import hall_management.ui.teacher.events.Events;
import hall_management.util.Interface.Controller;
import hall_management.util.Interface.Scenes;
import hall_management.util.Interface.Table;
import hall_management.util.SceneLoader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author esfs
 */
public class ManageUpcomingEventsController implements Initializable, Controller {

    public static String event_status;
    @FXML
    private Text textu_UporerPart;
    @FXML
    private TableColumn col_eventID;
    @FXML
    private TableColumn col_eventName;
    @FXML
    private TableColumn col_eventYear;
    @FXML
    private TableColumn col_eventSport;
    @FXML
    private TableColumn col_eventLocalOrGlobal;
    @FXML
    private TableColumn col_eventHallName;
    @FXML
    private TableColumn col_event_buttonMangeTeams;
    @FXML
    private TableView tableview;
    public static String event_id_toManage;
    @FXML
    private TableColumn col_event_finishing_status;

    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        String text = "Upcoming Events Of Teacher ID : " + Main.teacherID;
        textu_UporerPart.setText(text);

        try {
            initialiseTableView();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void initialiseTableView() throws Exception {
        List<Events> list = new ArrayList<>();
        list = Event_Queries.getListOfEventsSupervisedBy(Main.teacherID, "UPCOMING");
        showThisListToWindow(list);
    }

    private void showThisListToWindow(List<Events> list) throws Exception {
        List<Events> list2 = new ArrayList<>();
        Events e;
        Events e2;
        for (int i = 0; i < list.size(); i++) {
            e = list.get(i);
            e2 = new Events(this);
            e2.setEvent_id(e.getEvent_id());
            e2.setEvent_name(e.getEvent_name());
            e2.setEvenr_supervisorID(e.getEvenr_supervisorID());
            e2.setEvent_sport(e.getEvent_sport());
            e2.setEvent_status(e.getEvent_status());
            e2.setEvent_type(e.getEvent_type());
            e2.setEvent_type(e.getEvent_type());
            e2.setEvent_year(e.getEvent_year());
            e2.setHall_id(e.getHall_id());
            e2.setRunnerUp_team_id(e.getRunnerUp_team_id());
            e2.setWinner_team_id(e.getWinner_team_id());
            e2.setEvent_hallName(Query.getFieldInfoFromTable(Table.Hall, e2.getHall_id(), "HALL_NAME"));
            list2.add(e2);
        }
        ObservableList<Events> data = FXCollections.observableArrayList(list2);
        /*
            private String event_id;
    private String event_name;
    private String event_year;
    private String event_sport;
    private String event_type;
    private String event_status;
    private String hall_id;
    private String evenr_supervisorID;
    private String winner_team_id;
    private String runnerUp_team_id;
         */
        col_eventID.setCellValueFactory(
                new PropertyValueFactory<>("event_id")
        );
        col_eventName.setCellValueFactory(
                new PropertyValueFactory<>("event_name")
        );
        col_eventYear.setCellValueFactory(
                new PropertyValueFactory<>("event_year")
        );
        col_eventSport.setCellValueFactory(
                new PropertyValueFactory<>("event_sport")
        );
        col_eventLocalOrGlobal.setCellValueFactory(
                new PropertyValueFactory<>("event_type")
        );
        col_eventHallName.setCellValueFactory(
                new PropertyValueFactory<>("event_hallName")
        );
        col_event_buttonMangeTeams.setCellValueFactory(
                new PropertyValueFactory<>("button_Manage")
        );

        col_event_finishing_status.setCellValueFactory(
                new PropertyValueFactory<>("btn_changeEventStatus")
        );
//        col_eventSport.setCellValueFactory(
//                new PropertyValueFactory<>("event_sport")
//        );
        tableview.setItems(data);
    }

    public void runManageTeamsForEvent(String event_id, String event_status) {
        Stage stage = SceneLoader.getStageOfNewWindow(Scenes.teacher_manage_upcoming_events);
        stage.close();
        ManageUpcomingEventsController.event_id_toManage = event_id;
        ManageUpcomingEventsController.event_status = event_status;
        SceneLoader.loadSceneInADifferentWindow(Scenes.teacher_load_teams_for_events, this);
//        Controller controller =  
    }

    public void runChangeStatus(String event_id) throws Exception {
        WinnerAndRunnerUpSelectroController.eventIDToFinish = event_id;
        SceneLoader.closeNewWindow(Scenes.teacher_manage_upcoming_events);
        SceneLoader.loadSceneInADifferentWindow(Scenes.teacher_event_winnerSelector, this);

//        AdminQueries.updateField(Table.Event, "EVENT_STATUS", "OVER", "EVENT_ID", event_id);
//        AdminQueries.updateField("TABLE_TEAM_EVENT_HISTORY", "STATUS", "OVER", "EVENT_ID", event_id);
//        initialiseTableView();
    }

}
/*
    private TableColumn col_eventID;
    @FXML
    private TableColumn col_eventName;
    @FXML
    private TableColumn col_eventYear;
    @FXML
    private TableColumn col_eventSport;
    @FXML
    private TableColumn col_eventLocalOrGlobal;
    @FXML
    private TableColumn col_eventHallName;
    @FXML
    private TableColumn col_event_buttonMangeTeams;
 */
