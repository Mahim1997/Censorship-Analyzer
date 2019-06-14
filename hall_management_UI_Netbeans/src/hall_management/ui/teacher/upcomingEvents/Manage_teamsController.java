/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hall_management.ui.teacher.upcomingEvents;

import hall_management.db.queries.Query;
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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author esfs
 */
public class Manage_teamsController implements Initializable, Controller {

    /**
     * Initializes the controller class.
     */
    public static String sport_to_use;
    public static String team_id_to_use;
    public String event_id;
    @FXML
    private TableView tableview;
    @FXML
    private TableColumn col_teamName;
    @FXML
    private TableColumn col_sport;
    @FXML
    private TableColumn col_eventYear;
    @FXML
    private TableColumn col_hallName;
    @FXML
    private TableColumn col_verdict;
 
    @FXML
    private Text textu_Uporer_part;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.event_id = ManageUpcomingEventsController.event_id_toManage;
        textu_Uporer_part.setText("Event ID: " + event_id + " , Teacher ID : " + Main.teacherID);
        System.out.println("=-->>Manage_teamsControlle.eventID = " + this.event_id);
        try {
            initialiseTableView();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void initialiseTableView() throws Exception {
        List<Teams> list = new ArrayList<>();
//        list = Event_Queries.getListOfEventsSupervisedBy(Main.teacherID, "UPCOMING");
        list = Event_Queries.getListOfTeamsForEventID(event_id, ManageUpcomingEventsController.event_status);
//        showThisListToWindow(list);
        List<Teams> list2 = new ArrayList<>();
        Teams e;
        Teams e2;
        for (int i = 0; i < list.size(); i++) {
            e = list.get(i);
            e2 = new Teams(this, "VERDICT");
            e2.setCaptainStudentID(e2.getCaptainStudentID());
            e2.setHallID(e.getHallID());
            e2.setHallName(e.getHallName());
            e2.setTeam_id(e.getTeam_id());
            e2.setTeam_name(e.getTeam_name());
            e2.setTeam_sport(e.getTeam_sport());
            e2.setTeam_type(e.getTeam_type());
            e2.setTeam_year(e.getTeam_year());

            e2.setHallName(Query.getFieldInfoFromTable(Table.Hall, e2.getHallID(), "HALL_NAME"));
            list2.add(e2);
        }
        ObservableList<Teams> data = FXCollections.observableArrayList(list2);
        /*
            String team_id;
    String team_name;
    String team_year;
    String team_sport;
    String team_type;
    String hallID;
    String hallName;
         */
        col_eventYear.setCellValueFactory(
                new PropertyValueFactory<>("team_year")
        );
        col_hallName.setCellValueFactory(
                new PropertyValueFactory<>("hallName")
        );
        col_sport.setCellValueFactory(
                new PropertyValueFactory<>("team_sport")
        );
        col_teamName.setCellValueFactory(
                new PropertyValueFactory<>("team_name")
        );
        col_verdict.setCellValueFactory(
                new PropertyValueFactory<>("verdictButton")
        );
 
//        col_eventSport.setCellValueFactory(
//                new PropertyValueFactory<>("event_sport")
//        );
        tableview.setItems(data);
    }

    void runVerdictWindow(String team_id, String sport) {
        System.out.println("=-->>Run verdict window for " + team_id);
        Manage_teamsController.team_id_to_use = team_id;
        Manage_teamsController.sport_to_use = sport;
        SceneLoader.closeNewWindow(Scenes.teacher_load_teams_for_events);
        SceneLoader.loadSceneInADifferentWindow(Scenes.teacher_acceptOrReject_Teams, this);
    }

    @FXML
    private void goBack() {
        SceneLoader.closeNewWindow(Scenes.teacher_load_teams_for_events);
        SceneLoader.loadSceneInADifferentWindow(Scenes.teacher_manage_upcoming_events
                , this);
    }
}
